package travel.service.impl;

import travel.dao.ProductDao;
import travel.dao.impl.ProductDaoImpl;
import travel.domain.PageBean;
import travel.domain.Product;
import travel.service.ProductService;

import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();
    @Override
    public List<Product> findAllProduct() {
        return productDao.findAllProduct();
    }

    @Override
    public int totalCount(Map<String, String[]> parameterMap) {
        return productDao.totalCount(parameterMap);
    }

    @Override
    public PageBean<Product> findByPage(int currentPage,int rows,  Map<String, String[]> parameterMap) {
        PageBean<Product> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        int totalCount = totalCount(parameterMap);
        pb.setTotalCount(totalCount);
        if(pb.getTotalCount() % pb.getRows() ==0){
            pb.setTotalPage(pb.getTotalCount()/pb.getRows());
        }else {
            pb.setTotalPage(pb.getTotalCount()/pb.getRows()+1);
        }

        List<Product> pageProduct = productDao.findByPage(currentPage, rows, parameterMap);
        pb.setList(pageProduct);

        return pb;
    }
}
