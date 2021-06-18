(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["components/uni-badge/uni-badge"],{"0d14":function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var c={name:"UniBadge",props:{type:{type:String,default:"default"},inverted:{type:Boolean,default:!1},text:{type:[String,Number],default:""},size:{type:String,default:"normal"}},data:function(){return{badgeStyle:""}},watch:{text:function(){this.setStyle()}},mounted:function(){this.setStyle()},methods:{setStyle:function(){this.badgeStyle="width: ".concat(8*String(this.text).length+12,"px")},onClick:function(){this.$emit("click")}}};e.default=c},4665:function(t,e,n){"use strict";n.r(e);var c=n("5c1a"),u=n("5c00");for(var a in u)"default"!==a&&function(t){n.d(e,t,(function(){return u[t]}))}(a);n("c20c");var i,r=n("f0c5"),o=Object(r["a"])(u["default"],c["b"],c["c"],!1,null,"4a7ccc8f",null,!1,c["a"],i);e["default"]=o.exports},"5c00":function(t,e,n){"use strict";n.r(e);var c=n("0d14"),u=n.n(c);for(var a in c)"default"!==a&&function(t){n.d(e,t,(function(){return c[t]}))}(a);e["default"]=u.a},"5c1a":function(t,e,n){"use strict";var c,u=function(){var t=this,e=t.$createElement;t._self._c},a=[];n.d(e,"b",(function(){return u})),n.d(e,"c",(function(){return a})),n.d(e,"a",(function(){return c}))},b569:function(t,e,n){},c20c:function(t,e,n){"use strict";var c=n("b569"),u=n.n(c);u.a}}]);
;(global["webpackJsonp"] = global["webpackJsonp"] || []).push([
    'components/uni-badge/uni-badge-create-component',
    {
        'components/uni-badge/uni-badge-create-component':(function(module, exports, __webpack_require__){
            __webpack_require__('543d')['createComponent'](__webpack_require__("4665"))
        })
    },
    [['components/uni-badge/uni-badge-create-component']]
]);
