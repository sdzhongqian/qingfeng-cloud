import request from '@/utils/request'
import querystring from 'querystring'

/**
* 查询数据分页列表
* @param {*} params
* @returns
*/
export function getListPage (params) {
  let queryString = querystring.stringify(params);
  return request({
    url: '/${tablePd.mod_name}/${tablePd.bus_name}/findListPage?'+queryString,
    method: 'get',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    }
  })
}

/**
* 保存或更新数据
* @param {*} params
* @returns
*/
export function saveOrUpdate (params) {
  let url = '/${tablePd.mod_name}/${tablePd.bus_name}';
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
    url: '/${tablePd.mod_name}/${tablePd.bus_name}/'+ids,
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
    url: '/${tablePd.mod_name}/${tablePd.bus_name}/updateStatus',
    method: 'post',
    data: {
      id,
      status
    }
  })
}

/**
* 查询数据列表
* @param {*} params
* @returns
*/
export function getList (params) {
  let queryString = querystring.stringify(params);
  return request({
    url: '/${tablePd.mod_name}/${tablePd.bus_name}/findList?'+queryString,
    method: 'get',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    }
  })
}



