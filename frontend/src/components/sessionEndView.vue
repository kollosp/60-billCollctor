<template>
    <div class="has-background-image">
        <img class="background-image" src="../images/bg.jpg">
        <section id="panel-login" class="hero has-background-brand-yellow is-fullheight">
            <div class="hero-body">
                <div class="container">
                    <div class="columns is-centered">
                        <div class="column is-7-tablet is-6-desktop is-5-widescreen">
                            <div class="box content">

                                <h1 class="title has-text-grey-darker">Sesja wygasła :(</h1>

                                <p class="has-text-justified">
                                    Niestety twoja sesja uległa przedawnieniu. Zaloguj się ponownie aby dalej przeglądać swoje instalacje.
                                </p>


                                <button class="button is-link" v-on:click="gotoLogin">Zaloguj się</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</template>

<script>
    export default {
        name: "sessionEndView",
        methods: {
            gotoLogin: function () {
                if(this.$route.query.d) {
                    this.$router.push({path: "/login", query: {d: this.$route.query.d}}).catch(err => {})
                }else{
                    this.$router.push('/login').catch(err => {})
                }
            }
        }
    }
</script>

<style scoped>

</style>