<template>
    <infinite-loading ref="infinite" slot="append" @infinite="nextPage" force-use-infinite-wrapper=".el-table__body-wrapper">
        <span slot="no-more"></span>
    </infinite-loading>
</template>

<script>
    export default {
        name: "AppInfinite",
        props: {
            loadData: {
                type: Function,
                default: []
            },
        },
        data() {
            return {
                pageData: {
                    size: 20,
                    page: 0,
                }
            }
        },
        methods: {
            nextPage(){
                this.pageData.page += 1;
                this.loadData(this.pageData);
            },
            refresh(){
                this.pageData.page = 0;
                this.$refs.infinite.stateChanger.reset()
            },
            loadDone(length) {
                if (length === 0){
                    this.$refs.infinite.stateChanger.complete();
                }
                else{
                    this.$refs.infinite.stateChanger.loaded();
                }
            }
        }
    }
</script>
