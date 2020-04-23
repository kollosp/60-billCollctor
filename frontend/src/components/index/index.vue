<template>
    <div class="has-background-image">
        <img class="background-image" src="../../images/bg.jpg">
        <section id="panel-login" class="hero has-background-brand-yellow is-fullheight">
            <div class="hero-body">
                <div class="container">
                    <div class="columns is-centered">
                        <div class="column is-7-tablet is-6-desktop is-5-widescreen">
                            <div class="box content">

                                <h1 class="title has-text-grey-darker">Panel klienta</h1>

                                <p class="has-tex   t-justified">
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
                                    </tr>

                                </table>

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
        name: "index",
        data(){
            return {
                bills: []
            }
        }
    }
</script>

<style scoped>

</style>