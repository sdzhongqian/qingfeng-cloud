import storage from 'store'
import { login, getInfo, logout } from '@/api/auth/login'
import { ACCESS_TOKEN,REFRESH_TOKEN,EXPIRE_TIME } from '@/store/mutation-types'

const user = {
  state: {
    token: '',
    refreshToken: '',
    expireTime: '',
    authName:'',
    name: '',
    welcome: '',
    avatar: '',
    roles: [],
    info: {},
    authButton: [],
    authOrganize: {},
    loginUser:{}
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      storage.set(ACCESS_TOKEN,token);
      state.token = token
    },
    SET_REFRESH_TOKEN:(state,token)=> {
      storage.set(REFRESH_TOKEN,token);
      state.refreshToken = token
    },
    SET_EXPIRE_TIME:(state,expireTime)=> {
      storage.set(EXPIRE_TIME,expireTime);
      state.expireTime = expireTime
    },
    SET_AUTHNAME:(state,authName)=> {
      state.authName = authName
    },
    SET_NAME: (state, { name, welcome }) => {
      state.name = name
      state.welcome = welcome
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_INFO: (state, info) => {
      state.info = info
    },
    SET_AUTHBUTTON: (state, authButton) => {
      state.authButton = authButton
    },
    SET_AUTHORGANIZE: (state, authOrganize) => {
      state.authOrganize = authOrganize
    },
    SET_LOGINUSER: (state, user) => {
      state.loginUser = user
    },
  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
      return new Promise((resolve, reject) => {
        login(userInfo).then(response => {
          console.log('=====================')
          console.log(response)
          const data = response.data
          commit('SET_TOKEN', data.access_token)
          commit('SET_REFRESH_TOKEN', data.refresh_token)
          const current = new Date()
          const expireTime = current.setTime(current.getTime() + 1000 * data.expires_in)
          commit('SET_EXPIRE_TIME', expireTime)
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    GetInfo({ commit }) {
      return new Promise((resolve, reject) => {
        getInfo().then(res => {
          const response = res.data.data;
          if (response.role && response.role.permissions.length > 0) {
            const role = response.role
            role.permissions = response.role.permissions
            // role.permissions.map(per => {
            //   if (per.actionEntitySet != null && per.actionEntitySet.length > 0) {
            //     const action = per.actionEntitySet.map(action => { return action.action })
            //     per.actionList = action
            //   }
            // })
            role.permissionList = role.permissions.map(permission => { return permission.id })
            role.permissionList.push('system')
            const permissionButtonList = response.authList.map(authData => { return authData.parent_permission + ':' + authData.permission })
            console.log("------------------------")
            // console.log(role.permissionList)
            // console.log(response.orgPd)
            // console.log(permissionButtonList)

            commit('SET_AUTHORGANIZE', response.orgPd)
            commit('SET_AUTHBUTTON', permissionButtonList)
            commit('SET_ROLES', response.role)
            commit('SET_INFO', response)

            commit('SET_AUTHNAME', response.authName)
          } else {
            reject(new Error('getInfo: roles must be a non-null array !'))
          }

          commit('SET_NAME', { name: response.name, welcome: '' })
          commit('SET_AVATAR', response.avatar)

          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 登出
    Logout({ commit, state }) {
      return new Promise((resolve) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          storage.remove(ACCESS_TOKEN)
          resolve()
        }).catch(() => {
          resolve()
        }).finally(() => {

        })
      })
    },

    // 设置token
    SetToken({ commit }, token) {
      storage.set(ACCESS_TOKEN, token)
      commit('SET_TOKEN', token)
    },

    // 清空ClearRoles
    ClearRoles({ commit }) {
      commit('SET_ROLES', [])
    },

     // 设置SET_LOGINUSER
     SetLoginUser({ commit }, user) {
      commit('SET_LOGINUSER', user)
    },

  },
}

export default user
