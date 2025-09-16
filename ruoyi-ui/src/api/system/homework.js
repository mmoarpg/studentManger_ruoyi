import request from '@/utils/request'

// 查询作业定义列表
export function listHomework(query) {
  return request({
    url: '/system/homework/list',
    method: 'get',
    params: query
  })
}

// 查询作业定义详细
export function getHomework(homeworkId) {
  return request({
    url: '/system/homework/' + homeworkId,
    method: 'get'
  })
}

// 新增作业定义
export function addHomework(data) {
  return request({
    url: '/system/homework',
    method: 'post',
    data: data
  })
}

// 修改作业定义
export function updateHomework(data) {
  return request({
    url: '/system/homework',
    method: 'put',
    data: data
  })
}

// 删除作业定义
export function delHomework(homeworkId) {
  return request({
    url: '/system/homework/' + homeworkId,
    method: 'delete'
  })
}
