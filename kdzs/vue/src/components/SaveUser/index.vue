<template>
    <el-container>
        <el-dialog title="" :visible.sync="formPassword.visible" :close-on-click-modal="true" width="500px">
            <el-form ref="form" label-width="80px">
                <el-form-item label="姓名" v-if="userId === 0">
                    <el-input v-model="name"></el-input>
                </el-form-item>
                <el-form-item label="手机号" v-if="userId === 0">
                    <el-input v-model="phone"></el-input>
                </el-form-item>
                <el-form-item label="角色">
                    <el-checkbox label="管理员" v-model="adminBox" ></el-checkbox>
                    <el-checkbox label="填写人员" v-model="fillBox" ></el-checkbox>
                    <el-checkbox label="查阅人员" v-model="lookBox" ></el-checkbox>
                </el-form-item>
                <el-form-item label="分校">
                    <el-select v-model="schoolId" placeholder="请选择">
                        <el-option v-for="item in schoolList" :key="item.schoolId" :label="item.name" :value="item.schoolId"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer fxcc-c">
                <div>
                    <el-button type="primary" @click.native="commitInfo" >确定</el-button>
                    <el-button @click.native="formPassword.visible = false">取消</el-button>
                </div>
            </div>
        </el-dialog>
    </el-container>
</template>

<script>
    import axios from '@/util/ajax';
    import { Event } from '../../util/event.js'
    export default {
        name: "saveUser",
        data(){
            return{
                formPassword:{
                    visible: false,
                    loading: false,
                },
                name:'',
                phone:'',
                password:'',
                roles:[],
                schoolId:'',
                userId:0,//0：添加数据，>0:修改数据
                adminBox:false,
                fillBox:false,
                lookBox:false,
                schoolList:[],
            }
        },
        methods:{
            showPasswordExceedMsg(id){
                this.userId = id ;
                this.findAllSchool();
                this.createFomr();
                if(this.userId > 0){
                    let params = {id:id}
                    axios({
                        url: '/user/findOneUser',
                        method: 'get',
                        params: params
                    }).then(res => {
                        if(res.c === 0){
                            let data = res.ctt;
                            for(let i = 0; i < data.roleIds.length; i++){
                                if(data.roleIds[i] === 1){
                                    this.adminBox = true;
                                }else if(data.roleIds[i] === 2){
                                    this.fillBox = true;
                                }else if(data.roleIds[i] === 3){
                                    this.lookBox = true;
                                }
                            }
                            this.schoolId = data.schoolId
                            this.formPassword.visible = true;
                        }
                    });
                }else{
                    this.formPassword.visible = true;
                }
            },
            createFomr(){
                this.adminBox = false;
                this.fillBox = false;
                this.lookBox = false;
                this.name = "";
                this.phone = "";
                this.schoolId = '';
            },
            findAllSchool(){
                axios({
                    url: '/school/recruit/schoolFindAll',
                    method: 'get',
                }).then(res => {
                    if(res.c === 0){
                        this.schoolList = res.ctt;
                        this.schoolList.splice(0,1)
                    }
                });

            },
            commitInfo(){
                this.formPassword.visible = false;
                if(this.adminBox && (this.fillBox || this.lookBox)){
                    this.$message({
                        message: '管理员权限只能独立分配',
                        type: 'error'
                    });
                    return;
                }
                let roles = [];
                if(this.adminBox){
                    roles.push(1)
                }
                if(this.fillBox){
                    roles.push(2)
                }
                if(this.lookBox){
                    roles.push(3)
                }
                if(this.userId > 0){
                    schoolId:this.schoolId
                    let data = {roleIds:roles,id:this.userId,schoolId:this.schoolId};
                    data = JSON.stringify(data);
                    axios({
                        url: '/user/updateUser',
                        method: 'post',
                        data: data
                    }).then(res => {
                        if(res.c === 0){
                            Event.$emit('findAll', '')
                            this.$message({
                                message: '修改成功',
                                type: 'success'
                            });
                        }
                    });
                }else{
                    let data = {name:this.name,loginName:this.phone,schoolId:this.schoolId,roleIds:roles};
                    data = JSON.stringify(data);
                    axios({
                        url: '/user/saveUser',
                        method: 'post',
                        data: data
                    }).then(res => {
                        if(res.c === 0){
                            Event.$emit('findAll', '')
                            this.$message({
                                message: '添加成功',
                                type: 'success'
                            });
                        }else{
                            this.$message({
                                message: res.msg,
                                type: 'error'
                            });
                        }
                    });
                }
            }
        }
    }
</script>

<style scoped>

</style>
