import request from '@/utils/request'

// 查询课程包内容管理列表
export function listContent(query) {
  return request({
    url: '/system/content/list',
    method: 'get',
    params: query
  })
}

// 查询课程包内容管理详细
export function getContent(id) {
  return request({
    url: '/system/content/' + id,
    method: 'get'
  })
}

// 新增课程包内容管理
export function addContent(data) {
  return request({
    url: '/system/content',
    method: 'post',
    data: data
  })
}

// 修改课程包内容管理
export function updateContent(data) {
  return request({
    url: '/system/content',
    method: 'put',
    data: data
  })
}

// 删除课程包内容管理
export function delContent(id) {
  return request({
    url: '/system/content/' + id,
    method: 'delete'
  })
}
