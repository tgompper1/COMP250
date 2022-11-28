package finalproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry; // You may need it to implement fastSort

public class Sorting {

	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n^2) as it uses bubble sort, where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable> ArrayList<K> slowSort (HashMap<K, V> results) {
        ArrayList<K> sortedUrls = new ArrayList<K>();
        sortedUrls.addAll(results.keySet());	//Start with unsorted list of urls

        int N = sortedUrls.size();
        for(int i=0; i<N-1; i++){
			for(int j=0; j<N-i-1; j++){
				if(results.get(sortedUrls.get(j)).compareTo(results.get(sortedUrls.get(j+1))) <0){
					K temp = sortedUrls.get(j);
					sortedUrls.set(j, sortedUrls.get(j+1));
					sortedUrls.set(j+1, temp);					
				}
			}
        }
        return sortedUrls;                    
    }
    
    
	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n*log(n)), where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable> ArrayList<K> fastSort(HashMap<K, V> results) {
    	if(results.isEmpty()) {
    		return null;
    	}
    	ArrayList<K> sortedUrls = new ArrayList<K>();
        sortedUrls.addAll(results.keySet());	//Start with unsorted list of urls
        sortedUrls = mergeSort(sortedUrls, results);
    	return sortedUrls;
    }
    
    private static <K, V extends Comparable> ArrayList<K> mergeSort(ArrayList<K> arr, HashMap<K, V> results) {
    	int mid;
    	ArrayList<K> list1 = new ArrayList<K>();
    	ArrayList<K> list2 = new ArrayList<K>();
    	if(arr.size() == 1) {
    		return arr;
    	}
    	else {
    		mid = (arr.size()-1)/2;
    		for(int i= 0; i < mid+1; i++) {
    			list1.add(arr.get(i));
    		}
    		for(int i = mid+1; i < arr.size(); i++) {
    			list2.add(arr.get(i));
    		}
    		list1 = mergeSort(list1, results);
    		list2 = mergeSort(list2, results);
    		return merge(list1, list2, results);
    	}
    }
    
    private static <K, V extends Comparable> ArrayList<K> merge(ArrayList<K> list1, ArrayList<K> list2, HashMap<K, V> results){
    	ArrayList<K> list = new ArrayList<K>();
    	int l1 = 0;
    	int l2 = 0;
    	while(l1 < list1.size() && l2 < list2.size()) {
    		if(results.get(list1.get(l1)).compareTo(results.get(list2.get(l2))) >   0) {
    			list.add(list1.get(l1));
    			l1++;
    		}
    		else {
    			list.add(list2.get(l2));
    			l2++;
    		}
    	}
    	while(l2 < list2.size()) {
    		list.add(list2.get(l2));
    		l2++;
    	}
    	while(l1 < list1.size()) {
    		list.add(list1.get(l1));
    		l1++;
    	}
    	return list;
    }
}
package finalproject;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry; // You may need it to implement fastSort
 
public class Sorting {
 
	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n^2) as it uses bubble sort, where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable> ArrayList<K> slowSort (HashMap<K, V> results) {
        ArrayList<K> sortedUrls = new ArrayList<K>();
        sortedUrls.addAll(results.keySet());	//Start with unsorted list of urls
 
        int N = sortedUrls.size();
        for(int i=0; i<N-1; i++){
			for(int j=0; j<N-i-1; j++){
				if(results.get(sortedUrls.get(j)).compareTo(results.get(sortedUrls.get(j+1))) <0){
					K temp = sortedUrls.get(j);
					sortedUrls.set(j, sortedUrls.get(j+1));
					sortedUrls.set(j+1, temp);					
				}
			}
        }
        return sortedUrls;                    
    }
    
    
	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n*log(n)), where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable> ArrayList<K> fastSort(HashMap<K, V> results) {
    	if(results.isEmpty()) {
    		return null;
    	}
    	ArrayList<K> sortedUrls = new ArrayList<K>();
        sortedUrls.addAll(results.keySet());	//Start with unsorted list of urls
        sortedUrls = mergeSort(sortedUrls, results);
    	return sortedUrls;
    }
    
    private static <K, V extends Comparable> ArrayList<K> mergeSort(ArrayList<K> arr, HashMap<K, V> results) {
    	int mid;
    	ArrayList<K> list1 = new ArrayList<K>();
    	ArrayList<K> list2 = new ArrayList<K>();
    	if(arr.size() == 1) {
    		return arr;
    	}
    	else {
    		mid = (arr.size()-1)/2;
    		for(int i= 0; i < mid+1; i++) {
    			list1.add(arr.get(i));
    		}
    		for(int i = mid+1; i < arr.size(); i++) {
    			list2.add(arr.get(i));
    		}
    		list1 = mergeSort(list1, results);
    		list2 = mergeSort(list2, results);
    		return merge(list1, list2, results);
    	}
    }
    
    private static <K, V extends Comparable> ArrayList<K> merge(ArrayList<K> list1, ArrayList<K> list2, HashMap<K, V> results){
    	ArrayList<K> list = new ArrayList<K>();
    	int l1 = 0;
    	int l2 = 0;
    	while(l1 < list1.size() && l2 < list2.size()) {
    		if(results.get(list1.get(l1)).compareTo(results.get(list2.get(l2))) >   0) {
    			list.add(list1.get(l1));
    			l1++;
    		}
    		else {
    			list.add(list2.get(l2));
    			l2++;
    		}
    	}
    	while(l2 < list2.size()) {
    		list.add(list2.get(l2));
    		l2++;
    	}
    	while(l1 < list1.size()) {
    		list.add(list1.get(l1));
    		l1++;
    	}
    	return list;
    }
}
