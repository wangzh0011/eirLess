

$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'eir/list/page',
        datatype: "json",
        colModel: [			
			{ label: '预约号', name: 'id', width: 30, key: true },
            { label: '地点', name: 'site', width: 30 },
			{ label: '用户名', name: 'userName', width: 30 },
			{ label: '车牌号', name: 'plate', width: 50 },
            { label: '状态', name: 'state', width: 40 },
			{ label: '到港时间', name: 'appointmentTime', width: 80 },
            { label: '作业类型', name: 'tranType', width: 30 },
            { label: '作业数量', name: 'tranCount', width: 50 },
			{ label: '创建时间', name: 'createTime', width: 90 },
            { label: '手机号码', name: 'phone', width: 90 },
            { label: '过期时间', name: 'expireTime', width: 90 },
            { label: '备注', name: 'remark', width: 90 },
            { label: '操作', name: 'id', width: 90 ,formatter:function (cellvalue, options,rowObject) {
                    //cellvalue - 当前cell的值
                    // options - 该cell的options设置，包括{rowId, colModel（当前行的属性），grid(当前表格)，pos
                    //rowObject - 当前row数据select5
                    return '<a class="btn " onclick=operate(' + cellvalue + ')><i class=""></i>&nbsp;查看</a>';
                    
                },},
            { label: '更新时间', name: 'updateTime', width: 90 }
    ,
        ],
       // postData:{'key': '5400'},
		viewrecords: true,
        height: 685,
        rowNum: 20,
		rowList : [20,40,60,200],
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
            order: "order",
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });

        }
    });
});

$(function () {
    $('#viewer').viewer();
    $('#viewer2').viewer();
    $('#viewer3').viewer();

});
function operate(id) {
    $.ajax({
        url: baseURL + "eir/list/id",
        data: {
            id: id,
            lock:'no',
        },
        success(r) {
            if (r.code === 0) {
                vm.order = r.order;
                console.log(vm.order);
                var index =window.layer.open({
                    type: 1,
                    skin: 'layui-layer-molv',
                    title: "飞单列表",
                    area: ['92%', '98%'],
                    closeBtn: 0,
                    shadeClose: false,
                    content: jQuery('#eirLess'),
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
    el: '#rrapp',
    data: {
        eirLess: [],
        index: -1,
        order: {
            eirImg:''
        },
        selectMsg:'',
        rawHtml: "",
        a: {
            ad: "ad",
        },
        plate:'',
        count:0,
        q:{
            key: null,
            tran_Type:null,
            state:null,
            appointmentTime:null,
            createTime: null,
            updateTime:null,
            site:"ALL",
        },
        title: null,
        showList:true,
        daytime:[],
        remark:"",
        tranType:"",
    },

    mounted:function(){
        $('#viewer').viewer('update');  //异步加载，重新渲染插件
        $('#viewer2').viewer('update');
        $('#viewer4').viewer('update');
        $('#viewer3').viewer('update');
        $('#viewer5').viewer('update');
    },
    methods: {
        save: function (event) {

            var state=vm.order.state;
            if(state!='1'&&state!='2'&&state!='3'&&state!='4'&&state!='5'&&state!='6'&&state!='7'&&state!='8'&&state!='9'&&state!='10'&&state!='11'){

                alert("状态只能输入1-11");

                return;
            }


            if (vm.tranType == vm.order.tranType){

                if (vm.order.tranType=="交空") {
                    vm.order.tranType="RE";
                }else
                if (vm.order.tranType=="交重") {
                    vm.order.tranType="RF";
                }else
                if (vm.order.tranType=="提空") {
                    vm.order.tranType="DE";
                }else
                if (vm.order.tranType=="提重") {
                    vm.order.tranType="DF";
                }else {
                    alert("请修改作业类型为正确的数值!");
                    return;
                }
            }else {

                if(vm.order.tranType !='DE' && vm.order.tranType !='DF' && vm.order.tranType!='RE' && vm.order.tranType!='RF'){
                    alert("作业类型输入不正确!");
                    return;
                }
            }



            if (vm.remark==vm.order.remark){

                alert("输入修改原因");

                return;
            }




            $.ajax({
                type: "POST",
                url: baseURL + "eir/eirLess/save",
                contentType: "application/json",
                data: JSON.stringify(vm.order),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(index){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        update: function (event) {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            $.get(baseURL + "eir/list/id?id="+id, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.order = r.order;
                vm.remark=r.order.remark;
                vm.tranType=r.order.tranType;
            });
        },
        reload: function (event){
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'key': vm.q.key,
                          'tran_Type':vm.q.tran_Type,
                           'state':vm.q.state,
                           'appointmentTime':vm.q.appointmentTime,
                           'createTime':vm.q.createTime,
                           'updateTime':vm.q.updateTime,
                           'site':vm.q.site,
                },
                page:page
            }).trigger("reloadGrid");
        },
        exportExcel: function () {

           var data={'key': vm.q.key,
                'tran_Type':vm.q.tran_Type,
                'state':vm.q.state,
                'appointmentTime':vm.q.appointmentTime,
                'createTime':vm.q.createTime,
                'site':vm.q.site,
            }
            /console.log(data)

            var url=baseURL + 'eir/list/exportExcel';

           console.log(url)


            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'key': vm.q.key,
                    'tran_Type':vm.q.tran_Type,
                    'state':vm.q.state,
                    'appointmentTime':vm.q.appointmentTime,
                    'createTime':vm.q.createTime,
                    'site':vm.q.site,
                }
            });
            var dt=$("#jqGrid").jqGrid()[0].p.postData;
            console.log($("#jqGrid").jqGrid());

           var  s="?_search="+dt._search+"&nd="+dt.nd+"&limit="+dt.limit+"&page="+dt.page+"&sidx="+dt.sidx+
                "&order="+dt.order+"&state="+dt.state+"&tran_Type="+dt.tran_Type+"&key="+dt.key+"&createTime="+dt.createTime
                 +"&appointmentTime="+dt.appointmentTime+"&updateTime="+dt.updateTime+"&site="+dt.site;


/*            var  s=//"?_search="+dt._search+"&nd"+dt.nd+"&limit"+dt.limit+"&page"+dt.page+"&sidx"+dt.sidx+
                "?order"+dt.order+"&state"+dt.state+"&tran_Type"+dt.tran_Type+"&key"+dt.key+"&createTime"+dt.createTime
                +"&appointmentTime"+dt.appointmentTime;*/


            window.location=url+s;
            console.log(url+s)


            console.log("adasd");
/*            $.ajax({
                url:baseURL + 'eir/list/exportExcel',
                data:{
                    "params":data
                },
                success:function (res) {
                    console.log(res)
                }
            })*/


        },
        query: function () {
            vm.reload();
        },
        opena: function (id) {
            layer.alert("the id is " + id)
        },
        select:function(){
            var opt=$("#select").val();
            console.log(opt)

                this.q.state = opt;

        },
        select1:function(){
            var opt=$("#select1").val();
            console.log(opt)

                this.q.tran_Type = opt;

        },
        select2:function(){
            var opt=$("#select2").val();
            this.q.appointmentTime = opt;
        },
        select3:function(){
            var opt=$("#select3").val();
            this.q.createTime = opt;
        },
        select4:function(){
            var opt=$("#select4").val();
            this.q.updateTime = opt;
        },
        select5:function(){
            var site=$("#select5").val();
            this.q.site = site;
        },

        handup:function(){
            console.log(this.selectMsg)
            $.ajax({
                url:baseURL+"eir/eirLess/handle",
                data:{
                    'name':'handup',
                    'remark':this.selectMsg,
                    'id':this.order.id
                },
                success:function (res) {
                    console.log(res)
                    if (res.code==0){
                        window.layer.close(vm.index)
                    }else {
                        alert(res.msg);
                    }
                }
            })
        },
        nopass:function(){
            console.log(this.selectMsg)
            $.ajax({
                url:baseURL+"eir/eirLess/handle",
                data:{
                    'name':'nopass',
                    'remark':this.selectMsg,
                    'id':this.order.id
                },
                success:function (res) {
                    console.log(res)
                    if (res.code==0){
                        window.layer.close(vm.index)
                    }else {
                        alert(res.msg);
                    }
                }
            })
        },
        open:function(){
            var expiresDate= new Date();
            expiresDate.setTime(expiresDate.getTime() + ( 30 * 1000));
            $.cookie("id",vm.order.id,{expires: expiresDate });
            var path = getRootPath()+ '/sys/history.html';
            var index = layer.open({
                type: 2,
                area: ['85%', '90%'],
                title:'历史记录查询',
                closeBtn: 1,
                shadeClose: false,
                content: path,//jQuery('#eirLess')

            });
        },
        unLock:function(){
            $.ajax({
                url:baseURL+"eir/eirLess/unLock",
                data:{
                    plate:this.plate,
                },
                success:function (res) {
                    alert(res.msg)
                }
            })
        },
        cancel:function(){
            $.ajax({
                url:baseURL+"eir/eirLess/cancel",
                data:{
                    id:this.order.id,
                    plate:this.order.plate,
                },
                success:function (res) {
                    if (res.code==0){
                        refresh();
                        layer.close(vm.index)
                    }else {
                        alert(res.msg);
                    }
                }
            })
        },
        pass: function () {
            console.log(this.selectMsg)
            $.ajax({
                url:baseURL+"eir/eirLess/handle",
                data:{
                    'name':'pass',
                    'remark':this.selectMsg,
                    'id':this.order.id
                },
                success:function (res) {
                    refresh();
                    console.log(res)
                    if (res.code==0){
                        layer.close(vm.index)
                    }else {
                        alert(res.msg);
                    }
                }
            })
        },
        close: function () {
            layer.close(this.index);
        },
    },
    created:function(){
        var day1 = new Date();
        day1.setDate(day1.getDate() + 2);
        for (let i = 0; i <30 ; i++) {
            this.daytime.push(format(day1,"yyyy-mm-dd"))
            day1.setDate(day1.getDate() - 1);
        }


    },
});
function getSite(){
    var site=$.cookie("site")
    return site;
}
function format(date,str){
    var mat={};
    mat.M=date.getMonth()+1;//月份记得加1

    mat.H=date.getHours();
    mat.s=date.getSeconds();
    mat.m=date.getMinutes();

    mat.Y=date.getFullYear();
    mat.D=date.getDate();
    mat.d=date.getDay();//星期几
    mat.d=check(mat.d);
    mat.H=check(mat.H);
    mat.M=check(mat.M);
    mat.D=check(mat.D);
    mat.s=check(mat.s);
    mat.m=check(mat.m);

    if(str.indexOf(":")>-1){
        mat.Y=mat.Y.toString().substr(2,2);
        return mat.Y+"/"+mat.M+"/"+mat.D;
    }
    if(str.indexOf("/")>-1){
        return mat.Y+"/"+mat.M+"/"+mat.D;
    }
    if(str.indexOf("-")>-1){
        return mat.Y+"-"+mat.M+"-"+mat.D;
    }
}
//检查是不是两位数字，不足补全
function check(str){
    str=str.toString();
    if(str.length<2){
        str='0'+ str;
    }
    return str;
}
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
