<template>
  <div>
    <div>
      <span>
        <el-input
          v-model="search"
          placeholder="请输入"
          clearable
          style="margin-left: 10px; padding: 10px; width: 30%"
        />
      </span>
      <span>
        <el-button type="primary" @click="gosearch">搜索</el-button>
      </span>
      <span style="padding: 10px">
        <el-button type="primary" @click="$router.push('/newReviewer')"
          >添加审稿人</el-button
        >
      </span>
    </div>

    <div>
      <el-table
        :data="tableData"
        :default-sort="{ prop: 'userId', order: 'ascending' }"
        style="width: 90%; margin-left: 20px; margin-top: 10px"
      >
        <el-table-column prop="userId" label="ID" sortable />
        <el-table-column prop="nickName" label="昵称" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="email" label="E-mail" />
        <el-table-column>
          <template #default="scope2">
            <el-button
              type="primary"
              @click="
                form = scope2.row;
                show = true;
              "
              >详情</el-button
            >
          </template>
        </el-table-column>
        <el-table-column>
          <template #default="scope">
            <el-button type="primary" @click="deleteReviewer(scope.row.userId)"
              >删除</el-button
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
      <el-dialog title="审稿人详细信息" v-model="show" width="30%">
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
          <el-descriptions-item label="类别：">{{
            form.category
          }}</el-descriptions-item>
          <el-descriptions-item label="状态：">{{
            form.status
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
  name: "reviewer",
  components: {},
  data() {
    return {
      search: "",
      currentPage: 1,
      pageSize: 10,
      total: 0,
      tableData: [],
      form: [],
      show: false,

    };
  },
  created() {
    this.load();
  },
  methods: {
    deleteReviewer(id) {
      console.log(id);
      request.delete("/specialist/" + id).then((res) => {
        if (res.code === "0") {
          this.$message({
            type: "success",
            message: "删除成功",
          });
        } else {
          this.$message({
            type: "error",
            message: res.msg,
          });
        }
        this.load(); // 删除之后重新加载表格的数据
      });
    },

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
        .get("/specialist", {
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
