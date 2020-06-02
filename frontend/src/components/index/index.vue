<template>

    <div class="box content">
        <message ref="message"></message>
        <h1 class="title has-text-grey-darker">Panel klienta</h1>

        <p class="has-text-justified">
            Zapraszamy do zalogowania się do panelu rachunkow
        </p>

        <table class="table is-narrow">
            <tr>
                <th>L.P</th>
                <th>Data</th>
                <th>Kwota</th>
                <th>Akcje</th>
            </tr>

            <tr v-for="(item, index) in bills">
                <td>{{parseInt(index)+1}}</td>
                <td>{{item.date}}</td>
                <td>{{item.price}}</td>
                <td>
                    <div class="buttons">
                        <router-link class="button is-link" :to="'/bill/' + item.id">Szczgóły</router-link>
                        <button class="button is-danger" v-on:click="remove(item.id)">Usuń</button>
                    </div>
                </td>
            </tr>

        </table>

        <button class="button is-link" v-on:click="add()">Dodaj</button>

    </div>

</template>

<script>
    import axios from 'axios'
    import Message from "../assets/message.vue";

    export default {
        name: "index",
        components: {Message},
        data(){
            return {
                bills: [{date:  new Date(), price: 123.48}]
            }
        },

        mounted() {
            this.load()
        },

        methods: {
            load: function(){
                let token = window.sessionStorage.getItem("token")
                axios.post('/getBills', {
                    token: token
                }).then(m => {
                    this.bills = m.data
                }).catch(e => {
                    console.error(e)
                })
            },

            add: function () {
                let token = window.sessionStorage.getItem("token")
                axios.post('/createBill', {
                    token: token,
                }).then(m => {
                    if(m.data.error){
                        this.$refs.message.showDanger("", m.data.error)
                    }else{
                        this.$refs.message.showSuccess("", "Dodano rachunek")
                        this.load()
                    }
                })
            },

            remove: function (index) {
                if (confirm("Czy na pewno chcesz usunąć ten rachunek?")){
                    let token = window.sessionStorage.getItem("token")
                    axios.post('/deleteBill', {
                        token: token,
                        id: index
                    }).then(m => {
                        if(m.data.error){
                            this.$refs.message.showDanger("", m.data.error)
                        }else{
                            this.$refs.message.showSuccess("", "Usunięto rachunek")
                            this.load()
                        }
                    })
                }else{
                    this.$refs.message.showDanger("Nie udało się wykonać zadania")
                }
            }
        }
    }
</script>

<style scoped>

</style>