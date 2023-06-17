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

@WebServlet(name = "report", urlPatterns = {"/report"})
public class Report extends HttpServlet {
    Connection con = PostgresConnection.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getReport(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getReport(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    protected void getReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Справочно-аналитические отчеты</title>");
            out.println("</head>");
            style(out); // style
            out.println("<body>"); // body
            out.println("<table id='maintable' cellpadding='0' cellspacing='0'>");
            out.println("<tr><td height='50px' width='260px'>");
            out.println("<div class='logotip'><p>АС КМО</p></div></td>");
            out.println("<td>");
            out.println("<table width='100%' height='100%' border='0' cellpadding='0' cellspacing='5'>");
            out.println("<tr align='center'>");
            out.println("<td width='33%'><a href='inspection' class='aaa'>Комиссионные осмотры станций</a></td>");
            out.println("<td width='33%'><a href='report' class='aaa'>Справочно-аналитические отчеты</a></td>");
            out.println("<td width='33%'><a href='certificate' class='aaa'>Нормативно-справочная информация</a></td>");
            out.println("</tr></table></td></tr></table>");
            //top menu tugadi

            //left menu boshlanishi
            out.println("<div class='left'>");


            out.println("</div>");
            //left menu tugadi
            //conteyner boshi----------------------------------------------------------------------------------------
            out.println("<div class='conteiner' align='left'>");
            out.println("<form id='form1' name='form1' method='get' action=''>");
            out.println("<h4 align='center'>Общий анализ результатов комиссионных осмотров станции</h4>");
            out.println("<table border='1' cellspacing='0' cellpadding='3'>");
            out.println("<tr><td rowspan=5 width='90px' align='center' style='background-color:#CCCCFE;'>Выбрать</td>");
            out.println("<td style='background-color:#F0F0FE;'>");
            out.println("<input name='pred' type='checkbox' value='1' /><label for='checkbox'>По предприятию</label>&nbsp;&nbsp;");
            out.println("<select name='predpriyatie' id='predpriyatie'>");
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery("select * from predpriyatie where typePredpriyatie!=1 order by id ASC");
            out.println("<option value=''></option>");
            while (rs1.next()) {
                String s = rs1.getString(1);
                String s1 = rs1.getString(2);
                out.println("<option value='" + s + "'>" + s1 + "</option>");

            }

            out.println("</select>&nbsp;&nbsp;&nbsp;&nbsp;");
            out.println("<input type='checkbox' name='stan' id='stan' value='1' /><label for='radio'>&nbsp;По станции</label>&nbsp;&nbsp;");
            out.println("<select name='stansiya' id='stansiya'>");
            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery("select * from predpriyatie where typePredpriyatie=1 order by namePredpriyatie ASC");
            out.println("<option value=''></option>");
            while (rs2.next()) {
                String s = rs2.getString(1);
                String s1 = rs2.getString(2);
                out.println("<option value='" + s + "'>" + s1 + "</option>");

            }

            out.println("</select>");
            out.println("</td></tr>");
            out.println("<tr style='background-color:#F0F0FE;'><td>");
            out.println("<input type='checkbox' name='srok' id='srok'  value='1'/><label for='checkbox'>По состаянию устранения:&nbsp; </label>");
            out.println("<input type='radio' name='ustranen' id='ustranen'  value='0'/><label for='radio'>&nbsp;Устраненные&nbsp; </label>");
            out.println("<input type='radio' name='ustranen' id='ustranen'  value='1'/><label for='radio'>&nbsp;Неустраненные&nbsp; </label>");

            out.println("</td></tr>");
            out.println("<tr style='background-color:#F0F0FE;'><td>");
            out.println("<input type='checkbox' name='meri' id='meri'  value='1'/><label for='checkbox'>По мере обеспечения безопасности:&nbsp; </label>");
            out.println("<input type='checkbox' name='zakrit' id='zakrit'  value='0'/><label for='checkbox'>&nbsp;Закрытие движения&nbsp;&nbsp; </label>");
            out.println("<input type='checkbox' name='skorost' id='skorost'  value='1'/><label for='checkbox'>&nbsp;Ограничение скорости движения&nbsp; </label>");
            out.println("</td></tr>");
            out.println("<tr style='background-color:#F0F0FE;'><td>");
            out.println("<input type='checkbox' name='neis' id='neis'  value='1'/><label for='checkbox'>По виду неисправности&nbsp; </label>");
            out.println("<select name='neispravnost' id='neispravnost'>");
            Statement st4 = con.createStatement();
            String ss = request.getParameter("tex_sredstv");
            ResultSet rs4 = st4.executeQuery("select * from neispravnosti order by idNeispravnosti ASC");
            out.println("<option value=''></option>");
            while (rs4.next()) {
                String s = rs4.getString(1);
                String s1 = rs4.getString(3);
                String s2 = rs4.getString(2);
                out.println("<option value='" + s + "' style='color:#444'>" + s2 + ". " + s1 + "</option>");

            }
            out.println("</select>&nbsp;&nbsp;&nbsp;&nbsp;");
            out.println("</td></tr>");
            out.println("<tr style='background-color:#F0F0FE;'><td>");
            out.println("<input type='checkbox' name='tex_sredstv2' id='tex_sredstv2'  value='1'/><label for='checkbox'>По техническому устройству&nbsp; </label>");
            out.println("<select name='tex_sredstv' id='tex_sredstv'>");
            Statement st5 = con.createStatement();
            ResultSet rs5 = st5.executeQuery("select * from texsredstv order by idTexsredstv ASC");
            out.println("<option value=''></option>");
            while (rs5.next()) {
                String s = rs5.getString(1);
                String s1 = rs5.getString(2);
                out.println("<option value='" + s + "' style='color:#444'>" + s1 + "</option>");

            }
            out.println("</select>&nbsp;&nbsp;&nbsp;&nbsp;");
            out.println("</td></tr>");
            out.println("</table><br />");
            out.println("<input type='submit' name='dobavit' id='dobavit' value='OK' />");
            out.println("<input type='submit' name='ochistit' id='ochistit' value='Очистить все' />");

            out.println("<div><br>");//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

            if (request.getParameter("dobavit").equals("OK")) {
                Statement st = con.createStatement();
                String ss1 = request.getParameter("predpriyatie");
                String ss2 = request.getParameter("stansiya");
                String ss3 = request.getParameter("neispravnost");
                String ss4 = request.getParameter("tex_sredstv");

                if (request.getParameter("tex_sredstv2").equals("1")) {
                    ResultSet rs = st.executeQuery("select * from du46 d, texsredstv t, neispravnosti n, meri m, predpriyatie p, akt a where d.texSredstv=t.idTexsredstv and d.neispravnosti=n.idNeispravnosti and d.prinyatieMeri=m.idMeri and d.otvetsvennie=p.id and d.idAkt=a.id and d.texSredstv='" + ss4 + "'");
                    out.println("<table border='1' cellspacing='0' cellpadding='4'>");
                    out.println("<tr align='center' style='background-color:#e0e0e0; color:#333; font-family:Verdana; font-size:12px; font-weight:bold;'>");
                    out.println("<td>№</td>");
                    out.println("<td>Место обнаружения</td>");
                    out.println("<td>Неисправность</td>");
                    out.println("<td>Срок устранения</td>");
                    out.println("<td>Мера безопасности</td>");
                    out.println("<td>Ответственные</td>");
                    out.println("<td>Способ устранения</td>");
                    out.println("<td>Дата устранения</td>");
                    out.println("</tr>");
                    while (rs.next()) {
                        out.println("<tr style='background-color:#f0f0f0; color:#222; font-family:Verdana; font-size:12px;'>");
                        out.println("<td>" + rs.getString("nomer") + "</td>");
                        out.println("<td>" + rs.getString("nameTexsredstv") + "</td>");
                        out.println("<td>" + rs.getString("nameSmall") + "</td>");
                        out.println("<td>" + rs.getString("srokUstranenie") + "</td>");
                        out.println("<td>" + "&nbsp;" + rs.getString("nameMeri") + "</td>");
                        out.println("<td>" + rs.getString("namePredpriyatie") + "</td>");
                        out.println("<td>" + "&nbsp;" + rs.getString("sposobUstranenie") + "</td>");
                        out.println("<td>" + "&nbsp;" + rs.getString("dataUstranenie") + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table><br />");
                }


            }
            out.println("</div>");//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
            out.println("");
            out.println("</form></div>");
            out.println("");
            //conteyner tugadi---------------------------------------------------------------------------------------
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            out.println(e);
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
        out.println(".left{min-height:450px;margin: 5px 0 5px 5px;border: 1px solid #ccc;width: 238px;position: absolute;top: 70px;" + "background-color: #F8F8F8;padding: 10px 5px 10px 5px;}");
        out.println(".conteiner{min-height:430px;margin: 11px 5px 5px 265px;border: 1px solid #ccc;position: relative;width: auto;" + "min-width: 500px;background-color: #F8F8F8;padding: 20px;}");
        out.println("</style>");
    }

}
