package com.example.service.query.impl;

import com.example.service.query.ISystemQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SystemQueryServiceImpl implements ISystemQueryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<String> getUserIds(String serviceId) {
        String sql = "SELECT Phone FROM YUNYI_Member WHERE ServiceID = ?";
        return jdbcTemplate.queryForList(sql,serviceId).
                stream().
                map(i -> i.get("Phone").toString()).
                collect(Collectors.toList());
    }
}
