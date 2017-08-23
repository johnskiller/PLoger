package com.skykiller.getsql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;

@Component
public class WrapedJdbcTemplate extends JdbcTemplate {
    public WrapedJdbcTemplate() {
    }

    @Autowired
    public WrapedJdbcTemplate( DataSource dataSource) {
        this.setDataSource(dataSource);
        this.afterPropertiesSet();
    }

//    @Autowired
//    public WrapedJdbcTemplate(DataSource dataSource, boolean lazyInit) {
//        this.setDataSource(dataSource);
//        this.setLazyInit(lazyInit);
//        this.afterPropertiesSet();
//    }
    @Override
    public <T> T execute(PreparedStatementCreator psc, PreparedStatementCallback<T> action) throws DataAccessException {
        System.out.println("Haha, in my wrapper");
        return super.execute(psc,action);
    }
}
