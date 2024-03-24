import java.util.*;

interface ILinkedList {
    /**
     * Inserts a specified element at the specified position in the list.
     * @param index
     * @param element
     */
    public void add(int index, Object element);
    /**
     * Inserts the specified element at the end of the list.
     * @param element
     */
    public void add(Object element);
    /**
     * @param index
     * @return the element at the specified position in this list.
     */
    public Object get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index
     * @param element
     */
    public void set(int index, Object element);
    /**
     * Removes all of the elements from this list.
     */
    public void clear();
    /**
     * @return true if this list contains no elements.
     */
    public boolean isEmpty();
    /**
     * Removes the element at the specified position in this list.
     * @param index
     */
    public void remove(int index);
    /**
     * @return the number of elements in this list.
     */
    public int size();
    /**
     * @param fromIndex
     * @param toIndex
     * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
     */
    public ILinkedList sublist(int fromIndex, int toIndex);
    /**
     * @param o
     * @return true if this list contains an element with the same value as the specified element.
     */
    public boolean contains(Object o);
}


public class DoubleLinkedList implements ILinkedList {
    private Node Head = null ;
    private Node Tail = null;
    private int Size = 0;
    private static class Node{
        Object data ;
        Node Next,Prev ;
        Node(Object data , Node prev , Node next)
        {
            this.data=data;
            this.Next=next;
            this.Prev=prev;
        }
    }
    public void clear ()             //clear
    {
        Node trav = Head ;
        while(trav!=null)
        {
            Node next = trav.Next;
            trav.Prev=trav.Next=null ;
            trav.data = null ;
            trav = next ;
        }
        Head=Tail=null ;
        Size = 0 ;
    }
    public void show(){                            //show
        Node node = Head ;
        if (Head==null){
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        while(node.Next!=null){
            System.out.print(node.data+ ", ");
            node = node.Next ;
        }
        System.out.println(node.data+"]");
    }

    public int size(){return Size;}          //size
    public boolean isEmpty(){                //isEmpty
        return size()==0;
    }
    public void add( int idx , Object V )     //add
    {
        if(idx==0)addFirst((V));
        else if(idx==Size)add(V);
        else if(idx > 0 && idx < Size)addMiddle(V,idx);
    }
    public void addMiddle(Object V, int idx)          //addMiddle
    {
        Size++;
        Node INS = new Node(V,null,null);
        Node cur = Head.Next;
        for(int count = 1; count<idx ;){
            count++;
            cur=cur.Next;
        }
        Node temp =cur.Prev ;
        temp.Next=INS;
        INS.Prev=temp;
        INS.Next=cur;
        cur.Prev=INS;
    }
    public void add(Object elem) {         //addLast
        if (isEmpty())
            Head = Tail = new Node(elem, null, null);
        else {
            Tail.Next = new Node(elem,Tail, null);
            Tail = Tail.Next;
        }
        Size++;
    }
    public void addFirst(Object elem) {            //addFirst
        if (isEmpty())
            Head = Tail = new Node(elem, null, null);
        else {
            Head.Prev = new Node(elem, null, Head);
            Head = Head.Prev ;
        }
        Size++;
    }
    public Object get(int idx)              ///get
    {
        int count = 0 ;
        Node cur = Head ;
        for(;count<idx; )
        {
            count++;
            cur=cur.Next;
        }
        return cur.data;
    }
    public void pop_back()       //popback
    {
        Tail = Tail.Prev ;
        Tail.Next =null;
        Size--;
    }
    public void pop_front()        //popfront
    {
        Head = Head.Next ;
        Head.Prev=null ;
        Size-- ;
    }

    public void pop_middle(int idx)      //pop mid
    {
        Size--;
        int count = 1 ;
        Node cur = Head.Next;
        for(;count<idx ;count++)
        {
            cur = cur.Next;
        }
        Node P = cur.Prev;
        P.Next=cur.Next;
        cur.Next.Prev=P;
    }
    public void remove(int idx)            //remove
    {
        if(idx==0)pop_front();
        else if(idx==Size-1)pop_back();
        else if(idx>0 && idx<Size-1)pop_middle(idx);
    }
    public void set(int index, Object element)   // set
    {
        int count = 0 ;
        Node cur = Head ;
        for(;count<index; )
        {
            count++;
            cur=cur.Next;
        }
        cur.data=element;
    }
    public ILinkedList sublist(int fromIndex, int toIndex) {       //sub
        DoubleLinkedList L = new DoubleLinkedList();
        int count = 0;
        Node cur = Head;

        // Traverse the list until reaching the starting index
        while (count < fromIndex && cur != null) {
            cur = cur.Next;
            count++;
        }

        // Add elements to the new list from starting index to ending index
        while (cur != null && count <= toIndex) {
            L.add(cur.data);
            cur = cur.Next;
            count++;
        }
        L.show();
        return L;
    }
    public boolean contains(Object o)
    {
        Node cur = Head ;
        while(cur!=null)
        {
            if(cur.data==o)
                return true;
            cur = cur.Next;
        }
        return false;
    }


    public static void main(String[] args) {            //main
        Scanner in = new Scanner(System.in) ;
        int x = 0 , i=0;
        String str ;
        str = in.nextLine().replaceAll("\\[", "").replaceAll("\\]", "");
        String s[] = str.split(", ");
        DoubleLinkedList list = new DoubleLinkedList();
        for (i = 0; i < s.length; i++) {
            if (s.length == 1 && s[0].isEmpty()) {
                break;
            }
            list.add(Integer.parseInt(s[i]));
        }
        str = in.nextLine() ;
        switch (str){
            case "add":
                x = in.nextInt();
                list.add(x);
                list.show();
                break;
            case "addToIndex":
                int index = in.nextInt();
                x =in.nextInt();
                if (index>= list.size()||index<0){
                    System.out.println("Error");
                }
                else {
                    list.add(index,x);
                    list.show();}
                break;
            case "get":
                index = in.nextInt();
                if (index>=list.size()|| index<0){
                    System.out.println("Error");
                }
                else System.out.println(list.get(index));
                break;
            case "set":
                index = in.nextInt();
                x =in.nextInt();
                if (index<0){
                    System.out.println("Error");
                    break;
                }
                if(index==0 && list.size()==0)
                {
                    System.out.println("Error");
                    break;
                }
                if(index>= list.size() && index>0 &&list.size()!=0)
                    System.out.println("Error");
                else {
                    list.set(index,x);
                    list.show();
                }
                break;
            case "clear":
                list.clear();
                list.show();
                break;
            case "isEmpty" :
                if(list.isEmpty())
                    System.out.println("True");
                else System.out.println("False");
                break;
            case "remove":
                index = in.nextInt();
                if (index<0 || index>=list.size()){
                    System.out.println("Error");
                    break;
                }
                if(index==0 && list.size()==1)
                {
                    System.out.println("[]");
                    break;
                }
                if (index< list.size()) {
                    list.remove(index);
                    if (list.size() == 0)
                        System.out.println("[]");
                    else list.show();
                }
                break;
            case "sublist":
                index = in.nextInt();
                x =in.nextInt();
                if (x<0 || index<0)
                    System.out.println("Error");
                else if (x<index || x >= list.size()) {
                    System.out.println("Error");
                }
                else {list.sublist(index,x);}
                break;
            case "contains":
                x = in.nextInt();
                if(list.contains(x))
                    System.out.println("True");
                else System.out.println("False");
                break;
            case "size":
                System.out.println(list.size());
                break;
            default:
                System.out.println("Error");
                break;
        }
    }
}