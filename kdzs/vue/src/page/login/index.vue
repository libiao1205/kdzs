<template>
    <div class="sys-login fxcc-c">
        <div class="login-area">
            <div class="logo">
                <img src="../../assets/image/logo.png">
            </div>
            <div class="form-group mr-t-20">
                <el-form :model="loginForm" :rules="loginRules" ref="loginForm" label-position="left" label-width="0px">
                    <el-form-item prop="name">
                        <el-input v-model="loginForm.name" type="text" placeholder="请输入手机号"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input v-model="loginForm.password" type="password"  @keyup.enter.native = "submitForm" placeholder="请输入密码"></el-input>
                    </el-form-item>
                    <el-button style="width:355px" type="primary" @click="submitForm()">登录</el-button>
                </el-form>
                <div v-if="sysMsg" class="err-msg">{{sysMsg}}</div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'

export default {
    computed: {
        ...mapGetters({
            getDefaultUrl: 'user/getDefaultUrl',
        }),
    },
    data() {
        return {
            loginForm: {
                name: '',
                password: '',
                captcha: ''
            },
            loginRules: {
                name: [
                    {required: true, message: '请输入用户名', trigger: 'blur'}
                ],
                password :[
                    {required: true, message: '请输入密码', trigger: 'blur'}
                ],
                captcha: [
                    {required: false, message: '', trigger: 'blur'}
                ]
            },
            captcha: {
                show: false,
                src: ''
            },
            sysMsg: ''
        }
    },
    methods: {
        ...mapActions({
            login: 'auth/loginByEmail',
            loadLang: 'loadLang'
        }),
        submitForm(){
            this.$refs.loginForm.validate((valid) => {
                if (valid) {
                    let data = {
                        username: this.loginForm.name,
                        password: this.loginForm.password
                    }
                    data = JSON.stringify(data);
                    this.login(data).then(res => {
                        if(res.c===0){
                            this.$router.push("/home");
                        } else {
                            this.sysMsg = res.msg;
                        }
                    })
                } else {
                    return false
                }
            });
        },
    }
}
</script>
<style>
    .sys-login{
        position: absolute;
        top:50%;
        left:50%;
        transform: translate(-50%,-50%);
        border:0px solid gray;
        width:30%;
        height:30%;
    }
</style>
