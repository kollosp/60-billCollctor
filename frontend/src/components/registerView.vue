<template>
    <div class="has-background-brand-yellow has-background-image">
        <img class="background-image" src="../images/bg.jpg" />

        <section id="panel-login" class="hero is-primary is-fullheight">
            <div class="hero-body">
                <div class="container">
                    <div class="columns is-centered">
                        <div class="column is-full-mobile is-7-tablet is-6-desktop is-5-widescreen">
                            <div class="box">
                                <message ref="message"></message>
                                <div class="content">
                                    <p class="has-text-justified">
                                        Jeśli chcesz zostać naszym klientem to zarejestruj się!
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
                                        <label class="label">Imię</label>
                                        <div class="control has-icons-left">
                                            <input v-model="name" type="text" placeholder="Podaj login/email" class="input" required>
                                            <span class="icon is-small is-left">
                                            <i class="fa fa-envelope"></i>
                                        </span>
                                        </div>
                                        <p class="help is-danger">{{errors.name}}</p>
                                    </div>
                                    <div class="field">
                                        <label class="label">Nazwisko</label>
                                        <div class="control has-icons-left">
                                            <input v-model="surname" type="text" placeholder="Podaj login/email" class="input" required>
                                            <span class="icon is-small is-left">
                                            <i class="fa fa-envelope"></i>
                                        </span>
                                        </div>
                                        <p class="help is-danger">{{errors.surname}}</p>
                                    </div>
                                    <div class="field">
                                        <label class="label">Telefon</label>
                                        <div class="control has-icons-left">
                                            <input v-model="phone" type="text" placeholder="Podaj login/email" class="input" required>
                                            <span class="icon is-small is-left">
                                            <i class="fa fa-envelope"></i>
                                        </span>
                                        </div>
                                        <p class="help is-danger">{{errors.phone}}</p>
                                    </div>
                                    <div class="field">
                                        <label class="label">Hasło</label>
                                        <div class="control has-icons-left">
                                            <input v-model="pass1" type="password" placeholder="Podaj hasło" class="input" required>
                                            <span class="icon is-small is-left">
                                            <i class="fa fa-lock"></i>
                                        </span>
                                        </div>
                                        <p class="help is-danger">{{errors.pass1}}</p>
                                    </div>
                                    <div class="field">
                                        <label class="label">Hasło</label>
                                        <div class="control has-icons-left">
                                            <input v-model="pass2" type="password" placeholder="Podaj hasło" class="input" required>
                                            <span class="icon is-small is-left">
                                            <i class="fa fa-lock"></i>
                                        </span>
                                        </div>
                                        <p class="help is-danger">{{errors.pass2}}</p>
                                    </div>
                                    <div class="field is-grouped">
                                        <div class="control">
                                            <button v-on:click="register()" v-on:keyup.enter="register()" class="button is-success">
                                                Zarejestruj
                                            </button>
                                        </div>
                                        <div class="control">
                                            <router-link to="/login" class="button is-link">
                                                Przejdz do logowania
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
    import Message from "./assets/message.vue";

    export default {
        name: "loginView",
        components: {Message},
        data(){
            return {
                email: "",
                pass1: "",
                pass2: "",
                name: "",
                surname: "",
                phone: "",
                errors: {
                    email: "",
                    pass1: "",
                    pass2: "",
                    name: "",
                    surname: "",
                    phone: ""
                }
            }
        },

        methods: {
            validate: function(){
                let ret = true
                let keys = Object.keys(this.errors)
                for(let k of keys){
                    this[k] = this[k].trim()
                    if(this[k] == ""){
                        ret = false
                        this.errors[k] = "Pole nie może być puste"
                    }
                    else {
                        this.errors[k] = ""
                    }
                }


                if(this.errors.email == "" && !this.validateEmail(this.email)) {
                    ret = false
                    this.errors.email = "Niepoprawny email"
                }

                if(this.pass1 != this.pass2 && this.errors.pass1 == this.errors.pass1 && this.errors.pass1 == "") {
                    ret = false
                    this.errors.pass1 = "Hasła nie są identyczne"
                    this.errors.pass2 = "Hasła nie są identyczne"
                }

                return ret
            },

            validateEmail: function (email) {
                const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                return re.test(String(email).toLowerCase());
            },

            register: function(){
                if(this.validate() == false) return

                axios.post('/register', {
                    username: this.email.replace(/ /g, ''),
                    password: this.pass1,
                    name: this.name,
                    surname: this.surname,
                    phone: this.phone,
                    email: this.email,
                }).then(m => {
                    console.log("m.data" ,m.data)
                    if(m.data.error) {
                        if(m.data.error == "user exists")
                            this.$refs.message.showDanger("","Konto o podanym emailu już istnieje")
                        else
                            this.$refs.message.showDanger("",m.data.error)
                    }else {
                        this.$refs.message.showSuccess("", "Konto zostało założone. Zaloguj się")

                        let keys = Object.keys(this.errors)
                        for(let k of keys) {
                            this[k] = ""
                        }
                    }
                }).catch(e => {
                    console.error(e)
                    this.$refs.message.showDanger("",e.toString())
                })

            }
        }
    }
</script>

<style scoped>

</style>