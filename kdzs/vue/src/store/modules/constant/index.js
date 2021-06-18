function  getDisplayName(dataList, value, defaultValue) {
    let data = dataList.find(c => c.code === value);
    if (data)
        return data.value;
    else
        return defaultValue
}

const state = {
    gender: [
        { code: 0, value: '保密' },
        { code: 1, value: '男' },
        { code: 2, value: '女' }
    ],
    education: [
        { code: 1, value: '初中及以下' },
        { code: 2, value: '高中/中专/职校' },
        { code: 3, value: '大专' },
        { code: 4, value: '本科' },
        { code: 5, value: '硕士' },
        { code: 6, value: '博士' },
    ],
    publish: [
        {code: 1, value: '未审核'},
        {code: 2, value: '已发布'},
    ],

    signInStatue: [
        {code: true, value: '已报名'},
        {code: false, value: '未报名'},
    ],
    group:[
        {code:1	,value: '茶艺类教研组'},
        {code:2	,value: '创业培训类教研组'},
        {code:3	,value: '电工类教研组'},
        {code:4	,value: '电子商务类教研组'},
        {code:5	,value: '护理类教研组'},
        {code:6	,value: '花艺类教研组'},
        {code:7	,value: '机械加工类教研组'},
        {code:8	,value: '计算机软件类教研组'},
        {code:9	,value: '计算机应用类教研组'},
        {code:10, value: '计算机硬件类教研组'},
        {code:11, value: '家政类教研组'},
        {code:12, value: '旅游会务类教研组'},
        {code:13, value: '绿化类教研组'},
        {code:14, value: '美容美发类教研组'},
        {code:15, value: '模具制作类教研组'},
        {code:16, value: '农业类教研组'},
        {code:17, value: '其他组'},
        {code:18, value: '汽车类教研组'},
        {code:19, value: '西式烹调与面点类教研组'},
        {code:20, value: '育婴类教研组'},
        {code:21, value: '中式面点类教研组'},
        {code:22, value: '中式烹调类教研组'},
    ],
    rank:[
        {code:1, value:'初级/五级'},
        {code:2, value:'中级/四级'},
        {code:3, value:'高级/三级'},
        {code:4, value:'技师/二级'},
        {code:5, value:'高级技师/一级'},
        {code:6, value:'专项职业能力'},
    ],
    profession:[
        {code:1	, value: '3D打印机安装调试维修'},
        {code:2	, value: '3D打印设计与建模'},
        {code:3	, value: 'B2C网店运营[信服]'},
        {code:4	, value: 'Hadoop软件配置与操作'},
        {code:5	, value: 'SLA 3D打印'},
        {code:6	, value: 'SLA 3D打印机操作'},
        {code:7	, value: 'SLM 3D打印机操作[商发制造]'},
        {code:8	, value: 'SLM 3D打印机操作[增材协会]'},
        {code:9	, value: 'T恤设计与印染'},
        {code:10, value: 'Unity3D软件操作与环境搭建'},
        {code:11, value: '艾灸美容'},
        {code:12, value: '白山羊饲养'},
        {code:13, value: '百货商场商品陈列[百联]'},
        {code:14, value: '办公软件应用'},
        {code:15, value: '半导体器件筛选与测试'},
        {code:16, value: '半导体芯片贴片与引线键合'},
        {code:17, value: '宝玉石鉴别工'},
        {code:18, value: '保安人防[保安协会]'},
        {code:19, value: '保安员'},
        {code:20, value: '保健按摩师'},
        {code:21, value: '保洁员'},
        {code:22, value: '保税仓库出入库作业'},
        {code:23, value: '保育员'},
        {code:24, value: '变电站值班员[电力工程]'},
        {code:25, value: '财税应用软件操作[浦软]'},
        {code:26, value: '采购员'},
        {code:27, value: '彩色宝石鉴定'},
        {code:28, value: '菜肴装饰雕刻'},
        {code:29, value: '餐厅服务员'},
        {code:30, value: '餐饮6T实务操作[餐饮烹饪]'},
        {code:31, value: '餐饮食品安全管控[商联会]'},
        {code:32, value: '仓储管理员'},
        {code:33, value: '叉车司机'},
        {code:34, value: '插秧机操作[农科教]'},
        {code:35, value: '插秧机修理[农科教]'},
        {code:36, value: '茶艺师'},
        {code:37, value: '超声诊断仪安装维修'},
        {code:38, value: '超市理货'},
        {code:39, value: '超市生鲜存放[百联]'},
        {code:40, value: '车工'},
        {code:41, value: '车工（数控车工）'},
        {code:42, value: '车工（数控车工）[上汽]'},
        {code:43, value: '城轨电动列车驾驶员[申通]'},
        {code:44, value: '城轨电动列车检修工[申通]'},
        {code:45, value: '城轨行车值班员[申通]'},
        {code:46, value: '城轨通信工[申通]'},
        {code:47, value: '城轨线路工[申通]'},
        {code:48, value: '传感器应用技术'},
        {code:49, value: '传统插花'},
        {code:50, value: '创业能力'},
        {code:51, value: '创业指导师'},
        {code:52, value: '大棚蔬菜'},
        {code:53, value: '代驾服务[道路运输]'},
        {code:54, value: '道路客运汽车驾驶员'},
        {code:55, value: '道路清扫工[环卫]'},
        {code:56, value: '道路养护工[市政]'},
        {code:57, value: '电工'},
        {code:58, value: '电工[宝钢]'},
        {code:59, value: '电工[建委]'},
        {code:60, value: '电工[上汽]'},
        {code:61, value: '电焊工(钎焊)[技协]'},
        {code:62, value: '电气试验员[电力工程]'},
        {code:63, value: '电梯安装维修工'},
        {code:64, value: '电梯安装维修工[电梯]'},
        {code:65, value: '电梯安装维修工{电梯}'},
        {code:66, value: '电信业务营业员[通协]'},
        {code:67, value: '电讯呼叫中心客户服务'},
        {code:68, value: '电子商务客户服务[信服]'},
        {code:69, value: '电子商务师'},
        {code:70, value: '电子商务应用'},
        {code:71, value: '电子数据鉴定[信息安全]'},
        {code:72, value: '电子仪器仪表装调工'},
        {code:73, value: '对虾养殖[农科教]'},
        {code:74, value: '多媒体软件制作'},
        {code:75, value: '芳香美容'},
        {code:76, value: '翡翠鉴定'},
        {code:77, value: '服装制版师'},
        {code:78, value: '钢筋工[建委]'},
        {code:79, value: '工程安装钳工[建工]'},
        {code:80, value: '工程测量员'},
        {code:81, value: '工业废水处理工'},
        {code:82, value: '工业机器人操作与示教'},
        {code:83, value: '工业机器人基本编程与维护'},
        {code:84, value: '公厕保洁工[环卫]'},
        {code:85, value: '公共场所安检[保安协会]'},
        {code:86, value: '公共营养师'},
        {code:87, value: '固体制剂设备维修'},
        {code:88, value: '瓜果栽培'},
        {code:89, value: '管道工[燃气]'},
        {code:90, value: '管工'},
        {code:91, value: '光电子芯片镀膜'},
        {code:92, value: '光伏电池掺杂'},
        {code:93, value: '光伏电池镀膜'},
        {code:94, value: '光伏电池制造过程测试'},
        {code:95, value: '广告设计'},
        {code:96, value: '广告设计师'},
        {code:97, value: '贵金属首饰检验员'},
        {code:98, value: '锅炉操作工'},
        {code:99, value: '国际商务实务操作'},
        {code:100, value: '国际商务运营实施'},
        {code:101, value: '汉字录入'},
        {code:102, value: '焊工'},
        {code:103, value: '焊工[建委]'},
        {code:104, value: '焊接机器人装调'},
        {code:105, value: '河蟹养殖'},
        {code:106, value: '呼叫中心服务员'},
        {code:107, value: '互联网金融客户服务[信服]'},
        {code:108, value: '互联网网络管理员'},
        {code:109, value: '护林员'},
        {code:110, value: '花卉园艺工'},
        {code:111, value: '花式咖啡制作'},
        {code:112, value: '化学合成制药工[上药]'},
        {code:113, value: '化学实验室危废物处置[张江生药]'},
        {code:114, value: '话务员[通协]'},
        {code:115, value: '会展策划师'},
        {code:116, value: '混凝土工[建委]'},
        {code:117, value: '火灾风险控制'},
        {code:118, value: '机床装调维修工'},
        {code:119, value: '机械产品检验工'},
        {code:120, value: '集成电路版图设计'},
        {code:121, value: '计算机操作'},
        {code:122, value: '计算机程序设计员（.NET）'},
        {code:123, value: '计算机程序设计员（FPGA嵌入式应用）'},
        {code:124, value: '计算机程序设计员（JAVA）'},
        {code:125, value: '计算机程序设计员（嵌入式应用-ARM）'},
        {code:126, value: '计算机辅助设计CAD'},
        {code:127, value: '计算机辅助设计CAM'},
        {code:128, value: '计算机维修工'},
        {code:129, value: '计算机中文速记'},
        {code:130, value: '继电保护工[电力工程]'},
        {code:131, value: '加工中心操作调整工'},
        {code:132, value: '家具设计师'},
        {code:133, value: '家政服务'},
        {code:134, value: '家政服务员'},
        {code:135, value: '架子工[建委]'},
        {code:136, value: '建筑信息模型（BIM）辅助施工[建工]'},
        {code:137, value: '健康照护'},
        {code:138, value: '讲解员'},
        {code:139, value: '洁净室检测[室内环境]'},
        {code:140, value: '金属热处理工[热协]'},
        {code:141, value: '经典咖啡制作'},
        {code:142, value: '晶圆级半导体器件测试[上海集协]'},
        {code:143, value: '客房服务员(会务)'},
        {code:144, value: '空调清洗[空调清洗]'},
        {code:145, value: '快件X光安检[邮政]'},
        {code:146, value: '劳动关系纠纷处理'},
        {code:147, value: '劳动关系协调员'},
        {code:148, value: '梨树栽培[农科教]'},
        {code:149, value: '林木栽培'},
        {code:150, value: '美发师'},
        {code:151, value: '美容师'},
        {code:152, value: '美体(瘦身)'},
        {code:153, value: '秘书'},
        {code:154, value: '模具工（冷冲模具工）'},
        {code:155, value: '模具工（塑料模具工）'},
        {code:156, value: '抹灰工[建委]'},
        {code:157, value: '母婴保育'},
        {code:158, value: '母婴护理'},
        {code:159, value: '平版印刷工[出版]'},
        {code:160, value: '评茶员'},
        {code:161, value: '企业培训师'},
        {code:162, value: '企业人力资源管理师'},
        {code:163, value: '起重装卸机械操作工'},
        {code:164, value: '汽车配件销售员'},
        {code:165, value: '汽车维修工'},
        {code:166, value: '汽车维修工（环卫）[环卫]'},
        {code:167, value: '汽车维修工（汽车车身涂装修复工）'},
        {code:168, value: '汽车维修工（汽车车身整形修复工）'},
        {code:169, value: '汽车维修工（汽车电器维修工）'},
        {code:170, value: '汽车维修工（汽车美容装潢工）'},
        {code:171, value: '汽车营销'},
        {code:172, value: '砌筑工[建委]'},
        {code:173, value: '前台收衣服务[洗染]'},
        {code:174, value: '钳工（工具钳工）'},
        {code:175, value: '钳工（机修钳工）'},
        {code:176, value: '钳工（机修钳工）[宝钢]'},
        {code:177, value: '钳工（装配钳工）'},
        {code:178, value: '钳工（装配钳工）[高技校]'},
        {code:179, value: '嵌入式软件测试'},
        {code:180, value: '桥隧工（城轨）[申通]'},
        {code:181, value: '燃气PE管焊接'},
        {code:182, value: '燃气具安装维修工[燃气]'},
        {code:183, value: '热处理质量检验[热协]'},
        {code:184, value: '日式料理制作'},
        {code:185, value: '肉制品加工'},
        {code:186, value: '软件开发质量控制'},
        {code:187, value: '膳食营养'},
        {code:188, value: '商场安全防患处理'},
        {code:189, value: '商场客户权益维保'},
        {code:190, value: '商场收银'},
        {code:191, value: '商场巡检[百联]'},
        {code:192, value: '商场运营数据处理'},
        {code:193, value: '商场总台服务[百联]'},
        {code:194, value: '商品防损[百联]'},
        {code:195, value: '商品销售[百联]'},
        {code:196, value: '商品营业员'},
        {code:197, value: '商品营业员(家用电器)'},
        {code:198, value: '商品营业员(汽车配件)'},
        {code:199, value: '商品营业员(日用百货)'},
        {code:200, value: '商品营业员(食品)'},
        {code:201, value: '商品质量监控[百联]'},
        {code:202, value: '商铺作业[商联会]'},
        {code:203, value: '商务平台软件操作'},
        {code:204, value: '商务应用软件操作[浦软]'},
        {code:205, value: '商业摄影师'},
        {code:206, value: '社会工作者'},
        {code:207, value: '社会工作者（社区助残）'},
        {code:208, value: '省际客运汽车驾驶[强生控股]'},
        {code:209, value: '实时场景视频制作[数字内容]'},
        {code:210, value: '食品检验'},
        {code:211, value: '食品检验员'},
        {code:212, value: '食用菌生产工'},
        {code:213, value: '市容协查'},
        {code:214, value: '视频编辑'},
        {code:215, value: '室内环境空气治理[室内环境]'},
        {code:216, value: '手工木工[建委]'},
        {code:217, value: '手语翻译员'},
        {code:218, value: '首饰设计师'},
        {code:219, value: '蔬菜栽培工'},
        {code:220, value: '数据库应用(Oracle)'},
        {code:221, value: '数据库应用(SQL Server)'},
        {code:222, value: '数据清洗'},
        {code:223, value: '数字印刷[出版]'},
        {code:224, value: '水产监管[农科教]'},
        {code:225, value: '水稻栽培[农科教]'},
        {code:226, value: '司泵工'},
        {code:227, value: '桃树栽培[农科教]'},
        {code:228, value: '陶艺制作'},
        {code:229, value: '调酒师'},
        {code:230, value: '通风工[建工]'},
        {code:231, value: '图形全景制作'},
        {code:232, value: '图形图像处理'},
        {code:233, value: '网络安全防护'},
        {code:234, value: '网页制作'},
        {code:235, value: '微店组建与服务'},
        {code:236, value: '微官网设计与制作'},
        {code:237, value: '物流服务师(国际货代)'},
        {code:238, value: '物业设备操作'},
        {code:239, value: '物业设施设备操作'},
        {code:240, value: '物业设施设备运行与维护'},
        {code:241, value: '物业水电维修'},
        {code:242, value: '西式面点师'},
        {code:243, value: '西式烹调师'},
        {code:244, value: '铣工'},
        {code:245, value: '铣工（数控铣工）'},
        {code:246, value: '铣工（数控铣工）[上汽]'},
        {code:247, value: '下水道养护工[市政]'},
        {code:248, value: '消防设施操作员'},
        {code:249, value: '消防设施检测维护保养'},
        {code:250, value: '消防设施检查与维护'},
        {code:251, value: '心理咨询师'},
        {code:252, value: '信息安全防护'},
        {code:253, value: '形象设计师'},
        {code:254, value: '悬灸保健'},
        {code:255, value: '眼镜定配工'},
        {code:256, value: '眼镜验光员'},
        {code:257, value: '养老护理员'},
        {code:258, value: '养老护理员（医疗照护）'},
        {code:259, value: '药物分析工[上药]'},
        {code:260, value: '药物制剂工[上药]'},
        {code:261, value: '医药商品购销员'},
        {code:262, value: '移动APP测试项目'},
        {code:263, value: '移动应用测试'},
        {code:264, value: '艺术插花'},
        {code:265, value: '艺术品鉴定[名家艺术]'},
        {code:266, value: '艺术品经纪[名家艺术]'},
        {code:267, value: '艺术品评估[名家艺术]'},
        {code:268, value: '音频特效制作[数字内容]'},
        {code:269, value: '营销员(国际商务)'},
        {code:270, value: '有害生物防制员'},
        {code:271, value: '有机宝石鉴定'},
        {code:272, value: '育婴员'},
        {code:273, value: '预制混凝土构件装配施工[建委]'},
        {code:274, value: '园林绿化工'},
        {code:275, value: '制冷空调系统安装维修工'},
        {code:276, value: '智能家居系统集成及运维'},
        {code:277, value: '智能楼宇管理员'},
        {code:278, value: '中式面点师'},
        {code:279, value: '中式烹调师'},
        {code:280, value: '中央空调系统运行操作员'},
        {code:281, value: '中药材购销员'},
        {code:282, value: '中药调剂员'},
        {code:283, value: '重点安保人防[保安协会]'},
        {code:284, value: '珠宝首饰评估师'},
        {code:285, value: '自媒体运营编辑'},
        {code:286, value: '足部按摩'},
        {code:287, value: '钻石检验员'},
        {code:288, value: '钻石检验员[技协]'},
    ],
    officetype:[
        {code : 1, value :'兼职'},
        {code : 2, value :'专职'}
    ],
    courseTypes: [
        {code:0, value:'专题培训'},
        {code:1, value:'在线培训'},
        {code:2, value:'在线培训(外省市)'},
        {code:3, value:'专业教学法'},
        {code:4, value:'短训班'},
    ],
    studentTypes: [
        {code : 0 , value: '教师'},
        {code : 1 , value: '机构工作人员'},
        {code : 2 , value: '其他'},
        {code : 3 , value: '外省市教师'},
    ]
};

const getters = {
    getGenderNameForCell: (state) => (row, column, cellValue) => {
        return getDisplayName(state.gender, cellValue, state.gender[0])
    },
    getEducationNameForCell: (state) => (row, colum, cellValue) => {
        return getDisplayName(state.education, cellValue, state.education[0])
    },
    getRegisterStatuesNameForCell: () => (row, colum, cellValue) => {
        return cellValue > 0 ? '已注册' : '未注册'
    },
    getPublishNameForCell: (state) => (row, colum, cellValue) => {
        return getDisplayName(state.publish, cellValue, state.publish[0])
    },
    getSignInStatueNameForCell: (state) => (row, colum, cellValue) => {
        return getDisplayName(state.signInStatue, cellValue, state.signInStatue[1])
    },
    getCourseTypeName: (state) => (value) => {
        return getDisplayName(state.courseTypes, value, '未设置')
    },
};




export default {
    namespaced: true,
    state,
    getters
}