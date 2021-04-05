<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="24">
            <a-col :md="6" :sm="24">
              <a-form-item label="组名称">
                <a-input v-model="queryParam.name" placeholder="" />
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item label="组简称">
                <a-input v-model="queryParam.short_name" placeholder="" />
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item label="使用状态">
                <a-select
                  v-model="queryParam.status"
                  placeholder="请选择"
                  default-value="0"
                >
                  <a-select-option value="">请选择</a-select-option>
                  <a-select-option value="0">启用</a-select-option>
                  <a-select-option value="1">禁用</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
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
          <a-button v-if="authButton.includes('group:add')" @click="add()"
            >添加</a-button
          >
          <a-button
            v-if="authButton.includes('group:edit')"
            type="primary"
            @click="edits()"
            >编辑</a-button
          >
          <a-button
            v-if="authButton.includes('group:del')"
            type="danger"
            @click="dels()"
            >删除</a-button
          >
           <a-button
                v-if="authButton.includes('group:download')"
                @click="download()"
                >下载</a-button
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
                v-if="authButton.includes('group:info')"
                @click="() => info(record)"
                >详情</a-button
              >
              <a-button
                size="small"
                type="primary"
                v-if="authButton.includes('group:edit') && authOpera(record)"
                @click="() => edit(record)"
                >编辑</a-button
              >
              <a-button
                size="small"
                type="danger"
                v-if="authButton.includes('group:del') && authOpera(record)"
                @click="() => del(record.id)"
                >删除</a-button
              >
              <a-button
                size="small"
                type="danger"
                v-if="authButton.includes('group:status')"
                v-show="record.status == '0'"
                @click="() => updateStatus(record.id, '1')"
                >禁用</a-button
              >
              <a-button
                size="small"
                type="primary"
                v-if="authButton.includes('group:status')"
                v-show="record.status == '1'"
                @click="() => updateStatus(record.id, '0')"
                >启用</a-button
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
import { getListPage, del, updateStatus } from "@/api/system/group";
import EditForm from "./Edit";
import InfoForm from "./Info";
const columns = [
  {
    title: "组名称",
    dataIndex: "name",
    sorter: true,
    ellipsis: true,
  },
  {
    title: "组简称",
    dataIndex: "short_name",
    ellipsis: true,
  },
  {
    title: "状态",
    dataIndex: "status",
    customRender: (text, row, index) => {
      if (text == "0") {
        return "启用";
      } else {
        return "禁用";
      }
    },
  },
  {
    title: "排序",
    dataIndex: "order_by",
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
        id: "",
        name: "",
        short_name: "",
        status: "",
      },
    };
  },
  components: {
    EditForm,
    InfoForm,
  },
  computed: {
    ...mapState({
      // 动态主路由
      authButton: (state) => state.user.authButton,
      authOrganize: (state) => state.user.authOrganize,
      authName: (state) => state.user.authName,
    }),
    //处理数据权限-操作权限
    authOpera() {
      return function (record) {
        let bol = false;
        let authOrgIds = this.authOrganize.authOrgIds;
        let authParams = this.authOrganize.authParams;
        let user_id = this.authOrganize.user_id;
        var createParams = record.create_organize + ":Y";
        if (authOrgIds == "" || authOrgIds == undefined) {
          if (user_id == record.create_user) {
            bol = true;
          }
        } else {
          if (
            authParams.indexOf(createParams) > -1 ||
            user_id == record.create_user
          ) {
            bol = true;
          }
        }
        record.authOpera = bol;
        return bol;
      };
    },
    rowSelection() {
      return {
        onChange: (selectedRowKeys, selectedRows) => {
          this.selectedRows = selectedRows;
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
    handleTableChange(pagination, filters, sorter) {
      console.log(pagination);
      const pager = { ...this.pagination };
      pager.current = pagination.current;
      this.pagination = pager;
      this.fetch({
        pageSize: pagination.pageSize,
        pageNum: pagination.current,
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...filters,
      });
    },
    fetch(params = {}) {
      console.log("params:", params);
      this.loading = true;
      getListPage(params).then((response) => {
        const pagination = { ...this.pagination };
        // Read total count from server
        // pagination.total = data.totalCount;
        pagination.total = response.data.data.total;
        this.loading = false;
        this.data = response.data.data.rows;
        this.pagination = pagination;
      });
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
          width: 700,
          centered: true,
          maskClosable: false,
          footer: "",
        }
      );
    },
    edit(record) {
      this.dialogData(record);
    },
    del(key) {
      this.delData(key);
    },
    add() {
      this.dialogData({});
    },
    edits() {
      if (this.selectedRows.length == 1) {
        const record = this.selectedRows[0];
        if (record.authOpera) {
          this.dialogData(record);
        } else {
          this.$message.warning("没有编辑权限,请选择其他数据进行编辑。");
        }
      } else {
        this.$error({
          title: "提示：",
          content: "请选择一条数据进行编辑。",
          okText: "确认",
        });
      }
    },
    dels() {
      if (this.selectedRows.length > 0) {
        let delBol = true;
        for (let i = 0; i < this.selectedRows.length; i++) {
          const record = this.selectedRows[i];
          if (!record.authOpera) {
            delBol = false;
          }
        }
        if(!delBol){
          this.$message.warning("批量删除中存在没有删除权限的数据，请重新选择。");
          return
        }
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
    updateStatus(id, status) {
      const that = this;
      updateStatus(id, status).then((response) => {
         if (response.status==200) {
          that.fetch({ pageSize: 10 });
        } else {
          that.$error({
            title: "提示：",
            content: "状态修改失败",
          });
        }
      });
    },
    download() {
      window.location.href =
        process.env.VUE_APP_API_URL +
        "/system/group/exportData?name=" +
        this.queryParam.name +
        "&short_name=" +
        this.queryParam.short_name +
        "&status=" +
        this.queryParam.status +
        "&authName=" +
        this.authName;
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
          width: 700,
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
          del(ids).then((response) => {
            if (response.status==200) {
              that.$success({
                title: "提示：",
                content: "数据删除成功",
              });
              that.fetch({ pageSize: 10 });
            } else {
              that.$error({
                title: "提示：",
                content: "数据删除失败",
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