package backend.main.java.models;

public class Coupon {	//erledigt

	private String name;
	private double percent;
	private boolean active;

	public Coupon(){}

	public Coupon(String name, double percent, boolean active)
	{
		this.name = name;
		this.percent = percent;
		this.active = active;
	}

	public static Coupon getExampleCoupon()
	{
		return getExampleCoupon("DISCOUNT15");
	}

	public static Coupon getExampleCoupon(final String name)
	{
		return new Coupon(name, 15, true);
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