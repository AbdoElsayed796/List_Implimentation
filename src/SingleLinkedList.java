import java.util.*;

interface LinkedList {
    /**
     * Inserts a specified element at the specified position in the list.
     * @param index
     * @param element
     */
    public void addToIndex(int index, Object element);
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
    public LinkedList sublist(int fromIndex, int toIndex);
    /**
     * @param o
     * @return true if this list contains an element with the same value as the specified element.
     */
    public boolean contains(Object o);
}

class SingleLinkedList implements LinkedList {

    Node head ;
    public void show(){
        Node node = head ;
        if (head==null){
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        while(node.next!=null){
            System.out.print(node.data+ ", ");
            node = node.next ;
        }
        System.out.println(node.data+"]");
    }
    public void addToIndex (int index ,Object data)
    {
        Node n = head ;
        Node node = new Node();
        node.data = data ;
        if (index ==0 ){
            node.next= head ;
            head = node ;
            return;
        }
        while(index>1)
        {
            if (n.next==null){
                add(data) ;
                return;
            }
            n = n.next ;
            index = index-1 ;
        }
        node.next = n.next ;
        n.next = node ;
    }

    public void add(Object data){
        Node node = new Node();
        node.data = data ;
        node.next= null ;
        if (head == null)
        {
            head = node ;
        }
        else
        {
            Node n = head ;
            while (n.next!= null){
                n = n.next ;
            }
            n.next = node ;
        }
    }
    public Object get(int index){
        Node n = head ;
        for (int i = 0; i < index; i++) {
            if (n == null) {
                System.out.println("Error");
                return ("x") ;
            }
            n=n.next ;
        }
        return n.data ;
    }
    public void set(int index, Object element)
    {
        if (head==null){
            head = new Node();
            head.data=element;
            return;
        }
        if (index ==0 ){
            head.data=element;
            return;
        }
        Node n = head ;
        while(index>0)
        {
            n = n.next ;
            index = index-1 ;
        }
        n.data = element ;
    }
    public void clear()
    {
        Node n = head ;
        Node temp ;
        while (n!=null){
            temp=n.next ;
            n = null ;
            n = temp ;
        }
        head=n ;
    }
    public boolean isEmpty(){
        if (head == null)
            return true ;
        else return  false ;
    }
    public void remove(int index) {
        Node n = head ;
        if (head == null){
            System.out.println("Error");
            return;
        }
        if (index==0){
            head = n.next ;
            n= null ;
        }
        else {
            for (int i = 0; i <index - 1; i++) {
                if (n==null) {
                    System.out.println("Error");
                    return;
                }
                n = n.next;
            }
            if (n.next==null) {
                System.out.println("Error");
                return;
            }
            Node temp = n;
            temp = temp.next;
            n.next = temp.next;
            temp = null ;
        }
    }
    public int size(){
        Node n =head ;
        if (head==null){
            return 0 ;
        }
        int size=1 ;
        while (n.next!=null)
        {
            n=n.next ;
            size++ ;
        }
        return size ;
    }
    public LinkedList sublist(int fromIndex, int toIndex){
        Node n = head ;
        SingleLinkedList sublist = new SingleLinkedList() ;
        int index =0 ;
        while (index<fromIndex){
            if (n==null){
                System.out.println("Error");
                return null ;
            }
            n=n.next ;
            index++ ;
        }
        for (int i = fromIndex; i <= toIndex ; i++) {
            if(n==null){
                System.out.println("Error");
                return null ;
            }
            sublist.add(n.data);
            n=n.next ;
        }
        sublist.show() ;
        return sublist ;
    }
    public boolean contains(Object o){
        Node n = head ;
        while (n!=null)
        {
            if (n.data==o)
                return true ;
            n=n.next ;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in) ;
        int x = 0 , i=0;
        String str ;
        str = in.nextLine().replaceAll("\\[", "").replaceAll("\\]", "");
        String s[] = str.split(", ");
        SingleLinkedList list = new SingleLinkedList();
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
                if (index> list.size()|| index<0){
                    System.out.println("Error");
                }
                else {
                    list.addToIndex(index,x);
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
                if (index<0){
                    System.out.println("Error");
                    break;
                }
                if (index< list.size()) {
                    list.remove(index);
                    if (list.size() == 0)
                        System.out.println("[]");
                    else list.show();
                }
                else {
                    System.out.println("Error");
                }
                break;
            case "sublist":
                index = in.nextInt();
                x =in.nextInt();
                if (x<0 || index<0)
                    System.out.println("Error");
                else if (x<index) {
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