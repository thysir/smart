<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div>
    <div class="modal-header">
   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
   			&times;
   		</button>
   		<h3 class="modal-title" id="homeModalLabel">
   			<span class="glyphicon glyphicon-warning-sign"></span>
   			更新密码
   		</h3>
   	</div>
   				         
   	<div class="modal-body">
   		<form id="_editForm" class="form-horizontal" role="form" data-toggle="modal" validate="true">
   		  	<div class="tab-content">
   		    	<div class="tab-pane fade in active">
   		    		<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right">原密码</label>
						<div class="col-sm-9">
							<div class="clearfix help-validate">
								<input type="password" name="oldPassword" required="true" maxlength="32" placeholder="请输入原密码" class="col-xs-10 col-sm-8">
							</div>
						</div>
					</div>
   		    		<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right">新密码</label>
						<div class="col-sm-9">
							<div class="clearfix help-validate">
								<input type="password" name="newPassword" id="_newPassword" required="true" minlength="4" maxlength="10" placeholder="请输入新密码" class="col-xs-10 col-sm-8">
							</div>
						</div>
					</div>
   		    		<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right">重复密码</label>
						<div class="col-sm-9">
							<div class="clearfix help-validate">
								<input type="password" required="true" minlength="4" maxlength="10" equalsTo="_newPassword:两次密码不相同" placeholder="请输入重复密码" class="col-xs-10 col-sm-8">
							</div>
						</div>
					</div>
   				</div>
   			</div>
   		</form>
   	</div>
   	
   	<div class="modal-footer">
   		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
   		<button id="_submit" class="btn btn-info" type="button" data-loading-text="正在提交..." permission="/subject/save">
			<i class="ace-icon fa fa-check bigger-110"></i>
			提交
		</button>
   	</div>
</div>

<script src="${_staticPath}/script/jquery.validate-2.0.min.js"></script>
<script src="${_path}/script/jquery.validate-2.0.custom.min.js"></script>
<script src="${_staticPath}/script/jquery.form.min.js"></script>
<script type="text/javascript">
	jQuery(function($) {
		// 提交
		$("#_submit").click(function(){
			if($('#_editForm').validate()){
				var btn = $(this);
				btn.button('loading');
				$.post("${_path}/admin/admin/savePassword", $.formJson('_editForm'),function(d) {
					if(d){
						btn.button('reset');
						if(d.status == '0000'){
							$("#_modal").modal('hide');
						}else {
							$.gritter.add({text: d.message});
						}
					}
		        },'json');
			}
		});
	});
</script>