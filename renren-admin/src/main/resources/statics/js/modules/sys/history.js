 $(function  () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'eir/history/page',
        datatype: "json",
        colModel: [			
			{ label: '预约号', name: 'orderId', width: 30, key: true },
			{ label: '司机', name: 'userName', width: 50 },
			{ label: '车牌号', name: 'plate', width: 70 },
            { label: '状态', name: 'state', width: 50 },
			{ label: '更新时间', name: 'updateTime', width: 100 },
			{ label: '到港时间', name: 'appointmentTime', width: 80 },
            { label: '作业类型', name: 'tranType', width: 40 },
			{ label: '处理人', name: 'operator', width: 70 },
            { label: '手机号码', name: 'phone', width: 70 },
            { label: '过期时间', name: 'expireTime', width: 90 },
            { label: '备注', name: 'remark', width: 90 },
            { label: '单证照片', name: 'eirImg', width: 40 ,formatter:function (cellvalue, options,rowObject) {
                    //cellvalue - 当前cell的值
                    // options - 该cell的options设置，包括{rowId, colModel（当前行的属性），grid(当前表格)，pos
                    //rowObject - 当前row数据
                    if(null!=cellvalue){
                        return '<a href="'+cellvalue+'">&nbsp;查看</a>';
                    }
                    return '&nbsp;无照片'; ;

                }},
            { label: '封条照片', name: 'sealImg', width: 40 ,formatter:function (cellvalue, options,rowObject) {
                    //cellvalue - 当前cell的值
                    // options - 该cell的options设置，包括{rowId, colModel（当前行的属性），grid(当前表格)，pos
                    //rowObject - 当前row数据
                    if(null!=cellvalue){
                        return '<a href="'+cellvalue+'">&nbsp;查看</a>';
                    }
                    return '&nbsp;无照片'; ;

                }},
            { label: '附属证明材料', name: 'attachImg', width: 40,formatter:function (cellvalue, options,rowObject) {
                    //cellvalue - 当前cell的值
                    // options - 该cell的options设置，包括{rowId, colModel（当前行的属性），grid(当前表格)，pos
                    //rowObject - 当前row数据
                    if(null!=cellvalue){
                        return '<a href="'+cellvalue+'">&nbsp;查看</a>';
                    }
                    return '&nbsp;无照片'; ;

                } },
        ],
		viewrecords: true,
        height: 685,
        rowNum: 10,
		rowList : [10,20,60],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});
var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			key: "",
		},
	},
    mounted : function(){
	    console.log("初始化")
	  this.update();
    },
	methods: {
		query: function () {
			vm.reload();
		},
		reload: function (event) {
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'key': this.q.key},
                page:page
            }).trigger("reloadGrid");
		},
        update:function (){
            var id=$.cookie("id")
            console.log("id: "+id)
            if (id != null && id !=undefined && id !="" && id != "null"  ){

                console.log("前："+this.q.key);
                this.q.key=id;
                console.log("后："+this.q.key);

                setTimeout(function () {

                    vm.reload()

                },500);



            }
        }
	},
    created:function(){

    },
});