import request from '@/utils/request'

// 查询作业发布列表
export function listPublish(query) {
  return request({
    url: '/system/publish/list',
    method: 'get',
    params: query
  })
}

// 查询作业发布详细
export function getPublish(publishId) {
  return request({
    url: '/system/publish/' + publishId,
    method: 'get'
  })
}

// 新增作业发布
export function addPublish(data) {
  return request({
    url: '/system/publish',
    method: 'post',
    data: data
  })
}

// 修改作业发布
export function updatePublish(data) {
  return request({
    url: '/system/publish',
    method: 'put',
    data: data
  })
}

// 删除作业发布
export function delPublish(publishId) {
  return request({
    url: '/system/publish/' + publishId,
    method: 'delete'
  })
}
