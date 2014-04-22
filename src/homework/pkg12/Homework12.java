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
        
        binarySearch.deleteKeys(1, 3);
        
        try {
            binarySearch.delete(0);
        } catch(RuntimeException exception)
        {
            
        }
        
        binarySearch.printTree();
        
    }
    
}
