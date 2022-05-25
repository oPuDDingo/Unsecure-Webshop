package backend.main.java.models;

public class Coupon {	//erledigt

	private int id;
	private String name;
	private double percent;
	private boolean active;

	public Coupon(){}

	public Coupon(int id, String name, double percent, boolean active)
	{
		this.id = id;
		this.name = name;
		this.percent = percent;
		this.active = active;
	}

	public static Coupon getRandomCoupon()
	{
		return getRandomCoupon("DISCOUNT15");
	}

	public static Coupon getRandomCoupon(final String name)
	{
		return new Coupon(2, name, 15, true);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
