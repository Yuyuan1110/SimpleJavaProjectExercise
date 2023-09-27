package travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import travel.domain.Product;
import travel.service.ProductService;
import travel.service.impl.ProductServiceImpl;

import java.io.IOException;
import java.util.List;

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
}