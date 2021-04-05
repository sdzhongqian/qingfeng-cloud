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
    <a-form-model-item ref="type" label="菜单类型" prop="type">
      <a-select
        v-model="form.type"
        placeholder="请选择菜单类型"
        @change="selectTypeChange"
      >
        <a-select-option value="0" v-if="record.parent_type == '0'">
          目录
        </a-select-option>
        <a-select-option value="1" v-if="record.parent_type == '0'">
          菜单
        </a-select-option>
        <a-select-option value="2" v-if="record.parent_type == '1'">
          按钮
        </a-select-option>
        <a-select-option value="3" v-if="record.parent_type == '0'">
          外链
        </a-select-option>
        <a-select-option value="4" v-if="record.parent_type == '0'">
          iframe
        </a-select-option>
      </a-select>
    </a-form-model-item>

    <a-form-model-item ref="name" label="菜单名称" prop="name">
      <a-input
        v-model="form.name"
        @blur="
          () => {
            $refs.name.onFieldBlur();
          }
        "
      />
    </a-form-model-item>
    <a-form-model-item
      v-show="form.type == '0' || form.type == '1'|| form.type == '3'|| form.type == '4'"
      ref="path"
      label="路由地址"
      prop="path"
    >
      <a-input
        v-model="form.path"
        @blur="
          () => {
            $refs.path.onFieldBlur();
          }
        "
      />
    </a-form-model-item>
    <a-form-model-item
      v-show="form.type == '0'"
      ref="redirect"
      label="重定向地址"
      prop="redirect"
    >
      <a-input
        v-model="form.redirect"
        @blur="
          () => {
            $refs.redirect.onFieldBlur();
          }
        "
      />
    </a-form-model-item>
    <a-form-model-item
      v-show="form.type == '1'"
      ref="component"
      label="组件路径"
      prop="component"
    >
      <a-input
        v-model="form.component"
        @blur="
          () => {
            $refs.component.onFieldBlur();
          }
        "
      />
    </a-form-model-item>
    <a-form-model-item
      v-show="form.type == '1' || form.type == '2'"
      ref="permission"
      label="权限标识"
      prop="permission"
    >
      <a-input
        v-model="form.permission"
        @blur="
          () => {
            $refs.permission.onFieldBlur();
          }
        "
      />
    </a-form-model-item>
    <a-form-model-item
      v-show="form.type == '0' || form.type == '1' || form.type == '3' || form.type == '4'"
      ref="icon"
      label="菜单图标"
      prop="icon"
    >
      <a-input
        v-model="form.icon"
        @blur="
          () => {
            $refs.icon.onFieldBlur();
          }
        "
      />
    </a-form-model-item>
    <a-form-model-item
      v-show="form.type == '0' || form.type == '1' || form.type == '3' || form.type == '4'"
      ref="keepAlive"
      label="是否缓存"
      prop="keepAlive"
    >
      <a-radio-group :default-value="true" v-model="form.keepAlive">
        <a-radio value="true"> 缓存 </a-radio>
        <a-radio value="false"> 不缓存 </a-radio>
      </a-radio-group>
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
import { saveOrUpdate } from "@/api/system/menu";
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
        type: "0",
      },
      rules: {
        type: [{ required: true, message: "必填项不能为空", trigger: "blur" }],
        path: [
          { required: true, message: "必填项不能为空", trigger: "blur" },
          {
            min: 0,
            max: 50,
            message: "长度不得大于120个字符",
            trigger: "blur",
          },
        ],
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
    if (this.record.id == ""||this.record.id==undefined) {
      if (this.record.parent_type == "0") {
        this.record.type = "0";
      } else if (this.record.parent_type == "1") {
        this.record.type = "2";
        this.record.path = "/";
      }
    }
    this.form = this.record;
  },
  methods: {
    resetForm() {
      this.$refs.ruleForm.resetFields();
    },
    selectTypeChange(value) {
      console.log(value);
    },
    onOk() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          saveOrUpdate(this.form).then((response) => {
            console.log(response);
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
