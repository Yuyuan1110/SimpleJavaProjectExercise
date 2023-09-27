package travel.dao;

import travel.domain.Product;

import java.util.List;

public interface ProductDao {

    public List<Product> findAllProduct();
}
