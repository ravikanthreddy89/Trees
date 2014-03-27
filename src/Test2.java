import java.util.Scanner;


public class Test2 {

	public static void main(String []args){
		BinaryTree<Integer, String> bt= new BinaryTree<Integer, String>();
		
		Scanner input= new Scanner(System.in);
		while(true){
			String[] cmds= input.nextLine().split(" ");
			
			if(cmds[0].equals("quit"))break;
			
			else if(cmds[0].equals("insert")) {
				bt.insert(Integer.parseInt(cmds[1]),cmds[2]);
			}
			
			else if(cmds[0].equals("get")){
				if(bt.get(Integer.parseInt(cmds[1]))==null) System.out.println("No such key");
				else System.out.println(bt.get(Integer.parseInt(cmds[1])));
			}
			else if(cmds[0].equals("delMin")){
				bt.delMin();
			}
			else if(cmds[0].equals("del")){
				bt.del(Integer.parseInt(cmds[1]));
			}
			else if(cmds[0].equals("dist")){
				System.out.println("Distance : "+bt.getDist(Integer.parseInt(cmds[1])));
			}
			else if(cmds[0].equals("dist2")){
				System.out.println("Distance b/w nodes : "+bt.dist(Integer.parseInt(cmds[1]), Integer.parseInt(cmds[2])));
			}
			else if(cmds[0].equals("lca")){
				System.out.println("Common ancestor :"+bt.lca(Integer.parseInt(cmds[1]), Integer.parseInt(cmds[2])));
			}
		}
		
	}
}
