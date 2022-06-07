package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimeTraceAop {
    @Around("execution(* hello.hellospring..*(..))")
    // 어디서 실행 할 것인지 지정해준다.
    // 여기서는 hellospring 패키지의 모든 메서드에서 실행한다.
    // 클래스명 등등 지정해 줄 수도 있다.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString()); // 실행되고 있는 메서드를 표시한다.
        try {
            return joinPoint.proceed();
            // 메서드를 진행한다.
        } finally {
            // 에러가 난 경우에도 실행되야 하기 때문에 finally로 실행한다.
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}