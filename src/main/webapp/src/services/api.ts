import axios from 'axios'
import {ref} from 'vue'

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL

const loading = ref(false)
const error = ref(null)


const api = axios.create({
  baseURL: API_BASE_URL,
  timeout: 15000,
  headers: {
    'Content-Type': 'application/json'
  }
})


api.interceptors.request.use(
  (config) => {
    loading.value = true
    error.value = null

    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }

    return config
  },
  (err) => {
    loading.value = false
    return Promise.reject(err)
  }
)


api.interceptors.response.use(
  (response) => {
    loading.value = false
    return response.data
  },
  (err) => {
    loading.value = false
    error.value = err.response?.data?.message || 'Sunucu hatası'
    return Promise.reject(error.value)
  }
)

export function useApi() {
  return {
    api,
    loading,
    error
  }
}

