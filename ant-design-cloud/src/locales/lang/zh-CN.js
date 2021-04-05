import exceptionLang from '@/views/exception/locales/zhCN'

export default {
  navBar: {
    lang: '语言',
  },
  layouts: {
    usermenu: {
      dialog: {
        title: '注销',
        content: '要注销账户吗?',
      },
    },
  },
  menu: {
    home: '首页',
    dashboard: {
      default: '仪表盘',
      welcome: '欢迎',
      workplace: '工作台',
    },
    system:{
      default:'系统管理',
      user:'用户管理',
      role:'角色管理',
      organize:'组织管理',
      dictionary:'字典管理',
      menu:'菜单管理',
      area:'地区管理',
      group:'用户组管理'
    },
    example:{
      default:'案例管理',
      userOrganize:'选择用户组织',
      openHref:'跳转外部链接'
    },
    form: {
      default: '表单页',
      basicform: '基础表单',
      stepform: '分步表单',
      advancedform: '高级表单',
    },
    nav1: '导航1',
    nav2: '导航2',
    nav3: '导航3',
  },

  pages: {
    dashboard: {
      welcome: {
        tips: '欢迎使用 Ant Design Vue',
        'show-loading': '显示 Loading',
        'hide-loading': '隐藏 Loading',
      },
    },
    form: {
      basicform: {
        headers: {
          btn1: '按钮1',
          customTitle: '自定义标题',
        },
        content: '表单页用于向用户收集或验证信息，基础表单常见于数据项较少的表单场景。',
        tabs: {
          tab1: '标签1',
          tab2: '标签2',
          tab3: '标签3',
        },
      },
    },
  },

  'navBar.lang': '语言',

  'app.setting.pagestyle': '整体风格设置',
  'app.setting.pagestyle.light': '亮色菜单风格',
  'app.setting.pagestyle.dark': '暗色菜单风格',
  'app.setting.pagestyle.realdark': '黑暗模式',
  'app.setting.themecolor': '主题色',
  'app.setting.navigationmode': '导航模式',
  'app.setting.content-width': '内容区域宽度',
  'app.setting.fixedheader': '固定头部',
  'app.setting.fixedsidebar': '固定侧边栏',
  'app.setting.sidemenu': '侧面菜单布局',
  'app.setting.topmenu': '头部菜单布局',
  'app.setting.content-width.fixed': '固定',
  'app.setting.content-width.fluid': '非固定',
  'app.setting.othersettings': '其他设置',
  'app.setting.weakmode': '色弱模式',
  'app.setting.copy': '拷贝设置',
  'app.setting.loading': '正在载入主题',
  'app.setting.copyinfo': '拷贝设置成功 src/config/defaultSettings.js',
  'app.setting.production.hint': '配置栏只在开发环境用于预览，生产环境不会展现，请拷贝后手动修改配置文件',

  // page locales
  ...exceptionLang,
}
