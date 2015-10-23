/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atoudeft.web.mvc;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moumene
 */
public class ControleurFrontal extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action !=null)
        {
            if ("login".equals(action))
            {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/signin");  //redirection vers la servlet Login
                r.forward(request, response);     
                return;
            }            
            if ("logout".equals(action))
            {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/signout");  //redirection vers la servlet Logout
                r.forward(request, response);                
            }            
            return;
        }
        String  n1 = request.getParameter("nb1"),
                n2 = request.getParameter("nb2"),
                op = request.getParameter("operation");
        String msg = null;
        if ((n1!=null && n1.trim().equals("")) || (n2!=null && n2.trim().equals("")))
        {
            msg = "Vous devez donner 2 nombres et choisir une opération.";
            request.setAttribute("message", msg);
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
            r.forward(request, response);
        }
        else
        {
          char oper;
          if (op!=null && op.length()>0)
              oper = op.charAt(0);
          else
              oper = ' ';
          RequestDispatcher r = null;
          switch (oper)
          {
              case '+' :
                  //forward vers la servlet Addition :
                    r = this.getServletContext().getNamedDispatcher("somme");
                    break;
              case '-' :
                  //forward vers la servlet Soustraction :
                    r = this.getServletContext().getNamedDispatcher("difference");
                    break;
              case '*' :
                  //forward vers la servlet Multiplication :
                    r = this.getServletContext().getNamedDispatcher("produit");
                    break;
              case '/' :
                  //forward vers la servlet Division :
                    r = this.getServletContext().getNamedDispatcher("division");
                    break;
              default :
                    msg = "Opération "+oper+" inconnue";
                    request.setAttribute("message", msg);
                    //forward vers la page index.jsp :
                    r = this.getServletContext().getRequestDispatcher("/index.jsp");
          }
          r.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */

    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
