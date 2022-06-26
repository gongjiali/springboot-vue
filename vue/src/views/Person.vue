<template>
  <div style="width:400px;margin: auto">
    <el-card style="width: 100%; margin: 10px">
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="用户名">
          <el-input v-model="form.name" disabled></el-input>
        </el-form-item>
        <el-form-item label="角色">
            <span v-if="form.role===1" disabled>投稿人</span>
            <span v-if="form.role===2" disabled>审稿人</span>
            <span v-if="form.role===3" disabled>管理员</span>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickName"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio v-model="form.sex" :label="1">男</el-radio>
          <el-radio v-model="form.sex" :label="2">女</el-radio>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="form.age"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address"></el-input>
        </el-form-item>


      </el-form>
      <div style="text-align: center">
        <el-button type="primary" @click="update">保存</el-button>
      </div>
    </el-card>


  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Person",
  data() {
    return {
      form: {},
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
  created() {
    let str = sessionStorage.getItem("user") || "{}"
    this.form = JSON.parse(str)
    // JSON 通常用于与服务端交换数据。
    // 在接收服务器数据时一般是字符串。
    // 我们可以使用 JSON.parse() 方法将数据转换为 JavaScript 对象。
  },
  methods: {
    update() {
      request.put("/user", this.form).then(res => {
        console.log(res)
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "更新成功"
          })
          sessionStorage.setItem("user", JSON.stringify(this.form))
          // 触发Layout更新用户信息
          this.$emit("userInfo")
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    },
    delete(){

    }
  }
}
</script>

