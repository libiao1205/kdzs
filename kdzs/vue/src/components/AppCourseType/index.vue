<template>
    <div class="course-title mt10">
        <span class="course-title-txt">
            <b class="course-title-txt-line_1"></b>
            <strong>{{title}}</strong>
            <b class="course-title-txt-line_2"></b>
        </span>
        <!--<a class="more finger" @click="getAllCourse(link)">{{linkTxt}}<i :class="icon"></i></a>-->
    </div>
</template>

<script>
    export default {
        name: "AppCourseType",
        props: {
            title: {
                type: String,
                default: ''
            },
            linkTxt: {
                type: String,
                default: ''
            },
            icon: {
                type: String,
                default: 'el-icon-d-arrow-right'
            },
            link: {
                type: String,
                default: ''
            }
        },
        methods:{
        }
    }
</script>

<style lang="scss">
    .course-title{
        text-align: center;
        height: 40px;
        line-height: 40px;
        position: relative;
    }
    .course-title-txt {
        position: relative;
        font-size: 26px;
        font-weight: bold;
        color: #425c72;
        letter-spacing: 2px;
        display: inline-block;
    }
    .course-title-txt-line_1 {
        margin-right: 25px;
    }
    .course-title-txt-line_2 {
        margin-left: 25px;
    }
    .course-title-txt strong {
        float: left;
        margin-top: 3px;
    }
    .course-title-txt b{
        display: inline-block;
        float: left;
        width: 23px;
        height: 3px;
        background: #d1dade;
        top: 15px;
        margin-top: 20px;
    }
    .more{
        color: #4e667b;
        margin-top: 10px;
        position: absolute;
        top: 0;
        right: 7px;
    }
    .more:hover{
        color: #63acff;
    }
</style>
