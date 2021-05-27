
public abstract class Item {

	private String name;
	private int amount;
	private Integer price;
	
	public Item(String name, int amount, Integer price) {
		super();
		this.name = name;
		this.amount = amount;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	

	public Item() {
		// TODO Auto-generated constructor stub
	}

}
