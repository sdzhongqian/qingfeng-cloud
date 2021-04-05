import request from '@/utils/request'
import querystring from 'querystring'

/**
 * 查询分页列表
 * @param {*} params 
 * @returns 
 */
export function getListPage (params) {
  let queryString = querystring.stringify(params);
  return request({
    url: '/system/user/findListPage?'+queryString,
    method: 'get',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    }
  })
}
  
/**
 * 保存或更新
 * @param {*} params 
 * @returns 
 */
export function saveOrUpdate (params) {
  let url = '/system/user';
  let method = 'post';
  if(params.id!=''&&params.id!=undefined){
    method = 'put';
  }
  return request({
    url: url,
    method: method,
    data: params
  })
}

/**
 * 删除数据
 * @param {*} ids 
 * @returns 
 */
export function delData (ids) {
  return request({
    url: '/system/user/'+ids,
    method: 'delete',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    }
  })
}

/**
 * 更新状态
 * @param {*} id 
 * @param {*} status 
 * @returns 
 */
export function updateStatus(id,status) {
  return request({
    url: '/system/user/updateStatus',
    method: 'post',
    data: {
      id,
      status
    }
  })
}

/**
 * 获取用户列表
 * @param {*} params 
 * @returns 
 */
export function getList (params) {
  let queryString = querystring.stringify(params);
  return request({
    url: '/system/user/findList?'+queryString,
    method: 'get',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    }
  })
}

/**
 * 密码更新
 * @param {*} params 
 * @returns 
 */
export function updatePwd (params) {
  let queryString = querystring.stringify(params);
  return request({
    url: '/system/user/updatePwd?'+queryString,
    method: 'get',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    }
  })
}

/**
 * 获取用户组织信息分页列表
 * @param {*} params 
 * @returns 
 */
export function getMyOrganizeListPage (params) {
  let queryString = querystring.stringify(params);
  return request({
    url: '/system/user/findUserOrganizeListPage?'+queryString,
    method: 'get',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    }
  })
}

/**
 * 保存或更新用户组织信息
 * @param {*} params 
 * @returns 
 */
export function saveOrUpdateUserOrganize (params) {
  return request({
    url: '/system/user/saveOrUpdateUserOrganize',
    method: 'post',
    data: params
  })
}

/**
 * 删除用户组织信息
 * @param {*} id 
 * @returns 
 */
export function delUserOrganize(id) {
  return request({
    url: `/system/user/delUserOrganize/`+id,
    method: 'delete',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    }
  })
}

/**
 * 获取用户权限信息
 * @param {*} params 
 * @returns 
 */
export function getRoleAuth (params) {
  return request({
    url: '/system/user/findRoleAuth',
    method: 'post',
    data: params
  })
}

/**
 * 获取组织权限信息
 * @param {*} params 
 * @returns 
 */
export function getOrganizeAuth (params) {
  return request({
    url: '/system/user/findOrganizeAuth',
    method: 'post',
    data: params
  })
}

/**
 * 更新权限信息
 * @param {*} params 
 * @returns 
 */
export function updateAuth (params) {
  return request({
    url: '/system/user/updateAuth',
    method: 'post',
    data: params
  })
}

/**
 * 获取用户详情
 * @param {*} id 
 * @returns 
 */
export function getUserInfo (id) {
  return request({
    url: `/system/user/getUserInfo?id=${id}`,
    method: 'get',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    }
  })
}