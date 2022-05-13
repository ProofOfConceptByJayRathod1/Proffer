package com.proxibid.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Pointcut("execution(* com.proxibid.controller.*.*(..))")
	public void controllerPointcut() {
	}

	@Pointcut("execution(* com.proxibid.service.*.*(..))")
	public void servicePointcut() {
	}

	@Before("controllerPointcut()")
	@After("controllerPointcut()")
	public void beforeAfterControllerAdvice(JoinPoint joinPoint) {
		logger.info("Controller Method invoked : " + joinPoint.getSignature());
	}

	@Before("servicePointcut()")
	@After("servicPointcut()")
	public void beforeAfterServiceAdvice(JoinPoint joinPoint) {
		logger.info("Service Method invoked : " + joinPoint.getSignature());
	}

//	@AfterReturning("controllerPointcut()")
//	public void afterReturning(JoinPoint joinPoint) {
//		logger.info("Method returned succcessfully : " + joinPoint.getSignature());
//		joinPoint.getSignature();
//	}

	@AfterThrowing("execution(* com.proxibid.controller.*.*(..))")
	public void afterThrowing(JoinPoint joinPoint) {
		logger.error("Exception thrown by " + joinPoint.getSignature() + " ");
	}

//	@Around("execution(* com.springbootapp.emlpoyee.service.*.*(..))")
//	public List<Employee> aroundAdvcice(ProceedingJoinPoint joinPoint) {
//		logger.info("Around Before method invoked : " + joinPoint.getSignature());
//		List<Employee> employees = null;
//		try {
//			employees = (List<Employee>) joinPoint.proceed();
//			logger.info("Response : " + employees.toString());
//		} catch (Throwable e) {
//			logger.error(e.getMessage());
//		}
//		logger.info("Around After method invoked : " + joinPoint.getSignature());
//
//		return employees;
//	}

}
