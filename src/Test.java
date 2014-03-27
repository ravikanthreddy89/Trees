import java.util.Scanner;


public class Test {

	public static void main(String []args){
		BinaryTree<String, String> bt= new BinaryTree<String, String>();
		
		Scanner input= new Scanner(System.in);
		while(true){
			String[] cmds= input.nextLine().split(" ");
			
			if(cmds[0].equals("quit"))break;
			
			else if(cmds[0].equals("insert")) {
				bt.insert(cmds[1],cmds[2]);
			}
			
			else if(cmds[0].equals("get")){
				if(bt.get(cmds[1])==null) System.out.println("No such key");
				else System.out.println(bt.get(cmds[1]));
			}
			else if(cmds[0].equals("delMin")){
				bt.delMin();
			}
			else if(cmds[0].equals("del")){
				bt.del(cmds[1]);
			}
			else if(cmds[0].equals("dist")){
				System.out.println("Distance : "+bt.getDist(cmds[0]));
			}
		}
		
	}
}
