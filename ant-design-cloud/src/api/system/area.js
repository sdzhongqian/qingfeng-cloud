import request from '@/utils/request'
import querystring from 'querystring'

//查询数据分页列表
export function getListPage (params) {
  let queryString = querystring.stringify(params);
  return request({
    url: '/system/area/findListPage?'+queryString,
    method: 'get',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    }
  })
}
  
//保存或更新数据
export function saveOrUpdate (params) {
  let url = '/system/area';
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

//生成数据
export function delData (ids) {
  return request({
    url: '/system/area/'+ids,
    method: 'delete',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    }
  })
}

//更新数据状态
export function updateStatus(id,status) {
  return request({
    url: '/system/area/updateStatus',
    method: 'post',
    data: {
      id,
      status
    }
  })
}

//查询数据列表
export function getTreeList (params) {
  let queryString = querystring.stringify(params);
  return request({
    url: '/system/area/findList?'+queryString,
    method: 'get',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    }
  })
}




