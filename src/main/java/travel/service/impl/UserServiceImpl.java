package travel.service.impl;

import jakarta.mail.MessagingException;
import travel.Email.Gmailer;
import travel.Email.impl.GmailerImpl;
import travel.dao.UserDao;
import travel.dao.impl.UserDaoImpl;
import travel.domain.User;
import travel.service.UserService;

import java.io.IOException;
import java.util.Date;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();


    //    user register.
    @Override
    public boolean regist(User user) {
        User u = userDao.findByAccount(user.getAccount());
        if (u != null) {
            return false;
        } else {
            long active_code = new Date().getTime();
            user.setActive_code(active_code);
            userDao.save(user);

            String context = "click following link to authentication. (availed time: 15 minutes) \n http://localhost/user/active?active_code="+active_code;
            Gmailer gmailer = new GmailerImpl();
            try {
                gmailer.SendEmail(user.getEmail(), "Email authentication", context);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return true;
        }
    }

    @Override
    public User active(long code) {
        User u = userDao.findByCode(code);
        if(u != null){
            if(u.getActive_state() < 1){
                userDao.setUserState(u);
                return u;
            }
            System.out.println("have been active before");
            return null;
        }
        System.out.println("cannot find the user:" + code);
        return null;
    }

    @Override
    public User findLoginUser(User user) {
        User u = userDao.findUserByLogin(user);
        return u;
    }


}
