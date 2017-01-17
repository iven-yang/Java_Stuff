import java.util.*;
public class LinkedList {

	private ListNode first;
	
	public LinkedList(){
		first=null;
	}
	
	public boolean isEmpty(){
		return first==null;
	}
	
	public void addFirst(Object toAdd){
		ListNode second = first;
		first=new ListNode(toAdd, second);
	}
	
	public Object removeFirst(){
		if(isEmpty())
			return null;
		Object vv=first.getValue();
		first=first.getNext();
		return vv;
	}
	
	public int size(){
		int x=0;
		ListNode awesomesauce=first;
		while(awesomesauce!=null){
			awesomesauce=awesomesauce.getNext();
			x++;
		}
		return x;
	}
	
	public boolean contains(Object toFind){
		ListNode sauce=first;
		while(sauce!=null){
			if(sauce.getValue().equals(toFind))
				return true;
			else
				sauce=sauce.getNext();
		}
		
		return false;
	}
	
	public Object get(int index){
		if(size()<=index || index<0)
			throw new NoSuchElementException();
		ListNode current = first;
		for(int i=0; i<index; i++)
			current = current.getNext();
		return current.getValue();
	}
	
	public Object set(int index, Object newobject){
		Object supplydepot= null;
		if(size()<=index || index<0)
			throw new NoSuchElementException();
		ListNode current = first;
		for(int i=0; i< index; i++)
			current =current.getNext();
		supplydepot=current.getValue();
		current.setValue(newobject);
		return supplydepot;
	}
	
	public boolean add(Object toAdd){
		ListNode newandawesome = new ListNode(toAdd, null);
		ListNode nownownow = first;
		if(isEmpty()){
			first = newandawesome;
			return true;
		}
	
		while(nownownow.getNext()!=null)
			nownownow = nownownow.getNext();
		nownownow.setNext(newandawesome);
		return true;
	}
	
	public void add(int index, Object toAdd){
		ListNode pre = first;
		if(index ==0){
			this.addFirst(toAdd);
			return;
		}
		for(int i=0; i<index-1; i++)
			pre = pre.getNext();
		ListNode next = pre.getNext();
		pre.setNext(new ListNode(toAdd, next));
	}
	
	public Object remove(int index){
		ListNode pre=first;
		if(size()<index || index<0)
			throw new NoSuchElementException();
		if(index==0)
			this.removeFirst();
		for(int i=0; i<index-1; i++)
			pre = pre.getNext();
		ListNode DarkTemplar=pre.getNext();
		pre.setNext(DarkTemplar.getNext());
		return DarkTemplar.getValue();
	}
	
	public boolean remove(Object target){
		ListNode pre=null;
		ListNode DarkTemplar=null;
		while(DarkTemplar!=null && !DarkTemplar.getValue().equals(target)){
			pre=DarkTemplar;
			DarkTemplar=DarkTemplar.getNext();
		}
		if(DarkTemplar!=null && pre!=null){
			pre.setNext(DarkTemplar.getNext());
			return true;
		}
		else if(pre==null){
			this.removeFirst();
			return true;
		}
		else
			return false;
	}
	
	public String toString(){
		String ans="";
		ListNode current = first;
		while(current!=null){
			ans+=current.getValue()+" ";
			current=current.getNext();
		}
		return ans;
	}
}