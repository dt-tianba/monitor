package com.monitor.xo.service;

import com.monitor.xo.vo.CpuDataVO;

import java.util.List;

public interface PrometheusDataService {

    /**
     * 获取具体的json数据，并对数据进行相应的处理后进行返回
     * @return
     */
    List<CpuDataVO> getValueByName(String name);
}
