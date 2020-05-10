package cn.edu.sau.pojo.BO;

public class HouseBO {
	
	private double query_min_rent;       // 最小租金
	private double query_max_rent;       // 最大租金
	private String query_apartment;      // 户型
	private double query_min_area;       // 最小面积
	private double query_max_area;       // 最大面积
	private String query_orientation;    // 朝向
	private String query_residential_quarters;   // 所在小区
	private String query_respective_area;        // 所属区域
	
	public double getQuery_min_rent() {
		return query_min_rent;
	}
	public void setQuery_min_rent(double query_min_rent) {
		this.query_min_rent = query_min_rent;
	}
	public double getQuery_max_rent() {
		return query_max_rent;
	}
	public void setQuery_max_rent(double query_max_rent) {
		this.query_max_rent = query_max_rent;
	}
	public String getQuery_apartment() {
		return query_apartment;
	}
	public void setQuery_apartment(String query_apartment) {
		this.query_apartment = query_apartment;
	}
	public double getQuery_min_area() {
		return query_min_area;
	}
	public void setQuery_min_area(double query_min_area) {
		this.query_min_area = query_min_area;
	}
	public double getQuery_max_area() {
		return query_max_area;
	}
	public void setQuery_max_area(double query_max_area) {
		this.query_max_area = query_max_area;
	}
	public String getQuery_orientation() {
		return query_orientation;
	}
	public void setQuery_orientation(String query_orientation) {
		this.query_orientation = query_orientation;
	}
	public String getQuery_residential_quarters() {
		return query_residential_quarters;
	}
	public void setQuery_residential_quarters(String query_residential_quarters) {
		this.query_residential_quarters = query_residential_quarters;
	}
	public String getQuery_respective_area() {
		return query_respective_area;
	}
	public void setQuery_respective_area(String query_respective_area) {
		this.query_respective_area = query_respective_area;
	}
}