 $(function  () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'eir/ltrSchedule/etaList?status=eta',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'voyCd', width: 20, key: true },
            { label: '船名', name: 'bargeNameCn', width: 20 },
            { label: '航次', name: 'iVoyCd', width: 20 },
            { label: 'ETA', name: 'eta', width: 20 },
            { label: '电话', name: 'onBondTel', width: 20 },
        ],
		viewrecords: true,
        height: 685,
        rowNum: 10,
		rowList : [10,20,60],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
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
	},
    created:function(){

    },
});