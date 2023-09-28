package travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import travel.domain.ResultInfo;
import travel.domain.User;
import travel.service.UserService;
import travel.service.impl.UserServiceImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

@WebServlet("/user/*")
public class userServlet extends BaseServlet {

    UserService service = new UserServiceImpl();

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> map = req.getParameterMap();

        // check code verify
        String check = map.get("checkCode")[0];
        HttpSession session = req.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        session.removeAttribute(checkCode_session);
        if (!checkCode_session.equalsIgnoreCase(check)) {
            System.out.println("verification code failed.");
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("verification code failed");

//            ObjectMapper mapper = new ObjectMapper();
//            String json = mapper.writeValueAsString(info);
//
//            resp.setContentType("application/json;charset=utf-8");
            String json = super.writeValueAsString(info);
            resp.getWriter().write(json);
            return;
        }

        //solve exception: DateConverter does not support default String to 'Date' conversion.
        DateConverter converter = new DateConverter();
        converter.setPattern(("yyyy-MM-dd"));
        ConvertUtils.register(converter, Date.class);
        User user = new User();

        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

//        UserService service = new UserServiceImpl();
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        if (flag) {
            // registration success
            info.setFlag(true);
        } else {
            info.setFlag(false);
            info.setErrorMsg("registration fail");
        }

        String json = super.writeValueAsString(info);
        resp.getWriter().write(json);
    }

    public void active(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long code = Long.parseLong(req.getParameter("active_code"));
        if (new Date().getTime() - code > (1 * 60 * 1000)) {
            resp.sendRedirect(req.getContextPath() + "/validationTimeExceeded.html");
        } else {
//            UserService us = new UserServiceImpl();
            User activeUser = service.active(code);
            if (activeUser != null) {
                resp.sendRedirect(req.getContextPath() + "/activeSuccess.html");
            } else {
                System.out.println("null");
            }

        }
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();

        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        ResultInfo info = new ResultInfo();
        UserService userService = new UserServiceImpl();
        User loginUser = userService.findLoginUser(user);
        if(loginUser == null){
            info.setFlag(false);
            info.setErrorMsg("Account or Password error");
        }

        if(loginUser != null && loginUser.getActive_state() == 0){
            info.setFlag(false);
            info.setErrorMsg("Account not active");
        }

        if(loginUser != null && loginUser.getActive_state() > 0){
            info.setFlag(true);
        }

//        ObjectMapper mapper = new ObjectMapper();
//        resp.setContentType("application/json;charset=utf-8");
//        mapper.writeValue(resp.getOutputStream(), info);
        super.writeValue(info, resp);
    }
}