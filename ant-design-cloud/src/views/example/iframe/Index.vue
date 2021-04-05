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
      bdTokenUrl: "http://www.baidu.com",
    };
  },
  //生命周期 - 创建完成（访问当前this实例）
  created() {
    this.getUrl();
    this.$nextTick(() => {
      this.getCode();
    });
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
  },
  methods: {
    /**
     * 获取-外部接口信息
     */
    getUrl() {
      let that = this;
      let bdUrl = { queryurl: this.$paths.bdpath + "/locate" };
      this.$api.getBdToken(bdUrl, function (res) {
        that.bdTokenUrl = res.data.data;
      });
    },
  },
};
</script>
<style scoped>
/* @import url(); 引入css类 */
</style>