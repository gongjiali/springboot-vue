import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/index'
import axios from "axios";


import '@/assets/css/global.css'

import ElementPlus from 'element-plus';
import 'element-plus/theme-chalk/index.css';

createApp(App).use(store).use(router).use(ElementPlus).mount('#app')
Vue.prototype.$axios = axios
