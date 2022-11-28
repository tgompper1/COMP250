package assignment2;
 
import java.math.BigInteger;
import java.util.Iterator;
 
public class Polynomial implements DeepClone<Polynomial>
{
 private SLinkedList<Term> polynomial;
 public int size()
 { 
  return polynomial.size();
 }
 private Polynomial(SLinkedList<Term> p)
 {
  polynomial = p;
 }
 
 public Polynomial()
 {
  polynomial = new SLinkedList<Term>();
 }
 
 // Returns a deep copy of the object.
 public Polynomial deepClone()
 { 
  return new Polynomial(polynomial.deepClone());
 }
 
 /* 
  * TODO: Add new term to the polynomial. Also ensure the polynomial is
  * in decreasing order of exponent.
  */
 public void addTerm(Term t) //O(n)
 { 
	 if(t.getCoefficient().compareTo(new BigInteger("0"))==0){
		 return;
	 }
	 if(t.getExponent() < 0) {
		 return;
	 }
	 if(polynomial.size() == 0) {
		 polynomial.add(0,t);
		 return;
	 }
	 int termIndex = 0;
	 for(Term currentTerm : polynomial) { //O(n)
		 if(t.getExponent() > currentTerm.getExponent()) {
			 polynomial.add(termIndex, t);
			 return;
		 }
		 else if(t.getExponent() == currentTerm.getExponent()) { 
			 if(currentTerm.getCoefficient().add(t.getCoefficient()).compareTo(new BigInteger("0")) == 0) {
				 polynomial.remove(termIndex);
				 return;
			 }
			 else {
				 currentTerm.setCoefficient(currentTerm.getCoefficient().add(t.getCoefficient()));
				 return;
			 }
		 }
		 else if(termIndex == polynomial.size()-1) {
			 polynomial.add(termIndex+1,t);
			 return;
		 }
		 termIndex++;
	 }
  
  // Hint: Notice that the function SLinkedList.get(index) method is O(n), 
  // so if this method were to call the get(index) 
  // method n times then the method would be O(n^2).
  // Instead, use a Java enhanced for loop to iterate through 
  // the terms of an SLinkedList.
  /*
  for (Term currentTerm: polynomial)
  {
   // The for loop iterates over each term in the polynomial!!
   // Example: System.out.println(currentTerm.getExponent()) should print the exponents of each term in the polynomial when it is not empty.  
  }
  */
 }
 
 public Term getTerm(int index)
 {
  return polynomial.get(index);
 }
 
 //TODO: Add two polynomial without modifying either
 public static Polynomial add(Polynomial p1, Polynomial p2) //O(n1 +n2)
 {
	 Polynomial addedPoly = new Polynomial();
	 if(p2.polynomial.size() == 0 || p2 == null || p2.polynomial == null) {
		 if(p1.polynomial.size() == 0 || p1 == null || p1.polynomial == null) {
			 return null;
		 }
		 else{
			 addedPoly = p1.deepClone();
			 return addedPoly;
		 }
	 }
	 if(p1.polynomial.size() == 0 || p1 == null || p1.polynomial == null) {
		 addedPoly = p2.deepClone();
		 return addedPoly;
	 }
	 
	 Iterator<Term> iterator1  = p1.polynomial.iterator();
	 Iterator<Term> iterator2 = p2.polynomial.iterator();
	 Term t1 = iterator1.next();
	 Term t2 = iterator2.next();
	 
	while(true) {
		 if(iterator1.hasNext() == false) {
			 break;
		 }
		 if(iterator2.hasNext() == false) {
			 break;
		 }
		 if(t1.getExponent() > t2.getExponent()) {
			 addedPoly.polynomial.addLast(t1.deepClone());
			 t1 = iterator1.next();
		 }
		 else if(t2.getExponent() > t1.getExponent()) {
			 addedPoly.polynomial.addLast(t2.deepClone());
			 t2 = iterator2.next();
		 }
		 else if(t1.getExponent() == t2.getExponent()) {
			 if(t1.getCoefficient().add(t2.getCoefficient()).compareTo(new BigInteger("0")) == 0) {
				 t1 = iterator1.next();
				 t2 = iterator2.next();
			 }
			 else{
				 Term newT = new Term(t1.getExponent(), new BigInteger("1"));
				 newT.setCoefficient(t1.getCoefficient().add(t2.getCoefficient()));
				 addedPoly.polynomial.addLast(newT);
				 t1 = iterator1.next();
				 t2 = iterator2.next();
			 }
		 }
	 }
	
	if(!iterator1.hasNext() && !iterator2.hasNext()) {
		if(t1.getExponent()>t2.getExponent()) {
			addedPoly.polynomial.addLast(t1.deepClone());
			addedPoly.polynomial.addLast(t2.deepClone());
		}
		else if(t1.getExponent()<t2.getExponent()) {
			addedPoly.polynomial.addLast(t2.deepClone());
			addedPoly.polynomial.addLast(t1.deepClone());
		}
		else if((t1.getCoefficient().add(t2.getCoefficient())).compareTo(new BigInteger("0")) != 0) {
			Term newT = new Term(t1.getExponent(), new BigInteger("1"));
			 newT.setCoefficient(t1.getCoefficient().add(t2.getCoefficient()));
			 addedPoly.polynomial.addLast(newT);
		}
		return addedPoly;
	}
	if(iterator1.hasNext() && !iterator2.hasNext()) {
		 if(t2.getExponent() > t1.getExponent()) {
			 addedPoly.polynomial.addLast(t2.deepClone());
		}
		else if(t2.getExponent()<t1.getExponent()) {
			while(t2.getExponent()<t1.getExponent()) {
				if(iterator1.hasNext()==false) {
					addedPoly.polynomial.addLast(t1.deepClone());
					addedPoly.polynomial.addLast(t2.deepClone());
					return addedPoly;
				}
				else {
					addedPoly.polynomial.addLast(t1.deepClone());
					t1 = iterator1.next();
				}
			}
			if(t2.getExponent() > t1.getExponent()) {
				addedPoly.polynomial.addLast(t2);
			}
			else if(t1.getExponent()==t2.getExponent()) { 
				if((t1.getCoefficient().add(t2.getCoefficient())).compareTo(new BigInteger("0")) != 0) {
					Term newT = new Term(t1.getExponent(), new BigInteger("1"));
					newT.setCoefficient(t1.getCoefficient().add(t2.getCoefficient()));
					addedPoly.polynomial.addLast(newT);
					if(iterator1.hasNext() == false) {
						return addedPoly;
					}
					t1 = iterator1.next();
				}
			}
		}
		else if(t1.getExponent()==t2.getExponent()) { 
			if((t1.getCoefficient().add(t2.getCoefficient())).compareTo(new BigInteger("0")) != 0) {
				Term newT = new Term(t1.getExponent(), new BigInteger("1"));
				newT.setCoefficient(t1.getCoefficient().add(t2.getCoefficient()));
				addedPoly.polynomial.addLast(newT);
				t1 = iterator1.next();
			}
		}
		 while(iterator1.hasNext()) {
			 Term newT = new Term(t1.getExponent(), new BigInteger("1"));
			 newT.setCoefficient(t1.getCoefficient());
			 addedPoly.polynomial.addLast(newT);
			 t1 = iterator1.next();
		}
		 
		if(iterator1.hasNext() == false) {
			 Term newT = new Term(t1.getExponent(), new BigInteger("1"));
			 newT.setCoefficient(t1.getCoefficient());
			 addedPoly.polynomial.addLast(newT);
		 }
	 }
	
	 if(iterator2.hasNext() && !iterator1.hasNext()) {
		 if(t1.getExponent() > t2.getExponent()) {
			 addedPoly.polynomial.addLast(t1.deepClone());
		}
		else if(t1.getExponent()<t2.getExponent()) {
			while(t1.getExponent()<t2.getExponent()) {
				if(iterator2.hasNext()==false) {
					addedPoly.polynomial.addLast(t2.deepClone());
					addedPoly.polynomial.addLast(t1.deepClone());
					return addedPoly;
				}
				else {
					addedPoly.polynomial.addLast(t2.deepClone());
					t2 = iterator2.next();
				}
				if(t1.getExponent() > t2.getExponent()) {
					addedPoly.polynomial.addLast(t1.deepClone());
				}
				else if(t2.getExponent()==t1.getExponent()) { 
					if((t1.getCoefficient().add(t2.getCoefficient())).compareTo(new BigInteger("0")) != 0) {
						Term newT = new Term(t1.getExponent(), new BigInteger("1"));
						newT.setCoefficient(t1.getCoefficient().add(t2.getCoefficient()));
						addedPoly.polynomial.addLast(newT);
						if(iterator2.hasNext() == false) {
							return addedPoly;
						}
						t2 = iterator2.next();
					}
				}
			}
		}
		else if((t1.getCoefficient().add(t2.getCoefficient())).compareTo(new BigInteger("0")) != 0) {
			Term newT = new Term(t1.getExponent(), new BigInteger("1"));
			 newT.setCoefficient(t1.getCoefficient().add(t2.getCoefficient()));
			 addedPoly.polynomial.addLast(newT);
			 t2 = iterator2.next();
		}
		 while(iterator2.hasNext()) {
			 Term newT = new Term(t2.getExponent(), new BigInteger("1"));
			 newT.setCoefficient(t2.getCoefficient());
			 addedPoly.polynomial.addLast(newT);
			 t2 = iterator2.next();
			 }
		 
		 if(iterator2.hasNext() == false) {
			 Term newT = new Term(t2.getExponent(), new BigInteger("1"));
			 newT.setCoefficient(t2.getCoefficient());
			 addedPoly.polynomial.addLast(newT);
		 }
	 }
	 return addedPoly;
 }
 
 //TODO: multiply this polynomial by a given term.
 public void multiplyTerm(Term t) //O(n)
 { 
	 if(t.getCoefficient().compareTo( new BigInteger("0"))==0) {
		 int i = this.polynomial.size()-1;
		 for(Term currentTerm : this.polynomial) {	//O(<n)
			 this.polynomial.remove(i);
			 i--;
		 }
		 return;
	 }
	 for(Term currentTerm : this.polynomial) { //O(n)
		currentTerm.setCoefficient(currentTerm.getCoefficient().multiply(t.getCoefficient()));
		 currentTerm.setExponent(currentTerm.getExponent()+t.getExponent());
	 } 
 }
 
 //TODO: multiply two polynomials
 public static Polynomial multiply(Polynomial p1, Polynomial p2)
 {
	 Polynomial newPoly = new Polynomial();
	 Term[] terms = new Term[p1.polynomial.get(0).getExponent()+p2.polynomial.get(0).getExponent()+1];
	 int i = 0;
	 for(Term t1: p1.polynomial) {
		 for(Term t2 : p2.polynomial) {
			 Term newT = new Term(t1.getExponent()+t2.getExponent(), new BigInteger("1"));
			 newT.setCoefficient(t1.getCoefficient().multiply(t2.getCoefficient()));
			 if(terms[newT.getExponent()] != null) {
				 Term newT2 =  new Term(newT.getExponent(), new BigInteger("1"));
				 newT2.setCoefficient(newT.getCoefficient().add(terms[newT.getExponent()].getCoefficient()));
				 terms[newT.getExponent()] = newT2;
			 }
			 else{
				 terms[newT.getExponent()] = newT;
			 }
		 }
	 }
	 for(Term t : terms) {//O(n1*n2)
		 if(t == null) {
			 continue;
		 }
		 else newPoly.polynomial.addFirst(t);
	 } 
	 return newPoly;
 }
 
 // TODO: evaluate this polynomial.
 // Hint:  The time complexity of eval() must be order O(m), 
 // where m is the largest degree of the polynomial. Notice 
 // that the function SLinkedList.get(index) method is O(m), 
 // so if your eval() method were to call the get(index) 
 // method m times then your eval method would be O(m^2).
 // Instead, use a Java enhanced for loop to iterate through 
 // the terms of an SLinkedList.
 
 public BigInteger eval(BigInteger x) //O(n)
 {
	 int lastExp = this.polynomial.get(0).getExponent()+1;
	 BigInteger answer = new BigInteger("0");
	 for(Term t : this.polynomial) {	//O(n)
		  if(t.getExponent() == 0) {
			answer = answer.add(t.getCoefficient());
			return answer;
		 }
		 else if(t.getExponent() == lastExp-1) {
			 answer = answer.add(t.getCoefficient());
			 answer = (answer.multiply(x));
			 lastExp = t.getExponent();
		 }
		 else {
			 int rx2 = lastExp - t.getExponent() - 1;
			 answer = answer.multiply(x.pow(rx2));
			 answer = answer.add(t.getCoefficient());
			 answer = (answer.multiply(x));
			 lastExp = t.getExponent();
		 }
	 }
	 if(lastExp != 0) {
		 int exp = lastExp -1;
		 answer = answer.multiply(x.pow(exp));
	 }
	 return answer;
 }
 
 // Checks if this polynomial is a clone of the input polynomial
 public boolean isDeepClone(Polynomial p)
 { 
  if (p == null || polynomial == null || p.polynomial == null || this.size() != p.size())
   return false;
 
  int index = 0;
  for (Term term0 : polynomial)
  {
   Term term1 = p.getTerm(index);
 
   // making sure that p is a deep clone of this
   if (term0.getExponent() != term1.getExponent() ||
     term0.getCoefficient().compareTo(term1.getCoefficient()) != 0 || term1 == term0)  
    return false;
 
   index++;
  }
  return true;
 }
 
 // This method blindly adds a term to the end of LinkedList polynomial. 
 // Avoid using this method in your implementation as it is only used for testing.
 public void addTermLast(Term t)
 { 
  polynomial.addLast(t);
 }
 
 
 @Override
 public String toString()
 { 
  if (polynomial.size() == 0) return "0";
  return polynomial.toString();
 }
}
