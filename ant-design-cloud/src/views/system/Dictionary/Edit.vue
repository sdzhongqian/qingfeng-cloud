<template>
  <a-form-model
    ref="ruleForm"
    :model="form"
    :rules="rules"
    :label-col="labelCol"
    :wrapper-col="wrapperCol"
  >
    <a-form-model-item ref="parent_name" label="父级名称" prop="parent_name">
      <a-input disabled
        v-model="form.parent_name"
      />
    </a-form-model-item>
    <a-form-model-item ref="name" label="字典名称" prop="name">
      <a-input
        v-model="form.name"
        @blur="
          () => {
            $refs.name.onFieldBlur();
          }
        "
      />
    </a-form-model-item>
    <a-form-model-item ref="short_name" label="字典简称" prop="short_name">
      <a-input
        v-model="form.short_name"
        @blur="
          () => {
            $refs.short_name.onFieldBlur();
          }
        "
      />
    </a-form-model-item>
     <a-form-model-item ref="code" label="字典编码" prop="code">
      <a-input
        v-model="form.code"
        @blur="
          () => {
            $refs.code.onFieldBlur();
          }
        "
      />
    </a-form-model-item>
    <a-form-model-item label="排序" prop="order_by">
      <a-input-number
        id="inputNumber"
        v-model="form.order_by"
        :min="0"
        :max="1000"
        @change=""
      />
    </a-form-model-item>
    <a-form-model-item label="备注" prop="remark">
      <a-input v-model="form.remark" type="textarea" />
    </a-form-model-item>
  </a-form-model>
</template>
<script>
import { saveOrUpdate } from "@/api/system/dictionary";
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
      labelCol: { span: 4 },
      wrapperCol: { span: 14 },
      other: "",
      mybol: false,
      form: {
        id: "",
        name: "",
        short_name: "",
        order_by: 0,
        remark: "",
      },
      rules: {
        name: [
          { required: true, message: "必填项不能为空", trigger: "blur" },
          { min: 0, max: 50, message: "长度不得大于50个字符", trigger: "blur" },
        ],
        short_name: [
          { min: 0, max: 50, message: "长度不得大于50个字符", trigger: "blur" },
        ],
        code: [
          { min: 0, max: 50, message: "长度不得大于50个字符", trigger: "blur" },
        ],
        remark: [
          {
            min: 0,
            max: 500,
            message: "长度不得大于500个字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  mounted() {
    this.form = this.record;
  },
  methods: {
    resetForm() {
      this.$refs.ruleForm.resetFields();
    },

    onOk() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          saveOrUpdate(this.form).then((response) => {
            console.log(response)
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
