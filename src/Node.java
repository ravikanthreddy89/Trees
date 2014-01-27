
public class Node<Key extends Comparable<Key>, Value> {

	Key key;
	Value val;
	Node right;
	Node left;
		
	Node(Key key, Value val){
		this.key=key;
		this.val=val;
	}
}
