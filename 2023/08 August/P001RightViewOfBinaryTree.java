/*
P001: Right View of Binary Tree.
Date: 21 Aug 2023
Difficulty: Easy
*/

import java.util.LinkedList; 
import java.util.Queue; 
import java.io.*;
import java.util.*;

class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
}


class P001RightViewOfBinaryTree {
    
    static Node buildTree(String str){
        
        if(str.length()==0 || str.charAt(0)=='N'){
            return null;
        }
        
        String ip[] = str.split(" ");
        // Create the root of the tree
        Node root = new Node(Integer.parseInt(ip[0]));
        // Push the root to the queue
        
        Queue<Node> queue = new LinkedList<>(); 
        
        queue.add(root);
        // Starting from the second element
        
        int i = 1;
        while(queue.size()>0 && i < ip.length) {
            
            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();
                
            // Get the current node's value from the string
            String currVal = ip[i];
                
            // If the left child is not null
            if(!currVal.equals("N")) {
                    
                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }
                
            // For the right child
            i++;
            if(i >= ip.length)
                break;
                
            currVal = ip[i];
                
            // If the right child is not null
            if(!currVal.equals("N")) {
                    
                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));
                    
                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }
        
        return root;
    }
    void inOrder(Node node) {
        if (node == null) {
            return;
        }
 
        inOrder(node.left);
        System.out.print(node.data + " ");
 
        inOrder(node.right);
    }
    
	public static void main (String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String s = br.readLine();
		Node root = buildTree(s);
		Solution tree = new Solution();
		ArrayList<Integer> arr = tree.rightView(root);
		for(int x : arr)
		System.out.print(x +" ");
		System.out.println();

	}
}


class Solution{
    //Function to return list containing elements of right view of binary tree.
    ArrayList<Integer> rightView(Node node) {
        
        ArrayList<Integer> rsv = new ArrayList<Integer>();
        int level = 0;
        recRightView(node,rsv,level);
        
        return rsv;
        
    }
    
	//recursive function to find the right view of binary tree
    public static void recRightView(Node head, ArrayList<Integer> rsv, int level){
        if(head==null)
            return;
        
		//compare level with size of arraylist
		//if equal then store the elemnt into arrayList
        if(level==rsv.size())
            rsv.add(head.data);
        
		//first view the Right Sub Tree
        recRightView(head.right, rsv, level+1);
		
		//then view the Left Sub Tree
        recRightView(head.left, rsv, level+1);
    }
}

/*
Input:
1 2 3 N N 4 6 N 5 N N 7 N

Output:
1 3 6 5 7
*/