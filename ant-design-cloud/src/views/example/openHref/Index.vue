<!-- 菜单管理 -->
<template>
<div>菜单管理
     <a-button @click="aa()" type="primary">
      当前页打开百度
    </a-button>
    <a-button @click="bb()" type="primary">
      新页面打开百度
    </a-button>
</div>
</template>

<script>
export default {
    name:"",
    data() {
        return {

        }
    },
    //生命周期 - 创建完成（访问当前this实例）
    created() {

    },
    //生命周期 - 挂载完成（访问DOM元素）
    mounted() {

    },
    methods:{
        aa(){
            window.location.href="http://www.baidu.com"
        },
        bb(){
            window.open("http://www.baidu.com")
        }
    }
}
</script>
<style scoped>
/* @import url(); 引入css类 */

</style>