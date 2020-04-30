import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class addStudent extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession(false);
            response.setHeader("Cache-Control","No-Cache, No-Store");
            
            if(session != null) {
                String name = session.getAttribute("name").toString();
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Add Student</title>");
                out.println("<link rel='icon' type='image/png' href='images/frlogo.png'/>");
                out.println("<link href='styleFeeReport.css' rel='stylesheet' type='text/css'/>");
                out.println("</head>");
                out.println("<body class='accountant'>");
                out.println("<ul class='nav'>");
                out.println("<li class='nav'><a href='accountanthome'>Home</a></li>");
                out.println("<li class='nav'><a href='addStudent'>Add Student</a></li>");
                out.println("<li class='nav'><a href='viewStudentServlet'>View Student</a></li>");
                out.println("<li class='nav'><a href='accountantlogout'>Log Out ("+name+")</a></li>");
                out.println("</ul>");  
                out.println("<main class='addAcc'>");
                out.println("<div class='head'>Add Student's Details</div>");
                out.println("<form action='addStudentServlet' method=\"post\">");
                out.println("<br>Roll no<br>");
                out.println("<input type='text' name='roll' placeholder='Enter roll no' required/><br>");
                out.println("<br>Name<br>");
                out.println("<input type='text' name='name' placeholder='Enter name' required/><br>");
                out.println("<br>Email<br>");
                out.println("<input type='email' name='email' placeholder='Enter email' required/><br>");
                out.println("<br>Course<br>");
                out.println("<input type='text' name='course' placeholder='Enter course' required/><br>");
                out.println("<br>Total Fee<br>");
                out.println("<input type='number' name='fee' placeholder='Enter total fee' required/><br>");
                out.println("<br>Fee Paid<br>");
                out.println("<input type='number' name='paid' placeholder='Enter fee paid' required/><br>");
                out.println("<br>Address<br>");
                out.println("<input type='text' name='address' placeholder='Enter address' required/><br>");
                out.println("<br>City<br>");
                out.println("<input type='text' name='city' placeholder='Enter city' required/><br>");
                out.println("<br>Contact no<br>");
                out.println("<input type='number' name='cno' placeholder='Enter contact' required/><br>");
                out.println("<br><input type='submit' value='ADD STUDENT'>");
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
