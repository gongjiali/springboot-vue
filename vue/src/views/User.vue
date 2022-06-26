<template>
  <div style="padding: 10px">
    <div style="margin: 10px 0" @click="ttSubmitPaper">
      <el-button type="primary">投稿</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 30%"></el-input>
      <el-button type="primary" style="margin-left: 5px">查询</el-button>
    </div>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="paperId" label="稿件id" width="100" sortable/>
      <el-table-column prop="title" label="稿件标题" width="150" sortable/>
      <el-table-column prop="author" label="作者" sortable/>
      <el-table-column prop="category" label="类别" width="150" sortable/>
      <el-table-column prop="status1" label="稿件状态" :formatter="Fstatus1" sortable/>
      <el-table-column fixed="right" label="操作" width="230">
        <template #default="scope">
          <el-button type="text" @click="ttPaper(scope.row)" >详细信息</el-button>
          <el-button type="text" @click="ttUpdate(scope.row)" >更新稿件</el-button>
          <el-popconfirm title="确定撤销稿件吗？" @confirm="ttDelete(scope.row.paperId)">
            <template #reference>
              <el-button type="text">撤消稿件</el-button>
            </template>
          </el-popconfirm>

        </template>
      </el-table-column>

    </el-table>
    <div>
      <el-pagination style="margin: 10px 0"
                     v-model:currentPage="currentPage"
                     v-model:page-size="pageSize"
                     :page-sizes="[5, 10, 20]"
                     :small="false"
                     :disabled="false"
                     :background="false"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="total"
                     @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
      />


    </div>
    <el-dialog title="论文详细信息" v-model="show" width="40%">
      <el-descriptions :column="1">
        <el-descriptions-item label="稿件编号：">{{form.paperId}}</el-descriptions-item>
        <el-descriptions-item label="稿件标题：">{{form.title}}</el-descriptions-item>
        <el-descriptions-item label="作者：">{{form.author}}</el-descriptions-item>
        <el-descriptions-item label="类别：">{{form.category}}</el-descriptions-item>
        <el-descriptions-item label="稿件摘要：">{{form.content}}</el-descriptions-item>
        <el-descriptions-item label="提交或更新时间：">{{form.submitDate}}</el-descriptions-item>
      </el-descriptions>
      <el-button-group style="margin-bottom: 15px; width: 100%; ">
        <el-button type="info" plain @click="ttDownload(form.filePath)">下载</el-button>
      </el-button-group>
    </el-dialog>

    <el-dialog title="更新稿件" v-model="show1" width="50%">
      <el-form :rules="rules" label-width="80px" :model="paper" ref="ruleForm" style="width:80%;margin: 3.4% auto">
        <el-form-item label="上传论文" prop="paperfile">
          <el-upload action="http://localhost:9090/files/upload" :on-success="filesUploadSuccess">
            <el-button type="primary">重新上传文件</el-button>
            <template #tip>
              <div class="el-upload__tip" slot="tip">注意！只能上传一份doc文件(20M以内)，选取文件后点击上传</div>
            </template>
          </el-upload>
        </el-form-item>
        <el-form-item label="论文标题" prop="name">
          <el-input v-model="form.title"></el-input>
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="form.author"></el-input>
        </el-form-item>
        <el-form-item label="分类" prop="category">
          <el-select v-model="form.category" class="m-2" placeholder="请选择论文类别">
            <el-option
                v-for="item in categoryList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="摘要" prop="content">
          <el-input :rows="6" type="textarea" v-model="form.content"></el-input>
        </el-form-item>
        <el-form-item label="其他说明">
          <el-input :rows="2" type="textarea" v-model="form.note"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click=save>提交</el-button>
          <el-button @click="cancel">取消</el-button>
        </el-form-item>
      </el-form>

    </el-dialog>

  </div>

</template>

<script>
import request from "@/utils/request";
import Pagination from "@/components/Pagination";
import axios from 'axios';

export default {
  name: "User",
  components: {

  },
  data() {
    return {
      show: false,
      show1: false,
      search: '',
      currentPage: 1,
      pageSize: 10,
      total: 0,
      tableData: [],
      form: {

      },
    }
  },
  created() {
    this.load();
  },
  methods: {
    Fstatus1 :function (row){
      if(row.status1 === 0){
        return "未审核"
      } else if(row.status1 === 1){
        return "审核通过"
      }else if(row.status1 === 2){
        return "审核未通过"
      }
    },
    load(){
      let user = JSON.parse(sessionStorage.getItem("user"))
      this.form.userId = user.userId
      request.get("/paper/userPaper", {
        params:{
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.form.userId,
        }}).then(res => {
        console.log(res);
        this.tableData = res.data.records;
        this.total = res.data.total;
      })
    },
    save(){
      let year = new Date().getFullYear();
      let month = new Date().getMonth();
      let day = new Date().getDay();
      let hour = new Date().getHours()<10 ? '0'+new Date().getHours() : new Date().getHours();
      let minute = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
      let second = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
      this.form.submitDate = year + '-' + month + '-' + day + " " + hour + ":" + minute + ":" + second;
      let user = JSON.parse(sessionStorage.getItem("user"))
      this.form.userId = user.userId
      if(this.form.paperId){
        request.put("/paper",this.form).then(res =>{
          console.log(res);
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
          this.load();
          this.show1 = false;

        })
      }else {
        request.post("/paper", this.form).then(res => {
          console.log(res);
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
          this.load();
          this.show1 = false;
        })
      }

    },
    handleClick() {

    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.load();

    },
    handleCurrentChange(pageNum) {
      this.currentPage = pageNum;
      this.load();
    },
    ttSubmitPaper(){
      this.$router.replace("/SubmitPaper")
    },
    ttPaper(row){
      this.show = true;
      this.form = JSON.parse(JSON.stringify(row));
    },
    ttDownload(filePath){
      window.open(filePath,"_self");
    },
    ttUpdate(row){
      this.form = JSON.parse(JSON.stringify(row));
      this.show1 = true;

    },
    ttDelete(paperId){
      console.log(paperId);
      request.delete("/paper/"+ paperId).then(res =>{
        if(res.code === '0'){
          this.$message({
            type: "success",
            message: "删除成功"
          })
        }else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load();
      })
    },
    cancel(){
      let that = this;
      this.$confirm('是否放弃提交?', '取消', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.load();
        this.show1 = false;
        this.show = false;
      });
    },
  }
}
</script>

<style scoped>

</style>