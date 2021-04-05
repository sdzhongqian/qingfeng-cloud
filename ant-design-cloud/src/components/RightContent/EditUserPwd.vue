<template>
  <a-form-model
    ref="ruleForm"
    layout="horizontal"
    :model="form"
    :rules="rules"
    :label-col="labelCol"
    :wrapper-col="wrapperCol"
  >
   <a-form-model-item
      ref="old_password"
      label="旧密码"
      prop="old_password"
    >
      <a-input
        v-model="form.old_password"
        placeholder="请输入登录密码"
        type="password"
        @blur="
          () => {
            $refs.old_password.onFieldBlur();
          }
        "
      />
    </a-form-model-item>
    <a-form-model-item
      ref="login_password"
      label="登录密码"
      prop="login_password"
    >
      <a-input
        v-model="form.login_password"
        placeholder="请输入登录密码"
        type="password"
        @blur="
          () => {
            $refs.login_password.onFieldBlur();
          }
        "
      />
    </a-form-model-item>
    <a-form-model-item
      ref="confirm_password"
      label="确认密码"
      prop="confirm_password"
    >
      <a-input
        v-model="form.confirm_password"
        placeholder="请输入确认密码"
        type="password"
        @blur="
          () => {
            $refs.confirm_password.onFieldBlur();
          }
        "
      />
    </a-form-model-item>
  </a-form-model>
</template>
<script>
import { updatePwd } from "@/api/auth/login";
export default {
  data() {
    let confirm_password = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("必填项不能为空"));
      } else if (value !== this.form.login_password) {
        callback(new Error("两次密码输入不一致"));
      } else {
        callback();
      }
    };
    return {
      labelCol: { span: 4 },
      wrapperCol: { span: 18 },
      mybol: false,
      form: {
      },
      rules: {
        old_password: [
          { required: true, message: "必填项不能为空", trigger: "blur" },
          { min: 0, max: 50, message: "长度不得大于50个字符", trigger: "blur" },
        ],
        login_password: [
          { required: true, message: "必填项不能为空", trigger: "blur" },
          { min: 0, max: 50, message: "长度不得大于50个字符", trigger: "blur" },
        ],
        confirm_password: [
          { required: true, validator: confirm_password, trigger: "change" },
          { min: 0, max: 50, message: "长度不得大于50个字符", trigger: "blur" },
        ],
      },
    };
  },
  mounted() {
  },
  methods: {
    resetForm() {
      this.$refs.ruleForm.resetFields();
    },
    onOk() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          console.log(this.form)
          updatePwd(this.form).then((response) => {
            if (response.status==200) {
              this.$message.success(response.data.data.msg);
            } else {
              this.$message.error(response.data.data.msg);
            }
          });
          this.mybol = true;
          return true;
        } else {
          this.mybol = false;
          console.log("error submit!!");
          return false;
        }
      });
      console.log("监听了 modal ok 事件" + this.mybol);
      return new Promise((resolve) => {
        resolve(this.mybol);
      });
    },
    onCancel() {
      console.log("监听了 modal cancel 事件");
      return new Promise((resolve) => {
        resolve(true);
      });
    },
  },
};
</script>
