package com.clouds.dao.daoImpl;

import com.clouds.dao.UserDao;
import com.clouds.domain.User;
import com.clouds.utils.CloudUtils;
import com.clouds.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Description TODO
 * @Author lly
 * @Date 2020/3/6 15:58
 * @Version V1.0
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 通过用户名和密码查询用户信息
     * @param user
     * @return
     */
    @Override
    public User findUser(User user) {
        String sql = "select * from c_user where username=? and password=?";
        User user1;
        try {
           user1 = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getUsername(), user.getPassword());
        } catch (DataAccessException e) {
            return null;
        }
        return user1;
    }

    /**
     * 通过用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public User findUserByUserName(String username) {
        String sql = "select * from c_user where username=?";
        User user;
        try {
            user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),username);
        } catch (DataAccessException e) {
            return null;
        }
        return user;
    }

    /**
     * 添加一个用户
     * @param user
     */
    @Override
    public void addUser(User user) {
        String sql = "insert into c_user(username,password,email,max_storage,use_storage) values(?,?,?,?,?)";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getEmail(), CloudUtils.maxStorage,CloudUtils.useStorage);
    }

    @Override
    public void updateUserById(User user) {
        String sql = "update c_user set username=?,password=?,email=? where id=?";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getId());
    }

    //修改用户使用空间
    @Override
    public void updateUserUseStorageByUser(User user,Long UseStorage) {
        String sql = "update c_user set use_storage=? where id=?";
        jdbcTemplate.update(sql,UseStorage,user.getId());
    }

    @Override
    public User findUserById(Integer id) {
        String sql = "select * from c_user where id=?";
        User user;
        try {
            user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),id);
        } catch (DataAccessException e) {
            return null;
        }
        return user;
    }
}
