$(function  () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'eir/customer/suggestList',
        datatype: "json",
        colModel: [
            { label: '用户', name: 'userName', width: 30 },
            { label: '手机号码', name: 'phone', width: 30 },
            { label: '车牌号码', name: 'plate', width: 30 },
            { label: '投诉与建议', name: 'suggest', width: 100 },
            { label: '创建时间', name: 'createTime', width: 60 },
            { label: '回复时间', name: 'handleTime', width: 40 },
            { label: '状态', name: 'status', width: 20, formatter(cellvalue, options,rowObject){
                return '<span class="label label-success">'+cellvalue+'</span>';
            }},
            { label: '操作', name: 'id', width: 30 ,formatter:function (cellvalue, options,rowObject) {
                //cellvalue - 当前cell的值
                // options - 该cell的options设置，包括{rowId, colModel（当前行的属性），grid(当前表格)，pos
                //rowObject - 当前row数据select5
                if(rowObject.progressing=='Y'){
                    var name = rowObject.operator;
                    return '<a class="btn " onclick=operate(\'' + cellvalue + '\') style="color: grey"><i class=""></i>' + name + '&nbsp;正在处理</a>';
                }else {
                    if(rowObject.status == '待回复'){
                        return '<a class="btn " onclick=operate(\'' + cellvalue + '\')><i class=""></i>&nbsp;处理</a>';
                    }else{
                        return '<a class="btn " onclick=operate(\'' + cellvalue + '\')><i class=""></i>&nbsp;查看</a>';
                    }
                }
            }},
        ],
        viewrecords: true,
        height: 685,
        rowNum: 10,
        rowList : [10,20,60],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: false,
        pager: "#jqGridPager",
        // loadonce: true,
        // sortable: true,
        // sortname: "createTime",
        // sortorder: "desc",
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


function operate(id) {
    $.ajax({
        url: baseURL + "eir/customer/showSuggestInfo",
        data: {
            id: id,
            progressing: 'Y',
        },
        success(r) {
            if (r.code === 0) {
                vm.suggest = r.suggest;
                var index =window.layer.open({
                    type: 1,
                    skin: 'layui-layer-molv',
                    title: "建议与反馈",
                    area: ['30%', '85%'],
                    closeBtn: 0,
                    shadeClose: false,
                    content: jQuery('#suggest'),
                    btn: ['关闭'],
                    btn1:function (index) {
                        $.ajax({
                            url:baseURL+"eir/customer/unLock",
                            data:{
                                id: id,
                                type: 'suggest'
                            },
                            success:function (res) {
                                console.log(res)
                                if(res.code==0){
                                    layer.close(index);

                                }else {
                                    alert(res.msg)
                                }
                            }
                        })
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
        suggest:{
            id: "",
            remark: "",
        },
        index: -1
    },
    mounted : function(){
        console.log("初始化")
    },
    methods: {
        //刷新
        reload: function (event) {
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'key': this.q.key,
                },
                page:page
            }).trigger("reloadGrid");
        },

        all: function (event) {
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'key': this.q.key,
                    'status': ''
                },
                page:page
            }).trigger("reloadGrid");
        },
        
        replying: function () {
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'key': this.q.key,
                    'status': '0'
                },
                page:page
            }).trigger("reloadGrid");
        },
        
        replied: function () {
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'key': this.q.key,
                    'status': '1'
                },
                page:page
            }).trigger("reloadGrid");
        },

        reply: function () {
            var remark = this.suggest.remark;
            if(remark==''){
                alert("请填写回复内容")
                return ;
            }
            $.ajax({
                url: baseURL + "eir/customer/handle",
                data: {
                    id: this.suggest.id,
                    remark: remark,
                    status: '1',
                    type: 'suggest'
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

        open: function () {

            var expiresDate= new Date();
            expiresDate.setTime(expiresDate.getTime() + (5000));

            $.cookie("id",vm.suggest.id,{expires: expiresDate });

            var path = getRootPath()+ '/sys/historySuggest.html';

            layer.open({
                type: 2,
                area: ['85%', '90%'],
                title:'历史记录查询',
                closeBtn: 1,
                shadeClose: false,
                content: path

            });
        },
        
        
        
    },
    created:function(){

    },
});

setInterval(function () {
    vm.reload()
},10000);

function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
// 获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
