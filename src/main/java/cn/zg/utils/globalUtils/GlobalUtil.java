package cn.zg.utils.globalUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class GlobalUtil {
	/**
	 * 工具类方法说明：
	 * 	1、异常相关
	 * 		1.1 运行时异常生成方法
	 *  2、字符串相关
	 *  	2.1
	 *  3、集合类
	 *  	3.1 控制台显示map的key和value
	 *  
	 *  
	 *  
	 *  5、cookie相关
	 *  	5.1 cookie获取
	 *  	5.2 cookie获取重载
	 *  	5.3 cookie新增
	 *  	5.4 cookie删除
	 *  	5.5 cookie修改
	 *  	5.6 cookie展示
	 *  
	 *  6、序列化工具
	 *  	6.1  对象序列化
	 *  	6.2  对象反序列化
	 *  	6.3 list序列化
	 *  	6.4 list反序列化
	 *  
	 *  7、对象转json字符串
	 *  	
	 */
	
	
	///////////////////////////////////////////////////////////////////
	//    1、异常相关
	///////////////////////////////////////////////////////////////////
	/**   
	 * @Title: throwRuntimeException   
	 * @Description: 生成运行时异常信息 (包括父类类名、行数……)
	 * @param: @param exceptionMessage      
	 * @return: void        
	 */  
	public static void throwRuntimeException( String exceptionMessage ) {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		StackTraceElement fatherElement = stackTraceElements[2];
		String message = "异常信息：" +
				fatherElement.getClassName() + "-" + fatherElement.getLineNumber() + "行" + 
				fatherElement.getMethodName() + "-" + exceptionMessage ; 
		throw new RuntimeException( message);
	}
	
	
	///////////////////////////////////////////////////////////////////
	//    2、字符串相关
	///////////////////////////////////////////////////////////////////
	/**   
	 * @Title: splitStrByTwoSymbol   
	 * @Description: 一个字符串转化为一个hashmap  
	 * @param: @param originString 传入字符串
	 * @param: @param firstSymbol  第一个分隔符
	 * @param: @param secondSymbol 第二个分隔符
	 * @param: @return       
	 * @return: Map<String,String>        
	 */  
	public static Map<String, String> splitStrByTwoSymbol(
			String originString, 
			String firstSymbol, 
			String secondSymbol ){
		Map<String, String> returnMap = new HashMap<String, String>();
		String[] tempArray = originString.split(firstSymbol);
		for(String s : tempArray) {
			String[] tempArraySon = s.split(secondSymbol);
			if(tempArraySon[0] != null && !tempArraySon[0].isEmpty()) {
				if(tempArraySon.length > 1) {
					returnMap.put(tempArraySon[0], tempArraySon[1]);
				}else {
					returnMap.put(tempArraySon[0], null);
				}
			}
			
		}
		return returnMap;
	}
	
	/**   
	 * @Title: changeEntityIntoMap   
	 * @Description: 实体类转化为map  
	 * @param: @param object 实体类
	 * @param: @return      
	 * @return: Map<String,String>        
	 */  
	public static Map<String, String> changeEntityIntoMap( Object object ){		
		Map<String, String> map = new HashMap<String, String>();
		try {   
			BeanInfo  beanInfo= Introspector.getBeanInfo(object.getClass());    
			PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();   
			for (int i = 0; i < descriptors.length; i++) {   
				PropertyDescriptor descriptor = descriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(object, new Object[0]);
					if(result != null) {
						map.put(propertyName, result.toString());
					}else{
						map.put(propertyName, "");
					}
				}  
			}   
		} catch (Exception e) {   
		    e.printStackTrace(); 
		    return null;
		}   
		return map;
	} 
	
	/**   
	 * @Title: changePOJOIntoMap   
	 * @Description: POJO对象转化为maop   
	 * @param: @param obj
	 * @param: @return      
	 * @return: Map<String,Object>        
	 */  
	public static Map<String, Object> changePOJOIntoMap(Object obj){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Class cls = (Class)obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			try {
                Object val = field.get(obj);
                returnMap.put(field.getName(), val);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
		} 
		return returnMap;
	}
	
	/**   
	 * @Title: getPOJOfields   
	 * @Description: 获取POJO的所有属性  
	 * @param: @param obj
	 * @param: @return      
	 * @return: List<Object>        
	 */  
	public static List<Object> getPOJOfields(Object obj){
		List<Object> returnList = new ArrayList<Object>();		
		try {
			Class cls = (Class)obj.getClass();
			Field[] fields = cls.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				try {
					Field f = fields[i];
					f.setAccessible(true);
					returnList.add(f.getName());
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
		} catch (Exception e) {
			e.printStackTrace();
			return returnList;
		}
		return returnList;
	}
	
	/**   
	 * @Title: getPOJOFieldTypeByName   
	 * @Description: 获取POJO对象属性的类型  
	 * @param: @param obj
	 * @param: @param propertyName
	 * @param: @return      
	 * @return: String        
	 */  
	public static String getPOJOFieldTypeByName(Object obj, String propertyName) {
		try {
			Class c = obj.getClass();			
			Field field = c.getDeclaredField(propertyName);
			field.setAccessible(true);
			String type = field.getType().toString();
			if(type.endsWith("String")) {
				return "String";
			}else if(type.endsWith("Date")) {
				return "Date";
			}else if(type.endsWith("int")) {
				return "int";
			}else if(type.endsWith("Integer")) {
				return "Integer";
			}else if(type.endsWith("Long")) {
				return "Long";
			}else if(type.endsWith("Double")) {
				return "Double";
			}
			//未完待续
		} catch (Exception e) {
			e.printStackTrace();
			return " ";
		}				
		return " ";
	}
	
	/**   
	 * @Title: setPOJOValue   
	 * @Description: 设置对象的私有属性属性值  
	 * @param: @param obj
	 * @param: @param propertyName
	 * @param: @param value      
	 * @return: void        
	 */  
	public static void setPOJOPrivateValue(Object obj, String propertyName, Object value) {	
		//传入参数检查
		
		try {
			Class c = obj.getClass();
			Field field = c.getDeclaredField(propertyName);
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}							
	}
	
	/**   
	 * @Title: setPOJOValue   
	 * @Description: 设置对象的非私有属性属性值    
	 * @param: @param obj
	 * @param: @param propertyName
	 * @param: @param value      
	 * @return: void        
	 */  
	public static void setPOJOValue(Object obj, String propertyName, Object value) {
		//传入参数检查
		
		try {
			Class c = obj.getClass();
			Field field = c.getField(propertyName);
			field.set(propertyName, value);
		} catch (Exception e) {
			e.printStackTrace();
		}						
	}
	
	
	///////////////////////////////////////////////////////////////////
	//    3、集合类相关
	///////////////////////////////////////////////////////////////////
	public static void showMapKeyValue( Map<String,Object> map ) {
		for( Map.Entry<String,Object> entry : map.entrySet() ) {
			String key = entry.getKey();
			String value = entry.getValue().toString();
			System.out.println( "key-value:" + key + "-" + value );
		}
	}
	
	
	///////////////////////////////////////////////////////////////////
	//    cookie操作
	///////////////////////////////////////////////////////////////////
	/**   
	 * @Title: getCookie   
	 * @Description: 获取cookie
	 * @param: @param cookieName
	 * @param: @return      
	 * @return: String        
	 */  
	public static String getCookie( String cookieName ){
		/*
		 * 获取request
		 */
		ServletRequestAttributes attributes = 
				(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		/*
		 * 获取cookie
		 */
		try {
			Cookie[] cookies = request.getCookies();
			for ( int i = 0; i < (cookies == null ? 0 : cookies.length); i++) {
				if ( (cookieName).equalsIgnoreCase( cookies[i].getName() ) ) {
					return URLDecoder.decode( cookies[i].getValue(), "UTF-8" );
				}
			}
		} catch (Exception e) {
			System.out.println(" --------获取String cookie 失败--------   " + e.getMessage());
		}
		return null;
	}
	
	/**   
	 * @Title: getCookie   
	 * @Description: 获取cookie重载  
	 * @param: @param cookieName
	 * @param: @param clazz
	 * @param: @return      
	 * @return: T        
	 */  
	public static <T>T getCookie( String cookieName, Class<T> clazz ) {
		/*
		 * 获取request
		 */
		ServletRequestAttributes attributes = 
				(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		/*
		 * 获取cookie
		 */
		try {
			Cookie[] cookies = request.getCookies();
			String v = null;
			for ( int i = 0; i < (cookies == null ? 0 : cookies.length); i++ ) {
				if ( ( cookieName ).equalsIgnoreCase( cookies[i].getName() ) ) {
					v = URLDecoder.decode( cookies[i].getValue(), "UTF-8" );
				}
			}
			if (v != null) {
				return new Gson().fromJson( v, clazz);
			}
		} catch (Exception e) {
			System.out.println("------获取 clazz Cookie 失败----- " + e.getMessage());
		}
		return null;
	}
	
	/**   
	 * @Title: addCookie   
	 * @Description: 新增cookie   
	 * @param: @param cookieName
	 * @param: @param object      
	 * @return: void        
	 */  
	public static void addCookie( String cookieName, Object object) {
		/*
		 * 获取response
		 */
		ServletRequestAttributes attributes = 
				( ServletRequestAttributes ) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = attributes.getResponse();
		HttpServletRequest request = attributes.getRequest();
		
		/*
		 * 新增cookie
		 */
		try {
			String v = URLEncoder.encode( new Gson().toJson( object ), "UTF-8");
			Cookie cookie = new Cookie( cookieName, v );
			cookie.setPath( "/" );
			cookie.setMaxAge( Integer.MAX_VALUE );// 设置保存cookie最大时长
			response.addCookie( cookie );
		} catch (Exception e) {
			System.out.println(" -------添加cookie 失败！--------" + e.getMessage());
		}
	}
	
	/**   
	 * @Title: removeCookie   
	 * @Description: 删除cookie  
	 * @param: @param cookieName      
	 * @return: void        
	 */  
	public static void removeCookie( String cookieName ) {
		/*
		 * 获取request、response
		 */
		ServletRequestAttributes attributes = 
				( ServletRequestAttributes ) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = attributes.getResponse();
		HttpServletRequest request = attributes.getRequest();
		
		/*
		 * 删除cookie
		 */
		try {
			Cookie[] cookies = request.getCookies();
			for ( int i = 0; i < ( cookies == null ? 0 : cookies.length ); i++ ) {
				if ( ( cookieName ).equalsIgnoreCase( cookies[i].getName() ) ) {

					Cookie cookie = new Cookie( cookieName, "");
					cookie.setPath( "/" );
					cookie.setMaxAge( 0 );// 设置保存cookie最大时长为0，即使其失效
					response.addCookie( cookie );
				}
			}
		} catch (Exception e) {
			System.out.println(" -------删除cookie失败！--------" + e.getMessage());
		}
	}
	
	/**   
	 * @Title: updateCookie   
	 * @Description: 修改cookie值  
	 * @param: @param cookieName
	 * @param: @param cookieValue      
	 * @return: void        
	 */  
	public static void updateCookie( String cookieName, String cookieValue ) {
		/*
		 * 获取request、response
		 */
		ServletRequestAttributes attributes = 
				( ServletRequestAttributes ) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = attributes.getResponse();
		
		/*
		 * 修改cookie值
		 */
		Cookie cookie = new Cookie( cookieName, cookieValue );
		response.addCookie( cookie );	
	}
	
	/**   
	 * @Title: showCookie   
	 * @Description: 展示cookie  
	 * @param:       
	 * @return: void        
	 */  
	public static void showCookie() {
		/*
		 * 获取request、response
		 */
		ServletRequestAttributes attributes = 
				( ServletRequestAttributes ) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		/*
		 * 展示cookie
		 */
		System.out.println(  request.getCookies() );
		Cookie[] cookies = request.getCookies();
		for( int i=0; i<(cookies == null?0:cookies.length); i++ ) {
			System.out.println(  cookies[i].getName() + "=" + cookies[i].getValue() );
		}	
//		for( Cookie c : cookies ) {
//			System.out.println(  c.getName() + "=" + c.getValue() );
//		}
	}
	
	
	///////////////////////////////////////////////////////////////////
	//   6、序列化
	///////////////////////////////////////////////////////////////////
	/**   
	 * @Title: serialize   
	 * @Description:   对象序列化 
	 * @param: @param object
	 * @param: @return      
	 * @return: byte[]        
	 */  
	public static byte[] serialize( Object object) {
		if ( object == null ) {  
            return null;  
        }  
		ObjectOutputStream oos = null;
		ByteArrayOutputStream bos = null;
		byte[] bytes = null;
		try {
			oos = new ObjectOutputStream( bos );
			bos = new ByteArrayOutputStream();
			oos.writeObject( object );
			bytes = bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 close( oos );  
	         close( bos ); 
		}
		return bytes;
	}
	
	/**   
	 * @Title: deSerialize   
	 * @Description: 对象反序列化   
	 * @param: @param bytes
	 * @param: @return      
	 * @return: Object        
	 */  
	public static Object deSerialize( byte[] bytes ) {
		if ( bytes.length <= 0 ) {  
            return null;  
        }  
		ObjectInputStream ois = null;
		ByteArrayInputStream bis = null;
		try {
			ois = new ObjectInputStream( bis );
			bis = new ByteArrayInputStream( bytes );
			Object object = ois.readObject();
			return object;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 close( ois );  
	         close( bis ); 
		}
		return null;
	}
	
	/**   
	 * @Title: close   
	 * @Description: 关闭输入输出流 
	 * @param: @param closeable      
	 * @return: void        
	 */  
	public static void close( Closeable closeable ) {  
        if (closeable != null) {  
            try {  
                closeable.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
	
	///////////////////////////////////////////////////////////////////
	//   7、对象 <--> json字符串
	///////////////////////////////////////////////////////////////////
	/**   
	 * @Title: convertObjToJson   
	 * @Description: 对象转json字符串  
	 * @param: @param object
	 * @param: @return      
	 * @return: String        
	 */  
	public static String convertObjToJsonstr( Object o ) {
		if( o == null ) {
			return null;
		}
		Gson g = new Gson();
		String s = g.toJson( o );		
		return s;
	}
	
	/**   
	 * @Title: convertObj2String   
	 * @Description:  对象转json字符串  
	 * @param: @param o
	 * @param: @return      
	 * @return: String        
	 */  
	public static String convertObj2String( Object o ) {
		if( o == null ) {
			return null;
		}
        String s = null;
        try {
            s = new ObjectMapper().writeValueAsString( o );
        } catch ( JsonProcessingException e ) {
            e.printStackTrace();
        }
        return s;
    }
	
	/**   
	 * @Title: convertJsonstrToObj   
	 * @Description: json字符串  转  对象 
	 * @param: @param s
	 * @param: @param c
	 * @param: @return      
	 * @return: Object        
	 */  
	public static Object convertJsonstrToObj( String s, Class<?> c ) {
		if( s == null || s.isEmpty() ) {
			return null;
		}
		Gson g = new Gson();
		return g.fromJson( s, c );
	}
	
	/**   
	 * @Title: convertString2Obj   
	 * @Description: json字符串  转  对象   
	 * @param: @param s
	 * @param: @param c
	 * @param: @return      
	 * @return: T        
	 */  
	public static <T> T convertString2Obj( String s, Class<T> c ) {
		if( s == null || s.isEmpty() ) {
			return null;
		}
        T t = null;
        try {
            t = new ObjectMapper().readValue( s, c );
        } catch ( IOException e) {
            e.printStackTrace();
        }
        return t;
    }
	
	
	
	///////////////////////////////////////////////////////////////////
	//   
	///////////////////////////////////////////////////////////////////
}
