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

@WebServlet(name = "Admin", urlPatterns = {"/admin", "/add-admin", "/add-founder", "/delete-founder"})
public class AdminController extends HttpServlet {
    Connection con = PostgresConnection.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/admin":
                adminRequest(request, response);
                break;
            case "/add-admin":
                addRequest(request, response);
                break;
            case "/add-founder":
                addFounder(request, response);
                break;
            case "/delete-founder":
                deleteFounder(request, response);
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

    protected void adminRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Administrator</title>");
            out.println("<style type='text/css'>");
            out.println("body {font-family: Verdana, Geneva, sans-serif; margin: 0px; }");
            out.println(".tabletrtd{ background-color: #011C49;}");
            out.println(".header{text-align: center; color: #ddd; font-size: 20px; }");
            out.println("#headerdiv{width: 100%; height:120px; position: fixed; left: 0; top: 0; }");
            out.println("#conteiner{width: 100%;position:relative;margin: 0 auto;text-align:left;top: 125px;left: 0;" + "border: 1px solid #sss;}");
            out.println("#leftmenu{width: 300px;position: fixed;top:125px;left:5px;border: 1px solid #011C49;border-radius: 8px 8px 8px 8px;" + "padding: 15px;padding-left:0;background-color: #4682B4;min-height:400px;}");
            out.println("#leftmenu p{color:#001D3D;font-size: 16px;font-weight: bold;text-align: center;}");
            out.println(".menu {display: block;width: 285px;background-color:#4682B4;color:#E5E008;font-size: 14px;" + "text-decoration: none;padding: 15px;font-weight: bold;border-top: 1px  dotted #fff}");
            out.println(".menu:hover {display: block;width: 278px;background-color:#011C49;color:#fff;font-size: 14px;" + "text-decoration: none;padding: 15px;font-weight: bold;border-left: 7px solid #fff;}");
            out.println("#article{top:125px;margin-left:340px;margin-right:5px;border: 1px solid #011C49;" + "border-radius: 8px 8px 8px 8px;padding: 15px;min-height:400px;}");
            out.println("#logouttd{width:218px;border-left: 1px solid #fff;color: #fff;font-size: 14px;padding-left:20px;}");
            out.println("</style>");
            //style


            out.println("</head>");
            out.println("<body>");
            //out.println("");
            //HEADER qismi boshlanishi
            out.println("<div id='headerdiv'>");
            out.println("<table width='100%' border='0' cellpadding='0' cellspacing='0'>");
            out.println("<tr height='100px'>");
            out.println("<td bgcolor='#011C49'><p class='header'>Административной часть системы</p></td>");
            out.println("<td bgcolor='#011C49' id='logouttd'>avtorizatsiya");


            out.println("</td></tr></table></div>");
            //HEADER oxiri

            //CONTAINER boshlanishi
            out.println("<div id='conteiner'>");
            //LEFT MENU boshi
            out.println("<div id='leftmenu'><p>Меню</p>");
            links(out);
            out.println("</div>");
            //ARTICLE boshi
            out.println("<div id='article'>");


            out.println("</div>");
            //ARTICLE oxiri
            out.println("</div>");
            //CONTAINER oxiri
            out.println("</body>");
            out.println("</html>");

        } finally {
            out.close();
        }
    }

    protected void addRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=Windows-1251");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Administrator</title>");
            //style
            out.println("<style type='text/css'>");
            out.println("body {font-family: Verdana, Geneva, sans-serif; margin: 0px; }");
            out.println(".tabletrtd{ background-color: #011C49;}");
            out.println(".header{text-align: center; color: #ddd; font-size: 20px; }");
            out.println("#headerdiv{width: 100%; height:120px; position: fixed; left: 0; top: 0; }");
            out.println("#conteiner{width: 100%;position:relative;margin: 0 auto;text-align:left;top: 125px;left: 0;" + "border: 1px solid #sss;}");
            out.println("#leftmenu{width: 300px;position: fixed;top:125px;left:5px;border: 1px solid #011C49;border-radius: 8px 8px 8px 8px;" + "padding: 15px;padding-left:0;background-color: #4682B4;min-height:400px;}");
            out.println("#leftmenu p{color:#001D3D;font-size: 16px;font-weight: bold;text-align: center;}");
            out.println(".menu {display: block;width: 285px;background-color:#4682B4;color:#E5E008;font-size: 14px;" + "text-decoration: none;padding: 15px;font-weight: bold;border-top: 1px  dotted #fff}");
            out.println(".menu:hover {display: block;width: 278px;background-color:#011C49;color:#fff;font-size: 14px;" + "text-decoration: none;padding: 15px;font-weight: bold;border-left: 7px solid #fff;}");
            out.println("#article{top:125px;margin-left:340px;margin-right:5px;border: 1px solid #011C49;" + "border-radius: 8px 8px 8px 8px;padding: 15px;min-height:400px;}");
            out.println("#logouttd{width:218px;border-left: 1px solid #fff;color: #fff;font-size: 14px;padding-left:20px;}");
            out.println("</style>");
            //style


            out.println("</head>");
            out.println("<body>");
            //out.println("");
            //HEADER qismi boshlanishi
            out.println("<div id='headerdiv'>");
            out.println("<table width='100%' border='0' cellpadding='0' cellspacing='0'>");
            out.println("<tr height='100px'>");
            out.println("<td bgcolor='#011C49'><p class='header'>Административной часть системы</p></td>");
            out.println("<td bgcolor='#011C49' id='logouttd'>avtorizatsiya");


            out.println("</td></tr></table></div>");
            //HEADER oxiri

            //CONTAINER boshlanishi
            out.println("<div id='conteiner'>");
            //LEFT MENU boshi
            out.println("<div id='leftmenu'><p>Меню</p>");
            links(out);
            out.println("</div>");
            //ARTICLE boshi
            out.println("<div id='article'>");

            out.println("<h4>Регистрация</h4>");

            out.println("<form action='' method='post' name='form1'>");
            out.println("<table width='' border='0' cellpadding='0' cellspacing='5'>");
            out.println("<tr><td>Фамилия</td><td><input name='surname' type='text' value='' size='30' maxlength='255' /></td></tr>");
            out.println("<tr><td>Имя</td><td><input name='name' type='text' value='' size='30' maxlength='255' /></td></tr>");
            out.println("<tr><td>Отчество</td><td><input name='lastname' type='text' value='' size='30' maxlength='255' /></td></tr>");
            out.println("<tr><td>Логин</td><td><input name='login' type='text' value='' size='30' maxlength='255' /></td></tr>");
            out.println("<tr><td>Пароль</td><td><input name='password' type='password' value='' size='30' maxlength='32' /></td></tr>");
            out.println("<tr><td>Подтвердите пароль</td><td><input name='password2' type='password' value='' size='30' maxlength='32' /></td></tr>");
            out.println("<tr><td>Тип пользователей</td>");
            out.println("<td><select name='tip_polzovatel' id='tip_polzovatel'>");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from usertype2 ORDER BY `id` ASC");
            while (rs.next()) {
                String s = rs.getString(1);
                String s2 = rs.getString(2);
                out.println("<option value='" + s + "'>" + s2 + "</option>");
                out.println(" ");
            }
            st.close();
            rs.close();

            out.println("</select></td></tr>");
            out.println("<tr><td height='34'><input type='submit' name='Submit' value='OK' /></td>");
            out.println("<td>&nbsp;</td></tr></table>");
            out.println("</form>");

            if (request.getParameter("Submit").equals("OK")) {
                String ss1 = request.getParameter("surname");
                String ss2 = request.getParameter("name");
                String ss3 = request.getParameter("lastname");
                String ss4 = request.getParameter("login");
                String ss5 = request.getParameter("password");
//String ss6=request.getParameter("password2");
                String ss7 = request.getParameter("tip_polzovatel");

                Statement st2 = con.createStatement();
                int rs2 = st2.executeUpdate("insert into users (surname,name,lastname,login,password,user_type) values ('" + ss1 + "','" + ss2 + "','" + ss3 + "','" + ss4 + "','" + ss5 + "','" + ss7 + "')");
                out.println("Yangi buyurtma qabul qilindi!");
                st2.close();

            } else {
                //String ss = request.getParameter("film");
                Statement st3 = con.createStatement();
                ResultSet rs3 = st3.executeQuery("select * from users where id=(select max(id) from users)");
                while (rs3.next()) {
                    out.println("Oxirgi qabul qilingan buyurtma raqami - " + "<font color='#ff9000'>" + rs3.getString(2) + "</font>");

                }
                st.close();
                rs3.close();
            }
            out.println("</div>");
            //ARTICLE oxiri
            out.println("</div>");
            //CONTAINER oxiri
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            out.println(e);
        } finally {
            out.close();
        }
    }

    protected void addFounder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Administrator</title>");
            //style
            out.println("<style type='text/css'>");
            out.println("body {font-family: Verdana, Geneva, sans-serif; margin: 0px; }");
            out.println(".tabletrtd{ background-color: #011C49;}");
            out.println(".header{text-align: center; color: #ddd; font-size: 20px; }");
            out.println("#headerdiv{width: 100%; height:120px; position: fixed; left: 0; top: 0; }");
            out.println("#conteiner{width: 100%;position:relative;margin: 0 auto;text-align:left;top: 125px;left: 0;"
                    + "border: 1px solid #sss;}");
            out.println("#leftmenu{width: 300px;position: fixed;top:125px;left:5px;border: 1px solid #011C49;border-radius: 8px 8px 8px 8px;"
                    + "padding: 15px;padding-left:0;background-color: #4682B4;min-height:400px;}");
            out.println("#leftmenu p{color:#001D3D;font-size: 16px;font-weight: bold;text-align: center;}");
            out.println(".menu {display: block;width: 285px;background-color:#4682B4;color:#E5E008;font-size: 14px;"
                    + "text-decoration: none;padding: 15px;font-weight: bold;border-top: 1px  dotted #fff}");
            out.println(".menu:hover {display: block;width: 278px;background-color:#011C49;color:#fff;font-size: 14px;"
                    + "text-decoration: none;padding: 15px;font-weight: bold;border-left: 7px solid #fff;}");
            out.println("#article{top:125px;margin-left:340px;margin-right:5px;border: 1px solid #011C49;"
                    + "border-radius: 8px 8px 8px 8px;padding: 15px;min-height:400px;}");
            out.println("#logouttd{width:218px;border-left: 1px solid #fff;color: #fff;font-size: 14px;padding-left:20px;}");
            out.println("</style>");
            //style
            out.println("</head>");
            out.println("<body>");
            //out.println("");
            //HEADER qismi boshlanishi
            out.println("<div id='headerdiv'>");
            out.println("<table width='100%' border='0' cellpadding='0' cellspacing='0'>");
            out.println("<tr height='100px'>");
            out.println("<td bgcolor='#011C49'><p class='header'>Административной часть системы</p></td>");
            out.println("<td bgcolor='#011C49' id='logouttd'>avtorizatsiya");
            out.println("</td></tr></table></div>");
            out.println("<div id='conteiner'>");
            //LEFT MENU boshi
            out.println("<div id='leftmenu'><p>Меню</p>");
            links(out);
            out.println("</div>");
            //ARTICLE boshi
            out.println("<div id='article'>");
            out.println("</div>");
            //ARTICLE oxiri
            out.println("</div>");
            //CONTAINER oxiri
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            out.println(" ");
        } finally {
            out.close();
        }
    }

    protected void deleteFounder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Administrator</title>");
            //style
            out.println("<style type='text/css'>");
            out.println("body {font-family: Verdana, Geneva, sans-serif; margin: 0px; }");
            out.println(".tabletrtd{ background-color: #011C49;}");
            out.println(".header{text-align: center; color: #ddd; font-size: 20px; }");
            out.println("#headerdiv{width: 100%; height:120px; position: fixed; left: 0; top: 0; }");
            out.println("#conteiner{width: 100%;position:relative;margin: 0 auto;text-align:left;top: 125px;left: 0;"
                    + "border: 1px solid #sss;}");
            out.println("#leftmenu{width: 300px;position: fixed;top:125px;left:5px;border: 1px solid #011C49;border-radius: 8px 8px 8px 8px;"
                    + "padding: 15px;padding-left:0;background-color: #4682B4;min-height:400px;}");
            out.println("#leftmenu p{color:#001D3D;font-size: 16px;font-weight: bold;text-align: center;}");
            out.println(".menu {display: block;width: 285px;background-color:#4682B4;color:#E5E008;font-size: 14px;"
                    + "text-decoration: none;padding: 15px;font-weight: bold;border-top: 1px  dotted #fff}");
            out.println(".menu:hover {display: block;width: 278px;background-color:#011C49;color:#fff;font-size: 14px;"
                    + "text-decoration: none;padding: 15px;font-weight: bold;border-left: 7px solid #fff;}");
            out.println("#article{top:125px;margin-left:340px;margin-right:5px;border: 1px solid #011C49;"
                    + "border-radius: 8px 8px 8px 8px;padding: 15px;min-height:400px;}");
            out.println("#logouttd{width:218px;border-left: 1px solid #fff;color: #fff;font-size: 14px;padding-left:20px;}");
            out.println("</style>");
            //style


            out.println("</head>");
            out.println("<body>");
            //out.println("");
            //HEADER qismi boshlanishi
            out.println("<div id='headerdiv'>");
            out.println("<table width='100%' border='0' cellpadding='0' cellspacing='0'>");
            out.println("<tr height='100px'>");
            out.println("<td bgcolor='#011C49'><p class='header'>Административной часть системы</p></td>");
            out.println("<td bgcolor='#011C49' id='logouttd'>avtorizatsiya");
            out.println("</td></tr></table></div>");
            //HEADER oxiri
            //CONTAINER boshlanishi
            out.println("<div id='conteiner'>");
            //LEFT MENU boshi
            out.println("<div id='leftmenu'><p>Меню</p>");
            links(out);
            out.println("</div>");
            //ARTICLE boshi
            out.println("<div id='article'>");
            out.println("</div>");
            //ARTICLE oxiri
            out.println("</div>");
            //CONTAINER oxiri
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            out.println(" ");
        } finally {
            out.close();
        }
    }

    private void links(PrintWriter out) {
        out.println("<a href='index.jsp' class='menu'>> Главная</a>");
        out.println("<a href='add-admin' class='menu'>> Добавить администратор</a>");
        out.println("<a href='add-founder' class='menu'>> Добавить ползователь</a>");
        out.println("<a href='delete-founder' class='menu'>> Удалить ползователь</a>");
    }
}