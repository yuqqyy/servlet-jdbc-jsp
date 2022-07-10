package com.join.web_server.service;


import com.join.web_server.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User login(String username, String pwd) throws SQLException, ClassNotFoundException;
    User sign(String username, String pwd, String profession,String fileName) throws SQLException, ClassNotFoundException;
    User select(String username) throws SQLException, ClassNotFoundException;
    List<User> select_list(String username) throws SQLException;
    List<User> select() throws SQLException, ClassNotFoundException;
    User update(String username,Long t1,int i) throws ClassNotFoundException, SQLException;
    User update(String username,Long t2) throws ClassNotFoundException, SQLException;
    int update(Long duration,String username) throws ClassNotFoundException, SQLException;
}
