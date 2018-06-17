package com.onlineproduct.filter;

import com.onlineproduct.entity.UserAccount;
import com.onlineproduct.utils.DBUtils;
import com.onlineproduct.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter(filterName = "cookieFilter", urlPatterns = {"/*"})
public class CookieFilter implements Filter {
    public CookieFilter(){}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpSession session=request.getSession();

        UserAccount userInSession = MyUtils.getLoginedUser(session);

        if(userInSession!=null){
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        // Connection was created in JDBCFilter.
        Connection connection= MyUtils.getStoredConnection(servletRequest);

        // Flag check cookie
        String checked=(String) session.getAttribute("COOKIE_CHECKED");
        if(checked == null && connection!=null){
            String userName = MyUtils.getUserNameInCookie(request);
            try {
                UserAccount user= DBUtils.findUser(connection,userName);
                // if checkboxed is ticked, then it waas storing session.. so ignoring this cookie sesion
//                MyUtils.storeLoginUser(session,user);
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
            // Mark checked Cookies.
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
