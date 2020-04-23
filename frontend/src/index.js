import './stylesheets/style.sass'

import Vue from 'vue'
import VueRouter from 'vue-router'
import index from './components/router.vue'
import indexPage from './components/index/router.vue'
import indexView from './components/index/index.vue'
import login from './components/loginView.vue'
import sessionend from './components/sessionEndView.vue'
import pwdreset from './components/pwdResetView.vue'
import billView from './components/index/bill.vue'

Vue.use(VueRouter)

let router = new VueRouter({
    routes: [
        { path: '/login', component: login },
        { path: '/sessionend', component: sessionend },
        { path: '/pwdreset', component: pwdreset },
        { path: '/', component: indexPage, children: [
            { path: '/', component: indexView },
            { path: '/bill/:id', component: billView },
        ]},
    ]
})


let app = new Vue({
    el: "#app",
    mixins: [index],
    router: router,
})