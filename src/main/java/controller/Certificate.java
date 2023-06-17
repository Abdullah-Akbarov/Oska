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


@WebServlet(name = "certificate", urlPatterns = {"/certificate"})
public class Certificate extends HttpServlet {
    Connection con = PostgresConnection.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        certificateRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        certificateRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    protected void certificateRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Нормативно-справочная информация</title>");
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
            out.println("<td width='33%'><a href='certificate' class='aaa'>Нормативно-справочная информация</a></td>");
            out.println("</tr></table></td></tr></table>");
            //top menu tugadi

            //left menu boshlanishi
            out.println("<div class='left'>");

            out.println("<a href='personal-events' class='ssilka'>Персонал всех предприятий</a>");
            out.println("<a href='klassifikator-station' class='ssilka'>Классификатор станций</a>");
            out.println("<a href='klassifikator-events' class='ssilka'>Классификатор предприятий</a>");
            out.println("<a href='station-service' class='ssilka'>Обслуживание станций предприятиями</a>");
            out.println("<a href='klassifikator_failures' class='ssilka'>Классификатор видов неисправностей</a>");


            out.println("</div>");
            //left menu tugadi
            //conteyner boshi
            out.println("<div class='conteiner' align='center'>");
            out.println("<table border='0' cellpadding='5' cellspacing='0' width='90%'>");
            out.println("<tr>");
            out.println("<td id='td0' align='center'>Просмотр справочников системы</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td id='td1'>Персонал всех предприятий</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td id='td1'>Классификатор станций железной дороги</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td id='td1'>Классификатор предприятий железной дороги</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td id='td1'>Классификатор обслуживания станций предприятиями дороги</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td id='td1'>классификатор неисправностей технических устройств станции</td>");
            out.println("</tr>");
            out.println("</table>");

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

    private void style(PrintWriter out) {
        out.println("<style>");
        out.println("body{margin: 0;background-color: #F8F9FA;font-family:Verdana, Tahoma, Geneva;}");
        out.println("#maintable{border: none;width: 100%;}");
        out.println(".logotip{width: auto;height: auto;border: 1px solid #CCCCCC;margin: 5px;background-color: #00A8D3;}");
        out.println(".logotip p{font-family: Arial, Helvetica, sans-serif;font-size: 16px;font-weight: bold;text-align: center;}");
        out.println(".aaa{font-size: 14px;text-decoration: none;font-family: Arial, Helvetica, sans-serif;color: #f5f5f5;"
                + "display: block;padding: 15px 0 15px 0;border: none;background-color: #3969AA;font-weight: bold;}");
        out.println(".aaa:hover{font-size: 14px;text-decoration: none;font-family: Arial, Helvetica, sans-serif;"
                + "color: #fff;display: block;padding: 15px 0 15px 0;border: none;background-color: #448BCA;font-weight: bold;}");
        out.println(".left{margin: 5px 0 5px 5px;border: 1px solid #ccc;width: 248px;position: absolute;top: 70px;"
                + "background-color: #F8F8F8;padding: 20px 0px 20px 0px; min-height:450px;}");
        out.println(".conteiner{margin: 11px 5px 5px 265px;border: 1px solid #ccc;position: relative;width: auto;"
                + "min-width: 500px;background-color: #F8F8F8;padding: 20px; min-height:450px;}");
        out.println(".ssilka {font-weight: normal; font-size:12px; color:#333; font-family: 'PT Serif', Verdana, Tahoma; "
                + "text-decoration: none; display:block; border-bottom: 1px dotted #333; padding:10px 0 10px 5px;"
                + "}");
        out.println(".ssilka:hover {font-weight: normal; font-size:12px; color:#333; font-family: 'PT Serif', Verdana, Tahoma; "
                + "text-decoration: none; display:block; border-bottom: 1px dotted #333; padding:10px 0 10px 5px;"
                + "background-color: #efefef;}");
        out.println("#td1 {border-left:1px solid #d1d1d1;border-right:1px solid #d1d1d1;border-bottom:1px solid #d1d1d1;"
                + "font-size:14px;color:#444; padding: 10px;}");
        out.println("#td0 {border-left:1px solid #d1d1d1;border-right:1px solid #d1d1d1;border-top:1px solid #d1d1d1;"
                + "border-bottom:1px solid #d1d1d1; background-color:#EEEDEB; color:#888; font-weight: bold;"
                + " font-size:14px;}");
        out.println("</style>");

    }
}
