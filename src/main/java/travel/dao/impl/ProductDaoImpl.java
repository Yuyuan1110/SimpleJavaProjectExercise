package travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import travel.dao.ProductDao;
import travel.domain.Product;
import travel.domain.User;
import travel.util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Product> findAllProduct() {
        String sql = "select * from product";
        return template.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
    }

    @Override
    public int totalCount(Map<String, String[]> parameterMap) {
        String sql = "select count(*) from product where 1=1";
        List<Object> params = new ArrayList<Object>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()){
            if ("currentPage".equals(entry.getKey()) || "rows".equals(entry.getKey())) {
                continue;
            }
            if (!"".equals(entry.getValue()[0]) && entry.getValue()[0] != null) {
                sql += " and " + entry.getKey() + " like ?";
                params.add("%" + entry.getValue()[0] + "%");
            }
//            System.out.println(entry.getKey()+": "+entry.getValue()[0]);
        }
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    @Override
    public List<Product> findByPage(int currentPage, int rows,  Map<String, String[]> parameterMap) {
        String sql = "select * from PRODUCT where 1=1";
        List<Object> params = new ArrayList<Object>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()){
            if ("currentPage".equals(entry.getKey()) || "rows".equals(entry.getKey())) {
                continue;
            }
            if (!"".equals(entry.getValue()[0]) && entry.getValue()[0] != null) {
                sql += " and " + entry.getKey() + " like ?";
                params.add("%" + entry.getValue()[0] + "%");
            }
//            System.out.println(entry.getKey()+": "+entry.getValue()[0]);
        }

        params.add((currentPage-1)*5);
        params.add(rows);
        sql += " limit ?, ?";
//        System.out.println(sql);
        return template.query(sql, new BeanPropertyRowMapper<Product>(Product.class), params.toArray());
    }

    @Override
    public Product findByProductName(String productName) {
        Product product = null;

        try {
            String sql = "select * from product where product_name = ?";
            product = template.queryForObject(sql, new BeanPropertyRowMapper<Product>(Product.class), productName);
        } catch (Exception e) {
        }
        return product;
    }

    @Override
    public void saveProduct(Product product) {
        System.out.println("save execute");
        String sql = "insert into product(id, brand, product_name, price) values(?, ?, ?, ?)";
        template.update(sql, product.getId(), product.getBrand(), product.getProduct_name(), product.getPrice());
    }
}
