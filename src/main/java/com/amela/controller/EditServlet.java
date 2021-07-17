package com.amela.controller;

import com.amela.dao.CityDAO;
import com.amela.dao.NationDAO;
import com.amela.model.City;
import com.amela.model.Nation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "EditServlet", value = "/edit")
public class EditServlet extends HttpServlet {
    private CityDAO cityDAO;
    private NationDAO nationDAO;

    @Override
    public void init(){
        cityDAO = new CityDAO();
        nationDAO = new NationDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        City city = cityDAO.selectCity(id);
        List<Nation> nations = nationDAO.sellectAllNations();
        request.setAttribute("city", city);
        request.setAttribute("nations", nations);
        RequestDispatcher dispatcher = request.getRequestDispatcher("city/jsp/edit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String nation = request.getParameter("nation");
        double area = Double.parseDouble(request.getParameter("area"));
        int population = Integer.parseInt(request.getParameter("population"));
        int GDP = Integer.parseInt(request.getParameter("GDP"));
        String description = request.getParameter("description");

        City newCity = new City(id, name, nation, area, population, GDP, description);
        try {
            cityDAO.updateCity(newCity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("/");
    }
}
