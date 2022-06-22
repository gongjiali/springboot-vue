<template>
  <div>
    <span>
      <el-input
        v-model="search"
        placeholder="Please input"
        clearable
        style="margin-left: 20px; padding: 10px; width: 350px"
      />
    </span>
    <span style="padding: 10px">
      <el-button type="primary" @click="gosearch">search</el-button>
    </span>
  </div>

  <div style="display: flex; margin-left: 50px; margin-top: 50px">
    <el-table
      :data="tableData"
      :default-sort="{ prop: 'paperId', order: 'ascending' }"
    >
      <el-table-column prop="paperId" label="ID" sortable />
      <el-table-column prop="title" label="Title" />
      <el-table-column prop="author" label="Author" />
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
            >assign reviewer</el-button
          >

          <el-dialog
            v-model="dialogVisible"
            title="Assign reviewer"
            width="30%"
          >
            <el-table
              :data="specialistData"
              :default-sort="{ prop: 'specialistId', order: 'ascending' }"
              @selection-change="handleSelectionChange"
            >
              <el-table-column prop="specialistId" label="ID" sortable />
              <el-table-column prop="name" label="Name" />
              <el-table-column type="selection" />
            </el-table>
            <template #footer>
              <span class="dialog-footer">
                <el-button @click="dialogVisible = false">Cancel</el-button>
                <el-button type="primary" @click="dialogVisible = false; assign()"
                  >Confirm</el-button
                >
              </span>
            </template>
          </el-dialog>
        </template>
      </el-table-column>
      <el-table-column>
        <el-button type="primary">detail</el-button>
      </el-table-column>
    </el-table>
  </div>
  <div class="demo-pagination-block">
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
</template>

<script>
// @ is an alias to /src
import request from "@/utils/request";

export default {
  name: "un_article",
  components: {},
  data() {
    return {
      currentPaper:[],
      ChosenSpecialists: [],
      dialogVisible: false,
      search: "",
      currentPage: 1,
      pageSize: 10,
      total: 0,
      tableData: [],
      specialistData: [],
    };
  },
  created() {
    this.load();
  },
  methods: {
    handleSelectionChange(val) {
      console.log(val)
      this.ChosenSpecialists = val.map(v=>v.specialistId)  // [{id,name}, {id,name}] => [id,id]
      console.log(this.ChosenSpecialists)
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
    assign(){
      console.log(this.currentPaper);
      this.currentPaper.status = 1;
      this.currentPaper.specialistId = this.ChosenSpecialists[0];
      console.log(this.currentPaper);
      if (!this.ChosenSpecialists.length) {
        this.$message.warning("请选择数据！")
        return
      }
      request.post("paper/assign", this.currentPaper).then(res => {
        if (res.code === '0') {
          this.$message.success("分配成功")
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })

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
