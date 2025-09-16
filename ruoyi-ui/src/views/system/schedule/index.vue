<template>
  <div class="app-container">

    <!-- 班级切换 -->
    <el-form inline size="small" class="mb10">
      <el-form-item label="班级">
        <el-select v-model="selectedClazzId" placeholder="请选择班级" @change="loadSchedule">
          <el-option v-for="c in clazzOptions"
                     :key="c.clazzId"
                     :label="c.clazzName"
                     :value="c.clazzId"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增课程</el-button>
      </el-form-item>
    </el-form>

    <el-form inline size="small" class="mb10">
      <el-form-item label="周次">
        <el-button type="primary" icon="el-icon-arrow-left" size="mini" @click="changeWeek(-1)">上一周</el-button>
        <el-select v-model="selectedWeekNo" placeholder="请选择周次" @change="loadSchedule">
          <el-option v-for="w in weekOptions" :key="w" :label="'第' + w + '周'" :value="w"/>
        </el-select>
        <el-button type="primary" icon="el-icon-arrow-right" size="mini" @click="changeWeek(1)">下一周</el-button>
      </el-form-item>
    </el-form>


    <!-- 课表 -->
    <el-card>
      <div slot="header">班级课表</div>
      <el-table :data="sections" border>
        <el-table-column prop="section" label="节次" width="100"/>
        <el-table-column v-for="(day, idx) in weekDays" :key="idx" :label="day">
          <template slot-scope="scope">
            <draggable
              :list="getCell(scope.row, idx+1)"
              group="schedule"
              @end="onDragEnd"
              class="drag-area"
            >
              <div v-for="item in getCell(scope.row, idx+1)"
                   :key="item.scheduleId"
                   class="drag-item">
                <div class="course-card">
                  <div class="course-name">{{ item.courseName }}</div>
                  <div class="package">课程包：{{ item.packageName || '-' }}</div>
                  <div class="teacher">教师：{{ item.teacherName }}</div>
                  <div class="week-range">周次：第{{ item.startWeek }}-{{ item.endWeek }}周</div>
                </div>
              </div>

            </draggable>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 添加/修改课程排课 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="课程" prop="courseId">
        <el-select
          v-model="form.courseId"
          placeholder="请选择课程"
          filterable
          clearable
          @change="loadPackageOptions"
        >
          <el-option
            v-for="c in courseOptions"
            :key="c.value"
            :label="c.label"
            :value="c.value"
          />
        </el-select>
      </el-form-item>

        <el-form-item label="周次">
          <el-select v-model="selectedWeekNo" placeholder="请选择周次" @change="loadSchedule">
            <el-option v-for="w in weekOptions" :key="w" :label="'第' + w + '周'" :value="w"/>
          </el-select>
        </el-form-item>

        <el-form-item label="课程包" prop="packageId">
          <el-select v-model="form.packageId" placeholder="请选择课程包" filterable clearable>
            <el-option
              v-for="p in packageOptions"
              :key="p.value"
              :label="p.label"
              :value="p.value"
            />
          </el-select>
        </el-form-item>


        <el-form-item label="教师" prop="teacherId">
          <el-select v-model="form.teacherId" placeholder="请选择教师" filterable clearable>
            <el-option v-for="t in teacherOptions" :key="t.value" :label="t.label" :value="t.value"/>
          </el-select>
        </el-form-item>

        <el-form-item label="周几" prop="weekDay">
          <el-select v-model="form.weekDay" placeholder="请选择周几">
            <el-option v-for="(d, i) in weekDays" :key="i" :label="d" :value="i+1"/>
          </el-select>
        </el-form-item>
        <el-form-item label="节次" prop="section">
          <el-select v-model="form.section" placeholder="请选择节次">
            <el-option v-for="s in sections" :key="s.name" :label="s.name" :value="s.start"/>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listSchedule, addSchedule, updateSchedule} from "@/api/system/schedule"
import {listClazz} from "@/api/system/clazz"
import draggable from "vuedraggable"
import {listCourse} from "@/api/system/course"
import {listTeacher} from "@/api/system/teacher"
import { listPackageByCourse } from "@/api/system/package";


export default {
  name: "Schedule",
  components: {draggable},
  data() {
    return {
      // 班级选择
      clazzOptions: [],
      courseOptions: [],
      teacherOptions: [],
      packageOptions: [],
      selectedClazzId: null,
      selectedWeekNo: 1,
      weekOptions: Array.from({ length: 18 }, (_, i) => i + 1),

      // 课表展示
      weekDays: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
      sections: [
        {name: "第1节", start: "08:00", end: "09:40"},
        {name: "第2节", start: "10:00", end: "11:40"},
        {name: "第3节", start: "14:00", end: "15:40"},
        {name: "第4节", start: "16:00", end: "17:40"}
      ],
      schedules: [],

      // 弹窗
      open: false,
      title: "",
      form: {
        packageId: null,
        startWeek: null,
        endWeek: null
      },
      rules: {
        courseId: [{required: true, message: "课程ID不能为空", trigger: "blur"}],
        teacherId: [{required: true, message: "教师ID不能为空", trigger: "blur"}],
        weekDay: [{required: true, message: "周几不能为空", trigger: "change"}],
        section: [{required: true, message: "节次不能为空", trigger: "change"}]
      }
    }
  },
  created() {
    this.loadClazz()
    this.loadCourseOptions()
    this.loadTeacherOptions()
  },
  methods: {
    /** 班级列表 */
    loadClazz() {
      listClazz().then(res => {
        this.clazzOptions = res.rows
        if (this.clazzOptions.length > 0) {
          this.selectedClazzId = this.clazzOptions[0].clazzId
          this.loadSchedule()
        }
      })
    },
    // /** 课表 */
    // loadSchedule() {
    //   listSchedule({clazzId: this.selectedClazzId}).then(res => {
    //     console.log("课表返回：", res.rows)
    //     this.schedules = res.rows
    //   })
    // },
    /** 获取格子里的课程 */
    getCell(section, weekDay) {
      return this.schedules.filter(s =>
        s.weekDay === weekDay &&
        s.startTime === section.start &&
        this.selectedWeekNo >= s.startWeek &&
        this.selectedWeekNo <= s.endWeek
      )
    },

    /** 拖拽排课 */
    onDragEnd(evt) {
      let moved = evt.item.__vue__.item
      let newCol = evt.to.closest("th").innerText
      let newRow = evt.to.closest("tr").querySelector("td").innerText

      let sec = this.sections.find(s => s.name === newRow)
      let weekDay = this.weekDays.indexOf(newCol) + 1

      moved.weekDay = weekDay
      moved.startTime = sec.start
      moved.endTime = sec.end
      moved.clazzId = this.selectedClazzId

      updateSchedule(moved).then(() => {
        this.$modal.msgSuccess("课表已调整")
        this.loadSchedule()
      }).catch(err => {
        this.$modal.msgError("排课冲突：" + err.msg)
        this.loadSchedule()
      })
    },
    /** 新增 */
    handleAdd() {
      this.form = {clazzId: this.selectedClazzId}
      this.title = "新增排课"
      this.open = true
    },
    /** 提交 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) return
        let payload = {
          ...this.form,
          clazzId: this.selectedClazzId,
          packageId: this.form.packageId, // ⭐ 传课程包ID
          startTime: this.form.section,
          endTime: this.sections.find(s => s.start === this.form.section).end,
          startWeek: this.selectedWeekNo,
          endWeek: this.selectedWeekNo
        }
        if (this.form.scheduleId) {
          updateSchedule(payload).then(() => {
            this.$modal.msgSuccess("修改成功")
            this.open = false
            this.loadSchedule()
          })
        } else {
          addSchedule(payload).then(() => {
            this.$modal.msgSuccess("新增成功")
            this.open = false
            this.loadSchedule()
          })
        }
      })
    },

    cancel() {
      this.open = false
    },

    loadCourseOptions() {
      listCourse({pageNum: 1, pageSize: 9999}).then(res => {
        this.courseOptions = res.rows.map(c => ({
          label: c.courseName,
          value: c.courseId
        }))
      })
    },
    loadTeacherOptions() {
      listTeacher({pageNum: 1, pageSize: 9999}).then(res => {
        this.teacherOptions = res.rows.map(t => ({
          label: t.teacherName,
          value: t.teacherId
        }))
      })
    },
    loadPackageOptions(courseId) {
      if (!courseId) {
        this.packageOptions = []
        return
      }
      listPackageByCourse(courseId).then(res => {
        console.log("课程包返回：", res.data)  // 确认字段名
        this.packageOptions = res.data.map(p => ({
          label: p.packageName,  // 显示课程包名
          value: p.id            // 存储id
        }))
      })
    },
    loadSchedule() {
      listSchedule({
        clazzId: this.selectedClazzId,
        weekNo: this.selectedWeekNo
      }).then(res => {
        this.schedules = res.rows
        console.log(this.schedules)
      })
    },
    changeWeek(step) {
      let newWeek = this.selectedWeekNo + step
      if (newWeek < 1) {
        this.$modal.msgWarning("已经是第一周了")
        return
      }
      if (newWeek > this.weekOptions.length) {
        this.$modal.msgWarning("已经是最后一周了")
        return
      }
      this.selectedWeekNo = newWeek
      this.loadSchedule()
    }
  }
}
</script>

<style>
.drag-area {
  min-height: 60px;
}

.drag-item {
  background: #f0f9eb;
  border: 1px solid #d9ecff;
  border-radius: 4px;
  padding: 4px;
  margin: 2px 0;
  cursor: move;
  text-align: center;
}

.drag-item:hover {
  background: #d9ecff;
}

.drag-item {
  margin: 4px 0;
}

.course-card {
  background: #ecf5ff;
  border: 1px solid #b3d8ff;
  border-radius: 6px;
  padding: 6px;
  text-align: center;
  font-size: 13px;
  cursor: pointer;
  transition: all 0.2s;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.course-card:hover {
  background: #409EFF;
  color: #fff;
  transform: scale(1.03);
}

.course-name {
  font-weight: bold;
  font-size: 14px;
  margin-bottom: 4px;
}

.package, .teacher, .week-range {
  font-size: 12px;
  color: #555;
  margin: 2px 0;
}

</style>
