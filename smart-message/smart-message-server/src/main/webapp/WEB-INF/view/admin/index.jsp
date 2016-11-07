<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta charset="utf-8" />
		<title>首页</title>

		<meta name="description" content="with selectable items(single &amp; multiple) and custom icons" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<link rel="stylesheet" href="${_staticPath}/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="${_staticPath}/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="${_staticPath}/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="${_staticPath}/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />

		<!--[if lte IE 9]><link rel="stylesheet" href="${_staticPath}/assets/css/ace-part2.css" class="ace-main-stylesheet" /><![endif]-->
		<!--[if lte IE 9]><link rel="stylesheet" href="${_staticPath}/assets/css/ace-ie.css" /><![endif]-->
		<!--[if lte IE 8]><script src="${_staticPath}/assets/js/html5shiv.js"></script><script src="${_path}/assets/js/respond.js"></script><![endif]-->

		<script type="text/javascript" src="${_path}/script/jquery-1.9.1.min.js"></script>
	</head>

	<body class="no-skin">
		<!-- #section:basics/navbar.layout -->
		<div id="navbar" class="navbar navbar-default">

			<div class="navbar-container" id="navbar-container">
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>

				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<i class="fa fa-leaf"></i>${_systemAdminName }
						</small>
					</a>
					<!-- 顶部导航 -->
					<ul class="nav navbar-nav"></ul>
				</div>
				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<!-- 导航内容 -->
					<ul class="nav ace-nav">
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<span class="" id="_sessionAdminName">
									${sessionAdmin.name }
								</span>
								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a data-toggle='modal' onclick="userInfo()">
										<i class="ace-icon fa fa-user"></i>
										用户信息
									</a>
								</li>
								<li class="divider"></li>
								<li>
									<a data-toggle='modal' onclick="userPassword()">
										<i class="ace-icon fa fa-user"></i>
										更改密码
									</a>
								</li>
								<li class="divider"></li>
								<li>
									<a href="${path }/logout">
										<i class="ace-icon fa fa-power-off"></i>
										退出登录
									</a>
								</li>
							</ul>
						</li>

						<!-- /section:basics/navbar.user_menu -->
					</ul>
				</div>
			</div>
		</div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div id="sidebar" class="sidebar responsive">
				<!-- 菜单展开，收缩按钮 -->
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
				<ul class="nav nav-list">
				</ul>
				<%--<!-- 左侧导航 -->
				<ul id="navBox" class="ztree">
				</ul>
			--%></div>
			
			<div class="main-content">
				<div class="main-content-inner">
					<!-- #section:basics/content.breadcrumbs -->
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
						 <ul class="breadcrumb" id="mainTab">
							<li class="active"><a href="#home" title="后台首页" data-toggle="tab" ><span class="glyphicon glyphicon-home"></span> 后台首页</a></li>
						</ul>
					</div>
					<iframe name="mainFrame" id="mainFrame" style="width:100%;display: none" frameborder="no"></iframe>

					<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
						<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
					</a>
					
					<!-- 弹出层 -->
					<div class="modal fade" id="_modal" role="dialog" aria-labelledby="homeModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
							
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<script src="${_staticPath}/assets/js/ace-extra.js"></script>
		<script src="${_staticPath}/assets/js/bootstrap.js"></script>
		<script src="${_staticPath}/assets/js/ace/elements.scroller.js"></script>
		<script src="${_staticPath}/assets/js/ace/elements.treeview.js"></script>
		<script src="${_staticPath}/assets/js/ace/elements.wizard.js"></script>
		<script src="${_staticPath}/assets/js/ace/elements.aside.js"></script>
		<script src="${_staticPath}/assets/js/ace/ace.js"></script>
		<script src="${_staticPath}/assets/js/ace/ace.ajax-content.js"></script>
		<script src="${_staticPath}/assets/js/ace/ace.touch-drag.js"></script>
		<script src="${_staticPath}/assets/js/ace/ace.sidebar-scroll-1.js"></script>
		<script src="${_staticPath}/assets/js/ace/ace.widget-box.js"></script>
		<script src="${_staticPath}/assets/js/ace/ace.sidebar.js"></script>
		<script src="${_staticPath}/assets/js/ace/ace.widget-on-reload.js"></script>
		
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
		
			var menuArr=null;
			jQuery(function ($) {
				
				$.getJSON("menu",function(d) {
					menuArr=d.data;
					drawFirstLevelMenu();
					drawLeftMenuTree();
				});
				
				//顶部菜单click事件监听
				$(document).on("click",".navbar-nav li",function(){
					//移除所有的active样式
					$(".navbar-nav li").removeClass("active");
					$(this).addClass("active");
					drawLeftMenuTree(true);
				});
				
				//重绘顶部的菜单
				function drawFirstLevelMenu(){
					if(menuArr){
						var html='';
						for ( var i = 0; i < menuArr.length; i++) {
							data = menuArr[i];
							if (data.parentId == null || data.parentId == "") {
								if(i==0){
									html+='<li class="active">';
								}else{
									html+='<li class="">';
								}
								html+='		<a href="#" data-url="' + data.url + '" data-id="'+ data.id +'">' + data.name + '</a>';
								html+='</li>';
							}
						}
						
						$(".navbar-nav").html(html);
					}
				}
				
				//重绘侧边的菜单
				function drawLeftMenuTree(showFirstMenu){
					var parentMenuId=$(".navbar-nav .active a").attr("data-id");
					var childrens = _getChildrens(menuArr, parentMenuId);
					$('.nav-list').html(tree(childrens));
					if(showFirstMenu){
						$('.nav-list').find("[onclick]:first").click();
					}
				}
				
				var defaultPage = null;
				function tree(list) {
					var autoOpen=true;
					var html = "";
					var data = null;
					for ( var i = 0; i < list.length; i++) {
						data = list[i];
						
						if(defaultPage == null && data.url){
							defaultPage = data.url;
						}
						
						var childrens = _getChildrens(menuArr, data.id);
						html += '<li class="'+((autoOpen&&childrens.length > 0)?'open':'')+'">';
						if(childrens.length > 0){
							html += '	<a href="#" class="dropdown-toggle">';
						}else{
							if(data.url){
								html += '	<a data-id="'+data.id+'" onclick="openView(this)" href="#" class="' + (childrens.length > 0 ? 'dropdown-toggle' : '') + '"">';
							}else{
								html += '	<a href="#" class="">';
							}
						}
						html += '		<i class="menu-icon fa ' + data.icon + '"></i>';
						html += '		<span class="menu-text">' + data.name + ' </span>';
						html += '		<b class="arrow' + (childrens.length > 0 ? ' fa fa-angle-down' : '') + '"></b>';
						html += '	</a>';
						
						html += '	<b class="arrow"></b>';
						
						if (childrens.length > 0) {
							html += buildTree(list,childrens,autoOpen);
						}
						html += '</li>';
					}
					return html;
				}
				
				function buildTree(list, childrens,autoOpen) {
					var html = "";
					if (childrens.length > 0) {
						html += '	<ul class="submenu" style="'+(autoOpen?'display:block':'display:none')+'">';
						for ( var i = 0; i < childrens.length; i++) {
							data = childrens[i];
							
							if(defaultPage == null && data.url){
								defaultPage = data.url;
								//window.location.href = "${_path}/admin/admin#" + defaultPage;
							}
							
							html += '<li class="">';
							
							var tempChildrens = _getChildrens(list, data.id);
							if(data.url){
								html += '	<a data-id="'+data.id+'" onclick="openView(this)" href="#" class="' + (tempChildrens.length > 0 ? 'dropdown-toggle' : '') + '>';
							}
							else{
								html += '	<a href="#" class="' + (tempChildrens.length > 0 ? 'dropdown-toggle' : '') + '">';
							}
							html += '		<i class="menu-icon fa ' + data.icon + '"></i>';
							html += '		<span class="menu-text">' + data.name + ' </span>';
							html += '		<b class="arrow' + (tempChildrens.length > 0 ? ' fa fa-angle-down' : '') + '"></b>';
							html += '	</a>';
							
							html += '	<b class="arrow"></b>';
							
							
							if (tempChildrens.length > 0) {
								html += buildTree(list, tempChildrens, data.id);
							}
							html += '</li>';
						}
						html += '	</ul>';
					}
					return html;
				}
				
				function _getChildrens(list, id) {
					var children = new Array();
					var child = null;
					for ( var i = 0; i < list.length; i++) {
						child = new Object();
						if (list[i].parentId == id) {
							child.id = list[i].id;
							child.parentId = list[i].parentId;
							child.name = list[i].name;
							child.url = list[i].url;
							child.icon = list[i].icon;
							children.push(child);
						}
					}
					return children;
				}
			});
		</script>
		<script type="text/javascript">
			jQuery(function ($) {
				$("#mainFrame").css("height",(document.body.scrollHeight-90)+"px").show();
			});
			
			//跳转到指定页面(仅限于主页)
			function toPage(src){
				$("#mainFrame").css("display","none");
				var iframeDom = $("#mainFrame");
				iframeDom.attr("src",src);
				iframeDom.css("display","block");
				$("#home").append(iframeDom);
			}
		</script>
	</body>
	
	<script type="text/javascript">
		function openView(obj){
			
			var menuId=$(obj).attr("data-id");
			var menu=null;
			for ( var i = 0; i < menuArr.length; i++) {
				if (menuArr[i].id == menuId) {
					menu = menuArr[i];
				}
			}
			
			if(menu.url){
				var url=null;
				if(menu.url.indexOf("http://")==0){
					url=menu.url;
				}else{
					if(menu.url.indexOf("/")==0){
						url="${_path}/"+menu.url;
					}else{
						url=menu.url;
					}
				}
				$("#mainFrame").attr("src",url);
			}
		}
	</script>
	
	<script type="text/javascript">
		//个人信息
		function userInfo(){
			$("#_modal").modal({
	            remote: "${_path}/admin/admin/info"
	        });
	    };
	       
		//更改密码
		function userPassword(){
			$("#_modal").modal({
				remote: "${_path}/admin/admin/updatePassword"
			});
	    }		
	</script>
	
</html>
