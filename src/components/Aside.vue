<template>
  <el-row class="tac" style="width: 150px; display: flex">
    <el-col :span="12">
      <h5 class="mb-2"></h5>
      <el-menu
        active-text-color="#ffd04b"
        background-color="#545c64"
        class="el-menu-vertical-demo"
        default-active="1"
        text-color="#fff"
        router
        @open="handleOpen"
        @close="handleClose"
        style="width: 150px; min-height: calc(100vh-50px)"
      >
        <el-menu-item index="author">
          <el-icon><icon-menu /></el-icon>
          <span>Authors</span>
        </el-menu-item>
        <el-menu-item index="reviewer">
          <el-icon><icon-menu /></el-icon>
          <span>Reviewers</span>
        </el-menu-item>

        <el-menu-item-group title="Articles">
          <el-menu-item index="article">assigned article</el-menu-item>
          <el-menu-item index="un_article">unassigned article</el-menu-item>
        </el-menu-item-group>
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
