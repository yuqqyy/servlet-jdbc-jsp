package com.join.web_server.dao;

import com.join.web_server.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<User> select() throws SQLException, ClassNotFoundException;
    User selectByName(String username) throws SQLException, ClassNotFoundException;
    List<User> selectByName_list(String username) throws SQLException;
    User sign(String username, String pwd, String profession,String fileName) throws ClassNotFoundException, SQLException;
    User update(String username, Long t2) throws ClassNotFoundException, SQLException;
    User update(String username, Long t1, int i) throws ClassNotFoundException, SQLException;
    int update(Long duration, String username) throws ClassNotFoundException, SQLException;
}
