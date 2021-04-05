<template>
  <a-card :bordered="false">
    <a-descriptions title="quartz任务详情">
      <a-descriptions-item label="任务名称">{{
        form.job_name
      }}</a-descriptions-item>
      <a-descriptions-item label="任务分组">{{
        form.job_group
      }}</a-descriptions-item>
      <a-descriptions-item label="执行类">{{
        form.job_class_name
      }}</a-descriptions-item>
      <a-descriptions-item label="执行时间">{{
        form.cron_expression
      }}</a-descriptions-item>
      <a-descriptions-item label="任务描述">{{
        form.description
      }}</a-descriptions-item>
    </a-descriptions>
    <a-divider style="margin-bottom: 32px" />
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
