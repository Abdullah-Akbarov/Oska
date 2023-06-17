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


@WebServlet(name = "station-service", urlPatterns = {"/station-service"}) // /obslujivanie_stansiy
public class StationService extends HttpServlet {
    Connection con = PostgresConnection.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        stationServiceRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        stationServiceRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    protected void stationServiceRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Обслуживания стаций предприятиями</title>");
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
            //conteyner boshi----------------------------------------------------------------------
            out.println("<div class='conteiner' align='center'>");
            out.println("<p>Классификатор обслуживания станций предприятиями дороги</p>");
            {

                Statement st2 = con.createStatement();
                ResultSet rs2 = st2.executeQuery("select * from klassifikator");
                out.println("<table border='0' cellspacing='0' cellpadding='3' width='90%'>");
                out.println("<tr align='center'>");
                out.println("<td id='tdaa'>Станция</td>");
                out.println("<td id='tdab'>ПЧ</td>");
                out.println("<td id='tdab'>ШЧ</td>");
                out.println("<td id='tdab'>ЭЧ</td>");
                out.println("</tr>");
                while (rs2.next()) {
                    {
                        out.println("<tr>");
                        out.println("<td id='tdba'>" + rs2.getString("nameStansiya") + "</td>");
                        out.println("<td id='tdbb'>" + rs2.getString("pch") + "</td>");
                        out.println("<td id='tdbb'>" + rs2.getString("shch") + "</td>");
                        out.println("<td id='tdbb'>" + rs2.getString("ech") + "</td>");
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
        out.println(".conteiner p{ font-size:16px; color:#666;}");
        out.println(".ssilka {font-weight: normal; font-size:12px; color:#333; font-family: 'PT Serif', Verdana, Tahoma; " + "text-decoration: none; display:block; border-bottom: 1px dotted #333; padding:10px 0 10px 5px;" + "}");
        out.println(".ssilka:hover {font-weight: normal; font-size:12px; color:#333; font-family: 'PT Serif', Verdana, Tahoma; " + "text-decoration: none; display:block; border-bottom: 1px dotted #333; padding:10px 0 10px 5px;" + "background-color: #efefef;}");
        out.println("#tdaa {border: 1px solid #d0d0d0; background-color: #EEEDEB;}");
        out.println("#tdab {border-top: 1px solid #d0d0d0; border-bottom: 1px solid #d0d0d0; border-right: 1px solid #d0d0d0; background-color: #EEEDEB;}");
        out.println("#tdaa, #tdab {color: #666; font-size:14px; font-family: Verdana, Tahoma, Geneva; font-weight:bold;}");
        out.println("#tdba, #tdbb {color: #666; font-size:14px; font-family: Verdana, Tahoma, Geneva; padding-left: 20px;}");
        out.println("#tdba {border-left:1px solid #d0d0d0; border-bottom:1px solid #d0d0d0; border-right:1px solid #d0d0d0;}");
        out.println("#tdbb {border-right:1px solid #d0d0d0; border-bottom:1px solid #d0d0d0;}");
        out.println("</style>");
    }
}
