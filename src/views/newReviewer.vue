<template>
  <div style="width: 100%; height: 100vh; overflow: hidden">
    <div style="width: 400px; margin: 100px auto">
      <div style="font-size: 30px; text-align: center; padding: 30px 0">新增审稿人</div>
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
        <el-form-item label="昵称" prop="nickName">
          <el-input prefix-icon="Avatar" v-model="form.nickName"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio v-model="form.sex" label="男">男</el-radio>
          <el-radio v-model="form.sex" label="女">女</el-radio>
          <el-radio v-model="form.sex" label="未知">未知</el-radio>
        </el-form-item>
        <el-form-item label="类别" prop="category">
          <el-input prefix-icon="Avatar" v-model="form.category"></el-input>
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
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="register">注册</el-button>
        </el-form-item>
        <el-form-item><el-button type="text" @click="$router.push('/reviewer')">&lt;&lt;返回 </el-button></el-form-item>
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
  name: "newReviewer",
  data() {
    return {
      form: {},
      rules: {
        name: [
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
        ],
        category:[
          {required: true, message: '请输入类别', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    register() {
      console.log(this.form);
      if (this.form.password !== this.form.confirm) {
        this.$message({
          type: "error",
          message: '2次密码输入不一致！'
        })
        return
      }

      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.sex==='女'){
            this.form.sex=2;
          }else if(this.form.sex==='男'){
            this.form.sex=1;
          }else{
            this.form.sex=3;
          }
          request.post("/specialist/register", this.form).then(res => {
            if (res.code === '0') {
              this.$message({
                type: "success",
                message: "注册成功"
              })
              this.$router.push("/reviewer")  //登录成功之后进行页面的跳转，跳转到主页
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
