package cn.tedu.store.entity;
/**
 * 
 * @author tarena
 *
 */
public class OrederItem extends BaseEntity{

	private static final long serialVersionUID = 6868655313874751836L;
	private Integer id ; //'订单数据id',
	private Integer oid;//'归属订单id',
	private Integer pid ;//'商品id',
	private String title ;// '商品标题',
	private String image; //'商品图片',
	private Long price;//'商品单价',
	private Integer num ; //'商品数量',
	@Override
	public String toString() {
		return "OrederItem [id=" + id + ", oid=" + oid + ", pid=" + pid + ", title=" + title + ", image=" + image
				+ ", price=" + price + ", num=" + num + ", toString()=" + super.toString() + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		OrederItem other = (OrederItem) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
