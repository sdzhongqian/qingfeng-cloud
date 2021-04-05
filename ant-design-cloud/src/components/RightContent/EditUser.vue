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
import { getLoginUser, updateUser } from "@/api/auth/login";
import { upload } from "@/api/common/upload";
function getBase64(img, callback) {
  const reader = new FileReader();
  reader.addEventListener("load", () => callback(reader.result));
  reader.readAsDataURL(img);
}
export default {
  data() {
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
      },
      rules: {
        name: [
          { required: true, message: "必填项不能为空", trigger: "blur" },
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
    // this.form = this.record;
    // this.imageUrl = this.record.imageUrl;
    this.getUserInfo();
  },
  methods: {
    getUserInfo() {
      getLoginUser().then((response) => {
        this.form = response.data.data;
        console.log(this.form)
        this.imageUrl = response.data.imageUrl;
      });
    },
    resetForm() {
      this.$refs.ruleForm.resetFields();
    },
    onOk() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          //重新初始化用户
          this.$store.dispatch("ClearRoles").then(() => {});
          updateUser(this.form).then((response) => {
            // console.log(response);
            if (response.status==200) {
              this.$message.success("更新成功");
            } else {
              this.$message.error("更新失败");
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
           if (res.status==200) {
            this.loading = false;
            this.imageUrl = res.data.data.show_file_path;
            this.form.fileIds = res.data.data.id;
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
<style scoped>
</style>
