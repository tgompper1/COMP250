public class TankyBee extends HoneyBee{
	// fields
	private int attackDamage;
	private int armor;
	
	// constructor
	public TankyBee(Tile position, int attackDamage, int armor) {
		super(position, 30, 3);
		this.attackDamage = attackDamage;
		this.armor = armor;
		this.getPosition().addInsect(this);
	}
	
	// methods
	public boolean takeAction() {
		if(this.getPosition().getNumOfHornets() == 0) {
			return false;
		}
		if(this.getPosition().getNumOfHornets()  > 0) {
			this.getPosition().getHornet().takeDamage(this.attackDamage);
			return true;
		}
		return false;
	}
	
	public void takeDamage(int damage) { 
		//System.out.println(this.armor);
		double multiplier = (double)100/(100+this.armor);
		//System.out.println(multiplier);
		double newDamage = (damage * multiplier); // round toward 0?
		//System.out.println(newDamage);
		int newNewDamage = (int) newDamage;
		//System.out.println(newNewDamage);
		super.takeDamage(newNewDamage);
		//this.getPosition().addInsect(this);
	}
	
	public boolean equals(Object obj) {
		if(super.equals(obj)) {
			TankyBee t = (TankyBee) obj;
			if(this.attackDamage == t.attackDamage && this.armor == t.armor) {
				return true;
			}
			else return false;
		}
		else return false;
	}
	
}
