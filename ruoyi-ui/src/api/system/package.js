import request from '@/utils/request'


// 查询课程包管理列表
export function listPackage(query) {
  return request({
    url: '/system/package/list',
    method: 'get',
    params: query
  })
}

// 查询课程包管理详细
export function getPackage(id) {
  return request({
    url: '/system/package/' + id,
    method: 'get'
  })
}

// 新增课程包管理
export function addPackage(data) {
  return request({
    url: '/system/package',
    method: 'post',
    data: data
  })
}

// 修改课程包管理
export function updatePackage(data) {
  return request({
    url: '/system/package',
    method: 'put',
    data: data
  })
}

// 删除课程包管理
export function delPackage(id) {
  return request({
    url: '/system/package/' + id,
    method: 'delete'
  })
}

// 根据课程id获取课程包
export function listPackageByCourse(courseId) {
  return request({
    url: '/system/package/listByCourse/' + courseId,
    method: 'get'
  })
}
