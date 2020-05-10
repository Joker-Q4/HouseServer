package cn.edu.sau;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.sau.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class QueryHouseStatistics
 */
@WebServlet("/QueryHouseStatistics")
public class QueryHouseStatistics extends HttpServlet {
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
        ArrayList<String> listArea = new ArrayList<>();
        ArrayList<String> listRent = new ArrayList<>();
        ArrayList<String> listRQuarters = new ArrayList<>();
        UserServiceImpl impl = new UserServiceImpl();
        JSONObject jsonObject = new JSONObject();
        
        PrintWriter out = response.getWriter();
        // 采用固定的格式统计，包括： 户型面积、价格、小区、区域、是否已租
        // 1.房屋面积统计
        // 将租金信息填入 ArrayList
        listArea.add(String.valueOf(impl.queryHouseAreaStatistics(0, 50)));
        listArea.add(String.valueOf(impl.queryHouseAreaStatistics(51, 100)));
        listArea.add(String.valueOf(impl.queryHouseAreaStatistics(101, 150)));
        listArea.add(String.valueOf(impl.queryHouseAreaStatistics(151, 200)));
        listArea.add(String.valueOf(impl.queryHouseAreaStatistics(201, 1000)));
        // 将 ArrayList 转换成 String
        String LArea = String.join(",",  listArea.toArray(new String[listArea.size()]));
        // 将信息放入 JSONObject 中
        jsonObject.put("LArea", LArea);
        
        // 2.房屋租金统计
        // 将租金信息填入 ArrayList
        listRent.add(String.valueOf(impl.queryHouseRentStatistics(0, 1000)));
        listRent.add(String.valueOf(impl.queryHouseRentStatistics(1001, 1500)));
        listRent.add(String.valueOf(impl.queryHouseRentStatistics(1501, 2000)));
        listRent.add(String.valueOf(impl.queryHouseRentStatistics(2001, 3000)));
        listRent.add(String.valueOf(impl.queryHouseRentStatistics(3001, 10000)));
        // 将 ArrayList 转换成 String
        String LRent = String.join(",",  listRent.toArray(new String[listRent.size()]));
        // 将信息放入 JSONObject 中
        jsonObject.put("LRent", LRent);
        
        // 3.小区信息进行统计 
        // （1）遍历小区信息
        listRQuarters = impl.queryHouseRQuartersStatistics();
        // （2）通过遍历的结果，查询每个小区的房源信息
        for(int i = 0; i < listRQuarters.size(); i++) {
        	String a = String.valueOf(impl.queryHouseRQuartersStatistics(listRQuarters.get(i)));
        	jsonObject.put(listRQuarters.get(i), a);
        }
        
        // 4.所在区域房源信息统计
        // 和平区、沈河区、大东区、皇姑区、铁西区、苏家屯区、浑南区、沈北新区、于洪区、辽中区
        jsonObject.put("和平区", impl.queryHouseRAreaStatistics("和平区"));
        jsonObject.put("沈河区", impl.queryHouseRAreaStatistics("沈河区"));
        jsonObject.put("大东区", impl.queryHouseRAreaStatistics("大东区"));
        jsonObject.put("皇姑区", impl.queryHouseRAreaStatistics("皇姑区"));
        jsonObject.put("铁西区", impl.queryHouseRAreaStatistics("铁西区"));
        jsonObject.put("苏家屯区", impl.queryHouseRAreaStatistics("苏家屯区"));
        jsonObject.put("浑南区", impl.queryHouseRAreaStatistics("浑南区"));
        jsonObject.put("沈北新区", impl.queryHouseRAreaStatistics("沈北新区"));
        jsonObject.put("于洪区", impl.queryHouseRAreaStatistics("于洪区"));
        jsonObject.put("辽中区", impl.queryHouseRAreaStatistics("辽中区"));
        
        // 5.对是否已租进行统计
        // (1) 查询未租的房子数量
        String staterfalse = String.valueOf(impl.queryHouseStaterStatistics("false"));
        jsonObject.put("staterfalse", staterfalse);
        // (2) 查询已租的房子数量
        String statertrue = String.valueOf(impl.queryHouseStaterStatistics("true"));
        jsonObject.put("statertrue", statertrue);
        
        System.out.println(jsonObject.toString());
        out.write(jsonObject.toString());
        out.flush();
        out.close();
	}
}
