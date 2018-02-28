<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>办公自动化管理系统</title>
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
		<script>
			function setit()
			{
				document.forms[0].submit();
			}
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
	</head>
	
	<body>
		<div class="top">
			<div class="global-width">
				<img src="${pageContext.request.contextPath}/Images/logo.gif" class="logo" />
			</div>
		</div>
		<div class="status">
			<div class="global-width">
				${SessionUser.nicename} 你好！欢迎访问办公管理系统！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/user/tologin" onclick="location.href='loginOut.action'">注销</a>
			</div>
		</div>
		<!-- <form id="myForm" name="myForm" action="userInfo!editData.action" method="post"> -->
		<input type="hidden" name="u.id" value="26"/>
		<input type="hidden" name="u.sex" value="2" id="u_sex"/>
		<input type="hidden" name="u.supper" value="0" id="u_supper"/>
		<div class="main">
			<div class="global-width">
				
 


  
    <div class="nav" id="nav">
					<div class="t"></div>
					<dl>
							<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">信息管理 
						</dt>
						<dd>
							<a href="${pageContext.request.contextPath}/user/select.do" target="_self">个人信息</a>
						</dd>
					</dl>
					<dl>
						<dt
							onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
							邮件管理
						</dt>
						<dd>
							<a href="${pageContext.request.contextPath}/Email/writeEmail.do" target="_self">写邮件</a>
						</dd>
						<dd>
							<a href="javaScipt:void(0)" target="_self">收邮件</a>
						</dd>
						<dd>
							<a href="${pageContext.request.contextPath}/Email/checkRemail" target="_self">垃圾邮件</a>
						</dd>
					</dl>
					<dl>
						<dt
							onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
							考勤管理
						</dt>
						<dd>
							<a href="${pageContext.request.contextPath}/Vacation/checkvacation" target="_self">休假</a>
						</dd>
					</dl>
					
					<dl >
					
						<dt
							onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
							权限管理
						</dt>
						<dd>
							<a href="${pageContext.request.contextPath}/Manage/checkUser" target="_self">个人账户</a>
						</dd>
						<c:if test="${SessionUser.isadmin == 1}">
						<dd>
							<a href="${pageContext.request.contextPath}/Manage/manageUser" target="_self">管理账户</a>
						</dd>
						</c:if>
						
					</dl>
				</div>
						
					<div class="action">
						<div class="t">
							个人信息
						</div>
						<div class="pages">
						<form action="sendEmail" method="post" id="myForm">
							<table width="90%" border="0" cellspacing="0" cellpadding="0">
							
								<tr >
									<td  width="25%">
									用户名
									</td>
									<td  width="25%">
										昵称
									</td>
									<td  width="25%">
										手机
									</td>
									<td  width="25%">
										地址
									</td>
									
								</tr>
								<c:forEach items="${allUser}" var="ue">
								<tr >
									<td  width="25%">
										${ue.username}
									</td>
									<td  width="25%">
										${ue.nicename}
									</td>
									<td  width="25%">
										${ue.phone}
									</td>
									<td  width="25%">
										${ue.address}
									</td>
								</tr>
								</c:forEach>
								<tr>
								<td colspan="2" align="left"><input type="button" value="添加用户" onclick="insertUser()"></td>
								</tr>
								</table>
							</form>
							</div>
					</div>	
			</div>
		</div>
		<div class="copyright">
			Copyright &nbsp; &copy; &nbsp; 
		</div>
 
	</body>
	<script type="text/javascript">
		function insertUser(){
		location.href="${pageContext.request.contextPath}/Manage/insertUser";
		}
	</script>
</html>
