import request from '@/utils/request'
import querystring from 'querystring'


export function getListPage (params) {
  let queryString = querystring.stringify(params);
  return request({
    url: '/quartz/busTask/findListPage?'+queryString,
    method: 'get',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    }
  })
}
  
export function saveOrUpdate (params) {
  let url = '/quartz/busTask';
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

export function del (ids) {
  return request({
    url: '/quartz/busTask/'+ids,
    method: 'delete',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    }
  })
}

export function stopOrRestore(params) {
  let queryString = querystring.stringify(params);
  return request({
    url: '/quartz/busTask/stopOrRestore?'+queryString,
    method: 'get',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    }
  })
}

export function execution(jobName,jobGroup) {
  return request({
    url: '/quartz/busTask/execution?jobName='+jobName+'&jobGroup='+jobGroup,
    method: 'get'
  })
}


  