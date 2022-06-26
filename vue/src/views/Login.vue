<template>
  <div style="width:100%;height:100vh;overflow:hidden">
    <div style="height: 60px; weight: 1360px;line-height:50px;background-color: rgb(0,126,163)">
      <div style="color: azure;font-size:20px;padding-left: 20px">论文系统</div>
    </div>

    <div class="kuang">
      <div style="color:azure;font-size: 30px;text-align: center;padding: 30px;background-color: rgb(0,126,163);padding: 10px">欢迎登录论文系统</div>
      <el-form style="padding: 30px;" ref="form" :model="form" size="normal" :rules="rules">
        <el-form-item prop="username">
          <el-input :prefix-icon="User" v-model="form.name" placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input :prefix-icon="Lock" v-model="form.password" show-password placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-select v-model="form.role" placeholder="请选择身份" style="width:100%" @choose="rolechoose()">
            <el-option  v-for="item in roleList" :key="item.value" :label="item.label"
                        :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <div style="display: flex">
            <el-input prefix-icon="Key" v-model="form.validCode" style="width: 50%;" placeholder="请输入验证码"></el-input>
            <ValidCode @input="createValidCode" />
          </div>
        </el-form-item>
        <el-form-item>
          <el-button style="color:azure;width: 100%; background-color: rgb(0,126,163)" @click='login'>Log in</el-button>
        </el-form-item>
        <el-form-item><el-button type="text" @click="$router.push('/register')">前往注册 >> </el-button></el-form-item>
      </el-form>
    </div>
  </div>
</template>


<script setup >
import { reactive } from 'vue'
import { Avatar,Lock,User,Key } from '@element-plus/icons-vue'
</script>

<script>
import request from "@/utils/request";
import ValidCode from "@/components/ValidCode";


export default {
  name: "Login",
  components: {
    ValidCode,
  },
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
        role: [
          {required: true, message: '请选择身份', trigger: 'blur'},
        ]
      },
      validCode: '',
      roleList:[
        {
          value: '1',
          label: '投稿人',
        },
        {
          value: '2',
          label: '审稿人',
        },
        {
          value: '3',
          label: '管理员',
        },
      ]
    }
  },
  mounted() {
    // mounted是vue中的一个钩子函数，一般在初始化页面完成后，再对dom节点进行相关操作
    sessionStorage.removeItem("user")

    window.onresize = () => {
      const windowWidth = document.body.clientWidth
      const windowHeight = document.body.clientHeight
      const windowAspectRatio = windowHeight / windowWidth
      let videoWidth
      let videoHeight
      if (windowAspectRatio < 0.5625) {
        videoWidth = windowWidth
        videoHeight = videoWidth * 0.5625
        this.fixStyle = {
          height: windowWidth * 0.5625 + 'px',
          width: windowWidth + 'px',
          'margin-bottom': (windowHeight - videoHeight) / 2 + 'px',
          'margin-left': 'initial'
        }
      } else {
        videoHeight = windowHeight
        videoWidth = videoHeight / 0.5625
        this.fixStyle = {
          height: windowHeight + 'px',
          width: windowHeight / 0.5625 + 'px',
          'margin-left': (windowWidth - videoWidth) / 2 + 'px',
          'margin-bottom': 'initial'
        }
      }
    }
    window.onresize()
  },
  methods: {
    // 接收验证码组件提交的 4位验证码
    createValidCode(data) {
      this.validCode = data
    },
    login() {
      this.$refs['form'].validate((valid)=>{
        if(valid){
          if (!this.form.validCode) {
            this.$message.error("请填写验证码")
            return
          }
          if(this.form.validCode.toLowerCase() !== this.validCode.toLowerCase()) {
            this.$message.error("验证码错误")
            return
          }
          request.post("/user/login",this.form).then(res => {
            if (res.code === '0') {
              this.$message({
                type: "success",
                message: "登录成功"
              })
              sessionStorage.setItem("user", JSON.stringify(res.data))  // 缓存用户信息
              // 登录成功的时候更新当前路由
              // activeRouter()
              if (this.form.role==='1'){
                this.$router.push("/user")
              }else if(this.form.role==='2'){
                this.$router.push("/review")
              }else{
                this.$router.push("/author")
              }

            } else {
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          })
        }
      })
    },
    rolechoose(obj){
      this.form.role=obj.value;
    }
  }
}
</script>

<style>
body{
  margin:0;
  padding:0;
}
.kuang{
  left: 50%;
  top: 50%;
  width: 400px;
  margin: 150px auto;
  border:1px solid #000;
  border-radius: 15px;/*角边弧度*/
  overflow: hidden;

}
</style>

