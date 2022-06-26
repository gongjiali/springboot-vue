<template>
  <div style="padding: 10px">
    <div style="margin: 10px 0">
      <el-input
        v-model="search"
        placeholder="请输入关键字"
        style="width: 30%"
        clearable
      ></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load"
        >查询</el-button
      >
    </div>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="paperId" label="稿件id" width="100" sortable />
      <el-table-column prop="title" label="稿件标题" width="150" sortable />
      <el-table-column prop="author" label="作者" sortable />
      <el-table-column prop="category" label="类别" width="150" sortable />
      <el-table-column fixed="right" label="操作" width="120">
        <template #default>
          <el-button link type="primary" size="small" @click="ttPaper"
            >详细信息</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-pagination
        style="margin: 10px 0"
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
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "AllPaper",
  components: {},
  data() {
    return {
      search: "",
      currentPage: 1,
      pageSize: 10,
      total: 0,
      tableData: [],
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      request
        .get("/paper", {
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
    handleClick() {},
    handleSizeChange() {},
    handleCurrentChange() {},
    ttPaper() {
      this.$router.replace("/Paper");
    },
  },
};
</script>

<style scoped>
</style>