<!--  -->
<template>
  <div>
    <iframe
      style="width: 100%; height: 100%"
      frameborder="0"
      scrolling="yes"
      :src="bdTokenUrl"
      id="bdIframe"
    ></iframe>
  </div>
</template>

<script>
export default {
  name: "",
  data() {
    return {
      bdTokenUrl: "",
    };
  },
  //生命周期 - 创建完成（访问当前this实例）
  created() {
    this.bdTokenUrl = process.env.VUE_APP_API_URL+this.$route.path;
  },
  //生命周期 - 挂载完成（访问DOM元素）
  mounted() {
    /**
     * iframe-宽高自适应显示
     */
    const oIframe = document.getElementById("bdIframe");
    const deviceWidth = document.documentElement.clientWidth;
    const deviceHeight = document.documentElement.clientHeight;
    oIframe.style.width = Number(deviceWidth) - 220 + "px"; //数字是页面布局宽度差值
    oIframe.style.height = Number(deviceHeight) - 120 + "px"; //数字是页面布局高度差

    // 当前页面 Vue如何获取当前页面的url,获取路由地址
    // 完整url可以用 window.location.href
    // 路由路径可以用 this.$route.path
    // 路由路径参数 this.$route.params 例如：/user/:id → /user/2044011030 → this.$route.params.id
    // 路由查询参数 this.$route.query 例如：/user/search?name=sf → this.$route.query.name
    // console.log(window.location.href)
  },
  methods: {
  },
};
</script>
<style scoped>
/* @import url(); 引入css类 */
</style>