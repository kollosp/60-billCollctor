<template>
    <div class="has-background-image">
        <img class="background-image" src="../images/bg.jpg">

        <section class="hero is-primary is-fullheight">
            <div class="hero-body">
                <div class="container">
                    <div class="columns is-centered">
                        <div class="column is-8-tablet is-6-desktop is-5-widescreen">
                            <div class="box">
                                <message ref="message"></message>
                                <p class="has-text-justified content">
                                    <Strong class="has-text-danger">Uwaga!</Strong> W celu zresetowania hasła podaj <strong>takie same dane</strong>
                                    jak podczas rejestracji, <strong class="has-text-danger">telefon kontaktowy i adres email</strong>
                                    następnie wciśnij przycisk "<i>Resetuj</i>". Wtedy zostanie
                                    do Ciebie wysłany <strong>email</strong> z nowym hasłem, którym będzie można zalogować się na konto.
                                </p>
                                <div class="form">
                                    <div class="field">
                                        <label class="label">Telefon</label>
                                        <div class="control has-icons-left">
                                            <input v-model="phone" type="text" placeholder="Podaj telefon" class="input" required>
                                            <span class="icon is-small is-left">
                                            <i class="fa fa-phone"></i>
                                        </span>
                                        </div>
                                        <p class="help is-danger">{{errors.phone}}</p>
                                    </div>
                                    <div class="field">
                                        <label class="label">Email</label>
                                        <div class="control has-icons-left">
                                            <input v-model="email" type="text" placeholder="Podaj email" class="input" required>
                                            <span class="icon is-small is-left">
                                            <i class="fa fa-envelope"></i>
                                        </span>
                                        </div>
                                        <p class="help is-danger">{{errors.email}}</p>
                                    </div>
                                    <div class="field">
                                        <div class="buttons">
                                            <button v-on:click="reset" class="button is-link">
                                                Resetuj
                                            </button>
                                            <router-link to="/login" class="button is-success">
                                                Zaloguj
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
    import Message from "./assets/message.vue"

    export default {
        name: "pwdResetView",
        components: {Message},
        data() {
            return {
                email: "",
                name: "",
                surname: "",
                phone: "",

                errors: {
                    email: "",
                    name: "",
                    surname: "",
                    phone: "",
                }
            }
        },
        methods: {
            reset: function () {
                axios.post("/usermanagementopen/pwdreset", {
                    email: this.email.replace(/ /g, ''),
                    //name: this.name,
                    //surname: this.surname,
                    phone: this.phone.replace(/ /g, ''),
                }).then(m => {
                    if(m.data.error){
                        console.error(m.data.error)
                        this.$refs.message.showDanger("",m.data.error)
                    }else{
                        console.log(m.data)
                        if(m.data.status == "correct data"){
                            this.$refs.message.showSuccess("","Resetowanie hasła przebiegło pomyślnie. Nowe hasło zostało wysłane na podany email")
                        }else{
                            switch (m.data.code) {
                                case 1:
                                    this.$refs.message.showDanger("","Nie udało się dopasować adresu email do istniejącego konta."+
                                        " Upewnij się że został wprowadzony poprawnie. Jeśli wciąż masz problem skontaktuj się z administratorem")
                                    break;
                                case 2:
                                    this.$refs.message.showDanger("","Szukane konto zostało znalezione, lecz nie udało się dopasować numeru telefonu." +
                                        " Upewnij się, że wprowadzasz poprawny numer telefonu. Spróbuj z numerem kierunkowym i bez niego.")
                                    break;
                            }

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