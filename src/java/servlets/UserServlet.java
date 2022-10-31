/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

/**
 *
 * @author tobys
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService userservice = new UserService();
        RoleService roleservice = new RoleService();
        User user = null;
        List<User> users = null;
        List<Role> roles = null;
        String action = request.getParameter("action");
        String message = null;
        String useremail = null;

        try {
            users = userservice.getAll();
            roles = roleservice.getAll();
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);

            switch (action) {
                case "edit":
                    message = "edit";
                    request.setAttribute("message", message);
                    useremail = request.getParameter("useremail");
                    user = userservice.get(useremail);
                    request.setAttribute("user", user);
                    break;
                case "delete":
                    useremail = request.getParameter("useremail");
                    userservice.delete(useremail);
                    break;

            }
            users = userservice.getAll();
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
        request.setAttribute("users", users);

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService userservice = new UserService();
        RoleService roleservice = new RoleService();
        List<User> users = null;
        List<Role> roles = null;
        String action = request.getParameter("action");     
        Role role = null;
        User user = null;
        
        try {
            
            String email = request.getParameter("email");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String password = request.getParameter("password");
            int roleId = Integer.parseInt(request.getParameter("role"));

            role = roleservice.getRole(roleId);
            
            user = new User(email, firstname, lastname, password, role);
          
            
            switch (action) {
                case "insert":
                    userservice.insert(user);
                    break;
                case "update":
                    userservice.update(user);
                    break;
            }
            
        }catch (Exception ex) {
            
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            
        }
            
            try {
                users = userservice.getAll();
                roles = roleservice.getAll();
                request.setAttribute("users", users);
                request.setAttribute("roles", roles);
                
                getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
    }