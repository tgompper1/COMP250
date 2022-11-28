public class SwarmOfHornets {
	// fields
	private Hornet[] hornets;
	private int swarmSize;
	
	// constructor
	public SwarmOfHornets() {
		hornets = new Hornet[0];
		this.swarmSize = 0;
	}
	
	// methods
	public int sizeOfSwarm() {
		return this.swarmSize;
	}
	
	public Hornet[] getHornets() {
		return this.hornets;
	}
	
	public Hornet getFirstHornet() {
		if(this.swarmSize == 0) {
			return null;
		}
		return this.hornets[0];
	}
	
	public void addHornet(Hornet hornet) {
		Hornet[] newHornets = new Hornet[hornets.length+1];
		
		for(int i = 0; i < hornets.length; i++) {
			newHornets[i] = hornets[i];
		}
		this.hornets = newHornets;
		hornets[hornets.length - 1] = hornet;
		this.swarmSize++;
	}
	
	public boolean removeHornet(Hornet hornet) {
		for(int i = 0; i < this.hornets.length; i++) {
			if(this.hornets[i]==(hornet)) {
				this.hornets[i] = null;
				this.hornets = removeEmpties(this.hornets);
				this.swarmSize--;
				return true;
			}
		}
		return false;
	}
	
	private Hornet[] removeEmpties(Hornet[] currenthornets) {
		Hornet[] newHornets = new Hornet[currenthornets.length-1];
		for(int i = 0; i < currenthornets.length -1; i++) {
			if(currenthornets[i] == null) {
				for(int j = i; j < currenthornets.length - 1; j++) {
					newHornets[j] = currenthornets[j];
				}
				break;
			}
			newHornets[i] = currenthornets[i];
		}
		return newHornets;
	}
}
 
public class SwarmOfHornets {
	// fields
	private Hornet[] hornets;
	private int swarmSize;
	
	// constructor
	public SwarmOfHornets() {
		hornets = new Hornet[0];
		this.swarmSize = 0;
	}
	
	// methods
	public int sizeOfSwarm() {
		return this.swarmSize;
	}
	
	public Hornet[] getHornets() {
		return this.hornets;
	}
	
	public Hornet getFirstHornet() {
		if(this.swarmSize == 0) {
			return null;
		}
		return this.hornets[0];
	}
	
	public void addHornet(Hornet hornet) {
		Hornet[] newHornets = new Hornet[hornets.length+1];
		
		for(int i = 0; i < hornets.length; i++) {
			newHornets[i] = hornets[i];
		}
		this.hornets = newHornets;
		hornets[hornets.length - 1] = hornet;
		this.swarmSize++;
	}
	
	public boolean removeHornet(Hornet hornet) {
		for(int i = 0; i < this.hornets.length; i++) {
			if(this.hornets[i]==(hornet)) {
				this.hornets[i] = null;
				this.hornets = removeEmpties(this.hornets);
				this.swarmSize--;
				return true;
			}
		}
		return false;
	}
	
	private Hornet[] removeEmpties(Hornet[] currenthornets) {
		Hornet[] newHornets = new Hornet[currenthornets.length-1];
		for(int i = 0; i < currenthornets.length -1; i++) {
			if(currenthornets[i] == null) {
				for(int j = i; j < currenthornets.length - 1; j++) {
					newHornets[j] = currenthornets[j];
				}
				break;
			}
			newHornets[i] = currenthornets[i];
		}
		return newHornets;
	}
}
