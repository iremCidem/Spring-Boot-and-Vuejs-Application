import HomePage from '@/pages/HomePage.vue'
import { createRouter, createWebHistory } from 'vue-router'
import ProfilePage from "@/pages/ProfilePage.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
      { path: '/', component: HomePage },
    { path: '/profile/:id', component: ProfilePage },
  ],
})

export default router
