<template>
    <div class="main">
        <el-row :span="24" v-for="item in this.dataSource" class="content fx-l" style="box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)">
            <el-col :span="6" class="border font fxl pdl10 pdr10" style="font-family:SourceHanSansSC; font-weight:600; font-size:16px; color:rgb(16, 16, 16); font-style:normal; letter-spacing:0px; line-height:29px; text-decoration:none;">
                <div class="fxb">
                    <div>李四</div>
                    <div>{{item.createTime}}</div>
                    <div>提交</div>
                </div>
            </el-col>
            <el-col :span="12" class="border font fxcc-c">
                <div>内容：{{item.exampleExplain }}</div>
                <el-popover
                    placement="bottom"
                    width="400"
                    @show="findExampleLogById(item.exampleId)"
                    trigger="click">
                    <div class="block">
                        <el-timeline :reverse="reverse">
                            <el-timeline-item
                                v-for="(activity, index) in activities"
                                :key="index"
                                :icon="activity.icon"
                                :type="activity.type"
                                :color="activity.color"
                                :size="activity.size"
                                :timestamp="activity.timestamp">
                                <span style='color: #63acff'>{{activity.executeName}}</span><br>
                                <!--处理内容 鼠标悬浮显示全部内容-->
                                <el-popover
                                    placement="top-start"
                                    width="200"
                                    trigger="hover"
                                    :content="activity.opinion">
                                    <div class="word-hidden finger" slot="reference">{{activity.opinion}}</div>
                                </el-popover>
                                <span v-html=""></span>
                            </el-timeline-item>
                        </el-timeline>
                    </div>
                    <el-button style="border: 0px" slot="reference">详情</el-button>
                </el-popover>
            </el-col>
            <el-col :span="6" class="border border-right font fxcc-c">
                <div>
                    <el-popover v-if="item.nodeType === 0"
                        placement="bottom"
                        width="250"
                        trigger="click">
                        <el-input
                            type="textarea"
                            placeholder="请输入内容"
                            v-model="opinion"
                            maxlength="100"
                            show-word-limit
                        >
                        </el-input>
                        <div class="fxcc-x pdt10">
                            <el-button type="primary" size="small" @click="executeExample(item.step,item.exampleId)">通过</el-button>
                            <el-button type="danger" size="small" >驳回</el-button>
                        </div>
                        <el-button slot="reference">审批</el-button>
                    </el-popover>

                    <el-popover v-if="item.nodeType === 1"
                                placement="bottom"
                                width="250"
                                trigger="click">
                        <el-input
                            type="textarea"
                            placeholder="请输入内容"
                            v-model="opinion"
                            maxlength="100"
                            show-word-limit
                        >
                        </el-input>
                        <div class="fxcc-x pdt10">
                            <el-button type="primary" @click="executeExample(item.step,item.exampleId)" size="small" >执行成功</el-button>
                            <el-button type="danger" size="small" >执行失败</el-button>
                        </div>
                        <el-button slot="reference">执行</el-button>
                    </el-popover>
                </div>
            </el-col>
        </el-row>
        <el-button type="primary" @click="executeExample(1,1)" size="small" >执行成功</el-button>
    </div>
</template>
<script>

import { mapState} from 'vuex';
import axios from '@/util/ajax';
export default {
    name:'page',
    computed: {
        ...mapState({

        }),
    },
    mounted(){
        this.findExample("");
    },
    data() {
        return {
            activeNames: ['1'],
            activities: [],
            dataSource: [],
            opinion: '',
            reverse: true
        }
    },
    methods:{
        findExampleLogById(exampleId) {
            this.activities = [];
            axios({
                url: '/gd/myExamineExampleLogById',
                method: 'get',
                params:{exampleId:exampleId},
            }).then(res => {
                let myNode = {
                    executeName: '我',
                    opinion: '',
                    timestamp: '',
                    size: 'large',
                    type: 'danger ',//el-icon-success
                    icon: 'el-icon-remove'
                }
                this.activities.push(myNode)
                if(res.c === 0 && res.ctt != null){
                    for(let index = res.ctt.length-1; index >= 0; index--){
                        let node = {
                            executeName: '',
                            opinion: '',
                            timestamp: '',
                            size: 'large',
                            type: 'primary',//el-icon-success  el-icon-success
                            icon: 'el-icon-info'
                        }
                        let ctt = res.ctt[index];
                        node.executeName = ctt.executeName;
                        node.opinion = ctt.opinion;
                        node.timestamp = ctt.createTime;
                        this.activities.push(node)
                    }
                }
            });
        },
        findExample(){
            axios({
                url: '/gd/myExamineExample',
                method: 'get',
            }).then(res => {
                if(res && res.c === 0 && res.ctt != null){
                    this.dataSource = res.ctt;
                }
            });
        },
        executeExample(step,exampleId){
            let data = {
                step: step,
                exampleid: exampleId,
                opinion:this.opinion
            }
            data = JSON.stringify(data);
            axios({
                url: '/exampleLog/saveEs',
                method: 'post',
            }).then(res => {
                /*this.$message({
                    message: res.msg,
                    type: 'success'
                });*/
            });
        }
    }
}
</script>

<style scoped>
.content{
    height:70px;
}
.font{
    font-family:SourceHanSansSC; font-weight:400; font-size:14px; color:rgb(16, 16, 16); font-style:normal; letter-spacing:0px; line-height:29px; text-decoration:none;
}
</style>
