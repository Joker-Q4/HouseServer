package cn.edu.sau.pojo;

import java.sql.Date;

public class Contract {
	private int contract_number;       // 合同号
	private int house_number;          // 房号
	private String house_address;      // 房屋地址
	private String owner_name;         // 房主姓名
	private int owner_pnumber;         // 房主电话
	private String owner_idnumber;     // 房主身份证号
	private String renter_name;        // 租用人姓名
	private int renter_number;         // 租用人电话
	private String renter_idnumber;    // 租用人身份证号
	private int rent;                  // 租金
	private int lease_term;            // 租期
	private String agent;              // 中介人
	private int agency_fee;            // 中介费
	private Date contract_date;        // 合同日期
	private String contract_photo;     // 合同照片
	
	public int getContract_number() {
		return contract_number;
	}
	public void setContract_number(int contract_number) {
		this.contract_number = contract_number;
	}
	public int getHouse_number() {
		return house_number;
	}
	public void setHouse_number(int house_number) {
		this.house_number = house_number;
	}
	public String getHouse_address() {
		return house_address;
	}
	public void setHouse_address(String house_address) {
		this.house_address = house_address;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public int getOwner_pnumber() {
		return owner_pnumber;
	}
	public void setOwner_pnumber(int owner_pnumber) {
		this.owner_pnumber = owner_pnumber;
	}
	public String getOwner_idnumber() {
		return owner_idnumber;
	}
	public void setOwner_idnumber(String owner_idnumber) {
		this.owner_idnumber = owner_idnumber;
	}
	public String getRenter_name() {
		return renter_name;
	}
	public void setRenter_name(String renter_name) {
		this.renter_name = renter_name;
	}
	public int getRenter_number() {
		return renter_number;
	}
	public void setRenter_number(int renter_number) {
		this.renter_number = renter_number;
	}
	public String getRenter_idnumber() {
		return renter_idnumber;
	}
	public void setRenter_idnumber(String renter_idnumber) {
		this.renter_idnumber = renter_idnumber;
	}
	public int getRent() {
		return rent;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	public int getLease_term() {
		return lease_term;
	}
	public void setLease_term(int lease_term) {
		this.lease_term = lease_term;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public int getAgency_fee() {
		return agency_fee;
	}
	public void setAgency_fee(int agency_fee) {
		this.agency_fee = agency_fee;
	}
	public Date getContract_date() {
		return contract_date;
	}
	public void setContract_date(Date contract_date) {
		this.contract_date = contract_date;
	}
	public String getContract_photo() {
		return contract_photo;
	}
	public void setContract_photo(String contract_photo) {
		this.contract_photo = contract_photo;
	}
}
