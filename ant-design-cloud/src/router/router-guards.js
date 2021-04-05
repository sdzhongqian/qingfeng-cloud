import ls from 'store'
import router from './index'
import store from '@/store'
import NProgress from 'nprogress' // progress bar
import '@/components/NProgress/nprogress.less'
import { ACCESS_TOKEN } from '@/store/mutation-types' // progress bar custom style
import { notification } from 'ant-design-vue'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const allowList = ['login', 'register', 'registerResult'] // no redirect whitelist
const loginRoutePath = '/user/login'
const defaultRoutePath = '/dashboard'
import { Modal } from 'ant-design-vue';

router.beforeEach((to, from, next) => {
  //销毁所有弹框
  Modal.destroyAll();
  NProgress.start() // start progress bar
  const token = ls.get(ACCESS_TOKEN)
  // console.log('path:::::'+to.path)
  // console.log(token)
  if (token) {
    if (to.path === loginRoutePath) {
      next({ path: defaultRoutePath })
      NProgress.done()
    } else {
      //验证token是否有效，无效则刷新，有效则跳过。
      // check login user.roles is null
      // console.log(store.getters.roles)
      // if (store.getters.roles.length === 0) {
      if (store.getters.roles==''||store.getters.roles==undefined) {
        // console.log('999999999999999');
        store.dispatch('GetInfo').then(res => {
          const roles = res && res.role
          // generate dynamic router
          store.dispatch('GenerateRoutes', { roles }).then(() => {
            // 根据roles权限生成可访问的路由表
            // 动态添加可访问路由表
            // console.log('99999999999999999999999999');
            // console.log(store.getters.addRouters);
            router.addRoutes(store.getters.addRouters)
            // 请求带有 redirect 重定向时，登录自动重定向到该地址
            const redirect = decodeURIComponent(from.query.redirect || to.path)
            if (to.path === redirect) {
              // set the replace: true so the navigation will not leave a history record
              next({ ...to, replace: true })
            } else {
              // 跳转到目的路由
              next({ path: redirect })
            }
          })
        }).catch(() => {
          // notification.error({
          //   message: '错误',
          //   description: '请求用户信息失败，请重试',
          // })
          // 失败时，获取用户信息失败时，调用登出，来清空历史保留信息
          store.dispatch('Logout').then(() => {
            next({ path: loginRoutePath, query: { redirect: to.fullPath } })
          })
        })
      } else {
        next()
      }
    }
  } else {
    // not login
    if (allowList.includes(to.name)) {
      // 在免登录名单，直接进入
      next()
    } else {
      next({ path: loginRoutePath, query: { redirect: to.fullPath } })
      NProgress.done() // if current page is login will not trigger afterEach hook, so manually handle it
    }
  }
  next()
})

router.afterEach(() => {
  NProgress.done() // finish progress bar
})
