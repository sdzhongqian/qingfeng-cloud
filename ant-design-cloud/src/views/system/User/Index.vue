<template>
  <page-header-wrapper>
    <a-row :gutter="24">
      <a-col :md="24" :lg="5">
        <a-card :bordered="false">
          <org-tree ref="child" @selectTree="selectTree"></org-tree>
        </a-card>
      </a-col>
      <a-col :md="24" :lg="19">
        <a-card :bordered="false">
          <div class="table-page-search-wrapper">
            <a-form layout="inline">
              <a-row :gutter="24">
                <a-col :md="6" :sm="24">
                  <a-form-item label="登录账号">
                    <a-input v-model="queryParam.login_name" placeholder="" />
                  </a-form-item>
                </a-col>
                <a-col :md="6" :sm="24">
                  <a-form-item label="姓名">
                    <a-input v-model="queryParam.name" placeholder="" />
                  </a-form-item>
                </a-col>
                <!-- <a-col :md="6" :sm="24">
                  <a-form-item label="手机号">
                    <a-input v-model="queryParam.phone" placeholder="" />
                  </a-form-item>
                </a-col> -->
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
                      <a-select-option value="2">休眠</a-select-option>
                    </a-select>
                  </a-form-item>
                </a-col>
                <a-col :md="6" :sm="24">
                  <a-button
                    type="primary"
                    @click="fetch({ results: 10, ...queryParam })"
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
              <a-button v-if="authButton.includes('user:add')" @click="add()"
                >添加</a-button
              >
              <a-button
                v-if="authButton.includes('user:edit')"
                type="primary"
                @click="edits()"
                >编辑</a-button
              >
              <a-button
                v-if="authButton.includes('user:del')"
                type="danger"
                @click="dels()"
                >删除</a-button
              >
              <a-button
                v-if="authButton.includes('user:resetPwd')"
                @click="updatePwds()"
                >密码重置</a-button
              >
              <a-button
                v-if="authButton.includes('user:assignAuth')"
                @click="auths()"
                >权限分配</a-button
              >
              <a-button
                v-if="authButton.includes('user:download')"
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
              <div>
                {{ text }}
              </div>
            </template>
            <template slot="operation" slot-scope="text, record, index">
              <div class="editable-row-operations">
                <a-space :size="4">
                  <div slot="actions">
                    <a-space :size="4">
                      <a-button
                        size="small"
                        @click="() => info(record)"
                        v-if="authButton.includes('user:info')"
                        >详情</a-button
                      >
                      <a-button
                        size="small"
                        type="primary"
                        v-if="authButton.includes('user:edit')"
                        @click="() => edit(record)"
                        >编辑</a-button
                      >
                      <a-button
                        size="small"
                        type="danger"
                        v-if="authButton.includes('user:del')"
                        @click="() => del(record.id)"
                        >删除</a-button
                      >
                      <a-button
                        size="small"
                        type="primary"
                        v-if="authButton.includes('user:resetPwd')"
                        @click="() => resetPwd(record)"
                        >密码重置</a-button
                      >
                    </a-space>
                  </div>
                  <div slot="actions">
                    <a-dropdown>
                      <a-menu slot="overlay">
                        <a-menu-item v-if="authButton.includes('user:info')"
                          ><a @click="() => info(record)">详情</a></a-menu-item
                        >
                        <a-menu-item
                          v-if="
                            authButton.includes('user:edit') &&
                            authOpera(record)
                          "
                          ><a @click="() => edit(record)">编辑</a></a-menu-item
                        >
                        <a-menu-item
                          v-if="
                            authButton.includes('user:del') && authOpera(record)
                          "
                          ><a @click="() => del(record.id)"
                            >删除</a
                          ></a-menu-item
                        >
                        <a-menu-item v-if="authButton.includes('user:resetPwd')"
                          ><a @click="() => resetPwd(record)"
                            >密码重置</a
                          ></a-menu-item
                        >
                        <a-menu-item
                          v-if="authButton.includes('user:resetOrganize')"
                          ><a @click="() => mangeOrganize(record)"
                            >设置组织</a
                          ></a-menu-item
                        >
                        <a-menu-item
                          v-if="authButton.includes('user:assignAuth')"
                          ><a @click="() => auth(record)"
                            >权限分配</a
                          ></a-menu-item
                        >
                        <a-menu-item
                          v-show="record.status == '0'"
                          v-if="authButton.includes('user:status')"
                          ><a @click="() => updateStatus(record.id, '1')"
                            >禁用</a
                          ></a-menu-item
                        >
                        <a-menu-item
                          v-show="record.status == '1'"
                          v-if="authButton.includes('user:status')"
                          ><a @click="() => updateStatus(record.id, '0')"
                            >启用</a
                          ></a-menu-item
                        >
                      </a-menu>
                      <a>更多<a-icon type="down" /></a>
                    </a-dropdown>
                  </div>
                </a-space>
              </div>
            </template>
          </a-table>
        </a-card>
      </a-col>
    </a-row>
  </page-header-wrapper>
</template>
<script>
import { mapState } from "vuex";
import { getListPage, delData, updateStatus } from "@/api/system/user";
import { ACCESS_TOKEN } from '@/store/mutation-types'
import storage from 'store'
import EditForm from "./Edit";
import InfoForm from "./Info";
import OrgTree from "./Tree";
import ResetPwd from "./ResetPwd";
import mangeOrganize from "./mangeOrganize";
import AuthForm from "./Auth";
const columns = [
  {
    title: "登录名",
    dataIndex: "login_name",
    sorter: true,
    ellipsis: true,
  },
  {
    title: "姓名",
    dataIndex: "name",
    ellipsis: true,
  },
  {
    title: "性别",
    dataIndex: "sex",
    customRender: (text, row, index) => {
      if (text == "1") {
        return "男";
      } else if (text == "2") {
        return "女";
      } else {
        return "未知";
      }
    },
  },
  // {
  //   title: "手机号",
  //   dataIndex: "phone",
  //   ellipsis: true,
  // },
  // {
  //   title: "邮箱",
  //   dataIndex: "邮箱",
  //   ellipsis: true,
  // },
  {
    title: "状态",
    dataIndex: "status",
    customRender: (text, row, index) => {
      if (text == "0") {
        return "启用";
      } else if (text == "1") {
        return "禁用";
      } else if (text == "2") {
        return "休眠";
      }
    },
  },
  {
    title: "排序",
    dataIndex: "order_by",
  },
  // {
  //   title: "创建时间",
  //   dataIndex: "create_time",
  // },
  {
    title: "操作",
    dataIndex: "operation",
    scopedSlots: { customRender: "operation" },
    width: "300px",
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
        login_name: "",
        name: "",
        status: "",
      },
      organize_id: "",
      organize_name: "",
      org_cascade: "",
      level_num: "",
    };
  },
  components: {
    EditForm,
    OrgTree,
    InfoForm,
    mangeOrganize,
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
          // console.log(user_id == record.create_user);
          if (user_id == record.create_user) {
            bol = true;
          }
        } else {
          // console.log(
          //   authParams.indexOf(createParams) > -1 ||
          //     user_id == record.create_user
          // );
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
    handleTableChange(pagination, filters, sorter) {
      const pager = { ...this.pagination };
      pager.current = pagination.current;
      this.pagination = pager;
      this.fetch({
        pageSize: pagination.pageSize,
        pageNum: pagination.current,
        ...filters,
      });
    },
    selectTree(id, name, value) {
      this.organize_id = id;
      this.organize_name = name;
      this.org_cascade = value.split(":")[0];
      this.level_num = value.split(":")[1];
      this.fetch({ pageSize: 10 });
    },
    fetch(params = {}) {
      this.loading = true;
      getListPage({ organize_id: this.organize_id, ...params }).then(
        (response) => {
          const pagination = { ...this.pagination };
          // Read total count from server
          // pagination.total = data.totalCount;
          pagination.total = response.data.data.total;
          this.loading = false;
          this.data = response.data.data.rows;
          this.pagination = pagination;
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
              that.fetch({ results: 10 });
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
        if (!delBol) {
          this.$message.warning(
            "批量删除中存在没有删除权限的数据，请重新选择。"
          );
          return;
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
    resetPwd(record) {
      this.dialogResetPwd({ ids: record.id, names: record.login_name });
    },
    updatePwds() {
      if (this.selectedRows.length > 0) {
        const ids = this.selectedRows.map((map) => {
          return map.id;
        });
        const names = this.selectedRows.map((map) => {
          return map.login_name;
        });
        this.dialogResetPwd({ ids: ids.join(","), names: names.join(",") });
      } else {
        this.$error({
          title: "提示：",
          content: "请选择需要重置密码的数据。",
          okText: "确认",
        });
      }
    },
    updateStatus(id, status) {
      const that = this;
      updateStatus(id, status).then((response) => {
        if (response.status==200) {
          that.fetch({ results: 10 });
        } else {
          that.$error({
            title: "提示：",
            content: "状态修改失败",
          });
        }
      });
    },
    auth(record) {
      this.dialogAuth(record);
    },
    auths() {
      if (this.selectedRows.length == 1) {
        const record = this.selectedRows[0];
        this.dialogAuth(record);
      } else {
        this.$error({
          title: "提示：",
          content: "请选择一条数据进行编辑。",
          okText: "确认",
        });
      }
    },
    download() {
      window.location.href =
        process.env.VUE_APP_API_URL +
        "/system/user/exportData?login_name=" +
        this.queryParam.login_name +
        "&name=" +
        this.queryParam.name +
        "&status=" +
        this.queryParam.status+
        "&organize_id="+this.organize_id+"&authName="+this.authName;
    },
    dialogData(record) {
      const that = this;
      if (this.organize_id == "" || this.organize_id == null) {
        that.$error({
          title: "提示：",
          content: "请选择左侧父级节点",
        });
        return;
      }
      record = {
        organize_id: this.organize_id,
        organize_name: this.organize_name,
        org_cascade: this.org_cascade,
        level_num: this.level_num,
        ...record,
      };
      this.$dialog(
        EditForm,
        // component props
        {
          record,
          on: {
            ok() {
              that.fetch({ results: 10 });
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
          width: 900,
          height: 500,
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
                content: "数据删除成功",
              });
              that.fetch({ results: 10 });
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
    dialogResetPwd(record) {
      const that = this;
      this.$dialog(
        ResetPwd,
        // component props
        {
          record,
          on: {
            ok() {
              that.fetch({ results: 10 });
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
          width: 900,
          height: 500,
          centered: true,
          maskClosable: false,
          okText: "确认",
          cancelText: "取消",
        }
      );
    },
    mangeOrganize(record) {
      const that = this;
      this.$dialog(
        mangeOrganize,
        // component props
        {
          record,
          on: {
            ok() {
              that.fetch({ results: 10 });
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
          width: 800,
          height: 500,
          centered: true,
          maskClosable: false,
          okText: "确认",
          cancelText: "取消",
        }
      );
    },
    dialogAuth(record) {
      const that = this;
      this.$dialog(
        AuthForm,
        // component props
        {
          record,
          on: {
            ok() {
              that.fetch({ results: 10 });
              that.getTreeList();
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
          width: 800,
          centered: true,
          maskClosable: false,
          okText: "确认",
          cancelText: "取消",
        }
      );
    },
  },
};
</script>
<style scoped>
.list-content-item {
  margin-left: 20px;
}
</style>