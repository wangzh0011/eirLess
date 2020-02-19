 $(function  () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'eir/customer/showHistoryInfo?id='+$.cookie("id"),
        datatype: "json",
        colModel: [
            { label: 'id', name: 'hisId', width: 65, key: true },
            { label: '作业地点', name: 'site', width: 20 },
            { label: '用户', name: 'userName', width: 20 },
            { label: '车牌号码', name: 'plate', width: 20 },
            { label: '堆场位置', name: 'location', width: 20 },
            { label: '堆场机械问题', name: 'issueType', width: 80 },
            { label: '更新时间', name: 'updateTime', width: 40 },
            { label: '状态', name: 'status', width: 20, formatter(cellvalue, options,rowObject){
                    return '<span class="label label-success">'+cellvalue+'</span>';
                }},
            { label: '备注', name: 'remark', width: 50 },
            { label: '处理人', name: 'operator', width: 20 },

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
        yardPlanId: ""
	},
    mounted : function(){
	    console.log("初始化")
	  // this.update();
    },
	methods: {
		query: function () {
			vm.reload();
		},
		reload: function (event) {
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{
				    'key': this.q.key,
                    'id': $.cookie("id")
                },
                page:page
            }).trigger("reloadGrid");
		},
        // update:function (){
        //     var id=$.cookie("id")
        //     console.log("id: "+id)
        //     if (id != null && id !=undefined && id !="" && id != "null"  ){
        //
        //         this.yardPlanId=id;
        //         vm.reload();
        //     }
        // }
	},
    created:function(){

    },
});