package info.yinhua.core.context.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import info.yinhua.core.data.model.TLog;
import info.yinhua.core.service.TLogService;

/**
 * 在xml中不能启用aspectJ的注解模式，所以无法使用aop
 * @author user
 *
 */
@Aspect
@Controller
public class LogAspect {
	
	@Autowired
	private TLogService logService;

	@Pointcut("execution(public * info.yinhua.core.context.security.UserManager.*(..))")
	public void pointCut() {}

	@After("pointCut()")
	public void log(JoinPoint joinPoint) {
		TLog log = new TLog();
		logService.create(log);
	}
}
