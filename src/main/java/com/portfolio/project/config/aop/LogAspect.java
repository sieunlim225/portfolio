package com.portfolio.project.config.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author Sieun Lim
 * AOP 설정
 */
@Component
@Aspect
public class LogAspect {

	private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

	/*
	 * PointCut (Where)
	 * JointPoint (When)
	 *  @Before (이전)
	 *	@After (이후)
	 *	@AfterReturning (정상적 반환 이후)
	 *	@AfterThrowing (예외 발생 이후)
	 *	@Around (메소드 실행 전후)
	 * Advice(What)
	 */

	/*@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint jp) throws Throwable{
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object proceed = jp.proceed();
		
		sw.stop();
		logger.info(sw.prettyPrint());
		return proceed;
	}*/
	
	/**
	 * 서비스 INFO AOP
	 * @param jp
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.portfolio.project.mvc.*.*.*(..))")
	public Object executionMain(ProceedingJoinPoint jp) throws Throwable{
		String className = jp.getSignature().getDeclaringType().getName();
        String methodName = jp.getSignature().getName();
        String args = Arrays.toString(jp.getArgs());
		logger.info("================================서비스 실행================================");
		logger.info("클래스 : {}",className);
		logger.info("메소드 : {}",methodName);
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object proceed = jp.proceed();
		
		sw.stop();
		double tts = sw.getTotalTimeSeconds();
		logger.info("실행 시간 : {}", tts);
		if(tts > 30) {
			logger.warn("WARNING! 실행 시간 30초 초과");
		}
		logger.info("================================서비스 종료================================");
		
		return proceed;
	}
	
	
}
