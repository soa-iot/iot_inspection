package cn.zg.utils.globalUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalUtil {
	/**
	 * 工具类方法说明：
	 * 	1、运行时异常生成方法
	 */
	
	/**   
	 * @Title: throwRuntimeException   
	 * @Description: 生成运行时异常信息   
	 * @param: @param exceptionMessage      
	 * @return: void        
	 */  
	public static void throwRuntimeException(String exceptionMessage) {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		StackTraceElement fatherElement = stackTraceElements[2];
		String message = "异常信息：" +
				fatherElement.getClassName() + "-" + fatherElement.getLineNumber() + "行" + 
				fatherElement.getMethodName() + "-" + exceptionMessage ; 
		throw new RuntimeException(message);
	}
	
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
	public static Map<String, String> changeEntityIntoMap(Object object){		
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
	
	
	
}
