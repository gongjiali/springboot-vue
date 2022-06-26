<template>
  <div>
    <div>
      <el-input
        v-model="search"
        placeholder="请输入"
        clearable
        style="margin-left: 10px; padding: 10px; width: 30%"
      />
      <el-button type="primary" @click="gosearch">搜索</el-button>
    </div>
    <div style="margin-left: 20px; margin-top: 10px">
      <el-table
        :data="tableData"
        :default-sort="{ prop: 'userId', order: 'ascending' }"
        style="width: 90%"
      >
        <el-table-column prop="userId" label="ID" sortable />
        <el-table-column prop="nickName" label="昵称" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="email" label="E-mail" />
        <el-table-column>
          <template #default="scope">
            <el-button
              type="primary"
              @click="
                form = scope.row;
                show = true;
              "
              >详情</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div
      class="demo-pagination-block"
      style="margin-left: 10px; padding: 10px 10px"
    >
      <div class="demonstration"></div>
      <el-pagination
        v-model:currentPage="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[5, 10, 20, 30]"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
    <div>
      <el-dialog title="投稿人详细信息" v-model="show" width="30%">
        <el-descriptions :column="1">
          <el-descriptions-item label="编号：">{{
            form.userId
          }}</el-descriptions-item>
          <el-descriptions-item label="姓名：">{{
            form.name
          }}</el-descriptions-item>
          <el-descriptions-item label="昵称：">{{
            form.nickName
          }}</el-descriptions-item>
          <el-descriptions-item label="邮箱：">{{
            form.email
          }}</el-descriptions-item>
          <el-descriptions-item label="电话：">{{
            form.phoneNumber
          }}</el-descriptions-item>
          <el-descriptions-item label="年龄：">{{
            form.age
          }}</el-descriptions-item>
          <el-descriptions-item label="身份证：">{{
            form.idCard
          }}</el-descriptions-item>
        </el-descriptions>
      </el-dialog>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import request from "@/utils/request";
export default {
  name: "author",
  components: {},
  data() {
    return {
      search: "",
      currentPage: 1,
      pageSize: 10,
      total: 0,
      tableData: [],
      show: false,
      form: [],
    };
  },
  created() {
    this.load();
  },
  methods: {
    gosearch() {
      this.load();
    },
    handleSizeChange(pageSize) {
      console.log(pageSize);
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum);
      this.currentPage = pageNum;
      this.load();
    },

    load() {
      request
        .get("/user", {
          params: {
            pageNum: this.currentPage,
            pageSize: this.pageSize,
            search: this.search,
          },
        })
        .then((res) => {
          console.log(res);
          this.tableData = res.data.records;
          this.total = res.data.total;
        });
    },
  },
};
</script>
