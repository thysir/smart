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
	    <title>用户编辑</title>
		<jsp:include page="common/top.jsp"></jsp:include>
  	</head>
	<body class="page-content ">
		<div class="page-content-area" data-ajax-content="true">
			<div class="page-header">
				<h1>用户编辑</h1>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<form id="_editForm" class="form-horizontal" role="form" validate="true">
						<input type="hidden" name="id" value="${bean.id }"/> 
						<input type="hidden" name="applicationIds" value=""/> 
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">用户名称</label>
							<div class="col-sm-9">
								<div class="clearfix help-validate">
									<input type="text" name="name" value="${bean.name}" required="true" maxlength="50" placeholder="类型名称" class="col-xs-10 col-sm-5">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">登录账号</label>
							<div class="col-sm-9">
								<div class="clearfix help-validate">
									<input type="text" name="account" value="${bean.account}" vtype="english" required="true" maxlength="15" placeholder="登录账号" class="col-xs-10 col-sm-5">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">登陆密码</label>
							<div class="col-sm-9">
								<div class="clearfix help-validate">
									<input type="text" name="password" value="${bean.password}" vtype="english" maxlength="32" placeholder="登陆密码" class="col-xs-10 col-sm-5">
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
						$.post("${_path}/admin/user/save", $.formJson('_editForm'),function(d) {
							if(d){
								btn.button('reset');
								if(d.status == '0000'){
									window.location.href = '${_path}/admin/user';
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

