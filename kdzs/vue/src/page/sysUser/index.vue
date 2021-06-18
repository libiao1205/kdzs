<template>
    <div>
        <el-button type="primary" @click="saveUser" >添加用户</el-button>
        <div class="demo-input-suffix mr-t-10">
            <el-input
                size="min"
                @input="findAll"
                placeholder="手机号/姓名/分校/角色"
                prefix-icon="el-icon-search"
                v-model="key">
            </el-input>
        </div>
        <el-table
            v-loading="loading"
            height="730"
            :stripe="true"
            :fit="true"
            :data="tableData"
            :default-sort = "{prop: 'num', order: 'descending'}"
            style="width: 100%">
            <el-table-column
                prop="num"
                label="序号"
                sortable
            >
                <template slot-scope="scope">
                    <span >{{ scope.row.lineNumber }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="手机号"
                >
                <template slot-scope="scope">
                    <span>{{ scope.row.loginName }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="姓名"
                >
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                        <p>姓名: {{ scope.row.name }}</p>
                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium">{{ scope.row.name }}</el-tag>
                        </div>
                    </el-popover>
                </template>
            </el-table-column>
            <el-table-column
                label="分校"
                >
                <template slot-scope="scope">
                    <span>{{ scope.row.schoolName }}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        @click="resetOpen(scope.$index, scope.row)">重置密码</el-button>
                    <el-button
                        size="mini"
                        @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <SaveUser ref="userBox"></SaveUser>
    </div>
</template>
<script>

import { mapState} from 'vuex';
import axios from '@/util/ajax';
import SaveUser from "../../components/SaveUser/index";
import { Event } from '../../util/event.js'
export default {
  name:'sysUser',
    components: {SaveUser},
    computed: {
          ...mapState({

          }),
      },
      mounted(){
          let ther = this;
          Event.$on('findAll', function (msg) {
              ther.findAll("");
          })
          this.findAll("");
      },
      data() {
          return {
              tableData: [],
              key:'',
              loading:true,
          }
      },
      methods: {
          resetOpen(index, row) {
              this.$prompt('请输入新密码', '重置密码', {
                  confirmButtonText: '确定',
                  cancelButtonText: '取消',
                  inputPattern: /^[^.]+$/,//不能为空一位以上
                  inputErrorMessage: '密码不能为空'
              }).then(({ value }) => {
                  let data = {rePassword:value,userId:row.id}
                  axios({
                      url: '/auth/resetPassword',
                      method: 'post',
                      data: data
                  }).then(res => {
                      if(res.c === 0){
                          this.$message({
                              message: '重置成功',
                              type: 'success'
                          });
                      }
                  });
              }).catch(() => {
                  this.$message({
                      type: 'info',
                      message: '取消输入'
                  });
              });
          },
          saveUser(){
              this.$refs.userBox.showPasswordExceedMsg(0);

          },
          handleEdit(index, row) {
              this.$refs.userBox.showPasswordExceedMsg(row.id);

          },
          handleDelete(index, row) {
              this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
                  confirmButtonText: '确定',
                  cancelButtonText: '取消',
                  type: 'warning'
              }).then(() => {
                  this.deleteUser(row.id);
              }).catch(() => {});

         },
          findAll(key){
              this.loading = true;
              let params = {key:key}
              axios({
                  url: '/user/findAllUser',
                  method: 'get',
                  params: params
              }).then(res => {
                  if(res.c === 0){
                      this.tableData = res.ctt;
                      this.loading = false;
                  }
              });
          },
          deleteUser(id){
              let params = {id:id}
              axios({
                  url: '/user/deleteUser',
                  method: 'get',
                  params: params
              }).then(res => {
                  if(res.c === 0){
                      this.$message({
                          message: '删除成功',
                          type: 'success'
                      });
                      this.findAll(this.key);
                  }
              });
          },
      }

}
</script>

<style scoped>

</style>
