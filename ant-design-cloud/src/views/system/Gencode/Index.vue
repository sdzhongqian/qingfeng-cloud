<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="12">
            <a-col :md="6" :sm="12">
              <a-form-item label="表名称">
                <a-input v-model="queryParam.name" placeholder="" />
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="12">
              <a-form-item label="表描述">
                <a-input v-model="queryParam.table_comment" placeholder="" />
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="12">
              <a-form-item label="表时间">
                <a-range-picker
                  :show-time="{ format: 'HH:mm' }"
                  format="YYYY-MM-DD HH:mm"
                  :placeholder="['开始时间', '结束时间']"
                  @change="selectDate"
                />
              </a-form-item>
            </a-col>
            <a-col :md="3" :sm="12">
              <a-button
                type="primary"
                @click="fetch({ pageSize: 10, ...queryParam })"
                >查询</a-button
              >
              <a-button
                style="margin-left: 8px"
                @click="() => (queryParam = {})"
                >重置</a-button
              >
            </a-col>
          </a-row>
        </a-form>
      </div>
      <div style="margin: 0 0 10px 0">
        <a-space size="small">
          <a-button
            v-if="authButton.includes('gencode:gencode')"
            @click="gencodes()"
            ><a-icon type="plus" />代码生成</a-button
          >
          <a-button
            type="primary"
            v-if="authButton.includes('gencode:import')"
            @click="importTable({})"
            ><a-icon type="upload" />导入</a-button
          >
          <a-button
            v-if="authButton.includes('gencode:edit')"
            type="primary"
            @click="edits()"
            ><a-icon type="edit" />编辑</a-button
          >
          <a-button
            v-if="authButton.includes('gencode:del')"
            type="danger"
            @click="dels()"
            ><a-icon type="delete" />删除</a-button
          >
        </a-space>
      </div>

      <a-table
        :row-selection="rowSelection"
        :columns="columns"
        :data-source="data"
        :pagination="pagination"
        :loading="loading"
        @change="handleTableChange"
        bordered
      >
        <template slot-scope="text, record, index">
          <div :key="col">
            {{ text }}
          </div>
        </template>
        <template slot="operation" slot-scope="text, record, index">
          <div class="editable-row-operations">
            <a-space size="small">
              <a-button
                size="small"
                type="primary"
                v-if="authButton.includes('gencode:viewCode')"
                @click="() => viewCode(record)"
                >预览</a-button
              >
              <a-button
                size="small"
                type="primary"
                v-if="authButton.includes('gencode:gencode')"
                @click="() => gencode(record)"
                >生成</a-button
              >
              <a-button
                size="small"
                v-if="authButton.includes('gencode:info')"
                @click="() => info(record)"
                >详情</a-button
              >
              <a-button
                size="small"
                type="primary"
                v-if="authButton.includes('gencode:edit')"
                @click="() => edit(record)"
                >编辑</a-button
              >
              <a-button
                size="small"
                type="danger"
                v-if="authButton.includes('gencode:del')"
                @click="() => del(record.id)"
                >删除</a-button
              >
            </a-space>
          </div>
        </template>
      </a-table>
    </a-card>
  </page-header-wrapper>
</template>
<script>
import { mapState } from "vuex";
import { getListPage, delData, gencode } from "@/api/system/gencode";
import EditForm from "./Edit";
import InfoForm from "./Info";
import ViewCodeForm from "./ViewCode"
import SelectTable from "./SelectTable";
const columns = [
  {
    title: "表名称",
    dataIndex: "table_name",
    sorter: true,
    ellipsis: true,
  },
  {
    title: "表描述",
    dataIndex: "table_comment",
    ellipsis: true,
  },
  {
    title: "模板类型",
    dataIndex: "temp_type",
    customRender: (text, row, index) => {
      if (text == "0") {
        return "单表";
      } else if (text == "1") {
        return "树表";
      }
    },
  },
  {
    title: "生成包路径",
    dataIndex: "pack_path",
    ellipsis: true,
  },
  {
    title: "模块名称",
    dataIndex: "mod_name",
    ellipsis: true,
  },
  {
    title: "业务名称",
    dataIndex: "bus_name",
    ellipsis: true,
  },
  {
    title: "创建时间",
    dataIndex: "create_time",
  },
  {
    title: "操作",
    dataIndex: "operation",
    scopedSlots: { customRender: "operation" },
    width: "280px",
  },
];

export default {
  data() {
    return {
      data: [],
      pagination: {},
      loading: false,
      columns,
      selectedRows: [],
      queryParam: {
        name: "",
        table_comment: "",
        start_time: "",
        end_time: "",
      },
    };
  },
  components: {
    EditForm,
    InfoForm,
    SelectTable,
    ViewCodeForm,
  },
  computed: {
    ...mapState({
      // 动态主路由
      authButton: (state) => state.user.authButton,
    }),
    rowSelection() {
      return {
        onChange: (selectedRowKeys, selectedRows) => {
          this.selectedRows = selectedRows;
          console.log(
            `selectedRowKeys: ${selectedRowKeys}`,
            "selectedRows: ",
            selectedRows
          );
        },
        getCheckboxProps: (record) => ({
          props: {
            disabled: record.name === "Disabled User", // Column configuration not to be checked
            name: record.name,
          },
        }),
      };
    },
  },
  mounted() {
    this.fetch({ pageSize: 10 });
  },
  methods: {
    selectDate(value, dateString) {
      this.queryParam.start_time = dateString[0];
      this.queryParam.end_time = dateString[1];
    },
    handleTableChange(pagination, filters, sorter) {
      console.log(pagination);
      const pager = { ...this.pagination };
      pager.current = pagination.current;
      this.pagination = pager;
      this.fetch({
        pageSize: pagination.pageSize,
        pageNum: pagination.current,
        ...filters,
      });
    },
    fetch(params = {}) {
      this.loading = true;
      getListPage(params).then((response) => {
        const pagination = { ...this.pagination };
        pagination.total = response.data.data.total;
        this.loading = false;
        this.data = response.data.data.rows;
        this.pagination = pagination;
      });
    },
    viewCode(record) {
      this.$dialog(
        ViewCodeForm,
        // component props
        {
          record,
          on: {
            ok() {
              that.fetch({ pageSize: 10 });
              console.log("ok 回调");
            },
            cancel() {
              console.log("cancel 回调");
            },
            close() {
              console.log("modal close 回调");
            },
          },
        },
        // modal props
        {
          title: "预览",
          width: 1200,
          centered: true,
          maskClosable: false,
          footer: "",
        }
      );
    },
    info(record) {
      this.$dialog(
        InfoForm,
        // component props
        {
          record,
          on: {
            ok() {
              that.fetch({ pageSize: 10 });
              console.log("ok 回调");
            },
            cancel() {
              console.log("cancel 回调");
            },
            close() {
              console.log("modal close 回调");
            },
          },
        },
        // modal props
        {
          title: "详情",
          width: 1200,
          centered: true,
          maskClosable: false,
          footer: "",
        }
      );
    },
    importTable(record) {
      const that = this;
      this.$dialog(
        SelectTable,
        // component props
        {
          record,
          on: {
            ok() {
              that.fetch({ pageSize: 10 });
              console.log("ok 回调");
            },
            cancel() {
              console.log("cancel 回调");
            },
            close() {
              console.log("modal close 回调");
            },
          },
        },
        // modal props
        {
          title: "操作",
          width: 1000,
          centered: true,
          maskClosable: false,
          okText: "确认",
          cancelText: "取消",
        }
      );
    },
    gencode(record) {
      gencode(record.id).then((response) => {
          if (response.status==200) {
            if(record.gen_type=='0'){
              console.log(response)
              window.location.href = process.env.VUE_APP_API_URL + "/gencode/gencode/downloadCode?id="+record.id
            }else{
              this.$message.success("代码生成完成。");
            }
          } else {
            this.$message.error("代码生成失败。");
          }
      });
    },
    gencodes() {
      if (this.selectedRows.length == 1) {
        const record = this.selectedRows[0];
        gencode(record.id).then((response) => {
          if (response.status==200) {
            console.log(record)
            if(record.gen_type=='0'){
              window.location.href = process.env.VUE_APP_API_URL + "/gencode/gencode/downloadCode?id="+record.id;
            }else{
              this.$message.success("代码生成完成。");
            }
          } else {
            this.$message.error("代码生成失败。");
          }
        });
      } else {
        this.$error({
          title: "提示：",
          content: "请选择一条数据进行编辑。",
          okText: "确认",
        });
      }
    },
    edit(record) {
      this.dialogData(record);
    },
    edits() {
      if (this.selectedRows.length == 1) {
        const record = this.selectedRows[0];
        this.dialogData(record);
      } else {
        this.$error({
          title: "提示：",
          content: "请选择一条数据进行编辑。",
          okText: "确认",
        });
      }
    },
    del(key) {
      this.delData(key);
    },
    dels() {
      if (this.selectedRows.length > 0) {
        const ids = this.selectedRows.map((map) => {
          return map.id;
        });
        this.delData(ids);
      } else {
        this.$error({
          title: "提示：",
          content: "请选择需要删除的数据。",
          okText: "确认",
        });
      }
    },
    dialogData(record) {
      const that = this;
      this.$dialog(
        EditForm,
        // component props
        {
          record,
          on: {
            ok() {
              that.fetch({ pageSize: 10 });
              console.log("ok 回调");
            },
            cancel() {
              console.log("cancel 回调");
            },
            close() {
              console.log("modal close 回调");
            },
          },
        },
        // modal props
        {
          title: "操作",
          width: 1200,
          centered: true,
          maskClosable: false,
          okText: "确认",
          cancelText: "取消",
        }
      );
    },
    delData(ids) {
      const that = this;
      that.$confirm({
        title: "确定要删除选择的数据吗?",
        content: (h) => <div style="color:red;">数据删除后不可恢复</div>,
        okText: "确认",
        cancelText: "取消",
        onOk() {
          delData(ids).then((response) => {
            if (response.status==200) {
              that.$success({
                title: "提示：",
                content: "数据删除成功。",
              });
              that.fetch({ pageSize: 10 });
            } else {
              that.$error({
                title: "提示：",
                content: "数据删除失败。",
              });
            }
          });
          console.log("OK：" + ids);
        },
        onCancel() {
          console.log("Cancel");
        },
      });
    },
  },
};
</script>
<style scoped>
</style>