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
        <el-table-column>
          <template #default="scope">
            <!-- 弹窗 -->
            <el-button
              text
              @click="
                dialogVisible = true;
                load_reviewers();
                currentPaper = scope.row;
              "
              >分配审稿人</el-button
            >

            <el-dialog v-model="dialogVisible" title="分配审稿人" width="30%">
              <el-table
                :data="specialistData"
                :default-sort="{ prop: 'userId', order: 'ascending' }"
                @selection-change="handleSelectionChange"
              >
                <el-table-column prop="userId" label="ID" sortable />
                <el-table-column prop="name" label="姓名" />
                <el-table-column type="selection" />
              </el-table>
              <template #footer>
                <span class="dialog-footer">
                  <el-button @click="dialogVisible = false">取消</el-button>
                  <el-button
                    type="primary"
                    @click="
                      dialogVisible = false;
                      assign();
                    "
                    >确认</el-button
                  >
                </span>
              </template>
            </el-dialog>
          </template>
        </el-table-column>
        <el-table-column>
          <template #default="scope2">
            <el-button
              type="primary"
              @click="
                show = true;
                form = scope2.row;
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
  name: "un_article",
  components: {},
  data() {
    return {
      currentPaper: [],
      ChosenSpecialists: [],
      dialogVisible: false,
      search: "",
      currentPage: 1,
      pageSize: 10,
      total: 0,
      show: false,
      form: [],
      tableData: [],
      specialistData: [],
    };
  },
  created() {
    this.load();
  },
  methods: {
    handleSelectionChange(val) {
      console.log(val);
      this.ChosenSpecialists = val.map((v) => v.userId); // [{id,name}, {id,name}] => [id,id]
      console.log(this.ChosenSpecialists);
    },
    gosearch() {
      this.load();
    },
    ttDownload(filePath) {
      window.open(filePath, "_self");
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
    assign() {
      console.log(this.currentPaper);
      this.currentPaper.status = 1;
      this.currentPaper.specialistId = this.ChosenSpecialists[0];
      console.log(this.currentPaper);
      if (!this.ChosenSpecialists.length) {
        this.$message.warning("请选择数据！");
        return;
      }
      request.post("paper/assign", this.currentPaper).then((res) => {
        if (res.code === "0") {
          this.$message.success("分配成功");
          this.load();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    load() {
      request
        .get("/un_paper", {
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

    load_reviewers() {
      request
        .get("/specialist/bycat", {
          params: {
            pageNum: this.currentPage,
            pageSize: this.pageSize,
            cat: this.currentPaper.category,
          },
        })
        .then((res) => {
          console.log(res);
          this.specialistData = res.data.records;
          this.total = res.data.total;
        });
    },
    handleEdit(paperId) {
      console.log(paperId);
    },
  },
};
</script>
