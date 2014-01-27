

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
		BinaryTree<Integer, Integer> bt1= new BinaryTree<Integer, Integer>();
		bt1.insert(13,13);
		bt1.insert(6,6);
		bt1.insert(24, 24);
		bt1.insert(15, 15);
		bt1.insert(14,14);
		bt1.insert(10, 10);
		bt1.insert(4, 4);
		bt1.insert(3,3);
		bt1.insert(5,5);
		bt1.insert(28,28);
		bt1.insert(25,25);
		
		bt1.delNodesInPath(29);
		bt1.levelOrder();
		bt1.deepLeftLeaf();
		
		}
}
