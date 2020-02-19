$(function () {
    $('#viewer').viewer();
    $('#viewer2').viewer();
    $('#viewer3').viewer();
    $('#viewer4').viewer();
    $('#viewer5').viewer();
    var colunms = Menu.initColumn();
    //http://127.0.0.1:8080/renren-admin/eir/eirLess/list
    /*$.get(baseURL + "eir/eirLess/list",function(data,status){
        //alert("数据：" + data + "\n状态：" + status);
        console.log("data:  "+data)
        var table = new TreeTable(Menu.id, d ata, colunms);
        table.setExpandColumn(2);
        table.setIdField("id");
        table.setCodeField("id");
        table.setParentCodeField("id");
        table.init();
        Menu.table = table;
    });*/
    var site=getSite();
    console.log(site)
    var table = new TreeTable(Menu.id, baseURL+'/eir/eirLess/list', colunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("id");
    table.setHeight(800);
    table.init();
    Menu.table = table;
    setInterval(refresh, 20000);
});
var Menu = {
    id: "menuTable",
    table: null,
    layerIndex: -1
};
//var table;
/**
 * 初始化表格的列
 */

var order = null;

Menu.initColumn = function () {
    var columns = [

        {title: '预约号', field: 'id',  align: 'center', valign: 'middle', width: '30px'},
        {title: '司机', field: 'userName', align: 'center', valign: 'middle', sortable: false, width: '50px'},
        {title: '手机号码', field: 'phone', align: 'center', valign: 'middle', sortable: false, width: '60px'},
        {title: '车牌号码', field: 'plate', align: 'center', valign: 'middle', sortable: false, width: '50px',},
        {title: '作业类型', field: 'tranType', align: 'center', valign: 'middle', sortable: false, width: '60px',},
        {title: '作业数量', field: 'tranCount', align: 'center', valign: 'middle', sortable: false, width: '60px',},

        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: false, width: '95px',},
        {title: '预约到港时间', field: 'appointmentTime', align: 'center', valign: 'middle', sortable: false,width: '90px'},
        {title: '状态', field: 'state', align: 'center', valign: 'middle', sortable: true, width: '60px',formatter(item,index){
                   //'<span class="label label-danger">禁用</span>'
                   return '<span class="label label-success">'+item.state+'</span>';

            }},

        {title: '备注', field: 'remark', align: 'center', valign: 'middle', sortable: false,width: '100px',visible:false},

        {
            title: '操作', field: 'appointmentTime', align: 'center', valign: 'middle', width: '100px',sortable: true, formatter: function (item, index) {
                var id = item.id;
                var site='';
                if (item.site=='D'){
                    site='内贸';
                } else {
                    site='外贸'
                }
                if(item.progressing=='Y'){
                    var name = item.operator;
                    return '<a class="btn " onclick=operate(' + id + ') style="color: grey"><i class=""></i>'+name+'&nbsp; 审核中('+site+')</a>';
                }else {
                    return '<a class="btn " onclick=operate(' + id + ')><i class=""></i>&nbsp;审核('+site+')</a>';
                }
            }
        }

    ];
    return columns;
};

function operate(id) {


    var vhheight = $(window).height();
    var vwwidth  = $(window).width();
    console.log(vwwidth)
    console.log(vhheight)
    order = null;

    $.ajax({
        url: baseURL + "eir/eirLess/id",
        data: {
            id: id,
            lock:'yes',
            type:"eirList",
        },
        success(r) {
            if (r.code === 0) {
                vm.order = r.order;
                vm.selectMsg='';
                vm.getCount();
                console.log(vm.order);
                var options = document.getElementById('select').children;
                    options[0].selected=true;
                var index =window.layer.open({
                    type: 1,
                    skin: 'layui-layer-molv',
                    title: "审单",
                    area: ['92%', '98%'],
                    closeBtn: 0,
                    shadeClose: false,
                    content: jQuery('#eirLess'),
                    btn: ['取消'],
                    btn1:function (index) {
                        $.ajax({
                            url:baseURL+"eir/eirLess/unLock",
                            data:{
                                plate:vm.order.plate,
                            },
                            success:function (res) {
                                console.log(res)
                                if(res.code==0){
                                    refresh();
                                    layer.close(index);

                                }else {
                                 alert(res.msg)
                                }
                            }
                        })
                    }

                });

                //const  index= parent.layerOpen();
                vm.index=index;


                /*layer.style(index, {
                    position:'absolute'
                });*/
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
        template:[],


    },
    mounted:function(){
        $('#viewer').viewer('update');  //异步加载，重新渲染插件
        $('#viewer2').viewer('update');
        $('#viewer4').viewer('update');
        $('#viewer3').viewer('update');
        $('#viewer5').viewer('update');


    },

    methods: {
        update:function(){
            var i = layer.open({
                type:3,
                content:'加载数据中'
            })
            $.ajax({
                url:baseURL+"eir/eirLess/update/",
                success:function (res) {
                    layer.close(i);
                    if (res.code==0){
                        refresh();
                    }else {
                        alert(res.msg)
                    }
                },
                fail:function () {
                    layer.close(i);
                    alert("网络似乎有点问题")
                }
            })
        },
        opena: function (id) {
            console.log("the id is " + id)
            layer.alert("the id is " + id)
        },
        select:function(){
            var opt=$("#select").val();
            console.log(opt)
            if(opt != 0){
                this.selectMsg = opt;
            }

        },
        getCount:function(){

            console.log("openid    "+this.order.openId)
            $.ajax({
                url: baseURL + "eir/form/count",
                data:{
                    openid:this.order.openId,
                },
                success(res){
                    console.log("eir/form/count")
                    console.log(res)
                    vm.count=res.msg+"  ";
                    console.log("this.count:"+vm.count)
                }
            })
        },
        confirm:function(){
            $.ajax({
                url:baseURL+"eir/eirLess/confirm",
                data:{
                    'id':this.order.id,
                    'remark':this.selectMsg,
                },
                success:function (res) {
                    console.log(res)
                    if (res.code==0){
                        refresh();
                        window.layer.close(vm.index)
                    }else {
                        alert(res.msg);
                    }
                }
            })
        },
        reConfirm:function(){
            $.ajax({
                url:baseURL+"eir/eirLess/reconfirm",
                data:{
                    'id':this.order.id
                },
                success:function (res) {
                    console.log(res)
                    if (res.code==0){
                        refresh();
                        window.layer.close(vm.index)
                    }else {
                        alert(res.msg);
                    }
                }
            })
        },
        handup:function(){
            if(this.selectMsg=='' ||this.selectMsg=='您的内贸飞单已审核通过，请按预约时间进闸签到，以激活作业。签到前请先完成验箱及可能的费用处理.'){
                alert("请填写挂起原因")
                return ;
            }

            var index = layer.open({
                type:3,
                content:'处理中....'
            })

            $.ajax({
                url:baseURL+"eir/eirLess/handle",
                data:{
                    'name':'handUp',
                    'remark':this.selectMsg,
                    'id':this.order.id,

                },
                success:function (res) {
                    layer.close(index)
                    if (res.code==0){
                        /*if (res.next !=null){
                            alert("处理下一条预约");
                            vm.order=res.next;
                        } else {*/
                            refresh();
                            window.layer.close(vm.index)
                        /*}*/

                    }else {
                        alert(res.msg);
                    }
                }
            })
        },
        nopass:function(){
            if(this.selectMsg==''){
                alert("请填写不通过的原因")
                return ;
            }

            var index = layer.open({
                type:3,
                content:'处理中....'
            })
            console.log(this.selectMsg)
            $.ajax({
                url:baseURL+"eir/eirLess/handle",
                data:{
                    'name':'nopass',
                    'remark':this.selectMsg,
                    'id':this.order.id
                },
                success:function (res) {
                    layer.close(index)
                    if (res.code==0){
                        refresh();
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
                    refresh();
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

            if(this.selectMsg==''){
                this.selectMsg="您的飞单已审核通过，请按预约时间进闸签到，以激活作业。签到前请先完成验箱及可能的费用处理.";
            }

            var index = layer.open({
                type:3,
                content:'处理中....'
            })


            $.ajax({
                url:baseURL+"eir/eirLess/handle",
                data:{
                    'name':'pass',
                    'remark':this.selectMsg,
                    'id':this.order.id
                },
                success:function (res) {
                    refresh();
                    layer.close(index)
                    console.log(res)
                    if (res.code==0){
                        layer.close(vm.index)
                    }else {
                        alert(res.msg);
                    }
                }
            })
            //layer.full(index)
            //Vue.use(VueWebsocket, "ws://localhost:8099/websocket");
            /*var socket;
            if(typeof(WebSocket) == "undefined") {
                console.log("您的浏览器不支持WebSocket");
            }else{
                console.log("您的浏览器支持WebSocket");
                //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
                //等同于socket = new WebSocket("ws://localhost:8083/checkcentersys/websocket/20");
                socket = new WebSocket("ws://localhost:8099/websocket");
                //打开事件
                socket.onopen = function() {
                    console.log("Socket 已打开");

                    //socket.send("这是来自客户端的消息" + location.href + new Date());
                };
                //获得消息事件
                socket.onmessage = function(msg) {
                    console.log("Socket 获取信息中。。。");
                    console.log(msg.data);
                    //发现消息进入    开始处理前端触发逻辑
                };
                //关闭事件
                socket.onclose = function() {
                    console.log("Socket已关闭");
                };
                //发生了错误事件
                socket.onerror = function() {
                    alert("Socket发生了错误");
                    //此时可以尝试刷新页面
                }
                //离开页面时，关闭socket
                //jquery1.8中已经被废弃，3.0中已经移除
                // $(window).unload(function(){
                //     socket.close();
                //});
            }*/
        },
        close: function () {
            layer.close(this.index);
        },
        getEirLessAll: function () {
            $.get(baseURL + "eir/eirLess/list", getSite(),function (res) {
                console.log(res)
                vm.eirLess = res;

            })
        },
        gettemplate:function(){
            $.get(baseURL + "eir/eirLess/template", function (res) {
                if (res.code==0){
                    vm.template =JSON.parse(res.msg);
                    console.log(vm.template)
                    for ( var i in vm.template){
                        console.log("输出：  "+vm.template[i].name)
                    }


                }else {
                    alert(res.msg)
                }


            })
        }
    },
    created:function(){
        this.gettemplate();
    },
});

function getSite(){
    var site=$.cookie("site")
    return site;
}

function refresh() {
    console.log(getSite())

    var data={'site':getSite()};

    Menu.table.set("site",getSite());
    Menu.table.refresh();

}
/*
var socket;

if (typeof(WebSocket) == "undefined") {
    console.log("您的浏览器不支持WebSocket");
}
else {
    console.log("您的浏览器支持WebSocket");
    //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
    //等同于socket = new WebSocket("ws://localhost:8083/checkcentersys/websocket/20");
    socket = new WebSocket("ws://localhost:8099/websocket");
    //打开事件
    socket.onopen = function () {
        console.log("Socket 已打开");

        //socket.send("这是来自客户端的消息" + location.href + new Date());
    };
    //获得消息事件
    socket.onmessage = function (msg) {
        console.log("Socket 获取信息中。。。");
        console.log(msg.data);
        //发现消息进入    开始处理前端触发逻辑
    };
    //关闭事件
    socket.onclose = function () {
        console.log("Socket已关闭");
    };
    //发生了错误事件
    socket.onerror = function () {
        alert("Socket发生了错误");
        //此时可以尝试刷新页面
    }
    //离开页面时，关闭socket
    //jquery1.8中已经被废弃，3.0中已经移除
    // $(window).unload(function(){
    //     socket.close();
    //});
}*/
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

