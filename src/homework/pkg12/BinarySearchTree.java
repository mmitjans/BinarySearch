package homework.pkg12;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class represents a binary search tree. Data can be added into the 
 * tree with a key attached. 
 * 
 * @param <Key> Key type 
 * @param <T> Data type for the value to insert
 */
public class BinarySearchTree<Key extends Comparable<Key>, T> {

    /**
     * This class represent the node of a binary search tree
     *
     */
    class TreeNode {

        // Data to be stored
        private T data;
        // Pointer to the left node
        private TreeNode left;
        // Pointer to the right node
        private TreeNode right;
        private Key key;

        /**
         * Constructor of this class.
         * 
         * @param key value associated with the data
         * @param data data to store
         * @param leftNode left node of the tree
         * @param rightNode right node of the tree
         */
        public TreeNode(Key key, T data, TreeNode leftNode, TreeNode rightNode) {
            this.left = leftNode;
            this.right = rightNode;
            this.data = data;
            this.key = key;
        }

        /**
         * Constructor of this class.
         * @param key value associated with the data
         * @param data data to store
         */
        public TreeNode(Key key, T data) {
            this.data = data;
            this.key = key;
        }

        @Override
        /**
         * Returns a string representation of the node object
         */
        public String toString() {
            return data.toString();
        }
    }

    // Root node element of the tree.
    private TreeNode root;
    // Comparator object to perform comparison
    private Comparator<Key> comparator;

    /**
     * Default constructor of this class. Comparator object that is use
     * to compare values will be the one that implements the type.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * This constructor will use the comparator to compare the key objects
     * inserted into this tree.
     * @param comparator object use to compare
     */
    public BinarySearchTree(Comparator<Key> comparator) {
        this.comparator = comparator;
        this.root = null;
    }

    /**
     * This method inserts the data element with the specified key into the
     * tree.
     * @param key object key
     * @param data data to store in the tree
     */
    public void insert(Key key, T data) {
        this.root = insertData(this.root, data, key);
    }

    /**
     * This method deletes the data that was inserted by the key specified.
     * @param key key object of the data element that will be deleted
     */
    public void delete(Key key)
    {
        this.root = deleteData(key, this.root);
    }
    
    /**
     * This method delete all records between key1 and key2
     * @param key1 lower end of the keys
     * @param key2 high end of the keys
     */
    public void deleteKeys(Key key1, Key key2)
    {
        // Gets a list of all keys within the range
        Iterable<Key> allKeys = keys(key1, key2);
        
        // Iteratively deletes each data record mapped to the key
        for(Key currentKey : allKeys)
        {
            delete(currentKey);
        }
    }
    
    /**
     * This method verifies if the following key is present in the binary
     * tree
     * @param key object to search
     * @return true if the key is present, false otherwise
     */
    public boolean containsKey(Key key) {
        return get(key) != null;
    }

    /**
     * Returns the data record of the key passed in
     * @param key object to search for the data
     * @return data value
     */
    public T get(Key key) {
        return get(root, key);
    }
    
    /**
     * Prints to the console the node data value
     */
    public void printTree() {
        printTree(root);
    }
    
    /**
     * This method returns the minimum value that is present in the tree.
     * @return the minimum value, null when there is no data
     */
    public T getMin()
    {
        TreeNode minNode = findMin(this.root);
        if(minNode != null)
        {
            return minNode.data;
        } else {
            return null;
        }
    }
    
    /**
     * Returns an Iterable object that contains all keys between the lowKey and
     * highKey
     * @param lowKey lower bound of the key's
     * @param highKey higher bound of the key's
     * @return Iterable object will all the keys within the range
     */
    public Iterable<Key> keys(Key lowKey, Key highKey) {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lowKey, highKey);
        return queue;
    }
    
    /**
     * Helper method that compares the first value and second value. If a
     * Comparable object was set it will use this object to perform the
     * comparison
     * @param first object to compare
     * @param second second to object to compare
     * @return comparison result
     */
    private int compareValues(Key first, Key second) {
        if (this.comparator == null) {
            return first.compareTo(second);
        } else {
            return this.comparator.compare(first, second);
        }
    }

    /**
     * This method inserts data to search try with data and key.
     * @param node Node to make comparison
     * @param data data to be inserted
     * @param key key value to insert along with the data
     * @return a new TreeNode object that contains the data and key
     */
    private TreeNode insertData(TreeNode node, T data, Key key) {
        if (node == null) {
            return new TreeNode(key, data);
        } else {
            int comparison = compareValues(key, node.key);

            if (comparison == 0) {
                node.data = data;
            } else if (comparison < 0) {
                node.left = insertData(node.left, data, key);

            } else {
                node.right = insertData(node.right, data, key);

            }
            return node;
        }
    }
    
    /**
     * This methods deletes the data record that the specified key is attached.
     * @param key key value
     * @param node node to make the comparison
     * @return node that is deleted
     */
    private TreeNode deleteData(Key key, TreeNode node)
    {
        if (key == null) {
            return node;
        } else if(node == null)
        {
            throw new RuntimeException("Could not find key to delete");
        }

        int comparisonResult = compareValues(key, node.key);

        if (comparisonResult < 0) 
        {
            node.left = deleteData(key, node.left);
            return node;
        } else if (comparisonResult > 0) {
            node.right = deleteData(key, node.right);
            return node;
        } else if (node.left != null && node.right != null) {
            // Case when the node have two children
            TreeNode minNode = findMin(node.right);
            node.key = minNode.key;
            node.data = minNode.data;
            node.right = deleteData(node.key, node.right);
        } else {
            // If the left child is not null replace the node of
            // parent, else will replace the right node
            node = (node.left != null) ? node.left : node.right;
        }
        
        return node;
    }

    /**
     * This method returns the data that is mapped to the key specified
     * @param node parent node
     * @param key object value that holds the data
     * @return the data mapped to the key
     */
    private T get(TreeNode node, Key key) 
    {
        if (node == null) 
        {
            return null;
        }
        
        int compareResult = compareValues(key, node.key);
        
        if (compareResult < 0) {
            return get(node.left, key);
        } else if (compareResult > 0) {
            return get(node.right, key);
        } else {
            return node.data;
        }
    }

    /**
     * This method returns the node that holds the minimum key value
     * @param treeNode node to search
     * @return the node that contains the minimum key value in the tree node
     */
    private TreeNode findMin(TreeNode treeNode) {
        if (treeNode == null) {
            return null;
        } else if (treeNode.left == null) {
            return treeNode;
        }
        return findMin(treeNode.left);
    }

    /**
     * Prints the values of the nodes in the binary search tree
     * @param node node to print
     */
    private void printTree(TreeNode node) {
        if (node != null) {
            System.out.print(node + " ");
            printTree(node.left);
            printTree(node.right);
        }
    }

    /**
     * This methods adds to the queue all keys that resides between the lowKey
     * and highKey
     * @param node base node
     * @param queue queue that holds the key values
     * @param lowKey low range of the keys
     * @param highKey high range of the keys
     */
    private void keys(TreeNode node, 
                      Queue<Key> queue, 
                      Key lowKey, 
                      Key highKey) 
    { 
        // if no node don't add to the queue
        if (node == null) 
        {
            return;
        } 
        
        // Comparison's
        int comparisonLow = compareValues(lowKey, node.key);
        int comparisonHigh = compareValues(highKey, node.key); 
        
        // Left Node's
        if (comparisonLow < 0) 
        {
            keys(node.left, queue, lowKey, highKey);
        }
        
        // Value within the range, add it to the queue
        if (comparisonLow <= 0 && comparisonHigh >= 0) 
        {
            queue.add(node.key);
        } 
        
        // Right Node's
        if (comparisonHigh > 0) 
        {
            keys(node.right, queue, lowKey, highKey);
        } 
        
    } 
}
