package assignment3;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
 
public class DogShelter implements Iterable<Dog> {
	public DogNode root;
	
	
	public DogShelter(Dog d) {
		this.root = new DogNode(d);
	}
 
	private DogShelter(DogNode dNode) {
		this.root = dNode;
	}
		
 
	// add a dog to the shelter
	public void shelter(Dog d) {
		if (root == null) 
			root = new DogNode(d);
		else
			root = root.shelter(d);
	}
 
	// removes the dog who has been at the shelter the longest
	public Dog adopt() {
		if (root == null)
			return null;
 
		Dog d = root.d;
		root =  root.adopt(d);
		return d;
	}
	
	// overload adopt to remove from the shelter a specific dog
	public void adopt(Dog d) {
		if (root != null)
			root = root.adopt(d);
	}
 
 
	// get the oldest dog in the shelter
	public Dog findOldest() {
		if (root == null)
			return null;
		
		return root.findOldest();
	}
 
	// get the youngest dog in the shelter
	public Dog findYoungest() {
		if (root == null)
			return null;
		
		return root.findYoungest();
	}
	
	// get dog with highest adoption priority with age within the range
	public Dog findDogToAdopt(int minAge, int maxAge) {
		return root.findDogToAdopt(minAge, maxAge);
	}
 
	// Returns the expected vet cost the shelter has to incur in the next numDays days
	public double budgetVetExpenses(int numDays) {
		if (root == null)
			return 0;
		
		return root.budgetVetExpenses(numDays);
	}
	
	// returns a list of list of Dogs. The dogs in the list at index 0 need to see the vet in the next week. 
	// The dogs in the list at index i need to see the vet in i weeks. 
	public ArrayList<ArrayList<Dog>> getVetSchedule() {
		if (root == null)
			return new ArrayList<ArrayList<Dog>>();
			
		return root.getVetSchedule();
	}
 
 
	public Iterator<Dog> iterator() {
		return new DogShelterIterator();
	}
	
	
 
	public class DogNode {
		public Dog d;
		public DogNode younger;
		public DogNode older;
		public DogNode parent;
 
		public DogNode(Dog d) {
			this.d = d;
			this.younger = null;
			this.older = null;
			this.parent = null;
		}
		
		private DogNode addBST(DogNode d) {
			//DogNode newDog = new DogNode(d);
			if(d.d.getAge() < this.d.getAge()) {
				//System.out.println("y");
				if(this.younger == null) {
					this.younger = d;
					d.parent = this;
				}
				else {
					//this.younger = this.younger.addBST(d);
					this.younger.addBST(d);
				}
			}
			else if(d.d.getAge() > this.d.getAge()) {
				//System.out.println("o");
				if(this.older == null) {
					this.older = d;
					d.parent = this;
				}
				else {
					//this.older = this.older.addBST(d);
					this.older.addBST(d);
				}
			}
			//System.out.println("before" +this.younger);
			return d;
		}
		
		public DogNode shelter(Dog d) {
			DogNode newDog = new DogNode(d);
			 newDog = this.addBST(newDog);
			 if(newDog.parent == null) {
				 return this;
			 }
			if(newDog.parent != null && newDog.d.getDaysAtTheShelter() < newDog.parent.d.getDaysAtTheShelter()) {
				return this;
			}
			
			while(newDog.parent != null && newDog.d.getDaysAtTheShelter() > newDog.parent.d.getDaysAtTheShelter()) {
				//right rotation
				if(newDog.parent.younger == newDog) {
					if(newDog.parent.parent != null) {
						if(newDog.parent.parent.younger == newDog.parent) {
							newDog.parent.parent.younger = newDog;
						}
						else if(newDog.parent.parent.older == newDog.parent) {
							newDog.parent.parent.older = newDog;
						}
					}
					DogNode temp  = newDog.parent.parent;
					newDog.parent.parent = newDog;
					newDog.parent.younger = newDog.older;
					if(newDog.older != null) {
						newDog.older.parent = newDog.parent;
					}
					newDog.older = newDog.parent;
					newDog.parent = temp;
				}
				//left rotation
				else if(newDog.parent.older == newDog) {
					if(newDog.parent.parent != null) {
						if(newDog.parent.parent.younger == newDog.parent) {
							newDog.parent.parent.younger = newDog;
						}
						else if(newDog.parent.parent.older == newDog.parent) {
							newDog.parent.parent.older = newDog;
						}
					}
					DogNode temp  = newDog.parent.parent;
					newDog.parent.parent = newDog;
					newDog.parent.older = newDog.younger;
					if(newDog.younger != null) {
						newDog.younger.parent = newDog.parent;
					}
					newDog.younger = newDog.parent;
					newDog.parent = temp;
				}
			}
			if(newDog.parent == null) {
				return newDog;
			}
			else
				return this;
		}
 
		private DogNode removeAdopt(Dog d) {
//			if(this.younger == null && this.older == null) {
//				return this;
//			}
			if(d == null) {
				return null;
			}
			if(this.younger != null && d.getAge() < this.d.getAge()) {
				this.younger = this.younger.removeAdopt(d);
			}
			else if(this.older!= null &&d.getAge() > this.d.getAge()) {
				this.older = this.older.removeAdopt(d);
			}
			else if(d.getAge() == this.d.getAge()) {
				//only node
				if(this.younger == null && this.older == null && this.parent == null) {
					return null;
				}
				//no children
				else if(this.younger == null && this.older == null) {
					if(this.parent.younger == this) {
						this.parent.younger = null;
					}
					else if(this.parent.older == this) {
						this.parent.older = null;
					}
					this.parent = null;
					return null;
				}
				//younger child only
				else if(this.younger != null && this.older == null) {
					DogNode temp = this.younger.younger;
					this.d = this.younger.d;
					this.older = this.younger.older;
					this.younger.younger = null;
					this.younger.older = null;
					this.younger.parent = null;
					this.younger = temp;
				}
				//older child only
				else if(this.older != null && this.younger == null) {
					DogNode temp = this.older.older;
					this.younger = this.older.younger;
					this.d = this.older.d;
					this.older.older = null;
					this.older.younger = null;
					this.older.parent = null;
					this.older = temp;
				}
				//has both children
				else if(this.older != null && this.younger != null) {
					Dog oldest = this.younger.findOldest();
					this.d = oldest;
					this.parent = null;
					this.younger.parent = this;
					this.older.parent = this;
					this.younger = this.younger.removeAdopt(oldest);
				}
			}
			else if(this.younger == null && this.older == null) {
				return this;
			}
			return this;
		}
				
		public DogNode adopt(Dog d) {
			if(this.younger == null && this.older == null && this.parent == null) {
				return null;
			}
			this.removeAdopt(d);
			DogNode curRoot = this;
			
			while((this.younger!= null && this.younger.d.getDaysAtTheShelter()> this.d.getDaysAtTheShelter()) || (this.older != null && this.older.d.getDaysAtTheShelter() > this.d.getDaysAtTheShelter())) {
				if(this.older != null && this.younger != null &&this.younger.d.getDaysAtTheShelter() > this.d.getDaysAtTheShelter() && this.older.d.getDaysAtTheShelter() > this.d.getDaysAtTheShelter()) {
					if(this.younger.d.getDaysAtTheShelter() > this.older.d.getDaysAtTheShelter()) {
						//right rotation
						if(this.parent != null) {
							if(this.parent.younger == this) {
								this.parent.younger = this.younger;
							}
							else if(this.parent.older == this) {
								this.parent.older = this.younger;
							}
						}
						this.younger.parent = this.parent;
						this.parent = this.younger;
						DogNode temp = this.younger.older;
						if(this.younger.older != null) {
							this.younger.older.parent = this;
						}
						this.younger.older = this;
						this.younger = temp;
						if(this.parent.parent ==null) {
							curRoot = this.parent;
						}
					}
					else if(this.older != null && this.younger != null && this.older.d.getDaysAtTheShelter() > this.younger.d.getDaysAtTheShelter()) {
						//left rotation
						if(this.parent != null) {
							if(this.parent.younger == this) {
								this.parent.younger = this.older;
							}
							else if(this.parent.older == this) {
								this.parent.older = this.older;
							}
						}
						this.older.parent = this.parent;
						this.parent = this.older;
						DogNode temp = this.older.younger;
						if(this.older.younger != null) {
							this.older.younger.parent = this;
						}
						this.older.younger = this;
						this.older = temp;
						if(this.parent.parent == null) {
							curRoot = this.parent;
						}
					}
				}
				else if(this.younger != null && this.younger.d.getDaysAtTheShelter() > this.d.getDaysAtTheShelter()) {
					//right rotation
					if(this.parent != null) {
						if(this.parent.younger == this) {
							this.parent.younger = this.younger;
						}
						else if(this.parent.older == this) {
							this.parent.older = this.younger;
						}
					}
					this.younger.parent = this.parent;
					this.parent = this.younger;
					DogNode temp = this.younger.older;
					if(this.younger.older != null) {
						this.younger.older.parent = this;
					}
					this.younger.older = this;
					this.younger = temp;
					if(this.parent.parent == null) {
						curRoot = this.parent;
					}
				}
				else if(this.older != null && this.older.d.getDaysAtTheShelter() > this.d.getDaysAtTheShelter()) {
					//left rotation
					if(this.parent != null) {
						if(this.parent.younger == this) {
							this.parent.younger = this.older;
						}
						else if(this.parent.older == this) {
							this.parent.older = this.older;
						}
					}
					this.older.parent = this.parent;
					this.parent = this.older;
					DogNode temp = this.older.younger;
					if(this.older.younger != null) {
						this.older.younger.parent = this;
					}
					this.older.younger = this;
					this.older = temp;
					if(this.parent.parent == null) {
						curRoot = this.parent;
					}
				}
			}
			//System.out.println(curRoot);
            return curRoot; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}
		
		public Dog findOldest() {
            if(this.older == null) {
            	return this.d;
            }
            else
            	return this.older.findOldest(); // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}
 
		public Dog findYoungest() {
            if(this.younger == null) {
            	return this.d;
            }
            else
            	return this.younger.findYoungest(); // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}
 
		public Dog findDogToAdopt(int minAge, int maxAge) {
			if(minAge > findOldest().getAge() || maxAge < findYoungest().getAge()) {
				return null;
			}
            if(minAge == maxAge) {
            	if(this.d.getAge() == minAge) {
            		return this.d;
            	}
            	else if(this.d.getAge() > minAge && this.younger != null) {
            		return this.younger.findDogToAdopt(minAge, maxAge);
            	}
            	else if(this.d.getAge() < minAge && this.older != null) {
            		return this.older.findDogToAdopt(minAge, maxAge);
            	}
            	else
            		return null;
            }
            else if(this.d.getAge() >= minAge && this.d.getAge() <= maxAge) {
            	return this.d;
            }
            else if(this.d.getAge() < minAge) {
            	return this.older.findDogToAdopt(minAge, maxAge);
            }
            else if(this.d.getAge() > maxAge) {
            	return this.younger.findDogToAdopt(minAge, maxAge);
            }
            return null; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}
		
		public double budgetVetExpenses(int numDays) {
			double budget = 0;
			Iterator<Dog> iter = iterator();
			Dog cur = iter.next();
			while(true) {
				if(cur.getDaysToNextVetAppointment() <= numDays) {
					budget+=cur.getExpectedVetCost();
				}
				if(iter.hasNext()) {
					cur = iter.next();
				}
				else if(iter.hasNext() == false) {
					break;
				}
			}
            return budget; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}
 
		public ArrayList<ArrayList<Dog>> getVetSchedule() {
			ArrayList<ArrayList<Dog>> schedule = new ArrayList<ArrayList<Dog>>();
			Iterator<Dog> iter1 = iterator();
			Dog cur1 = iter1.next();
			Dog cur2 = iter1.next();
			Iterator<Dog> iter3 = iterator();
			Dog cur3 = iter3.next();
			int max = 0;
			while(true) {
				if(iter1.hasNext() == false) {
					break;
				}
				if(cur1.getDaysToNextVetAppointment()/7 > cur2.getDaysToNextVetAppointment()/7) {
					max = cur1.getDaysToNextVetAppointment()/7;
					cur2 = iter1.next();
				}
				else if(cur2.getDaysToNextVetAppointment()/7 > cur1.getDaysToNextVetAppointment()/7) {
					max = cur2.getDaysToNextVetAppointment()/7;
					cur1 = iter1.next();
				}
				else if(cur2.getDaysToNextVetAppointment()/7 == cur1.getDaysToNextVetAppointment()/7) {
					max = cur2.getDaysToNextVetAppointment()/7;
					cur1 = iter1.next();
				}
			}
			max++;
			for(int i = 0; i < max; i++) {
				ArrayList<Dog> d = new ArrayList<Dog>(0);
				schedule.add(d);
			}
			//System.out.println("max"+max);
			//int i = 1;
			while(true) {
				//System.out.println(i++);
				int days = cur3.getDaysToNextVetAppointment();
				int week = days/7;
				//System.out.println("week"+week);
				schedule.get(week).add(cur3);
				if(iter3.hasNext()) {
					cur3 = iter3.next();
				}
				else if(iter3.hasNext() == false){
					break;
				}
			}
            return schedule; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}
		
 
		public String toString() {
			String result = this.d.toString() + "\n";
			if (this.younger != null) {
				result += "younger than " + this.d.toString() + " :\n";
				result += this.younger.toString();
			}
			if (this.older != null) {
				result += "older than " + this.d.toString() + " :\n";
				result += this.older.toString();
			}
			if (this.parent != null) {
				result += "parent of " + this.d.toString() + " :\n";
				result += this.parent.d.toString() +"\n";
			}
			return result;
		}
	}
 
 
	private class DogShelterIterator implements Iterator<Dog> {
		ArrayList<Dog> dogs = new ArrayList<Dog>();
		int i = 0;
		Dog cur;
		
		private DogShelterIterator() {
			inOrderBST(root);
			cur = dogs.get(0);
		}
 
		public Dog next()  throws NoSuchElementException{
			if(i >= dogs.size()) {
				throw new NoSuchElementException("No such element");
			}
			cur = dogs.get(i++);
//			if(i >= dogs.size()) {
//				throw new NoSuchElementException("No such element");
//			}
            return cur;
		}
 
		public boolean hasNext() {
			if(i > dogs.size()-1) {
				return false;
			}
            return cur != null; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}
		private void inOrderBST(DogNode root) {
			if(root != null) {
				inOrderBST(root.younger);
				dogs.add(root.d);
				inOrderBST(root.older);
			}
 
		}
	}
}
