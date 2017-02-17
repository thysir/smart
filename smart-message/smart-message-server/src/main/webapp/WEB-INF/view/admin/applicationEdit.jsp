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
	    <title>应用编辑</title>
		<jsp:include page="common/top.jsp"></jsp:include>
  	</head>
	<body class="page-content ">
		<div class="page-content-area" data-ajax-content="true">
			<div class="page-header">
				<h1>应用信息编辑</h1>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<form id="_editForm" class="form-horizontal" role="form" validate="true">
						<input type="hidden" name="id" value="${bean.id }"/> 
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">应用名称</label>
							<div class="col-sm-9">
								<div class="clearfix help-validate">
									<input type="text" name="name" value="${bean.name}" required="true" maxlength="50" placeholder="应用名称" class="col-xs-10 col-sm-5">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">应用类型</label>
							<div class="col-sm-9">
								<div class="clearfix help-validate">
									<select name="applicationTypeId" class="col-xs-10 col-sm-5">
										<c:forEach var="item" items="${applicationTypeList }">
											<option value="${item.id }" <c:if test="${item.id==bean.applicationTypeId }">selected="selected"</c:if>>${item.name }</option>
										</c:forEach>
									</select>
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
						<div id="_authInfo"></div>
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
						
						var authInfo=new Array();
						$("#_authInfo input").each(function(i,item){
							var item={authKey:item.name,authValue:item.value};
							authInfo[i]=item;
						});		
						
						var data=$.formJson('_editForm');
						data.authInfo=JSON.stringify(authInfo);
						
						$.post("${_path}/admin/application/save", data,function(d) {
							if(d){
								btn.button('reset');
								if(d.status == '0000'){
									window.location.href = '${_path}/admin/application';
								}else {
									$.gritter.add({text: d.message});
								}
							}
				        },'json');
					}
				});
				
				$("[name='applicationTypeId']").change(function(){
					loadApplicationAuthInfo();
				});
				
				/**加载授权信息*/
				function loadApplicationAuthInfo(){
					
					var applicationTypeId=$("[name='applicationTypeId']").val();
					$.get("${_path}/admin/applicationAuth/authInfo", {"applicationTypeId":applicationTypeId,"applicationId":'${bean.id}'},function(d) {
						if(d){
							if(d.status == '0000'){
								
								var html='';
								var list=d.data;
								if(list!=undefined && list!=null && list.length>0){
									$.each(list,function(i,item){
										html+='<div class="form-group">';
										html+='		<label class="col-sm-3 control-label no-padding-right">'+item.description+'</label>';
										html+='		<div class="col-sm-9">';
										html+='			<div class="clearfix help-validate">';
										html+='				<input type="text" name="'+item.authKey+'" value="'+(item.authValue==undefined?"":item.authValue)+'" required="true" maxlength="50" placeholder="'+item.description+'" class="col-xs-10 col-sm-5 auth">';
										html+='			</div>';
										html+='		</div>';
										html+='</div>';
									});
								}
								$("#_authInfo").html(html);
								smart.validate.init();
							}else {
								$.gritter.add({text: d.message});
							}
						}
			        },'json');
				}
				
				loadApplicationAuthInfo();
			});
		</script>
	</body>
</html>

