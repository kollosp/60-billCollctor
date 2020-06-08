<template>

    <div class="box">
        <message ref="message"></message>
        <h1 class="title has-text-grey-darker">Panel klienta</h1>

        <div class="content">
            <p class="has-text-justified">
                Zapraszamy do zalogowania się do panelu rachunkow
            </p>
        </div>

        <div class="field">
            <label class="label">Wyszukaj fraze</label>
            <div class="control">
                <input v-on:change="reloadPage" v-model="text" class="input" type="text" placeholder="Wprowdz wyszukiwana fraze">
            </div>
        </div>

        <table class="table is-narrow">
            <tr>
                <th>L.P</th>
                <th>Id</th>
                <th>Data</th>
                <th>Kwota</th>
                <th>Akcje</th>
            </tr>
            <h1 v-if="bills.length == 0">Nie został dodany jeszcze zaden rachunek!</h1>
            <tr v-for="(item, index) in bills">
                <td>{{parseInt(index)+ offset}}</td>
                <td>{{item.id}}</td>
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


        <div class="box">
            <h3 class="subtitle">Konfiguracja tabelki</h3>
            <pagination
                :elements="billsCount"
                :elementsPerPage="elementsPerPage"
                v-on:pageChanged="pageChanged"
            ></pagination>
            <hr>
            <div class="field">
                <label class="label">Sortowanie</label>
                <div class="control">
                    <div class="select">
                        <select v-model="sort" v-on:change="reloadPage">
                            <option value="1">Cena Rosnaco</option>
                            <option value="2">Data Rosnaco</option>
                            <option value="3">Cena Malejaco</option>
                            <option value="4">Data Malejaco</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="field">
                <label class="label">Liczba elementow na stronie</label>
                <div class="control">
                    <input v-on:change="reloadPage" v-model="elementsPerPage" class="input" type="number" placeholder="Text input">
                </div>
            </div>

        </div>

        <button class="button is-link" v-on:click="add()">Dodaj</button>

    </div>

</template>

<script>
    import axios from 'axios'
    import Message from "../assets/message.vue";
    import Pagination from "../assets/pagination.vue"

    export default {
        name: "index",
        components: {Pagination, Message},
        data(){
            return {
                token: null,
                billsCount: 0,
                bills: [],
                offset: 0,
                elementsPerPage: 5,
                sort: 1,
                text: "",
            }
        },

        mounted() {
            this.token = window.sessionStorage.getItem("token")
            this.loadBillCount()
        },

        methods: {
            pageChanged: function(first, count){
                this.offset = first
                this.reloadPage()
            },

            reloadPage: function(){
                axios.post('/getSpecificBills', {
                    token: this.token,
                    offset: this.offset,
                    count: this.elementsPerPage,
                    sortBy: this.sort%2 == 0 ? "date" : "price",
                    sortMode: this.sort < 3 ? "ascending" : "descending",
                    descString: this.text
                }).then(m => {
                    this.bills = m.data
                    console.log(this.bills)
                }).catch(e => {
                    console.error(e)
                })
            },

            loadBillCount: function(){
                axios.post('/getBillCount', {
                    token: this.token,
                    descString: this.text
                }).then(m => {
                    this.billsCount  = m.data.count
                    if(this.billsCount > 0){
                        this.pageChanged(1, this.elementsPerPage)
                    }

                }).catch(e => {
                    console.error(e)
                })
            },

            add: function () {

                axios.post('/createBill', {
                    token: this.token,
                }).then(m => {
                    if(m.data.error){
                        this.$refs.message.showDanger("", m.data.error)
                    }else{
                        this.$refs.message.showSuccess("", "Dodano rachunek")
                        this.loadBillCount()
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
                            this.loadBillCount()
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