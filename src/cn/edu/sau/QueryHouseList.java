package cn.edu.sau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.sau.key.KeyValue;
import cn.edu.sau.pojo.VO.HouseVO;
import cn.edu.sau.service.impl.UserServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class QueryHouseList
 */
@WebServlet("/QueryHouseList")
public class QueryHouseList extends HttpServlet {
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
        // response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        List<HouseVO> list = new ArrayList<HouseVO>();
        // 获取客户端数据
        BufferedReader in = new BufferedReader(new InputStreamReader(
                request.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        String result = "";
        JSONObject jsonObject = null;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        result = sb.toString();
        System.out.println("接收到数据：" + result);
        jsonObject = JSONObject.fromObject(result);
		
        UserServiceImpl impl = new UserServiceImpl();
        ResultSet resultSet = impl.queryHouseList(jsonObject);
        
        try {
			while (resultSet.next()) {
				// 将信息存入实体化类中
				HouseVO house = new HouseVO();
				
				house.setId(resultSet.getInt("id"));
				house.setRent(resultSet.getDouble(KeyValue.RENT));
				house.setApartment(resultSet.getString(KeyValue.APART));
				house.setImage1(resultSet.getString(KeyValue.IMAGE1));
				house.setImage2(resultSet.getString(KeyValue.IMAGE2));
				house.setImage3(resultSet.getString(KeyValue.IMAGE3));
				
			    // 将类加入列表
			    list.add(house);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        JSONArray jsonList=JSONArray.fromObject(list);
        
        out.write(jsonList.toString());
        out.flush();
        out.close();
        System.out.println(jsonList);
	}
}
