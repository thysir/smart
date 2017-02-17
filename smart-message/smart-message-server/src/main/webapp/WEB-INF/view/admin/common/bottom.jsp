<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery || document.write("<script src='${_path}/script/jquery-1.9.1.min.js'>"+"<"+"/script>");
</script>
<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${_path}/script/jquery-1.9.1.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='${_staticPath}/assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
</script>
<script src="${_staticPath}/assets/js/bootstrap.js"></script>

<!-- ace scripts -->
<script src="${_staticPath}/assets/js/ace/elements.scroller.js"></script>
<script src="${_staticPath}/assets/js/ace/elements.colorpicker.js"></script>
<script src="${_staticPath}/assets/js/ace/elements.fileinput.js"></script>
<script src="${_staticPath}/assets/js/ace/elements.typeahead.js"></script>
<script src="${_staticPath}/assets/js/ace/elements.wysiwyg.js"></script>
<script src="${_staticPath}/assets/js/ace/elements.spinner.js"></script>
<script src="${_staticPath}/assets/js/ace/elements.treeview.js"></script>
<script src="${_staticPath}/assets/js/ace/elements.wizard.js"></script>
<script src="${_staticPath}/assets/js/ace/elements.aside.js"></script>
<!-- 修改默认首页 -->
<script id="_ace" src="${_staticPath}/script/assets/ace.js" data-path="${defaultPage}"></script>
<!-- 切换菜单处理 -->
<script id="_ajaxContent" src="${_staticPath}/script/assets/ace.ajax-content.js" data-path="${_path}"></script>
<!-- 权限处理 -->
<script id="_permission" src="${_staticPath}/script/jquery.permission.min.js" data="${_sessionUserNoPermission}"></script>
<!-- 金额格式化处理 -->
<script src="${_staticPath}/assets/js/ace/ace.touch-drag.js"></script>
<script src="${_staticPath}/assets/js/ace/ace.sidebar.js"></script>
<script src="${_staticPath}/assets/js/ace/ace.sidebar-scroll-1.js"></script>
<script src="${_staticPath}/assets/js/ace/ace.submenu-hover.js"></script>
<script src="${_staticPath}/assets/js/ace/ace.widget-box.js"></script>
<script src="${_staticPath}/assets/js/ace/ace.settings.js"></script>
<script src="${_staticPath}/assets/js/ace/ace.settings-rtl.js"></script>
<script src="${_staticPath}/assets/js/ace/ace.settings-skin.js"></script>
<script src="${_staticPath}/assets/js/ace/ace.widget-on-reload.js"></script>
<script src="${_staticPath}/assets/js/ace/ace.searchbox-autocomplete.js"></script>

<!-- 列表通用js -->
<script type="text/javascript" src="${_staticPath}/assets/js/bootbox.js"></script>
<script type="text/javascript" src="${_staticPath}/script/assets/bootbox.custom.js"></script>
<script type="text/javascript" src="${_staticPath}/script/jquery.table.min.js"></script>
<script type="text/javascript" src="${_staticPath}/assets/js/jquery.gritter.js"></script>
<script type="text/javascript" src="${_staticPath}/script/assets/jquery.gritter.custom.js"></script>

<script type="text/javascript" src="${_path}/script/jquery.enums.min.js"></script>
<!-- 表单通用js -->
<!-- juqery ui -->
<script src="${_staticPath}/assets/js/jquery-ui.custom.js"></script>
<!-- 表单验证 -->
<script src="${_staticPath}/script/jquery.validate-2.0.min.js"></script>
<script src="${_staticPath}/script/jquery.form.min.js"></script>
<script src="${_path}/script/jquery.validate-2.0.custom.min.js"></script>
<!-- 日期选择框 -->
<script src="${_staticPath }/assets/js/date-time/bootstrap-datepicker.js"></script>
<script src="${_staticPath}/assets/js/date-time/bootstrap-timepicker.js"></script>

<!-- the following scripts are used in demo only for onpage help and you don't need them -->
<%-- <link rel="stylesheet" href="${_staticPath}/assets/css/ace.onpage-help.css" />
<link rel="stylesheet" href="${_staticPath}/docs/assets/js/themes/sunburst.css" />

<script type="text/javascript"> ace.vars['base'] = '..'; </script>
<script src="${_staticPath}/assets/js/ace/elements.onpage-help.js"></script>
<script src="${_staticPath}/assets/js/ace/ace.onpage-help.js"></script>
<script src="${_staticPath}/assets/js/rainbow.js"></script>
<script src="${_staticPath}/docs/assets/js/language/generic.js"></script>
<script src="${_staticPath}/docs/assets/js/language/html.js"></script>
<script src="${_staticPath}/docs/assets/js/language/css.js"></script>
<script src="${_staticPath}/docs/assets/js/language/javascript.js"></script> --%>