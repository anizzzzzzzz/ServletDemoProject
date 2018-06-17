package com.onlineproduct.servlet;

import com.onlineproduct.utils.DBUtils;
import com.onlineproduct.utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/deleteProduct"})
public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteProductServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection= MyUtils.getStoredConnection(req);

        String code=req.getParameter("pCode");

        String errorString=null;

        try {
            DBUtils.deleteProduct(connection,code);
        }
        catch (SQLException e){
            e.printStackTrace();
            errorString=e.getMessage();
        }

        if(errorString!=null){
            req.setAttribute("errorString",errorString);

            RequestDispatcher dispatcher=this.getServletContext().getRequestDispatcher("/WEB-INF/views/deleteProductErrorView.jsp");
            dispatcher.forward(req,resp);
        }
        else{
            resp.sendRedirect(req.getContextPath()+"/productList");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}

