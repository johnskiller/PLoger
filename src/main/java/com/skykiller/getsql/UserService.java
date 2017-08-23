package com.skykiller.getsql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//    @Autowired
//    private WrapedJdbcTemplate wrapedJdbcTemplate;


    private static User mapRow(ResultSet result, int rowNum) {
        User contact = new User();
        try {
            contact.setName(result.getString("name"));

        contact.setAge(result.getInt("age"));
        contact.setId(result.getInt("id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }

    public List<User> getAllUsers() {
        //return null; //jdbcTemplate.queryForList("select * from test_users");
        return jdbcTemplate.query("select * from test_users", UserService::mapRow);
    }


    public List<User> findUserByName(String name) {
        //return null; //jdbcTemplate.queryForList("select * from test_users");
        String sql = "select * from test_users where name=:name";
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("name", name);

        String sqlToUse = NamedParameterUtils.substituteNamedParameters(sql, paramSource);
        System.out.println(sqlToUse);

        return namedParameterJdbcTemplate.query(sql,
                paramSource, new BeanPropertyRowMapper(User.class));
    }

    public List<User> findUserByName2(String name) {
        //return null; //jdbcTemplate.queryForList("select * from test_users");
        String sql = "select * from test_users where name=?";



        //JdbcTemplate & ラムダ式 & 三項演算子
        return jdbcTemplate.query(sql, UserService::mapRow, new Object[]{new String(name)});


    }
}
