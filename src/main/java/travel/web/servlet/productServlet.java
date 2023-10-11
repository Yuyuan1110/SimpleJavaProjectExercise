package travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import travel.domain.PageBean;
import travel.domain.Product;
import travel.domain.ResultInfo;
import travel.service.ProductService;
import travel.service.impl.ProductServiceImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/product/*")
public class productServlet extends BaseServlet {

    private ProductService service = new ProductServiceImpl();

    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> allProduct = service.findAllProduct();
//        ObjectMapper mapper = new ObjectMapper();
//
//        resp.setContentType("application/json;charset=utf-8");
//        mapper.writeValue(resp.getOutputStream(), allProduct);

        super.writeValue(allProduct, resp);
    }

    public void totalCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        int totalCount = service.totalCount(parameterMap);
        super.writeValue(totalCount, resp);
    }

    public void findByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        int currentPage = Math.abs(Integer.parseInt(parameterMap.get("currentPage") == null ||
                "".equals(parameterMap.get("currentPage")[0]) ||
                "0".equals(parameterMap.get("currentPage")[0]) ? "1" : parameterMap.get("currentPage")[0]));

        int rows = Math.abs(Integer.parseInt(parameterMap.get("rows") == null ||
                "".equals(parameterMap.get("rows")[0]) ||
                "0".equals(parameterMap.get("rows")[0]) ? "5" : parameterMap.get("rows")[0]));

        PageBean<Product> pb = service.findByPage(currentPage, rows, parameterMap);

        super.writeValue(pb, resp);
    }

    public void addProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Product product = new Product();
        try {
            BeanUtils.populate(product, parameterMap);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }


//        UserService service = new UserServiceImpl();
        boolean flag = service.addProduct(product);
        ResultInfo info = new ResultInfo();
        if (flag) {
            // registration success
            info.setFlag(true);
        } else {
            info.setFlag(false);
            info.setErrorMsg("add product fail");
        }

        String json = super.writeValueAsString(info);
        resp.getWriter().write(json);
    }


}