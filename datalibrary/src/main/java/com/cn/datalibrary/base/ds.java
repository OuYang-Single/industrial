package com.cn.datalibrary.base;

import com.github.mikephil.charting.data.Entry;
import com.ijcsj.common_library.bean.TemperatureBase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ds {
    int i=0;
    public void aVoid(){
        List<TemperatureBase>temperatureBases=new ArrayList<>();
        List<Entry> userDTOs = temperatureBases.stream()
                .map(user ->{
                    i++;
                    return new Entry(i, user.getTemperature(),user);
                }
        ).collect(Collectors.toList());
    }
}
