<template>
    <nav class="pagination is-centered" role="navigation" aria-label="pagination">
        <button class="pagination-previous" v-on:click="previous">Poprzednia</button>
        <button class="pagination-next" v-on:click="next">Następna</button>
        <ul class="pagination-list">
            <template v-if="this.currentPage - 1 > 1">
                <li>
                    <a class="pagination-link" v-on:click="gotoPage(1)" >1</a>
                </li>
                <li>
                    <span class="pagination-ellipsis">&hellip;</span>
                </li>
            </template>

            <li v-if="this.currentPage > 1">
                <a class="pagination-link" v-on:click="gotoPage(currentPage - 1)">{{this.currentPage - 1}}</a>
            </li>


            <li>
                <a class="pagination-link is-current" v-bind:aria-label="'Goto page' + (currentPage)" aria-current="page">{{this.currentPage}}</a>
            </li>

            <li  v-if="this.currentPage < this.pageCount">
                <a class="pagination-link" v-on:click="gotoPage(currentPage + 1)" v-bind:aria-label="'Goto page' + (currentPage + 1)">{{this.currentPage + 1}}</a>
            </li>
            <template v-if="this.currentPage + 1 < this.pageCount">
                <li>
                    <span class="pagination-ellipsis">&hellip;</span>
                </li>
                <li>
                    <a class="pagination-link" v-on:click="gotoPage(pageCount)" v-bind:aria-label="'Goto page' + (pageCount)">{{this.pageCount}}</a>
                </li>
            </template>
        </ul>
    </nav>
</template>

<script>
    export default {
        name: "pagination.vue",
        props: ["elements", "elementsPerPage"],

        data: function(){
            return {
                currentPage: 1,
            }
        },

        mounted() {

        },

        methods: {
            previous: function () {
                if(this.currentPage > 1){
                    this.currentPage--

                    this.$emit("pageChanged", (this.currentPage-1)*this.elementsPerPage+1, this.elementsPerPage)
                }
            },

            next: function () {
                if(this.currentPage < this.pageCount){
                    this.currentPage++

                    this.$emit("pageChanged", (this.currentPage-1)*this.elementsPerPage+1, this.elementsPerPage)
                }
            },
            gotoPage: function (p) {
                if(p <= this.pageCount && p >= 1){
                    this.currentPage = p
                    //emit signal. First element and count
                    this.$emit("pageChanged", (this.currentPage-1)*this.elementsPerPage+1, this.elementsPerPage)
                }
            }
        },

        computed:{
            pageCount: function(){
                return Math.ceil(this.elements / this.elementsPerPage)
            }
        }
    }
</script>

<style scoped>

</style>