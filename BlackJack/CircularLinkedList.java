import java.util.*;


public class CircularLinkedList {
	private ListNode last;

	public CircularLinkedList(){
		last = null;
	}

	public boolean isEmpty(){return last==null;}
	
	public void add(Object c){
		if(isEmpty()){
			last = new ListNode(c,null);
			last.setNext(last);
		}
		else{
			last.setNext(new ListNode(c,last.getNext()));
			last = last.getNext();//new last
		}
	}
	public void addFirst(Object toAdd){
		ListNode second = last;
		last=new ListNode(toAdd, second);
	}
	public int size(){
		 int sz=1;
		 if(isEmpty())
			 return 0;
		ListNode c = last.getNext();
		while(c!=last){
			 sz++;
			 c=c.getNext();
		 }
		 return sz;
	}
	public Object removeFirst(){
		 if(isEmpty())
		  throw new NoSuchElementException();
		ListNode f = last.getNext();
		if(f==last){//size is 1
			last=null;
			return f.getValue();
		 }
		last.setNext(f.getNext());
		return f.getValue();
	}
	public Object removeLast(){
		if(isEmpty())
		     throw new NoSuchElementException("list is empty");
		ListNode curr = last.getNext();
		ListNode prev = last;
		if(curr==last){
			last = null;
			return curr.getValue();
		}
		while(curr!=last){
			prev = curr;
			curr = curr.getNext();
		}
		prev.setNext(last.getNext());
		last = prev;//point to new last node
		return curr.getValue();
	}
	public Object Get(int i){
		if(size()<=i || i<0)
			throw new NoSuchElementException();
		ListNode current = last;
		for(int n=0; n<i+1; n++)
			current = current.getNext();
		return current.getValue();
	}
	public Object Set(int i, Object o){
		Object storage = null;
		if(size()<=i || i<0)
			throw new NoSuchElementException();
		ListNode current = last;
		for(int n=0; n<i+1; n++)
			current = current.getNext();
		storage=current.getValue();
		current.setValue(o);
		return storage;
	}
	public void Add(int i, Object o){
		ListNode newy = new ListNode(o, null);
		ListNode prev = last;
		if(i ==0){
			this.addFirst(o);
			return;
		}
		for(int n=0; n<i; n++)
			prev = prev.getNext();
		ListNode next = prev.getNext();
		prev.setNext(new ListNode(o, next));
		if(i==this.size())
			last=prev.getNext();
	}
	public Object Remove(int i){
		ListNode prev=last;
		if(size()<i || i<0)
			throw new NoSuchElementException();
		if(i==0 || this.size()==1){
			ListNode Ultra=last;
			this.removeFirst();
			return Ultra;
		}
		if(i==this.size()-1){
			ListNode Bultra=last;
		this.removeLast();
		return Bultra;
		}
		for(int n=0; n<i; n++)
			prev = prev.getNext();
		ListNode UltraLisk=prev.getNext();
		if(prev==UltraLisk){
			last=null;
			return UltraLisk.getValue();
		}
		prev.setNext(UltraLisk.getNext());
		if(i==this.size())
			last=prev.getNext();
		return UltraLisk.getValue();
	}
	public String toString(){
		String ans="";
		if(last==null)
			return "empty";
		ListNode current = last.getNext();//start
		while(current!=last){
			ans+=current.getValue()+ ", ";
			current=current.getNext();
		}
		ans+=current.getValue()+ " ";
		return ans;
	}	
}