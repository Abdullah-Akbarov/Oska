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


@WebServlet(name = "inspection", urlPatterns = {"/inspection"})
public class Inspection extends HttpServlet {
    Connection con = PostgresConnection.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Комиссионные осмотры станций</title>");
            out.println("</head>");
            inspectionStyle(out);
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
                String s = rs2.getString("id");
                String s1 = rs2.getString("nachaloProved");
                String s2 = rs2.getString("tipOsmotr");
                String sss3 = "";
                if (request.getParameter("nakt") != null)
                    sss3 = ((request.getParameter("nakt").equals(s)) ? "selected=true" : "");
                out.println("<option value='" + s + "' " + sss3 + ">" + s1 + " (" + s2 + ")" + "</option>");
            }
            out.println("</select></td>");
            st2.close();
            out.println("</div>");
            out.println("<div class='conteiner' align='center'>");
            out.println("<table border='0' cellpadding='0' cellspacing='3' width='100%' align='center'><tr align='center'>");
            out.println("<td><a href='add-act' class='atopmenu'>Добавить акт</a></td>");
            out.println("<td><a href='add-failure' class='atopmenu'>Добавить неисправности</a></td>");
            out.println("<td><a href='troubleshooting' class='atopmenu'>Устранение замечания</a></td>");
            out.println("</tr></table>");
            if (!request.getParameter("nakt").equals("")) {
                out.println("<h4 align='center'>Акты комиссионного осмотра станции</h4>");
                Statement st11 = con.createStatement();
                String osmotr = request.getParameter("nakt");
                ResultSet rs11 = st11.executeQuery("SELECT * FROM akt a, predpriyatie p, shtat sh, doljnost d "
                        + "where  a.nameStansiya=p.id and a.predsedatel=sh.idShtat and sh.doljnost=d.idDoljnost and a.id='" + osmotr + "'");
                out.println("<table border='1' cellspacing='0' cellpadding='2' width='800px'>");
                out.println("<tr align='center' class='tdd1'>");
                out.println("<td rowspan='2'>Станция</td>");
                out.println("<td colspan='2'>Сроки проведения осмотра</td>");
                out.println("<td rowspan='2'>Председатель комисси</td>");
                out.println("<td rowspan='2'>Дата отмены</td>");
                out.println("</tr>");
                out.println("<tr align='center' class='tdd1'>");
                out.println("<td>Начало</td>");
                out.println("<td>Окончание</td>");
                out.println("</tr>");
                while (rs11.next()) {
                    out.println("<tr class='tdd2'>");
                    out.println("<td>" + rs11.getString("namePredpriyatie") + "</td>");
                    out.println("<td>" + rs11.getString("nachaloProved") + "</td>");
                    out.println("<td>" + rs11.getString("okonchaniyaProved") + "</td>");
                    out.println("<td>" + rs11.getString("family") + " " + rs11.getString("sh.name") + "." + rs11.getString("sh.lastname") + ".  (" + rs11.getString("d.nameDoljnost") + ")" + "</td>");
                    out.println("<td>&nbsp;</td>");
                    out.println("</tr>");
                }
                out.println("</table><br />");

            }
            out.println("");
            out.println("");
            out.println("");

            out.println("</div></form>");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            out.println("");
        } finally {
            out.close();
        }
    }

    private void inspectionStyle(PrintWriter out) {
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
        out.println(".conteiner{min-height:500px; margin: 11px 5px 5px 265px;border: 1px solid #ccc;position: relative;width: auto;"
                + "min-width: 505px; background-color: #F8F8F8;padding: 0;}");
        out.println(".atopmenu{text-decoration:none; font-size:14px; font-family:Verdana; display:block; color:#444;"
                + "background-color:#DEE1E2; padding:8px 5px; border:1px solid #CCCCCC;}");
        out.println(".atopmenu:hover{text-decoration:none; font-size:14px; font-family:Verdana; display:block; color:#fff;"
                + "background-color:#bbb; padding:8px 5px; border:1px solid #555;}");
        out.println(".select1{width:100%;}");
        out.println(".tdd1{background-color:#eee; font-size:12px; font-family:Verdana; color:#444; font-weight:bold;}");
        out.println(".tdd2{background-color:#f7f7f7; font-size:12px; font-family:Verdana; color:#444;}");
        out.println("</style>");
    }
}
