<template>
  <div>
    <Header />
    <div style="display: flex">
      <Aside />
    </div>
  </div>
  <router-view />
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
