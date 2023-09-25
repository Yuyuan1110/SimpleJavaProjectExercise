package travel.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import travel.dao.UserDao;
import travel.domain.User;
import travel.util.JDBCUtils;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByAccount(String account) {
        User user = null;
        try {
            String sql = "select * from User where account = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), account);
        } catch (Exception e) {
        }
        return user;
    }

    @Override
    public void save(User user) {
        String sql = "insert into User (account, username, email, password, birthday, gender, phone, active_code)" +
                " values(?,?,?,?,?,?,?,?)";

        template.update(sql,
                user.getAccount(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getBirthday(),
                user.getGender(),
                user.getPhone(),
                user.getActive_code());
    }

    @Override
    public User findByCode(long code) {
        User user = null;
        try {
            String sql = "select * from User where active_code = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (Exception e) {
        }
        return user;
    }

    @Override
    public User setUserState(User u) {
        String sql = "update user set active_state = ? where account = ?";
        template.update(sql, "1", u.getAccount());
        return this.findByAccount(u.getAccount());
    }

    @Override
    public User findUserByLogin(User u) {
        User user = null;
        try {
            String sql = "select * from User where account = ? and password = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), u.getAccount(), u.getPassword());
        } catch (Exception e) {
        }
        return user;
    }

    public User setUserState(User u, int state) {
        String sql = "update user set active_state = ? where account = ?";
        template.update(sql, state, u.getAccount());
        return this.findByAccount(u.getAccount());
    }
}
