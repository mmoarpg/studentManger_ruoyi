<template>
  <div class="app-container">
    <el-card shadow="hover">
      <div slot="header" class="card-header">
        <span>课程表</span>
      </div>

      <el-table :data="sections" border style="width: 100%">
        <!-- 节次 -->
        <el-table-column prop="name" label="节次" width="120" align="center">
          <template slot-scope="scope">
            <div class="section-cell">
              <div>{{ scope.row.name }}</div>
              <small>{{ scope.row.start }} ~ {{ scope.row.end }}</small>
            </div>
          </template>
        </el-table-column>

        <!-- 周一到周日 -->
        <el-table-column
          v-for="(day, idx) in weekDays"
          :key="idx"
          :label="day"
          align="center"
          min-width="180"
        >
          <template slot-scope="scope">
            <div class="course-cell">
              <div
                v-for="c in getCourses(scope.row, idx+1)"
                :key="c.scheduleId"
                class="course-card"
                @click="openCourse(c)"
                :style="{ backgroundColor: getColor(c.courseId) }"
              >
                <div class="course-name">{{ c.courseName }}</div>
                <div class="package">课程包：{{ c.packageName || '-' }}</div>
                <div class="teacher">教师：{{ c.teacherName }}</div>
                <div class="week-range">第{{ c.startWeek }}-{{ c.endWeek }}周</div>
              </div>


              <!-- 空单元格：新增 -->
              <div
                v-if="!getCourses(scope.row, idx+1).length"
                class="empty-cell"
                @click="addCourse(idx+1, scope.row)"
              >
                <i class="el-icon-plus"></i> 添加
              </div>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 课程弹窗同前，略 -->
  </div>
</template>

<script>
import {listSchedule, addSchedule, updateSchedule} from "@/api/system/schedule"
import {listCourse} from "@/api/system/course"
import {listTeacher} from "@/api/system/teacher"

export default {
  name: "ScheduleTable",
  data() {
    return {
      weekDays: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
      sections: [
        {name: "第1节", start: "08:00", end: "09:40"},
        {name: "第2节", start: "10:00", end: "11:40"},
        {name: "第3节", start: "14:00", end: "15:40"},
        {name: "第4节", start: "16:00", end: "17:40"}
      ],
      schedules: [],

      // 下拉数据
      courseOptions: [],
      teacherOptions: [],

      // 弹窗
      open: false,
      title: "",
      form: {},
      rules: {
        courseId: [{required: true, message: "课程不能为空", trigger: "change"}],
        teacherId: [{required: true, message: "教师不能为空", trigger: "change"}],
        weekDay: [{required: true, message: "周几不能为空", trigger: "change"}],
        section: [{required: true, message: "节次不能为空", trigger: "change"}]
      }
    }
  },
  created() {
    this.loadSchedules()
    this.loadCourseOptions()
    this.loadTeacherOptions()
  },
  methods: {
    loadSchedules() {
      listSchedule({weekNo: this.selectedWeekNo}).then(res => {
        this.schedules = res.rows
      })
    },
    getCourses(section, weekDay) {
      return this.schedules.filter(s =>
        s.weekDay === weekDay && s.startTime === section.start
      )
    },
    openCourse(course) {
      this.form = {...course, section: course.startTime}
      this.title = "编辑课程"
      this.open = true
    },
    addCourse(weekDay, section) {
      this.form = {weekDay, section: section.start}
      this.title = "新增课程"
      this.open = true
    },
    submitForm() { /* 同之前，不变 */
    },
    cancel() {
      this.open = false
    },

    loadCourseOptions() { /* 同之前 */
    },
    loadTeacherOptions() { /* 同之前 */
    },

    /** 随机颜色（按课程固定） */
    getColor(courseId) {
      const colors = ["#ecf5ff", "#f0f9eb", "#fdf6ec", "#fef0f0", "#f5f7fa"]
      return colors[courseId % colors.length]
    }
  }
}
</script>

<style scoped>
.card-header {
  font-size: 18px;
  font-weight: bold;
  color: #409EFF;
}

.section-cell {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.course-cell {
  min-height: 90px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.course-card {
  border-radius: 8px;
  padding: 8px;
  margin: 4px 0;
  width: 95%;
  text-align: center;
  font-size: 13px;
  cursor: pointer;
  transition: all 0.2s;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

.course-card:hover {
  transform: scale(1.05);
}

.course-name {
  font-weight: bold;
  margin-bottom: 4px;
}

.teacher, .week-range {
  font-size: 12px;
  color: #666;
}

.empty-cell {
  color: #999;
  font-size: 14px;
  cursor: pointer;
  width: 100%;
  height: 60px;
  line-height: 60px;
  border: 1px dashed #ccc;
  border-radius: 6px;
  transition: all 0.2s;
}

.empty-cell:hover {
  background: #f5f7fa;
  border-color: #409EFF;
  color: #409EFF;
}

.package {
  font-size: 12px;
  color: #444;
  margin-bottom: 4px;
}

</style>
