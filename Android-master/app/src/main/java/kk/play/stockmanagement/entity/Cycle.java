package kk.play.stockmanagement.entity;


public class Cycle {
	
	private long id;
	private String type;
	private String compName;
	private String modelName;
	private String image;
	private String description;
	private int quantity;
	private int size;
	private String price;
	private String color;
	private String lastUpdatedDate;
	private String lastUpdatedTime;
	private String synced;
	public String getSynced() {
		return synced;
	}

	public void setSynced(String synced) {
		this.synced = synced;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	

	public Cycle() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Cycle(long id, String type, String compName, String modelName,
			String image, String description, int quantity, int size,
			String price, String color, String lastUpdatedDate,
			String lastUpdatedTime) {
		super();
		this.id = id;
		this.type = type;
		this.compName = compName;
		this.modelName = modelName;
		this.image = image;
		this.description = description;
		this.quantity = quantity;
		this.size = size;
		this.price = price;
		this.color = color;
		this.lastUpdatedDate = lastUpdatedDate;
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long cycleId) {
		this.id = cycleId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price=price;
	}
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	@Override
	public String toString() {
		return "Cycle [id=" + id + ", type=" + type + ", compName=" + compName
				+ ", modelName=" + modelName + ", image=" + image
				+ ", description=" + description + ", quantity=" + quantity
				+ ", size=" + size + ", price=" + price + ", color=" + color
				+ ", lastUpdatedDate=" + lastUpdatedDate + ", lastUpdatedTime="
				+ lastUpdatedTime + ", synced=" + synced + "]";
	}

	
	
	

	

}
