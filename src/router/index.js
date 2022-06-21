import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/layout.vue'


const routes = [{

    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: "/author",
    children: [{
            path: 'author',
            name: 'author',
            component: () =>
                import ("@/views/author"),
        },
        {
            path: 'article',
            name: 'article',
            component: () =>
                import ("@/views/article"),
        },
        {
            path: 'reviewer',
            name: 'reviewer',
            component: () =>
                import ("@/views/reviewer"),
        },
        {
            path: 'un_article',
            name: 'un_article',
            component: () =>
                import ("@/views/un_article"),
        }
    ]













}]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router