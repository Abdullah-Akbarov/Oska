<%--
	/*
	 * Copyright (c) 2010, Oracle. All rights reserved.
	 *
	 * Redistribution and use in source and binary forms, with or without
	 * modification, are permitted provided that the following conditions are met:
	 *
	 * * Redistributions of source code must retain the above copyright notice,
	 *   this list of conditions and the following disclaimer.
	 *
	 * * Redistributions in binary form must reproduce the above copyright notice,
	 *   this list of conditions and the following disclaimer in the documentation
	 *   and/or other materials provided with the distribution.
	 *
	 * * Neither the name of Oracle nor the names of its contributors
	 *   may be used to endorse or promote products derived from this software without
	 *   specific prior written permission.
	 *
	 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
	 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
	 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
	 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
	 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
	 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
	 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
	 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
	 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
	 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
	 * THE POSSIBILITY OF SUCH DAMAGE.
	 */
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Нормативно-справочная информация</title>
    </head>
    
        <style>
            body{margin: 0;background-color: #F8F9FA;font-family:Verdana, Tahoma, Geneva;}
            #maintable{border: none;width: 100%;}
            .logotip{width: auto;height: auto;border: 1px solid #CCCCCC;margin: 5px;background-color: #00A8D3;}
            .logotip p{font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;text-align: center;
            line-height: 0.2;}
            .aaa{font-size: 14px;text-decoration: none;font-family: Arial, Helvetica, sans-serif;color: #f5f5f5;
                    display: block;padding: 15px 0 15px 0;border: none;background-color: #3969AA;font-weight: bold;}
            .aaa:hover{font-size: 14px;text-decoration: none;font-family: Arial, Helvetica, sans-serif;
                    color: #fff;display: block;padding: 15px 0 15px 0;border: none;background-color: #448BCA;font-weight: bold;}
            .left{margin: 5px 0 5px 5px;border: 1px solid #ccc;width: 248px;position: absolute;top: 70px;
                    background-color: #F8F8F8;padding: 20px 0px 20px 0px; min-height:450px;}
            .conteiner{margin: 11px 5px 5px 265px;border: 1px solid #ccc;position: relative;width: auto;
                    min-width: 500px;background-color: #F8F8F8;padding: 20px; min-height:450px;}
            .ssilka {font-weight: normal; font-size:12px; color:#333; font-family: 'PT Serif', Verdana, Tahoma;
                    text-decoration: none; display:block; border-bottom: 1px dotted #333; padding:10px 0 10px 5px;
                    }
            .ssilka:hover {font-weight: normal; font-size:12px; color:#333; font-family: 'PT Serif', Verdana, Tahoma; 
                    text-decoration: none; display:block; border-bottom: 1px dotted #333; padding:10px 0 10px 5px;
                    background-color: #efefef;}
            #td1 {border-left:1px solid #d1d1d1;border-right:1px solid #d1d1d1;border-bottom:1px solid #d1d1d1;
                    font-size:14px;color:#444; padding: 10px;}
            #td0 {border-left:1px solid #d1d1d1;border-right:1px solid #d1d1d1;border-top:1px solid #d1d1d1;
                    border-bottom:1px solid #d1d1d1; background-color:#EEEDEB; color:#888; font-weight: bold;
                     font-size:14px;}
            </style>
    <body>    
        
        
        
       
    
    <table id='maintable' cellpadding='0' cellspacing='0'>
            <tr><td height='50px' width='260px'>
                    <div class='logotip' align="center" style="min-width:248px;">
                
                <f:view>
            
                    <p>Ползователь: ${user.firstname} ${user.lastname}!</p>
            <h:form>
                <h:commandButton id="logout" value="Logout" action="#{usermanager.logout}"/>
            </h:form>
        </f:view>
                
                
            </div></td>
            <td>
            <table width='100%' height='100%' border='0' cellpadding='0' cellspacing='5'>
            <tr align='center'>
            <td width='33%'><a href='inspection' class='aaa'>Комиссионные осмотры станций</a></td>
            <td width='33%'><a href='report' class='aaa'>Справочно-аналитические отчеты</a></td>
            <td width='33%'><a href='welcomeJSF.jsp' class='aaa'>Нормативно-справочная информация</a></td>
            </tr></table></td></tr></table>
           
            <div class='left'>
            
            <a href='personal-events' class='ssilka'>Персонал всех предприятий</a>
            <a href='klassifikator-station' class='ssilka'>Классификатор станций</a>
            <a href='klassifikator-events' class='ssilka'>Классификатор предприятий</a>
            <a href='station-service' class='ssilka'>Обслуживание станций предприятиями</a>
            <a href='klassifikator-failures' class='ssilka'>Классификатор видов неисправностей</a>
            
            
            </div>
            
            <div class='conteiner' align='center'>
            <table border='0' cellpadding='5' cellspacing='0' width='90%'>
            <tr>
            <td id='td0' align='center'>Просмотр справочников системы</td>
            </tr>
            <tr>
            <td id='td1'>Персонал всех предприятий</td>
            </tr>
            <tr>
            <td id='td1'>Классификатор станций железной дороги</td>
            </tr>
            <tr>
            <td id='td1'>Классификатор предприятий железной дороги</td>
            </tr>
            <tr>
            <td id='td1'>Классификатор обслуживания станций предприятиями дороги</td>
            </tr>
            <tr>
            <td id='td1'>классификатор неисправностей технических устройств станции</td>
            </tr>
            </table>
             
    
            </div>
    </body>
</html>
