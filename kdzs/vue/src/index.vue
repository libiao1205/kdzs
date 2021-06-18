<template>
    <div id="app" class="sys-app">
        <router-view>
        </router-view>
    </div>
</template>

<script>
    import setTheme from "@/util/setTheme"
    export default {
        // TODO: 全局状态加载及变更。请根据实际情况改写
        beforeMount(){

        },
        // 初次加载时，可通过接口获取用户的主题信息，或者通过按钮触发，或者直接加载默认主题
        mounted() {
            this.$nextTick(() => {
                setTheme("theme-default")
                this.$store.commit("setThemeColor", "theme-default");
            });
        },

    }
</script>
