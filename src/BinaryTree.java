import java.util.ArrayList;

/*Some classes for pathSum variant problems*/
class Sum{
	long sum=0;
}


public class BinaryTree<Key extends Comparable<Key>,Value> {

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
}
