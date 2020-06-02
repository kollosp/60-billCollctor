<template>
    <div class="has-background-image">
        <img class="background-image" src="../../images/bg.jpg">
        <section id="panel-login" class="hero has-background-brand-yellow is-fullheight">
            <div class="hero-body">
                <div class="container">
                    <div class="columns is-centered">
                        <div class="column is-12-tablet is-9-desktop is-8-widescreen">
                            <router-view></router-view>
                            <button class="button is-link" v-on:click="logout()">Wyloguj</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "router",
        data() {
            return {
                token: null
            }
        },

        mounted() {
            if(this.token == null){
                let t = window.sessionStorage.getItem("token")
                console.log(t)
                if(t!=null){
                    this.verify(t)
                    this.token = t
                }else{
                    this.$router.push("/login").catch(err => {})
                }
            }
        },

        methods: {
            verify: function (token) {
                axios.post("/verify",{
                    token: token
                }).then(m => {
                    if(m.data.verify != 'ok'){
                        window.sessionStorage.setItem("token", null)
                        this.$router.push({path: "/sessionend", query: {d: this.$router.currentRoute.path}}).catch(err => {})
                    }
                }).catch(e => {
                    window.sessionStorage.setItem("token", null)
                    this.$router.push({path: "/sessionend", query: {d: this.$router.currentRoute.path}}).catch(err => {})
                })
            },

            logout: function () {
                console.log("this.token", this.token)
                axios.post("/logout",{
                    token: this.token
                }).then(m => {
                    window.sessionStorage.setItem("token", null)
                    this.$router.push({path: "/login"}).catch(err => {})
                }).catch(e => {
                    console.error(e)
                    window.sessionStorage.setItem("token", null)
                    this.$router.push({path: "/login"}).catch(err => {})
                })
            }
        }
    }
</script>

<style scoped>

</style>