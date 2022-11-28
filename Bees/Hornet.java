public class Hornet extends Insect{
	// fields
	private int attackDamage;
	
	// constructor
	public Hornet(Tile position, int hp, int attackDamage) {
		super(position, hp);
		this.attackDamage = attackDamage;
		this.getPosition().addInsect(this);
	}
	
	// methods
	public boolean takeAction() { // recheck
		//if(this.getPosition().isHive()) {
			//return false;
		//}
		HoneyBee beePresent = this.getPosition().getBee();
		if(beePresent == null && this.getPosition().isHive()) {
			return false;
		}
		else if(beePresent != null) {
			beePresent.takeDamage(this.attackDamage);
			return true;
		}
		else if(beePresent == null){ 
			Tile t = this.getPosition();
			t = t.towardTheHive();
			this.getPosition().removeInsect(this);
			t.addInsect(this);
			return true;
		}
		else return false;
	}
	
	public boolean equals(Object obj) {
		Hornet h = (Hornet) obj;
		if(this.attackDamage == h.attackDamage) {
			return super.equals(obj);
		}
		else return false;
	}
}
