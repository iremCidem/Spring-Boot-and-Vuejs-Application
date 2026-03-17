<template>
  <div class="header">
    <router-link :to="'/'">
      Home
    </router-link>
    <router-link :to="'/profile/' + userId">
      Profile
    </router-link>
  </div>
</template>

<script setup>
const userId = 1

</script>

<style scoped>
.header{
  padding: 1rem;
  display: flex;
  justify-content: space-between;
  border-bottom: 1px solid gray;
}
</style>
