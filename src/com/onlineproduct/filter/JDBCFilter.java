package com.onlineproduct.filter;

import com.onlineproduct.conn.ConnectionController;
import com.onlineproduct.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

@WebFilter(filterName="jdbcFilter", urlPatterns = {"/*"})
public class JDBCFilter implements Filter {
    public JDBCFilter(){}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request=(HttpServletRequest) servletRequest;

        // Only open connections for the special requests.
        // (For example, the path to the servlet, JSP, ..)
        //
        // Avoid open connection for commons request.
        // (For example: image, css, javascript,... )
        //
        if(this.needJDBC(request)){
            System.out.println("Open Connection for: "+request.getServletPath());

            Connection connection=null;

            try{
                // Create a Connection.
                connection= ConnectionController.getConnection();
                // Set outo commit to false.
                connection.setAutoCommit(false);

                // Store Connection object in attribute of request.
                MyUtils.storeConnection(servletRequest,connection);

                // Allow request to go forward
                // (Go to the next filter or target)
                filterChain.doFilter(servletRequest,servletResponse);

                // Invoke the commit() method to complete the transaction with the DB.
                connection.commit();
            }
            catch(Exception ex){
                ex.printStackTrace();
                ConnectionController.rollBack(connection);
                throw new ServletException();
            }
            finally {
                ConnectionController.closeConnection(connection);
            }
        }
        else{
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }

    // Check the target of the request is a servlet?
    private boolean needJDBC(HttpServletRequest request){
        System.out.println("JDBC filter");
        //
        // Servlet Url-pattern: /spath/*
        //
        // => /spath
        String servletPath=request.getServletPath();
        // => /abc/mnp
        String pathInfo=request.getPathInfo();

        String urlPattern = servletPath;
        if(pathInfo!=null){
            // => /spath/*
            urlPattern=servletPath+"/*";
        }

        // Key: servletName.
        // Value: ServletRegistration
        Map<String, ? extends ServletRegistration> servletRegistrations= request.getServletContext().getServletRegistrations();

        Collection<? extends ServletRegistration> values=servletRegistrations.values();
        for(ServletRegistration servletRegistration:values){
            Collection<String> mappings=servletRegistration.getMappings();
            if(mappings.contains(urlPattern))
                return true;
        }
        return false;
    }
}
