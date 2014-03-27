import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/*Some classes for pathSum variant problems*/
class Sum{
	long sum=0;
}


public class BinaryTree<Key extends Comparable<Key>,Value> implements Iterable<Key> {

	Node root=null;

	public void insert(Key key, Value val){
		root=insert(root,key,val);
	}

	private Node insert(Node x, Key key, Value val){
		if(x==null) x= new Node(key, val);

		int comp= x.key.compareTo(key);
		if(comp>0) x.left=insert(x.left, key, val);
		if(comp<0) x.right=insert(x.right, key, val);
		else if(comp==0) x.val=val;
		return x;
	}

	public Value get(Key key){
		Value ret=null;
		ret=get(root, key);
		return ret;
	}

	private Value get(Node x, Key key){
		if(x==null) return null;
		int comp=key.compareTo((Key) x.key);

		if(comp<0) return get(x.left, key);
		if(comp>0) return get(x.right, key);
		return (Value)x.val;
	}

	/*Node deletion*/
	public void del(Key key){
		root=del(root, key);
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
				x.val=min(x.right);
				x.right=delMin(x.right);	
			}

		}
		return x;
	}

	public void delMin(){
		root=delMin(root);
	}

	private Node delMin(Node x){
		if(x.left==null && x.right==null) return null;
		else if(x.left==null && x.right!=null) return x.right;
		else if(x.left!=null) x.left=delMin(x.left);
		return x;
	}

	private Value min(Node x){
		if(x.left==null) return (Value) x.val;
		return min(x.left);
	}

	/*Sum of all the nodes in a given binary tree*/
	public void pathSums(){
		int[] p_sum= new int[100];
		Sum t_sum=new Sum();

		pathSums(root,p_sum,0, t_sum);		
		System.out.println("The total sum:"+t_sum.sum);
	}

	private void pathSums(Node x, int[] p_sum,int level, Sum t_sum){
		if(x==null) return;
		p_sum[level]=(Integer)x.val;
		if(x.right==null && x.left==null){
			arrayAdd(p_sum,level, t_sum);
			return;
		}
		pathSums(x.left, p_sum, level+1, t_sum);
		pathSums(x.right,p_sum, level+1, t_sum);

	}

	private void arrayAdd(int[] p_sum, int level, Sum t_sum) {
		// TODO Auto-generated method stub
		int temp=0;
		for(int i=0;i<=level;i++){
			temp=temp*10+p_sum[i];
		}
		t_sum.sum+=temp;
	}

	public void levelOrder(){
		levelOrder(root);
	}

	/*The marker used to distinguish each level is an integer, not a generic one*/
	private void levelOrder(Node x){
		ArrayList<Node> q= new ArrayList<Node>();
		q.add(x);
		q.add(new Node(-1,null));
		while(!q.isEmpty()){
			Node temp;

			while(true){
				temp=q.remove(0);
				if(temp.key.equals(-1)){
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
	
	/*Prints the value of a node right to a given node in that level*/
	
	public void rightNode(Key key){
		Value ret= rightNode(root,key);
		if(ret==null) System.out.println("No right node");
		else System.out.println("Right node value :"+ret);
		
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
	
	/*Deepest Left leaf node*/
	
	public void deepLeftLeaf(){
		Node result = new Node(-1, null);
		deepLeftLeaf(root, "left", 0, result);
		System.out.println("The deepest left leaf node : "+ result.val);
	}
	
	private void deepLeftLeaf(Node x, String dir, int level, Node ret){
		
		if(x==null) return;
		if(x.left==null && x.right==null) {
			if(level>(Integer) ret.key && dir.equals("left")){
				ret.val=x.val;
				return;
			}
		}
		
		deepLeftLeaf(x.left, "left", level+1, ret);
		deepLeftLeaf(x.right,"right", level+1, ret);
	}
	
	
	
	/*Remove all the nodes in the path if the sum <= given k*/
	
	public void delNodesInPath(int sum){
		root=delNodesInPath(root,sum);
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
	
	public Iterator<Key> iterator() {
   	// TODO Auto-generated method stub
	   preOrderIterator pitr= new preOrderIterator(this.root);
	   
   	return (Iterator<Key>)pitr;
   }
	
	
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
	
	
   /*Method that returns distance between two keys in a binary search tree.Assuming both keys exist.*/
	int dist(Key k1, Key k2){
		return dist(root,k1,k2);
	}
	
	int dist(Node x, Key k1,Key k2){
		/*handle three cases
		 * 1. if any key matches with current node's key
		 * 2. if keys are on either side of current node's key
		 * 3. if both keys fall on one side of current node*/
		int ret=-1;
		int comp1=x.key.compareTo(k1);
		int comp2=x.key.compareTo(k2);
		
		if(comp1==0) 
			return getDist(x,k2);
		if(comp2==0)
			return getDist(x,k1);
				
		if(comp1<0 && comp2 <0)
			return dist(x.right,k1,k2);
		
		if(comp1>0 && comp2 >0)
			return dist(x.left,k1,k2);
		return getDist(k1)+getDist(k2);
	}
	
	/*Given a key this method returns the distance of it from current node and -1 if key is not found*/
	int getDist(Key k){
		return getDist(root,k);
	}
	
	int getDist(Node x, Key k){
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
}
