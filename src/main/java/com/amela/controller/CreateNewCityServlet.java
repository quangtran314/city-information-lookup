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
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@WebServlet(name = "CreateNewCityServlet", value = "/create")
public class CreateNewCityServlet extends HttpServlet {
    private CityDAO cityDAO;
    private NationDAO nationDAO;

    @Override
    public void init(){
        cityDAO = new CityDAO();
        nationDAO = new NationDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Nation> nations = nationDAO.sellectAllNations();
        request.setAttribute("nations", nations);
        request.setAttribute("city", new City());
        request.setAttribute("filled", false);
        RequestDispatcher dispatcher = request.getRequestDispatcher("city/jsp/create.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String name = request.getParameter("name");
        String nation = request.getParameter("nation");
        double area = Double.parseDouble(request.getParameter("area"));
        int population = Integer.parseInt(request.getParameter("population"));
        int GDP = Integer.parseInt(request.getParameter("GDP"));
        String description = request.getParameter("description");

        City newCity = new City(name, nation, area, population, GDP, description);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<City>> constraintViolations = validator.validate(newCity);
        if (!constraintViolations.isEmpty()) {
            String errors = "<ul>";
            for (ConstraintViolation<City> constraintViolation : constraintViolations) {
                errors += "<li>" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()
                        + "</li>";
            }
            errors += "</ul>";
            request.setAttribute("errors", errors);
            List<Nation> nations = nationDAO.sellectAllNations();
            request.setAttribute("nations", nations);
            request.setAttribute("city", newCity);
            request.setAttribute("filled", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("city/jsp/create.jsp");
            dispatcher.forward(request, response);
        } else {
            try {
                cityDAO.insertCity(newCity);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            response.sendRedirect("/");
        }
    }
}
