package com.example;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
public class BookServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bookstore",
                "root",
                "pass@123"
            );

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");

            out.println("<html><body>");
            out.println("<h2>Book Details</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Author</th><th>Price</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt(1) + "</td>");
                out.println("<td>" + rs.getString(2) + "</td>");
                out.println("<td>" + rs.getString(3) + "</td>");
                out.println("<td>" + rs.getDouble(4) + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}



// javac -cp "C:\Program Files\Apache Software Foundation\Tomcat 10.1_MyTomcat\lib\servlet-api.jar;lib\mysql-connector-j-9.3.0.jar" -d WebContent/WEB-INF/classes src/com/example/BookServlet.java
// xcopy WebContent "C:\Program Files\Apache Software Foundation\Tomcat 10.1_MyTomcat\webapps\Week8Project" /E /I
// Copy-Item "lib\mysql-connector-j-9.3.0.jar" -Destination "C:\Program Files\Apache Software Foundation\Tomcat 10.1_MyTomcat\lib"
// cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1_MyTomcat\bin"
// .\shutdown.bat
// .\startup.bat
// http://localhost:8080/Week8Project/books