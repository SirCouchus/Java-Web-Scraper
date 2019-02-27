package crawlerPackage;

import java.util.HashSet;
import javax.swing.SwingWorker;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JProgressBar;

public class Spider {
	
	public static JProgressBar progressBar = new JProgressBar();
	public static int urlCount = 50;
	public String word;
	public int instanceCount;
	private static final int MAX_PAGES_TO_SEARCH = 50; //this means it only searches 10 links
	private Set<String> pagesVisited = new HashSet<String>(); //counts the amount of pages visited
	private List<String> pagesToVisit = new LinkedList<String>(); //amount of pages to visit
	
	/*
	 * @param url
	 * 			-starting point of the spider
	 * @param searchWord
	 * 			-the word or string you seek
	*/
	public void search(String url, String searchWord) { //the website's URL and the word you wish to find on the page
		word = searchWord;
		while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH) {
			String currentURL;
			SpiderLeg leg = new SpiderLeg();
			if(this.pagesToVisit.isEmpty()) {
				currentURL = url; 
				this.pagesVisited.add(url); //adds the URL to the pagesVisited List
			}else {
				currentURL = this.nextURL();
			}
			leg.crawl(currentURL); //crawl method called
			boolean success = leg.searchForWord(searchWord);
			if(success) { 
				System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentURL));
				instanceCount++;
				//txtArea.setText(String.format("**Success** Word %s found at %s", searchWord, currentURL));
			}
			this.pagesToVisit.addAll(leg.getLinks());
		}
		System.out.println(String.format("**Done** Visited %s web page(s)", this.pagesVisited.size()));
	}
	
	/*
	 * returns next URL to visit (in order they were found).
	 * this also checks to ensure it doesn't return a URL
	 * that has already been visited
	 * @return
	 */
	private String nextURL() { 
		String nextURL;
		do {
			nextURL = this.pagesToVisit.remove(0);
			urlCount--;
		} while(this.pagesVisited.contains(nextURL));
		this.pagesVisited.add(nextURL); //contains all the pages you've visited
		return nextURL; //displays the URL
	}	
}
