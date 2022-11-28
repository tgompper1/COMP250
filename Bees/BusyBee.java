public class BusyBee extends HoneyBee {
	// constructor
	public BusyBee(Tile position) {
		super(position, 5, 2);
		this.getPosition().addInsect(this);
	}
	
	// methods
	public boolean takeAction() {
		this.getPosition().storeFood(2);
		return true;
	}
}
 
public class BusyBee extends HoneyBee {
	// constructor
	public BusyBee(Tile position) {
		super(position, 5, 2);
		this.getPosition().addInsect(this);
	}
	
	// methods
	public boolean takeAction() {
		this.getPosition().storeFood(2);
		return true;
	}
}
