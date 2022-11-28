public class StingyBee extends HoneyBee {
	// fields
	private int attackDamage;
	
	// constructor
	public StingyBee(Tile position, int attackDamage) {
		super(position, 10, 1);
		this.attackDamage = attackDamage;
		this.getPosition().addInsect(this);
	}
	
	// methods
	public boolean takeAction() { 
		if(this.getPosition().isOnThePath() == false && this.getPosition().isHive() == false) {
			return false;
		}
		Tile checkTile = this.getPosition();
		//System.out.println(checkTile.isNest());
		while(checkTile.isNest() == false) {
			//System.out.println(checkTile.isNest());
			if(checkTile.getNumOfHornets() > 0) {
				checkTile.getHornet().takeDamage(this.attackDamage);
				return true;
			}
			//System.out.println(checkTile.isNest() + " "+ checkTile.towardTheNest());
			checkTile = checkTile.towardTheNest();
			//System.out.println(checkTile.isNest());
		}
		//System.out.println(checkTile.isNest());
		return false;
	}
	
	public boolean equals(Object obj) {
		if(super.equals(obj)) {
			StingyBee s = (StingyBee) obj;
			return (this.attackDamage == s.attackDamage);
		}
		else return false;
	}
	
}
