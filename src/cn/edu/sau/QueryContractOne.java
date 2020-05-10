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
 * Servlet implementation class QueryContractOne
 */
@WebServlet("/QueryContractOne")
public class QueryContractOne extends HttpServlet {
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
        ResultSet resultSet = impl.queryContractOne(result);
        
        JSONObject jsonObjec = new JSONObject();
        try {
			if (resultSet.next()) {
				jsonObjec.put(KeyValue.CN, String.valueOf(resultSet.getInt(KeyValue.CN)));
				jsonObjec.put(KeyValue.HN, String.valueOf(resultSet.getInt(KeyValue.HN)));
				jsonObjec.put(KeyValue.HA, resultSet.getString(KeyValue.HA));
				jsonObjec.put(KeyValue.ON, resultSet.getString(KeyValue.ON));
				jsonObjec.put(KeyValue.OP, String.valueOf(resultSet.getInt(KeyValue.OP)));
				jsonObjec.put(KeyValue.OI, resultSet.getString(KeyValue.OI));
				jsonObjec.put(KeyValue.RNA, resultSet.getString(KeyValue.RNA));
				jsonObjec.put(KeyValue.RNU, String.valueOf(resultSet.getInt(KeyValue.RNU)));
				jsonObjec.put(KeyValue.RI, resultSet.getString(KeyValue.RI));
				jsonObjec.put(KeyValue.R, String.valueOf(resultSet.getInt(KeyValue.R)));
				jsonObjec.put(KeyValue.LT, String.valueOf(resultSet.getInt(KeyValue.LT)));
				jsonObjec.put(KeyValue.A, resultSet.getString(KeyValue.A));
				jsonObjec.put(KeyValue.AF, String.valueOf(resultSet.getInt(KeyValue.AF)));
				jsonObjec.put(KeyValue.CD, resultSet.getDate(KeyValue.CD).toString());
				jsonObjec.put(KeyValue.CP, resultSet.getString(KeyValue.CP));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        out.write(jsonObjec.toString());
        out.flush();
        out.close();
	}

}
