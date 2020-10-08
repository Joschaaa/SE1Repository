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
        
        ParkhausController controller = ParkhausController.GetInstance();
        
        switch (func)
        {       
            case "Initialize":
                controller.Initialize(Integer.parseInt(param));
                break;
            case "Enter":
                controller.besucherEinfahrt(Integer.parseInt(param));
                break;    
            case "Pay":
                controller.besucherBezahlt(Integer.parseInt(param));
                break;                  
            case "Leave":
                controller.besucherAusfahrt(Integer.parseInt(param));
                break;               
            case "UpdateTicketList":
                result = controller.UpdateTicketList();
                break;             
            case "GetEarnings":
                result = controller.GetEarnings();
                break;
        }

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(result);
        
    }
}
