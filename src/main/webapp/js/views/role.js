$(function(){
var role_datagrid,role_dialog,role_form,role_datagrid_tb,allPermissions,selfPermissions;
role_datagrid=$("#role_datagrid");
role_dialog=$("#role_dialog");
role_form=$("#role_form");
role_datagrid_editDelet=$("#role_datagrid_del","#role_datagrid_edit");
selfPermissions=$("#selfPermissions")
allPermissions=$("#allPermissions")

role_datagrid.datagrid({
	url:'role_list',
	fit:true,
	fitColumns:true,
	rownumbers:true,
	pagination:true,
	singleSelect:true,
	toolbar:'#role_datagrid_tb',
	pageList:[1,8,10,20],
	 columns:[[
         {field:'sn',title:'角色编号',width:1,align:'center'},

         {field:'name',title:'角色名称',width:1,align:'center'},
        
     ]]	
	
})
role_dialog.dialog({
	width:700,
	height:450,
	buttons:'#role_diglog_bt',
	closed:true
	
})
allPermissions.datagrid({
	width:300,
	height:300,
	title:"所有权限",
	url:"/permission_list",
	fitColumns:true,
    rownumbers:true,
    pagination:true,
    singleSelect:true,
    onDblClickRow:function(rowIndex,rowData){
    	console.log(rowData);
    	var rows=selfPermissions.datagrid("getRows");
    	var flag=false;
    	var index=-1;
    	for(var i=0;i<rows.length;i++){
    		if(rows[i].id==rowData.id){
    			flag=true;
    			index=i;
    			break;
    			
    			
    		}
    		
    	}
    	if(flag){
    		selfPermissions.datagrid("selectRow",index);
    		console.log(rowData+"进入selectRow");
    	}else{
    		selfPermissions.datagrid("appendRow",rowData);
    		console.log(rowData+"进入appendRow");
    	}
    },
    columns:[[
              {title:"权限名", field:"name", width:1, align:"center"}
          ]]
	
});

//弹窗的分页显示做设置，使分页更简洁
var pagerOfAllPermissions = allPermissions.datagrid("getPager");
pagerOfAllPermissions.pagination({
    showPageList:false,
    showRefresh:false,
    displayMsg:''
});
selfPermissions.datagrid({
    width: 300,
    height: 300,
    title:"已有权限",
    fitColumns:true,
    rownumbers:true,
    singleSelect:true,
    pagination:true,
    onDblClickRow: function (rowIndex, rowData) {
        selfPermissions.datagrid("deleteRow", rowIndex);
    },
    columns:[[
        {title:"权限名", field:"name", width:1, align:"center"}
    ]]

/*selfPermissions.datagrid({
    width: 300,
    height: 300,
    title:"已有权限",
    fitColumns:true,
    rownumbers:true,
    singleSelect:true,
    pagination:true,
    onDblClickRow: function (rowIndex, rowData) {
    	 selfPermissions.datagrid("deleteRow", rowData);
    },
    columns:[[
        {title:"权限名", field:"name", width:1, align:"center"}
    ]]*/
    // closed: true
});

var pagerOfSelfPermissions = selfPermissions.datagrid("getPager");
pagerOfSelfPermissions.pagination({
    showPageList:false,
    showRefresh:false,
    displayMsg:''
});

//允许用户通过enter键查询
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        //如果按的是enter键，调用find()
        cmdObj.find();
    }
});

	
var butFunction={
	add:function(){
		role_dialog.dialog('open');
		role_dialog.dialog('setTitle',"新增");
		 //联合选择器，先选择name=id的数组。再选择name=sn和name=name
		 
         // 右侧载入空数据，而左侧会请求后端，查询所有的permission_list
         //将新打开的selfPermissions那个小表格清空
         selfPermissions.datagrid("loadData", {rows:[]})
         // TODO 其实最好是能做一个支持再双击就删除的效果
		
	},
	
	save:function(){
	var idval=$("#role_form[name='id']").val();
	var url;
		if(idval){
			url="/role_update";
		}else{
			url="/role_add";
		}
		role_form.form("submit",{
			url:url,
	
		    onSubmit: function (param) {
                // 从selfPermissions获取已选权限，额外添加一些参数到param，一同提交后台
                var rows = selfPermissions.datagrid("getRows");
                for (var i = 0; i < rows.length; i++) {
                    // 把右侧选中的权限id都设置进param中的permission数组：permission[0].id = xx, permission[1].id = xx, ...
                    param["permissions["+i+"].id"] = rows[i].id;
                }
            },
            success: function (data) {
                data = $.parseJSON(data);
                if (data.success) {
                    $.messager.alert("温馨提示", data.msg, "info", function () {
                        // 关闭对话框
                        roleDiglog.dialog("close");
                        // 刷新数据表格
                        roleDatagrid.datagrid("reload");
                    });
                } else {
                    $.messager.alert("温馨提示", data.msg, "info")
                }
            }	
		
		})
	},
edit:function(){
		var rowData=role_datagrid.datagrid('getSelected');
		 
		if(rowData){
			role_dialog.dialog("open");
			role_dialog.dialog("setTitle","编辑");
			
			 selfPermissions.datagrid("loadData", {rows:[]});

            // 让右侧框也向后台发起请求，根据角色id查询对应的permission。而左侧本来就能向后台请求权限列表，不必额外设置
            //options是获取selfPermissions这个表格的参数，将其中的url参数设置，并将角色id放到load参数中一起传过去
            var options = selfPermissions.datagrid("options");
            options.url = "/permission_queryByRid";
            selfPermissions.datagrid("load", {
                rid:rowData.id,
            });

			
			role_form.form("load", rowData);
		}else{
			$.messager.alert("温馨提示","请选中你要 编辑的数据","error")
		}
		
	},
	
	
	del:function(){
		var rowData=role_datagrid.datagrid('getSelected');
		if(rowData){
			$.messager.alert("温馨提示","您确定要删除这条数据吗？",function(yes){
				if(yes){
				$.get("/role_delete?id="+rowData.id,function(data){
					if(data.success){
						$.messager.alert("温馨提示",data.msg,"info",function(){
							role_dialog.dialog('close');
							role_datagrid.datagrid("reload");
				});	
				}else{
					$.messager.alert("温馨提示",data.msg,"error")
				}
				},"json");
			}
			});
		}else{$.messager.alert("温馨提示","请选中你要 编辑的数据","error")}
		
	},
	reload:function(){
		role_datagrid.datagrid("reload");
	},
	cancel:function(){
		role_dialog.dialog('close');
	},
	find:function(){
	var keyWord=$("[name='keyWord']").val();
	role_datagrid.datagrid("load",{
		keyWord:keyWord
	});
	}
	
	
}

$("a[data-cmd]").on("click",function(){
	var cmd=$(this).data("cmd");
	if(cmd){
		butFunction[cmd]();
	}
	
})

});
