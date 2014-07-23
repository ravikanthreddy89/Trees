import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Stack;

/*Some classes for pathSum variant problems*/
class Sum{
	long sum=0;
}

 /*BinarySearchTree, which behaves as a LookUpTable with log(N) look up time(Not a balanced BST implementation).
 * Handles any type of <Key, Value> pairs. 
 * All the methods are implemented for binary search trees with <Key=Integer,Value=String> pairs ,though some methods work for binray trees 
 * (like pathSums, deepest left leaf node, right node and delpaths) */

public class BinaryTree<Key extends Comparable<Key>,Value> implements Iterable<Key> {

	Node root=null;

	/*Method used by clients to insert a <Key,Value> pair into the tree. Similar to hashtable's put(Key,Value).*/
	public void insert(Key key, Value val){
		root=insert(root,key,val);
	}

	/*Given a key "get()" return the value assosciated with that key. Similar to hashtable's get(key)*/
	public Value get(Key key){
		Value ret=null;
		ret=get(root, key);
		return ret;
	}
	
	
	/*Node deletion : Delete a node that contains given key.*/
	public void del(Key key){
		root=del(root, key);
	}
	
	/*Sum of all numbers formed by paths from node to leaf*/
	/*eg      6
	         / \
	        /   \
	       /     \
	      3       5
	     / \       \
  	    2   5       4
  	       / \
  	      7   4
  	      
  	   path       number
  	   
  	   6->3->2     632
  	   6->3->5->7 6357
  	   6->3->5->4 6354
  	   6->5->4     654
  	   
  	   total_sum = 632+6357+6354+654=13997
  	   the method below "pathSums()" returns the total sum explained above.
  	   
  	   ********* NOTE : The code doesn't work if any number contains zeros eg : 10. ******************* 
  	   */
  	   
	public void pathSums(){
		int[] p_sum= new int[100];
		Sum t_sum=new Sum();

		pathSums(root,p_sum,0, t_sum);		
		System.out.println("The total sum:"+t_sum.sum);
	}

	
	/*Prints the deepest left leaf node.*/
	/*eg : For the tree in the above figure deepest left leaf node is "7"*/
	public void deepLeftLeaf(){
		Node result = new Node(-1, null);
		deepLeftLeaf(root, "left", 0, result);
		System.out.println("The deepest left leaf node : "+ result.val);
	}
	
	/*Prints the value of a node right to a given node in that level*/
	/*eg : For the above tree , the right node of 3 is 5 in level two. And for 7 is 4 in last levle. It prints no right
	 * node if there is node right node.*/
	public void rightNode(Key key){
		Value ret= rightNode(root,key);
		if(ret==null) System.out.println("No right node");
		else System.out.println("Right node value :"+ret);
		
	}

	/*Remove all the nodes in the path(from root to any leaf) if the sum of node values along the path <= given k*/
	/*Below link directs you to the page containing problem description.
	 * http://www.geeksforgeeks.org/remove-all-nodes-which-lie-on-a-path-having-sum-less-than-k/
	 * */
	public void delNodesInPath(int sum){
		root=delNodesInPath(root,sum);
	}

	
	/*Prints the levelOrder traversal of the tree*/
	public void levelOrder(){
		levelOrder(root);
	}
	
	   	
	/*Pre-Order iterator to iterate over the keys in the tree.*/
	public Iterator<Key> iterator() {
	   	// TODO Auto-generated method stub
		   preOrderIterator pitr= new preOrderIterator(this.root);
		   
	   	return (Iterator<Key>)pitr;
	}
	
	
	/*This method return lowest common ancestor in BST assuming both keys exist.*/
	public Key lca(Key k1,Key k2){
		return lca(root,k1,k2);
	}
	
	
	/*Method that returns distance between two keys in a binary search tree.Assuming both keys exist.*/
	public int dist(Key k1, Key k2){
		return dist(root,k1,k2);
	}
	
	/*Deletes the minimum node in the tree.*/
	public void delMin(){
		root=delMin(root);
	}
	
	/*Convert the tree into Doubly Linked List & return the head pointer
	 * NOTE : Original tree will be destroyed as this conversion is in-place
	 * And the method "printDLL()" given head pointer to DLL prints it.
	 */
	
	public void convert2DLL(){
		Node x=convert2DLL(this.root);
		printDLL(x);
	}
	
	
	
	public void rotateAlternateLevel(){
		rotateAlternateLevel(root,0);
	}
	
	
	public void printVertically(){
		HashMap<Integer, ArrayList<Key>> map=new HashMap<Integer,ArrayList<Key>>();
		printVertically(root,0,map);
		
		for(int u : map.keySet()){
			for(Key key : map.get(u)){
				System.out.print("-->"+key);
			}
			System.out.println("========================");
		}
	}
	
	
	
	
	
	/*=============================================================*/
	/*Private methods hidden from client that implement the real functionality of above methods.*/
	
	private Node insert(Node x, Key key, Value val){
		if(x==null) x= new Node(key, val);
		//Tree being built is Binary Search Tree.
		int comp= x.key.compareTo(key);
		if(comp>0) x.left=insert(x.left, key, val);
		if(comp<0) x.right=insert(x.right, key, val);
		else if(comp==0) x.val=val;
		return x;
	}

	

	private Value get(Node x, Key key){
		if(x==null) return null;
		int comp=key.compareTo((Key) x.key);
		//follow BST retrieval rules.
		if(comp<0) return get(x.left, key);
		if(comp>0) return get(x.right, key);
		return (Value)x.val;
	}

	
	private Node del(Node x,Key key){
		if(x==null) return null;
		int comp=key.compareTo((Key) x.key);

		if(comp<0) x.left=del(x.left,key);
		if(comp>0) x.right=del(x.right,key);
		if(comp==0) {
			if(x.left==null && x.right==null) return null;
			if(x.right==null) return x.left;
			if(x.left==null) return x.right;
			if(x.left!=null && x.right!=null){
				//find minimum node in right subtree 
				//and replace current node with it.
				x.val=min(x.right);
				x.right=delMin(x.right);	
			}
		}
		return x;
	}
	
	private Value min(Node x){
		if(x.left==null) return (Value) x.val;
		return min(x.left);
	}

	private Node delMin(Node x){
		if(x.left==null && x.right==null) return null;
		else if(x.left==null && x.right!=null) return x.right;
		else if(x.left!=null) x.left=delMin(x.left);
		return x;
	}

	private void pathSums(Node x, int[] p_sum,int level, Sum t_sum){
		if(x==null) return;
		p_sum[level]=(Integer)x.key;
		if(x.right==null && x.left==null){
			//when you hit a leaf node, find the number formed by the path 
			//and add it to total sum
			arrayAdd(p_sum,level, t_sum);
			return;
		}
		pathSums(x.left, p_sum, level+1, t_sum);
		pathSums(x.right,p_sum, level+1, t_sum);

	}

	private void arrayAdd(int[] p_sum, int level, Sum t_sum) {
		//this method extracts number formed by the path from root to leaf and adds to total sum.
		int temp=0;
		for(int i=0;i<=level;i++){
			
			int lTemp=p_sum[i];
			int rlTemp=0;
			
			/*if the number is more than one digit reverse it first*/
			while (lTemp>0) {
			    rlTemp = rlTemp * 10 + lTemp % 10;
			    lTemp = lTemp / 10;   
			}
			while(rlTemp>0){
				temp=temp*10+(rlTemp%10);
				rlTemp=rlTemp/10;
			}
			//temp=temp*10+p_sum[i];
		}
		t_sum.sum+=temp;
	}

	/*The marker used to distinguish each level is an integer, not a generic one*/
	private void levelOrder(Node x){
		ArrayList<Node> q= new ArrayList<Node>();//There is no Queue data structure in java.
		q.add(x);
		q.add(new Node(-1,null));//add a marker to indicate start of new level
		while(!q.isEmpty()){
			Node temp;

			while(true){
				temp=q.remove(0);
				if(temp.key.equals(-1)){//if you hit a marker add a new marker at the end
					if(!q.isEmpty()){
						q.add(new Node(-1,null));
						System.out.println();
						break;
					}
					else {
						break;
					}
				}
				System.out.print(" "+temp.val);
				if(temp.left!=null){
					q.add(temp.left);
				}
				if(temp.right!=null){
					q.add(temp.right);
				}	
			}
		}		
	}
		
	private Value rightNode(Node x,Key key){
		Value ret=null;
		boolean brk=false;
		ArrayList<Node> q= new ArrayList<Node>();
		q.add(x);
		q.add(new Node(-1, null));
		
		while(!q.isEmpty()){
			Node temp;
			while(true){
				temp=q.remove(0);
				int comp= key.compareTo((Key)temp.key);
				if(comp==0) {
					Node right=q.remove(0);
					if(right.key.equals(-1)) {
						ret=null;
					}
					else {
						ret=(Value) right.val;
					}
					brk=true;
					break;
				}				
				if(temp.key.compareTo(-1)==0){
					q.add(new Node(-1,null));
					break;
				}
				if(temp.left!=null){
				q.add(temp.left);
				}
				if(temp.right!=null){
				q.add(temp.right);
				}
			}
			if(brk==true) break;
				
		}
		return ret;
	}
	
		
	private void deepLeftLeaf(Node x, String dir, int level, Node ret){
		
		// dir : to indicate whether left child or right child
		// ret : contains deepest left leaf node untill now
		if(x==null) return;
		if(x.left==null && x.right==null) {
			if(level>(Integer) ret.key && dir.equals("left")){//if the current node is left one and has more depth update return value
				ret.val=x.val;
				return;
			}
		}
		
		deepLeftLeaf(x.left, "left", level+1, ret);
		deepLeftLeaf(x.right,"right", level+1, ret);
	}
	
	private Node delNodesInPath(Node x, int sum){
		if(x==null) return null;
		
		int check_sum = sum-(Integer)x.val;
		if(x.right==null && x.left==null) {
			if(check_sum>0) {
				return null;
			}
			else {
				return x;
			}
		}
		
		x.left=delNodesInPath(x.left, check_sum);
		x.right=delNodesInPath(x.right,check_sum);
		
		
		if(x.right==null && x.left==null ){
			return null;
		}
		
		return x;
	}
	
	/*Logic : Mimic/Simulate the recursion using a stack without recursive calls. */
	private class preOrderIterator<Key> implements Iterator<Key>{

	   private Node root;
	   private Stack<Node> stack;
	   private Node current;
	   
	   preOrderIterator(Node x){
		   this.root=x;
		   this.stack=new Stack<Node>();
		   stack.push(this.root);
	   }
	   @Override
	   public boolean hasNext() {
		// TODO Auto-generated method stub
		if(stack.isEmpty()) return false;
		return true;
	   } 

	   @Override
	   public Key next() {
		// TODO Auto-generated method stub
		if(this.stack.isEmpty()) return null;
		current=stack.pop();
		if(current.right!=null) stack.push(current.right);
		if(current.left!=null) stack.push(current.left);
		return (Key)current.key;
	   }
	  @Override
	  public void remove() {
		// TODO Auto-generated method stub
		
	  }
   }
	
	
	private Key lca(Node x, Key k1, Key k2){
		if(x==null) return null;
		int comp1=x.key.compareTo(k1);
		int comp2=x.key.compareTo(k2);
		
		if(comp1==0 || comp2==0)return (Key)x.key;//if both keys are same and equal to current key
		if(comp1<0 && comp2 <0) return (Key) lca(x.right,k1,k2);//if both keys fall on right side
		if(comp1>0 && comp2>0) return (Key)lca(x.left,k1,k2);//if both keys fall on left side.
		return (Key)x.key;//if both keys fall on either side of current node => current node is LCA.
	}
	  
	
	private int dist(Node x, Key k1,Key k2){
		/*handle three cases
		 * 1. if any key matches with current node's key
		 * 2. if keys are on either side of current node's key
		 * 3. if both keys fall on one side of current node*/
		int ret=-1;
		int comp1=x.key.compareTo(k1);
		int comp2=x.key.compareTo(k2);
		
		/*case 1 : one of the key matches with current node's key*/
		if(comp1==0) 
			return getDist(x,k2);
		if(comp2==0)
			return getDist(x,k1);
				
		/*case 2 : keys fall on same(right or left) side of the current node.*/
		if(comp1<0 && comp2 <0)
			return dist(x.right,k1,k2);
		
		if(comp1>0 && comp2 >0)
			return dist(x.left,k1,k2);
		
		/*case 3 : keys fall on different side of the current node, get the distances of each key and return their sum*/
		return getDist(x,k1)+getDist(x,k2);
	}
	
	/*Given a key this method returns the distance of it from current node and -1 if key is not found*/
	public int getDist(Key k){
		return getDist(root,k);
	}
	
	private int getDist(Node x, Key k){
		int dist=0;
		if(x==null){
			dist=-1;
			return dist;
		}
		int comp=x.key.compareTo(k);
		if(comp==0) {
			dist=0;
			return dist;
		}
		int temp=0;
		if(comp>0)  temp=getDist(x.left,k);
		else temp=getDist(x.right,k);
		if(temp==-1) dist=-1;
		else dist=temp+1;
		
		return dist;
	}
	
	
	private Node convert2DLL(Node x){
		Node head=null;//return this as head pointer to DLL
		Node prev=null;// temp reference to keep track of previously visited node
		
		Node node=x;
		Stack<Node> stack=new Stack<Node>();
		
		while(!stack.isEmpty() || node!=null){
			if(node!=null){
				stack.push(node);
				node=node.left;
			}
			else {
				node=stack.pop();
				if(head==null){
					head=node; //current node is head pointer of resultant DLL
				}
				node.left=prev;
				if(prev!=null) prev.right=node;
				prev=node;
				node=node.right;
			}
		}
		
		return head;
	}
	
	
	private void printDLL(Node x){
		Node temp=x;
		while(temp!=null){
			System.out.println(temp.val+" ");
			temp=temp.right;
		}
	}
	
	// Not working....under construction
	private void rotateAlternateLevel(Node node, int i) {
		// TODO Auto-generated method stub
		
		if(node==null) return;
		if(i%2==0){
			Node temp=node.right;
			node.right=node.left;
			node.left=temp;
			
		}
		
		rotateAlternateLevel(node.right, i+1);
		rotateAlternateLevel(node.left, i+1);
	}
	
	

	//Based on inorder traversal
	private void printVertically(Node node, int offset, HashMap<Integer, ArrayList<Key>> map) {
		// TODO Auto-generated method stub
		//base case
		if(node==null) return;
		
		printVertically(node.left, offset-1, map);
		
		if(map.get(offset)==null) map.put(offset, new ArrayList<Key>());
		map.get(offset).add((Key)node.key);
		
		printVertically(node.right,offset+1,map);
		
		
	}

}
