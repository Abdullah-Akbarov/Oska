
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/mainstyle.css" rel="stylesheet" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body align="center">

<form name="form1" method="post">
<div id="vxod" align="center">
<h1>АС КМО</h1>

<table  align="center" id="avtoriz" cellspacing="5" cellpadding="0">
	<tr>
		<td>
			<p>Логин:</p>
		</td>
		<td>
			<input id="input" type="text" name="login" value="" />
		</td>
	</tr>
	<tr>
		<td>
			<p>Пароль:</p>
		</td>
		<td>
			<input id="input2" type="password" name="password" value="" />
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
			<input type="submit" name="submit" value="Войти" id="submitvoyti"/>
                         <a href="index">OK</a>
		</td>
	</tr>
</table>
</div>
</form>
</body>
</html>
