package cn.zg.intercepter;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	/**   
	 * <p>Title: afterCompletion</p>   
	 * <p>Description: </p>   
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception   
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion  
	 */ 
	@Override
	public void afterCompletion( HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
		
	}

	/**   
	 * <p>Title: postHandle</p>   
	 * <p>Description: </p>   
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception   
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle  
	 */ 
	@Override
	public void postHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	/**   
	 * <p>Title: preHandle</p>   
	 * <p>Description: </p>   
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception   
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle
	 */ 
	@Override
	public boolean preHandle( HttpServletRequest request, 
			HttpServletResponse response, Object handler )
			throws Exception {
		
		/*
		 * 不需要验证的地址过滤（暂时多余，在LoginConfig已经配置不需要验证的）
		 */
//       String path = request.getRequestURI();  
//		if( isValidataUrl( path ) )  return true;
		
		/*
		 * 登录验证
		 
		String path = request.getRequestURI();  
		System.out.println( "path:" + path );
		HttpSession session = request.getSession();
		System.out.println("session:" + session.getAttribute( "SESSION_KEY" ));
		if( session.getAttribute( "SESSION_KEY" ) == null) {
				response.sendRedirect(  "/html/login.html" );
			return false; 
		} else {
			if( "/".equals( path.trim() ) ) {
				response.sendRedirect(  "/html/index.html" );
				return false; 
			}else if( "".equals( path.trim() ) ) {
				response.sendRedirect(  "/html/index.html" );
				return false;
			}
		}
		*/
		return true;		
	}
	
	
	/**   
	 * @Title: isValidataUrl   
	 * @Description: 判断是否需要进行登录验证  (暂时多余的，在LoginConfig已经配置不需要验证的)
	 * @param: @return      
	 * @return: boolean        
	 */  
	public boolean isValidataUrl( String path) {
		/*
		 * 不需要进行的验证的url
		 */
        Set<String> notLoginPaths = new HashSet<>();  
        //此处添加不需要登录验证的路径
        notLoginPaths.add( "login.html" );
        
        if( notLoginPaths.contains( path ) ) return true;
		return false;
	}
	
}
