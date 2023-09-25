package travel.dao;

import travel.domain.User;

public interface UserDao {
    public User findByAccount(String account);
    public void save(User user);

    User findByCode(long code);

    User setUserState(User u);

    User findUserByLogin(User u);
}
