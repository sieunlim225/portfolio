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
 * AOP ����
 */
@Component
@Aspect
public class LogAspect {

	private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

	/*
	 * PointCut (Where)
	 * JointPoint (When)
	 *  @Before (����)
	 *	@After (����)
	 *	@AfterReturning (������ ��ȯ ����)
	 *	@AfterThrowing (���� �߻� ����)
	 *	@Around (�޼ҵ� ���� ����)
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
	 * ���� INFO AOP
	 * @param jp
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.portfolio.project.mvc.*.*.*(..))")
	public Object executionMain(ProceedingJoinPoint jp) throws Throwable{
		String className = jp.getSignature().getDeclaringType().getName();
        String methodName = jp.getSignature().getName();
        String args = Arrays.toString(jp.getArgs());
		logger.info("================================���� ����================================");
		logger.info("Ŭ���� : {}",className);
		logger.info("�޼ҵ� : {}",methodName);
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object proceed = jp.proceed();
		
		sw.stop();
		double tts = sw.getTotalTimeSeconds();
		logger.info("���� �ð� : {}", tts);
		if(tts > 30) {
			logger.warn("WARNING! ���� �ð� 30�� �ʰ�");
		}
		logger.info("================================���� ����================================");
		
		return proceed;
	}
	
	
}
