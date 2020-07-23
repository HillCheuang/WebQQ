package com.yc.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.yc.bean.QQInfo;
/**
 * 访问权限过滤
 * @author 衡阳吴彦祖
 *
 */
@WebFilter(filterName="loginFilter",urlPatterns={"/back/manager/*"})
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
/*		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		HttpSession session=request.getSession();
		QQInfo admin=(QQInfo)session.getAttribute("mmber");
		if(admin==null){
			//根据项目名   路径/    服务器的根目录(webApps)
			response.sendRedirect("/fresh/back/backLogin.html");
		}else{
			chain.doFilter(request, response);
		}*/
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
