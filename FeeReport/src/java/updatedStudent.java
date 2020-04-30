import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class updatedStudent extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            response.setHeader("Cache-Control","No-Cache, No-Store");
         
            if(session != null){
                String roll = request.getParameter("roll");
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String course = request.getParameter("course");
                String fee = request.getParameter("fee");
                String paid = request.getParameter("paid");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String cno = request.getParameter("cno");
                
                String namea = session.getAttribute("name").toString();

                int fees = Integer.parseInt(fee);
                int feesPaid = Integer.parseInt(paid);
                int feesDue = fees - feesPaid;
                
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/FeeReport","root","root");
                PreparedStatement ps = con.prepareStatement("update students set name = ?, email = ?, course = ?, fee = ?, paid = ?, address = ?, city = ?, contactno = ?, due = ? where rollno = ?");
                
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, course);
                ps.setString(4, fee);
                ps.setString(5, paid);
                ps.setString(6, address);
                ps.setString(7, city);
                ps.setString(8, cno);
                ps.setInt(9, feesDue);
                ps.setString(10, roll);
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Edit Student</title>");
                out.println("<link rel='icon' type='image/png' href='images/frlogo.png'/>");
                out.println("<link href='styleFeeReport.css' rel='stylesheet' type='text/css'/>");
                out.println("</head>");
                out.println("<body class='accountant'>");
                out.println("<ul class='nav'>");
                out.println("<li class='nav'><a href='accountanthome'>Home</a></li>");
                out.println("<li class='nav'><a href='addStudent'>Add Student</a></li>");
                out.println("<li class='nav'><a href='viewStudentServlet'>View Student</a></li>");
                out.println("<li class='nav'><a href='accountantlogout'>Log Out ("+namea+")</a></li>");
                out.println("</ul>");
                out.println("<main class='welcome'>");
                    
                if(ps.executeUpdate()!=0) {
                    out.println("Student Updated Successfully!");
                }

                else{
                    out.println("Sorry Try Again!");
                }
                con.close();
                out.println("</main>");
                out.println("</body>");
                out.println("</html>");
            }
            
            else{
                response.sendRedirect("index.html");
            }
        }
        
        catch (ClassNotFoundException ex) {
            Logger.getLogger(updatedStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        catch (SQLException ex) {
            Logger.getLogger(updatedStudent.class.getName()).log(Level.SEVERE, null, ex);
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
