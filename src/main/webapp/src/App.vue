<script setup lang="ts">

import Header from "@/components/Header.vue";
</script>

<template>
<Header/>
  <main class="app-content">
    <router-view />
  </main>

</template>

<style>
  .app-content{
    margin: 2rem;
  }
  a {
    text-decoration: none;
  }
</style>
