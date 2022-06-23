<template>
  <div
    style="
      height: 50px;
      line-height: 50px;
      border-bottom: 4px solid #ccc;
      display: flex;
      background-color: #545c64;
    "
  >
    <div
      style="width: 200px; padding-left: 30px; font-weight: bold; color: #fff"
    >
      管理员
    </div>
    <div style="width: 835px;"> </div>

    <div style="flex: 1; clear: both">
      <el-dropdown>
        <span
          class="el-dropdown-link"
          style="
            padding-top: 17px;
            font-weight: bold;
            color: #fff;
          "
        >
          个人中心
          <el-icon class="el-icon--right">
            <arrow-down />
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="show=true">个人信息</el-dropdown-item>
            <el-dropdown-item>退出系统</el-dropdown-item>
            <!-- <el-dropdown-item>Action 3</el-dropdown-item>
            <el-dropdown-item disabled>Action 4</el-dropdown-item>
            <el-dropdown-item divided>Action 5</el-dropdown-item> -->
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>


    <div>
    <el-dialog title="详细信息" v-model="show" width="40%">
      <el-descriptions :column="1">
        <el-descriptions-item label="用户名">{{
          "admin"
        }}</el-descriptions-item>
        <el-descriptions-item label="密码">{{
          "admin"
        }}</el-descriptions-item>
        <el-descriptions-item label="编号：">{{
          "1"
        }}</el-descriptions-item>
        <el-descriptions-item label="联系方式：">{{
          "12345678"
        }}</el-descriptions-item>
        </el-descriptions>
    </el-dialog>
  </div>
</template>





<script>
export default {
  name: "Header",
   data() {
    return {
      show: false,
    }
  },
};
</script>

<style scoped>
.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
</style>
