package com.yc.util;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author liyan
 *
 */
public class FileUploadUtil {
	private static final String IMAGEPATH = "../fresh_images/";
	private static final String CHARSET = "UTF-8";

	/**
	 * @param request
	 * @param cls
	 * @return
	 * @throws Exception 
	 */
	public  static <T> T parseRequest(HttpServletRequest request,Class<T> cls) throws Exception{
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(factory);
		//请求解析对象
		List<FileItem> items=upload.parseRequest(request);
		//创建T对象
		T t=cls.newInstance();
		Method []methods=cls.getDeclaredMethods();
		System.out.println(items.size()+"-------");
		//循环文件项
		for(FileItem item:items){
			if(item.isFormField()){
				//获取表单元素的name值
				String name=item.getFieldName();
				//获取表单元素的value值
				String value=item.getString(CHARSET);
				for(Method m:methods){
					if(("set"+name).equalsIgnoreCase(m.getName())){
						String typeName=m.getParameterTypes()[0].getName();
						System.out.println(typeName);
						if("java.lang.Integer".equals(typeName)){
							m.invoke(t, Integer.parseInt(value));
						}
						else if("java.lang.Double".equals(typeName)){
							m.invoke(t, Double.parseDouble(value));
						}
						else if("java.lang.Float".equals(typeName)){
							m.invoke(t, Float.parseFloat(value));
						}
						else if("java.lang.String".equals(typeName)){
							m.invoke(t, value);
						}
						else if("java.lang.Long".equals(typeName)){
							m.invoke(t, Long.parseLong(value));
						}else {
							//后期拓展
						}
						break;
				}
			}
				System.out.println(name+"="+value);
		}else {
			String fieldName=item.getFieldName();
			//获取文件名称
			String name=item.getName();
			//文件存在服务器的那个位置
			String path =request.getServletContext().getRealPath("/");
			System.out.println("文件存在服务器的位置"+path);
			//文件重名问题
			UUID uuid=UUID.randomUUID();
			String fileName=uuid.toString()+""+name;
			//创建文件对象
			File file=new File(path,IMAGEPATH+fileName);
			//将文件对象写入 到磁盘中
			item.write(file);
			//获取储存后的文件路径 如何处理 存储image_path
			String image_path=IMAGEPATH+fileName;
			System.out.println("图片路径"+image_path);
			for(Method m:methods){
				if(("set"+fieldName).equalsIgnoreCase(m.getName())){
					m.invoke(t, image_path);
					}
				}
			}
			
			
		}
		return t;
		
	}
}
