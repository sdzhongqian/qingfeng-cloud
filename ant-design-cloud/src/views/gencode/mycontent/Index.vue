<template>
  <page-header-wrapper>
        <a-card :bordered="false">
          <div class="table-page-search-wrapper">
            <a-form layout="inline">
              <a-row :gutter="24">
                <a-col :md="6" :sm="24">
                  <a-form-item label="标题">
                    <a-input v-model="queryParam.title" placeholder="标题" />
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
              <a-button
                v-if="authButton.includes('mycontent:add')"
                @click="add()"
                >添加</a-button
              >
              <a-button
                v-if="authButton.includes('mycontent:edit')"
                type="primary"
                @click="edits()"
                >编辑</a-button
              >
              <a-button
                v-if="authButton.includes('mycontent:del')"
                type="danger"
                @click="dels()"
                >删除</a-button
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
              <div>
                {{ text }}
              </div>
            </template>
            <template slot="operation" slot-scope="text, record, index">
              <div class="editable-row-operations">
                <a-space size="small">
                  <a-button
                    size="small"
                    v-if="authButton.includes('mycontent:info')"
                    @click="() => info(record)"
                    >详情</a-button
                  >
                  <a-button
                    size="small"
                    type="primary"
                    v-if="
                      authButton.includes('mycontent:edit') &&
                      authOpera(record)
                    "
                    @click="() => edit(record)"
                    >编辑</a-button
                  >
                  <a-button
                    size="small"
                    type="danger"
                    v-if="
                      authButton.includes('mycontent:del') && authOpera(record)
                    "
                    @click="() => del(record)"
                    >删除</a-button
                  >
                  <a-button
                    size="small"
                    type="danger"
                    v-if="authButton.includes('mycontent:status')"
                    v-show="record.status == '0'"
                    @click="() => updateStatus(record.id, '1')"
                  >禁用</a-button>
                  <a-button
                    size="small"
                    type="primary"
                    v-if="authButton.includes('mycontent:status')"
                    v-show="record.status == '1'"
                    @click="() => updateStatus(record.id, '0')"
                  >启用</a-button>
                </a-space>
              </div>
            </template>
          </a-table>
        </a-card>
  </page-header-wrapper>
</template>
<script>
import { mapState } from "vuex";
import { getListPage, delData, updateStatus } from "@/api/gencode/mycontent";
import storage from "store";
import EditForm from "./Edit";
import InfoForm from "./Info";
const columns = [
  {
    title: "标题",
    dataIndex: "title",
    ellipsis: true,
  },
  {
    title: "阅读数量",
    dataIndex: "read_num",
    ellipsis: true,
  },
  {
    title: "排序",
    dataIndex: "order_by",
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
      queryParam: {},
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
    }),
    //处理数据权限-操作权限
    authOpera(){
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
  rowSelection(){
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
      }
    },
  },
  mounted() {
    this.fetch({ pageSize: 10 });
  },
  methods: {
      handleTableChange(pagination, filters, sorter){
        console.log(pagination);
        const pager = {...this.pagination }
        pager.current = pagination.current;
        this.pagination = pager;
        this.fetch({
          pageSize: pagination.pageSize,
          pageNum: pagination.current,
          ...filters,
        })
      },
      fetch(params = {}){
        this.loading = true;
        getListPage({...params }).then((response) => {
          const pagination = {...this.pagination }
          // Read total count from server
          // pagination.total = data.totalCount;
          pagination.total = response.data.data.total;
          this.loading = false;
          this.data = response.data.data.rows;
          this.pagination = pagination;
        })
      },
      info(record) {
        this.$dialog(
          InfoForm,
          // component props
          {
            record,
            on: {
              ok() {
                that.fetch({pageSize: 10});
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
      edit(record){
        this.dialogData(record);
      },
      del(record){
        this.delData(record.id);
      },
      add(){
        this.dialogData({});
      },
      edits(){
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
      dels(){
        if (this.selectedRows.length > 0) {
          let delBol = true;
          for (let i = 0; i < this.selectedRows.length; i++) {
            const record = this.selectedRows[i];
            if (!record.authOpera) {
                delBol = false;
            }
          }
          if (!delBol) {
            this.$message.warning("批量删除中存在没有删除权限的数据，请重新选择。");
            return;
          }
          const ids = this.selectedRows.map((map) => {
            return map.id;
          })
          this.delData(ids);
        } else {
          this.$error({
            title: "提示：",
            content: "请选择需要删除的数据。",
            okText: "确认",
          });
        }
      },
      dialogData(record){
        const that = this;
        record = {
          ...record,
        }
        this.$dialog(
          EditForm,
          // component props
          {
            record,
            on: {
              ok() {
                that.fetch({pageSize: 10});
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
      delData(ids){
        const that = this;
        that.$confirm({
          title: "确定要删除选择的数据吗?",
          content: (h) => <div style="color:red;">数据删除后不可恢复</div>,
          okText:"确认",
          cancelText:"取消",
          onOk(){
            delData(ids).then((response) => {
              if (response.status==200) {
                that.$success({
                  title: "提示：",
                  content: "数据删除成功",
                });
                that.fetch({pageSize: 10});
              }else{
                that.$error({
                  title: "提示：",
                  content: "数据删除失败",
                });
              }
            })
            console.log("OK：" + ids);
          },
          onCancel(){
            console.log("Cancel");
          },
        })
      },
    }
  }
</script>
<style scoped>
</style>