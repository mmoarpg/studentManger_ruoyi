<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课程名称" prop="courseName">
        <el-input
          v-model="queryParams.courseName"
          placeholder="请输入课程名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程时长" prop="duration">
        <el-input
          v-model="queryParams.duration"
          placeholder="请输入课程时长"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:course:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:course:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:course:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:course:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="courseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="课程ID" align="center" prop="courseId" />
      <el-table-column label="课程名称" align="center" prop="courseName" />
      <el-table-column label="课程时长" align="center" prop="duration" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:course:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:course:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改课程管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程名称" prop="courseName">
          <el-input v-model="form.courseName" placeholder="请输入课程名称" />
        </el-form-item>
        <el-form-item label="课程时长" prop="duration">
          <el-input v-model="form.duration" placeholder="请输入课程时长" />
        </el-form-item>

        <el-form-item label="课程包">
          <el-tag
            v-for="name in form.packageNames"
            :key="name"
            closable
            @close="removePackage(name)">
            {{ name }}
          </el-tag>
          <el-button type="primary" plain size="mini" @click="openPkgDialog">选择课程包</el-button>
          <el-button type="text" size="mini" @click="clearPackages" v-if="form.packageIds.length">清空</el-button>
        </el-form-item>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 课程包选择弹窗 -->
    <el-dialog :title="pkgDialog.title" :visible.sync="pkgDialog.open" width="700px" append-to-body>
      <div style="margin-bottom:10px;">
        <el-input v-model="pkgDialog.query.packageName" placeholder="搜索课程包名称" size="small" style="width:220px;margin-right:6px" @keyup.enter.native="loadPkg"/>
        <el-button type="primary" size="small" @click="loadPkg">搜索</el-button>
      </div>

      <el-table ref="pkgTable"
        v-loading="pkgDialog.loading"
        :data="pkgDialog.list"
        @selection-change="onPkgSelectionChange"
        :row-key="row => row.id"
        height="360px"
      >
        <el-table-column type="selection" width="55"/>
        <el-table-column label="ID" prop="id" width="100"/>
        <el-table-column label="课程包名称" prop="packageName"/>
        <el-table-column label="描述" prop="description"/>
      </el-table>

      <pagination
        v-show="pkgDialog.total>0"
        :total="pkgDialog.total"
        :page.sync="pkgDialog.query.pageNum"
        :limit.sync="pkgDialog.query.pageSize"
        @pagination="loadPkg"
      />

      <div slot="footer" class="dialog-footer">
        <el-button @click="pkgDialog.open=false">取 消</el-button>
        <el-button type="primary" @click="confirmPkg">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listCourse, getCourse, delCourse, addCourse, updateCourse } from "@/api/system/course"
import { listPackage } from "@/api/system/package"

export default {
  name: "Course",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 课程管理表格数据
      courseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        courseName: null,
        duration: null,
      },
      // 表单参数
      form: {
        courseId: null,
        courseName: null,
        duration: null,
        packageIds: [],   // ★ 选中的课程包ID集合（提交给后端）
        createTime: null,
        updateTime: null,
        packageNames:[]
      },
      // 表单校验
      rules: {
      },
      pkgDialog: {
        open: false,
        loading: false,
        title: "选择课程包",
        list: [],         // 弹窗内的课程包数据
        total: 0,
        query: { pageNum: 1, pageSize: 10, packageName: null },
        selectedRowKeys: [], // 弹窗内临时选中的keys
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询课程管理列表 */
    getList() {
      this.loading = true
      listCourse(this.queryParams).then(response => {
        this.courseList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        courseId: null,
        courseName: null,
        duration: null,
        createTime: null,
        updateTime: null,
        packageIds: [],
        packageNames: []
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.courseId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加课程管理"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const courseId = row.courseId || this.ids
      getCourse(courseId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改课程管理"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.courseId != null) {
            updateCourse(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addCourse(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const courseIds = row.courseId || this.ids
      this.$modal.confirm('是否确认删除课程管理编号为"' + courseIds + '"的数据项？').then(function() {
        return delCourse(courseIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/course/export', {
        ...this.queryParams
      }, `course_${new Date().getTime()}.xlsx`)
    },
    openPkgDialog() {
      this.pkgDialog.open = true
      this.pkgDialog.selectedRowKeys = [...(this.form.packageIds || [])] // 带着已选进去
      this.loadPkg()
    },
// 拉取课程包
    loadPkg() {
      this.pkgDialog.loading = true
      listPackage(this.pkgDialog.query).then(res => {
        this.pkgDialog.list = res.rows || []
        this.pkgDialog.total = res.total || 0
        this.pkgDialog.loading = false
        // 恢复勾选
        this.$nextTick(() => {
          const table = this.$refs.pkgTable
          if (table && table.clearSelection) {
            table.clearSelection()
            this.pkgDialog.list.forEach(row => {
              if (this.pkgDialog.selectedRowKeys.includes(row.id)) {
                table.toggleRowSelection(row, true)
              }
            })
          }
        })
      }).catch(()=> this.pkgDialog.loading=false)
    },
// 勾选变化
    onPkgSelectionChange(rows) {
      this.pkgDialog.selectedRowKeys = rows.map(r => r.id)
    },
// 确定
    confirmPkg() {
      // 保存 ID
      this.form.packageIds = [...this.pkgDialog.selectedRowKeys]

      // 保存对应的名称
      this.form.packageNames = this.pkgDialog.list
        .filter(row => this.pkgDialog.selectedRowKeys.includes(row.id))
        .map(row => row.packageName)

      this.pkgDialog.open = false
    },

    clearPackages() {
      this.form.packageIds.splice(0)
      this.form.packageNames.splice(0)
      this.pkgDialog.selectedRowKeys = []
      const table = this.$refs.pkgTable
      if (table && table.clearSelection) {
        table.clearSelection()
      }
    },
    removePackage(name) {
      const idx = this.form.packageNames.indexOf(name)
      if (idx !== -1) {
        this.form.packageNames.splice(idx, 1)
        this.form.packageIds.splice(idx, 1)
        this.pkgDialog.selectedRowKeys = [...this.form.packageIds]
      }
    }
  }
}
</script>
