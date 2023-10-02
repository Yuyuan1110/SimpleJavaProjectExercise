package travel.dao;

import travel.domain.Product;

import java.util.List;
import java.util.Map;

public interface ProductDao {

    public List<Product> findAllProduct();

    int totalCount(Map<String, String[]> parameterMap);

    List<Product> findByPage(int currentPage,int rows,  Map<String, String[]> parameterMap);
}
