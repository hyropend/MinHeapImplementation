import java.util.Arrays;



public class MinHeap {
	private static final int MAX_SIZE=100;
	private int []items=new int[MAX_SIZE];
	private int size=0;
	
	
	private static int Parent(int n) {
		
		return (n-1)/2;
	}
	private static int 	LeftChild(int n) {
		return 2*n+1;
	}
	private static int 	RightChild(int n) {
		return 2*n+2;
	}
		
	private void heapUp(int loc) {
		while(loc>0) {
			if(items[loc]<items[Parent(loc)]) {
				//swap(items[loc],items[Parent(loc)])
				int t=items[loc];
				items[loc]=items[Parent(loc)];
				items[Parent(loc)]=t;
				//update loc
				loc=Parent(loc);
			}
			else 
				break;
		}
	}
	public void insert(int itm) {
		if(!isFull()) {
			//copy itm to next free slot
			items[size]=itm;//ensures the structure property
			heapUp(size); //restores the relation property
			size++;
		}
	}
	public void heapDown(int loc) {
		boolean isDone=false;
		while(loc<size) {
			if(LeftChild(loc)>=size) // this node is leaf node 
				break;
			else if(LeftChild(loc)<size && RightChild(loc)>=size ) //if there is only left child
			{
				if(items[loc]>items[LeftChild(loc)])//swap
				{
					int t=items[loc];
					items[loc]=items[LeftChild(loc)];
					items[LeftChild(loc)]=t;
					loc=LeftChild(loc);
				}
				else//since current root is less then its left child relational property is restored then break
					break;
			}
			else if(LeftChild(loc)<size && RightChild(loc)<size) {
				
				int minIdx=LeftChild(loc);
				if(items[minIdx]>items[RightChild(loc)])
					minIdx=RightChild(loc);
				
				if(items[minIdx]<items[loc]) {
					int t=items[minIdx];
					items[minIdx]=items[loc];
					items[loc]=t;
					loc=minIdx;
				}
				else 
					break;
					
			}
		}
	}
	public int deleteMin() {
		int minItem=0;
		if(!isEmpty()) {
			minItem=items[0];
			items[0]=items[size-1];//Exchange root with rightmost leaf
			size--;
			heapDown(0);
		}
		return minItem;
	}
	public boolean isEmpty() {
		return size==0;
	}
	public boolean isFull() {
		return size>=MAX_SIZE;
	}
	
	public String toString() {
		String res="[";
		int i=0;
		for(;i<size-1;i++) {
			res+=items[i]+",";
		}
		res+=items[i]+"]";
		return res;
	}

	/*min heap tester*/
	
	public static void main(String []s) {
		MinHeap mh=new MinHeap();
		mh.insert(10);
		mh.insert(5);
		mh.insert(7);
		mh.insert(7);
		mh.insert(6);
		mh.insert(11);
		mh.insert(3);
		System.out.println(mh);
		System.out.println("Delete:"+mh.deleteMin());
		System.out.println(mh);
		
		
	}
}
