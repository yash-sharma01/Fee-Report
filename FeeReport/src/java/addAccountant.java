import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class addAccountant extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            HttpSession session = request.getSession(false);
            response.setHeader("Cache-Control","No-Cache, No-Store");
 
            if(session != null) {
                String username = session.getAttribute("username").toString().toUpperCase();
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Add Accountant</title>");
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
                out.println("<div class ='head'>Add Accountant's Details</div>");
                out.println("<form action='addAccountantServlet' method=\"post\">");
                out.println("<br>Id<br>");
                out.println("<input type='text' name='id' placeholder='Enter id' required/><br>");
                out.println("<br>Name<br>");
                out.println("<input type='text' name='name' placeholder='Enter name' required/><br>");
                out.println("<br>Email<br>");
                out.println("<input type='email' name='email' placeholder='Enter email' required/><br>");
                out.println("<br>Password<br>");
                out.println("<input type='password' name='pass' placeholder='Enter password' required/><br>");
                out.println("<br>Contact no<br>");
                out.println("<input type='number' name='cno' placeholder='Enter contact' required/><br>");
                out.println("<br><input type='submit' value='ADD'>");
                out.println("</form>");
                                  
            }
            else {
                response.sendRedirect("index.html");
            }
            out.println("</main>");
            out.println("</body>");
            out.println("</html>");
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
