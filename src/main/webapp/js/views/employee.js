$(function(){
var emp_datagrid,emp_dialog,emp_form,emp_datagrid_tb;
emp_datagrid=$("#emp_datagrid");
emp_dialog=$("#emp_dialog");
emp_form=$("#emp_form");
emp_datagrid_editDelet=$("#emp_datagrid_del","#emp_datagrid_edit");

emp_datagrid.datagrid({
	url:'employee_list',
	fit:true,
	fitColumns:true,
	rownumbers:true,
	pagination:true,
	singleSelect:true,
	toolbar:'#emp_datagrid_tb',
	pageList:[1,8,10,20],
	 columns:[[
         {field:'username',title:'用户名',width:1,align:'center'},
         {field:'realname',title:'真实姓名',width:1,align:'center'},
         {field:'tel',title:'电话',width:1,align:'center'},
         {field:'email',title:'邮箱',width:1,align:'center'},
         {field:'dept',title:'部门',width:1,align:'center',formatter:deptFormatter},
         {field:'inputtime',title:'入职时间',width:1,align:'center'},
         {field:'state',title:'在职状态',width:1,align:'center',formatter:stateFormatter},
         {field:'admin',title:'是否为超级管理员',width:1,align:'center',formatter:adminFormatter},
    
     ]]	
	
})
emp_dialog.dialog({
	width:300,
	height:300,
	buttons:'#emp_diglog_bt',
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
		emp_dialog.dialog('open');
		emp_dialog.dialog('setTitle',"新增");
		emp_form.form('clear');
		
	},
	
	save:function(){
	var idval=$("#emp_form[name='id']")	.val();
	var url;
		if(idval){
			url="/employee_update";
		}else{
			url="/employee_add";
		}
		emp_form.form("submit",{
			url:url,
		onSubmit:function(param){
			var ids=$('#emp_roles').combobox('getValues')
			for(var i=0;i<ids.length;i++){
				param["roles["+i+"].id"]=ids[i];
				
			}
		},
		success:function(data){
			data=$.parseJSON(data);
			if(data.success){
				$.messager.alert("温馨提示",data.msg,"info",function(){
					emp_dialog.dialog('close');
					emp_datagrid.datagrid("reload");
				})
			}else{
				$.messager.alert("温馨提示",data.msg,"error")
			}
		
		},	
		
		})
	},
edit:function(){
		var rowData=emp_datagrid.datagrid('getSelected');
		 console.log(rowData);
		if(rowData){
			emp_dialog.dialog("open");
			emp_dialog.dialog("setTitle","编辑");
			emp_form.form('clear');	
			if(rowData.dept){
				rowData["dept.id"]=rowData.dept.id;
				
			}
			var roles=$.ajax({
				url:"/role_queryById?eid="+rowData.id,
				async:false
			}).responseText;
			roles=$.parseJSON(roles);
			$("#emp_roles").combobox("setValues",roles);
			emp_form.form('load',rowData);
			console.log(rowData);
		}else{
			$.messager.alert("温馨提示","请选中你要 编辑的数据","error")
		}
		
	},
	
	
	del:function(){
		var rowData=emp_datagrid.datagrid('getSelected');
		if(rowData){
			$.messager.alert("温馨提示","您确定要删除这条数据吗？",function(yes){
				if(yes){
				$.get("/employee_delete?id="+rowData.id,function(data){
					if(data.success){
						$.messager.alert("温馨提示",data.msg,"info",function(){
							emp_dialog.dialog('close');
							emp_datagrid.datagrid("reload");
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
		emp_datagrid.datagrid("reload");
	},
	cancel:function(){
		emp_dialog.dialog('close');
	},
	find:function(){
	var keyWord=$("[name='keyWord']").val();
	emp_datagrid.datagrid("load",{
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
function deptFormatter(value,row,index){
	return value?value.name:value;
}
function stateFormatter(value,row,index){
	return value?"<font color=green>正常</font>":"<font color=red>离职</font>";
}
function adminFormatter(value,row,index){
	return value?"是":"否";
}