package cn.edu.sau;

import java.io.IOException;
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

import cn.edu.sau.pojo.Worker;
import cn.edu.sau.service.impl.UserServiceImpl;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class QueryWorkerList
 */
@WebServlet("/QueryWorkerList")
public class QueryWorkerList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 解决中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        List<Worker> list = new ArrayList<Worker>();
        
        UserServiceImpl impl = new UserServiceImpl();
        ResultSet resultSet = impl.queryWorkerList();
        
        try {
			while (resultSet.next()) {
				// 将信息存入实体化类中
				Worker worker = new Worker();
				worker.setName(resultSet.getString("workername"));
				worker.setPassword(resultSet.getString("password"));
			    // 将类加入列表
			    list.add(worker);
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
