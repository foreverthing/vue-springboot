import { createRouter, createWebHistory } from 'vue-router'
const routes = [
    {
        path: '/',
        name: 'Home',
        component: () => import('../src/views/Home.vue')
    },
    {

        path: '/login',
        name: 'Login',
        component: () => import('../src/views/Login.vue')

    }
]
const router = createRouter({
    history: createWebHistory("/"),
    routes: routes,
})
/**

 - 输出对象
 */
export default router;