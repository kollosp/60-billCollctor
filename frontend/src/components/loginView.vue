<template>
    <div class="has-background-brand-yellow has-background-image">
        <img class="background-image" src="../images/bg.jpg" />

        <section id="panel-login" class="hero is-primary is-fullheight">
            <div class="hero-body">
                <div class="container">
                    <div class="columns is-centered">
                        <div class="column is-full-mobile is-7-tablet is-6-desktop is-5-widescreen">
                            <div class="box">
                                <div class="content">
                                    <p class="has-text-justified">
                                        Witamy wszystkich klientów naszego serwisu na stronie logowania.
                                    </p>
                                </div>
                                <div class="form">
                                    <div class="field">
                                        <label class="label">Login / Email</label>
                                        <div class="control has-icons-left">
                                            <input v-model="email" type="text" placeholder="Podaj login/email" class="input" required>
                                            <span class="icon is-small is-left">
                                            <i class="fa fa-envelope"></i>
                                        </span>
                                        </div>
                                        <p class="help is-danger">{{errors.email}}</p>
                                    </div>
                                    <div class="field">
                                        <label class="label">Hasło</label>
                                        <div class="control has-icons-left">
                                            <input v-model="pass" type="password" placeholder="Podaj hasło" class="input" required>
                                            <span class="icon is-small is-left">
                                            <i class="fa fa-lock"></i>
                                        </span>
                                        </div>
                                        <p class="help is-danger">{{errors.pass}}</p>
                                    </div>
                                    <div class="field is-grouped">
                                        <div class="control">
                                            <button v-on:click="login()" v-on:keyup.enter="login()" class="button is-success">
                                                Zaloguj się
                                            </button>
                                        </div>
                                        <div class="control">
                                            <router-link to="/pwdreset" class="button is-link">
                                                Zresetuj hasło
                                            </router-link>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
        name: "loginView",
        data(){
            return {
                email: "",
                pass: "",
                errors: {
                    email: "",
                    pass: "",
                }
            }
        },

        methods: {
            login: function(){
                axios.post('/usermanagementopen/verifyuser', {
                    username: this.email.replace(/ /g, ''),
                    password: this.pass,
                }).then(m => {

                    console.log("m.data" ,m.data)

                    if(m.data.token) {

                        //save logged in status
                        this.$root.$data.token = m.data.token

                        if(this.$root.$data.token.indexOf('admin')>=0) {
                            this.$root.$data.adminMode = true
                        }

                        window.sessionStorage.setItem('token', m.data.token);

                        //redirect to panel
                        if(this.$route.query.d) {
                            this.$router.push({path: this.$route.query.d.toString()}).catch(err => {})
                        }else{
                            this.$router.push('/').catch(err => {})
                        }
                    }
                    //handle errors
                    else if(m.data.success == false){
                        if(m.data.error.indexOf("Incorrect username") > -1) {
                            this.errors.email = "Niepoprawna nazwa użytkownika"
                            this.errors.password = ""
                        }
                        else{
                            this.errors.email = ""
                            this.errors.pass = "Niepoprawne hasło"
                        }
                    }
                }).catch(e => console.error(e))

            }
        }
    }
</script>

<style scoped>

</style>