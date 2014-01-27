

public class Tt {

	public static void main(String[] args){
	
		BinaryTree<Integer, Integer> bt= new BinaryTree<Integer, Integer>();
		bt.insert(6,6);
		bt.insert(7,7);
		bt.insert(5,5);
		bt.insert(4,4);
		bt.insert(8,8);
		bt.insert(9,9);
		bt.pathSums();
		bt.levelOrder();
		bt.rightNode(4);		
		}
}
