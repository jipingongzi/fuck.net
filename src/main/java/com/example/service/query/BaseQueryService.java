package com.example.service.query;

public interface BaseQueryService {

    String REPLACE_SQL = "replace_sql";

    default String getPageQuery(String sql,String orderKey,int pageSize,int pageNumber){
        String pageSql =
                "SELECT TOP " + pageSize + " * " +
                "FROM ( SELECT ROW_NUMBER() OVER ( ORDER BY t." + orderKey + " DESC ) AS rownumber,* " +
                "       FROM (" + REPLACE_SQL + ")t " +
                ")tt " +
                "WHERE tt.rownumber > " + (pageNumber * pageSize);
        pageSql = pageSql.replace(REPLACE_SQL,sql);
        return pageSql;
    }
}
