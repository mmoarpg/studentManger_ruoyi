<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams">
      <el-form-item label="班级">
        <el-input v-model="queryParams.clazzName" placeholder="请输入班级"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getList">查询</el-button>
        <el-button type="success" @click="handleAdd">新增发布</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="list" border>
      <el-table-column prop="homeworkTitle" label="作业标题"/>
      <el-table-column prop="clazzName" label="班级"/>
      <el-table-column prop="courseName" label="课程"/>
      <el-table-column prop="teacherName" label="教师"/>
      <el-table-column prop="deadline" label="截止时间"/>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="title" :visible.sync="open" width="600px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="作业">
          <el-select v-model="form.homeworkId">
            <el-option v-for="h in homeworkOptions" :key="h.homeworkId" :label="h.title" :value="h.homeworkId"/>
          </el-select>
        </el-form-item>
        <el-form-item label="班级">
          <el-select v-model="form.clazzId">
            <el-option v-for="c in clazzOptions" :key="c.clazzId" :label="c.clazzName" :value="c.clazzId"/>
          </el-select>
        </el-form-item>
        <el-form-item label="课程">
          <el-select v-model="form.courseId">
            <el-option v-for="co in courseOptions" :key="co.courseId" :label="co.courseName" :value="co.courseId"/>
          </el-select>
        </el-form-item>
        <el-form-item label="教师">
          <el-select v-model="form.teacherId">
            <el-option v-for="t in teacherOptions" :key="t.teacherId" :label="t.teacherName" :value="t.teacherId"/>
          </el-select>
        </el-form-item>
        <el-form-item label="截止时间">
          <el-date-picker v-model="form.deadline" type="datetime"/>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="open=false">取消</el-button>
        <el-button type="primary" @click="submitForm">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPublish, addPublish, updatePublish, delPublish, getPublish } from '@/api/system/publish'
import { listHomework } from '@/api/system/homework'
import { listClazz } from '@/api/system/clazz'
import { listCourse } from '@/api/system/course'
import { listTeacher } from '@/api/system/teacher'

export default {
  data() {
    return {
      list: [],
      queryParams: {},
      form: {},
      open: false,
      title: '',
      homeworkOptions: [],
      clazzOptions: [],
      courseOptions: [],
      teacherOptions: []
    }
  },
  created() {
    this.getList()
    this.loadOptions()
  },
  methods: {
    getList() {
      listPublish(this.queryParams).then(res => this.list = res.rows)
    },
    loadOptions() {
      listHomework({ pageNum: 1, pageSize: 999 }).then(res => this.homeworkOptions = res.rows)
      listClazz({ pageNum: 1, pageSize: 999 }).then(res => this.clazzOptions = res.rows)
      listCourse({ pageNum: 1, pageSize: 999 }).then(res => this.courseOptions = res.rows)
      listTeacher({ pageNum: 1, pageSize: 999 }).then(res => this.teacherOptions = res.rows)
    },
    handleAdd() {
      this.form = {}
      this.open = true
      this.title = '新增发布'
    },
    handleUpdate(row) {
      getPublish(row.publishId).then(res => {
        this.form = res.data
        this.open = true
        this.title = '修改发布'
      })
    },
    handleDelete(row) {
      this.$confirm('确认删除？').then(() => {
        delPublish(row.publishId).then(() => this.getList())
      })
    },
    submitForm() {
      if (this.form.publishId) {
        updatePublish(this.form).then(() => {
          this.$message.success('修改成功')
          this.open = false
          this.getList()
        })
      } else {
        addPublish(this.form).then(() => {
          this.$message.success('新增成功')
          this.open = false
          this.getList()
        })
      }
    }
  }
}
</script>
