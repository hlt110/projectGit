<%@page import="java.util.List"%>
<%@page import="com.sun.szk.base.business.common.domain.ManagementMenu"%>
<%@page import="com.sun.szk.base.common.bootstrap.func.SidebarMenu"%>
<%@
	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@
	taglib prefix="e" tagdir="/WEB-INF/tags"
%><%@
	taglib prefix="bs" tagdir="/WEB-INF/tags/BootStrap"
%><e:html>
<head>
<e:-head-from-head />
<e:-head-from-end-of-body />
<script type="text/javascript">
function reinitIframe(){  
    var iframe = document.getElementById("frame");  
    try{
    	var outHeight = $('.content-wrapper').height() - 50;
        var inHeight =  iframe.contentWindow.document.documentElement.scrollHeight - 30;  
        iframe.height = Math.max(outHeight, inHeight);
    }catch (ex){}  
}  
window.setInterval("reinitIframe()", 200); 

function win_pwd(){
	 $('#changePW').modal('show');
}
function win_pwd_submit() {
	if($("#password").val() != $("#rpwd").val()){
		alert("俩次输入的密码不一致");
	}

    
    $.ajax( {  
        type : "POST",  
        url : '${path}/service/managerController/changePW',
        data : $("#changePWForm").serialize(),  
        success : function(msg) { 
            if (eval("("+msg+")").code=='1') {  
            	 alert('修改成功！');
            	 $('#changePW').modal('hide');
            } else { 
            	 alert('旧密码输入错误！');
            }  
        }  
    })
}


</script>
</head>
<body class="skin-green sidebar-mini">
<div class="wrapper">
	<header class="main-header">
		<!-- Logo -->
		<a href="" class="logo">
			<!-- mini logo for sidebar mini 50x50 pixels -->
			<span class="logo-mini"><b>M</b>C</span>
			<!-- logo for regular state and mobile devices -->
			<span class="logo-lg"><b>My</b>Coach</span>
		</a>
		<!-- Header Navbar: style can be found in header.less -->
		<nav class="navbar navbar-static-top" role="navigation">
			<!-- Sidebar toggle button-->
			<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
				<span class="sr-only">Toggle navigation</span>
			</a>
			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
					<li class="">
						<a href="${path}/service/management/dashboard" target="contentWrapperFrame">
							<i class="fa  fa-dashboard"></i>
							<span class="hidden-xs">DashBoard</span>
						</a>
					</li>
					<li class="dropdown user user-menu">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<img src="${path}/images/icon/splash.page.icon.png" class="user-image" alt="User Image"/>
							<span class="hidden-xs">${manager.realName}</span>
						</a>
						<ul class="dropdown-menu">
							<li class="user-header">
								<img src="${path}/images/icon/splash.page.icon.png" class="img-circle" alt="User Image" />
								<p>
									${manager.realName} - ${manager.managerName}
									<small>Member since ${manager.createDateTime}</small>
								</p>
							</li>
							<li class="user-footer">
								<div class="pull-left">
									<a id="dashboardLink" href="javascript:void(0);" class="btn btn-default btn-flat" onclick="win_pwd()">
										<i class="fa fa-pencil-square-o"></i>
										修改密码
									</a>
								</div>
								<div class="pull-right">
									<a id="signOutLink" href="${path}/service/managerController/logout" class="btn btn-default btn-flat">
										<i class="fa fa-sign-out"></i>
										退出系统
									</a>
								</div>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</nav>
	</header>
	<!-- Left side column. contains the logo and sidebar -->
	<aside class="main-sidebar">
		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					<img src="${path}/images/icon/splash.page.icon.png" class="img-circle" alt="User Image" />
				</div>
				<div class="pull-left info">
					<p>${manager.realName}</p>
					<a href="#"><i class="fa fa-circle text-success"></i> ${manager.managerName}</a>
				</div>
			</div>
			${menus}
		</section>
	</aside>
	<!-- Content Wrapper. Contains page content -->
	
	                <div class="modal fade" id="changePW" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content"   style="margin-top: 180px;">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true" onclick="clean()">&times;</span></button>
                                <h4 class="modal-title" id="exampleModalLabel"></h4>
                            </div>
                            <div class="modal-body">
                                <form id="changePWForm" action="" method="post" 
                                      enctype="multipart/form-data">
                                    <input type="hidden" id="managerId" name="managerId"/>
                                    <div class="form-group">
                                        <label for="opwd" class="control-label"><b
                                                style="color: red">*</b>旧密码:</label>
                                        <input type="password" class="form-control" id="opwd" name="opwd"
                                               required="required">
                                    </div>
                               
                                    <div class="form-group" id="managerNameDiv">
                                        <label for="password" class="control-label"><b
                                                style="color: password">*</b>新密码:</label>
                                       			 <input type="password" class="form-control" id="password" name="password"
                                               required="required" >
                                    </div>
                                    <div class="form-group" id="passwordDiv">
                                        <label for="rpwd" class="control-label"><b
                                                style="color: red">*</b>重复新密码:</label>
                                        <input type="password" class="form-control" id="rpwd" name="rpwd"
                                               required="required">
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                        <button type="button" class="btn btn-primary" onclick="win_pwd_submit()">保存</button>
                                    </div>

                                </form>
                            </div>

                        </div>
                    </div>
                </div>
	
	
	
	<div class="content-wrapper">
		<section id="content" class="content">
			<iframe id="frame" name="contentWrapperFrame" width="100%" frameborder="0" scrolling="no" src="${path}/service/management/dashboard"></iframe>
		</section>
    </div>
	<footer class="main-footer">
		<div class="pull-right hidden-xs">
			<b>Version</b> 1.0.0
		</div>
		<strong>Copyright &copy; 2015 All rights reserved.
	</footer>
</div>
</body>
</e:html>