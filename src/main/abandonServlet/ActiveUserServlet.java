package travel.web.servlet.abandonServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import travel.domain.User;
import travel.service.UserService;
import travel.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.Date;

@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        long code = Long.parseLong(req.getParameter("active_code"));
        if(new Date().getTime() - code > (1*60*1000)){
            resp.sendRedirect(req.getContextPath()+"/validationTimeExceeded.html");
        }else {
            UserService us = new UserServiceImpl();
            User activeUser = us.active(code);
            if (activeUser != null){
                resp.sendRedirect(req.getContextPath()+"/activeSuccess.html");
            }else {
                System.out.println("null");
            }

        }
    }
}