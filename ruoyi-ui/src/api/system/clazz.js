import request from '@/utils/request'

// 查询班级管理列表
export function listClazz(query) {
  return request({
    url: '/system/clazz/list',
    method: 'get',
    params: query
  })
}

// 查询班级管理详细
export function getClazz(clazzId) {
  return request({
    url: '/system/clazz/' + clazzId,
    method: 'get'
  })
}

// 新增班级管理
export function addClazz(data) {
  return request({
    url: '/system/clazz',
    method: 'post',
    data: data
  })
}

// 修改班级管理
export function updateClazz(data) {
  return request({
    url: '/system/clazz',
    method: 'put',
    data: data
  })
}

// 删除班级管理
export function delClazz(clazzId) {
  return request({
    url: '/system/clazz/' + clazzId,
    method: 'delete'
  })
}
