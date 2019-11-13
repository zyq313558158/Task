package com.jnshu.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * @author pipiretrak
 * @date 2019/3/22 - 16:42
 */
//让这个类被spring当作一个bean管理
@Component
//标明这个类是一个切面对象
@Aspect
public class LogAspect {
    private static Logger logger = Logger.getLogger(String.valueOf(LogAspect.class));

    /**
     * 定义切点函数
     */
    //    execution([权限修饰符] [返回值类型] [简单类名/全类名] [方法名]([参数列表]))
    @Pointcut("execution(* com.jnshu.service.*.*(..))")
    //声明一个切入点
    public void pointcut(){

    }

    // 声明该方法是一个环绕通知
    @Around(value = "execution(* com.jnshu.controller.*.*(..)) || execution(* com.jnshu.service.*.*(..))")
    public Object TimeAround(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        Object obj = null;
        //获取执行的名称
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        //获取执行的方法
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        long start = System.currentTimeMillis();
        obj = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        logger.info("开始执行"+className+"中的方法"+methodName+"执行时间"+(end-start)+"ms");
        return obj;
    }
}