<template>
  <div>
    <div style="padding: 10px">
      <span style="margin: 10px 0">
        <el-input
          v-model="search"
          placeholder="请输入"
          clearable
          style="margin-left: 20px; padding: 10px; width: 30%"
        />
      </span>
      <span style="padding: 10px">
        <el-button type="primary" @click="gosearch">搜索</el-button>
      </span>
    </div>

    <div>
      <el-table
        :data="tableData"
        :default-sort="{ prop: 'paperId', order: 'ascending' }"
        style="width: 90%; margin-left: 20px; margin-top: 10px"
      >
        <el-table-column prop="paperId" label="ID" sortable />
        <el-table-column prop="title" label="标题" />
        <el-table-column prop="author" label="作者" />
        <el-table-column prop="specialistId" label="审稿人" />
        <el-table-column>
          <template #default="scope">
            <el-button
              type="primary"
              @click="
                show = true;
                form = scope.row;
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
      <el-dialog title="论文详细信息" v-model="show" width="40%">
        <el-descriptions :column="1">
          <el-descriptions-item label="稿件编号：">{{
            form.paperId
          }}</el-descriptions-item>
          <el-descriptions-item label="稿件标题：">{{
            form.title
          }}</el-descriptions-item>
          <el-descriptions-item label="作者：">{{
            form.author
          }}</el-descriptions-item>
          <el-descriptions-item label="类别：">{{
            form.category
          }}</el-descriptions-item>
          <el-descriptions-item label="稿件状态：">{{
            form.status
          }}</el-descriptions-item>
        </el-descriptions>
        <el-button-group style="margin-bottom: 15px; width: 100%">
          <el-button type="info" plain @click="ttDownload(form.filePath)"
            >下载</el-button
          >
        </el-button-group>
      </el-dialog>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import request from "@/utils/request";
export default {
  name: "article",
  components: {},
  data() {
    return {
      search: "",
      currentPage: 1,
      pageSize: 10,
      total: 0,
      show: false,
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

    ttDownload(filePath) {
      window.open(filePath, "_self");
    },
    load() {
      request
        .get("/paper/Assigned", {
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
