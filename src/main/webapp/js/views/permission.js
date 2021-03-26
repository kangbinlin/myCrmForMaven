$(function(){
var permission_datagrid,permission_dialog,permission_form,permission_datagrid_tb;
permission_datagrid=$("#permission_datagrid");
permission_dialog=$("#permission_dialog");
permission_form=$("#permission_form");
permission_datagrid_editDelet=$("#permission_datagrid_del","#permission_datagrid_edit");

permission_datagrid.datagrid({
	url:'permission_list',
	fit:true,
	fitColumns:true,
	rownumbers:true,
	pagination:true,
	singleSelect:true,
	toolbar:'#permission_datagrid_tb',
	pageList:[1,8,10,20],
	 columns:[[
         {field:'name',title:'权限名称',width:1,align:'center'},

         {field:'resource',title:'权限url',width:1,align:'center'},
        
     ]]	
	
})
permission_dialog.dialog({
	width:300,
	height:300,
	buttons:'#permission_diglog_bt',
	closed:true
	
})

//允许用户通过enter键查询
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        //如果按的是enter键，调用find()
        cmdObj.find();
    }
});

	
var butFunction={
	add:function(){
		permission_dialog.dialog('open');
		permission_dialog.dialog('setTitle',"新增");
		permission_form.form('clear');
		
	},
	
	save:function(){
	var idval=$("#permission_form[name='id']")	.val();
	var url;
		if(idval){
			url="/permission_update";
		}else{
			url="/permission_add";
		}
		permission_form.form("submit",{
			url:url,
	
		success:function(data){
			data=$.parseJSON(data);
			if(data.success){
				$.messager.alert("温馨提示",data.msg,"info",function(){
					permission_dialog.dialog('close');
					permission_datagrid.datagrid("reload");
				})
			}else{
				$.messager.alert("温馨提示",data.msg,"error")
			}
		
		},	
		
		})
	},
edit:function(){
		var rowData=permission_datagrid.datagrid('getSelected');
		 console.log(rowData);
		if(rowData){
			permission_dialog.dialog("open");
			permission_dialog.dialog("setTitle","编辑");
			permission_form.form('clear');
			permission_form.form("load", rowData);
		}else{
			$.messager.alert("温馨提示","请选中你要 编辑的数据","error")
		}
		
	},
	
	
	del:function(){
		var rowData=permission_datagrid.datagrid('getSelected');
		if(rowData){
			$.messager.alert("温馨提示","您确定要删除这条数据吗？",function(yes){
				if(yes){
				$.get("/permission_delete?id="+rowData.id,function(data){
					if(data.success){
						$.messager.alert("温馨提示",data.msg,"info",function(){
							permission_dialog.dialog('close');
							permission_datagrid.datagrid("reload");
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
		permission_datagrid.datagrid("reload");
	},
	cancel:function(){
		permission_dialog.dialog('close');
	},
	find:function(){
	var keyWord=$("[name='keyWord']").val();
	permission_datagrid.datagrid("load",{
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
