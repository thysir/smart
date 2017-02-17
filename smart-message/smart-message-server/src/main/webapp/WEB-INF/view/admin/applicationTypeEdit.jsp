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
	    <title>类型编辑</title>
		<jsp:include page="common/top.jsp"></jsp:include>
  	</head>
	<body class="page-content ">
		<div class="page-content-area" data-ajax-content="true">
			<div class="page-header">
				<h1>类型编辑</h1>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<form id="_editForm" class="form-horizontal" role="form" validate="true">
						<input type="hidden" name="id" value="${bean.id }"/> 
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">类型名称</label>
							<div class="col-sm-9">
								<div class="clearfix help-validate">
									<input type="text" name="name" value="${bean.name}" required="true" maxlength="50" placeholder="类型名称" class="col-xs-10 col-sm-5">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">应用类型</label>
							<div class="col-sm-9">
								<div class="clearfix help-validate">
									<input type="text" name="type" value="${bean.type}" required="true" placeholder="应用类型" vtype="integer" min="0"
									ajax="{url : '${_path}/admin/applicationType/validateType', dataId : '_editForm'}" class="col-xs-10 col-sm-5">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">应用描述</label>
							<div class="col-sm-9">
								<div class="clearfix help-validate">
									<input type="text" name="description" value="${bean.description}" maxlength="500" placeholder="应用描述" class="col-xs-10 col-sm-5">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">排序</label>
							<div class="col-sm-9">
								<div class="clearfix help-validate">
									<input type="text" name="sort" value="${bean.sort}" required="true" vtype="integer" placeholder="排序" class="col-xs-10 col-sm-5">
								</div>
							</div>
						</div>
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button id="_submit" class="btn btn-info" type="button" data-loading-text="正在提交..." permission="/subject/save">
									<i class="ace-icon fa fa-check bigger-110"></i>
									提交
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="common/bottom.jsp"></jsp:include>
		<script type="text/javascript">
			jQuery(function($) {
				// 提交
				$("#_submit").click(function(){
					if($('#_editForm').validate()){
						var btn = $(this);
						btn.button('loading');
						$.post("${_path}/admin/applicationType/save", $.formJson('_editForm'),function(d) {
							if(d){
								btn.button('reset');
								if(d.status == '0000'){
									window.location.href = '${_path}/admin/applicationType';
								}else {
									$.gritter.add({text: d.message});
								}
							}
				        },'json');
					}
				});
			});
		</script>
	</body>
</html>

