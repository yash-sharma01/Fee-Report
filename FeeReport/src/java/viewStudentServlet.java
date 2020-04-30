import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class viewStudentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession(false);
            response.setHeader("Cache-Control","No-Cache, No-Store");
            
            if(session != null) {
                
                String namea = session.getAttribute("name").toString();
                
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/FeeReport","root","root");
                
                String sql = "select * from students";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>View Students</title>");
                out.println("<link rel='icon' type='image/png' href='images/frlogo.png'/>");
                out.println("<link href='styleFeeReport.css' rel='stylesheet' type='text/css'/>");;
                out.println("</head>");
                out.println("<body class='accountant'>");
                out.println("<ul class='nav'>");
                out.println("<li class='nav'><a href='accountanthome'>Home</a></li>");
                out.println("<li class='nav'><a href='addStudent'>Add Student</a></li>");
                out.println("<li class='nav'><a href='viewStudentServlet'>View Student</a></li>");
                out.println("<li class='nav'><a href='accountantlogout'>Log Out ("+namea+")</a></li>");
                out.println("</ul>"); 
                out.println("<main class='table1'>");
                out.println("<div class='head'>Student Details</div>");
                out.println("<table id='enteries'>");
                out.println("<th>Roll no</th>");
                out.println("<th>Name</th>");
                out.println("<th>Email</th>");
                out.println("<th>Course</th>");
                out.println("<th>Fee</th>"); 
                out.println("<th>Paid</th>");
                out.println("<th>Due</th>");
                out.println("<th>Address</th>");
                out.println("<th>City</th>");
                out.println("<th>Contact No</th>");
                out.println("<th>Edit</th>");
                out.println("<th>Delete</th>");
                
                while(rs.next()){
                    out.println("<tr>");
                    out.println("<td>"+rs.getString("rollno")+"</td>");
                    out.println("<td>"+rs.getString("name")+"</td>");
                    out.println("<td>"+rs.getString("email")+"</td>");
                    out.println("<td>"+rs.getString("course")+"</td>");
                    out.println("<td>"+rs.getString("fee")+"</td>");
                    out.println("<td>"+rs.getString("paid")+"</td>");
                    out.println("<td>"+rs.getInt("due")+"</td>");
                    out.println("<td>"+rs.getString("address")+"</td>");
                    out.println("<td>"+rs.getString("city")+"</td>");
                    out.println("<td>"+rs.getString("contactno")+"</td>");
                    out.println("<td>"+"<a class='edit' href = 'editStudent?rollno="+rs.getString("rollno")+"'>Edit</a>"+"</td>");
                    out.println("<td>"+"<a class='edit' href = 'deleteStudent?rollno=" + rs.getString("rollno")+"'>Delete</a>"+"</td>");   
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("</main>");
                out.println("</body>");
                out.println("</html>");
            }
            else {
                response.sendRedirect("index.html");
            }
        }
        
        catch (ClassNotFoundException ex) {
            Logger.getLogger(viewStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        catch (SQLException ex) {
            Logger.getLogger(viewStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
