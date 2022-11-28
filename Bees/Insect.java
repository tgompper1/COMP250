public abstract class Insect {
	// fields
	private Tile position;
	private int hp;
	
	// constructor
	public Insect(Tile position, int hp) {
		this.position = position;
		this.hp = hp;
		if(this instanceof HoneyBee) {
			if(this.position.getBee() != null || this.position.isNest()) {
				throw new IllegalArgumentException("bee already on tile or nest is present");
			}
		}
		if(this instanceof Hornet) {
			if(this.position.isNest() == false && this.position.isHive() == false && this.position.isOnThePath()==false) {
				throw new IllegalArgumentException("hornet cant be placed");
			}
		}
	}
	
	// methods
	public final  Tile getPosition() {
		return this.position;
	}
	
	public final int getHealth() {
		return this.hp;
	}
	
	public void setPosition(Tile position) {
		this.position = position;
	}
	
	public void takeDamage(int damage) {
		if(this instanceof HoneyBee && this.position.isHive()){
				double reduced = (double)damage - (double) damage/10;
				int newDamage = (int) reduced; 
				this.hp -= newDamage;
		}
		else{
			this.hp = this.hp - damage;
		}
		if(this.hp <= 0) {
			this.position.removeInsect(this);
		}
		
	}
	
	public abstract boolean takeAction();
		
	public boolean equals(Object obj) {
		if(obj instanceof Hornet && this instanceof Hornet ) {
			Hornet h = (Hornet) obj;
			if(h.getPosition() == this.position && h.getHealth() == this.hp) {
				return true;
			}
			else return false;
		}
		if(obj instanceof BusyBee && this instanceof BusyBee) {
			BusyBee b = (BusyBee) obj;
			if(b.getPosition() == this.position && b.getHealth() == this.hp) {
				return true;
			}
			else return false;
		}
		if(obj instanceof StingyBee && this instanceof StingyBee) {
			StingyBee s = (StingyBee) obj;
			if(s.getPosition() == this.position && s.getHealth() == this.hp) {
				return true;
			}
			else return false;
		}
		if(obj instanceof TankyBee && this instanceof TankyBee) {
			TankyBee t = (TankyBee) obj;
			if(t.getPosition() == this.position && t.getHealth() == this.hp) {
				return true;
			}
			else return false;
		}
		return false;
	}
}
