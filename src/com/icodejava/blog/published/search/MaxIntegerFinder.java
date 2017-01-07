package com.icodejava.blog.published.search;

import java.util.Arrays;

/**
 * 
 * This class is used to demonstrate 3 different ways of finding a maximum
 * number in an Integer Array holding positive integer numbers.
 * 
 * Also provides a Big O analysis.
 */
public class MaxIntegerFinder {

    public static void main(String args[]) {

        int[] numbers = {5, 4, 9, 2, 2, 1, 10, 21, 6 };
        
        System.out.println("Source Array " + Arrays.toString(numbers));

        findMaxCompareMax(numbers);

        findMaxCompareAll(numbers);

        findMaxCompareAfterIndex(numbers);

    }


    /**
     * This method assumes the first number on the array to be the maximum
     * You can also assume the last one to be the max and compare it in reverse.
     * It then loops through other numbers in the array and replaces the 
     * current maximum with the new maximum it finds.
     * 
     * Complexity: O(n) where n is the number of elements in the array
     */
    private static int findMaxCompareMax(int[] numbers) {

        if (numbers.length < 1 ) {
            return 0;
        }
        
        int currentMax = numbers[0];
        for (int i = 1 ; i < numbers.length; i++) {
            if(numbers[i] > currentMax) {
                currentMax = numbers[i];
            }
        }
        
        System.out.println("Max Number using findMaxCompareMax -> " + currentMax);
        return currentMax;
        
    }
    
    /**
     * This method takes an array element and compare it with 
     * every other element in the array to see if it is maximum.
     * 
     * If it is not, then it proceeds with the next element
     * 
     * Complexity: O(n^2) in worst case and O(n) in best case 
     * where n is the number of elements in the array
     * 
     * Best case scenario is where the first element itself is maximum
     * Worst case scenario is where the last element is maximum.
     */
    private static int findMaxCompareAll(int[] numbers) {

        if (numbers.length < 1) {
            return 0;
        }

        int max = -1;

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (max < numbers[j]) {
                    max = numbers[j];
                    break;
                }
            }
        }
        System.out.println("Max Number using findMaxCompareAll -> " + max);
        return max;

    }
    
    /**
     * This method takes an array element and compare it with 
     * other elements in the array to see if it is maximum.
     * 
     * If it is not, then it proceeds with the next element. 
     * 
     * However, this method does not compare every element from the beginning. 
     * To avoid redundant comparing, it only compares with the remaining elements
     * in the array (i.e. position + 1 onwards)
     * 
     * Complexity: O(n^2) in worst case and O(n) in best case 
     * where n is the number of elements in the array
     * 
     * Best case scenario is where the first element itself is maximum
     * Worst case scenario is where the last element is maximum.
     */
    private static int findMaxCompareAfterIndex(int[] numbers) {

        if (numbers.length < 1 ) {
            return 0;
        }
        
        int max=-1;
        
        for (int i = 0 ; i < numbers.length; i++) {
            for(int j = i ; j< numbers.length; j++) {
                if(max < numbers [j]) {
                    max = numbers[j];
                    break;
                }
            }
        }
        System.out.println("Max Number using findMaxCompareAfterIndex -> " + max);
        return max;
    }

}
