package com.amela.dao;

import com.amela.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAO implements ICityDAO{
    private String jdbcURL = "jdbc:mysql://localhost:3306/city_information?useSSL=false";
    private String jdbccityname = "root";
    private String jdbcPassword = "";

    private static final String INSERT_CITY_SQL = "INSERT INTO city" + "  (name, nation, area, population, GDP, description) VALUES " +
            " (?, ?, ?, ?, ?, ?);";

    private static final String SELECT_CITIES_BY_ID = "select * from city where id =?";
    private static final String SELECT_ALL_CITIES = "select * from city";
    private static final String DELETE_CITIES_SQL = "delete from city where id = ?;";
    private static final String UPDATE_CITIES_SQL = "update city set name = ?,nation= ?, area =?, population =?, GDP =?, description =? where id = ?;";
    private static final String SORT_BY_NAME = "select * from city order by name";
    private static final String SEARCH_BY_NATION = "select city.* " +
            "from city " +
            "WHERE city.nation in ( " +
            "select nation.NationName " +
            "from nation " +
            "WHERE nation.NationName LIKE ?);";
    private static final String SELECT_ALL_CITIES_PER_PAGE = "SELECT * FROM city LIMIT ?, ?;";
    private static final String SELECT_ALL_CITIES_PER_PAGE_AFTER_SORT = "SELECT * FROM city ORDER BY name LIMIT ?, ?";
    private static final String GET_NUMBER_OF_ROWS = "SELECT COUNT(id) AS num FROM city;";

    public CityDAO() {
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
    public void insertCity(City city) throws SQLException {
        System.out.println(INSERT_CITY_SQL);
        try(
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CITY_SQL);
        ){
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getNation());
            preparedStatement.setDouble(3, city.getArea());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getGDP());
            preparedStatement.setString(6, city.getDescription());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public City selectCity(int id) {
        City city = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CITIES_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String nation = rs.getString("nation");
                double area = rs.getDouble("area");
                int population = rs.getInt("population");
                int GDP = rs.getInt("GDP");
                String description = rs.getString("description");
                city = new City(id, name, nation, area, population, GDP, description);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return city;
    }

    @Override
    public List<City> selectAllCities() {
        List<City> cities = new ArrayList<>();
        try (Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CITIES);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String nation = rs.getString("nation");
                double area = rs.getDouble("area");
                int population = rs.getInt("population");
                int GDP = rs.getInt("GDP");
                String description = rs.getString("description");
                cities.add(new City(id, name, nation, area, population, GDP, description));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cities;
    }

    @Override
    public boolean deleteCity(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CITIES_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateCity(City city) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CITIES_SQL);) {
            statement.setString(1, city.getName());
            statement.setString(2, city.getNation());
            statement.setDouble(3, city.getArea());
            statement.setInt(4, city.getPopulation());
            statement.setInt(5, city.getGDP());
            statement.setString(6, city.getDescription());
            statement.setInt(7, city.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public List<City> sortByName() {
        List<City> cities = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SORT_BY_NAME);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String nation = rs.getString("nation");
                double area = rs.getDouble("area");
                int population = rs.getInt("population");
                int GDP = rs.getInt("GDP");
                String description = rs.getString("description");
                cities.add(new City(id, name, nation, area, population, GDP, description));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cities;
    }

    public List<City> searchByNation(String nation){
        List<City> cities = new ArrayList<>();
        try (Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_NATION);) {
            preparedStatement.setString(1, "%" + nation + "%");
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String matchNation = rs.getString("nation");
                double area = rs.getDouble("area");
                int population = rs.getInt("population");
                int GDP = rs.getInt("GDP");
                String description = rs.getString("description");
                cities.add(new City(id, name, matchNation, area, population, GDP, description));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cities;
    }

    public List<City> findCitiesPerPage(int currentPage, int recordsPerPage, boolean sort){
        List<City> cities = new ArrayList<City>();
        int start = currentPage * recordsPerPage - recordsPerPage;

        try (Connection connection = getConnection();) {
            PreparedStatement preparedStatement = null;
            if(sort){
                preparedStatement = connection.prepareStatement(SELECT_ALL_CITIES_PER_PAGE_AFTER_SORT);
            } else{
                preparedStatement = connection.prepareStatement(SELECT_ALL_CITIES_PER_PAGE);
            }
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, recordsPerPage);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String nation = rs.getString("nation");
                double area = rs.getDouble("area");
                int population = rs.getInt("population");
                int GDP = rs.getInt("GDP");
                String description = rs.getString("description");
                cities.add(new City(id, name, nation, area, population, GDP, description));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cities;
    }

    public int getNumberOfRows(){
        int numOfRows = 0;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_NUMBER_OF_ROWS);){
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                numOfRows = rs.getInt("num");
            }
        } catch (SQLException ex){
            printSQLException(ex);
        }
        return numOfRows;
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
