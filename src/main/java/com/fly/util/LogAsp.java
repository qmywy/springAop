package com.fly.util;

import com.fly.bean.LogMessage;
import com.fly.dao.LogMessageMapper;
import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAsp {

    @Autowired
    private LogMessageMapper logMessageMapper;
    
    @Around("@annotation(com.fly.util.SystemLog)") 
    public Object logAround(ProceedingJoinPoint joinPoint) throws ClassNotFoundException {

        Object object = null;
        long startTime = System.currentTimeMillis();
        LogMessage logMessage = new LogMessage();

        String msg = getServiceMethodDescription(joinPoint);
        String params = getServiceMethodParams(joinPoint);
        logMessage.setStarttime(new Date());
        logMessage.setDesciption(msg);
        logMessage.setArgus(params);

        try {
            object = joinPoint.proceed(joinPoint.getArgs());
            logMessage.setSuccess("成功");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logMessage.setSuccess("失败");
            logMessage.setException(throwable.toString());
        }

        long endTime = System.currentTimeMillis();

        logMessage.setRuntime(endTime-startTime+"ms");

        logMessageMapper.insertSelective(logMessage);

        return object;
        
    }

//    @Before("@annotation(com.fly.util.SystemLog)")
//    public void logBefore(JoinPoint joinPoint){
//
//
//        try {
//
//            String msg = getServiceMethodDescription(joinPoint);
//            String params = getServiceMethodParams(joinPoint);
//
//            System.out.println(msg);
//            System.out.println(params);
//
//        } catch (ClassNotFoundException e) {
//            Logger.getRootLogger().error(e);
//        }
//
//    }

    private String getServiceMethodParams(JoinPoint joinPoint){
        Object[] objects = joinPoint.getArgs();
        Gson gson = new Gson();
        return gson.toJson(objects);
    }

    private String getServiceMethodDescription(JoinPoint joinPoint) throws ClassNotFoundException {

        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] params = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();

        String description = "";
        for (Method m : methods) {

            if(m.getName().equals(methodName)){
                Class[] classes = m.getParameterTypes();
                if (classes.length == params.length){
                    description = m.getAnnotation(SystemLog.class).type().getDescription();
                }
            }

        }

        return description;

    }

}
