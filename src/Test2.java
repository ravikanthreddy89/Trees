import java.util.Scanner;


public class Test2 {

	public static void main(String []args){
		BinaryTree<Integer, String> bt= new BinaryTree<Integer, String>();
		
		Scanner input= new Scanner(System.in);
		System.out.println("List of commands ");
		System.out.println("Please use <Integer,String> Key-value pairs.");
		System.out.println("=====================================================================================");
		System.out.println("insert <key> <Value>  : insert into tree a key-value pair");
		System.out.println("get <key> : get the value associated with key");
		System.out.println("del <key> : del a node with given key");
		System.out.println("delMin : delete the node with minimum key");
		System.out.println("dist <key> : return the distance of a node with given key from root node");
		System.out.println("dist2 <key_1> <key_2> : return the distance between two nodes (nodes must exist)");
		System.out.println("lca <key_1> <key_2> : return the lowest common ancestor of given keys");
		System.out.println("pathSums : returns the sum of numbers fromed by root to leaf paths");
		System.out.println("delPaths : deletes any path where sum of its keys <= given sum");
		System.out.println("rightNode <key> : print right node in the same level of given key");
		System.out.println("deepll : prints deepest left leaf node ");
		System.out.println("levelorder : prints the level order tree traversal");
		System.out.println("quit : quits the execution");
		System.out.println("=====================================================================================");
		
		while(true){
			String[] cmds= input.nextLine().split(" ");
			
			if(cmds[0].equals("quit"))break;
			
			else if(cmds[0].equals("insert")) {
				if(cmds.length!=3){
					System.out.println("Usage : insert <key> <value>");
					continue;
				}
				bt.insert(Integer.parseInt(cmds[1]),cmds[2]);
			}
			
			else if(cmds[0].equals("get")){
				if(cmds.length !=2) {
					System.out.println("Usage : get <Key>");
					continue;
				}
				if(bt.get(Integer.parseInt(cmds[1]))==null) System.out.println("No such key");
				else System.out.println(bt.get(Integer.parseInt(cmds[1])));
			}
			else if(cmds[0].equals("levelorder")){
				bt.levelOrder();
				System.out.println("");
			}
			else if(cmds[0].equals("delMin")){
				if(cmds.length!=1){
					System.out.println("Usage : delMin");
					continue;
				}
				bt.delMin();
			}
			else if(cmds[0].equals("del")){
				if(cmds.length!=2) {
					System.out.println("Usage : del <key>");
					continue;
				}
				bt.del(Integer.parseInt(cmds[1]));
			}
			else if(cmds[0].equals("dist")){
				if(cmds.length!=2){
					System.out.println("Usage : dist <key>");
					continue;
				}
				System.out.println("Distance : "+bt.getDist(Integer.parseInt(cmds[1])));
			}
			else if(cmds[0].equals("dist2")){
				if(cmds.length!=3) {
					System.out.println("Usage : dist2 <key_1>  <key_2>");
					continue;
				}
				System.out.println("Distance b/w nodes : "+bt.dist(Integer.parseInt(cmds[1]), Integer.parseInt(cmds[2])));
			}
			else if(cmds[0].equals("lca")){
				if(cmds.length!=3){
					System.out.println("Usage : lca <key_1> <key_2>");
					continue;
				}
				System.out.println("Common ancestor :"+bt.lca(Integer.parseInt(cmds[1]), Integer.parseInt(cmds[2])));
			}
			else if(cmds[0].equals("pathSums")){
				if(cmds.length!=1){
					System.out.println("Usage : pathSums");
					continue;
				}
				bt.pathSums();
			}
			else if(cmds[0].equals("delPaths")){
				if(cmds.length!=2){
					System.out.println("Usage : delPaths <sum>");
					continue;
				}
				bt.delNodesInPath(Integer.parseInt(cmds[1]));
			}
			else if(cmds[0].equals("rightNode")){
				if(cmds.length!=2){
					System.out.println("Usage : rightNode <key>");
					continue;
				}
				bt.rightNode(Integer.parseInt(cmds[1]));
			}
			else if(cmds[0].equals("deepll")){
				bt.deepLeftLeaf();
			}
			
		}
		
	}
}
