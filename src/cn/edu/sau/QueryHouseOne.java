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
 * Servlet implementation class QueryHouseOne
 */
@WebServlet("/QueryHouseOne")
public class QueryHouseOne extends HttpServlet {
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
        ResultSet resultSet = impl.queryHouseOne(result);
        
        JSONObject jsonObjec = new JSONObject();
        try {
			if (resultSet.next()) {
				jsonObjec.put(KeyValue.NAME, resultSet.getString(KeyValue.NAME));
				jsonObjec.put(KeyValue.PHONE, String.valueOf(resultSet.getInt(KeyValue.PHONE)));
				jsonObjec.put(KeyValue.RENT, String.valueOf(resultSet.getDouble(KeyValue.RENT)));
				jsonObjec.put(KeyValue.APART, resultSet.getString(KeyValue.APART));
				jsonObjec.put(KeyValue.AREA, String.valueOf(resultSet.getDouble(KeyValue.AREA)));
				jsonObjec.put(KeyValue.FLOOR, String.valueOf(resultSet.getInt(KeyValue.FLOOR)));
				jsonObjec.put(KeyValue.ORIEN, resultSet.getString(KeyValue.ORIEN));
				jsonObjec.put(KeyValue.COND, resultSet.getString(KeyValue.COND));
				jsonObjec.put(KeyValue.RES_Q, resultSet.getString(KeyValue.RES_Q));
				jsonObjec.put(KeyValue.RES_A, resultSet.getString(KeyValue.RES_A));
				jsonObjec.put(KeyValue.ADDR, resultSet.getString(KeyValue.ADDR));
				jsonObjec.put(KeyValue.PRO_R, String.valueOf(resultSet.getInt(KeyValue.PRO_R)));
				jsonObjec.put(KeyValue.DESC, resultSet.getString(KeyValue.DESC));
				jsonObjec.put(KeyValue.IMAGE1, resultSet.getString(KeyValue.IMAGE1));
				jsonObjec.put(KeyValue.IMAGE2, resultSet.getString(KeyValue.IMAGE2));
				jsonObjec.put(KeyValue.IMAGE3, resultSet.getString(KeyValue.IMAGE3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        out.write(jsonObjec.toString());
        out.flush();
        out.close();
	}
}
