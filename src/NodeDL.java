
public class NodeDL<Key extends Comparable<Key>,Value> {

	Key key;
	Value val;
	Node right;
	Node left;
	
	NodeDL(Key key, Value val){
		this.key=key;
		this.val=val;
	}
	
}
