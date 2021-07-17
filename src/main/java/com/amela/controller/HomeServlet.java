package com.amela.controller;

import com.amela.dao.CityDAO;
import com.amela.model.City;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = "/")
public class HomeServlet extends HttpServlet {
    private CityDAO cityDAO;

    @Override
    public void init(){

        cityDAO = new CityDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recordsPerPage = 5;
        int currentPage = 1;
        String page = request.getParameter("page");
        if(page == null){

        } else {
            currentPage = Integer.parseInt(page);
        }

        boolean sort = false;
        if(request.getParameter("sort") == null){

        } else {
            sort = Boolean.parseBoolean(request.getParameter("sort"));
        }
        List<City> cities = cityDAO.findCitiesPerPage(currentPage, recordsPerPage, sort);

        int rows = cityDAO.getNumberOfRows();
        int nOfPages = rows / recordsPerPage;

        if(rows % recordsPerPage > 0){
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        request.setAttribute("cities", cities);
        request.setAttribute("sort", sort);

        RequestDispatcher dispatcher = request.getRequestDispatcher("city/jsp/list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
