package cn.tedu.store.entity;
/**
 * 产品类别 实体类
 * @author tarena
 *
 */
public class ProductCategory extends BaseEntity {
	
	private static final long serialVersionUID = -69046459708085589L;
	
	private Integer id;	//主键
	private Long parentId;//父分类id	
	private String name;	//名称
	private Integer status;	//状态   1：正常   0：删除
	private Integer sortOrder;	//排序号	
	private Integer isParent;	//是否是父分类   1：是  0：否	

	 
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Long getParentId() {
		return parentId;
	}


	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getSortOrder() {
		return sortOrder;
	}


	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}


	public Integer getIsParent() {
		return isParent;
	}


	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}


	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", parentId=" + parentId + ", name=" + name + ", status=" + status
				+ ", sortOrder=" + sortOrder + ", isParent=" + isParent + ", toString()=" + super.toString() + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductCategory other = (ProductCategory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
