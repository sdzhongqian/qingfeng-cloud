<#assign isContainStatus = 'false'>
<#assign isContainType = 'false'>
<#list fieldList as obj>
  <#if obj.field_name == 'status' && (tablePd.status_type == '0' || tablePd.status_type == '1')>
    <#assign isContainStatus = 'true'>
  </#if>
  <#if obj.field_name == 'type'>
    <#assign isContainType = 'true'>
  </#if>
</#list>
<template>
  <page-header-wrapper>
<#if tablePd.temp_type == '1'>
    <a-row :gutter="24">
      <a-col :md="24" :lg="6">
        <a-card :bordered="false">
          <tree ref="child" @selectTree="selectTree"></tree>
        </a-card>
      </a-col>
      <a-col :md="24" :lg="18">
</#if>
        <a-card :bordered="false">
          <div class="table-page-search-wrapper">
            <a-form layout="inline">
              <a-row :gutter="24">
<#list fieldList as obj>
    <#if obj.field_query == 'Y'>
	<#if obj.show_type == '1'||obj.show_type == '2'||obj.show_type == '6'>
                <a-col :md="6" :sm="24">
                  <a-form-item label="${obj.field_comment}">
                    <a-input v-model="queryParam.${obj.field_name}" placeholder="${obj.field_comment}" />
                  </a-form-item>
                </a-col>
	</#if>
	<#if obj.show_type == '7'>
                <a-col :md="6" :sm="24">
                  <a-form-item label="${obj.field_comment}">
                    <a-date-picker  v-model="queryParam.${obj.field_name}"  placeholder="${obj.field_comment}" />
                  </a-form-item>
                </a-col>
	</#if>
	<#if obj.show_type == '3'>

	</#if>
	<#if obj.show_type == '4'>

	</#if>
	<#if obj.show_type == '5'>

	</#if>
	</#if>
</#list>
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
                v-if="authButton.includes('${tablePd.bus_name}:add')"
                @click="add()"
                >添加</a-button
              >
              <a-button
                v-if="authButton.includes('${tablePd.bus_name}:edit')"
                type="primary"
                @click="edits()"
                >编辑</a-button
              >
              <a-button
                v-if="authButton.includes('${tablePd.bus_name}:del')"
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
                    v-if="authButton.includes('${tablePd.bus_name}:info')"
                    @click="() => info(record)"
                    >详情</a-button
                  >
                  <a-button
                    size="small"
                    type="primary"
                    v-if="
                      authButton.includes('${tablePd.bus_name}:edit') &&
                      authOpera(record)
                    "
                    @click="() => edit(record)"
                    >编辑</a-button
                  >
                  <a-button
                    size="small"
                    type="danger"
                    v-if="
                      authButton.includes('${tablePd.bus_name}:del') && authOpera(record)
                    "
                    @click="() => del(record)"
                    >删除</a-button
                  >
                  <#if isContainStatus == 'true'>
                  <a-button
                    size="small"
                    type="danger"
                    v-if="authButton.includes('${tablePd.bus_name}:status')"
                    v-show="record.status == '0'"
                    @click="() => updateStatus(record.id, '1')"
                  >禁用</a-button>
                  <a-button
                    size="small"
                    type="primary"
                    v-if="authButton.includes('${tablePd.bus_name}:status')"
                    v-show="record.status == '1'"
                    @click="() => updateStatus(record.id, '0')"
                  >启用</a-button>
                  </#if>
                </a-space>
              </div>
            </template>
          </a-table>
        </a-card>
<#if tablePd.temp_type == '1'>
      </a-col>
    </a-row>
</#if>
  </page-header-wrapper>
</template>
<script>
import { mapState } from "vuex";
import { getListPage, delData, updateStatus } from "@/api/${tablePd.mod_name}/${tablePd.bus_name}";
import storage from "store";
import EditForm from "./Edit";
import InfoForm from "./Info";
<#if tablePd.temp_type == '1'>
import Tree from "./Tree";
</#if>
const columns = [
<#list fieldList as obj>
<#if obj.field_list == 'Y'>
  {
    title: "${obj.field_comment}",
    dataIndex: "${obj.field_name}",
    ellipsis: true,
  },
</#if>
</#list>
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
      <#if tablePd.temp_type == '1'>
      parent_id: "",
      parent_name: "",
      </#if>
    };
  },
  components: {
    EditForm,
    InfoForm,
    <#if tablePd.temp_type == '1'>
    Tree,
    </#if>
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
  <#if tablePd.temp_type == '1'>
      selectTree(id, name){
        this.parent_id = id;
        this.parent_name = name;
        this.fetch({pageSize: 10});
      },
  </#if>
      fetch(params = {}){
        this.loading = true;
        <#if tablePd.temp_type == '1'>
        getListPage({parent_id: this.parent_id,...params }).then((response) => {
        </#if>
      <#if tablePd.temp_type != '1'>
        getListPage({...params }).then((response) => {
      </#if>
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
        this.${'$'}dialog(
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
        <#if tablePd.temp_type == '1'>
        if (record.child_num > 0) {
        this.${'$'}message.warning("删除的节点存在下级节点，不可删除。");
        } else {
        this.delData(record.id);
        }
        </#if>
        <#if tablePd.temp_type != '1'>
        this.delData(record.id);
        </#if>
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
            this.${'$'}message.warning("没有编辑权限,请选择其他数据进行编辑。");
          }
        } else {
         this.${'$'}error({
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
            this.${'$'}message.warning("批量删除中存在没有删除权限的数据，请重新选择。");
            return;
          }
        <#if tablePd.temp_type == '1'>
          let names = [];
          for (let i = 0; i < this.selectedRows.length; i++) {
            if (this.selectedRows[i].child_num > 0) {
                names.push(this.selectedRows[i].name)
            }
          }
          if (names.length > 0) {
            this.${'$'}message.warning("节点" + names.join(',') + "存在下级节点，不可删除。");
            return;
          }
        </#if>
          const ids = this.selectedRows.map((map) => {
            return map.id;
          })
          this.delData(ids);
        } else {
          this.${'$'}error({
            title: "提示：",
            content: "请选择需要删除的数据。",
            okText: "确认",
          });
        }
      },
      dialogData(record){
        const that = this;
        <#if tablePd.temp_type == '1'>
        if (this.parent_id == "" || this.parent_id == null) {
          that.${'$'}error({
            title: "提示：",
            content: "请选择左侧父级节点",
          });
          return;
        }
        </#if>
        record = {
          <#if tablePd.temp_type == '1'>
          parent_id:this.parent_id,
          parent_name:this.parent_name,
          </#if>
          ...record,
        }
        this.${'$'}dialog(
          EditForm,
          // component props
          {
            record,
            on: {
              ok() {
                that.fetch({pageSize: 10});
                <#if tablePd.temp_type == '1'>
                that.getTreeList();
                </#if>
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
      <#if isContainStatus == 'true'>
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
      </#if>
      delData(ids){
        const that = this;
        that.${'$'}confirm({
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
                <#if tablePd.temp_type == '1'>
                that.getTreeList();
                </#if>
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
      <#if tablePd.temp_type == '1'>
      getTreeList(){
        this.${'$'}refs.child.getTreeList({});
      }
      </#if>
    }
  }
</script>
<style scoped>
</style>