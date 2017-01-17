import java.util.*;
import java.util.LinkedList;
public class Tester {
	public static void main(String[] args) {
		ArrayList<Integer> list=new ArrayList();
		//String[] y={"a","b","c","d","e","f","g"};
		for(int i=1;i<=5;i++)
			list.add(3*i+5);
		for(int j=1;j<=5;j++)
			//if(list.get(j)>20)
				list.add(0,4*j+10);
		for(int i=0;i<list.size();i++)
			if(list.get(i)%2==0)
				list.remove(i);
		for(Integer x:list)
			System.out.print(x);
		
		/*LinkedList<Integer> list=new LinkedList();
		for(int i=2;i<35;i+=3)
			list.add(0,i);
		for(int i=0;i<list.size();i++)
			if(list.get(i)>10)
				list.remove(i);
		for(Integer n:list)
			System.out.print(n);*/
	}
}