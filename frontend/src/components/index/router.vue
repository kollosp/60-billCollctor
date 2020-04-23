<template>
    <router-view></router-view>
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
                axios.post("/usermanagementopen/verifysession").then(m => {

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