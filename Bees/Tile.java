public class Tile {
	// fields
	private int foodPresent;
	private boolean beeHiveBuilt;
	private boolean hornetNestBuilt;
	private boolean leadsNestToHive;
	private Tile nextTileOnPathNestToHive;
	private Tile nextTileOnPathHiveToNest;
	private HoneyBee beeOnTile;
	private SwarmOfHornets hornetsOnTile;
	
	// constructors
	public Tile() {
		this.foodPresent = 0;
		this.beeHiveBuilt = false;
		this.hornetNestBuilt = false;
		this.leadsNestToHive = false;
		this.hornetsOnTile = new SwarmOfHornets();
	}
	
	public Tile(int foodPresent, boolean beeHiveBuilt, boolean hornetNestBuilt, 
				boolean leadsNestToHive, Tile nextTileOnPathNestToHive, 
				Tile nextTileOnPathHiveToNest, HoneyBee beeOnTile, SwarmOfHornets hornetsOnTile) {
		this.foodPresent = foodPresent;
		this.beeHiveBuilt = beeHiveBuilt;
		this.hornetNestBuilt = hornetNestBuilt;
		this.leadsNestToHive = leadsNestToHive;
		this.nextTileOnPathNestToHive = nextTileOnPathNestToHive;
		this.nextTileOnPathHiveToNest = nextTileOnPathHiveToNest;
		this.beeOnTile = beeOnTile;
		this.hornetsOnTile = hornetsOnTile;
	}
	
	// methods
	public boolean isHive() {
		return this.beeHiveBuilt;
	}
	
	public boolean isNest() {
		return this.hornetNestBuilt;
	}
	
	public void buildHive() {
		this.beeHiveBuilt = true;
	}
	
	public void buildNest() {
		this.hornetNestBuilt = true;
	}
	
	public boolean isOnThePath() {
		return this.leadsNestToHive;
	}
	
	public Tile towardTheHive() {
		//if(nextTileOnPathNestToHive.isOnThePath()) {
			return this.nextTileOnPathNestToHive;
		//}
		//return null;
	}
	
	public Tile towardTheNest() {
		//if(nextTileOnPathHiveToNest.isOnThePath()) {
			return this.nextTileOnPathHiveToNest;
		//}
		//return null;
	}
	
	public void createPath(Tile toHive, Tile toNest) {
		this.nextTileOnPathNestToHive = toHive;
		this.nextTileOnPathHiveToNest = toNest;
		this.leadsNestToHive = true;
	}
	
	public int collectFood(){
		int food = this.foodPresent;
		this.foodPresent = 0;
		return food;
	}
	
	public void storeFood(int foodReceived) {
		this.foodPresent += foodReceived;
	}
	
	public HoneyBee getBee() {
		return this.beeOnTile;
	}
	
	public Hornet getHornet() {
		return this.hornetsOnTile.getFirstHornet();
	}
	
	public int getNumOfHornets() {
		return hornetsOnTile.sizeOfSwarm();
	}
	
	public boolean addInsect(Insect insect) {
		if(insect instanceof HoneyBee) {
			if(this.beeOnTile == null && this.hornetNestBuilt == false) {
				this.beeOnTile = (HoneyBee) insect;
				insect.setPosition(this);
				return true;
			}
			else return false;
		}
		if(insect instanceof Hornet) {
			if(this.hornetNestBuilt || this.beeHiveBuilt || this.leadsNestToHive) {
				this.hornetsOnTile.addHornet((Hornet) insect);
				insect.setPosition(this);
				return true;
			}
		 	else return false;
		}
		return false;
	}
	
	public boolean removeInsect(Insect insect) {
		if(insect instanceof HoneyBee) {
			if(this.beeOnTile == insect) {
				this.beeOnTile = null;
				insect.setPosition(null);
				return true;
			}
		}
		if(insect instanceof Hornet) {
			this.hornetsOnTile.removeHornet((Hornet) insect);
			if(this.hornetsOnTile.removeHornet((Hornet) insect) == true) {
				insect.setPosition(null);
				return true;
			}
		}
		return false;
	}
}
