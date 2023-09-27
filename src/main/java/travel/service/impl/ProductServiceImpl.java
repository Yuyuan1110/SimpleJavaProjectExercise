package travel.service.impl;

import travel.dao.ProductDao;
import travel.dao.impl.ProductDaoImpl;
import travel.domain.Product;
import travel.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();
    @Override
    public List<Product> findAllProduct() {
        return productDao.findAllProduct();
    }
}
