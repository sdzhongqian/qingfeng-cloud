<template>
  <a-form-model
    ref="ruleForm"
    layout="horizontal"
    :model="form"
    :rules="rules"
    :label-col="labelCol"
    :wrapper-col="wrapperCol"
  >
    <a-row :gutter="0">
      <a-col :md="12" :lg="12">
        <a-form-model-item ref="login_name" label="登录账号" prop="login_name">
          <a-input
            v-model="form.login_name"
            placeholder="请输入登录账号"
            @blur="
              () => {
                $refs.login_name.onFieldBlur();
              }
            "
          />
        </a-form-model-item>
      </a-col>
      <a-col :md="12" :lg="12">
        <a-form-model-item ref="name" label="姓名" prop="name">
          <a-input
            v-model="form.name"
            placeholder="请输入姓名"
            @blur="
              () => {
                $refs.name.onFieldBlur();
              }
            "
          />
        </a-form-model-item>
      </a-col>
    </a-row>
    <a-row :gutter="0" v-if="form.id == '' || form.id == null">
      <a-col :md="12" :lg="12">
        <a-form-model-item
          ref="login_password"
          label="登录密码"
          prop="login_password"
        >
          <a-input
            v-model="form.login_password"
            placeholder="请输入登录密码"
            @blur="
              () => {
                $refs.login_password.onFieldBlur();
              }
            "
          />
        </a-form-model-item>
      </a-col>
      <a-col :md="12" :lg="12">
        <a-form-model-item
          ref="confirm_password"
          label="确认密码"
          prop="confirm_password"
        >
          <a-input
            v-model="form.confirm_password"
            placeholder="请输入确认密码"
            @blur="
              () => {
                $refs.confirm_password.onFieldBlur();
              }
            "
          />
        </a-form-model-item>
      </a-col>
    </a-row>
    <a-row :gutter="0">
      <a-col :md="12" :lg="12">
        <a-form-model-item ref="sex" label="性别" prop="sex">
          <a-select v-model:value="form.sex" placeholder="请选择性别">
            <a-select-option value="1"> 男 </a-select-option>
            <a-select-option value="2"> 女 </a-select-option>
          </a-select>
        </a-form-model-item>
      </a-col>
      <a-col :md="12" :lg="12">
        <a-form-model-item ref="phone" label="手机号" prop="phone">
          <a-input
            v-model="form.phone"
            placeholder="请输入手机号"
            @blur="
              () => {
                $refs.phone.onFieldBlur();
              }
            "
          />
        </a-form-model-item>
      </a-col>
    </a-row>
    <a-row :gutter="0">
      <a-col :md="12" :lg="12">
        <a-form-model-item ref="email" label="邮箱" prop="email">
          <a-input
            v-model="form.email"
            placeholder="请输入邮箱"
            @blur="
              () => {
                $refs.email.onFieldBlur();
              }
            "
          />
        </a-form-model-item>
      </a-col>
      <a-col :md="12" :lg="12">
        <a-form-model-item ref="birth_date" label="出生日期" prop="birth_date">
          <a-input
            v-model="form.birth_date"
            placeholder="请输入出生日期"
            @blur="
              () => {
                $refs.birth_date.onFieldBlur();
              }
            "
          />
        </a-form-model-item>
      </a-col>
    </a-row>
    <a-row :gutter="0">
      <a-col :md="12" :lg="12">
        <a-form-model-item ref="motto" label="座右铭" prop="motto">
          <a-input
            v-model="form.motto"
            placeholder="请输入座右铭"
            @blur="
              () => {
                $refs.motto.onFieldBlur();
              }
            "
          />
        </a-form-model-item>
      </a-col>
      <a-col :md="12" :lg="12">
        <a-form-model-item ref="order_by" label="排序号" prop="order_by">
          <a-input
            v-model="form.order_by"
            placeholder="请输入排序号"
            @blur="
              () => {
                $refs.order_by.onFieldBlur();
              }
            "
          />
        </a-form-model-item>
      </a-col>
    </a-row>
    <a-row :gutter="0">
      <a-col :md="12" :lg="12">
        <a-form-model-item
          ref="live_address"
          label="居住地址"
          prop="live_address"
        >
          <a-input
            v-model="form.live_address"
            placeholder="请输入居住地址"
            @blur="
              () => {
                $refs.live_address.onFieldBlur();
              }
            "
          />
        </a-form-model-item>
      </a-col>
      <a-col :md="12" :lg="12">
        <a-form-model-item
          ref="birth_address"
          label="出生地址"
          prop="birth_address"
        >
          <a-input
            v-model="form.birth_address"
            placeholder="请输入出生地址"
            @blur="
              () => {
                $refs.birth_address.onFieldBlur();
              }
            "
          />
        </a-form-model-item>
      </a-col>
    </a-row>
    <a-row :gutter="0" v-if="form.id == '' || form.id == null">
      <a-col :md="12" :lg="12">
        <a-form-model-item
          ref="organize_name"
          label="组织名称"
          prop="organize_name"
        >
          <a-input disabled v-model="form.organize_name" />
        </a-form-model-item>
      </a-col>
      <a-col :md="12" :lg="12">
        <a-form-model-item ref="position" label="职位" prop="position">
          <a-input
            v-model="form.position"
            placeholder="请输入职位"
            @blur="
              () => {
                $refs.birth_address.onFieldBlur();
              }
            "
          />
        </a-form-model-item>
      </a-col>
    </a-row>
    <a-row :gutter="0">
      <a-col :md="12" :lg="24">
        <a-form-model-item
          ref="live_address"
          label="头像地址"
          prop="live_address"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 20 }"
        >
          <a-upload
            v-model:fileList="fileList"
            name="file"
            list-type="picture-card"
            class="avatar-uploader"
            :show-upload-list="false"
            :customRequest="uploadImage"
            :before-upload="beforeUpload"
            :headers="headers"
            @change="handleChange"
          >
            <img v-if="imageUrl" width="100px" :src="imageUrl" alt="avatar" />
            <div v-else>
              <!-- todo -->
              <a-icon :type="loading ? 'loading' : 'plus'" />
              <div class="ant-upload-text">选择</div>
            </div>
          </a-upload>
        </a-form-model-item>
      </a-col>
    </a-row>
    <a-row :gutter="0">
      <a-col :md="12" :lg="24">
        <a-form-model-item
          label="备注"
          prop="remark"
          :label-col="{ span: 4 }"
          :wrapper-col="{ span: 20 }"
        >
          <a-input
            v-model="form.remark"
            type="textarea"
            :style="{ height: '80px' }"
          />
        </a-form-model-item>
      </a-col>
    </a-row>
  </a-form-model>
</template>
<script>
import { saveOrUpdate } from "@/api/system/user";
import { upload } from "@/api/common/upload";
function getBase64(img, callback) {
  const reader = new FileReader();
  reader.addEventListener("load", () => callback(reader.result));
  reader.readAsDataURL(img);
}
export default {
  // 声明当前子组件接收父组件传递的属性
  props: {
    record: {
      type: Object,
      default: null,
    },
  },
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
      loading: false,
      imageUrl: "",
      fileList: [],
      headers: {},
      labelCol: { span: 7 },
      wrapperCol: { span: 17 },
      other: "",
      mybol: false,
      form: {
        id: "",
        organize_id: "",
        organize_name: "",
        old_login_name: "",
        fileIds: "",
      },
      rules: {
        login_name: [
          { required: true, message: "必填项不能为空", trigger: "blur" },
          { min: 0, max: 50, message: "长度不得大于50个字符", trigger: "blur" },
        ],
        name: [
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
        position: [
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
    this.imageUrl = this.record.imageUrl;
  },
  methods: {
    resetForm() {
      this.$refs.ruleForm.resetFields();
    },
    onOk() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.form.old_login_name = this.form.login_name;
          saveOrUpdate(this.form).then((response) => {
            console.log(response)
            if (response.status==200&&response.data.data.success) {
              this.$message.success("操作成功");
            } else {
              let msg = "操作失败";
              if(response.data.data.msg!=''){
                msg = response.data.data.msg;
              }
              this.$message.error(msg);
            }
          });
          this.mybol = true;
          return true;
        } else {
          this.mybol = false;
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

    handleChange(info) {
      if (info.file.status === "uploading") {
        this.loading = true;
        return;
      }
      if (info.file.status === "done") {
        // Get this url from response in real world.
        getBase64(info.file.originFileObj, (imageUrl) => {
          this.imageUrl = imageUrl;
          this.loading = false;
        });
      }
    },
    beforeUpload(file) {
      const isJpgOrPng =
        file.type === "image/jpeg" || file.type === "image/png";
      if (!isJpgOrPng) {
        this.$message.error("You can only upload JPG file!");
      }
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.$message.error("Image must smaller than 2MB!");
      }
      return isJpgOrPng && isLt2M;
    },
    // 上传头像
    uploadImage(file) {
      this.loading = true;
      const formData = new FormData();
      formData.append("file", file.file);
      upload(formData).then(
        (res) => {
          if (res.success) {
            this.loading = false;
            this.imageUrl = res.data.show_file_path;
            this.form.fileIds = res.data.id;
          }
        },
        (err) => {
          this.avatarLoading = false;
        }
      );
    },
  },
};
</script>
