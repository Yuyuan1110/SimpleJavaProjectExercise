package travel.service;

import travel.domain.PageBean;
import travel.domain.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    public List<Product> findAllProduct();
    int totalCount(Map<String, String[]> parameterMap);

    PageBean<Product> findByPage(int currentPage, int rows, Map<String, String[]> parameterMap);
}
