public abstract class HoneyBee extends Insect {
	// fields
	private int costInFood;
	
	// constructors
	public HoneyBee(Tile position, int hp, int costInFood) {
		super(position, hp);
		this.costInFood = costInFood;
	}
	
	// methods
	public int getCost() {
		return this.costInFood;
	}
	
	public boolean equals(Object obj) {
		if(super.equals(obj)) {
			HoneyBee h = (HoneyBee) obj;
			return (this.costInFood == h.costInFood);
		}
		else return false;
	}
}
 
public abstract class HoneyBee extends Insect {
	// fields
	private int costInFood;
	
	// constructors
	public HoneyBee(Tile position, int hp, int costInFood) {
		super(position, hp);
		this.costInFood = costInFood;
	}
	
	// methods
	public int getCost() {
		return this.costInFood;
	}
