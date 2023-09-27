package travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import travel.dao.ProductDao;
import travel.domain.Product;
import travel.domain.User;
import travel.util.JDBCUtils;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Product> findAllProduct() {
        String sql = "select * from product";
        return template.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
    }
}
