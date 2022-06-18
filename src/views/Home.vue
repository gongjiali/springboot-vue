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
      :default-sort="{ prop: 'date', order: 'descending' }"
    >
      <el-table-column prop="date" label="Date" sortable />
      <el-table-column prop="name" label="Name" />
      <el-table-column prop="address" label="Address" :formatter="formatter" />
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
      tableData: [
        {
          date: "2016-05-03",
          name: "Tom",
          address: "No. 189, Grove St, Los Angeles",
        },
        {
          date: "2016-05-02",
          name: "Tom",
          address: "No. 189, Grove St, Los Angeles",
        },
        {
          date: "2016-05-04",
          name: "Tom",
          address: "No. 189, Grove St, Los Angeles",
        },
        {
          date: "2016-05-01",
          name: "Tom",
          address: "No. 189, Grove St, Los Angeles",
        },
      ],
    };
  },
};
</script>
