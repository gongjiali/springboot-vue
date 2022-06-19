<template>
  <el-row class="tac" style="width:150px; display:flex">
    <el-col :span="12">
      <h5 class="mb-2"></h5>
      <el-menu
        active-text-color="#ffd04b"
        background-color="#545c64"
        class="el-menu-vertical-demo"
        default-active="1"
        text-color="#fff"
        @open="handleOpen"
        @close="handleClose"
        style ="width: 150px; "
      >
        <el-menu-item index="1">
          <el-icon><icon-menu /></el-icon>
          <span>Authors</span>
        </el-menu-item>
        <el-menu-item index="2">
          <el-icon><icon-menu /></el-icon>
          <span>Reviewers</span>
        </el-menu-item>
        <el-menu-item index="3">
          <el-icon><document /></el-icon>
          <span>Articles</span>
        </el-menu-item>

      </el-menu>
    </el-col>
  </el-row>
</template>





<script>
export default {
  name: "Aside",
};
</script>

<style scoped>
</style>
