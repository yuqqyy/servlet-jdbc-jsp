package com.join.web_server.service;


import com.join.web_server.dao.UserDao;
import com.join.web_server.dao.UserDaoImpl;
import com.join.web_server.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String pwd) throws SQLException, ClassNotFoundException {
        User user = userDao.selectByName(username);
        if(Objects.isNull(user)) return null;
        if(pwd.equals(user.getPwd())){
            System.out.println("登录success");
            return user;
        }
        return null;
    }
    public User select(String username) throws SQLException, ClassNotFoundException {
        User user = userDao.selectByName(username);
        return user;
    }
    public List<User> select_list(String username) throws SQLException {
        return userDao.selectByName_list(username);
    }

    public User sign(String username, String pwd, String profession,String fileName) throws SQLException, ClassNotFoundException {
        return userDao.sign(username,pwd,profession,fileName);
    }

    public List<User> select() throws SQLException, ClassNotFoundException{
        return userDao.select();
    }
    public User update(String username,Long t1,int i) throws ClassNotFoundException, SQLException{
        return userDao.update(username,t1,i);
    }
    public User update(String username,Long t2) throws ClassNotFoundException, SQLException{
        return userDao.update(username,t2);
    }

    @Override
    public int update(Long duration,String username) throws ClassNotFoundException, SQLException{
        return userDao.update(duration,username);
    }
}
