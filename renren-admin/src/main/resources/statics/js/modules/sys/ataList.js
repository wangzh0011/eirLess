 $(function  () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'eir/ltrSchedule/ataList?status=ata',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'voyCd', width: 20, key: true },
            { label: '船名', name: 'bargeNameCn', width: 20 },
            { label: '航次', name: 'iVoyCd', width: 20 },
            { label: 'ETA', name: 'eta', width: 20 },
            { label: 'ATA', name: 'iAta', width: 20 },
            { label: '电话', name: 'onBondTel', width: 20 },
            { label: '推送泊位信息', name: 'voyCd', width: 30 ,formatter:function (cellvalue, options,rowObject) {
                    //cellvalue - 当前cell的值
                    // options - 该cell的options设置，包括{rowId, colModel（当前行的属性），grid(当前表格)，pos
                    //rowObject - 当前row数据select5
                    return '<a class="btn " onclick=operate(\'' + cellvalue + '\')><i class=""></i>&nbsp;操作</a>';
                }},
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

 function operate(voyCd) {
     $.ajax({
         url: baseURL + "eir/ltrSchedule/showInfo",
         data: {
             voyCd: voyCd,
         },
         success(r) {
             if (r.code === 0) {
                 vm.ltrSchedule = r.ltrSchedule;
                 console.log(vm.ltrSchedule);
                 var index =window.layer.open({
                     type: 1,
                     skin: 'layui-layer-molv',
                     title: "船期信息",
                     area: ['30%', '85%'],
                     closeBtn: 0,
                     shadeClose: false,
                     content: jQuery('#ltrSchedule'),
                     btn: ['关闭'],
                     btn1:function (index) {
                         layer.close(index);
                     }
                 });
                 vm.index=index;
             } else {
                 alert(r.msg);
             }
         }
     });
 }

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			key: "",
		},
        berth: "",
        ltrSchedule: ""
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
        sendMsg: function () {
            var berth = this.berth;
            if(berth==''){
                alert("请输入泊位")
                return ;
            }
            $.ajax({
                url: baseURL + "eir/ltrSchedule/sendMsg",
                data: {
                    iVoyCd: this.ltrSchedule.iVoyCd,
                    bargeNameCn: this.ltrSchedule.bargeNameCn,
                    ata: this.ltrSchedule.iAta,
                    berth: berth
                },
                success(res) {
                    if(res.code == '0'){
                        layer.close(vm.index);
                        vm.reload();
                    }else{
                        alert(res.msg)
                    }

                }
            })
        },
	},
    created:function(){

    },
});