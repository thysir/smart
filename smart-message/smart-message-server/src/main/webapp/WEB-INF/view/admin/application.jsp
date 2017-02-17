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
	    <title>应用列表</title>
		<jsp:include page="common/top.jsp"></jsp:include>
  	</head>
	<body class="page-content">
		<div class="page-content-area" data-ajax-content="true">
			<div class="page-header">
				<h1>应用列表</h1>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="row">
						<div class="col-xs-12">
							<div class="widget-box">
								<div class="widget-header widget-header-small">
									<h5 class="widget-title lighter">搜索栏</h5>
								</div>
			
								<div class="widget-body">
									<div class="widget-main">
										<form id="_form" class="form-inline">
											<label>
												<label class="control-label" for="form-field-1"> 应用名称： </label>
												<input name="name" type="text" class="form-data input-medium search-data">
											</label>
										</form>
									</div>
								</div>
							</div>
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
	    			url : "application/list",
	    			formId : "_form",
					tools : [
						{text : '添加应用', clazz : 'btn-info', icon : 'fa fa-plus-circle blue', permission : '/admin/application/edit', handler : function(){
							window.location.href = "${_path}/admin/application/edit";
						}},
						{text : '删除', clazz : 'btn-danger', icon : 'fa fa-trash-o red', permission : '/admin/application/delete', handler : function(){
							$table.ajaxDelete({
								confirm : "该操作将会删除应用信息，确定继续操作?", 
								url : "${_path}/admin/application/delete"
							});
						}}
					],
					columns : [
				        {field:'id', hide : true},
				        {field:'name', title:'应用名称', align:'left'},
				        {field:'applicationTypeName', title:'应用类型', align:'left'},
				        {field:'count', title:'应用调用次数', align:'left'},
				        {field:'successCount', title:'调用成功数', align:'left'},
				        {field:'errorCount', title:'失败数', align:'left'},
				        {field:'sort', title:'排序', align:'left'}
					],
					operate : [
						{text : '查看日志', clazz : 'blue', icon : 'fa fa-bar-chart-o', permission : '/admin/applicationCallLog', handler : function(d, i){
							window.location.href = "${_path}/admin/applicationCallLog?applicationId=" + d.id;
						}},
						{text : '修改应用', clazz : 'blue', icon : 'fa fa-pencil', permission : '/admin/application/edit', handler : function(d, i){
							window.location.href = "${_path}/admin/application/edit?id=" + d.id;
						}},
						{text : '删除', clazz : 'red', icon : 'fa fa-trash-o', permission : '/admin/application/delete', handler : function(d, i){
							$table.ajaxDelete({
								confirm : "该操作将会删除应用信息，确定继续操作?", 
								url : "${_path}/admin/application/delete"
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

