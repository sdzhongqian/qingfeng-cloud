<template>
  <global-footer class="footer custom-render">
    <template v-slot:links>
      <a href="http://xinlingge.cn:8181/qingfeng/main" target="_blank">青锋预览</a>
      <a href="https://gitee.com/msxy/qingfeng" target="_blank">Gitee</a>
      <a href="#" target="_blank">@青锋</a>
    </template>
    <template v-slot:copyright>
      <a href="https://www.antdv.com/docs/vue/introduce-cn/" target="_blank">vue ant design</a>
    </template>
  </global-footer>
</template>

<script>
import { GlobalFooter } from '@ant-design-vue/pro-layout'

export default {
  name: 'ProGlobalFooter',
  components: {
    GlobalFooter,
  },
}
</script>
