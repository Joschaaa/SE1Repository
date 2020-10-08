package com.parkhaus.jsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ParkhausServlet", urlPatterns = {"/ParkhausServlet"})
public class ParkhausServlet extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String func = request.getParameter("func");
        String param = request.getParameter("param");
        String result = "";
        
        ParkhausController controller = ParkhausController.getInstance();
        
        switch (func)
        {       
            case "initialize":
                controller.initialize(Integer.parseInt(param)); //param: Parkplatz-Anzahl
                break;
            case "enter":
                controller.besucherEinfahrt(Integer.parseInt(param)); //param: Parkplatz-Index
                break;    
            case "pay":
                controller.besucherBezahlt(Integer.parseInt(param)); //param: Parkplatz-Index
                break;                  
            case "leave":
                controller.besucherAusfahrt(Integer.parseInt(param)); //param: Parkplatz-Index
                break;               
            case "getTicketList":
                result = controller.getTicketList();
                break;             
            case "getEarnings":
                result = controller.getEarnings();
                break;
        }

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(result);        
    }
}
