<template>
  <!--  崔安然-->
  <el-button text @click="dialogVisible = true">注销账户</el-button>

  <el-dialog v-model="dialogVisible" width="40%" >
   <span>
     是否要注销账户？
   </span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="delete_acc">注销</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import request from "@/utils/request";


export default {

  name: "delete",
  components: {},
  data(){
    return{    dialogVisible : false
    }
  },

  methods:{
    delete_acc(){

    }
  }

}
</script>

<style scoped>
</style>