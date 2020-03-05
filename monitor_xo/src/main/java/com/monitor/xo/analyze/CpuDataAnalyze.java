package com.monitor.xo.analyze;


import com.monitor.xo.vo.CpuDataVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import java.text.SimpleDateFormat;
import java.util.*;

public class CpuDataAnalyze {



    long addSeconds = 0;//模拟时间增长

    long time = 0;//数据实时生成时间

    long nowTime = 0;//当前时间

    String cpu = "";//获取数据标识

    List<CpuDataVO> cpuDatas = new ArrayList<CpuDataVO>();

    List<Map<String, Object>> valueList = new ArrayList<Map<String, Object>>();//单个数据情况集合
    /**
     * 处理json数据
     *
     * @param jsonStr
     * @return
     */
    public List<CpuDataVO> handleValue(String jsonStr) {

        nowTime = Long.valueOf(new Date().getTime());
        cpuDatas.clear();
        addSeconds = 0;

        JSONArray provinceArray;
        if (jsonStr.startsWith("[") && jsonStr.endsWith("]")) {
            provinceArray = JSONArray.fromObject(jsonStr);
        } else {
            provinceArray = JSONArray.fromObject("[" + jsonStr + "]");
        }
        List<Map<String, Object>> mapList = (List<Map<String, Object>>) provinceArray;
        cpuDatas = forMapInList(mapList);
        return cpuDatas;
    }

    /**
     * 进一步遍历json深层数据
     *
     * @param mapList
     * @return
     */
    public List<CpuDataVO> forMapInList(List<Map<String, Object>> mapList) {

        List<CpuDataVO> resultCupData = new ArrayList<>();
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> obj = mapList.get(i);
            for (Map.Entry<String, Object> entry : obj.entrySet()) {
                String strkey1 = entry.getKey();
                Object strval1 = entry.getValue();
                if (strkey1.equals("values")) {
                    List<Object> list = (List<Object>) strval1;
                    time = nowTime;//重置CPU数据生成时间
                    addSeconds = 0;//重置时间增长进度
                    valueList = forList(list, new ArrayList<Map<String, Object>>());
                    CpuDataVO cpuData = new CpuDataVO();
                    cpuData.setId(cpu);
                    cpuData.setValueList(valueList);
                    cpuDatas.add(cpuData);
                    valueList = new ArrayList<Map<String, Object>>();
                    cpu = "";
                } else if (strkey1.equals("cpu")) {
                    cpu = strval1.toString();
                } else if (strval1 instanceof JSONArray || strval1 instanceof JSONObject) {
                    handleValue(strval1.toString());
                }
            }
        }
        return cpuDatas;
    }

    /**
     * 遍历key为Values的数组值，并通过list集合的方式进行返回
     *
     * @param list
     * @param valueList
     * @return
     */
    public List<Map<String, Object>> forList(List<Object> list, List<Map<String, Object>> valueList) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        for (Object object : list) {
            if (object instanceof JSONArray) {
                List<Object> objectList = (List<Object>) object;
                forList(objectList, valueList);
            } else if (object instanceof Double) {
                Double value = (Double) object;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dataStr = sdf.format(new Date(time - addSeconds));
                //TODO 通过Prometheus获取数据时使用
//                String dataStr = sdf.format(new Date(Long.valueOf(value.longValue())*1000));
                hashMap.put("date", dataStr);
                addSeconds = addSeconds + 30000;
            } else if (object instanceof String) {
                hashMap.put("fluctuation", (int) (Math.random() * 100));
                //TODO 通过Prometheus获取数据时使用
//                hashMap.put("fluctuation",object);
                valueList.add(hashMap);
            }
        }
        return valueList;
    }
}
