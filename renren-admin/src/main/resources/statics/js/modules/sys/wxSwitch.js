$(function  () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'wx/system/wxFunctionList',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'id', width: 50, key: true },
            { label: '功能', name: 'functionName', width: 30 },
            { label: '功能代码', name: 'functionCode', width: 30 },
            { label: '功能描述', name: 'functionDesc', width: 30 },
            { label: '关闭通知', name: 'notice', width: 100 },
            { label: '开关', name: 'wxSwitch', width: 60, formatter(cellvalue) {
                if(cellvalue == '1') {
                    return "开";
                } else {
                    return "关";
                }
            }},
            { label: '是否在小程序界面显示', name: 'isShow', width: 60, formatter(cellvalue) {
                if(cellvalue == '1') {
                    return "是";
                } else {
                    return "否";
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
        toggled: this.value,
        colorChecked: '#25b9e9',
        colorUnchecked: '#db572e',
        labelChecked: '开',
        labelUnchecked: '关',
        value: {
            type: Boolean,
            default: true
        },
        speed: {
            type: Number,
            default: 100
        },
        index: -1,
        showList: true,
        title:null,
        wxFunction: {
            functionName: null,
            functionCode: null,
            functionDesc: null,
            wxSwitch: 1,
            notice: null,
            isShow: 1
        }
    },

    mounted : function(){
        console.log("初始化")
    },
    methods: {


        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.wxFunction = {
                functionName: null,
                functionCode: null,
                functionDesc: null,
                wxSwitch: 1,
                notice: null,
                isShow: 1};
        },
        update: function () {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }

            vm.showList = false;
            vm.title = "修改";

            vm.getDetail(id);
        },

        saveOrUpdate: function () {

            if(vm.wxFunction.wxSwitch == '0' && vm.wxFunction.notice == ''){
                alert("请输入关闭通知！");
                return;
            }

            var url = vm.wxFunction.id == null ? "wx/system/save" : "wx/system/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.wxFunction),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },

        getDetail: function (id) {
            $.ajax({
                url: baseURL + "wx/system/getDetail",
                data: {
                    id: id,
                },
                success (res) {
                    if(res.code === 0){
                        vm.wxFunction = res.wxFunction;
                    }else{
                        alert(res.message)
                    }
                }
            })
        },

        toggle (event) {
            this.toggled = !this.toggled
            this.$emit('change', event)
        },
        reload: function (event) {
            vm.showList = true;
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
