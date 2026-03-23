<template>
  <div>
    <div><label for="postTitle">Title</label> <input name="postTitle" type="text" v-model="postTitle"/>
    </div>
    <div><label for="postText">Text</label> <input name="postText" type="text" v-model="postText"/>
    </div>
    <button @click="createPost">Post</button>
  </div>
  <div v-if="loading">Loading...</div>
  <div v-else-if="error">{{ error }}</div>
  <div v-else>
    <div v-for="post in postList" :key="post.id">
      <PostCard :post="post"/>
    </div>
  </div>
</template>

<script setup lang="ts">
import {ref, onMounted} from "vue";
import PostCard from "@/components/PostCard.vue";
import {useApi} from "@/services/api";
import type {Post} from "@/services/types/Post";

const {api, loading, error} = useApi();
const postList = ref<Post[] | any>([]);
const postTitle = ref("");
const postText = ref("");

const createPost = async () => {
  const body = {
    title: postTitle.value,
    text: postText.value,
    userId: 1
  }
  try {
    const res = await api.post("/api/posts", body)
    postList.value = res;
  } catch (error) {
  }
}

onMounted(async () => {
  try {
    const res = await api.get<Post[]>("/api/posts");
    postList.value = res;
  } catch (e) {
    console.log("API error", e);
  }
});

</script>
