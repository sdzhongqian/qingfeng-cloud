import request from '@/utils/request'
export function upload (formData) {
  return request({
    url: '/system/upload/uploadFile',
    method: 'post',
    data: formData
  })
}
