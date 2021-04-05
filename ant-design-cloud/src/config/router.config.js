import { BasicLayout, UserLayout } from '@/layouts'

const RouteView = {
  name: 'RouteView',
  render: (h) => h('router-view'),
}

export const constantRouterMap = [
  {
    path: '/user',
    name: 'user',
    component: UserLayout,
    children: [
      {
        path: '/user/login',
        name: 'login',
        component: () => import('@/views/user/Login'),
      },
    ],
  },
  {
    path: '/404',
    component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/404'),
  },
]

export const asyncRouterMap = [
  {
    path: '/',
    name: 'index',
    component: BasicLayout,
    meta: { title: 'menu.home' },
    redirect: '/dashboard/welcome',
    children: [
      // dashboard
      {
        path: '/dashboard',
        name: 'dashboard',
        redirect: '/dashboard/welcome',
        component: RouteView,
        meta: { title: 'menu.dashboard.default', keepAlive: true, icon: 'dashboard', permission: ['dashboard'] },
        children: [
          {
            path: '/dashboard/welcome',
            name: 'Welcome',
            component: () => import('@/views/dashboard/Welcome'),
            meta: { title: 'menu.dashboard.welcome', keepAlive: false, permission: ['dashboard'] },
          },
          {
            path: 'https://www.baidu.com/',
            name: 'Monitor',
            meta: { title: 'menu.dashboard.monitor', target: '_top' }
          },
        ],
      },
      {
        path: '/system',
        name: 'system',
        redirect: '/system/user',
        component: RouteView,
        meta: { title: 'menu.system.default', keepAlive: true, icon: 'dashboard', permission: ['system'] },
        children: [
          {
            path: '/system/user',
            name: 'User',
            component: RouteView,
            meta: { title: 'menu.system.user', keepAlive: false, permission: ['system'] },
            children: [
              {
                path: '/system/user',
                name: 'User',
                component: () => import('@/views/system/User/Index'),
                meta: { title: 'menu.system.user', keepAlive: false, permission: ['system'] },
              },
              {
                path: '/system/wl',
                name: 'Wl',
                redirect: 'http://www.baidu.com',
                meta: { title: '测试外链', keepAlive: false, permission: ['system'] },
              }
            ]
          },
          {
            path: '/system/organize',
            name: 'Organize',
            component: () => import('@/views/system/Organize/Index'),
            meta: { title: 'menu.system.organize', keepAlive: false, permission: ['system'] },
          },
          {
            path: '/system/role',
            name: 'Role',
            component: () => import('@/views/system/Role/Index'),
            meta: { title: 'menu.system.role', keepAlive: false, permission: ['system'] },
          },
          {
            path: '/system/dictionary',
            name: 'Dictionary',
            component: () => import('@/views/system/Dictionary/Index'),
            meta: { title: 'menu.system.dictionary', keepAlive: false, permission: ['system'] },
          },
          {
            path: '/system/menu',
            name: 'Menu',
            component: () => import('@/views/system/Menu/Index'),
            meta: { title: 'menu.system.menu', keepAlive: false, permission: ['system'] },
          },
          {
            path: '/system/area',
            name: 'Area',
            component: () => import('@/views/system/Area/Index'),
            meta: { title: 'menu.system.area', keepAlive: false, permission: ['system'] },
          },
          {
            path: '/system/group',
            name: 'Group',
            component: () => import('@/views/system/Group/Index'),
            meta: { title: 'menu.system.group', keepAlive: false, permission: ['system'] },
          },
        ],
      },
      {
        path: '/example',
        name: 'example',
        meta: {
          keepAlive: true,
          title: 'menu.example.default',
          icon: 'video-camera',
        },
        component: RouteView,
        children: [
          {
            path: '/example/userOrganize',
            name: 'userOrganize',
            component: () => import('@/views/example/userOrganize/Index'),
            meta: { title: 'menu.example.userOrganize', keepAlive: false, permission: ['system'] },
          },
          {
            path: '/example/openHref',
            name: 'openHref',
            component: () => import('@/views/example/openHref/Index'),
            meta: { title: 'menu.example.openHref', keepAlive: false, permission: ['system'] },
          },
        ],
      },
    ],
  },
  {
    path: '*', redirect: '/404', hidden: true,
  },
]
