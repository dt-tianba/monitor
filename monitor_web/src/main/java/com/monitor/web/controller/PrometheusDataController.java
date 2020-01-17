package com.monitor.web.controller;


import com.monitor.xo.service.PrometheusDataService;
import com.monitor.xo.vo.CpuDataVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * prometheus数据接口
 * @since 01.17 2020
 */
@RestController
@RequestMapping("prometheus")
public class PrometheusDataController {

    @Autowired
    private PrometheusDataService prometheusDataService;

    @GetMapping("getJson")
    public List<CpuDataVO> getPrometheusByName(@RequestParam(name = "data", required = false) String data){

        try {

            if (StringUtils.isBlank(data)){
                data = "cpuData";
            }
            return prometheusDataService.getValueByName(data);
        }catch (Exception e){
            return null;
        }

    }

}
