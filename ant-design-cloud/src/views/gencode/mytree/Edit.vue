<template>
  <a-form-model
    ref="ruleForm"
    :model="form"
    :rules="rules"
    :label-col="labelCol"
    :wrapper-col="wrapperCol"
  >
       <a-form-model-item ref="parent_name" label="父级名称" prop="parent_name">
      <a-input disabled v-model="form.parent_name" />
    </a-form-model-item>
	<a-form-model-item ref="name" label="字典名称" prop="name">
	    <a-input v-model="form.name"
		    @blur="() => {
		        $refs.name.onFieldBlur();
		    }"
	      />
	</a-form-model-item>
	<a-form-model-item ref="short_name" label="字典简称" prop="short_name">
	    <a-input v-model="form.short_name"
		    @blur="() => {
		        $refs.short_name.onFieldBlur();
		    }"
	      />
	</a-form-model-item>
	<a-form-model-item ref="order_by" label="排序" prop="order_by">
	    <a-input v-model="form.order_by"
		    @blur="() => {
		        $refs.order_by.onFieldBlur();
		    }"
	      />
	</a-form-model-item>
	<a-form-model-item ref="remark" label="备注" prop="remark">
	    <a-input v-model="form.remark"
		    @blur="() => {
		        $refs.remark.onFieldBlur();
		    }"
	      />
	</a-form-model-item>
  </a-form-model>
</template>
<script>
import { saveOrUpdate } from "@/api/gencode/mytree";
import { findDictionaryList } from "@/api/system/dictionary";
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
      headers: {
        authorization: "authorization-text",
      },
      uploading: false,
      form: {
        id: "",
        name:"",
        short_name:"",
        order_by:"",
        remark:"",
      },
      rules: {
        name: [
            { required: true, message: "必填项不能为空", trigger: "blur" },
            { min: 0, max: 120, message: "长度不得大于120个字符", trigger: "blur" },
        ],
      },
    }
  },
  components: {
  },
  mounted() {
    if(this.record.id != undefined) {
      this.form = this.record;
    }
    this.form.parent_id = this.record.parent_id;
    this.form.parent_name = this.record.parent_name;
    this.$forceUpdate();
    this.initData();
  },
  methods: {
    resetForm() {
      this.$refs.ruleForm.resetFields();
    },
    onOk() {
      console.log(this.mybol);
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
           saveOrUpdate(this.form).then((response) => {
               console.log(response)
           })
          this.mybol = true;
          return true;
        }else {
          this.mybol = false;
          console.log("error submit!!");
          return false;
        }
      })
        return new Promise((resolve) => {
            resolve(this.mybol)
        })
    },
    onCancel() {
      console.log("监听了 modal cancel 事件");
      return new Promise((resolve) => {
        resolve(true)
      })
    },
    initData(){
    },
  },
};
</script>
