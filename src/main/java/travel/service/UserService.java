package travel.service;

import travel.domain.User;

public interface UserService {

    //    user register.
    boolean regist(User user);

    User active(long code);

    User findLoginUser(User user);
}
