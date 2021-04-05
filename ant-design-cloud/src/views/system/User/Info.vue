<template>
<a-card :bordered="false">
      <a-descriptions title="用户详情">
        <a-descriptions-item label="登录账号">{{form.login_name}}</a-descriptions-item>
        <a-descriptions-item label="姓名">{{form.name}}</a-descriptions-item>
        <a-descriptions-item label="性别">{{form.sex=='1'?'男':'女'}}</a-descriptions-item>
        <a-descriptions-item label="手机号">{{form.phone}}</a-descriptions-item>
        <a-descriptions-item label="邮箱">{{form.email}}</a-descriptions-item>
        <a-descriptions-item label="出生日期">{{form.birth_date}}</a-descriptions-item>
        <a-descriptions-item label="座右铭">{{form.motto}}</a-descriptions-item>
        <a-descriptions-item label="排序号">{{form.order_by}}</a-descriptions-item>
        <a-descriptions-item label="居住地址">{{form.live_address}}</a-descriptions-item>
        <a-descriptions-item label="出生地址">{{form.birth_address}}</a-descriptions-item>
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
