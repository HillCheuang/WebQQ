package com.yc.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
/**
 * 基本的Servlet  封装
 * @author 衡阳吴彦祖
 *
 */
public class BaseServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 设置编码集
	 * @param request   请求对象
	 * @param response	响应对象
	 * @throws UnsupportedEncodingException
	 */
	public void setCharacterEncoding(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	}
	
	/**
	 * 将数据转为json格式字符串输出
	 * @param response
	 * @param obj
	 * @throws IOException
	 */
	public void toPrintJson(HttpServletResponse response,Object obj) throws IOException{
		Gson gson=new Gson();
		String str=gson.toJson(obj);
		PrintWriter out=response.getWriter();
		out.print(str);
	}
	
	public <T> T parseRequest(HttpServletRequest request,Class<T> cls) throws Exception{
		T t=null;
		//反射实例获取类的所有属性
		Field [] fields=cls.getDeclaredFields();
		//获取所有的方法
		Method [] methods=cls.getDeclaredMethods();
		//创建对象
		t=cls.newInstance();//调用无参数的构造函数
		//循环所有的属性
		for(Field f:fields){
			String fname=f.getName();
			if("serialVersionUID".equals(fname)){
				continue;
			}
			//根据字段名获取页面传参数
			String value=request.getParameter(fname);
			if(null==value || "".equals(value)){
				continue;//界面为传入该值   直接下个属性的循环
			}
//			System.out.println(value);
			//调用set方法赋值
			for(Method m:methods){
				//set方法
				if(("set"+fname).equalsIgnoreCase(m.getName())){
					//获取set方法形参类型     形参类型的全路径名称
					String typeName=m.getParameterTypes()[0].getName();
//					System.out.println(typeName);
					if("java.lang.Integer".equals(typeName)){
						m.invoke(t, Integer.parseInt(value));
					}else if("java.lang.Double".equals(typeName)){
						m.invoke(t, Double.parseDouble(value));
					}else if("java.lang.Float".equals(typeName)){
						m.invoke(t, Float.parseFloat(value));
					}else if("java.lang.Long".equals(typeName)){
						m.invoke(t, Long.parseLong(value));
					}else if("java.lang.String".equals(typeName)){
						m.invoke(t, value);
					}else{//未来扩展的其他数据类型
						//未处理
					}
				}
			}
		}
		return t;
	}
}
