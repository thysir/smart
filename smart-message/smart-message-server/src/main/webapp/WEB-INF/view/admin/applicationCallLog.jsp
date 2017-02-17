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
	    <title>应用日志列表</title>
		<jsp:include page="common/top.jsp"></jsp:include>
  	</head>
	<body class="page-content">
		<div class="page-content-area" data-ajax-content="true">
			<div class="page-header">
				<h1>${application.name }-日志列表</h1>
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
											<input type="hidden" name="applicationId" value="${applicationId }">
											<label>
												<label class="control-label" for="form-field-1"> 消息接收者： </label>
												<input name="receiver" type="text" class="form-data input-medium search-data">
											</label>
											<label>
												<label class="control-label" for="form-field-1"> 开始时间： </label>
												<input name="beginTime" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" class="form-data input-medium date-picker">
											</label>
											<label>
												<label class="control-label" for="form-field-1"> 结束时间： </label>
												<input name="endTime" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" class="form-data input-medium date-picker">
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
				
				$('.date-picker').datepicker({
					autoclose: true,
					todayHighlight: true
				})
				
				// 列表
	    		var $table = $("#_table").table({
	    			url : "applicationCallLog/list",
	    			formId : "_form",
					tools : [
						{text : '删除', clazz : 'btn-danger', icon : 'fa fa-trash-o red', permission : '/admin/applicationCallLogo/delete', handler : function(){
							$table.ajaxDelete({
								confirm : "该操作将会删除选中数据，确定继续操作?", 
								url : "${_path}/admin/applicationCallLogo/delete"
							});
						}}
					],
					columns : [
				        {field:'id', hide : true},
				        {field:'receiver', title:'消息接收者', align:'left'},
				        {field:'status', title:'消息状态', align:'left', replace:function(row,index){
				        	if(row.status==0){
				        		return '<span style="color:green">成功</span>';
				        	}else{
				        		return '<span style="color:red">失败</span>';
				        	}
				        }},
				        {field:'createTime', title:'创建时间', align:'left'},
				        {field:'info', title:'日志信息', align:'left'}
					],
					operate : [
						{text : '查看', clazz : 'red', icon : 'fa fa-eye', permission : '/admin/applicationCallLogo/show', handler : function(d, i){
							window.location.href = "${_path}/admin/applicationCallLog/show?id=" + d.id;
						}},
						{text : '删除', clazz : 'red', icon : 'fa fa-trash-o', permission : '/admin/applicationCallLogo/delete', handler : function(d, i){
							$table.ajaxDelete({
								confirm : "该操作将会删除选中数据，确定继续操作?", 
								url : "${_path}/admin/applicationCallLogo/delete"
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
				$('.date-picker').change(function () { 
					$table.search();
				});
			});
		</script>
	</body>
</html>

