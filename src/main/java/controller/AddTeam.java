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


@WebServlet(name = "add-team", urlPatterns = {"/add-team"}) // /dobavit_sostav
public class AddTeam extends HttpServlet {
    Connection con = PostgresConnection.getInstance();
    String akt;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addTeamRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addTeamRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    protected void addTeamRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Добавления акта осмотра</title>");
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
            out.println("<div class='left'>");


            out.println("</div>");
            //left menu tugadi-----------------------------------------------------
            //conteyner boshi---------------------------------------------------------------------------------------------------------------------------
            out.println("<div class='conteiner'>");
            out.println("<table border='0' cellpadding='0' cellspacing='3' width='100%' align='center'><tr align='center'>");
            out.println("<td><a href='add-act' class='atopmenu'>Добавить акт</a></td>");
            out.println("<td><a href='add-team' class='atopmenu'>Состав комиссии</a></td>");
            out.println("<td><a href='add-failure' class='atopmenu'>Добавить неисправности</a></td>");
            out.println("<td><a href='personal-events' class='atopmenu'>Устранение замечания</a></td>");
            out.println("<td><a href='inspection' class='atopmenu'>Контроль устранения</a></td>");
            out.println("</tr></table>");
            //out.println("");
            //------------------------------------------------------------------------------------------------------
            out.println("<form id='form1' name='form1' method='get' action=''>");
            out.println("<div>");
            out.println("<h4 align='center'>Состав комиссии по осмотру станции. Председатель</h4>");
            out.println("<table border='1' cellspacing='0' cellpadding='3'>");
            out.println("<tr>");
            out.println("<th scope='col'>РЖУ</th>");
            out.println("<th scope='col'>Станция</th>");
            out.println("<th scope='col'>Акт</th>");
            out.println("<th scope='col'>Предприятие</th>");
            out.println("<th scope='col'>Должность</th>");
            out.println("<th scope='col'>Фамилия И.О.</th>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><select class='select1' name='sostavrju' size='10' multiple='MULTIPLE' onchange='javascript:form1.submit();' onselect='javascript:form1.submit();'>");

            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery("select * from mtu order by idMTU");
            while (rs1.next()) {
                String s = rs1.getString(1);
                String s1 = rs1.getString(2);
                String sss = "";
                if (request.getParameter("sostavrju") != null)
                    sss = ((request.getParameter("sostavrju").equals(s)) ? "selected=true" : "");
                out.println("<option value='" + s + "' " + sss + ">" + s1 + "</option>");

            }

            out.println("</select></td>");
            st1.close();
            out.println("<td><select class='select1' name='sostavstansiya' size='10' multiple='MULTIPLE' onchange='javascript:form1.submit();' onselect='javascript:form1.submit();'>");

            Statement st = con.createStatement();
            String sss = request.getParameter("sostavrju");
            ResultSet rs = st.executeQuery("select * from predpriyatie where idMTU='" + sss + "' and typePredpriyatie=1 order by namePredpriyatie ASC");
            while (rs.next()) {
                String s = rs.getString("id");
                String s1 = rs.getString("namePredpriyatie");
                String sss1 = "";
                if (request.getParameter("sostavstansiya") != null)
                    sss1 = ((request.getParameter("sostavstansiya").equals(s)) ? "selected=true" : "");
                out.println("<option value='" + s + "' " + sss1 + ">" + s1 + "</option>");

            }

            out.println("</select></td>");
            st.close();
            out.println("<td><select class='select1' name='sostavakt' size='10' multiple='MULTIPLE'>");

            Statement st2 = con.createStatement();
            String stan = request.getParameter("sostavstansiya");
            ResultSet rs2 = st2.executeQuery("select * from akt where nameStansiya='" + stan + "'");
                while (rs2.next()) {
                    String s = rs2.getString("id");
                String s1 = rs2.getString("nachaloProved");
                String s2 = rs2.getString("tipOsmotr");
                String sss3 = "";
                if (request.getParameter("sostavakt") != null)
                    sss3 = ((request.getParameter("sostavakt").equals(s)) ? "selected=true" : "");
                out.println("<option value='" + s + "' " + sss3 + ">" + s1 + "</option>");

            }

            out.println("</select></td>");
            st2.close();
            out.println("<td><select class='select1' name='sostavpredpriyatie' size='10' multiple='MULTIPLE' onchange='javascript:form1.submit();' onselect='javascript:form1.submit();'>");
            Statement st8 = con.createStatement();
            ResultSet rs8 = st8.executeQuery("select * from predpriyatie where typePredpriyatie!=1");
            while (rs8.next()) {
                String s = rs8.getString(1);
                String s1 = rs8.getString(2);
                String sss3 = "";
                if (request.getParameter("sostavpredpriyatie") != null)
                    sss3 = ((request.getParameter("sostavpredpriyatie").equals(s)) ? "selected=true" : "");
                out.println("<option value='" + s + "' " + sss3 + ">" + s1 + "</option>");

            }
            st8.close();


            out.println("</select></td>");
            out.println("<td><select class='select1' name='sostavdoljnost' size='10' multiple='MULTIPLE' onchange='javascript:form1.submit();' onselect='javascript:form1.submit();'>");


            Statement st9 = con.createStatement();
            ResultSet rs9 = st9.executeQuery("select * from doljnost where idTypepred!=1");
            while (rs9.next()) {
                String s = rs9.getString(1);
                String s1 = rs9.getString(2);
                String sss3 = "";
                if (request.getParameter("sostavdoljnost") != null)
                    sss3 = ((request.getParameter("sostavdoljnost").equals(s)) ? "selected=true" : "");
                out.println("<option value='" + s + "' " + sss3 + ">" + s1 + "</option>");

            }
            st9.close();

            out.println("</select></td>");

            out.println("<td><select class='select1' name='sostavfio' size='10' multiple='MULTIPLE'>");
            String sd = request.getParameter("sostavdoljnost");
            String sp = request.getParameter("sostavpredpriyatie");
            Statement st10 = con.createStatement();
            ResultSet rs10 = st10.executeQuery("select * from shtat where doljnost='" + sd + "' and idPredpriyatie='" + sp + "'");
            while (rs10.next()) {
                String s = rs10.getString(1);
                String s1 = rs10.getString(2);
                String s2 = rs10.getString(3);
                String s3 = rs10.getString(4);
                out.println("<option value='" + s + "'>" + s1 + " " + s2 + ". " + s3 + "." + "</option>");

            }
            st10.close();
            out.println("</select></td>");
            out.println("</tr></table>");
            out.println("<br />");
            out.println("<input name='sostavdobavit' type='submit' value='OK' />");
            out.println("<input name='sostavotmena' type='submit' value='Исключить' />");

            out.println("");


            out.println("<div style='padding-left:55px;'>");
            if (request.getParameter("sostavdobavit").equals("OK")) {
                String ss8 = request.getParameter("sostavpredpriyatie");
                String ss1 = request.getParameter("sostavdoljnost");
                String ss2 = request.getParameter("sostavfio");
                String ss3 = request.getParameter("sostavakt");
                Statement st6 = con.createStatement();
                int rs6 = st6.executeUpdate("insert into sostavkomissi (predpriyatie,doljnost,fio,idAkt) " + "values ('" + ss8 + "','" + ss1 + "','" + ss2 + "','" + ss3 + "')");


            }
            {
                out.println("<br />");
                Statement st11 = con.createStatement();

                ResultSet rs11 = st11.executeQuery("SELECT * FROM sostavkomissi s, shtat sh, predpriyatie p, doljnost d, akt a where s.predpriyatie=p.id and s.doljnost=d.idDoljnost and s.fio=sh.idShtat and s.idAkt=a.id and s.id=(select max(id) from sostavkomissi)");
                out.println("<table border='1' cellspacing='0' cellpadding='2' width='600px'>");
                out.println("<tr align='center' style='background-color:#e0e0e0; color:#333; font-family:Verdana;'>");
                out.println("<td>Предприятие</td>");
                out.println("<td>Должность</td>");
                out.println("<td>Ф.И.О</td>");
                out.println("</tr>");
                while (rs11.next()) {
                    out.println("<tr style='background-color:#f0f0f0; color:#222; font-family:Verdana;'>");
                    out.println("<td>" + rs11.getString("namePredpriyatie") + "</td>");
                    out.println("<td>" + rs11.getString("nameDoljnost") + "</td>");
                    out.println("<td>" + rs11.getString("family") + " " + rs11.getString("sh.name") + "." + rs11.getString("sh.lastname") + "." + "</td>");
                    out.println("</tr>");
                }
                out.println("</table><br />");

            }

            out.println("</div>");

            out.println("</div>");
            out.println("</form>");


            out.println("");

            out.println("</div>");
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
        out.println(".aaa{font-size: 14px;text-decoration: none;font-family: Arial, Helvetica, sans-serif;color: #f5f5f5;" + "display: block;padding: 15px 0 15px 0;border: none;background-color: #3969AA;font-weight: bold;}");
        out.println(".aaa:hover{font-size: 14px;text-decoration: none;font-family: Arial, Helvetica, sans-serif;" + "color: #fff;display: block;padding: 15px 0 15px 0;border: none;background-color: #448BCA;font-weight: bold;}");
        out.println(".left{min-height:450px;margin: 5px 0 5px 5px;border: 1px solid #ccc;width: 238px;position: absolute;top: 70px;" + "background-color: #F8F8F8;padding: 5px 5px 10px 5px;}");
        out.println(".conteiner{min-height:470px; margin: 11px 5px 5px 265px;border: 1px solid #ccc;position: relative;width: auto;" + "min-width: 500px;background-color: #F8F8F8;padding: 0;}");
        out.println(".atopmenu{text-decoration:none; font-size:14px; font-family:Verdana; display:block; color:#444;" + "background-color:#DEE1E2; padding:8px 5px; border:1px solid #CCCCCC;}");
        out.println(".atopmenu:hover{text-decoration:none; font-size:14px; font-family:Verdana; display:block; color:#fff;" + "background-color:#bbb; padding:8px 5px; border:1px solid #555;}");
        out.println(".select1{min-width:120px;}");
        out.println("</style>");
    }
}
