<template>
    <div class="has-background-image">
        <img class="background-image" src="../../images/bg.jpg">
        <section id="panel-login" class="hero has-background-brand-yellow is-fullheight">
            <div class="hero-body">
                <div class="container">
                    <div class="columns is-centered">
                        <div class="column is-12-tablet is-9-desktop is-8-widescreen">
                            <router-view></router-view>
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

            }
        },

        mounted() {
            if(this.token == null){
                let t = window.sessionStorage.getItem("token")

                if(t!=null){
                    this.verify()
                    this.token = t
                }else{
                    this.$router.push("/login").catch(err => {})
                }
            }
        },

        methods: {
            verify: function () {
                axios.post("/hello").then(m => {
                    alert("hello" + m.data)
                }).catch(e => {
                    window.sessionStorage.setItem("token", null)
                    this.$router.push({path: "/sessionend", query: {d: this.$router.currentRoute.path}}).catch(err => {})
                })
            }
        }


    }
</script>

<style scoped>

</style>