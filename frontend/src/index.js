import './stylesheets/style.sass'

import Vue from 'vue'
import VueRouter from 'vue-router'
import index from './components/index/router.vue'
import indexView from './components/index/index.vue'
import login from './components/loginView.vue'
import sessionend from './components/sessionEndView.vue'

Vue.use(VueRouter)

let router = new VueRouter({
    routes: [
        { path: '/login', component: login },
        { path: '/sessionend', component: sessionend },
        { path: '/', component: indexView },
    ]
})


let app = new Vue({
    el: "#app",
    mixins: [index],
    router: router,
})