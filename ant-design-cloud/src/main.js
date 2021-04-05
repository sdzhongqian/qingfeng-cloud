// with polyfills
import 'core-js/stable'
import 'regenerator-runtime/runtime'

import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import i18n from './locales'
import bootstrap from './core/bootstrap'
import { ConfigProvider, Icon, Button, Tag, Menu, Dropdown, Avatar, Spin, Result, Form, Tabs, Input, Checkbox,Select,Radio,TimePicker,Slider,DatePicker,InputNumber, Row, Col, Modal, Alert, Divider, notification, message,Table,Pagination,Breadcrumb,Space,FormModel,Card,Tree,Descriptions,Upload,List,Transfer,TreeSelect } from 'ant-design-vue'
import ProLayout, { PageHeaderWrapper } from '@ant-design-vue/pro-layout'
import { PageLoading } from '@/components'
import themeConfig from './config/theme.config.js'
import Dialog from '@/components/Dialog'
import utils from '@/utils/utils.js';

import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css';
import 'quill/dist/quill.snow.css';
import 'quill/dist/quill.bubble.css';
Vue.use(VueQuillEditor)

// 路由守卫
import './router/router-guards'
// 其他
import './styles/global.less'

// Ant Design Vue
Vue.use(ConfigProvider)
Vue.use(Icon)
Vue.use(Tag)
Vue.use(Button)
Vue.use(Menu)
Vue.use(Dropdown)
Vue.use(Avatar)
Vue.use(Spin)
Vue.use(Result)
Vue.use(Form)
Vue.use(Tabs)
Vue.use(Input)
Vue.use(Checkbox)
Vue.use(Select)
Vue.use(Radio)
Vue.use(TimePicker)
Vue.use(Slider)
Vue.use(DatePicker)
Vue.use(InputNumber)
Vue.use(Row)
Vue.use(Col)
Vue.use(Modal)
Vue.use(Alert)
Vue.use(Divider)
Vue.use(Table )
Vue.use(Pagination)
Vue.use(Breadcrumb)
Vue.use(Space)
Vue.use(Dialog)
Vue.use(FormModel)
Vue.use(Card)
Vue.use(Tree)
Vue.use(Descriptions)
Vue.use(Upload)
Vue.use(List)
Vue.use(Transfer)
Vue.use(TreeSelect)
Vue.use(utils)

Vue.prototype.$confirm = Modal.confirm
Vue.prototype.$message = message
Vue.prototype.$notification = notification
Vue.prototype.$info = Modal.info
Vue.prototype.$success = Modal.success
Vue.prototype.$error = Modal.error
Vue.prototype.$warning = Modal.warning

// ProLayout
Vue.component('pro-layout', ProLayout)
Vue.component('page-container', PageHeaderWrapper)
Vue.component('page-header-wrapper', PageHeaderWrapper)
window.umi_plugin_ant_themeVar = themeConfig.theme

// Global imports
Vue.use(PageLoading)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  i18n,
  created: bootstrap,
  render: h => h(App),
}).$mount('#app')
