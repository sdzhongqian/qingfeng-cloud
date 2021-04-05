<template>
<a-card :bordered="false">
      <a-descriptions title="菜单详情">
        <a-descriptions-item label="父级名称">{{form.parent_name}}</a-descriptions-item>
        <a-descriptions-item label="菜单类型">
          <span v-if="form.type == '0'">
            目录
          </span>
          <span v-if="form.type == '1'">
            菜单
          </span>
          <span v-if="form.type == '2'">
            按钮
          </span>
          <span v-if="form.type == '3'">
            外链
          </span>
          <span v-if="form.type == '4'">
            iframe
          </span>
        </a-descriptions-item>
        <a-descriptions-item label="菜单名称">{{form.name}}</a-descriptions-item>
        <a-descriptions-item v-show="form.type == '0' || form.type == '1'|| form.type == '3'|| form.type == '4'" label="路由地址">{{form.path}}</a-descriptions-item>
        <a-descriptions-item v-show="form.type == '0'" label="重定向地址">{{form.redirect}}</a-descriptions-item>
        <a-descriptions-item v-show="form.type == '1'" label="组件路径">{{form.component}}</a-descriptions-item>
        <a-descriptions-item v-show="form.type == '1' || form.type == '2'" label="权限标识">{{form.permission}}</a-descriptions-item>
        <a-descriptions-item v-show="form.type == '0' || form.type == '1' || form.type == '3' || form.type == '4'" label="菜单图标">{{form.icon}}</a-descriptions-item>
        <a-descriptions-item v-show="form.type == '0' || form.type == '1' || form.type == '3' || form.type == '4'" label="是否缓存">
           <span v-if="form.keepAlive == 'true'">
            缓存
          </span>
           <span v-if="form.keepAlive == 'false'">
            不缓存
          </span>
        </a-descriptions-item>
        <a-descriptions-item label="排序">{{form.order_by}}</a-descriptions-item>
        <a-descriptions-item label="备注">{{form.remark}}</a-descriptions-item>
      </a-descriptions>
      <a-divider style="margin-bottom: 32px"/>
    </a-card>
</template>
<script>
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
      mybol: false,
      form: {
        id: "",
        name: "",
        short_name: "",
        order_by: 0,
        remark: "",
      },
    };
  },
  mounted() {
    this.form = this.record;
  },
  methods: {
    onCancel() {
      console.log("监听了 modal cancel 事件");
      return new Promise((resolve) => {
        resolve(true);
      });
    },
  },
};
</script>
