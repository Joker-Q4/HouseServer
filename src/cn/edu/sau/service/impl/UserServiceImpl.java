package cn.edu.sau.service.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import cn.edu.sau.key.KeyValue;
import cn.edu.sau.pojo.Contract;
import cn.edu.sau.pojo.House;
import cn.edu.sau.pojo.Owner;
import cn.edu.sau.pojo.Tenant;
import cn.edu.sau.service.UserService;
import cn.edu.sau.util.JdbcUtils;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class UserServiceImpl implements UserService{

	/**
	 * 工作人员登陆
	 */
	@Override
	public boolean queryUserForLogin(String username, String password) {
		boolean result = false;
	//	Worker worker=null;
		Connection connection =null;
		PreparedStatement pstmt=null;
		ResultSet resultSet=null;
		try {
			connection=JdbcUtils.getCon();
			// 编写数据库语句
			String sql = "select * from worker where workername=? and password=?"; 
		    pstmt = (PreparedStatement) connection.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			resultSet = pstmt.executeQuery();
			if(resultSet.next()){
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	
		   JdbcUtils.close(pstmt, connection);	
		}
		
		return result;
	}

	/**
	 * 管理员登陆
	 */
	@Override
	public boolean queryAdminForLogin(String username, String password) {
		boolean result = false;
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			connection=JdbcUtils.getCon();
			// sql语句
			String sql = "select * from admin where adminname=? and password=?"; 
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			resultSet = ps.executeQuery();
			if(resultSet.next()){
				result = true;
				System.out.println("��¼�ɹ���");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}

	/**
	 * 查询工作人员列表
	 */
	@Override
	public ResultSet queryWorkerList() {
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		// 连接数据库
		try {
			connection=JdbcUtils.getCon();
			// 编写sql语句
			String sql = "select * from worker";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	/*	finally {	
			JdbcUtils.close(ps, connection);	
		}*/
		return resultSet;
	}
	
	/**
	 * 添加工作人员
	 */
	@Override
	public boolean addWorkerToList(String name, String password) {
		boolean result = false;
		int a = 0;
		Connection connection =null;
		PreparedStatement ps=null;
		try {
			connection=JdbcUtils.getCon();
			// sql 语句
			String sql = "insert into worker(workername,password) values(?,?)";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {	
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}

	/**
	 * 修改工作人员信息
	 */
	@Override
	public boolean updateWokerList(String name, String password) {
		boolean result = false;
		int a = 0;
		Connection connection =null;
		PreparedStatement ps=null;
		try {
			connection=JdbcUtils.getCon();
			// 执行sql语句
			String sql = "update worker set password=? where workername=?";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, name);
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {	
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}

	/**
	 * 删除工作人员
	 */
	@Override
	public boolean delWorkerFromList(String name, String password) {
		boolean result = false;
		int a = 0;
		Connection connection =null;
		PreparedStatement ps=null;
		try {
			connection=JdbcUtils.getCon();
			//��̬sql���
			String sql = "delete from worker where workername=? and password=?";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {	
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}

	/**
	 * 添加房屋信息
	 */
	@Override
	public boolean addHouseToList(JSONObject jsonObject) {
		boolean result = false;
		int a = 0;
		Connection connection =null;
		PreparedStatement ps=null;
		try {
			House house = new House();
	    	house.setUsername(jsonObject.getString(KeyValue.NAME));
	    	house.setPhone(Integer.parseInt(jsonObject.getString(KeyValue.PHONE)));
	    	house.setRent(Double.parseDouble(jsonObject.getString(KeyValue.RENT)));
	    	house.setApartment(jsonObject.getString(KeyValue.APART));
	    	house.setArea(Double.parseDouble(jsonObject.getString(KeyValue.AREA)));
	    	house.setFlr(Integer.parseInt(jsonObject.getString(KeyValue.FLOOR)));
	    	house.setOrientation(jsonObject.getString(KeyValue.ORIEN));
	    	house.setCondi(jsonObject.getString(KeyValue.COND));
	    	house.setResidential_quarters(jsonObject.getString(KeyValue.RES_Q));
	    	house.setRespective_area(jsonObject.getString(KeyValue.RES_A));
	    	house.setAddress(jsonObject.getString(KeyValue.ADDR));
	    	house.setProperty_right(Integer.parseInt(jsonObject.getString(KeyValue.PRO_R)));
	    	house.setDescription(jsonObject.getString(KeyValue.DESC));
	    	house.setImage1(jsonObject.getString(KeyValue.IMAGE1));
	    	house.setImage2(jsonObject.getString(KeyValue.IMAGE2));
	    	house.setImage3(jsonObject.getString(KeyValue.IMAGE3));
	       	house.setStater("false");
       
        	// 连接数据库
        	connection=JdbcUtils.getCon();
        	
        	if(!addOwnerToList(house.getUsername(), house.getPhone())) {
        		return result;
        	}
        	int num = queryOwnerId(house.getUsername(), house.getPhone());
        	house.setOwner_id(num);
        	
        	
			// sql语句
			String sql = "insert into house(username,phone,rent,apartment,area,flr,orientation,condi,residential_quarters,respective_area,address,property_right,description,image1,image2,image3,stater,id_owner) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, house.getUsername());
			ps.setInt(2, house.getPhone());
			ps.setDouble(3, house.getRent());
			ps.setString(4, house.getApartment());
			ps.setDouble(5, house.getArea());
			ps.setInt(6, house.getFlr());
			ps.setString(7, house.getOrientation());
			ps.setString(8, house.getCondi());
			ps.setString(9, house.getResidential_quarters());
			ps.setString(10, house.getRespective_area());
			ps.setString(11, house.getAddress());
			ps.setInt(12, house.getProperty_right());
			ps.setString(13, house.getDescription());
			ps.setString(14, house.getImage1());
			ps.setString(15, house.getImage2());
			ps.setString(16, house.getImage3());
			ps.setString(17, house.getStater());
			ps.setInt(18, house.getOwner_id());
			
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
//            queryHouseId(num);
            
            if(!addHouseIdToOwner(queryHouseId(num), num)) {
            	return false;
            }
            
			result = true;
        } catch (JSONException e) {
            e.printStackTrace();
            result = false;
        } catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}
	
	/**
	 * 添加房屋时添加部分房主信息
	 */
	@Override
	public boolean addOwnerToList(String username, int pnumber) {
		boolean result = false;
		int a = 0;
		Connection connection =null;
		PreparedStatement ps=null;
		try {
			connection=JdbcUtils.getCon();
			// sql语句
			String sql = "insert into houseowner(name,sex,age,p_number,id_number,address,id_house) values(?,?,?,?,?,?,?)";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, "");
			ps.setInt(3, 0);
			ps.setInt(4, pnumber);
			ps.setInt(5, 0);
			ps.setString(6, "");
			ps.setInt(7, 0);
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}

	/**
	 * 查询房主id
	 */
	@Override
	public int queryOwnerId(String username, int pnumber) {
		int result = 0;
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			connection=JdbcUtils.getCon();
			// sql语句
			String sql = "select id from houseowner where name=? and p_number=?";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setInt(2, pnumber);
			resultSet = ps.executeQuery();
			if(resultSet.next()){
				result = resultSet.getInt("id");
				System.out.println("用户id：" + result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}
	
	/**
	 * 通过房主id查询房屋id
	 */
	@Override
	public int queryHouseId(int ownerId) {
		int result = 0;
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			connection=JdbcUtils.getCon();
			// sql语句
			String sql = "select id from house where id_owner=?";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, ownerId);
			resultSet = ps.executeQuery();
			if(resultSet.next()){
				result = resultSet.getInt("id");
				System.out.println("房屋id：" + result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}

	/**
	 * 把房屋id添加到房主表中
	 */
	@Override
	public boolean addHouseIdToOwner(int houseId, int ownerId) {
		boolean result = false;
		int a = 0;
		Connection connection =null;
		PreparedStatement ps=null;
		try {
			connection=JdbcUtils.getCon();
			// 执行sql语句
			String sql = "update houseowner set id_house=? where id=?";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, houseId);
			ps.setInt(2, ownerId);
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {	
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}

	/**
	 * 查询房屋列表
	 */
	@Override
	public ResultSet queryHouseList(JSONObject jsonObject) {
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		// 执行sql语句
		try {
			// 读取 JSONObject 中的信息
	    	double QMinRent = Double.parseDouble(jsonObject.getString(KeyValue.QMinRent));
	    	double QMaxRent = Double.parseDouble(jsonObject.getString(KeyValue.QMaxRent));
	    	String QApartment = jsonObject.getString(KeyValue.QApartment);
	    	double QMinArea = Double.parseDouble(jsonObject.getString(KeyValue.QMinArea));
	    	double QMaxArea = Double.parseDouble(jsonObject.getString(KeyValue.QMaxArea));
	    	String QOrientation = jsonObject.getString(KeyValue.QOrientation);
	    	String QResidentialQuarters = jsonObject.getString(KeyValue.QResidentialQuarters);
	    	String QRespectiveArea = jsonObject.getString(KeyValue.QRespectiveArea);
	    	String QStater = jsonObject.getString(KeyValue.QStater);
	    	
	    	// 连接数据库
			connection=JdbcUtils.getCon();
			
			String sql = "";
			
			// 什么条件都没有
			if(QMinRent == 0 && QMaxRent == 0 && QApartment == "" 
					&& QMinArea == 0 && QMaxArea == 0 && QOrientation == "" 
					&& QResidentialQuarters == "" && QRespectiveArea == "") {
				sql = "select id,rent,apartment,image1,image2,image3 from house";
			} else {
				// 拼接sql语句
				sql = "select id,rent,apartment,image1,image2,image3 from house";
				// 判断租金
				if(QMaxRent != 0) {
					sql = sql + " where rent>=" + QMinRent + " and rent<=" + QMaxRent;
				}
				// 判断户型
				if(QApartment != "") {
					// 判断是否有 where 被执行过
					if(sql.indexOf("where") == -1) {
						sql = sql + " where apartment like '%" + QApartment + "%'";
					} else {
						sql = sql + " and apartment like '%" + QApartment + "%'";
					}
				}
				// 判断面积
				if(QMaxArea != 0) {
					if(sql.indexOf("where") == -1) {
						sql = sql + " where area>=" + QMinArea + " and area<=" + QMaxArea;
					} else {
						sql = sql + " and area>=" + QMinArea + " and area<=" + QMaxArea;
					}
				}
				// 判断朝向
				if(QOrientation != "") {
					if(sql.indexOf("where") == -1) {
						sql = sql + " where orientation like '%" + QOrientation + "%'";
					} else {
						sql = sql + " and orientation like '%" + QOrientation + "%'";
					}
				}
				// 判断所在小区
				if(QResidentialQuarters != "") {
					if(sql.indexOf("where") == -1) {
						sql = sql + " where residential_quarters like '%" + QResidentialQuarters + "%'";
					} else {
						sql = sql + " and residential_quarters like '%" + QResidentialQuarters + "%'";
					}
				}
				// 判断所属区域
				if(QRespectiveArea != "") {
					if(sql.indexOf("where") == -1) {
						sql = sql + " where respective_area like '%" + QRespectiveArea + "%'";
					} else {
						sql = sql + " and respective_area like '%" + QRespectiveArea + "%'";
					}
				}
				// 判断是否已租
				if(QStater != "") {
					if(sql.indexOf("where") == -1) {
						sql = sql + " where stater like '%" + QStater + "%'";
					} else {
						sql = sql + " and stater like '%" + QStater + "%'";
					}
				}
			}
			
			// 拼接sql语句
			ps = (PreparedStatement) connection.prepareStatement(sql);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}

	/**
	 * 根据条件查询在该面积段内的房屋数据
	 */
	@Override
	public int queryHouseAreaStatistics(double min, double max) {
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		int count = 0;
		// 连接数据库
		try {
			connection=JdbcUtils.getCon();
			// 编写数据库语句
			String sql = "select count(*) totalCount from house";
			sql = sql + " where area>=" + min + " and area<=" + max;
			ps = (PreparedStatement) connection.prepareStatement(sql);
			resultSet = ps.executeQuery();
			if(resultSet.next()) {
				count=resultSet.getInt("totalCount");
			}
//			resultSet.last();
//			count = resultSet.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return count;
	}

	/**
	 * 根据条件查询在该租金段内的房屋数据
	 */
	@Override
	public int queryHouseRentStatistics(double min, double max) {
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		int count = 0;
		// 连接数据库
		try {
			connection=JdbcUtils.getCon();
			// 编写数据库语句
			String sql = "select count(*) totalCount from house";
			sql = sql + " where rent>=" + min + " and rent<=" + max;
			ps = (PreparedStatement) connection.prepareStatement(sql);
			resultSet = ps.executeQuery();
			if(resultSet.next()) {
				count=resultSet.getInt("totalCount");
			}
//			resultSet.last();
//			count = resultSet.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return count;
	}
	
	/**
	 * 查询所有的小区
	 */
	@Override
	public ArrayList<String> queryHouseRQuartersStatistics() {
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		ArrayList<String> list = new ArrayList<>();
		// 连接数据库
		try {
			connection=JdbcUtils.getCon();
			// 编写sql语句
			String sql = "select residential_quarters from house";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			resultSet = ps.executeQuery();
			while(resultSet.next()) {
				list.add(resultSet.getString(KeyValue.RES_Q));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	
			JdbcUtils.close(ps, connection);	
		}
		return list;
	}
	
	/**
	 * 查询该小区内的房屋数据
	 */
	@Override
	public int queryHouseRQuartersStatistics(String residential_quarters) {
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		int count = 0;
		// 连接数据库
		try {
			connection=JdbcUtils.getCon();
			// 编写数据库语句
			String sql = "select count(*) totalCount from house";
			sql = sql + " where residential_quarters like '%" + residential_quarters + "%'";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			resultSet = ps.executeQuery();
			if(resultSet.next()) {
				count=resultSet.getInt("totalCount");
			}
//			resultSet.last();
//			count = resultSet.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return count;
	}

	/**
	 * 查询该区域的房屋数据
	 */
	@Override
	public int queryHouseRAreaStatistics(String respective_area) {
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		int count = 0;
		// 连接数据库
		try {
			connection=JdbcUtils.getCon();
			// 编写数据库语句
			String sql = "select count(*) totalCount from house";
			sql = sql + " where respective_area like '%" + respective_area + "%'";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			resultSet = ps.executeQuery();
			if(resultSet.next()) {
				count=resultSet.getInt("totalCount");
			}
//			resultSet.last();
//			count = resultSet.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return count;
	}
	
	/**
	 * 查询已租或未租的房屋数据
	 */
	@Override
	public int queryHouseStaterStatistics(String stater) {
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		int count = 0;
		// 连接数据库
		try {
			connection=JdbcUtils.getCon();
			// 编写数据库语句
			String sql = "select count(*) totalCount from house";
			sql = sql + " where stater like '%" + stater + "%'";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			resultSet = ps.executeQuery();
			if(resultSet.next()) {
				count=resultSet.getInt("totalCount");
			}
//			resultSet.last();
//			count = resultSet.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return count;
	}

	/**
	 * 添加房主信息
	 */
	@Override
	public boolean addOwnerToList(JSONObject jsonObject) {
		boolean result = false;
		int a = 0;
		Connection connection =null;
		PreparedStatement ps=null;
		try {
			Owner owner = new Owner();
			owner.setName(jsonObject.getString(KeyValue.ONAME));
			owner.setSex(jsonObject.getString(KeyValue.OSEX));
			owner.setAge(Integer.valueOf(jsonObject.getString(KeyValue.OAGE)));
			owner.setP_number(Integer.valueOf(jsonObject.getString(KeyValue.OPNUM)));
			owner.setId_number(jsonObject.getString(KeyValue.OIDCard));
			owner.setAddress(jsonObject.getString(KeyValue.OADDR));
			owner.setId_house(0);

        	// 连接数据库
        	connection=JdbcUtils.getCon();
        	
			// sql语句
			String sql = "insert into houseowner(name,sex,age,p_number,id_number,address,id_house) values(?,?,?,?,?,?,?)";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, owner.getName());
			ps.setString(2, owner.getSex());
			ps.setInt(3, owner.getAge());
			ps.setInt(4, owner.getP_number());
			ps.setString(5, owner.getId_number());
			ps.setString(6, owner.getAddress());
			ps.setInt(7, owner.getId_house());
			
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
			result = true;
        } catch (JSONException e) {
            e.printStackTrace();
            result = false;
        } catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}

	/**
	 * 添加房客需求
	 */
	@Override
	public boolean addTenantToList(JSONObject jsonObject) {
		boolean result = false;
		int a = 0;
		Connection connection =null;
		PreparedStatement ps=null;
		try {
			Tenant tenant = new Tenant();
			tenant.setName(jsonObject.getString(KeyValue.TNAME));
			tenant.setSex(jsonObject.getString(KeyValue.TSEX));
			tenant.setAge(Integer.valueOf(jsonObject.getString(KeyValue.TAGE)));
			tenant.setId_number(jsonObject.getString(KeyValue.TIDCard));
			tenant.setMin_rent(Double.valueOf(jsonObject.getString(KeyValue.TMINRENT)));
			tenant.setMax_rent(Double.valueOf(jsonObject.getString(KeyValue.TMAXRENT)));
			tenant.setApart(jsonObject.getString(KeyValue.TAPART));
			tenant.setMin_area(Double.valueOf(jsonObject.getString(KeyValue.TMINArea)));
			tenant.setMax_area(Double.valueOf(jsonObject.getString(KeyValue.TMAXArea)));
			tenant.setResidential_quarters(jsonObject.getString(KeyValue.TRQuarters));
			tenant.setRespective_area(jsonObject.getString(KeyValue.TRArea));
			tenant.setP_number(Integer.valueOf(jsonObject.getString(KeyValue.TPNUM)));
			tenant.setAddress(jsonObject.getString(KeyValue.TADDR));

        	// 连接数据库
        	connection=JdbcUtils.getCon();
        	
			// sql语句
			String sql = "insert into tenant(name,sex,age,id_number,min_rent,max_rent,apart,min_area,max_area,residential_quarters,respective_area,p_number,address) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			
			ps.setString(1, tenant.getName());
			ps.setString(2, tenant.getSex());
			ps.setInt(3, tenant.getAge());
			ps.setString(4, tenant.getId_number());
			ps.setDouble(5, tenant.getMin_rent());
			ps.setDouble(6, tenant.getMax_rent());
			ps.setString(7, tenant.getApart());
			ps.setDouble(8, tenant.getMin_area());
			ps.setDouble(9, tenant.getMax_area());
			ps.setString(10, tenant.getResidential_quarters());
			ps.setString(11, tenant.getRespective_area());
			ps.setInt(12, tenant.getP_number());
			ps.setString(13, tenant.getAddress());
			
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
			result = true;
        } catch (JSONException e) {
            e.printStackTrace();
            result = false;
        } catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}

	/**
	 * 添加合同信息
	 */
	@Override
	public boolean addContractToList(JSONObject jsonObject) {
		boolean result = false;
		java.util.Date d = null;
		int a = 0;
		Connection connection =null;
		PreparedStatement ps=null;
		try {
			Contract contract = new Contract();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			d = sdf.parse(jsonObject.getString(KeyValue.CD));
			java.sql.Date date = new java.sql.Date(d.getTime());
			
			contract.setContract_number(Integer.valueOf(jsonObject.getString(KeyValue.CN)));
			contract.setHouse_number(Integer.valueOf(jsonObject.getString(KeyValue.HN)));
			contract.setHouse_address(jsonObject.getString(KeyValue.HA));
			contract.setOwner_name(jsonObject.getString(KeyValue.ON));
			contract.setOwner_pnumber(Integer.valueOf(jsonObject.getString(KeyValue.OP)));
			contract.setOwner_idnumber(jsonObject.getString(KeyValue.OI));
			contract.setRenter_name(jsonObject.getString(KeyValue.RNA));
			contract.setRenter_number(Integer.valueOf(jsonObject.getString(KeyValue.RNU)));
			contract.setRenter_idnumber(jsonObject.getString(KeyValue.RI));
			contract.setRent(Integer.valueOf(jsonObject.getString(KeyValue.R)));
			contract.setLease_term(Integer.valueOf(jsonObject.getString(KeyValue.LT)));
			contract.setAgent(jsonObject.getString(KeyValue.A));
			contract.setAgency_fee(Integer.valueOf(jsonObject.getString(KeyValue.AF)));
			contract.setContract_date(date);
			contract.setContract_photo(jsonObject.getString(KeyValue.CP));
			
        	// 连接数据库
        	connection=JdbcUtils.getCon();
        	
			// sql语句
			String sql = "insert into contract(contract_number,house_number,house_address,owner_name,owner_pnumber,owner_idnumber,renter_name,renter_number,renter_idnumber,rent,lease_term,agent,agency_fee,contract_date,contract_photo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			
			ps.setInt(1, contract.getContract_number());
			ps.setInt(2, contract.getHouse_number());
			ps.setString(3, contract.getHouse_address());
			ps.setString(4, contract.getOwner_name());
			ps.setInt(5, contract.getOwner_pnumber());
			ps.setString(6, contract.getOwner_idnumber());
			ps.setString(7, contract.getRenter_name());
			ps.setInt(8, contract.getRenter_number());
			ps.setString(9, contract.getRenter_idnumber());
			ps.setInt(10, contract.getRent());
			ps.setInt(11, contract.getLease_term());
			ps.setString(12, contract.getAgent());
			ps.setInt(13, contract.getAgency_fee());
			ps.setDate(14, contract.getContract_date());
			ps.setString(15, contract.getContract_photo());
			
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
			result = true;
        } catch (JSONException e) {
            e.printStackTrace();
            result = false;
        } catch (SQLException e) {
			e.printStackTrace();
			result = false;
		} catch (ParseException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}

	/**
	 * 条件查询及模糊查询合同信息
	 */
	@Override
	public ResultSet queryContractList(JSONObject jsonObject) {
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		// 执行sql语句
		try {
			// 读取 JSONObject 中的信息
			
			double QMIND = Double.valueOf(jsonObject.getString(KeyValue.QMIND));
			double QMAXD = Double.valueOf(jsonObject.getString(KeyValue.QMAXD));
			int CN = Integer.valueOf(jsonObject.getString(KeyValue.CN));
			String HA = jsonObject.getString(KeyValue.HA);
			String ON = jsonObject.getString(KeyValue.ON);
	    	
	    	// 连接数据库
			connection=JdbcUtils.getCon();
			
			String sql = "";
			
			// 什么条件都没有
			if(QMIND == 0 && QMAXD == 0 && CN == 0 
					&& HA == "" && ON == "") {
				sql = "select id,contract_photo,owner_name,renter_name from contract";
			} else {
				// 拼接sql语句
				sql = "select id,contract_photo,owner_name,renter_name from contract";
				// 判断日期
				if(QMAXD != 0) {
					sql = sql + " where lease_term>=" + QMIND + " and lease_term<=" + QMAXD;
				}
				// 判断合同号
				if(CN != 0) {
					// 判断是否有 where 被执行过
					if(sql.indexOf("where") == -1) {
						sql = sql + " where contract_number=" + CN;
					} else {
						sql = sql + " and contract_number=" + CN;
					}
				}
				// 判断房屋地址
				if(HA != "") {
					if(sql.indexOf("where") == -1) {
						sql = sql + " where house_address like '%" + HA + "%'";
					} else {
						sql = sql + " and house_address like '%" + HA + "%'";
					}
				}
				// 判断房主姓名
				if(ON != "") {
					if(sql.indexOf("where") == -1) {
						sql = sql + " where owner_name like '%" + ON + "%'";
					} else {
						sql = sql + " and owner_name like '%" + ON + "%'";
					}
				}
			}
			
			// 拼接sql语句
			ps = (PreparedStatement) connection.prepareStatement(sql);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	/**
	 * 查询时间段内合同的统计数据
	 */
	@Override
	public int queryContractDateStatistics(Date mindate, Date maxdate) {
		System.out.println(mindate);
		System.out.println(maxdate);
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		int count = 0;
		// 连接数据库
		try {
			connection=JdbcUtils.getCon();
			// 编写数据库语句
			// 不能用字符串拼接（妈的智障，拼了老子半天，还以为数据库语句有问题）
			String sql = "select count(*) totalCount from contract where contract_date>=? and contract_date<=?";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setDate(1, mindate);
			ps.setDate(2, maxdate);
			resultSet = ps.executeQuery();
			if(resultSet.next()) {
				count=resultSet.getInt("totalCount");
				System.out.println(count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return count;
	}

	/**
	 * 查询中介人列表
	 */
	@Override
	public ArrayList<String> queryContractAgentStatistics() {
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		ArrayList<String> list = new ArrayList<>();
		// 连接数据库
		try {
			connection=JdbcUtils.getCon();
			// 编写sql语句
			String sql = "select agent from contract";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			resultSet = ps.executeQuery();
			while(resultSet.next()) {
				list.add(resultSet.getString(KeyValue.A));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	
			JdbcUtils.close(ps, connection);	
		}
		return list;
	}

	/**
	 * 查询中介人统计数据
	 */
	@Override
	public int queryContractAgentStatistics(String agentName) {
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		int count = 0;
		// 连接数据库
		try {
			connection=JdbcUtils.getCon();
			// 编写数据库语句
			String sql = "select count(*) totalCount from contract";
			sql = sql + " where agent like '%" + agentName + "%'";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			resultSet = ps.executeQuery();
			if(resultSet.next()) {
				count=resultSet.getInt("totalCount");
			}
//			resultSet.last();
//			count = resultSet.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return count;
	}

	/**
	 * 查询房主列表
	 */
	@Override
	public ResultSet queryOwnerList(String name) {
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		// 连接数据库
		try {
			connection=JdbcUtils.getCon();
			// 编写sql语句
			if(name.equals("")) {
				String sql = "select id,name from houseowner";
				ps = (PreparedStatement) connection.prepareStatement(sql);
			} else {
				String sql = "select id,name from houseowner ";
				sql = sql + "where name like '%" + name + "%'";
				ps = (PreparedStatement) connection.prepareStatement(sql);
			}
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	/*	finally {	
			JdbcUtils.close(ps, connection);	
		}*/
		return resultSet;
	}

	/**
	 * 查询房客列表
	 */
	@Override
	public ResultSet queryTenantList(String name) {
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		// 连接数据库
		try {
			connection=JdbcUtils.getCon();
			// 编写sql语句
			if(name.equals("")) {
				String sql = "select id,name from tenant";
				ps = (PreparedStatement) connection.prepareStatement(sql);
			} else {
				String sql = "select id,name from tenant ";
				sql = sql + "where name like '%" + name + "%'";
				ps = (PreparedStatement) connection.prepareStatement(sql);
			}
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	/*	finally {	
			JdbcUtils.close(ps, connection);	
		}*/
		return resultSet;
	}

	/**
	 * 查询房屋具体信息
	 */
	@Override
	public ResultSet queryHouseOne(String id) {
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		// 连接数据库
		try {
			connection=JdbcUtils.getCon();
			// 编写sql语句
			String sql = "select * from house where id=?";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(id));
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	/*	finally {	
			JdbcUtils.close(ps, connection);	
		}*/
		return resultSet;
	}

	/**
	 * 根据id查询所有房主信息
	 */
	@Override
	public ResultSet queryOwnerOne(String id) {
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		// 连接数据库
		try {
			connection=JdbcUtils.getCon();
			// 编写sql语句
			String sql = "select * from houseowner where id=?";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(id));
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	/*	finally {	
			JdbcUtils.close(ps, connection);	
		}*/
		return resultSet;
	}

	/**
	 * 查询租客的全部信息
	 */
	@Override
	public ResultSet queryTenantOne(String id) {
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		// 连接数据库
		try {
			connection=JdbcUtils.getCon();
			// 编写sql语句
			String sql = "select * from tenant where id=?";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(id));
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	/*	finally {	
			JdbcUtils.close(ps, connection);	
		}*/
		return resultSet;
	}

	@Override
	public ResultSet queryContractOne(String id) {
		Connection connection =null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		// 连接数据库
		try {
			connection=JdbcUtils.getCon();
			// 编写sql语句
			String sql = "select * from contract where id=?";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(id));
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	/*	finally {	
			JdbcUtils.close(ps, connection);	
		}*/
		return resultSet;
	}
	
	/**
	 * 修改房主信息
	 */
	@Override
	public boolean updateOwner(String name, int phone) {
		boolean result = false;
		int a = 0;
		Connection connection =null;
		PreparedStatement ps=null;
		try {
			connection=JdbcUtils.getCon();
			// 执行sql语句
			String sql = "update houseowner set p_number=? where name=?";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, phone);
			ps.setString(2, name);
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {	
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}

	/**
	 * 修改房屋信息
	 */
	@Override
	public boolean updateHouse(JSONObject jsonObject) {
		boolean result = false;
		int a = 0;
		String id = "";
		Connection connection =null;
		PreparedStatement ps=null;
		try {
			House house = new House();
	    	house.setUsername(jsonObject.getString(KeyValue.NAME));
	    	house.setPhone(Integer.parseInt(jsonObject.getString(KeyValue.PHONE)));
	    	house.setRent(Double.parseDouble(jsonObject.getString(KeyValue.RENT)));
	    	house.setApartment(jsonObject.getString(KeyValue.APART));
	    	house.setArea(Double.parseDouble(jsonObject.getString(KeyValue.AREA)));
	    	house.setFlr(Integer.parseInt(jsonObject.getString(KeyValue.FLOOR)));
	    	house.setOrientation(jsonObject.getString(KeyValue.ORIEN));
	    	house.setCondi(jsonObject.getString(KeyValue.COND));
	    	house.setResidential_quarters(jsonObject.getString(KeyValue.RES_Q));
	    	house.setRespective_area(jsonObject.getString(KeyValue.RES_A));
	    	house.setAddress(jsonObject.getString(KeyValue.ADDR));
	    	house.setProperty_right(Integer.parseInt(jsonObject.getString(KeyValue.PRO_R)));
	    	house.setDescription(jsonObject.getString(KeyValue.DESC));
	    	house.setImage1(jsonObject.getString(KeyValue.IMAGE1));
	    	house.setImage2(jsonObject.getString(KeyValue.IMAGE2));
	    	house.setImage3(jsonObject.getString(KeyValue.IMAGE3));
	       	house.setStater("false");
	       	id = jsonObject.getString("id");
       
        	// 连接数据库
        	connection=JdbcUtils.getCon();
        	
        	if(!updateOwner(house.getUsername(), house.getPhone())) {
        		return result;
        	}
        	int num = queryOwnerId(house.getUsername(), house.getPhone());
        	house.setOwner_id(num);
        	
			// sql语句
        	String sql = "update house set username=?,phone=?,rent=?,apartment=?,area=?,flr=?,orientation=?,condi=?,residential_quarters=?,respective_area=?,address=?,property_right=?,description=?,image1=?,image2=?,image3=?,stater=?,id_owner=? where id=?";
//			String sql = "insert into house(username,phone,rent,apartment,area,flr,orientation,condi,residential_quarters,respective_area,address,property_right,description,image1,image2,image3,stater,id_owner) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, house.getUsername());
			ps.setInt(2, house.getPhone());
			ps.setDouble(3, house.getRent());
			ps.setString(4, house.getApartment());
			ps.setDouble(5, house.getArea());
			ps.setInt(6, house.getFlr());
			ps.setString(7, house.getOrientation());
			ps.setString(8, house.getCondi());
			ps.setString(9, house.getResidential_quarters());
			ps.setString(10, house.getRespective_area());
			ps.setString(11, house.getAddress());
			ps.setInt(12, house.getProperty_right());
			ps.setString(13, house.getDescription());
			ps.setString(14, house.getImage1());
			ps.setString(15, house.getImage2());
			ps.setString(16, house.getImage3());
			ps.setString(17, house.getStater());
			ps.setInt(18, house.getOwner_id());
			ps.setInt(19, Integer.valueOf(id));
			
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
//            queryHouseId(num);
            
            if(!addHouseIdToOwner(queryHouseId(num), num)) {
            	return false;
            }
            
			result = true;
        } catch (JSONException e) {
            e.printStackTrace();
            result = false;
        } catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}

	/**
	 * 删除房屋信息
	 */
	@Override
	public boolean deleteHouse(JSONObject jsonObject) {
		boolean result = false;
		int a = 0;
		String id = "";
		Connection connection =null;
		PreparedStatement ps=null;
		try {
	       	id = jsonObject.getString("id");
       
        	// 连接数据库
        	connection=JdbcUtils.getCon();
        	
        	String sql = "delete from house where id=?";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(id));
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            result = false;
        } catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}
	
	/**
	 * 更新合同信息
	 */
	@Override
	public boolean updateContract(JSONObject jsonObject) {
		boolean result = false;
		java.util.Date d = null;
		int a = 0;
		String id = "";
		Connection connection =null;
		PreparedStatement ps=null;
		try {
			Contract contract = new Contract();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			d = sdf.parse(jsonObject.getString(KeyValue.CD));
			java.sql.Date date = new java.sql.Date(d.getTime());
			
			contract.setContract_number(Integer.valueOf(jsonObject.getString(KeyValue.CN)));
			contract.setHouse_number(Integer.valueOf(jsonObject.getString(KeyValue.HN)));
			contract.setHouse_address(jsonObject.getString(KeyValue.HA));
			contract.setOwner_name(jsonObject.getString(KeyValue.ON));
			contract.setOwner_pnumber(Integer.valueOf(jsonObject.getString(KeyValue.OP)));
			contract.setOwner_idnumber(jsonObject.getString(KeyValue.OI));
			contract.setRenter_name(jsonObject.getString(KeyValue.RNA));
			contract.setRenter_number(Integer.valueOf(jsonObject.getString(KeyValue.RNU)));
			contract.setRenter_idnumber(jsonObject.getString(KeyValue.RI));
			contract.setRent(Integer.valueOf(jsonObject.getString(KeyValue.R)));
			contract.setLease_term(Integer.valueOf(jsonObject.getString(KeyValue.LT)));
			contract.setAgent(jsonObject.getString(KeyValue.A));
			contract.setAgency_fee(Integer.valueOf(jsonObject.getString(KeyValue.AF)));
			contract.setContract_date(date);
			contract.setContract_photo(jsonObject.getString(KeyValue.CP));
			id = jsonObject.getString("id");
			
        	// 连接数据库
        	connection=JdbcUtils.getCon();
        	
			// sql语句
        	String sql = "update contract set contract_number=?,house_number=?,house_address=?,owner_name=?,owner_pnumber=?,owner_idnumber=?,renter_name=?,renter_number=?,renter_idnumber=?,rent=?,lease_term=?,agent=?,agency_fee=?,contract_date=?,contract_photo=? where id=?";
//        	String sql = "insert into contract(contract_number,house_number,house_address,owner_name,owner_pnumber,owner_idnumber,renter_name,renter_number,renter_idnumber,rent,lease_term,agent,agency_fee,contract_date,contract_photo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			
			ps.setInt(1, contract.getContract_number());
			ps.setInt(2, contract.getHouse_number());
			ps.setString(3, contract.getHouse_address());
			ps.setString(4, contract.getOwner_name());
			ps.setInt(5, contract.getOwner_pnumber());
			ps.setString(6, contract.getOwner_idnumber());
			ps.setString(7, contract.getRenter_name());
			ps.setInt(8, contract.getRenter_number());
			ps.setString(9, contract.getRenter_idnumber());
			ps.setInt(10, contract.getRent());
			ps.setInt(11, contract.getLease_term());
			ps.setString(12, contract.getAgent());
			ps.setInt(13, contract.getAgency_fee());
			ps.setDate(14, contract.getContract_date());
			ps.setString(15, contract.getContract_photo());
			ps.setInt(16, Integer.valueOf(id));
			
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
			result = true;
        } catch (JSONException e) {
            e.printStackTrace();
            result = false;
        } catch (SQLException e) {
			e.printStackTrace();
			result = false;
		} catch (ParseException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}

	/**
	 * 删除合同信息
	 */
	@Override
	public boolean deleteContract(JSONObject jsonObject) {
		boolean result = false;
		int a = 0;
		String id = "";
		Connection connection =null;
		PreparedStatement ps=null;
		try {
	       	id = jsonObject.getString("id");
       
        	// 连接数据库
        	connection=JdbcUtils.getCon();
        	
        	String sql = "delete from contract where id=?";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(id));
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            result = false;
        } catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}

	/**
	 * 更新房主信息
	 */
	@Override
	public boolean updateOwner(JSONObject jsonObject) {
		boolean result = false;
		int a = 0;
		String id = "";
		Connection connection =null;
		PreparedStatement ps=null;
		try {
			Owner owner = new Owner();
			owner.setName(jsonObject.getString(KeyValue.ONAME));
			owner.setSex(jsonObject.getString(KeyValue.OSEX));
			owner.setAge(Integer.valueOf(jsonObject.getString(KeyValue.OAGE)));
			owner.setId_house(Integer.valueOf(jsonObject.getString(KeyValue.OHID)));
			owner.setP_number(Integer.valueOf(jsonObject.getString(KeyValue.OPNUM)));
			owner.setId_number(jsonObject.getString(KeyValue.OIDCard));
			owner.setAddress(jsonObject.getString(KeyValue.OADDR));
			id = jsonObject.getString("id");

        	// 连接数据库
        	connection=JdbcUtils.getCon();
        	
			// sql语句
			String sql = "update houseowner set name=?,sex=?,age=?,p_number=?,id_number=?,address=?,id_house=? where id=?";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, owner.getName());
			ps.setString(2, owner.getSex());
			ps.setInt(3, owner.getAge());
			ps.setInt(4, owner.getP_number());
			ps.setString(5, owner.getId_number());
			ps.setString(6, owner.getAddress());
			ps.setInt(7, owner.getId_house());
			ps.setInt(8, Integer.valueOf(id));
			
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
			result = true;
        } catch (JSONException e) {
            e.printStackTrace();
            result = false;
        } catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}

	/**
	 * 删除房主信息
	 */
	@Override
	public boolean deleteOwner(JSONObject jsonObject) {
		boolean result = false;
		int a = 0;
		String id = "";
		Connection connection =null;
		PreparedStatement ps=null;
		try {
	       	id = jsonObject.getString("id");
       
        	// 连接数据库
        	connection=JdbcUtils.getCon();
        	
        	String sql = "delete from houseowner where id=?";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(id));
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            result = false;
        } catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}

	/**
	 * 更新房客信息
	 */
	@Override
	public boolean updateTenant(JSONObject jsonObject) {
		boolean result = false;
		int a = 0;
		String id = "";
		Connection connection =null;
		PreparedStatement ps=null;
		try {
			Tenant tenant = new Tenant();
			tenant.setName(jsonObject.getString(KeyValue.TNAME));
			tenant.setSex(jsonObject.getString(KeyValue.TSEX));
			tenant.setAge(Integer.valueOf(jsonObject.getString(KeyValue.TAGE)));
			tenant.setId_number(jsonObject.getString(KeyValue.TIDCard));
			tenant.setMin_rent(Double.valueOf(jsonObject.getString(KeyValue.TMINRENT)));
			tenant.setMax_rent(Double.valueOf(jsonObject.getString(KeyValue.TMAXRENT)));
			tenant.setApart(jsonObject.getString(KeyValue.TAPART));
			tenant.setMin_area(Double.valueOf(jsonObject.getString(KeyValue.TMINArea)));
			tenant.setMax_area(Double.valueOf(jsonObject.getString(KeyValue.TMAXArea)));
			tenant.setResidential_quarters(jsonObject.getString(KeyValue.TRQuarters));
			tenant.setRespective_area(jsonObject.getString(KeyValue.TRArea));
			tenant.setP_number(Integer.valueOf(jsonObject.getString(KeyValue.TPNUM)));
			tenant.setAddress(jsonObject.getString(KeyValue.TADDR));
			id = jsonObject.getString("id");

        	// 连接数据库
        	connection=JdbcUtils.getCon();
        	
			// sql语句
			String sql = "update tenant set name=?,sex=?,age=?,id_number=?,min_rent=?,max_rent=?,apart=?,min_area=?,max_area=?,residential_quarters=?,respective_area=?,p_number=?,address=? where id=?";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			
			ps.setString(1, tenant.getName());
			ps.setString(2, tenant.getSex());
			ps.setInt(3, tenant.getAge());
			ps.setString(4, tenant.getId_number());
			ps.setDouble(5, tenant.getMin_rent());
			ps.setDouble(6, tenant.getMax_rent());
			ps.setString(7, tenant.getApart());
			ps.setDouble(8, tenant.getMin_area());
			ps.setDouble(9, tenant.getMax_area());
			ps.setString(10, tenant.getResidential_quarters());
			ps.setString(11, tenant.getRespective_area());
			ps.setInt(12, tenant.getP_number());
			ps.setString(13, tenant.getAddress());
			ps.setInt(14, Integer.valueOf(id));
			
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
			result = true;
        } catch (JSONException e) {
            e.printStackTrace();
            result = false;
        } catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}

	/**
	 * 删除房客信息
	 */
	@Override
	public boolean deleteTenant(JSONObject jsonObject) {
		boolean result = false;
		int a = 0;
		String id = "";
		Connection connection =null;
		PreparedStatement ps=null;
		try {
	       	id = jsonObject.getString("id");
       
        	// 连接数据库
        	connection=JdbcUtils.getCon();
        	
        	String sql = "delete from tenant where id=?";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(id));
			a = ps.executeUpdate();
            if(a==1){
            	result = true;
            }else{
            	result = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            result = false;
        } catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {
			JdbcUtils.close(ps, connection);	
		}
		return result;
	}
}
