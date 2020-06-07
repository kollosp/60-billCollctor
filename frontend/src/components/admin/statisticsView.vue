<template>

    <div class="box">
        <message ref="message"></message>
        <div class="content">
            <p class="has-text-justified">
                Poniżej zostały zaprezentowane statystyki serwisu
            </p>
        </div>
        <table class="table is-narrow">
            <tr><th>Liczba użytkowników</th><td>{{params.userCount}}</td></tr>
            <tr><th>Liczba rachunków</th><td>{{params.billCount}}</td></tr>
            <tr><th>Najdroższy rachuenk</th><td>{{params.billMax}}</td></tr>
            <tr><th>Najwyższa suma rachunków u użytkownika</th><td>{{params.userSumMax}}</td></tr>
        </table>
        <router-link to="/" class="button is-link">
            Powrot
        </router-link>
    </div>

</template>

<script>
    import axios from 'axios'
    import Message from "../assets/message.vue";

    export default {
        name: "loginView",
        components: {Message},
        data(){
            return {
                params: {
                    userSumMax: 0,
                    billMax: 0,
                    userCount: 0,
                    billCount: 0,
                }
            }
        },

        mounted() {
            let keys = Object.keys(this.params)

            for(let k of keys){
                this.loadStat(k).then(m => this.params[k] = m[k])
            }
        },

        methods: {

            loadStat: function(statName){
                return new Promise((resolve, reject) => {
                    axios.post('/' + statName, {}).then(m => {
                        console.log("m.data" ,m.data)
                        resolve(m.data)
                    }).catch(e => {
                        console.error(e)
                        this.$refs.message.showDanger("",e.toString())
                        //reject(e);
                    })
                })

            }
        }
    }
</script>

<style scoped>

</style>