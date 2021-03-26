$(function(){
var dep_datagrid,dep_dialog,dep_form,dep_datagrid_tb;
dep_datagrid=$("#dep_datagrid");
dep_dialog=$("#dep_dialog");
dep_form=$("#dep_form");
dep_datagrid_editDelet=$("#dep_datagrid_del","#dep_datagrid_edit");

dep_datagrid.datagrid({
	url:'department_list',
	fit:true,
	fitColumns:true,
	rownumbers:true,
	pagination:true,
	singleSelect:true,
	toolbar:'#dep_datagrid_tb',
	pageList:[1,8,10,20],
	 columns:[[
         {field:'sn',title:'sn',width:1,align:'center'},

         {field:'name',title:'部门',width:1,align:'center'},
        
     ]]	
	
})
dep_dialog.dialog({
	width:300,
	height:300,
	buttons:'#dep_diglog_bt',
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
		dep_dialog.dialog('open');
		dep_dialog.dialog('setTitle',"新增");
		dep_form.form('clear');
		
	},
	
	save:function(){
	var idval=$("#dep_form[name='id']")	.val();
	var url;
		if(idval){
			url="/department_update";
		}else{
			url="/department_add";
		}
		dep_form.form("submit",{
			url:url,
	
		success:function(data){
			data=$.parseJSON(data);
			if(data.success){
				$.messager.alert("温馨提示",data.msg,"info",function(){
					dep_dialog.dialog('close');
					dep_datagrid.datagrid("reload");
				})
			}else{
				$.messager.alert("温馨提示",data.msg,"error")
			}
		
		},	
		
		})
	},
edit:function(){
		var rowData=dep_datagrid.datagrid('getSelected');
		 console.log(rowData);
		if(rowData){
			dep_dialog.dialog("open");
			dep_dialog.dialog("setTitle","编辑");
			dep_form.form('clear');
			dep_form.form("load", rowData);
		}else{
			$.messager.alert("温馨提示","请选中你要 编辑的数据","error")
		}
		
	},
	
	
	del:function(){
		var rowData=dep_datagrid.datagrid('getSelected');
		if(rowData){
			$.messager.alert("温馨提示","您确定要删除这条数据吗？",function(yes){
				if(yes){
				$.get("/department_delete?id="+rowData.id,function(data){
					if(data.success){
						$.messager.alert("温馨提示",data.msg,"info",function(){
							dep_dialog.dialog('close');
							dep_datagrid.datagrid("reload");
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
		dep_datagrid.datagrid("reload");
	},
	cancel:function(){
		dep_dialog.dialog('close');
	},
	find:function(){
	var keyWord=$("[name='keyWord']").val();
	dep_datagrid.datagrid("load",{
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
