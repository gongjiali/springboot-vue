<template>
  <div style="padding: 10px">
    <el-card>
      <el-form :rules="rules" label-width="180px" :model="paper" ref="ruleForm" style="width:80%;margin: 3.4% auto">
        <el-form-item label="上传论文" prop="paperfile">
          <el-upload action="http://localhost:9090/files/upload" :on-success="filesUploadSuccess">
            <el-button type="primary">上传文件</el-button>
            <template #tip>
              <div class="el-upload__tip" slot="tip">注意！只能上传一份doc文件(20M以内)，选取文件后点击上传</div>
            </template>
          </el-upload>
        </el-form-item>
        <el-form-item label="论文标题" prop="name">
          <el-input v-model="paper.title"></el-input>
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="paper.author"></el-input>
        </el-form-item>
        <el-form-item label="分类" prop="category">
          <el-select v-model="paper.category" class="m-2" placeholder="请选择论文类别">
            <el-option
                v-for="item in categoryList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="摘要" prop="content">
          <el-input :rows="6" type="textarea" v-model="paper.content"></el-input>
        </el-form-item>
        <el-form-item label="其他说明">
          <el-input :rows="2" type="textarea" v-model="paper.note"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button v-if="!isUpdate" type="primary" @click.prevent="submit('ruleForm')">提交</el-button>
          <el-button @click="cancel">取消</el-button>
        </el-form-item>
      </el-form>

    </el-card>
  </div>
</template>

<script>

import request from "@/utils/request";
export default {
  name: "SubmitPaper",
  components: {

  },
  data(){
    return{
      isUpdate: false,
      paper:{
        id: '',
        filePath: '',
        keyword: '',
        name: '',
        note:'',
        summary: '',
        author: ''
      },
      categoryList:[
        {
          value: '心理',
          label: '心理',
        },
        {
          value: '软件工程',
          label: '软件工程',
        },
        {
          value: '中文',
          label: '中文',
        },

      ],
      rules: {
        title: [
          {required: true, message: '请输入论文标题', trigger: 'blur'}
        ],
        category: [
          {required: true, message: '请输入类别', trigger: 'blur'}
        ],
        summary: [
          {required: true, message: '请输入摘要', trigger: 'blur'}
        ],
        author: [
          {required: true, message: '请输入作者', trigger: 'blur'}
        ]
      }
    }
  },

  methods:{

    handleExceed(){

    },

    filesUploadSuccess(res){
      console.log(res);
      this.paper.filePath = res.data;
    },
    submit(){
      let that = this;
      if (!that.paper.filePath){
        that.$message.error("请上传论文");
        return;
      }
      let year = new Date().getFullYear();
      let month = new Date().getMonth();
      let day = new Date().getDay();
      let hour = new Date().getHours()<10 ? '0'+new Date().getHours() : new Date().getHours();
      let minute = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
      let second = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
      this.paper.submitDate = year + '-' + month + '-' + day + " " + hour + ":" + minute + ":" + second;
      let user = JSON.parse(sessionStorage.getItem("user"))
      this.paper.userId = user.userId
      request.post("/paper",this.paper).then(res =>{
        console.log(res)
        if(res.code === '0'){
          this.$message({
            type: "success",
            message: "提交成功"
          })
        }else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
      this.$router.push("/User")

    },
    update(){
      this.$router.push("/User")

    },
    cancel(){
      let that = this;
      this.$confirm('是否放弃提交?', '取消', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        that.$router.push('/User');
      });

    },
    beforePaperUpload(){

    },

  }

}
</script>

<style scoped>


</style>