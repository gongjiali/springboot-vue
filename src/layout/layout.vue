<template>
  <div>
    <Header />
    <div style="display: flex">
      <Aside />
    </div>
      <router-view style="flex:1"/>
  </div>

</template>

<style>
</style>

<script>
import Header from "@/components/Header";
import Aside from "@/components/Aside";
export default {
  Name: "Layout",
  components: {
    Header,
    Aside,
  },
  
};
</script>
