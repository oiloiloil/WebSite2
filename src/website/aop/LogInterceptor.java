package website.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Before;
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
}
