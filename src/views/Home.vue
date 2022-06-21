<template>
  <div>
    <span>
      <el-input
        v-model="search"
        placeholder="Please input"
        clearable
        style="margin-left:20px; padding: 10px; width: 350px"
      />
    </span>
    <span style="padding: 10px">
      <el-button type="primary">search</el-button>
    </span>
  </div>

  <div style="display: flex; margin-left: 50px; margin-top: 50px">
    <el-table
      :data="tableData"
      :default-sort="{ prop: 'userId', order: 'descending' }"
    >
      <el-table-column prop="userId" label="userID" sortable />
      <el-table-column prop="nickName" label="nickName" />
      <el-table-column prop="phoneNumber" label="phoneNumber" :formatter="formatter" />
    </el-table>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "HomeView",
  components: {},
  data() {
    return {
      search: '',
      currentPage:1,
      total:10,
      tableData: [
        
      ],
    };
  },
};
</script>
