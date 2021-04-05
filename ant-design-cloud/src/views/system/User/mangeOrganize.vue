<template>
  <a-card :bordered="false">
    <div class="operate">
      <a-button type="dashed" style="width: 100%" icon="plus" @click="add"
        >添加</a-button
      >
    </div>
    <a-table
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
            <a-button size="small" type="primary" @click="() => edit(record)"
              >编辑</a-button
            >
            <a-button size="small" type="danger" v-show="record.type == '1'" @click="() => del(record.id)"
              >删除</a-button
            >
          </a-space>
        </div>
      </template>
    </a-table>
    <a-modal
      title="Title"
      :visible="visible"
      :confirm-loading="confirmLoading"
      @ok="handleOk"
      @cancel="handleCancel"
      ok-text="确认"
      cancel-text="取消"
    >
      <a-form-model layout="vertical" :model="form">
        <a-form-model-item label="组织类型">
          <a-input v-model="form.type_name" disabled placeholder="兼职组织" />
        </a-form-model-item>
        <a-form-model-item label="组织名称">
          <a-row type="flex">
            <a-col :flex="4"
              ><a-input v-model="form.organize_name" placeholder="请选择组织"
            /></a-col>
            <a-col :flex="1"
              ><a-button @click="selectOneOrganize()">选择</a-button></a-col
            >
          </a-row>
        </a-form-model-item>
        <a-form-model-item label="职务">
          <a-input v-model="form.position" placeholder="请输入职务" />
        </a-form-model-item>
        <a-form-model-item label="排序">
          <a-input v-model="form.order_by" placeholder="请输入排序" />
        </a-form-model-item>
      </a-form-model>
    </a-modal>
  </a-card>
</template>

<script>
// 演示如何使用 this.$dialog 封装 modal 组件
import {
  getMyOrganizeListPage,
  saveOrUpdateUserOrganize,
  delUserOrganize
} from "@/api/system/user";
import SelectOneOrganize from "@/views/system/Organize/SelectOneOrganize";
const columns = [
  {
    title: "组织类型",
    dataIndex: "type",
    customRender: (text, row, index) => {
      if (text == "0") {
        return "主组织";
      } else if (text == "1") {
        return "兼职组织";
      } else {
        return "未知";
      }
    },
  },
  {
    title: "组织名称",
    dataIndex: "organize_name",
    ellipsis: true,
  },
  {
    title: "职务",
    dataIndex: "position",
    customRender: (text, row, index) => {
      if (text == "" || text == null) {
        return "未指定";
      } else {
        return text;
      }
    },
  },
  {
    title: "排序",
    dataIndex: "order_by",
  },
  {
    title: "操作",
    dataIndex: "operation",
    scopedSlots: { customRender: "operation" },
    width: "120px",
  },
];

export default {
  // 声明当前子组件接收父组件传递的属性
  props: {
    record: {
      type: Object,
      default: null,
    },
  },
  data() {
    return {
      data: [],
      pagination: {
        current: 1,
        pageSize: 10,
      },
      loading: false,
      columns,
      visible: false,
      confirmLoading: false,
      form: {
        id: "",
        type: "1",
        type_name: "兼职组织",
        user_id: "",
        organize_id: "",
        organize_name: "",
        position: "",
        order_by: "1",
      },
    };
  },
  mounted() {
    this.fetch({
      pageNum: this.pagination.current,
      pageSize: this.pagination.pageSize,
    });
    this.form.user_id = this.record.id;
  },
  methods: {
    handleTableChange(pagination, filters, sorter) {
      const pager = { ...this.pagination };
      pager.current = pagination.current;
      this.pagination = pager;
      this.fetch({
        pageSize: pagination.pageSize,
        pageNum: pagination.current,
      });
    },
    fetch(params = {}) {
      getMyOrganizeListPage({
        user_id: this.record.id,
        ...params,
      }).then((response) => {
        const pagination = { ...this.pagination };
        pagination.total = response.data.data.total;
        this.data = response.data.data.rows;
        this.pagination = pagination;
      });
    },
    add() {
      this.form = {
        id: "",
        type: "1",
        type_name: "兼职组织",
        user_id: this.record.id,
        organize_id: "",
        organize_name: "",
        position: "",
        order_by: "1",
      };
      this.visible = true;
    },
    handleOk() {
      if (this.form.organize_id == "") {
        this.$message.warning("请选择组织信息");
        return;
      }
      this.visible = false;
      saveOrUpdateUserOrganize(this.form).then((response) => {
        // console.log(response);
        this.fetch({
          pageNum: this.pagination.current,
          pageSize: this.pagination.pageSize,
        });
      });
    },
    handleCancel() {
      this.visible = false;
    },
    edit(record) {
      this.form = record;
      if (record.type == "0") {
        this.form.type_name = "主组织";
      } else {
        this.form.type_name = "兼职组织";
      }
      this.visible = true;
    },
    del(id) {
      const that = this;
      that.$confirm({
        title: "确定要删除选择的数据吗?",
        content: (h) => <div style="color:red;">数据删除后不可恢复</div>,
        okText: "确认",
        cancelText: "取消",
        onOk() {
          delUserOrganize(id).then((response) => {
            if (response.status==200) {
              that.fetch({
                pageNum: that.pagination.current,
                pageSize: that.pagination.pageSize,
              });
              that.$success({
                title: "提示：",
                content: "删除成功",
              });
            } else {
              that.$error({
                title: "提示：",
                content: "删除失败",
              });
            }
          });
        },
        onCancel() {
          console.log("Cancel");
        },
      });
    },
    selectOneOrganize() {
      const organize = {
        organize_id: this.form.organize_id,
        organize_name: this.form.organize_name,
      };
      this.dialog(SelectOneOrganize, organize);
    },
    dialog(component, record) {
      console.log(component, record);
      const that = this;
      this.$dialog(
        component,
        // component props
        {
          record,
          on: {
            ok() {
              console.log("ok 回调");
            },
            cancel() {
              console.log("cancel 回调");
            },
            close() {
              console.log("modal close 回调");
            },
            initValue(value, type) {
              if (type == "3") {
                that.form.organize_id = value.split(":")[0];
                that.form.organize_name = value.split(":")[1];
              }
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
  },
};
</script>

<style lang="less" scoped>
.ant-avatar-lg {
  width: 48px;
  height: 48px;
  line-height: 48px;
}

.list-content-item {
  color: rgba(0, 0, 0, 0.45);
  display: inline-block;
  vertical-align: middle;
  font-size: 14px;
  margin-left: 40px;
  span {
    line-height: 20px;
  }
  p {
    margin-top: 4px;
    margin-bottom: 0;
    line-height: 22px;
  }
}
</style>
