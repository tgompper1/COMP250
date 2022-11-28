package finalproject;
 
import java.util.HashMap;
import java.util.ArrayList;
 
public class SearchEngine {
	public HashMap<String, ArrayList<String> > wordIndex;   // this will contain a set of pairs (String, LinkedList of Strings)	
	public MyWebGraph internet;
	public XmlParser parser;
 
	public SearchEngine(String filename) throws Exception{
		this.wordIndex = new HashMap<String, ArrayList<String>>();
		this.internet = new MyWebGraph();
		this.parser = new XmlParser(filename);
	}
	
	/* 
	 * This does a graph traversal of the web, starting at the given url.
	 * For each new page seen, it updates the wordIndex, the web graph,
	 * and the set of visited vertices.
	 * 
	 * 	This method will fit in about 30-50 lines (or less)
	 */
	public void crawlAndIndex(String url) throws Exception {
		if(internet.getVisited(url) == true) {
			return;
		}
		else {
			internet.addVertex(url);
			internet.setVisited(url, true);
			ArrayList<String> cont  = parser.getContent(url);
			for(int i = 0; i < cont.size(); i++) {
				boolean contains = false;
				if(wordIndex.containsKey(cont.get(i).toLowerCase())) {
					if(wordIndex.get(cont.get(i).toLowerCase()).size() > 0) {
						for(int j = 0; j < wordIndex.get(cont.get(i).toLowerCase()).size(); j++) {
							if(wordIndex.get(cont.get(i).toLowerCase()).get(j) == url) {
								contains = true;
							}
						}
					}
					if(contains == false) {
						wordIndex.get(cont.get(i).toLowerCase()).add(url);
					}
				}
				else {
					ArrayList<String> urls = new ArrayList<String>();
					urls.add(url);
					wordIndex.put(cont.get(i).toLowerCase(), urls);
				}
			}
			ArrayList<String> urls = parser.getLinks(url);
			for(int i = 0; i < urls.size(); i++) {
				crawlAndIndex(urls.get(i));
				internet.addEdge(url, urls.get(i));	
			}
		}
	}
	
	
	
	/* 
	 * This computes the pageRanks for every vertex in the web graph.
	 * It will only be called after the graph has been constructed using
	 * crawlAndIndex(). 
	 * To implement this method, refer to the algorithm described in the 
	 * assignment pdf. 
	 * 
	 * This method will probably fit in about 30 lines.
	 */
	public void assignPageRanks(double epsilon) {
		double d = 0.5;
		ArrayList<String> urls = internet.getVertices();
		int size = urls.size();
		ArrayList<Double> ranks = new ArrayList<Double>(size);
		for(int i = 0; i < size; i++) {
			ranks.add(1.0);
			internet.setPageRank(urls.get(i), 1.0);
		}
		ArrayList<Double> prevranks = new ArrayList<Double>(size);
		boolean finish = true;
		while(finish) {
			for(int i = 0; i < size; i++) {
				prevranks.add(ranks.get(i));
			}
			ranks = computeRanks(urls);
			for(int i = 0; i < urls.size(); i++) {
				internet.setPageRank(urls.get(i), ranks.get(i));
			}
			finish = false;
			for(int j = 0; j < ranks.size(); j++) {
				if(Math.abs(prevranks.get(j) -ranks.get(j)) > epsilon) {
					finish = true;
				}
			}
			for(int i = size-1; i >= 0; i--) {
				prevranks.remove(i);
			}
		}
	}
 
	/*
	 * The method takes as input an ArrayList<String> representing the urls in the web graph 
	 * and returns an ArrayList<double> representing the newly computed ranks for those urls. 
	 * Note that the double in the output list is matched to the url in the input list using 
	 * their position in the list.
	 */
	public ArrayList<Double> computeRanks(ArrayList<String> vertices) {
		
		ArrayList<Double> ranks = new ArrayList<Double>(vertices.size());
	
		for(int i = 0; i < vertices.size(); i++) {
			ArrayList<String> intoV = internet.getEdgesInto(vertices.get(i));
			double x = 0;
			for(int j = 0; j < intoV.size(); j++) {
				x += (internet.getPageRank(intoV.get(j)))/(internet.getOutDegree(intoV.get(j)));
			}
			double pr = (.5)+(.5)*(x);
			ranks.add(pr);
		}
		return ranks;
	}
 
	
	/* Returns a list of urls containing the query, ordered by rank
	 * Returns an empty list if no web site contains the query.
	 * 
	 * This method should take about 25 lines of code.
	 */
	public ArrayList<String> getResults(String query) {
		ArrayList<String> urls = new ArrayList<String>();
		if(wordIndex.containsKey(query.toLowerCase()) == false) {
			return  urls;
		}
		HashMap<String, Double> mappy = new HashMap<String, Double>();
		urls = wordIndex.get(query.toLowerCase());
		for(int i = 0; i < urls.size(); i++) {
			mappy.put(urls.get(i), internet.getPageRank(urls.get(i)));
		}
		urls = Sorting.fastSort(mappy);
		return urls;
	}
}
