package com.example.service.query.impl;

import com.example.service.query.IOrderQueryService;
import com.example.service.query.IUserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderQueryServiceImpl implements IOrderQueryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Map<String, Object>> getGoodsOrderList(int pageNumber, int pageSize) {
        String sql = "";
        return jdbcTemplate.queryForList(
                getPageQuery(sql,"",pageSize,pageNumber)
        );
    }
}
