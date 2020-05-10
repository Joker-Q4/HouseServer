package cn.edu.sau;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.edu.sau.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class QueryContractStatistics
 */
@WebServlet("/QueryContractStatistics")
public class QueryContractStatistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 解决中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        ArrayList<String> listCAgent = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFor = new SimpleDateFormat("yyyy-MM");
        int maxCurrentMonthDay=0;
        String date = "";
        Date date1 = new Date();
        Date date2 = new Date();
        UserServiceImpl impl = new UserServiceImpl();
        JSONObject jsonObject = new JSONObject();
        java.sql.Date a,b;
        Calendar calendar = Calendar.getInstance();
        
        // 1.按时间统计合同信息
        try {
			// 查询当前时间
			calendar.setTime(date1); // 设置为当前时间
			date1 = calendar.getTime(); // 获取当前时间
			date1 = dateFormat.parse(dateFormat.format(date1)); // 去除时分秒 CST
			// 查询当前月1号
			calendar.set(Calendar.DAY_OF_MONTH, 1); // 设置为当月1号
			date2 = calendar.getTime();
			date2 = dateFormat.parse(dateFormat.format(date2)); // 去除时分秒 CST
			date = dateFor.format(date2); // 转换格式
			// 查询数据
			a = new java.sql.Date(date1.getTime());
			b = new java.sql.Date(date2.getTime());
			String aa = String.valueOf(impl.queryContractDateStatistics(b, a));
			jsonObject.put(date, aa);
			for (int i = 0; i < 6; i++) {
				calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
				maxCurrentMonthDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 获取最大天数
				calendar.set(Calendar.DAY_OF_MONTH, maxCurrentMonthDay); // 设置最后一天
				date1 = calendar.getTime(); // 获取当前时间
				date1 = dateFormat.parse(dateFormat.format(date1)); // 去除时分秒 CST
				// 查询当前月1号
				calendar.set(Calendar.DAY_OF_MONTH, 1); // 设置为当月1号
				date2 = calendar.getTime();
				date2 = dateFormat.parse(dateFormat.format(date2)); // 去除时分秒 CST
				date = dateFor.format(date2); // 转换格式
				// 查询数据
				a = new java.sql.Date(date1.getTime());
				b = new java.sql.Date(date2.getTime());
				String bb = String.valueOf(impl.queryContractDateStatistics(b, a));
				jsonObject.put(date, bb);
			} 
		} catch (Exception e) {}
        
		// 2.按中介人统计信息
        // (1) 遍历中介人信息
        listCAgent = impl.queryContractAgentStatistics();
        // (2) 通过中介人统计信息
        for(int i = 0; i < listCAgent.size(); i++) {
        	String cc = String.valueOf(impl.queryContractAgentStatistics(listCAgent.get(i)));
        	jsonObject.put(listCAgent.get(i), cc);
        }
        
        System.out.println(jsonObject.toString());
        out.write(jsonObject.toString());
        out.flush();
        out.close();
	}

}
