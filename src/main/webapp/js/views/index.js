$(function(){
	$("#menuTree").tree({
	url:'queryForMenu',
	onClick:function(node){
		if(node.attributes){
			node.attributes=$.parseJSON(node.attributes);
		
		}	
		var mytabs=$("#myTabs");
		if(mytabs.tabs("exists",node.text)){
			mytabs.tabs("select",node.text);
		}else{
			mytabs.tabs("add",{
				title:node.text,
				closable:true,
				content:"<iframe src='"+node.attributes.url+"'style='width:100%;height:100%' frameborder=0></iframe>"	
			})
		}
		
	}		
	})
});
