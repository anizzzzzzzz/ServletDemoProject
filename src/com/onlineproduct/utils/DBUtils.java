package com.onlineproduct.utils;

import com.onlineproduct.entity.Product;
import com.onlineproduct.entity.UserAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    public static UserAccount findUser(Connection connection, String userName, String password) throws SQLException{
        String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a "
                +" where a.User_Name=? and a.Password=?";

        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,userName);
        pstm.setString(2,password);
        ResultSet resultSet=pstm.executeQuery();

        if(resultSet.next()){
            String gender=resultSet.getString("Gender");
            return new UserAccount(userName,gender,password);
        }
        return null;
    }

    public static UserAccount findUser(Connection connection,String userName) throws SQLException{
        String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a "//
                + " where a.User_Name = ? ";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, userName);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String password = rs.getString("Password");
            String gender = rs.getString("Gender");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }
        return null;
    }

    public static List<Product> findAllProducts(Connection connection) throws SQLException{
        String sql="Select p.P_Code, p.Name, p.Price from Product p";

        PreparedStatement pst=connection.prepareStatement(sql);

        ResultSet rs=pst.executeQuery();
        List<Product> products=new ArrayList<>();

        while(rs.next()){
            Product product=new Product(rs.getString(1), rs.getString(2), rs.getFloat(3));
            products.add(product);
        }
        return products;
    }

    public static Product findOneProduct(Connection connection,String pCode) throws SQLException{
        String sql = "Select p.P_Code, p.Name, p.Price from Product p "
                +" where p.P_Code = ?";
        PreparedStatement pst=connection.prepareStatement(sql);
        pst.setString(1,pCode);

        ResultSet rs=pst.executeQuery();
        while(rs.next()){
            return new Product(pCode, rs.getString("Name"), rs.getFloat("Price"));
        }
        return null;
    }

    public static void updateProduct(Connection connection, Product product) throws SQLException{
        String sql = "Update Product set Name=?, Price=? where P_Code=?";

        PreparedStatement pst=connection.prepareStatement(sql);
        pst.setString(1,product.getName());
        pst.setFloat(2,product.getPrice());
        pst.setString(3,product.getpCode());

        pst.executeUpdate();
    }

    public static void insertProduct(Connection connection, Product product) throws SQLException{
        String sql = "Insert into Product(P_Code, Name, Price) values(?,?,?)";

        PreparedStatement pst=connection.prepareStatement(sql);
        pst.setString(1,product.getpCode());
        pst.setString(2,product.getName());
        pst.setFloat(3,product.getPrice());

        pst.executeUpdate();
    }

    public static void deleteProduct(Connection connection, String pCode) throws SQLException{
        String sql = "Delete from Product where P_Code=?";

        PreparedStatement pst=connection.prepareStatement(sql);
        pst.setString(1,pCode);

        pst.executeUpdate();
    }
}
