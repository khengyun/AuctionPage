/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authentication;

import dal.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.User;

/**
 *
 * @author nkhan
 */
@WebServlet(name = "AdminManagerAction", urlPatterns = {"/AdminManagerAction"})
public class AdminManagerAction extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            
            UserDao userdao = new UserDao();
            ArrayList<User> list = userdao.getUsers();
            request.setAttribute("data", list);
            
            request.getRequestDispatcher("/page/adminmanager.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Kiểm tra xem người dùng có phải là admin hay không
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user.getRole() == user.RoleDefault()) {
            response.sendRedirect(request.getContextPath() + "/LoginAction");
        } else {
            processRequest(request, response);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static void main(String[] args) {
        UserDao userdao = new UserDao();
            ArrayList<User> lists = userdao.getUsers();
            for (User list : lists){
                System.out.println(list.toString());
            }
    }
}
