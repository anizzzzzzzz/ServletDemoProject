package com.onlineproduct.servlet;

import com.onlineproduct.entity.UserAccount;
import com.onlineproduct.utils.DBUtils;
import com.onlineproduct.utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

// When the user enters userName & password, and click Submit.
// This method will be executed.
@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID =1L;

    public LoginServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();

        if(session.getAttribute("loginedUser")==null) {
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
            dispatcher.forward(req, resp);
        }
        else{
            resp.sendRedirect(req.getContextPath()+"/userInfo");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName= req.getParameter("userName");
        String password= req.getParameter("password");
        String rememberMeStr = req.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        UserAccount user=null;
        boolean hasError=false;
        String errorString=null;

        if(userName == null || password ==null || userName.length()==0 || password.length()==0){
            hasError=true;
            errorString="Required username and password";
        }
        else{
            Connection conn= MyUtils.getStoredConnection(req);
            try{
                user= DBUtils.findUser(conn,userName,password);

                if(user==null){
                    hasError=true;
                    errorString="Username or Password invalid";
                }
            }
            catch (SQLException e){
                e.printStackTrace();
                hasError=true;
                errorString=e.getMessage();
            }
        }

        // If error, forward to /WEB-INF/views/login.jsp
        if(hasError){
            user=new UserAccount(userName,password);

            // Store information in request attribute, before forward.
            req.setAttribute("errorString",errorString);
            req.setAttribute("user",user);

            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher=this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
            dispatcher.forward(req,resp);
        }
        // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else{
            HttpSession session= req.getSession();
            MyUtils.storeLoginUser(session,user);

            // If user checked "Remember me".
            if(remember){
                MyUtils.storeCookies(resp,user);
            }
            // Else delete cookie.
            else{
                MyUtils.deleteUserCookie(resp);
            }

            // Redirect to userInfo page.
            resp.sendRedirect(req.getContextPath()+"/userInfo");
        }
    }
}
