import { createRouter, createWebHistory } from 'vue-router'
import Review from "@/views/Review";
import Layout from "@/Layout/Layout";
import AllPaper from "@/views/AllPaper";

const routes = [{
        path: '/',
        name: 'Layout',
        component: Layout,
        redirect:'/Login',
        children: [{
                path: 'user',
                name: 'User',
                component: () =>
                    import ("@/views/User"),

            },
            {
                path: 'submitPaper',
                name: 'SubmitPaper',
                component: () =>
                    import ("@/views/SubmitPaper")
            },
            {
                path: 'review',
                name: 'Review',
                component: () =>
                    import ("@/views/Review")
            },
            {
                path: 'allPaper',
                name: 'AllPaper',
                component: () =>
                    import ("@/views/AllPaper")
            },
            {
                path: 'author',
                name: 'author',
                component: () =>
                    import ("@/views/admin/author"),
            },
            {
                path: 'article',
                name: 'article',
                component: () =>
                    import ("@/views/admin/article"),
            },
            {
                path: 'reviewer',
                name: 'reviewer',
                component: () =>
                    import ("@/views/admin/reviewer"),
            },
            {
                path: 'un_article',
                name: 'un_article',
                component: () =>
                    import ("@/views/admin/un_article"),
            },

        ]
    },
    {
        path: '/login',
        name: 'Login',
        component: () =>
            import ("@/views/Login")
    },
    {
        path: '/register',
        name: 'Register',
        component: () =>
            import ("@/views/Register")
    },
    {
        path: '/newReviewer',
        name: 'newReviewer',
        component: () =>
            import ("@/views/admin/newReviewer"),
    },
    {
            path: '/person',
            name: 'person',
            component: () =>
                import ("@/views/Person"),
        },
        {
                path: '/password',
                name: 'password',
                component: () =>
                    import ("@/views/Password"),
            },

]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router