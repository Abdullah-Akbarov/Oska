package controller;

import dao.PostgresConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


@WebServlet(name = "add-failure", urlPatterns = {"/add-failure"}) // dobavit_neispravnosti
public class AddFailure extends HttpServlet {
    Connection con = PostgresConnection.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        addFailureRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        addFailureRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    protected void addFailureRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Комиссионные осмотры станций</title>");
            out.println("</head>");
            //style boshi
            style(out);

            //style tugadi
            //body boshi
            out.println("<body>");
            out.println("<table id='maintable' cellpadding='0' cellspacing='0'>");
            out.println("<tr><td height='50px' width='260px'>");
            out.println("<div class='logotip'><p>АС КМО</p></div></td>");
            out.println("<td>");
            out.println("<table width='100%' height='100%' border='0' cellpadding='0' cellspacing='5'>");
            out.println("<tr align='center'>");
            out.println("<td width='33%'><a href='inspection' class='aaa'>Комиссионные осмотры станций</a></td>");
            out.println("<td width='33%'><a href='report' class='aaa'>Справочно-аналитические отчеты</a></td>");
            out.println("<td width='33%'><a href='faces/welcomeJSF.jsp' class='aaa'>Нормативно-справочная информация</a></td>");
            out.println("</tr></table></td></tr></table>");
            //top menu tugadi

            //left menu boshlanishi------------------------------------------------
            out.println("<form id='form1' name='form1' method='post' action=''>");
            out.println("<div class='left'>");
            out.println("<h4 style='line-height:0.3;'>РЖУ</h4>");

            out.println("<select class='select1' name='nrju' id='nrju' onchange='javascript:form1.submit();' onselect='javascript:form1.submit();'>");
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery("select * from mtu order by idMTU");
            out.println("<option value=''></option>");
            while (rs1.next()) {
                String s = rs1.getString(1);
                String s1 = rs1.getString(2);
                String sss = "";
                if (request.getParameter("nrju") != null)
                    sss = ((request.getParameter("nrju").equals(s)) ? "selected=true" : "");
                out.println("<option value='" + s + "' " + sss + ">" + s1 + "</option>");

            }
            out.println("</select>");
            st1.close();
            out.println("<h4 style='line-height:0.3;'>Станции</h4>");
            out.println("<select class='select1' name='nstansiya' id='nstansiya' size='10' multiple='MULTIPLE' onchange='javascript:form1.submit();' onselect='javascript:form1.submit();'>");
            Statement st = con.createStatement();
            String ss = request.getParameter("nrju");
            ResultSet rs = st.executeQuery("select * from predpriyatie where idMTU='" + ss + "' and typePredpriyatie=1 order by namePredpriyatie");
            while (rs.next()) {
                String s = rs.getString("id");
                String s1 = rs.getString("namePredpriyatie");
                String sss1 = "";
                if (request.getParameter("nstansiya") != null)
                    sss1 = ((request.getParameter("nstansiya").equals(s)) ? "selected=true" : "");
                out.println("<option value='" + s + "' " + sss1 + ">" + s1 + "</option>");

            }
            out.println("</select>");
            st.close();
            out.println("<h4 style='line-height:0.3;'>Осмотры</h4>");
            out.println("<td><select class='select1' name='nakt' size='10' multiple='MULTIPLE' onchange='javascript:form1.submit();' onselect='javascript:form1.submit();'>");

            Statement st2 = con.createStatement();
            String stan = request.getParameter("nstansiya");
            ResultSet rs2 = st2.executeQuery("select * from akt a, tip_osmotr t where a.tipOsmotr=t.id and a.nameStansiya='" + stan + "'");
            while (rs2.next()) {
                String s = rs2.getString("a.id");
                String s1 = rs2.getString("a.nachaloProved");
                String s2 = rs2.getString("t.tipOsmotr");
                String sss3 = "";
                if (request.getParameter("nakt") != null)
                    sss3 = ((request.getParameter("nakt").equals(s)) ? "selected=true" : "");
                out.println("<option value='" + s + "' " + sss3 + ">" + s1 + " (" + s2 + ")" + "</option>");

            }

            out.println("</select></td>");
            st2.close();


            out.println("</div>");
            //left menu tugadi-----------------------------------------------------
            //conteyner boshi-------------------------------------------------------------------------------------
            out.println("<div class='conteiner'>");

            out.println("<table border='0' cellpadding='0' cellspacing='3' width='100%' align='center'><tr align='center'>");
            out.println("<td><a href='add-act' class='atopmenu'>Добавить акт</a></td>");
            out.println("<td><a href='add-failure' class='atopmenu'>Добавить неисправности</a></td>");
            out.println("<td><a href='troubleshooting' class='atopmenu'>Устранение замечания</a></td>");
            out.println("<td><a href='inspection' class='atopmenu'>Контроль устранения</a></td>");
            out.println("</tr></table>");
            out.println("<h3 align='center'>Выявленные неисправности технических устройств станции</h3>");
            out.println("<div  style='padding-left:50px;'>");
            out.println("<table id='tas' border='1' cellspacing='0' cellpadding='5'>");
            out.println("<tr><td width='112' class='td1'>Номер в ДУ-46</td>");
            out.println("<td width='154' class='td2'>По текущему осмотру</td>");
            out.println("<td width='343' class='td3'>");

            out.println("<input type='text' name='nnomer' id='nnomer' value='" + request.getParameter("nnomer") + "'/>");

            out.println("</td></tr>");
            out.println("<tr>");
            out.println("<td rowspan='2' class='td1'><p>Место обнаружения неисправности</p></td>");
            out.println("<td class='td2'>Неисправное средства</td>");
            out.println("<td class='td3'>");

            out.println("<select name='ntexsredstv' id='ntexsredstv' onchange='javascript:form1.submit();' onselect='javascript:form1.submit();'>");
            Statement st7 = con.createStatement();
            ResultSet rs7 = st7.executeQuery("select * from texsredstv order by idTexsredstv ASC");
            while (rs7.next()) {
                String s = rs7.getString(1);
                String s1 = rs7.getString(2);
                String sss3 = "";
                if (request.getParameter("ntexsredstv") != null)
                    sss3 = ((request.getParameter("ntexsredstv").equals(s)) ? "selected=true" : "");
                out.println("<option value='" + s + "' " + sss3 + ">" + s1 + "</option>");
            }
            st7.close();
            out.println("</select>");

            out.println("</td></tr>");
            out.println("<tr><td class='td2'>Комментарий</td>");
            out.println("<td class='td3'>");

            out.println("<input type='text' name='ntexsrcomment' id='ntexsrcomment' size='60' value='" + request.getParameter("ntexsrcomment") + "'/>");
            out.println("</td></tr><tr>");
            out.println("<td rowspan='3' class='td1'><p>Неисправност технического средства</p></td>");
            out.println("<td class='td2'>Вид неисправности</td>");
            out.println("<td class='td3'>");
            out.println("<select style='min-width:380px;' name='nnneispravnost' id='nnneispravnost' size='5' multiple onchange='javascript:form1.submit();' onselect='javascript:form1.submit();'>");

            Statement st4 = con.createStatement();
            String tex = request.getParameter("ntexsredstv");
            ResultSet rs4 = st4.executeQuery("select * from neispravnosti where idTexsredstv='" + tex + "'");
            while (rs4.next()) {
                String s = rs4.getString("idNeispravnosti");
                String s1 = rs4.getString("nomerpp");
                String s2 = rs4.getString("nameSmall");
                String sss3 = "";
                if (request.getParameter("nnneispravnost") != null)
                    sss3 = ((request.getParameter("nnneispravnost").equals(s)) ? "selected=true" : "");
                out.println("<option value='" + s + "' " + sss3 + ">" + s1 + ". " + s2 + "</option>");
            }
            out.println("</select>");
            st4.close();
            out.println("</td></tr>");
            out.println("<tr><td class='td2'>Подробнее:</td>");
            out.println("<td class='td3'>");
            out.println("<div width='300px' style='background-color:#fff; text-indent: 30px;'>");
            Statement st5 = con.createStatement();
            String neis = request.getParameter("nnneispravnost");
            ResultSet rs5 = st5.executeQuery("select * from neispravnosti where idNeispravnosti='" + neis + "'");

            while (rs5.next()) {
                String s1 = rs5.getString("nameNeispravnosti");
                out.println(s1);
            }
            st5.close();
            out.println("</div>");
            out.println("</td></tr>");
            out.println("<tr><td class='td2'>Комментарий</td>");
            out.println("<td class='td3'>");
            out.println("<input type='text' name='nneiscomment' id='nneiscomment' size='60' value='" + request.getParameter("nneiscomment") + "' />");
            out.println("</td></tr><tr>");
            out.println("<td class='td1'><p>Плановый срок  устранения</p></td> ");
            out.println("<td class='td2'>Дата</td>");
            out.println("<td class='td3'>");
            out.println("<input type='text' name='ndata' id='ndata' value='" + request.getParameter("ndata") + "' />");
            out.println("</td></tr><tr></tr>");

            out.println("<tr>");
            out.println("<td rowspan='2' class='td1'><p>Прынятие меры бесопасности движения</p></td>");
            out.println("<td class='td2'>Принятие меры</td>");
            out.println("<td class='td3'>");
            out.println("<select name='nmeri' id='nmeri'>");
            Statement st6 = con.createStatement();
            ResultSet rs6 = st6.executeQuery("select * from meri order by idMeri ASC");
            while (rs6.next()) {
                String s = rs6.getString(1);
                String s1 = rs6.getString(2);
                String sss3 = "";
                if (request.getParameter("nmeri") != null)
                    sss3 = ((request.getParameter("nmeri").equals(s)) ? "selected=true" : "");
                out.println("<option value='" + s + "' " + sss3 + ">" + s1 + "</option>");
            }

            out.println("</select>");
            st6.close();
            out.println("</td></tr><tr>");
            out.println("<td class='td2'>Комментарий</td>");
            out.println("<td class='td3'>");
            out.println("<input type='text' name='nmericomment' id='nmericomment' size='60' value='" + request.getParameter("nmericomment") + "' />");
            out.println("</td></tr><tr>");
            out.println("<td class='td1'><p>Ответственный за устранение неисправности</p></td>");
            out.println("<td colspan='2' class='td3'>");

            out.println("<input type='radio' name='npedtype' id='npedtype' value='2' onchange='javascript:form1.submit();' onselect='javascript:form1.submit();' /><label for='radio'>ПЧ</label>");
            out.println("<input type='radio' name='npedtype' id='npedtype' value='3' onchange='javascript:form1.submit();' onselect='javascript:form1.submit();' /><label for='radio'>ШЧ</label>");
            out.println("<input type='radio' name='npedtype' id='npedtype' value='4' onchange='javascript:form1.submit();' onselect='javascript:form1.submit();' /><label for='radio'>ЭЧ</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
            out.println("<select name='npred' id='npred' />");
            Statement st8 = con.createStatement();
            String pch = request.getParameter("npedtype");
            ResultSet rs8 = st8.executeQuery("select * from predpriyatie where typePredpriyatie='" + pch + "'");
            while (rs8.next()) {
                String s = rs8.getString("id");
                String s1 = rs8.getString("namePredpriyatie");
                out.println("<option value='" + s + "'>" + s1 + "</option>");
            }
            out.println("</select>");
            st8.close();

            out.println("</td></tr></table><br />");
            out.println("<input type='submit' name='ndobavit' id='ndobavit' value='OK' />");
            out.println("<input type='submit' name='nochistit' id='nochistit' value='Очистить все' />");

            out.println("</div><br /><div style='padding-left:50px;'>");
            if (request.getParameter("ndobavit").equals("OK")) {

                String ss1 = request.getParameter("nnomer");
                String ss2 = request.getParameter("ntexsredstv");
                String ss3 = request.getParameter("ntexsrcomment");
                String ss4 = request.getParameter("nnneispravnost");
                String ss5 = request.getParameter("nneiscomment");
                String ss6 = request.getParameter("ndata");
                String ss7 = request.getParameter("nmeri");
                String ss8 = request.getParameter("nmericomment");
                String ss9 = request.getParameter("npred");
                String ss10 = request.getParameter("nakt");
                Statement st9 = con.createStatement();
                int rs9 = st9.executeUpdate("insert into du46 (nomer,texSredstv,commentexsr,neispravnosti,commentneis,srokUstranenie,prinyatieMeri,commentpm,otvetsvennie,idAkt) "
                        + "values ('" + ss1 + "','" + ss2 + "','" + ss3 + "','" + ss4 + "','" + ss5 + "','" + ss6 + "','" + ss7 + "','" + ss8 + "','" + ss9 + "','" + ss10 + "')");

            }
            {
                Statement st11 = con.createStatement();
                ResultSet rs11 = st11.executeQuery("select * from du46 d, texsredstv t, neispravnosti n, meri m, predpriyatie p where d.texSredstv=t.idTexsredstv and d.neispravnosti=n.idNeispravnosti and d.prinyatieMeri=m.idMeri and d.otvetsvennie=p.id and d.id=(select max(id) from du46)");
                out.println("<table border='1' cellspacing='0' cellpadding='4'>");
                out.println("<tr align='center' style='background-color:#e0e0e0; color:#333; font-family:Verdana; font-size:12px; font-weight:bold;'>");
                out.println("<td>№</td>");
                out.println("<td>Место обнаружения</td>");
                out.println("<td>Неисправность</td>");
                out.println("<td>Срок устранения</td>");
                out.println("<td>Мера безопасности</td>");
                out.println("<td>Ответственные</td>");
                out.println("<td>Дата устранения</td>");
                out.println("</tr>");
                while (rs11.next()) {
                    out.println("<tr style='background-color:#f0f0f0; color:#222; font-family:Verdana; font-size:12px;'>");
                    out.println("<td>" + rs11.getString("nomer") + "</td>");
                    out.println("<td>" + rs11.getString("nameTexsredstv") + "</td>");
                    out.println("<td>" + rs11.getString("nameSmall") + "</td>");
                    out.println("<td>" + rs11.getString("srokUstranenie") + "</td>");
                    out.println("<td>" + "&nbsp;" + rs11.getString("nameMeri") + "</td>");
                    out.println("<td>" + rs11.getString("namePredpriyatie") + "</td>");
                    out.println("<td>&nbsp;</td>");
                    out.println("</tr>");
                }
                out.println("</table><br />");

            }
            out.println("</div>");


            out.println("");
            out.println("");

            out.println("</div>");
            out.println("</form>");
            //conteyner tugadi--------------------------------------------------------------------------------------
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            out.println("");
        } finally {
            out.close();
        }
    }

    private void style(PrintWriter out) {
        out.println("<style>");
        out.println("body{margin: 0;background-color: #F8F9FA;}");
        out.println("#maintable{border: none;width: 100%;}");
        out.println(".logotip{width: auto;height: auto;border: 1px solid #CCCCCC;margin: 5px;background-color: #00A8D3;}");
        out.println(".logotip p{font-family: Arial, Helvetica, sans-serif;font-size: 16px;font-weight: bold;text-align: center;}");
        out.println(".aaa{font-size: 14px;text-decoration: none;font-family: Arial, Helvetica, sans-serif;color: #f5f5f5;"
                + "display: block;padding: 15px 0 15px 0;border: none;background-color: #3969AA;font-weight: bold;}");
        out.println(".aaa:hover{font-size: 14px;text-decoration: none;font-family: Arial, Helvetica, sans-serif;"
                + "color: #fff;display: block;padding: 15px 0 15px 0;border: none;background-color: #448BCA;font-weight: bold;}");
        out.println(".left{min-height:450px;margin: 5px 0 5px 5px;border: 1px solid #ccc;width: 238px;position: absolute;top: 70px;"
                + "background-color: #F8F8F8;padding: 5px 5px 10px 5px;}");
        out.println(".conteiner{min-height:470px; margin: 11px 5px 5px 265px;border: 1px solid #ccc;position: relative;width: auto;"
                + "min-width: 500px;background-color: #F8F8F8;padding: 0;}");
        out.println(".atopmenu{text-decoration:none; font-size:14px; font-family:Verdana; display:block; color:#444;"
                + "background-color:#DEE1E2; padding:8px 5px; border:1px solid #CCCCCC;}");
        out.println(".atopmenu:hover{text-decoration:none; font-size:14px; font-family:Verdana; display:block; color:#fff;"
                + "background-color:#bbb; padding:8px 5px; border:1px solid #555;}");
        out.println(".td1{background-color:#CCCCFE;}");
        out.println(".td2{background-color:#F0F0FE;}");
        out.println(".td3{background-color:#f0f0f0;}");
        out.println(".select1{width: 100%;}");

        out.println("</style>");
    }
}
