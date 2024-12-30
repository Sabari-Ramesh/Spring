package SpringEcom.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class Aop {

    public static final Logger log= LoggerFactory.getLogger(Aop.class);

    //Return Type,fully Qualified Package name , Arguments...
//    @Before("execution(* SpringEcom.Controller.getProducts(..))")
    @Before("execution(* SpringEcom.Controller.ProductController.getProducts(..))")
    public void methodCalling(JoinPoint jp){
       log.info("Method Called.................."+jp.getSignature().getName());
    }

    //After Execution Program...
    @After("execution(* SpringEcom.Controller.ProductController.getProducts(..))")
    public void methodExecuted(JoinPoint jp){
        log.info("Method Executed.................."+jp.getSignature().getName());
    }

    @AfterReturning("execution(* SpringEcom.Controller.ProductController.getProducts(..))")
    public void MethodAfterReturning(JoinPoint jp){
        log.info("Method SuccessFully Returning......"+jp.getSignature().getName());
    }

    @AfterThrowing("execution(* SpringEcom.Controller.ProductController.getProducts(..))")
    public void MethodThrowingError(JoinPoint jp){
        log.info("Method has Some issues......"+jp.getSignature().getName());
    }

    @Around("execution(* SpringEcom.Controller.ProductController.getProducts(..))")
    public Object performanceTestOfMethod(ProceedingJoinPoint jp) throws Throwable {
        long startTime=System.currentTimeMillis();
        Object obj=jp.proceed();
        long endTime=System.currentTimeMillis();
        log.info("Method Performance Time is ==========================================>"+(endTime-startTime)+" ms Taken By : "+jp.getSignature().getName());
        return obj;
    }

    @Around("execution(* SpringEcom.Controller.ProductController.findProductById(..)) && args(id)")
    public Object validateInputs(ProceedingJoinPoint jp,int id) throws Throwable {
        if(id<0){
            id=-id;
        }
        log.info("Proceeding is start to Executed.....");
        Object obj=jp.proceed(new Object[]{id});
        return obj;
    }

}
