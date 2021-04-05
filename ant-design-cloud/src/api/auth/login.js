import request from '@/utils/request'
import store from '@/store'
import defaultSettings from '@/config/defaultSettings'
/**
 * login func
 * parameter: {
 *     username: '',
 *     password: '',
 *     remember_me: true,
 *     captcha: '12345'
 * }
 * @param parameter
 * @returns {*}
 */
export function login (params) {
  params = {...params , grant_type : 'password'}
  return request({
    url: '/auth/oauth/token',
    method: 'post',
    data: params,
    transformRequest: [(params) => {
      return tansParams(params)
    }],
    headers: {
      'Authorization': defaultSettings.authorizationValue
    },
  })
}

export function refresh (params) {
  params = {...params , grant_type : 'refresh_token'}
  return request({
    url: '/auth/oauth/token',
    method: 'post',
    data: params,
    transformRequest: [(params) => {
      return tansParams(params)
    }],
    headers: {
      'Authorization': defaultSettings.authorizationValue
    },
  })
}

function tansParams(params) {
  let result = ''
  Object.keys(params).forEach((key) => {
    if (!Object.is(params[key], undefined) && !Object.is(params[key], null)) {
      result += encodeURIComponent(key) + '=' + encodeURIComponent(params[key]) + '&'
    }
  })
  return result
}


export function getInfo () {
  return request({
    url: '/system/user/findUserInfo',
    method: 'get',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    },
  })
}

export function logout () {
  return request({
    url: '/auth/signout',
    method: 'delete',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    },
  })
}

export function getLoginUser () {
  return request({
    url: '/system/user/findLoginUser',
    method: 'get',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    },
  })
}

export function updateUser (params) {
  return request({
    url: '/system/user/updateUser',
    method: 'post',
    data: params,
  })
}

export function updatePwd (params) {
  return request({
    url: '/system/user/updateMyPwd',
    method: 'post',
    data: params,
  })
}

export function updateSwitchOrganize (parameter) {
  return request({
    url: '/system/user/updateSwitchOrganize',
    method: 'post',
    data: parameter,
  })
}

