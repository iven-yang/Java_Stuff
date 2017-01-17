import java.util.*;
public class Tester {
	public static void main(String[] args){
		/*BinarySearchTree bst=new BinarySearhTree();
		bst.insert('m');
		bst.insert('d');
		bst.insert('s');
		bst.insert('t');
		bst.insert('f');
		bst.insert('z');
		bst.insert('n');
		bst.preOrderPrint(bst.getRoot());*/
		//int pix1=0x00F2A3B5;
		//int pix2=0xC9;
		//System.out.println((pix2 & pix1)>>8);
		//System.out.println((pix1 & 0x00FF0000)>>16);
		//System.out.println(0x00BC00 | 0xFF0000);
		int pix = 0x00F2A3B5;
		int z = (pix & 0x00FF0000) >> 16;
		System.out.println((0x0000ff00 & 0x0045A2D9)>>8); 
	}
	public static void mystery(int x){
		if(x<0)
			return;
		System.out.print(x+" ");
		mystery(x-2);
		System.out.print(x+" ");
	}
}