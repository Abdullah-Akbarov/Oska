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

@WebServlet(name = "klassifikator", urlPatterns = {"/klassifikator-failures", "/klassifikator-events", "/klassifikator-station"})
public class Klassifikator extends HttpServlet {
    Connection con = PostgresConnection.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/klassifikator-failures":
                klassifikatorFailuresRequest(request, response);
                break;
            case "/klassifikator-events":
                klassifikatorEventsRequest(request, response);
                break;
            case "/klassifikator-station":
                klassifikatorStationRequest(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    protected void klassifikatorFailuresRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Классификатор видов неисправностей</title>");
            out.println("</head>");
            //style boshi
            out.println("<style>");
            out.println("body{margin: 0;background-color: #F8F9FA;}");
            out.println("#maintable{border: none;width: 100%;}");
            out.println(".logotip{width: auto;height: auto;border: 1px solid #CCCCCC;margin: 5px;background-color: #00A8D3;}");
            out.println(".logotip p{font-family: Arial, Helvetica, sans-serif;font-size: 16px;font-weight: bold;text-align: center;}");
            out.println(".aaa{font-size: 14px;text-decoration: none;font-family: Arial, Helvetica, sans-serif;color: #f5f5f5;" + "display: block;padding: 15px 0 15px 0;border: none;background-color: #3969AA;font-weight: bold;}");
            out.println(".aaa:hover{font-size: 14px;text-decoration: none;font-family: Arial, Helvetica, sans-serif;" + "color: #fff;display: block;padding: 15px 0 15px 0;border: none;background-color: #448BCA;font-weight: bold;}");
            out.println(".left{margin: 5px 0 5px 5px;border: 1px solid #ccc;width: 248px; position: absolute;top: 70px;" + "background-color: #F8F8F8;padding: 20px 0px 20px 0px; min-height:450px;}");
            out.println(".conteiner{margin: 11px 5px 5px 265px;border: 1px solid #ccc;position: relative;width: auto;" + "min-width: 500px;background-color: #F8F8F8;padding: 20px; min-height:450px; font-family:Verdana, Tahoma, Geneva;}");
            out.println(".conteiner p{text-align: center; font-size:16px; color:#666;}");
            out.println(".ssilka {font-weight: normal; font-size:12px; color:#333; font-family: 'PT Serif', Verdana, Tahoma; " + "text-decoration: none; display:block; border-bottom: 1px dotted #333; padding:10px 0 10px 5px;" + "}");
            out.println(".ssilka:hover {font-weight: normal; font-size:12px; color:#333; font-family: 'PT Serif', Verdana, Tahoma; " + "text-decoration: none; display:block; border-bottom: 1px dotted #333; padding:10px 0 10px 5px;" + "background-color: #efefef;}");
            out.println("#tdaa {border: 1px solid #d0d0d0; background-color: #EEEDEB;}");
            out.println("#tdab {border-top: 1px solid #d0d0d0; border-bottom: 1px solid #d0d0d0; border-right: 1px solid #d0d0d0; background-color: #EEEDEB;}");
            out.println("#tdaa, #tdab {color: #666; font-size:14px; font-family: Verdana, Tahoma, Geneva; font-weight:bold;}");
            out.println("#tdba, #tdbb {color: #666; font-size:14px; font-family: Verdana, Tahoma, Geneva; padding-left: 10px;}");
            out.println("#tdba {border-left:1px solid #d0d0d0; border-bottom:1px solid #d0d0d0; border-right:1px solid #d0d0d0;}");
            out.println("#tdbb {border-right:1px solid #d0d0d0; border-bottom:1px solid #d0d0d0;}");

            out.println("</style>");

            //style tugadi
            //body boshi
            out.println("<body>");
            out.println("<table id='maintable' cellpadding='0' cellspacing='0'>");
            out.println("<tr><td height='50px' width='260px'>");
            out.println("<div class='logotip'><p>АС КМО</p></div></td>");
            out.println("<td>");
            out.println("<table width='100%' height='100%' border='0' cellpadding='0' cellspacing='5'>");
            out.println("<tr align='center'>");
            out.println("<td width='33%'><a href=inspection' class='aaa'>Комиссионные осмотры станций</a></td>");
            out.println("<td width='33%'><a href='report' class='aaa'>Справочно-аналитические отчеты</a></td>");
            out.println("<td width='33%'><a href='faces/welcomeJSF.jsp' class='aaa'>Нормативно-справочная информация</a></td>");
            out.println("</tr></table></td></tr></table>");
            //top menu tugadi

            //left menu boshlanishi
            out.println("<div class='left'>");

            links(out);


            out.println("</div>");
            //left menu tugadi------------------------------------------------------------------

            //conteyner boshi-------------------------------------------------------------------
            out.println("<div class='conteiner'>");

            out.println("<p>Классификатор неисправностей технических средств станции</p>");

            out.println("<form action='' method='post' name='form1'>");
            out.println("<table border='0' cellpadding='0' cellspacing='20'>");

            out.println("<tr><td>Поиск по технических средств:   </td>");
            out.println("<td><select name='tex_sredstv' onchange='javascript:form1.submit();' onselect='javascript:form1.submit();'>");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from texsredstv");
            out.println("<option value=''></option>");
            out.println("<option value='0'>Все</option>");
            while (rs.next()) {
                String s = rs.getString(1);
                String s2 = rs.getString(2);
                String sss3 = "";
                if (request.getParameter("tex_sredstv") != null)
                    sss3 = ((request.getParameter("tex_sredstv").equals(s)) ? "selected=true" : "");
                out.println("<option value='" + s + "' " + sss3 + ">" + s2 + "</option>");
                out.println(" ");
            }

            out.println("</select></td></tr>");
            out.println("</table>");
            try {
                int n = 0;
                String ss1 = request.getParameter("tex_sredstv");
                Statement st2 = con.createStatement();
                if ((request.getParameter("tex_sredstv").equals("0"))) {
                    ResultSet rs2 = st2.executeQuery("select * from neispravnosti n, texsredstv t where n.idTexsredstv=t.idTexsredstv");
                    {

                        out.println("<table border='0' cellspacing='0' cellpadding='3'>");
                        out.println("<tr align='center'>");
                        out.println("<td id='tdaa'>п/п</td>");
                        out.println("<td id='tdab'>Вид неисправности</td>");
                        out.println("<td id='tdab'>Тип технических средств</td>");
                        out.println("</tr>");

                        while (rs2.next()) {

                            {
                                out.println("<tr>");
                                out.println("<td align='center' id='tdba'>" + rs2.getString("n.nomerpp") + "</td>");
                                out.println("<td id='tdbb'>" + rs2.getString("nameNeispravnosti") + "</td>");
                                out.println("<td id='tdbb'>" + rs2.getString("nameTexsredstv") + "</td>");
                                out.println("</tr>");
                            }

                        }
                        out.println("</table>");

                    }
                } else {
                    ResultSet rs2 = st2.executeQuery("select * from neispravnosti n, texsredstv t where " + "n.idTexsredstv in ('" + ss1 + "') and n.idTexsredstv=t.idTexsredstv");

                    {

                        out.println("<table border='0' cellspacing='0' cellpadding='3'>");
                        out.println("<tr align='center'>");
                        out.println("<td id='tdaa'>п/п</td>");
                        out.println("<td id='tdab'>Вид неисправности</td>");
                        out.println("<td id='tdab'>Тип технических средств</td>");
                        out.println("</tr>");

                        while (rs2.next()) {

                            {
                                out.println("<tr>");
                                out.println("<td align='center' id='tdba'>" + rs2.getString("n.nomerpp") + "</td>");
                                out.println("<td id='tdbb'>" + rs2.getString("nameNeispravnosti") + "</td>");
                                out.println("<td id='tdbb'>" + rs2.getString("nameTexsredstv") + "</td>");
                                out.println("</tr>");
                            }

                        }
                        out.println("</table>");

                    }
                }


            } catch (Exception e) {
                out.println("");
            } finally {
                out.close();
            }


            out.println("</form>");
            out.println("</div>");

            //conteyner tugadi
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            out.println(e);
        } finally {
            out.close();
        }
    }

    protected void klassifikatorEventsRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Классификатор предприятий</title>");
            out.println("</head>");
            style(out);
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
            out.println("<td width='33%'><a href='certificate' class='aaa'>Нормативно-справочная информация</a></td>");
            out.println("</tr></table></td></tr></table>");
            //top menu tugadi

            //left menu boshlanishi
            out.println("<div class='left'>");
            links(out);

            out.println("</div>");
            //left menu tugadi
            //conteyner boshi
            out.println("<div class='conteiner' align='center'>");


            out.println("<p>Классификатор станций железной дороги</p>");
            {

                Statement st2 = con.createStatement();
                ResultSet rs2 = st2.executeQuery("select * from predpriyatie p, mtu m where p.idMTU=m.idMTU and p.typePredpriyatie >1 ORDER BY p.id ASC");
                out.println("<table border='0' cellspacing='0' cellpadding='3' width='60%'>");
                out.println("<tr align='center'>");
                out.println("<td id='tdaa'>РЖУ</td>");
                out.println("<td id='tdab'>Сокращенное наименование</td>");
                out.println("</tr>");
                while (rs2.next()) {
                    {
                        out.println("<tr>");
                        out.println("<td id='tdba'>" + rs2.getString("nameMTU") + "</td>");
                        out.println("<td id='tdbb'>" + rs2.getString("namePredpriyatie") + "</td>");
                        out.println("</tr>");
                    }
                }

                out.println("</table>");

            }


            out.println("</div>");
            //conteyner tugadi
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            out.println(e);
        } finally {
            out.close();
        }
    }

    protected void klassifikatorStationRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Классификатор станций</title>");
            out.println("</head>");
            //style boshi
            out.println("<style>");
            out.println("body{margin: 0;background-color: #F8F9FA;}");
            out.println("#maintable{border: none;width: 100%;}");
            out.println(".logotip{width: auto;height: auto;border: 1px solid #CCCCCC;margin: 5px;background-color: #00A8D3;}");
            out.println(".logotip p{font-family: Arial, Helvetica, sans-serif;font-size: 16px;font-weight: bold;text-align: center;}");
            out.println(".aaa{font-size: 14px;text-decoration: none;font-family: Arial, Helvetica, sans-serif;color: #f5f5f5;" + "display: block;padding: 15px 0 15px 0;border: none;background-color: #3969AA;font-weight: bold;}");
            out.println(".aaa:hover{font-size: 14px;text-decoration: none;font-family: Arial, Helvetica, sans-serif;" + "color: #fff;display: block;padding: 15px 0 15px 0;border: none;background-color: #448BCA;font-weight: bold;}");
            out.println(".left{margin: 5px 0 5px 5px;border: 1px solid #ccc;width: 248px;position: absolute;top: 70px;" + "background-color: #F8F8F8;padding: 20px 0px 20px 0px; min-height:450px;}");
            out.println(".conteiner{margin: 11px 5px 5px 265px;border: 1px solid #ccc;position: relative;width: auto;" + "min-width: 500px;background-color: #F8F8F8;padding: 20px; min-height:450px; font-family:Verdana;}");
            out.println(".ssilka {font-weight: normal; font-size:12px; color:#333; font-family: 'PT Serif', Verdana, Tahoma; " + "text-decoration: none; display:block; border-bottom: 1px dotted #333; padding:10px 0 10px 5px;" + "}");
            out.println(".ssilka:hover {font-weight: normal; font-size:12px; color:#333; font-family: 'PT Serif', Verdana, Tahoma; " + "text-decoration: none; display:block; border-bottom: 1px dotted #333; padding:10px 0 10px 5px;" + "background-color: #efefef;}");
            out.println("#tdaa {border: 1px solid #d0d0d0; background-color: #EEEDEB;}");
            out.println("#tdab {border-top: 1px solid #d0d0d0; border-bottom: 1px solid #d0d0d0; border-right: 1px solid #d0d0d0; background-color: #EEEDEB;}");
            out.println("#tdaa, #tdab {color: #666; font-size:14px; font-family: Verdana, Tahoma, Geneva; font-weight:bold;}");
            out.println("#tdba, #tdbb {color: #666; font-size:14px; font-family: Verdana, Tahoma, Geneva; padding-left: 20px;}");
            out.println("#tdba {border-left:1px solid #d0d0d0; border-bottom:1px solid #d0d0d0; border-right:1px solid #d0d0d0;}");
            out.println("#tdbb {border-right:1px solid #d0d0d0; border-bottom:1px solid #d0d0d0;}");
            out.println(".conteiner p{ font-size:16px; color:#666;}");

            out.println("</style>");

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
            out.println("<td width='33%'><a href='certificate' class='aaa'>Нормативно-справочная информация</a></td>");
            out.println("</tr></table></td></tr></table>");
            //top menu tugadi

            //left menu boshlanishi
            out.println("<div class='left'>");

            links(out);


            out.println("</div>");
            //left menu tugadi
            //conteyner boshi--------------------------------------------------------------------
            out.println("<div class='conteiner' align='center'>");
            out.println("<p>Классификатор станций железной дороги</p>");
            {

                Statement st2 = con.createStatement();
                ResultSet rs2 = st2.executeQuery("select * from predpriyatie p, mtu m where p.idMTU=m.idMTU and p.typePredpriyatie =1 ORDER BY p.id ASC");
                out.println("<table border='0' cellspacing='0' cellpadding='3' width='70%'>");
                out.println("<tr align='center'>");
                out.println("<td id='tdaa'>РЖУ</td>");
                out.println("<td id='tdab'>Станция</td>");
                out.println("<td id='tdab'>код станции по ЕСР</td>");
                out.println("</tr>");
                while (rs2.next()) {
                    {
                        out.println("<tr>");
                        out.println("<td id='tdba'>" + rs2.getString("nameMTU") + "</td>");
                        out.println("<td id='tdbb'>" + rs2.getString("namePredpriyatie") + "</td>");
                        out.println("<td id='tdbb'>" + rs2.getString("kodESR") + "</td>");
                        out.println("</tr>");
                    }
                }

                out.println("</table>");

            }
            out.println("</div>");
            //conteyner tugadi
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            out.println(e);
        } finally {
            out.close();
        }
    }

    private void links(PrintWriter out) {
        out.println("<a href='personal-events' class='ssilka'>Персонал всех предприятий</a>");
        out.println("<a href='klassifikator-station' class='ssilka'>Классификатор станций</a>");
        out.println("<a href='klassifikator-events' class='ssilka'>Классификатор предприятий</a>");
        out.println("<a href='station-service' class='ssilka'>Обслуживание станций предприятиями</a>");
        out.println("<a href='klassifikator-failures' class='ssilka'>Классификатор видов неисправностей</a>");
    }

    private void style(PrintWriter out) {
        out.println("<style>");
        out.println("body{margin: 0;background-color: #F8F9FA;}");
        out.println("#maintable{border: none;width: 100%;}");
        out.println(".logotip{width: auto;height: auto;border: 1px solid #CCCCCC;margin: 5px;background-color: #00A8D3;}");
        out.println(".logotip p{font-family: Arial, Helvetica, sans-serif;font-size: 16px;font-weight: bold;text-align: center;}");
        out.println(".aaa{font-size: 14px;text-decoration: none;font-family: Arial, Helvetica, sans-serif;color: #f5f5f5;" + "display: block;padding: 15px 0 15px 0;border: none;background-color: #3969AA;font-weight: bold;}");
        out.println(".aaa:hover{font-size: 14px;text-decoration: none;font-family: Arial, Helvetica, sans-serif;" + "color: #fff;display: block;padding: 15px 0 15px 0;border: none;background-color: #448BCA;font-weight: bold;}");
        out.println(".left{margin: 5px 0 5px 5px;border: 1px solid #ccc;width: 248px;position: absolute;top: 70px;" + "background-color: #F8F8F8;padding: 20px 0px 20px 0px; min-height:450px;}");
        out.println(".conteiner{margin: 11px 5px 5px 265px;border: 1px solid #ccc;position: relative;width: auto;" + "min-width: 500px;background-color: #F8F8F8;padding: 20px; min-height:450px; font-family:Verdana;}");
        out.println(".ssilka {font-weight: normal; font-size:12px; color:#333; font-family: 'PT Serif', Verdana, Tahoma; " + "text-decoration: none; display:block; border-bottom: 1px dotted #333; padding:10px 0 10px 5px;" + "}");
        out.println(".ssilka:hover {font-weight: normal; font-size:12px; color:#333; font-family: 'PT Serif', Verdana, Tahoma; " + "text-decoration: none; display:block; border-bottom: 1px dotted #333; padding:10px 0 10px 5px;" + "background-color: #efefef;}");
        out.println("#tdaa {border: 1px solid #d0d0d0; background-color: #EEEDEB;}");
        out.println("#tdab {border-top: 1px solid #d0d0d0; border-bottom: 1px solid #d0d0d0; border-right: 1px solid #d0d0d0; background-color: #EEEDEB;}");
        out.println("#tdaa, #tdab {color: #666; font-size:14px; font-family: Verdana, Tahoma, Geneva; font-weight:bold;}");
        out.println("#tdba, #tdbb {color: #666; font-size:14px; font-family: Verdana, Tahoma, Geneva; padding-left: 20px;}");
        out.println("#tdba {border-left:1px solid #d0d0d0; border-bottom:1px solid #d0d0d0; border-right:1px solid #d0d0d0;}");
        out.println("#tdbb {border-right:1px solid #d0d0d0; border-bottom:1px solid #d0d0d0;}");
        out.println(".conteiner p{ font-size:16px; color:#666;}");

        out.println("</style>");
    }
}
