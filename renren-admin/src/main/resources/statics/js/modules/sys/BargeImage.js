$(function  () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'eir/bargeImage/getBargeMessage',
        datatype: "json",
        colModel: [
            { label: '船名', name: 'vesselName', width: 30 },
            { label: '出口航次', name: 'oVoyCd', width: 50, sortable: false },
            { label: '进口航次', name: 'iVoyCd', width: 50, sortable: false },
            { label: '上传时间', name: 'createTime', width: 30 },
            { label: '查看照片', name: 'imagePath', width: 30, formatter(cellvalue) {
                    return '<a class="btn" onclick=operate(\'' + cellvalue + '\')>查看</a>'
                } },
            // { label: '操作', name: 'id', width: 20 ,formatter:function (cellvalue, options,rowObject) {
            //     //cellvalue - 当前cell的值
            //     // options - 该cell的options设置，包括{rowId, colModel（当前行的属性），grid(当前表格)，pos
            //     //rowObject - 当前row数据select5
            //     return '<a class="btn " onclick=operate(\'' + cellvalue + '\')><i class=""></i>&nbsp;修改</a>';
            // }},
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


function operate(imagePath) {
    let path = imagePath.split(",")
    vm.path = path;
    console.log(path)
    vm.showList = false;
}

var vm = new Vue({
    el:'#rrapp',
    data:{
        keyWord: '',
        showList: true,
        title: null,
        path: []
    },
    mounted:function(){
    },
    methods: {

        preImage: function (i) {
            $('#viewer' + i).viewer();
        },

        viewer: function (i) {
            return 'viewer' + i;
        },

        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                page:page
            }).trigger("reloadGrid");
        },

        query: function () {
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'keyWord': this.keyWord,},
                page:page
            }).trigger("reloadGrid");
        }

    },
    created:function(){

    },
});

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
