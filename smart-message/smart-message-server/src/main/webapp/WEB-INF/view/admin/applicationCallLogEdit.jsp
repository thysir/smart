<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
	<head lang="en">
	    <meta charset="UTF-8">
	    <title>日志查看</title>
		<jsp:include page="common/top.jsp"></jsp:include>
  	</head>
	<body class="page-content ">
		<div class="page-content-area" data-ajax-content="true">
			<div class="page-header">
				<h1>日志查看</h1>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<form id="_editForm" class="form-horizontal" role="form" validate="true">
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">应用名称</label>
							<div class="col-sm-9">
								<div class="clearfix help-validate">
									<input type="text" value="${application.name}" placeholder="应用名称" class="col-xs-10 col-sm-5">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">创建时间</label>
							<div class="col-sm-9">
								<div class="clearfix help-validate">
									<input type="text" value="<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="创建时间" class="col-xs-10 col-sm-5">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">状态</label>
							<div class="col-sm-9">
								<div class="clearfix help-validate">
									<label class="col-xs-10 col-sm-5 control-label no-padding-left align-left">
										<c:choose>
											<c:when test="${ bean.status==0}"><span style="color: green;font-weight: bold;">成功</span></c:when>
											<c:otherwise><span style="color: red;font-weight: bold;">失败</span></c:otherwise>
										</c:choose>
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">消息接收者</label>
							<div class="col-sm-9">
								<div class="clearfix help-validate">
									<input type="text" value="${bean.receiver}" placeholder="消息接收者" class="col-xs-10 col-sm-5">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">消息内容</label>
							<div class="col-sm-9">
								<div class="clearfix help-validate">
									<textarea class="col-xs-10 col-sm-5" placeholder="消息内容" rows="4">${bean.content}</textarea>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">日志信息</label>
							<div class="col-sm-9">
								<div class="clearfix help-validate">
									<textarea class="col-xs-10 col-sm-5" placeholder="日志信息" rows="4">${bean.info}</textarea>
								</div>
							</div>
						</div>
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button id="_back" class="btn btn-info" type="button">
									<i class="ace-icon glyphicon glyphicon-backward bigger-110"></i>
									返回
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
				$("#_back").click(function(){
					window.history.go(-1);
				});
			});
		</script>
	</body>
</html>

