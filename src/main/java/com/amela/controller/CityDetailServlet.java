package com.amela.controller;

import com.amela.dao.CityDAO;
import com.amela.model.City;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CityDetailServlet", value = "/city")
public class CityDetailServlet extends HttpServlet {
    private CityDAO cityDAO;

    @Override
    public void init(){
        cityDAO = new CityDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        City city = cityDAO.selectCity(id);
        request.setAttribute("city", city);
        RequestDispatcher dispatcher = request.getRequestDispatcher("city/jsp/city.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
