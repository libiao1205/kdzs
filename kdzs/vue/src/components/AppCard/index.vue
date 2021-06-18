<template>
    <div style="flex-wrap:wrap;display: flex;">
        <template v-for="(item,index) in data">
            <el-card shadow="hover" style="width: 296px;box-sizing: border-box;padding: 10px;margin: 10px;" class="finger bg-w" :body-style="{ padding: '0px' }" @click.native="showClassInfo(item.id)">
                <div style="position: relative;width: 100%;height: 200px;">
                    <img v-bind:src=item.image  :onerror="item.noImage" class="img-200" >
                    <div class="subscript" v-if="false">HOT</div>
                </div>

                <div class="pd10">
                    <el-tooltip effect="dark" :content="item.name" placement="bottom">
                        <div class="course-summary-text-title">{{item.name}}</div>
                    </el-tooltip>
                    <div class="course-summary-address">
                        <i class="el-icon-time"></i>&nbsp;{{getDateTimeRangeName(item.starttime, item.endtime)}}
                    </div>
                    <el-tooltip effect="dark" :content="item.address" placement="bottom">
                        <div class="course-summary-address">
                            <i class="el-icon-location"></i> {{item.address}}
                        </div>
                    </el-tooltip>
                    <div class="course-summary-address">
                        <i class="el-icon-time"></i>&nbsp;{{item.hour}}课时
                    </div>
                </div>
            </el-card>
        </template>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex';
    export default {
        name: "AppCard",
        props: {
            gap: {
                type: Number,
                default: 20
            },
            data: {
                type: Array,
                default: []
            },
            span: {
                type: Number,
                default: 8
            }
        },
        computed: {
            ...mapGetters({
                getDateTimeRangeName: 'dateInfo/getDateTimeRangeName',
            }),
        },
        methods:{
            showClassInfo(id) {
                this.$router.push({ path: "classInfo", query: { courseId: id }})
            },
        }
    }
</script>

<style lang="scss">
    .course-summary-text-title{
        font-size: 16px;
        margin-bottom: 5px;
        color: #63acff;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
    .course-summary-address{
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        color: #999;
        margin-bottom: 3px;
    }
    .img-200{
        height: 200px;
        width: 100%;
    }
    .subscript {
        color: #fff;
        height: 30px;
        width: 100px;
        position: absolute;
        right: -36px;
        top:0px;
        text-align: center;
        line-height: 30px;
        background-color: #f56c6c;
        -moz-transform: rotate(45deg);
        -webkit-transform: rotate(45deg);
        -o-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        transform: rotate(45deg);
        }
</style>
