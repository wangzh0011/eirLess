$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'wx/user/list',
        datatype: "json",
        colModel: [			
			{ label: '用户ID', name: 'id', index: "user_id", width: 45, key: true },
			{ label: '用户名', name: 'userName', width: 50 },
			{ label: '手机号码', name: 'phone', width: 50 },
            { label: '车牌号码', name: 'plate', sortable: false, width: 75 },
			{ label: '注册时间', name: 'createTime', width: 50 },
			{ label: 'openid', name: 'openid', width: 100 },
			{ label: '更新时间', name: 'updateTime', index: "create_time", width: 50},
            { label: '微信昵称', name: 'nickName', width: 50},
			{ label: '上次登录', name: 'lastLoginTime', width: 50}
        ],
		viewrecords: true,
        height: 685,
        rowNum: 20,
		rowList : [10,30,50],
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
var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};
var ztree;

var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            username: null
        },
        showList: true,
        title:null,
        roleList:{},
        user:{
            status:1,
            deptId:null,
            deptName:null,
            roleIdList:[]
        },
        message:{
            template_id: "",
            touser: "",
            page: "",
            keyword1: "",
            keyword2: "",
            keyword3: "",
            keyword4: "",
            keyword5: "",
        },
    },
    methods: {
        query: function () {
            vm.reload();
        },


        sendMsg:function(){

            vm.showList = false;

        },

        // sendSingle:function(){
        //     console.log("this.message.template_id"+this.message.template_id)
        //     console.log("this.message.page"+this.message.page)
        //
        //     var i = layer.open({
        //         type:3,
        //         content:'处理中.....'
        //     })
        //     if (this.message.template_id == "" || this.message.touser == ""){
        //         alert("template_id/touser/page不能为空")
        //         return ;
        //     }
        //     $.ajax({
        //         url:baseURL + "app/send/single",
        //         data:{
        //             templateId: this.message.template_id,
        //             toUser: this.message.touser,
        //             page: "pages/listEir/Eir",
        //             keyword1: this.message.keyword1,
        //             keyword2: this.message.keyword2,
        //             keyword3: this.message.keyword3,
        //             keyword4: this.message.keyword4,
        //             keyword5: this.message.keyword5,
        //         },
        //         type: 'POST',
        //         success:function (r) {
        //             console.log(r)
        //             layer.close(i)
        //             alert(r.msg, function(){
        //                 vm.reload();
        //             });
        //
        //         }
        //     })
        // },
        sendAll:function(){
            var i = layer.open({
                type:3,
                content:'处理中.....'
            })
            if (this.message.template_id == "" || this.message.touser == ""){
                alert("template_id/touser不能为空")
                layer.close(i);
                return ;
            }

            $.ajax({
                url:baseURL + "app/send/all",
                data:{
                    templateId: this.message.template_id,
                    toUser: this.message.touser,
                    page: "pages/listEir/Eir",
                    keyword1: this.message.keyword1,
                    keyword2: this.message.keyword2,
                    keyword3: this.message.keyword3,
                    keyword4: this.message.keyword4,
                    keyword5: this.message.keyword5,
                },
                type: 'POST',
                success:function (r) {
                    layer.close(i)
                    alert(r.msg, function(){
                        vm.reload();
                    });


                }
            })

        },

        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'username': vm.q.username},
                page:page
            }).trigger("reloadGrid");
        }
    }
});