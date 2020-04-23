<template>
    <router-view></router-view>
</template>

<script>
    import axios from "axios";

    export default {
        name: "router",
        mounted() {
            console.log("page mounted")
            axios.post("/hello").then(m => {
                alert("hello" + m.data)
            }).catch(e => {
                console.error(e)
            })
        }
    }
</script>

<style scoped>

</style>