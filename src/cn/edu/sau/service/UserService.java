package cn.edu.sau.service;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.sf.json.JSONObject;

public interface UserService {
	
	/**
	 * 工作人员登录
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean queryUserForLogin(String username, String password);
	
	/**
	 * 管理员登陆
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean queryAdminForLogin(String username, String password);
	
	/**
	 * 查询所有工作人员列表
	 * @return
	 */
	public ResultSet queryWorkerList();
	
	/**
	 * 添加工作人员
	 * @param name
	 * @param password
	 * @return
	 */
	public boolean addWorkerToList(String name, String password);
	
	/**
	 * 修改工作人员信息
	 * @param name
	 * @param password
	 * @return
	 */
	public boolean updateWokerList(String name, String password);
	
	/**
	 * 删除一个工作人员
	 * @param name
	 * @param password
	 * @return
	 */
	public boolean delWorkerFromList(String name, String password);
	
	/**
	 * 添加房屋信息
	 * @param jsonObject
	 * @return
	 */
	public boolean addHouseToList(JSONObject jsonObject);
	
	/**
	 * 添加房屋时添加房主信息
	 * @param username
	 * @param pnumber
	 * @return
	 */
	public boolean addOwnerToList(String username, int pnumber);
	
	/**
	 * 通过用户名和手机号查询房主id
	 * @param username
	 * @param pnumber
	 * @return
	 */
	public int queryOwnerId(String username, int pnumber);
	
	/**
	 * 通过房主id查询房屋id
	 * @param ownerId
	 * @return
	 */
	public int queryHouseId(int ownerId);
	
	/**
	 * 把房屋的id添加到房主表中
	 * @param housId
	 * @return
	 */
	public boolean addHouseIdToOwner(int houseId, int ownerId);
	
	/**
	 * 模糊查询房屋列表
	 */
	public ResultSet queryHouseList(JSONObject jsonObject);
	
	/**
	 * 房屋面积区间数据统计
	 */
	public int queryHouseAreaStatistics(double min, double max);
	
	/**
	 * 房屋价格区间数据统计
	 */
	public int queryHouseRentStatistics(double min, double max);
	
	/**
	 * 查询所有小区名称
	 * @return
	 */
	public ArrayList<String> queryHouseRQuartersStatistics();
	
	/**
	 * 房屋区域数据统计
	 * @param respective_area
	 * @return
	 */
	public int queryHouseRAreaStatistics(String respective_area);
	
	/**
	 * 房屋小区统计数据
	 * @param residential_quarters
	 * @return
	 */
	public int queryHouseRQuartersStatistics(String residential_quarters);
	
	/**
	 * 房屋状态数据统计
	 * @param stater
	 * @return
	 */
	public int queryHouseStaterStatistics(String stater);
	
	/**
	 * 添加房主信息
	 * @param jsonObject
	 * @return
	 */
	public boolean addOwnerToList(JSONObject jsonObject);
	
	/**
	 * 添加房客需求
	 * @param jsonObject
	 * @return
	 */
	public boolean addTenantToList(JSONObject jsonObject);
	
	/**
	 * 添加合同信息
	 * @param jsonObject
	 * @return
	 */
	public boolean addContractToList(JSONObject jsonObject);
	
	/**
	 * 模糊查询合同列表
	 * @param jsonObject
	 * @return
	 */
	public ResultSet queryContractList(JSONObject jsonObject);
	
	/**
	 * 查询合同日期统计数据
	 * @param mindate
	 * @param maxdate
	 * @return
	 */
	public int queryContractDateStatistics(Date mindate, Date maxdate);
	
	/**
	 * 查询所有代理人名字
	 * @return
	 */
	public ArrayList<String> queryContractAgentStatistics();
	
	/**
	 * 查询代理人统计数据
	 * @param agentName
	 * @return
	 */
	public int queryContractAgentStatistics(String agentName);
	
	/**
	 * 查询房主列表
	 * @return
	 */
	public ResultSet queryOwnerList(String name);
	
	/**
	 * 查询房客列表
	 * @return
	 */
	public ResultSet queryTenantList(String name);
	
	/**
	 * 根据id查询房屋所有信息
	 * @param id
	 * @return
	 */
	public ResultSet queryHouseOne(String id);
	
	/**
	 * 根据id查询房主所有信息
	 * @param id
	 * @return
	 */
	public ResultSet queryOwnerOne(String id);
	
	/**
	 * 根据id查询租客的全部信息
	 * @param id
	 * @return
	 */
	public ResultSet queryTenantOne(String id);
	
	/**
	 * 根据id查询合同详细信
	 * @param id
	 * @return
	 */
	public ResultSet queryContractOne(String id);
	
	/**
	 * 修改房主的信息
	 * @param name
	 * @param phone
	 * @return
	 */
	public boolean updateOwner(String name, int phone);
	
	/**
	 * 修改房屋信息
	 * @param jsonObject
	 * @return
	 */
	public boolean updateHouse(JSONObject jsonObject);
	
	/**
	 * 删除房屋信息
	 * @param jsonObject
	 * @return
	 */
	public boolean deleteHouse(JSONObject jsonObject);
	
	/**
	 * 修改合同信息
	 * @param jsonObject
	 * @return
	 */
	public boolean updateContract(JSONObject jsonObject);
	
	/**
	 * 删除合同信息
	 * @param jsonObject
	 * @return
	 */
	public boolean deleteContract(JSONObject jsonObject);
	
	/**
	 * 更新房主信息
	 * @param jsonObject
	 * @return
	 */
	public boolean updateOwner(JSONObject jsonObject);
	
	/**
	 * 删除房主信息
	 * @param jsonObject
	 * @return
	 */
	public boolean deleteOwner(JSONObject jsonObject);
	
	/**
	 * 更新房客信息
	 * @param jsonObject
	 * @return
	 */
	public boolean updateTenant(JSONObject jsonObject);
	
	/**
	 * 删除房客信息
	 * @param jsonObject
	 * @return
	 */
	public boolean deleteTenant(JSONObject jsonObject);
}