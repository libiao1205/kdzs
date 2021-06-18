const Layout = () => import(/* webpackChunkName: 'index' */ '../page/layout')
const staticRoute = [
    {
        path: '/',
        redirect: '/home'
    },
    {
        path: '/home',
        component: Layout,
        children: [
            {
                path: '',
                component: () => import(/* webpackChunkName: 'home' */ '../page/home'),
            },
            {
                path: '/sysUser',
                name: '/sysUser',
                component: () => import(/* webpackChunkName: 'home' */ '../page/sysUser'),
            },

        ]
    },
    {
        path:'/login',
        component: () => import(/* webpackChunkName: 'home' */ '../page/login')
    },
    {
        path: '*',
        redirect: '/error/404'
    },
]

export default staticRoute
