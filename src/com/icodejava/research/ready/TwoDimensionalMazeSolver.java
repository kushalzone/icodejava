package com.icodejava.research.ready;

import java.util.Stack;

/**
 * 
 * @author Kushal Paudyal
 * Created on: 1/24/2017
 * Last Modified on: 1/24/2017
 * 
 * Simple class to solve the two dimensional maze in Java
 */
public class TwoDimensionalMazeSolver {
	
	public static void main (String args []) {
		
		//2d Array. Borders are defined to be 1 to avoid exceptions. 
		//1 is wall, 0 is unvisited node. 
		//2 is visited node. 99 is target node.
		int [][] maze = 
	        { {1,1,1,1,1,1,1,1,1,1,1,1,1},
	          {1,0,1,0,1,0,1,0,0,0,0,0,1},
	          {1,0,1,0,0,0,1,0,1,1,1,0,1},
	          {1,0,0,0,1,1,1,0,0,0,0,0,1},
	          {1,1,1,0,0,0,0,0,1,1,1,0,1},
	          {1,0,1,0,1,1,1,0,1,0,0,0,1},
	          {1,0,1,0,1,0,0,0,1,1,1,0,1},
	          {1,0,1,0,1,1,1,0,1,0,1,0,1},
	          {1,0,0,0,0,0,0,99,0,0,1,0,1},
	          {1,1,1,1,1,1,1,1,1,1,1,1,1}
	        };
		//This path tracks the flow across the maze
		Stack<Path> path = new Stack<Path>();
		boolean pathExists = doDepthFirstSearch(maze, 1, 1, path);
		
		if(pathExists) {
			printFormattedStack(path); //uses stack operation
		} else {
			System.out.println("Path does not exist to the target from the start point");
		}
		
	}

	
	private static void printFormattedStack(Stack<Path> path) {
		while (!path.isEmpty()) {
			Path object = path.pop();
			System.out.print(object + (path.size() > 0 ? "-->":""));
		}
	}

	public static boolean doDepthFirstSearch(int[][] maze, int x, int y, Stack<Path> pathStack) {

		// Target Found
		if (maze[y][x] == 99) {
			pathStack.push(new Path(x, y));
			return true;
		}

		// Non-visited Node found
		if (maze[y][x] == 0) {
			maze[y][x] = 2;

			// TRY TO GP LEFT
			if (doDepthFirstSearch(maze, x - 1, y, pathStack)) {
				pathStack.push(new Path(x, y));
				return true;
			}

			// TRY TO GO TO RIGHT
			if (doDepthFirstSearch(maze, x + 1, y, pathStack)) {
				pathStack.push(new Path(x, y));
				return true;
			}

			// TRY TO GO UP
			if (doDepthFirstSearch(maze, x, y - 1, pathStack)) {
				pathStack.push(new Path(x, y));
				return true;
			}

			// TRY TO GO DOWN
			if (doDepthFirstSearch(maze, x, y + 1, pathStack)) {
				pathStack.add(new Path(x, y));
				return true;
			}
		}
		return false;
	}
	
}
