package website.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Aspect
@Component
@Service
public class LogInterceptor {
    // 宣告並取得logger
    private Logger logger = Logger.getLogger(LogInterceptor.class);

    @Pointcut("execution(* website..*(..))")  
    private void anyMethod() {} // 聲明一個切入點  
    
    // 程式在進入website下的方法前皆會執行此段程式
    @Around("anyMethod()")
    public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
        // 將訊息寫至logger
    	String className = pjp.getTarget().getClass().getSimpleName();
    	String methodName = pjp.getSignature().getName();
    	logger.info("進入 Class: " + className + " method: " + methodName);  
        Object obj = pjp.proceed();  
        logger.info("離開 Class: " + className + " method: " + methodName);  
        return obj;
    }
    
    @AfterThrowing(pointcut = "anyMethod()", throwing = "e")  
    public void doAfterThrow(Exception e) {
    	logger.info("Error message: " + e.getMessage());  
    }
    
}
