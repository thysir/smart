<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
	<head lang="en">
	    <meta charset="UTF-8">
	    <title>应用授权信息</title>
		<jsp:include page="common/top.jsp"></jsp:include>
  	</head>
	<body class="page-content">
		<div class="page-content-area" data-ajax-content="true">
			<div class="page-header">
				<h1>授权信息</h1>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="row">
						<div class="col-xs-12">
							<div>
								<div class="dataTables_wrapper form-inline no-footer">
									<table id="_table" class="table table-striped table-bordered table-hover dataTable no-footer"></table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<jsp:include page="common/bottom.jsp"></jsp:include>
		<script type="text/javascript">
			jQuery(function($) {
				// 列表
	    		var $table = $("#_table").table({
	    			url : "applicationAuth/list?applicationId=${applicationId}",
					tools : [
						{text : '添加授权', clazz : 'btn-info', icon : 'fa fa-plus-circle blue', permission : '/admin/applicationAuth/edit', handler : function(){
							window.location.href = "${_path}/admin/applicationAuth/edit?applicationId=${applicationId}";
						}},
						{text : '删除', clazz : 'btn-danger', icon : 'fa fa-trash-o red', permission : '/admin/applicationAuth/delete', handler : function(){
							$table.ajaxDelete({
								confirm : "该操作将会删除应用授权信息，确定继续操作?", 
								url : "${_path}/admin/applicationAuth/delete"
							});
						}}
					],
					columns : [
				        {field:'id', hide : true},
				        {field:'authKey', title:'授权key', align:'left'},
				        {field:'authValue', title:'授权value', align:'left'},
				        {field:'description', title:'字段描述', align:'left'}
					],
					operate : [
						{text : '修改授权', clazz : 'blue', icon : 'fa fa-pencil', permission : '/admin/applicationAuth/edit', handler : function(d, i){
							window.location.href = "${_path}/admin/applicationAuth/edit?id=" + d.id;
						}},
						{text : '删除', clazz : 'red', icon : 'fa fa-trash-o', permission : '/admin/applicationAuth/delete', handler : function(d, i){
							$table.ajaxDelete({
								confirm : "该操作将会删除应用授权信息，确定继续操作?", 
								url : "${_path}/admin/applicationAuth/delete"
							});
						}}
					],
					after : function(){
						// 权限处理
						$.permission();
					}
				});
				
	    		//搜索
				$(".search-data").keyup(function () { 
					$table.search();
				});
			});
		</script>
	</body>
</html>

