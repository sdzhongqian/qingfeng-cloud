import request from '@/utils/request'
import querystring from 'querystring'

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

export function delData (ids) {
  return request({
    url: '/${tablePd.mod_name}/${tablePd.bus_name}/'+ids,
    method: 'delete',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    }
  })
}

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



