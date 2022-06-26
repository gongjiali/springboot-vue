<template>
  <el-row class="tac" >
    <el-col :span="12">
      <h5 class="mb-2"></h5>
      <el-menu
        active-text-color="#ffd04b"
        background-color="#545c64"
        class="el-menu-vertical-demo"
        default-active="user"
        router
        text-color="#fff"
        @open="handleOpen"
        @close="handleClose"
        style="width: 150px; min-height: calc(100vh - 50px)"
      >
        <el-menu-item index="user" v-if="user.role === 1">
          <el-icon><icon-menu /></el-icon>
          <span>投稿人</span>
        </el-menu-item>
        <el-menu-item index="review" v-if="user.role === 2">
          <el-icon><icon-menu /></el-icon>
          <span>审稿人</span>
        </el-menu-item>
        <el-menu-item v-if="user.role === 3">
          <el-icon><icon-menu /></el-icon>
          <span>管理员</span>
        </el-menu-item>
        <el-menu-item-group title="角色" v-if="user.role === 3">
          <el-menu-item index="author">投稿人</el-menu-item>
          <el-menu-item index="reviewer">审稿人</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="稿件" v-if="user.role === 3">
          <el-menu-item index="article">已分配稿件</el-menu-item>
          <el-menu-item index="un_article">未分配稿件</el-menu-item>
        </el-menu-item-group>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "Aside",
  data() {
    return {
      user: {},
    };
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}";
    this.user = JSON.parse(userStr);
  },
};
</script>

<style>
body{
  margin:0;
  padding:0;
}
</style>