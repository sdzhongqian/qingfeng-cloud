<!--  -->
<template>
  <div>
    <p>当前组织：{{ organize_name }}</p>
    <a-divider style="background: #1890ff" />
    <a-radio-group
      name="organize_id"
      v-for="(item, index) in data"
      v-model="current_organize"
      @change="onChange"
    >
      <a-radio :value="item.organize_id + ':' + item.organize_name">
        {{ item.organize_name }}
      </a-radio>
    </a-radio-group>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import { getLoginUser, updateSwitchOrganize } from "@/api/auth/login";
export default {
  data() {
    return {
      current_organize: "",
      organize_id: "",
      organize_name: "",
      data: {},
    };
  },
  //生命周期 - 创建完成（访问当前this实例）
  created() {},
  //生命周期 - 挂载完成（访问DOM元素）
  mounted() {
    this.getUserInfo();
  },
  methods: {
    ...mapActions(["SetToken"]),
    ...mapActions(["ClearRoles"]),
    onChange(e) {
      const value = e.target.value.split(":");
      this.organize_id = value[0];
      this.organize_name = value[1];
      console.log(this.current_organize);
    },
    onOk() {
      updateSwitchOrganize({ organize_id: this.organize_id }).then(
        (response) => {
          // console.log(response);
          if (response.status==200) {
            this.$message.success("组织切换成功，请重新登录");
            //需要重新登录
            return this.$store.dispatch("Logout").then(() => {
              this.$router.push({ name: "login" });
            });
          } else {
            this.$message.error("组织切换失败");
          }
        }
      );
      this.mybol = true;
      return new Promise((resolve) => {
        resolve(this.mybol);
      });
    },
    getUserInfo() {
      getLoginUser().then((response) => {
        this.data = response.data.data.organizeList;
        for (let i = 0; i < this.data.length; i++) {
          //do something
          if (this.data[i].use_status == "0") {
            this.current_organize =
              this.data[i].organize_id + ":" + this.data[i].organize_name;
            console.log(this.current_organize);
            this.organize_id = this.data[i].organize_id;
            this.organize_name = this.data[i].organize_name;
          }
        }
      });
    },
  },
};
</script>
<style scoped>
/* @import url(); 引入css类 */
</style>