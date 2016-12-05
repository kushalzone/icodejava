package com.icodejava.blog.published.maths;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Kushal Paudyal
 * www.icodejava.com
 * 
 * Created on: 02/19/2014
 * Last Modified On: 02/19/2014
 *
 */
public class SetIntersection {
	
	public static void main(String args [] ) {
		/**
		 * Prepare a set of Developers
		 */
		Set<String> developers = new HashSet<String>();
		developers.add("Kushal");
		developers.add("Madan");
		developers.add("Pradip");
		developers.add("Nick");
		
		System.out.println("Developers: " + developers.toString());
		
		/**
		 * Prepare a set of Tech Leads
		 * Note that some of the items added are duplicates.
		 * Set does not allow duplicates which is apparent console print.
		 */
		Set<String> techLeads = new HashSet<String>();
		techLeads.add("Kushal");
		techLeads.add("Kushal"); //set does not allow duplicates
		techLeads.add("Nick");
		techLeads.add("Matthew");
		
		System.out.println("Tech Leads: " + techLeads.toString());
		
		/**
		 * To do set intersection, you can call retainAll() method
		 * and pass another set as parameter.
		 */
		developers.retainAll(techLeads);
		
		System.out.println("Tech Leads who are also developers:" + developers);
		
	}

}
