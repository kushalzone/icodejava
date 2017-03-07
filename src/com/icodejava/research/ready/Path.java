package com.icodejava.research.ready;
/**
 * 
 * @author Kushal Paudyal
 * Created on: 1/24/2017
 * Last Modified on: 1/24/2017
 * 
 * Represents a simple co-ordinate.
 */
public class Path {
		int x,y;
		public Path (int x, int y) {
			this.x = x; 
			this.y = y;
		}
		
		@Override
		public String toString() {
			return "("+x + ","+y+")";
		}
	}