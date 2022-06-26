<template>
  <div style="padding: 10px">
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 30%"></el-input>
      <el-button type="primary" style="margin-left: 5px">查询</el-button>
    </div>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="paperId" label="稿件id" width="100" sortable/>
      <el-table-column prop="title" label="稿件标题" width="150" sortable/>
      <el-table-column prop="date" label="更新时间" sortable/>
      <el-table-column prop="status1" label="稿件状态" :formatter="Fstatus1" sortable/>

      <el-table-column fixed="right" label="操作" width="200">
        <template #default = "scope">
          <el-button link type="primary" size="small" @click="ttPaper(scope.row)">详细信息</el-button>
          <el-button link type="primary" size="small" @click="ttClick(scope.row)">论文审核</el-button>
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
        <el-descriptions-item label="稿件状态：">{{form.status1}}</el-descriptions-item>
        <el-descriptions-item label="提交或更新时间：">{{form.submitDate}}</el-descriptions-item>
      </el-descriptions>
      <el-button-group style="margin-bottom: 15px; width: 100%; ">
        <el-button type="info" plain @click="ttDownload(form.filePath)">下载</el-button>
      </el-button-group>
    </el-dialog>

    <el-dialog title="论文审核" v-model="show1" width="40%">
      <el-form-item label="审核">
        <el-radio v-model="form.status1" label=1>通过</el-radio>
        <el-radio v-model="form.status1" label=2>拒绝</el-radio>
      </el-form-item>
      <el-form-item label="回复" >
        <el-input :rows="6" type="textarea" v-model="form.review"></el-input>
      </el-form-item>
      <el-button-group style="margin-bottom: 15px; width: 100%; ">
        <el-button type="info" plain @click="save">提交</el-button>
        <el-button type="info" plain @click="cancel">取消</el-button>
      </el-button-group>
    </el-dialog>
  </div>

</template>

<script>
import request from "@/utils/request";

export default {
  name: "Specialist",
  components: {

  },
  data() {
    return {
      show: false,
      show1: false,
      search: '',
      currentPage: 1,
      pageSize: 10,
      total: 100,
      tableData: [],
      form: {

      }
    }
  },
  created() {
    this.load()
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
      request.get("/paper/reviewPaper", {
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
      this.form.checkDate = year + '-' + month + '-' + day + " " + hour + ":" + minute + ":" + second;
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
    handleSizeChange() {

    },
    handleCurrentChange() {

    },
    ttClick(row){
      this.show1 = true;
      this.form = JSON.parse(JSON.stringify(row));

    },
    ttPaper(row){
      this.show = true;
      this.form = JSON.parse(JSON.stringify(row));
    },
    ttDownload(filePath){
      window.open(filePath,"_self");
    },
    cancel(){
      let that = this;
      this.$confirm('是否放弃提交?', '取消', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.show1 = false;
      });

    },

  }
}

</script>

<style scoped>

</style>