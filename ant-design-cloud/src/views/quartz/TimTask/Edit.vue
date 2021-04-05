<template>
  <div>
    <a-form-model
      ref="ruleForm"
      :model="form"
      :rules="rules"
      :label-col="labelCol"
      :wrapper-col="wrapperCol"
    >
      <a-form-model-item ref="job_name" label="任务名称" prop="job_name">
        <a-input
          v-model="form.job_name"
          @blur="
            () => {
              $refs.job_name.onFieldBlur();
            }
          "
        />
      </a-form-model-item>
      <a-form-model-item ref="job_group" label="任务分组" prop="job_group">
        <a-input
          v-model="form.job_group"
          @blur="
            () => {
              $refs.job_group.onFieldBlur();
            }
          "
        />
      </a-form-model-item>

      <a-form-model-item ref="job_class_name" label="执行类" prop="job_class_name">
        <a-input
          v-model="form.job_class_name"
          @blur="
            () => {
              $refs.job_class_name.onFieldBlur();
            }
          "
        />
      </a-form-model-item>
      <a-form-model-item
        ref="cron_expression"
        label="执行时间"
        prop="cron_expression"
      >
        <a-input
          v-model="form.cron_expression"
          @input="handleinput"
          @blur="
            () => {
              $refs.cron_expression.onFieldBlur();
            }
          "
        >
          <a-icon
            slot="prefix"
            type="schedule"
            title="corn控件"
            @click="openModal"
          />
          <a-icon
            v-if="form.cron_expression"
            slot="suffix"
            type="close-circle"
            @click="handleEmpty"
            title="清空"
          />
        </a-input>
      </a-form-model-item>
      <a-form-model-item label="任务描述" prop="description">
        <a-input v-model="form.description" type="textarea" />
      </a-form-model-item>
    </a-form-model>
    <AntCron ref="innerVueCron" :data="afterCron" @ok="handleOK"></AntCron>
  </div>
</template>
<script>
import { saveOrUpdate } from "@/api/quartz/timTask";
import AntCron from "../Cron/antCron/AntCron";
import { replaceWeekName } from "../Cron/antCron/validator";
export default {
  components: {
    AntCron,
  },
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
        job_name: "",
        job_group: "",
        job_class_name: "",
        cron_expression: "",
        description: "",
      },
      afterCron: "",
      rules: {
        job_name: [
          { required: true, message: "必填项不能为空", trigger: "blur" },
          { min: 0, max: 50, message: "长度不得大于50个字符", trigger: "blur" },
        ],
        job_group: [
          { required: true, message: "必填项不能为空", trigger: "blur" },
          { min: 0, max: 50, message: "长度不得大于50个字符", trigger: "blur" },
        ],
        job_class_name: [
          { required: true, message: "必填项不能为空", trigger: "blur" },
          { min: 0, max: 50, message: "长度不得大于50个字符", trigger: "blur" },
        ],
        cron_expression: [
          { required: true, message: "必填项不能为空", trigger: "blur" },
          { min: 0, max: 50, message: "长度不得大于50个字符", trigger: "blur" },
        ],
        description: [
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
    openModal() {
      this.$refs.innerVueCron.show();
    },
    handleOK(val) {
      this.form.cron_expression = val;
      console.log(this.form.cron_expression);
      this.$forceUpdate();
    },
    handleinput(evt) {
      this.form.cron_expression = evt.target.value;
    },
    handleEmpty() {
      this.handleOK("");
    },
  },
};
</script>
