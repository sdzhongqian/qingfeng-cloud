import { asyncRouterMap, constantRouterMap } from '@/config/router.config'
import { getTreeList } from "@/api/system/menu";
import { BasicLayout, UserLayout } from '@/layouts'

const RouteView = {
  name: 'RouteView',
  render: (h) => h('router-view'),
}

/**
 * 过滤账户是否拥有某一个权限，并将菜单从加载列表移除
 *
 * @param permission
 * @param route
 * @returns {boolean}
 */
function hasPermission(permission, route) {
  if (route.meta && route.meta.permission) {
    let flag = false
    for (let i = 0, len = permission.length; i < len; i++) {
      flag = route.meta.permission.includes(permission[i])
      if (flag) {
        return true
      }
    }
    return false
  }
  return false
}

/**
 * 单账户多角色时，使用该方法可过滤角色不存在的菜单
 *
 * @param roles
 * @param route
 * @returns {*}
 */
// eslint-disable-next-line
function hasRole(roles, route) {
  if (route.meta && route.meta.roles) {
    return route.meta.roles.includes(roles.id)
  } else {
    return true
  }
}

function filterAsyncRouter(routerMap, roles) {
  const accessedRouters = routerMap.filter(route => {
    if (hasPermission(roles.permissionList, route)) {
      if (route.children && route.children.length) {
        route.children = filterAsyncRouter(route.children, roles)
      }
      return true
    }
    return false
  })
  return accessedRouters
}

function filterEmptyDirectory(routerMap) {
  const accessedRouters = routerMap.filter(route => {
    if (route.children && route.children.length) {
      route.children = filterEmptyDirectory(route.children)
      return true
    } else {
      if (route.meta.permission.includes('system')) {
        return false
      } else {
        return true
      }
    }
  })
  return accessedRouters
}

function fommat({
  arrayList,
  pidStr = "parent_id",
  idStr = "id",
  childrenStr = "children",
}) {
  arrayList.push({
    path: "/",
    name: "index",
    component: BasicLayout,
    title: '主页',
    redirect: '/dashboard/welcome',
    id: "1",
    child_num: 1
  });
  let listOjb = {}; // 用来储存{key: obj}格式的对象
  let treeList = []; // 用来储存最终树形结构数据的数组
  // 将数据变换成{key: obj}格式，方便下面处理数据
  for (let i = 0; i < arrayList.length; i++) {
    var data = arrayList[i];
    data.key = data.id;
    data.icon = "";
    //处理菜单格式信息
    if (data.type == '0') {//目录
      data.component = RouteView
    } else if (data.type == '1') {
      const views = data.component;
      if (views == '/dashboard/Welcome' || views == '/dashboard/welcome') {
        data.component = (resolve) => require([`@/views/dashboard/Welcome`], resolve)
      } else {
        data.component = (resolve) => require([`@/views${views}/Index`], resolve)
      }
      // data.component = () => import(`@${views}`)
      // data.component = () => import('@/views${component}')
      // data.component = this.loadView(component)
    } else if (data.type == '4') {
     
      data.component = (resolve) => require([`@/views/system/Iframe/Index`], resolve)
    }
    const role_ids = data.role_ids?.split(',')
    if (data.child_num > 0) {
      if (role_ids == undefined) {
        role_ids = ['system']
      } else {
        role_ids.push('system');
      }
    }

    if (data.type == '3') {
      data.meta = {
        title: data.title,
        keepAlive: data.keepAlive,
        icon: data.qf_icon,
        permission: role_ids,
        target: '_black'
      }
    } else {
      data.meta = {
        title: data.title,
        keepAlive: data.keepAlive,
        icon: data.qf_icon,
        permission: role_ids
      }
    }

    // console.log(data.meta)
    listOjb[arrayList[i][idStr]] = data;
  }
  // 根据pid来将数据进行格式化
  for (let j = 0; j < arrayList.length; j++) {
    // 判断父级是否存在
    let haveParent = listOjb[arrayList[j][pidStr]];
    if (haveParent) {
      // 如果有没有父级children字段，就创建一个children字段
      !haveParent[childrenStr] && (haveParent[childrenStr] = []);
      // 在父级里插入子项
      haveParent[childrenStr].push(arrayList[j]);
    } else {
      // 如果没有父级直接插入到最外层
      treeList.push(arrayList[j]);
    }
  }
  return treeList;
}

// function loadView (view) {
//   return () => import(`@/views/${view}`)
//   // return (resolve) => require([`@/views/${view}`], resolve)
// }

const permission = {
  state: {
    routers: constantRouterMap,
    addRouters: [],
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers
      state.routers = constantRouterMap.concat(routers)
    },
  },
  actions: {
    GenerateRoutes({ commit }, data) {
      return new Promise(resolve => {
        const { roles } = data
        getTreeList({ types: '0,1,3,4' }).then((response) => {
          const treeData = fommat({
            arrayList: response.data.data,
            pidStr: "parent_id",
          });
          const accessedRouters = filterAsyncRouter(treeData, roles)
          const finalRouters = filterEmptyDirectory(accessedRouters)
          console.log("==================")
          console.log(finalRouters)
          commit('SET_ROUTERS', finalRouters)
          resolve()
        });
      })
    },
  },
}

export default permission
