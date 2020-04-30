import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class edit extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession(false);
            response.setHeader("Cache-Control","No-Cache, No-Store");
                       
            if(session != null) {
                String username = session.getAttribute("username").toString().toUpperCase();
                
                String id = request.getParameter("id");
                
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/FeeReport","root","root");

                PreparedStatement st = con.prepareStatement("select * from accountants where id = ?");
                st.setString(1, id);
                ResultSet rs = st.executeQuery();
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Edit Accountant</title>");
                out.println("<link rel='icon' type='image/png' href='images/frlogo.png'/>");
                out.println("<link href='styleFeeReport.css' rel='stylesheet' type='text/css'/>");
                out.println("</head>");
                out.println("<body class='admin'>");
                out.println("<ul class='nav'>");
                out.println("<li class='nav'><a href='adminhome'>Home</a></li>");
                out.println("<li class='nav'><a href='addAccountant'>Add Accountant</a></li>");
                out.println("<li class='nav'><a href='viewAccountantServlet'>View Accountant</a></li>");
                out.println("<li class='nav'><a href='adminlogout'>Log Out ("+username+")</a></li>");
                out.println("</ul>");
                out.println("<main class='addAcc'>"); 
                out.println("<div class='head'>Edit Accountant's Details</div>");
                out.println("<form action='updatedAccountant' method='post'>");
                
                while(rs.next()){
                    
                    out.println("<br>Id<br>");
                    out.println("<input type='text' name='id' value='" +rs.getString("id")+ "' readonly/><br>");
                    out.println("<br>Name<br>");
                    out.println("<input type='text' name='name' value='" +rs.getString("name")+ "' required/><br>");
                    out.println("<br>Email<br>");
                    out.println("<input type='email' name='email' value='" +rs.getString("email")+ "' required/><br>");
                    out.println("<br>Password<br>");
                    out.println("<input type='password' name='pass' value='" +rs.getString("password")+ "' required/><br>");
                    out.println("<br>Contact no<br>");
                    out.println("<input type='number' name='cno' value='" +rs.getString("contactno")+ "' required/><br>");
                    out.println("<br><input type='submit' value='UPDATE'>");
                }

                out.println("</form>");
                out.println("</main>");
                out.println("</body>");
                out.println("</html>");
            }
            
            else {
                response.sendRedirect("index.html");
            }
            
        } 
        
        catch (ClassNotFoundException ex) {
            Logger.getLogger(edit.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(edit.class.getName()).log(Level.SEVERE, null, ex);
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
