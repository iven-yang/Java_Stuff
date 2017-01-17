public class ListNode{

	private Object value;
	private ListNode next;

	public ListNode(Object v, ListNode n){
		value=v;
		next=n;
	}
	
	//Accessors
	public Object getValue(){return value;}
	public ListNode getNext(){return next;}
	
	//Mutators
	public void setValue(Object o){value=o;}
	public void setNext(ListNode ln){next=ln;}	

}