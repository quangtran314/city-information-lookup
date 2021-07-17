package com.amela.dao;

import com.amela.model.Nation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NationDAO implements INationDAO{
    private String jdbcURL = "jdbc:mysql://localhost:3306/city_information?useSSL=false";
    private String jdbccityname = "root";
    private String jdbcPassword = "";

    private static final String SELECT_ALL_NATIONS = "select * from nation";

    public NationDAO(){
    }

    protected Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbccityname, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Nation> sellectAllNations() {
        List<Nation> nations = new ArrayList<>();
        try (Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NATIONS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nation = rs.getString("NationName");
                nations.add(new Nation(nation));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return nations;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
