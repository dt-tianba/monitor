package com.monitor.xo.service.impl;

import com.monitor.utils.HttpJsonUtil;
import com.monitor.xo.mapper.PrometheusDataMapper;
import com.monitor.xo.analyze.CpuDataAnalyze;
import com.monitor.xo.service.PrometheusDataService;
import com.monitor.xo.vo.CpuDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PrometheusDataServiceImpl implements PrometheusDataService {

    @Autowired
    private PrometheusDataMapper prometheusDataMapper;

    @Override
    public List<CpuDataVO> getValueByName(String name) {
        //获取到数据请求的url
        String urlValue = prometheusDataMapper.getUrlValueByname(name);

        //根据url获取prometheus的返回数据
        String json = HttpJsonUtil.HttpGet(urlValue);

        //解析json数据
        return new CpuDataAnalyze().handleValue(json);


    }


}
