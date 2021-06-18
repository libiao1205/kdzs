<template>
    <div>
        <div class="top wow fadeInUp">
            <el-menu
                default-active="2"
                class="el-menu-vertical-demo"
                @open="handleOpen"
                @close="handleClose"
                @select="handleSelect"
                background-color="#545c64"
                text-color="#fff"
                active-text-color="#ffd04b">
                <!--<el-submenu index="1">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>导航一</span>
                    </template>
                    <el-menu-item-group>
                        <template slot="title">分组一</template>
                        <el-menu-item index="1-1">选项1</el-menu-item>
                        <el-menu-item index="1-2">选项2</el-menu-item>
                    </el-menu-item-group>
                    <el-menu-item-group title="分组2">
                        <el-menu-item index="1-3">选项3</el-menu-item>
                    </el-menu-item-group>
                    <el-submenu index="1-4">
                        <template slot="title">选项4</template>
                        <el-menu-item index="1-4-1">选项1</el-menu-item>
                    </el-submenu>
                </el-submenu>
                <el-menu-item index="2">
                    <i class="el-icon-menu"></i>
                    <span slot="title">导航二</span>
                </el-menu-item>-->
                <el-menu-item index="/home">
                    <i class="el-icon-s-home"></i>
                    <span slot="title">首页</span>
                </el-menu-item>
                <el-menu-item index="/sysUser" v-if="isAdmin()">
                    <i class="el-icon-setting"></i>
                    <span slot="title">后台管理</span>
                </el-menu-item>
            </el-menu>
        </div>
        <div class="headMenu">
            <div style="float:right;margin-right: 1%;margin-top: 10px">
                <el-button icon="el-icon-switch-button" @click="quit">退出</el-button>
            </div>
            <div style="float:right;margin-right: 5%;margin-top: 15px">
                <span>角色：{{getRoleName()}}</span>
            </div>
            <div style="float:right;margin-right: 5%;margin-top: 15px">
                <span>欢迎：{{getUserName()}}</span>
            </div>
        </div>
        <div class="fxc fx-auto w100"  >
            <router-view class="w100" style="margin-bottom: 50px;margin-left:250px;width:85%;margin-top: 80px;"></router-view>
        </div>
        <div class="fxcc-c w100 bg-w bot wow fadeInUp">
            <div style="text-align: center">
                上海开放大学<br>
            </div>
        </div>
        <el-backtop :bottom="40" exclude="product">
        </el-backtop>
    </div>
</template>
<script>
    import { mapActions, mapGetters,mapState } from 'vuex';
    import Auth from '@/util/auth'
    export default {
        name: "layout",
        computed: {
            ...mapState({
                username: state => state.user.name,
                operation: state => state.user.operation
            }),
            ...mapGetters({
                getRoleName: 'user/getRoleName',
                getUserName: 'user/getUserName',
                isAdmin: 'user/isAdmin',
            }),
        },
        created() {
            if(!sessionStorage.getItem(Auth.loginKey)){
                this.$router.push("/login")
            }
        },
        data() {
            return {
                isCollapse: true,
            }
        },
        methods: {
            ...mapActions({
                logout: 'auth/logout',
            }),
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
                this.$router.push(key);
            },
            handleSelect(key, keyPath){
                console.log(key, keyPath);
                this.$router.push(key);
            },
            quit(){
                this.logout().then(()=>{
                    this.$router.push("/login")
                });
            }
        }
    }
</script>

<style lang="scss">
    .menuHeight{
        height:120px;
    }
    .top{
        position:fixed;
        z-index:1000;
        top:0;
        line-height: 60px;
    }
    .bot{
        position:fixed;
        bottom: 0;
        height: 40px;
        z-index:900;
    }
    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 200px;
        height: 1300px;
        min-height: 600px;
    }
    .headMenu{
        position:fixed;
        border:1px solid lightgrey;
        background-color: white;
        width:100%;
        height:60px;
        top:0px;
        z-index:900;
    }
</style>
