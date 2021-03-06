package cn.zg.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cn.zg.annotation.CacheNameSpace;
import cn.zg.annotation.QueryCache;
import cn.zg.annotation.QueryCacheKey;
import cn.zg.utils.redis.RedisUtils;

/**
 * @ClassName: RedisCacheAspect
 * @Description: String类型的redis缓存切面
 * @author zhugang
 * @date 2018年12月2日
 */
@Aspect
@Service
public class RedisCacheAspect {
	private static Logger logger = LoggerFactory.getLogger( RedisCacheAspect.class );
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Pointcut( "@annotation(cn.zg.annotation.QueryCache)" )
	public void queryCachePointcut(){}
	
	@Around("queryCachePointcut()")
	public List<Object> interceptor( ProceedingJoinPoint joinPoint ) throws Throwable{
		long beginTime = System.currentTimeMillis();
		logger.debug( "aop-redis>>>>>>>>>start");
		/*
		 * 获取被切面信息
		 */
		Object[] args = joinPoint.getArgs();
		String className = joinPoint.getSignature().getName();
		String methodName =  joinPoint.getTarget().toString().split( "@" )[0];
		logger.debug( "aop-redis>>>>>>>>>className:" + className);
		logger.debug( "aop-redis>>>>>>>>>methodName:" + methodName);
		//注解解析
		MethodSignature signature = ( MethodSignature ) joinPoint.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        CacheNameSpace cacheType = method.getAnnotation( QueryCache.class ).nameSpace();
        String key = null;
        int i = 0;
        // 循环所有的参数
        for ( Object value : args ) {
            MethodParameter methodParam = new SynthesizingMethodParameter(method, i);
            Annotation[] paramAnns = methodParam.getParameterAnnotations();

            // 循环参数上所有的注解
            for ( Annotation paramAnn : paramAnns ) {
                if ( paramAnn instanceof QueryCacheKey ) {
                    QueryCacheKey requestParam = ( QueryCacheKey ) paramAnn;
                    key = cacheType.name() + "_" + value;   // 取到QueryCacheKey的标识参数的值
                }
            }
            i++;
        } 
        
        if ( key == null || key.isEmpty() ) throw new RuntimeException("缓存key值不存在");
        logger.debug( "aop-redis>>>>>>>>>key:" + key );
        //取缓存
        if( redisUtils.hasKey( key )) {
        	//List<Object> lists = redisUtils.get( key, new ArrayList<Object>().getClass() );
        	List<Object> lists = (List<Object>) redisUtils.get( key, (Class<?>) List.class );
        	logger.debug( "aop-redis>>>>>>>>>获取缓存数据:" + key );
        	long endTime = System.currentTimeMillis();
        	logger.debug( "aop-redis>>>>>>>>>end" + endTime );
        	logger.debug( "获取缓存数据 >>>>>>>>>>耗时：" + ( endTime - beginTime) );
        	return lists;
        }
        //取DB
        List<Object> lists = (List<Object>) joinPoint.proceed();
        redisUtils.set( key, new Gson().toJson( lists ).toString() );
        logger.debug( "aop-redis>>>>>>>>>DB缓存数据:" + lists.toString() );
        long endTime = System.currentTimeMillis();
    	logger.debug( "aop-redis>>>>>>>>>end" + endTime );
    	logger.debug( "DB缓存数据 >>>>>>>>>>耗时：" + ( endTime - beginTime) );
		return lists;
	}	
}
