<template>
  <div class="main">
    <a-form
      id="formLogin"
      class="user-layout-login"
      ref="formLogin"
      :form="form"
      @submit="handleSubmit"
    >
      <a-tabs
        :active-key="customActiveKey"
        :tab-bar-style="{ textAlign: 'center', borderBottom: 'unset' }"
        @change="handleTabClick"
      >
        <a-tab-pane key="tab1" tab="账号密码登录">
          <a-alert
            v-if="isLoginError"
            type="error"
            show-icon
            style="margin-bottom: 24px"
            message="账户或密码错误（admin/123456 )"
          />
          <a-form-item>
            <a-input
              size="large"
              type="text"
              placeholder="账户: admin"
              v-decorator="[
                'username',
                {
                  rules: [
                    { required: true, message: '请输入帐户名或邮箱地址' },
                    { validator: handleUsernameOrEmail },
                  ],
                  validateTrigger: 'change',
                },
              ]"
            >
              <a-icon
                slot="prefix"
                type="user"
                :style="{ color: 'rgba(0,0,0,.25)' }"
              />
            </a-input>
          </a-form-item>

          <a-form-item>
            <a-input-password
              size="large"
              placeholder="密码: 123456"
              v-decorator="[
                'password',
                {
                  rules: [{ required: true, message: '请输入密码' }],
                  validateTrigger: 'blur',
                },
              ]"
            >
              <a-icon
                slot="prefix"
                type="lock"
                :style="{ color: 'rgba(0,0,0,.25)' }"
              />
            </a-input-password>
          </a-form-item>
        </a-tab-pane>
      </a-tabs>
      <a-form-item>
        <a-row type="flex">
          <a-col :flex="3">
            <a-input
              type="text"
              placeholder="验证码"
              v-decorator="[
                'code',
                {
                  rules: [{ required: true, message: '请输入验证码' }],
                  validateTrigger: 'change',
                },
              ]"
            />
          </a-col>
          <a-col :flex="2">
            <img
              :src="imageCode"
              alt="codeImage"
              class="code-image"
              @click="getCodeImage"
            />
          </a-col>
        </a-row>
      </a-form-item>
      <a-form-item>
        <a-checkbox
          v-decorator="[
            'rememberMe',
            { valuePropName: 'checked', initialValue: true },
          ]"
          >记住密码</a-checkbox
        >
      </a-form-item>

      <a-form-item style="margin-top: 24px">
        <a-button
          size="large"
          type="primary"
          html-type="submit"
          class="login-button"
          :loading="state.loginBtn"
        >
          确定
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import { randomNum } from "@/utils";
import axios from "axios";
export default {
  name: "Login",
  data() {
    return {
      customActiveKey: "tab1",
      loginBtn: false,
      // login type: 0 email, 1 username, 2 telephone
      loginType: 0,
      isLoginError: false,
      form: this.$form.createForm(this),
      codeUrl: process.env.VUE_APP_API_BASE_URL + `/auth/captcha`,
      randomId: randomNum(24, 16),
      imageCode: "",
      state: {
        time: 60,
        loginBtn: false,
        // login type: 0 email, 1 username, 2 telephone
        loginType: 0,
        smsSendBtn: false,
      },
      params: {},
    };
  },
  mounted() {
    this.params = this.$store.state.user.loginUser;
    if (this.params.rememberMe) {
      this.form.setFieldsValue(this.params);
    }
    this.getCodeImage();
  },

  methods: {
    ...mapActions(["Login", "Logout"]),
    // handler
    handleUsernameOrEmail(rule, value, callback) {
      const { state } = this;
      const regex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
      if (regex.test(value)) {
        state.loginType = 0;
      } else {
        state.loginType = 1;
      }
      callback();
    },
    handleTabClick(key) {
      this.customActiveKey = key;
      // this.form.resetFields()
    },
    handleSubmit(e) {
      e.preventDefault();
      const {
        form: { validateFields },
        state,
        customActiveKey,
        Login,
      } = this;

      state.loginBtn = true;

      const validateFieldsKey = ["username", "password", "rememberMe", "code"];
      validateFields(validateFieldsKey, { force: true }, (err, values) => {
        if (!err) {
          console.log("login form", values);
          const loginParams = { ...values,key: this.randomId };
          delete loginParams.username;
          loginParams[!state.loginType ? "email" : "username"] =
            values.username;
          loginParams.password = values.password; // md5(values.password)
          // console.log(loginParams);
          this.params = loginParams;
          Login(loginParams)
            .then((res) => this.loginSuccess(res))
            .catch((err) => this.requestFailed(err))
            .finally(() => {
              state.loginBtn = false;
            });
        } else {
          setTimeout(() => {
            state.loginBtn = false;
          }, 600);
        }
      });
    },
    loginSuccess(res) {
      console.log(res);
      // check res.homePage define, set $router.push name res.homePage
      // Why not enter onComplete
      /*
      this.$router.push({ name: 'analysis' }, () => {
        console.log('onComplete')
        this.$notification.success({
          message: '欢迎',
          description: `${timeFix()}，欢迎回来`
        })
      })
      */
      if (res.status==200) {
        //保存账号密码
        console.log(
          this.params.username +
            ":" +
            this.params.password +
            ":" +
            this.params.rememberMe
        );
        this.$store.dispatch("SetLoginUser", this.params);
        //执行跳转
        this.$router.push({ path: "/" });
        // 延迟 1 秒显示欢迎信息
        setTimeout(() => {
          this.$notification.success({
            message: "欢迎",
            description: "欢迎回来",
          });
        }, 1000);
        this.isLoginError = false;
      } else {
        // 延迟 1 秒显示欢迎信息
        setTimeout(() => {
          this.$notification.warning({
            message: "登录失败",
            description: res.msg,
          });
        }, 1000);
      }
    },
    requestFailed(err) {
      console.log(err)
      this.isLoginError = true;
      this.$notification.error({
        message: "错误",
        description:
          ((err.response || {}).data || {}).message ||
          "请求出现错误，请稍后再试",
        duration: 4,
      });
    },
    getCodeImage() {
      axios({
        method: "GET",
        url: `${this.codeUrl}?key=${this.randomId}`,
        responseType: "arraybuffer",
      })
        .then((res) => {
          return (
            "data:image/png;base64," +
            btoa(
              new Uint8Array(res.data).reduce(
                (data, byte) => data + String.fromCharCode(byte),
                ""
              )
            )
          );
        })
        .then((res) => {
          this.imageCode = res;
        })
        .catch((e) => {
          if (e.toString().indexOf("429") !== -1) {
            this.$notification.warning({
              message: "请求超时,请稍后重试",
              description: res.msg,
            });
          } else {
            this.$notification.warning({
              message: "获取图片验证码失败",
              description: res.msg,
            });
          }
        });
    },
  },
};
</script>

<style lang="less" scoped>
.code-image {
  display: inline-block;
  vertical-align: top;
  cursor: pointer;
  padding-left: 10px;
}

.user-layout-login {
  label {
    font-size: 14px;
  }

  .forge-password {
    font-size: 14px;
  }

  button.login-button {
    padding: 0 15px;
    font-size: 16px;
    height: 40px;
    width: 100%;
  }

  .user-login-other {
    text-align: left;
    margin-top: 24px;
    line-height: 22px;

    .item-icon {
      font-size: 24px;
      color: rgba(0, 0, 0, 0.2);
      margin-left: 16px;
      vertical-align: middle;
      cursor: pointer;
      transition: color 0.3s;

      &:hover {
        color: #1890ff;
      }
    }

    .register {
      float: right;
    }
  }
}
</style>
