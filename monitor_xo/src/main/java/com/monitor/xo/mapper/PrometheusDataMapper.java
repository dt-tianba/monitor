package com.monitor.xo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface PrometheusDataMapper {

    @Select("select url_value from m_prometheus_data where url_name = #{urlName}")
    String getUrlValueByname(@Param("urlName") String urlName);
}
