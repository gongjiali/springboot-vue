<template>
  <div
      style="
      height: 50px;
      line-height: 50px;
      border-bottom: 3px solid #ccc;
      display: flex;
      background-color: rgb(0,126,163);
    "
  >
    <div style="color: azure;font-size:20px;padding-left: 20px">
      投稿系统
    </div>
    <div style="flex: 1"></div>
    <div style="width: 120px ">
      <el-dropdown>
        <span
            class="el-dropdown-link"
            style="padding-top: 17px; font-weight: bold; color: #fff"
        >
          个人中心
          <el-icon class="el-icon--right">
            <arrow-down />
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
<!--            <el-dropdown-item @click="show = true">个人信息</el-dropdown-item>-->
            <el-dropdown-item @click="$router.push('/person')">个人信息</el-dropdown-item>
            <el-dropdown-item @click="$router.push('/password')">修改密码</el-dropdown-item>
            <el-dropdown-item @click="$router.push('/Login')">退出登录</el-dropdown-item>
            <el-dropdown-item @click="dialogVisible = true">注销账户</el-dropdown-item>
<!--            这里需要注意路由-->
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
  <div>
<!--    <el-dialog title="详细信息" v-model="show" width="40%">-->
<!--      <el-descriptions :column="1">-->
<!--        <el-descriptions-item label="用户名">{{-->
<!--            user.name-->
<!--          }}</el-descriptions-item>-->
<!--        <el-descriptions-item label="密码">{{-->
<!--            user.password-->
<!--          }}</el-descriptions-item>-->
<!--        <el-descriptions-item label="编号：">{{-->
<!--            user.userId-->
<!--          }}</el-descriptions-item>-->
<!--        <el-descriptions-item label="昵称：">{{-->
<!--            user.nickName-->
<!--          }}</el-descriptions-item>-->
<!--        <el-descriptions-item label="电话：">{{-->
<!--            user.phoneNumber-->
<!--          }}</el-descriptions-item>-->
<!--        <el-descriptions-item label="住址：">{{-->
<!--            user.address-->
<!--          }}</el-descriptions-item>-->
<!--      </el-descriptions>-->
<!--    </el-dialog>-->
    <el-dialog v-model="dialogVisible" width="40%" >
   <span>
     是否要注销账户？
   </span>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="deleteacc">注销</el-button>
      </span>
      </template>
    </el-dialog>

  </div>
</template>



<script>
import request from "@/utils/request";

export default {
  name: "Header",
  data() {
    return {
      dialogVisible: false,
      user: {},
      show: false,
    };
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}";
    this.user = JSON.parse(userStr);
  },
  methods: {
    deleteacc() {
      let user = JSON.parse(sessionStorage.getItem("user"))
      request.post("/user/deleteacc",user).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "注销成功"
          })
          this.$router.push("/login")
        }
      })
    }
  }
};
</script>

<style>
body{
  margin:0;
  padding:0;
}
</style>