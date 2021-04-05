<template>
  <a-dropdown v-if="currentUser && currentUser.name" placement="bottomRight">
    <span class="ant-pro-account-avatar">
      <a-avatar
        size="small"
        src="https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png"
        class="antd-pro-global-header-index-avatar"
      />
      <span>{{ currentUser.name }}</span>
    </span>
    <template v-slot:overlay>
      <a-menu class="ant-pro-drop-down menu" :selected-keys="[]">
        <a-menu-item v-if="menu" key="center" @click="updateUser()">
          <a-icon type="user" />
          个人信息
        </a-menu-item>
        <a-menu-item v-if="menu" @click="updatePwd()">
          <a-icon type="setting" />
          密码修改
        </a-menu-item>
        <a-menu-item v-if="menu" @click="switchOrganize()">
          <a-icon type="team" />
          组织切换
        </a-menu-item>
        <a-menu-divider v-if="menu" />
        <a-menu-item key="logout" @click="handleLogout">
          <a-icon type="logout" />
          退出登录
        </a-menu-item>
      </a-menu>
    </template>
  </a-dropdown>
  <span v-else>
    <a-spin size="small" :style="{ marginLeft: 8, marginRight: 8 }" />
  </span>
</template>

<script>
import { Modal } from "ant-design-vue";
import EditUser from "./EditUser";
import EditUserPwd from "./EditUserPwd";
import SwitchOrganize from "./SwitchOrganize";
export default {
  name: "AvatarDropdown",
  props: {
    currentUser: {
      type: Object,
      default: () => null,
    },
    menu: {
      type: Boolean,
      default: true,
    },
  },
  methods: {
    //更新个人信息
    updateUser(record) {
      const that = this;
      this.$dialog(
        EditUser,
        {
          record,
          on: {
            ok() {},
            cancel() {},
            close() {},
          },
        },
        {
          title: "操作",
          width: 700,
          centered: true,
          maskClosable: false,
          okText: "确认",
          cancelText: "取消",
        }
      );
    },
    //更新密码信息
    updatePwd(record) {
      const that = this;
      this.$dialog(
        EditUserPwd,
        {
          record,
          on: {
            ok() {},
            cancel() {},
            close() {},
          },
        },
        {
          title: "操作",
          width: 700,
          centered: true,
          maskClosable: false,
          okText: "确认",
          cancelText: "取消",
        }
      );
    },
    //切换组织
    switchOrganize(record) {
      const that = this;
      this.$dialog(
        SwitchOrganize,
        {
          record,
          on: {
            ok() {},
            cancel() {},
            close() {},
          },
        },
        {
          title: "操作",
          width: 700,
          centered: true,
          maskClosable: false,
          okText: "确认",
          cancelText: "取消",
        }
      );
    },
    handleLogout(e) {
      Modal.confirm({
        title: this.$t("layouts.usermenu.dialog.title"),
        content: this.$t("layouts.usermenu.dialog.content"),
        okText: "确定",
        cancelText: "取消",
        onOk: () => {
          // return new Promise((resolve, reject) => {
          //   setTimeout(Math.random() > 0.5 ? resolve : reject, 1500)
          // }).catch(() => console.log('Oops errors!'))
          return this.$store.dispatch("Logout").then(() => {
            this.$router.push({ name: "login" });
          });
        },
        onCancel() {},
      });
    },
  },
};
</script>

<style lang="less" scoped>
.ant-pro-drop-down {
  /deep/ .action {
    margin-right: 8px;
  }
  /deep/ .ant-dropdown-menu-item {
    min-width: 160px;
  }
}
</style>
