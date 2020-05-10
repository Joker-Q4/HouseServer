package cn.edu.sau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.sau.key.KeyValue;
import cn.edu.sau.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class AddWorkerToList
 */
@WebServlet("/AddWorkerToList")
public class AddWorkerToList extends HttpServlet {
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
		// �����������
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        // ��ȡ�ͻ�������
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
        jsonObject = JSONObject.fromObject(result);
        String username = jsonObject.getString(KeyValue.USER);
        String password = jsonObject.getString(KeyValue.PWD);
        System.out.println("�û�����" + username);
        System.out.println("������" + password);
        UserServiceImpl impl = new UserServiceImpl();
        boolean IsExit = impl.addWorkerToList(username, password);
        //��ʼ��������
        Map<String, String> params = new HashMap<String, String>();
        if(IsExit) {
        	params.put("status", "success");
        } else {
        	params.put("status", "fail");
        }
        //System.out.println("����������" + result);
        JSONObject jsonObjec = JSONObject.fromObject(params);
        out.write(jsonObjec.toString());
        out.flush();
        out.close();
	}

}
