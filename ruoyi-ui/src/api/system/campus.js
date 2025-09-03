import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listCampus(query) {
  return request({
    url: '/system/campus/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getCampus(campusId) {
  return request({
    url: '/system/campus/' + campusId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addCampus(data) {
  return request({
    url: '/system/campus',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateCampus(data) {
  return request({
    url: '/system/campus',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delCampus(campusId) {
  return request({
    url: '/system/campus/' + campusId,
    method: 'delete'
  })
}
