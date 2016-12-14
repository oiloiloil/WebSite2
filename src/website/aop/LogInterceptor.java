package website.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class LogInterceptor {
    // 宣告並取得logger
    private Logger logger = Logger.getLogger(LogInterceptor.class);

    // 程式在進入website下的方法前皆會執行此段程式
    @Before("execution(* website..*(..))")
    public void invoke() throws Throwable {
        // 將訊息寫至logger
        logger.info("進來了!!!");
    }
    /*
    @Before("anyMethod()") 
    public void doAccessCheck() {
        System.out.println("anyMethod");
    	System.out.println("前置通知"); 
    }
    
    @Before("anyMethod() && args(name)")  
    public void doAccessCheck(String name) {  
        System.out.println(name + "前置通知");  
    }
    
    @AfterReturning("anyMethod()") 
    public void doAfterReturn() { 
        System.out.println("后置通知"); 
    }
    
    @AfterReturning(pointcut = "anyMethod()", returning = "result")  
    public void doAfterReturn(String result) {  
        System.out.println("后置通知  " + result);  
    }
    
    @After("anyMethod()")  
    public void doAfter() {  
        System.out.println("最终通知");  
    }
    
    @AfterThrowing("anyMethod()") 
    public void doAfterThrow(){ 
        System.out.println("异常通知"); 
    }
    
    @AfterThrowing(pointcut = "anyMethod()", throwing = "e")  
    public void doAfterThrow(Exception e) {  
        System.out.println("异常通知------" + e.getMessage());  
    }
    
    @Around("anyMethod()")  
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {  
        System.out.println("环绕通知  开始");  
        Object obj = pjp.proceed();  
        System.out.println("环绕通知  结束");  
        return obj;  
    }
    */
}
