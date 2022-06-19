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
      Administrator
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
          Personal center
          <el-icon class="el-icon--right">
            <arrow-down />
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>Personal information</el-dropdown-item>
            <el-dropdown-item>Change password</el-dropdown-item>
            <!-- <el-dropdown-item>Action 3</el-dropdown-item>
            <el-dropdown-item disabled>Action 4</el-dropdown-item>
            <el-dropdown-item divided>Action 5</el-dropdown-item> -->
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>





<script>
export default {
  name: "Header",
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
