<template>
    <div class="box">
        <message ref="message"></message>
        <h1 class="title has-text-grey-darker">Panel admina</h1>

        <div class="content">
            <p class="has-text-justified">
                Witaj w serwisie
            </p>
        </div>

        <table class="table is-narrow">
            <tr>
                <th>L.P</th>
                <th>Id</th>
                <th>Imie</th>
                <th>Nazwisko</th>
                <th>Email</th>
                <th>Telefon</th>
                <th>Rola</th>
                <th>Akcje</th>
            </tr>
            <h1 v-if="users.length == 0">Nie istnieje zaden klient</h1>
            <tr v-for="(item, index) in users">
                <td>{{parseInt(index)+1}}</td>
                <td>{{item.id}}</td>
                <td>{{item.name}}</td>
                <td>{{item.surname}}</td>
                <td>{{item.email}}</td>
                <td>{{item.role == "admin" ? "admin" : "klient"}}</td>
                <td>{{item.phone}}</td>
                <td>
                    <div class="buttons">
                        <button v-if="item.role !='admin'" class="button is-success" v-on:click="addAdminRole(item.id, 'admin')">Nadaj upraw. admina</button>
                        <button v-else class="button is-warning" v-on:click="addAdminRole(item.id, 'client')">Usuń upraw. admina</button>
                        <button class="button is-danger" v-on:click="remove(item.id)">Usuń</button>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</template>

<script>
    import axios from "axios";
    import Message from "../assets/message.vue";

    export default {
        name: "adminIndexView.vue",
        components: {Message},
        data(){
            return {
                token: null,
                users: []
            }
        },

        mounted() {
            this.token = window.sessionStorage.getItem("token")
            this.load()
        },

        methods: {
            load: function(){
                axios.post('/getUsers', {
                    token: this.token,
                }).then(m => {
                    if(m.data.error){
                        this.$refs.message.showDanger("", m.data.error)
                    }else{
                        this.users = m.data
                    }


                }).catch(e => {
                    console.error(e)
                    this.$refs.message.showDanger("", e)
                })
            },

            addAdminRole: function (id, role) {
                axios.post('/addUserRole', {
                    token: this.token,
                    userId: id,
                    userRole: role,
                }).then(m => {
                    if(m.data.error){
                        this.$refs.message.showDanger("", m.data.error)
                    }else{
                        this.load()
                        this.$refs.message.showSuccess("", "Poprawnie zmodyfikowano uprawnienia")
                    }

                }).catch(e => {
                    console.error(e)
                    this.$refs.message.showDanger("", e)
                })
            },

            remove: function (id){
                axios.post('/deleteUserByAdmin', {
                    token: this.token,
                    userId: id,
                    userRole: "",
                }).then(m => {
                    if(m.data.error){
                        this.$refs.message.showDanger("", m.data.error)
                    }else{
                        this.load()
                        this.$refs.message.showSuccess("", "Poprawnie usunieto klienta")
                    }

                }).catch(e => {
                    console.error(e)
                    this.$refs.message.showDanger("", e)
                })
            }

        }
    }
</script>

<style scoped>

</style>