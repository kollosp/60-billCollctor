<template>
    <div class="has-background-image">
        <img class="background-image" src="../../images/bg.jpg">
        <section id="panel-login" class="hero has-background-brand-yellow is-fullheight">
            <div class="hero-body">
                <div class="container">
                    <div class="columns is-centered">
                        <div class="column is-7-tablet is-6-desktop is-5-widescreen">
                            <div class="box content">

                                <h1 class="title has-text-grey-darker">Strona tytułowa</h1>

                                <p class="has-text-justified">
                                    Zapraszamy do zalogowania się do panelu rachunkow
                                </p>

                                <router-link to="/login" class="button is-link">Zaloguj</router-link>
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
        name: "index"
    }
</script>

<style scoped>

</style>