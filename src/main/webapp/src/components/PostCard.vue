<template>
  <el-card style="max-width: 480px">
    <template #header>
      <div class="card-header">
        <span>By:{{ userId }}</span>
      </div>
    </template>
    <div>
      <h2>{{ post.title }}</h2>
      <p>{{ post.text }}</p>
    </div>
    <template #footer v-if="userId">
      <el-button
        type="warning"
        text
        bg
        @click="updatePostDialog = true;"
      >
        Update
      </el-button>
      <el-button
        type="danger"
        text
        bg
        @click="handleDelete(post.id)"
      >
        Delete
      </el-button>
    </template>
  </el-card>
  <el-dialog v-model="updatePostDialog" title="Update your post" width="500">
    <el-form :model="updatePostForm">
      <el-form-item label="Post Title">
        <el-input v-model="updatePostForm.postTitle" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="Text">
        <el-input v-model="updatePostForm.postText" autocomplete="off"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="updatePostDialog = false">Cancel</el-button>
        <el-button type="primary" @click="updatePost(post.id)">
          Confirm
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import {useRoute} from "vue-router";
import {ref} from "vue";

const route = useRoute()
const userId = route.params.id
const emit = defineEmits(["delete", "update"])
const props = defineProps({
  post: {
    type: Object,
    required: true,
  },
})
let updatePostDialog = ref(false)
const updatePostForm = ref({
  postTitle: props.post.title,
  postText: props.post.text
})

function handleDelete(postId: Number) {
  emit("delete", postId)
}

function updatePost(postId: Number) {
  emit("update", {
    postId, body: {
      body: {
        title: updatePostForm.value.postTitle,
        text: updatePostForm.value.postText
      }
    }
  })
}


</script>

<style scoped>

</style>
