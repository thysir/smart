<%@ page language="java" pageEncoding="utf-8"%>
<link rel="stylesheet" href="${_staticPath}/assets/css/jquery.gritter.css" />

<title>模块-${_systemName}</title>

<div class="page-header">
	<h1>
		模块列表
	</h1>
</div>

<div class="row">
	<div class="col-xs-12">
		<div class="row">
			<div class="col-xs-12">
				<div>
					<div class="dataTables_wrapper form-inline no-footer">
						<table id="_table" class="table table-striped table-bordered table-hover dataTable no-footer">
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- page specific plugin scripts -->
<script type="text/javascript">
	var scripts = [null, 
		"${_staticPath}/assets/js/bootbox.js?v=" + Math.random(),
		"${_staticPath}/script/assets/bootbox.custom.js?v=" + Math.random(),
		"${_staticPath}/script/jquery.table.min.js?v=" + Math.random(),
		"${_staticPath}/script/jquery.form.min.js?v=" + Math.random(),
		"${_staticPath}/assets/js/jquery.gritter.js?v=" + Math.random(),
		"${_staticPath}/script/assets/jquery.gritter.custom.js?v=" + Math.random(),
		null];
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			// 列表
    		var $table = $("#_table").table({
    			url : "${_path}/admin/modular/list?appId=${appId}",
				tools : [
					{text : '新增', clazz : 'btn-info', icon : 'fa fa-plus-circle blue', permission : '/admin/modular/edit', handler : function(){
						window.location.href = "${_path}/admin/admin#/admin/modular/edit?appId=${appId}";
					}},
					{text : '删除', clazz : 'btn-danger', icon : 'fa fa-trash-o red', permission : '/admin/modular/delete', handler : function(){
						$table.ajaxDelete({
							confirm : "删除模块会影响关联的管理员、角色、权限，确认要删除?", 
							url : "${_path}/admin/modular/delete"
						});
					}}
				],
				columns : [
			        {field:'id', hide : true},
			        {field:'name', title:'名称', align:'left'},
			        {field:'code', title:'编码', align:'left', mobileHide : true}
				],
				operate : [
					{text : '修改', clazz : 'blue', icon : 'fa fa-pencil', permission : '/admin/modular/edit', handler : function(d, i){
						window.location.href = "${_path}/admin/admin#/admin/modular/edit?id=" + d.id;
					}},
					{text : '删除', clazz : 'red', icon : 'fa fa-trash-o', permission : '/admin/modular/delete', handler : function(d, i){
						$table.ajaxDelete({
							confirm : "删除应用会影响关联的管理员、角色、权限，确认要删除?", 
							url : "${_path}/admin/modular/delete"
						});
					}}
				],
				after : function(){
					// 权限处理
					$.permission();
				}
			});
		});
	});
</script>
