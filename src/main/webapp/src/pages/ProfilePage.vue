<template>
  <div v-if="loading">Loading...</div>
  <div v-else-if="error">{{ error }}</div>
  <div v-else v-for="post in postList" :key="post.id">
    <PostCard :post="post" @delete="deletePost" @update="updatePost" />
  </div>
</template>

<script setup>
import {onMounted, ref} from "vue";
import axios from "axios";
import { useRoute } from "vue-router"
import PostCard from "@/components/PostCard.vue";
import {useApi} from "@/services/api.js";
const {api, loading, error} = useApi();

let postList = ref([])
const route = useRoute()
const userId = route.params.id

const getMyPosts = async () => {
  try {
   const res = await axios.get(`/api/posts?userId=${userId}`)
    postList.value = res.data
  }catch(err) {
    console.log(err)
  }
}

const deletePost = async (postId) => {
  try {
    await api.delete(`/api/posts/${postId}`)
    await getMyPosts()
  }
  catch (err){
    console.log(err)
  }

}

async function updatePost(post) {
  const res = await api.put(`/api/posts/${post.postId}`, post.body.body)
  const updatePost = postList.value.find((el) => el.id === post.postId)
  if(updatePost){
    updatePost.title = res.title,
      updatePost.text = res.text
  }
}

onMounted(() => {
  getMyPosts();
})
</script>

<style lang="scss" scoped>

</style>
