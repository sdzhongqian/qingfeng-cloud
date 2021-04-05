<template>
<a-card :bordered="false">
      <a-descriptions title="用户组详情">
        <a-descriptions-item label="组名称">{{form.name}}</a-descriptions-item>
        <a-descriptions-item label="组简称">{{form.short_name}}</a-descriptions-item>
        <a-descriptions-item label="组用户">{{form.user_names}}</a-descriptions-item>
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
