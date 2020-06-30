package com.sundeinfo.core.log;


import com.sundeinfo.foundation.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class LogInfo extends Entity {

    private String http_Method;

    private String class_Method;

    private String ip;

    private String url;

    private String parameter;

    private String result;

    private long startTime;

    private long endTime;

    private long execTime;

    private long userId;

    private String userName;

    private String execInfo;

    private Object returnValue;

    public String getLog(){
        return String.format("[%s] 用户[%s]执行了操作，执行时间(%s)ms",getDateString(),getUserName(),getExecTime());
    }

    public String getDateString(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(getStartTime()));
    }
}
