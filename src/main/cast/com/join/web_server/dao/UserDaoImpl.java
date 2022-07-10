package com.join.web_server.dao;
import com.join.web_server.utils.JDBCUtils;

import com.join.web_server.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    public User selectByName(String username) throws SQLException {

        Connection conn = JDBCUtils.getConnection();
        conn.setAutoCommit(false);
        String sql = "select * from ex where username=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();
        User user = new User();
        while(rs.next()){
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPwd(rs.getString("pwd"));
            user.setProfession(rs.getString("profession"));
            user.setSignInTime(rs.getLong("signInTime"));
            user.setDuration(rs.getInt("duration"));
            user.setFileName(rs.getString("fileName"));
        }
        conn.commit();
        JDBCUtils.close(rs,ps,conn);
        return user;
    }
    public List<User> selectByName_list(String username) throws SQLException {

        Connection conn = JDBCUtils.getConnection();
        String sql = "select duration from ex where username=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,username);
//        System.out.println("selectByName_list:"+username);
        ResultSet rs = ps.executeQuery();
        List<User> list=new ArrayList<User>();
        while(rs.next()){
            User user = new User();
            user.setUsername(username);
            user.setDuration(rs.getInt("duration"));
            list.add(user);
        }
//        System.out.println("userDaoImpl"+list.size());
        JDBCUtils.close(rs,ps,conn);
        return list;
    }
         /*
            升序：select * from 表名 order by表中的字段 asc(MySQL中默认是升序排列，可不写) ；
            降序：select  * from 表名 order by 表中的字段 desc ；
            若要进行同时一个升序一个降序 例如：order by 升序字段 asc，降序字段 desc
        */
    @Override
    public List<User> select() throws SQLException, ClassNotFoundException {
        Connection conn = JDBCUtils.getConnection();
        String sql = "select id,username,profession,duration,fileName from ex order by duration desc;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<User> list = new ArrayList<User>();
        while (rs.next()){
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setDuration(rs.getInt("duration"));
            user.setProfession(rs.getString("profession"));
            user.setFileName(rs.getString("fileName"));
            list.add(user);
        }
        JDBCUtils.close(rs,ps,conn);
        return list;
    }

    public User sign(String username, String pwd, String profession,String fileName) throws  SQLException {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into ex(username,pwd,profession,fileName) values(?,?,?,?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,username);
        ps.setString(2,pwd);
        ps.setString(3,profession);
        ps.setString(4,fileName);
        int count = ps.executeUpdate();
        System.out.println(count);
        JDBCUtils.close(ps,conn);
        return null;
    }

    public User update(String username,Long t1,int i) throws SQLException {
        //向数据库中录入签到时间
        Connection conn = JDBCUtils.getConnection();
        String sql = "update ex set signInTime=? where username=?;";
        return getUser(username, t1, conn, sql);
    }

    public User update(String username, Long t2) throws SQLException {
        //向数据库录入签退时间
        Connection conn = JDBCUtils.getConnection();
        String sql = "update ex set signOutTime=? where username=?;";
        return getUser(username, t2, conn, sql);
    }

    private User getUser(String username,Long t, Connection conn, String sql) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1,t);
        ps.setString(2,username);
        int count = ps.executeUpdate();
        System.out.println(count);
        JDBCUtils.close(ps,conn);
        return null;
    }

    public int update(Long duration,String username) throws SQLException {
        //更新数据库中的时长
        Connection conn = JDBCUtils.getConnection();
        String sql = "update ex set duration=? where username=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1,duration);
        ps.setString(2,username);
        int count = ps.executeUpdate();
        JDBCUtils.close(ps,conn);
        return count;
    }


}
