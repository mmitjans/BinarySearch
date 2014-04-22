/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework.pkg12;

public class Homework12 
{

    public static void main(String[] args) 
    {
        BinarySearchTree<Integer, Integer> binarySearch = 
                new BinarySearchTree<>();
        
        binarySearch.insert(3, 34);
        binarySearch.insert(4, 5);
        binarySearch.insert(9, 22);
        binarySearch.insert(1, 11);
        
        //binarySearch.printTree();
        
        binarySearch.delete(3);
        
        binarySearch.printTree();
        
        System.out.println("Contains " + binarySearch.containsKey(2));
        System.out.println("Minimum value: " + binarySearch.getMin());
        System.out.println("Get value: " + binarySearch.get(4));
        
        binarySearch.deleteKeys(0, 3);
       
        binarySearch.printTree();
    }
    
}
