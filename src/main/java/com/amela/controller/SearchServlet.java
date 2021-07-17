package com.amela.controller;

import com.amela.dao.CityDAO;
import com.amela.model.City;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchServlet", value = "/search")
public class SearchServlet extends HttpServlet {
    private CityDAO cityDAO;

    private final int RECORDS_PER_PAGE = 5;

    @Override
    public void init(){
        cityDAO = new CityDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = 1;
        String page = request.getParameter("page");
        if(page == null){

        } else {
            currentPage = Integer.parseInt(page);
        }

        String nation = request.getParameter("nation");
        List<City> cities = cityDAO.searchByNation(nation);

        List<City> citiesPerPage = getCitiesPerPage(cities, currentPage, RECORDS_PER_PAGE);

        int nOfPages = getNumOfPages(cities.size());

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", RECORDS_PER_PAGE);

        request.setAttribute("cities", citiesPerPage);
        request.setAttribute("nation", nation);

        RequestDispatcher dispatcher = request.getRequestDispatcher("city/jsp/search.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nation = request.getParameter("nation");
        List<City> cities = cityDAO.searchByNation(nation);
        request.setAttribute("cities", cities);

        int currentPage = 1;

        List<City> citiesFirstPage = getCitiesPerPage(cities,1, RECORDS_PER_PAGE);

        int nOfPages = getNumOfPages(cities.size());

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", RECORDS_PER_PAGE);

        request.setAttribute("cities", citiesFirstPage);
        request.setAttribute("nation", nation);

        RequestDispatcher dispatcher = request.getRequestDispatcher("city/jsp/search.jsp");
        dispatcher.forward(request, response);
    }

    private int getNumOfPages(int rows){
        int nOfPages = rows / RECORDS_PER_PAGE;

        if(rows % RECORDS_PER_PAGE > 0){
            nOfPages++;
        }
        return nOfPages;
    }

    private List<City> getCitiesPerPage(List<City> cities, int currentPage, int recordsPerPage){
        List<City> result = new ArrayList<City>();
        int begin = (currentPage-1) * recordsPerPage;
        int end = 0;
        if(recordsPerPage > (cities.size() - begin)){
            end = cities.size();
        } else {
            end = currentPage * recordsPerPage;
        }

        for(int i = begin; i < end; i++){
            result.add(cities.get(i));
        }
        return result;
    }
}
