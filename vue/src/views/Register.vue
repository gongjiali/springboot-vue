<template>
  <div style="width: 100%; height: 100vh; overflow: hidden">
    <div style="width: 400px; margin: 50px auto">
      <div style="font-size: 30px; text-align: center; padding: 10px 0">欢迎注册</div>
      <el-form ref="form" :model="form" size="normal" :rules="rules" label-width="80px">
        <el-form-item label="用户名" prop="name">
          <el-input prefix-icon="Avatar" v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input prefix-icon="Lock" v-model="form.password" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirm">
          <el-input prefix-icon="Lock" v-model="form.confirm" show-password></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="nickname">
          <el-input prefix-icon="Avatar" v-model="form.nickname"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio v-model="form.sex" :label="1">男</el-radio>
          <el-radio v-model="form.sex" :label="2">女</el-radio>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input prefix-icon="Avatar" v-model="form.age"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input prefix-icon="Avatar" v-model="form.email"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phoneNumber">
          <el-input prefix-icon="Avatar" v-model="form.phoneNumber"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input prefix-icon="Avatar" v-model="form.address"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="register">注册</el-button>
        </el-form-item>
        <el-form-item><el-button type="text" @click="$router.push('/login')">&lt;&lt;返回登录 </el-button></el-form-item>
      </el-form>
    </div>
  </div>
</template>


<script setup >
import { reactive } from 'vue'
import { Avatar,Lock } from '@element-plus/icons-vue'
</script>

<script>
import request from "@/utils/request";

export default {
  name: "Register",
  data() {
    return {
      form: {
        role:1
      },

      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
        confirm: [
          {required: true, message: '请确认密码', trigger: 'blur'},
        ],
        sex:[
          {required: true, message: '选择不能为空', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    register() {
      if (this.form.password !== this.form.confirm) {
        this.$message({
          type: "error",
          message: '2次密码输入不一致！'
        })
        return
      }

      this.$refs['form'].validate((valid) => {
        if (valid) {
          request.post("/user/register", this.form).then(res => {
            if (res.code === '0') {
              this.$message({
                type: "success",
                message: "注册成功"
              })
              this.$router.push("/login")  //登录成功之后进行页面的跳转，跳转到主页
            } else {
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
