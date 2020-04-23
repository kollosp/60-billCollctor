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
                <td>{{parseInt(index) + index}}</td>
                <td>{{item.time.toLocaleString()}}</td>
                <td>{{item.price}}</td>
                <td>
                    <div class="buttons">
                        <router-link class="button is-link" :to="'/bill/' + index">Szczgóły</router-link>
                        <button class="button is-danger" v-on:click="remove(index)">Usuń</button>
                    </div>
                </td>
            </tr>

        </table>

        <button class="button is-link" v-on:click="add">Dodaj</button>

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
                bills: [{time:  new Date(), price: 123.48}]
            }
        },

        mounted() {
        },

        methods: {
            load: function(){

            },

            add: function () {

            },

            remove: function (index) {
                if (confirm("Czy na pewno chcesz usunąć ten rachunek?")){
                    console.log("")
                    this.$refs.message.showSuccess("Usunięto rachunek")
                }else{
                    this.$refs.message.showDanger("Nie udało się wykonać zadania")
                }
            }
        }
    }
</script>

<style scoped>

</style>