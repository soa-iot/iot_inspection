package cn.zg.utils.redis;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import cn.zg.service.impl.PurificationSchemeService;

@Component( "redisUtils" )
public class RedisUtils {
	private static Logger logger = LoggerFactory.getLogger( RedisUtils.class );
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**   
	 * @Title: hasKey   
	 * @Description: 查询redis缓存key是否存在   
	 * @param: @param key
	 * @param: @return      
	 * @return: boolean        
	 */  
	public boolean hasKey( String key ) {
		logger.debug( "查询redis缓存key是否存在>>>>>>>>key" + key ); 
		if( key == null || key.isEmpty() ) {
			return false;
		}
		return stringRedisTemplate.hasKey( key );
	}
	
	/**   
	 * @Title: get   
	 * @Description: 根据key，查询value  
	 * @param: @param key
	 * @param: @return      
	 * @return: String        
	 */  
	public String get( String key ) {
		logger.debug( "获取redis缓存>>>>>>>>key" + key ); 
		if( key == null || key.isEmpty() ) {
			return null;
		}
		String value = stringRedisTemplate.opsForValue().get( key );
		logger.debug( "获取redis缓存>>>>>>>>value" + value );
		return value;
	}
	
	/**   
	 * @Title: get   
	 * @Description: 根据key、类，查询value,返回指定类对象   
	 * @param: @param key
	 * @param: @param calzz
	 * @param: @return      
	 * @return: T        
	 */  
	public <T> T get( String key, Class<T> calzz ){
		logger.debug( "获取redis缓存calzz>>>>>>>>key" + key ); 
		if( key == null || key.isEmpty() ) {
			return null;
		}
		String value = stringRedisTemplate.opsForValue().get( key );
		logger.debug( "获取redis缓存calzz>>>>>>>>value" + value );
		return new Gson().fromJson( value , calzz);
	}
	/*
	public List<Object> get( String key, List<Object> lists ){
		logger.debug( "获取redis缓存calzz>>>>>>>>key" + key ); 
		if( key == null || key.isEmpty() ) {
			return null;
		}
		String value = stringRedisTemplate.opsForValue().get( key );
		logger.debug( "获取redis缓存calzz>>>>>>>>value" + value );
		return null;
	}*/
	
	/**   
	 * @Title: set   
	 * @Description: 加入redis缓存   
	 * @param: @param key
	 * @param: @param value      
	 * @return: void        
	 */  
	public void set( String key, Object value ) {
		String objStr = new Gson().toJson( value );
		logger.debug( "加入redis缓存>>>>>>>>key-value:" + key + "-" + value);
		stringRedisTemplate.opsForValue().set( key, objStr, 2, TimeUnit.HOURS);
	}
	
	/**   
	 * @Title: set   
	 * @Description: 加入redis缓存  
	 * @param: @param key
	 * @param: @param value
	 * @param: @param expireTime      
	 * @return: void        
	 */  
	public void set(String key, Object value, long expireTime) {
		String objStr = new Gson().toJson( value );
		logger.debug( "加入redis缓存>>>>>>>>key-value:" + key + "-" + value);
		stringRedisTemplate.opsForValue().set( key, objStr, expireTime, TimeUnit.HOURS);
	}
	
	/**   
	 * @Title: delete   
	 * @Description: 根据key,删除缓存  
	 * @param: @param key      
	 * @return: void        
	 */  
	public void delete( String key ) {
		logger.debug( "根据key,删除缓存>>>>>>>>key" + key );
		stringRedisTemplate.delete( key );
    }
	
}
