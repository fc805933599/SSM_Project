package cn.itcast.Aop;

import cn.itcast.Service.SysLogService;
import cn.itcast.domain.Syslog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

//将切面类对象的创建权交由spring管理
@Component
//切面类
@Aspect
public class LogAop {
    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;

    //通知类型和切点表达式
      @Around("execution(* cn.itcast.Controller.*.*(..))")
      public Object around(ProceedingJoinPoint joinPoint){
          /**
           * 主键 无意义uuid
           * 访问时间
           * 操作者用户名
           * 访问ip
           * 访问资源url
           * 执行时长
           * 访问方法
           */
          try {
              Date VisitTime = new Date();  //访问时间
              String username = SecurityContextHolder.getContext().getAuthentication().getName();//操作者用户名
              long start = System.currentTimeMillis();  // 当前时间毫秒值
              Object proceed = joinPoint.proceed();    // 执行切入点方法
              String ip = request.getRemoteAddr();    //访问ip
              String url = request.getRequestURI();   //访问资源url
              long end = System.currentTimeMillis();  // 访问结束毫秒值
              long executionTime = end - start;       // //获取Controller方法执行时长；
              String className = joinPoint.getTarget().getClass().getName();  //获取切入点方法所在类的字节码对象
              String methodName = joinPoint.getSignature().getName();       //获取切入点方法名称
              String method = className + "." + methodName;

              //将以上参数封装SysLog对象中；
              Syslog syslog = new Syslog();
              syslog.setUsername(username);
              syslog.setVisitTime(VisitTime);
              syslog.setIp(ip);
              syslog.setUrl(url);
              syslog.setExecutionTime(executionTime);
              syslog.setMethod(method);

              //调用业务层保存
              System.out.println(syslog);
              sysLogService.save(syslog);
              return proceed;

          } catch (Throwable throwable) {
              throwable.printStackTrace();
          }
          return null;
      }
}
