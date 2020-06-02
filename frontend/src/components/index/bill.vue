<template>
    <div class="box content">

        <message ref="message"></message>
        <h1 class="title has-text-grey-darker">Rachunek</h1>
        <p>
            Zarządzanie rachunkiem
        </p>

        <p>{{billData}}</p>

        <router-link to="/" class="button is-link">Powrót</router-link>

    </div>
</template>

<script>

    import axios from 'axios'
    import Message from "../assets/message.vue";

    export default {
        name: "bill",
        data(){
            return {
                billData: ""
            }
        },

        mounted() {
            this.load()
        },

        methods: {
            load: function(){
                let token = window.sessionStorage.getItem("token")
                axios.post('/getBill', {
                    token: token,
                    id: this.$route.params.id
                }).then(m => {
                    if(m.data.error){
                        this.$refs.message.showDanger("", "Odmowa dostepu do rachunku")
                    }else{
                        this.billData = m.data
                    }

                }).catch(e => {
                    console.error(e)
                })
            }
        }
    }
</script>

<style scoped>

</style>