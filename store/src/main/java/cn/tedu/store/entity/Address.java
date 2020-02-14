package cn.tedu.store.entity;
/**
 * 收货地址信息实体类
 * @author tarena
 *
 */
public class Address extends BaseEntity{
	
	private static final long serialVersionUID = -8279969621748996475L;
	
	private Integer aid;//'收货地址id',
	private Integer uid; //'用户id',
	private String receiver; //VARCHAR(20) COMMENT '收货人',
	private String provinceName; //VARCHAR(15) COMMENT '省名称',
	private String provinceCode; //CHAR(6) COMMENT '省代号',
	private String cityName; //VARCHAR(15) COMMENT '市名称',
	private String cityCode; //CHAR(6) COMMENT '市代号',
	private String areaName; //VARCHAR(15) COMMENT '区名称',
	private String areaCode; //CHAR(6) COMMENT '区代号',
	private String zip; //CHAR(6) COMMENT '邮政编码',
	private String address; //VARCHAR(50) COMMENT '详情地址',
	private String phone; //VARCHAR(20) COMMENT '手机',
	private String tel; //VARCHAR(20) COMMENT '固话',
	private String tag; //VARCHAR(20) COMMENT '地址类型',
	private Integer isDefault; //INT(1) COMMENT '是否默认:0-非默认,1-默认',
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aid == null) ? 0 : aid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (aid == null) {
			if (other.aid != null)
				return false;
		} else if (!aid.equals(other.aid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Adderss [aid=" + aid + ", uid=" + uid + ", receiver=" + receiver + ", provinceName=" + provinceName
				+ ", provinceCode=" + provinceCode + ", cityName=" + cityName + ", cityCode=" + cityCode + ", areaName="
				+ areaName + ", areaCode=" + areaCode + ", zip=" + zip + ", address=" + address + ", phone=" + phone
				+ ", tel=" + tel + ", tag=" + tag + ", isDefault=" + isDefault + ", toString()=" + super.toString()
				+ "]";
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
}
