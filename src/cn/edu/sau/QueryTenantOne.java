package cn.edu.sau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.sau.key.KeyValue;
import cn.edu.sau.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class QueryTenantOne
 */
@WebServlet("/QueryTenantOne")
public class QueryTenantOne extends HttpServlet {
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
        result = jsonObject.getString("id");
        UserServiceImpl impl = new UserServiceImpl();
        ResultSet resultSet = impl.queryTenantOne(result);
        
        JSONObject jsonObjec = new JSONObject();
        try {
			if (resultSet.next()) {
				jsonObjec.put(KeyValue.TNAME, resultSet.getString("name"));
				jsonObjec.put(KeyValue.TSEX, resultSet.getString("sex"));
				jsonObjec.put(KeyValue.TAGE, String.valueOf(resultSet.getInt("age")));
				jsonObjec.put(KeyValue.TIDCard, resultSet.getString("id_number"));
				jsonObjec.put(KeyValue.TMINRENT, String.valueOf(resultSet.getDouble("min_rent")));
				jsonObjec.put(KeyValue.TMAXRENT, String.valueOf(resultSet.getDouble("max_rent")));
				jsonObjec.put(KeyValue.TAPART, resultSet.getString("apart"));
				jsonObjec.put(KeyValue.TMINArea, String.valueOf(resultSet.getDouble("min_area")));
				jsonObjec.put(KeyValue.TMAXArea, String.valueOf(resultSet.getDouble("max_area")));
				jsonObjec.put(KeyValue.TRQuarters, resultSet.getString("residential_quarters"));
				jsonObjec.put(KeyValue.TRArea, resultSet.getString("respective_area"));
				jsonObjec.put(KeyValue.TPNUM, String.valueOf(resultSet.getInt("p_number")));
				jsonObjec.put(KeyValue.TADDR, resultSet.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        out.write(jsonObjec.toString());
        out.flush();
        out.close();
	}

}
