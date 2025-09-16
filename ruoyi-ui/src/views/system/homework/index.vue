<template>
  <div class="app-container">
    <!-- 顶部操作 -->
    <el-button type="primary" @click="handleAdd">新增作业</el-button>

    <!-- 作业列表 -->
    <el-table :data="list" border>
      <el-table-column prop="title" label="作业标题"/>
      <el-table-column prop="content" label="说明"/>
      <el-table-column prop="createTime" label="创建时间"/>
      <el-table-column label="操作" width="260">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
          <el-button size="mini" type="warning" @click="handlePublish(scope.row)">发布</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 新增/修改 作业对话框（仅作业本身，不含发布） -->
    <el-dialog :title="mode === 'add' ? '新增作业' : '修改作业'" :visible.sync="open" width="700px">
      <el-form :model="form" ref="form" label-width="80px">

        <el-form-item label="标题">
          <el-input v-model="form.title" placeholder="请输入标题"/>
        </el-form-item>

        <el-form-item label="布置老师">
          <el-select v-model="form.creatorId" placeholder="请选择布置老师" filterable>
            <el-option
              v-for="t in teacherOptions"
              :key="t.teacherId"
              :label="t.teacherName"
              :value="t.teacherId"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="说明">
          <el-input type="textarea" v-model="form.content" placeholder="请输入说明"/>
        </el-form-item>


      </el-form>
      <span slot="footer">
        <el-button @click="open=false">取消</el-button>
        <el-button type="primary" @click="submitForm">{{ mode === 'add' ? '新增' : '保存' }}</el-button>
      </span>
    </el-dialog>

    <!-- 发布管理对话框（仅发布，不含作业编辑；行内编辑，无三级弹窗） -->
    <el-dialog :title="'发布管理 - ' + (currentHomework.title || '')" :visible.sync="publishOpen" width="900px">
      <el-table :data="publishList" border>
        <!-- 班级 -->
        <el-table-column label="班级">
          <template slot-scope="{ row }">
            <template v-if="row.isEdit">
              <el-select v-model="row.clazzId" placeholder="选择班级" filterable style="width: 160px">
                <el-option v-for="c in clazzOptions" :key="c.clazzId" :label="c.clazzName" :value="c.clazzId"/>
              </el-select>
            </template>
            <template v-else>{{ row.clazzName }}</template>
          </template>
        </el-table-column>
        <!-- 课程 -->
        <el-table-column label="课程">
          <template slot-scope="{ row }">
            <template v-if="row.isEdit">
              <el-select v-model="row.courseId" placeholder="选择课程" filterable style="width: 160px">
                <el-option v-for="co in courseOptions" :key="co.courseId" :label="co.courseName" :value="co.courseId"/>
              </el-select>
            </template>
            <template v-else>{{ row.courseName }}</template>
          </template>
        </el-table-column>
        <!-- 教师 -->
        <el-table-column label="教师">
          <template slot-scope="{ row }">
            <template v-if="row.isEdit">
              <el-select v-model="row.teacherId" placeholder="选择教师" filterable style="width: 160px">
                <el-option v-for="t in teacherOptions" :key="t.teacherId" :label="t.teacherName" :value="t.teacherId"/>
              </el-select>
            </template>
            <template v-else>{{ row.teacherName }}</template>
          </template>
        </el-table-column>
        <!-- 截止时间 -->
        <el-table-column label="截止时间" width="210">
          <template slot-scope="{ row }">
            <template v-if="row.isEdit">
              <el-date-picker
                v-model="row.deadline"
                type="datetime"
                value-format="yyyy-MM-dd HH:mm:ss"
                placeholder="选择截止时间"
                style="width: 200px"
              />
            </template>
            <template v-else>{{ row.deadline }}</template>
          </template>
        </el-table-column>
        <!-- 操作 -->
        <el-table-column label="操作" width="200">
          <template slot-scope="{ row, $index }">
            <template v-if="row.isEdit">
              <el-button type="text" @click="savePublishRow(row)">保存</el-button>
              <el-button type="text" @click="cancelPublishRow(row, $index)">取消</el-button>
            </template>
            <template v-else>
              <el-button type="text" @click="editPublishRow(row)">编辑</el-button>
              <el-button type="text" @click="removePublishRow(row)">删除</el-button>
            </template>
          </template>
        </el-table-column>
      </el-table>

      <div class="mt10">
        <el-button type="primary" @click="addPublishRow">新增发布</el-button>
      </div>

      <div slot="footer">
        <el-button @click="publishOpen=false">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listHomework, getHomework, addHomework, updateHomework, delHomework } from '@/api/system/homework'
import { listClazz } from '@/api/system/clazz'
import { listCourse } from '@/api/system/course'
import { listTeacher } from '@/api/system/teacher'
import { listPublish, addPublish, updatePublish, delPublish } from '@/api/system/publish'

export default {
  data() {
    return {
      // 列表
      list: [],
      // 作业表单
      open: false,
      mode: 'add', // 'add' | 'edit'
      form: { homeworkId: undefined, title: '', content: '' },
      // 发布管理
      publishOpen: false,
      currentHomework: {},
      publishList: [],
      // 选项
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
    // ===== 作业：新增 / 修改 / 删除 =====
    getList() {
      listHomework().then(res => { this.list = res.rows || [] })
    },
    handleAdd() {
      this.mode = 'add'
      this.form = { homeworkId: undefined, title: '', content: '' }
      this.open = true
    },
    handleUpdate(row) {
      this.mode = 'edit'
      getHomework(row.homeworkId).then(res => {
        const d = res.data || {}
        this.form = { homeworkId: d.homeworkId, title: d.title, content: d.content }
        this.open = true
      })
    },
    handleDelete(row) {
      this.$confirm('确认删除该作业吗？').then(() => {
        delHomework(row.homeworkId).then(() => {
          this.$message.success('删除成功')
          this.getList()
        })
      })
    },
    submitForm() {
      if (this.mode === 'add') {
        addHomework(this.form).then(() => {
          this.$message.success('新增成功')
          this.open = false
          this.getList()
        })
      } else {
        updateHomework(this.form).then(() => {
          this.$message.success('保存成功')
          this.open = false
          this.getList()
        })
      }
    },

    // ===== 发布：独立管理，无三级弹窗；行内编辑 =====
    handlePublish(row) {
      this.currentHomework = row
      this.publishOpen = true
      this.reloadPublishList()
    },
    reloadPublishList() {
      listPublish({ homeworkId: this.currentHomework.homeworkId }).then(res => {
        // 加一层 isEdit=false
        this.publishList = (res.rows || []).map(x => ({ ...x, isEdit: false, __bak: null }))
      })
    },
    addPublishRow() {
      // 新行：isEdit=true，尚未有 publishId
      this.publishList.unshift({
        publishId: undefined,
        homeworkId: this.currentHomework.homeworkId,
        clazzId: undefined,
        courseId: undefined,
        teacherId: undefined,
        deadline: undefined,
        isEdit: true,
        __bak: null
      })
    },
    editPublishRow(row) {
      row.__bak = JSON.parse(JSON.stringify(row))
      row.isEdit = true
    },
    cancelPublishRow(row, index) {
      if (row.publishId) {
        // 还原
        const bak = row.__bak || {}
        Object.assign(row, bak, { isEdit: false, __bak: null })
      } else {
        // 新增未保存的行，直接移除
        this.publishList.splice(index, 1)
      }
    },
    savePublishRow(row) {
      // 校验（可按需加更严格校验）
      if (!row.clazzId || !row.courseId || !row.teacherId || !row.deadline) {
        this.$message.warning('请完整填写班级/课程/教师/截止时间')
        return
      }
      const payload = {
        publishId: row.publishId,
        homeworkId: this.currentHomework.homeworkId,
        clazzId: row.clazzId,
        courseId: row.courseId,
        teacherId: row.teacherId,
        deadline: row.deadline
      }
      const req = row.publishId ? updatePublish(payload) : addPublish(payload)
      req.then(() => {
        this.$message.success('保存成功')
        this.reloadPublishList() // 重新加载以拿到最新的 *Name 字段
      })
    },
    removePublishRow(row) {
      this.$confirm('确认删除该发布记录吗？').then(() => {
        if (!row.publishId) {
          // 新增未保存的行
          const idx = this.publishList.indexOf(row)
          if (idx > -1) this.publishList.splice(idx, 1)
          return
        }
        delPublish(row.publishId).then(() => {
          this.$message.success('删除成功')
          this.reloadPublishList()
        })
      })
    },

    // ===== 下拉选项 =====
    loadOptions() {
      listClazz({ pageNum: 1, pageSize: 9999 }).then(res => { this.clazzOptions = res.rows || [] })
      listCourse({ pageNum: 1, pageSize: 9999 }).then(res => { this.courseOptions = res.rows || [] })
      listTeacher({ pageNum: 1, pageSize: 9999 }).then(res => { this.teacherOptions = res.rows || [] })
    }
  }
}
</script>

<style scoped>
.mt10 { margin-top: 10px; }
</style>
