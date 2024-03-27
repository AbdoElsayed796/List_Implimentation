import java.util.*;
import java.text.*;
import java.awt.Point;
interface IPolynomialSolver {

    public void setPolynomial( Polynomial X , int C[]);
    public String print(Polynomial X);
    public void clearPolynomial(Polynomial X);
    public float evaluatePolynomial(Polynomial X, float value);
    public Polynomial add(Polynomial X1 );
    public Polynomial subtract(Polynomial X1);
    public Polynomial multiply(Polynomial X1);
}

public class Polynomial implements IPolynomialSolver{ // A B C
    Node Head = null ;
    Node Tail = null;
    int Size = 0;
    static class Node {
        int Exp , Coeff ;
        Node Next,Prev ;
        Node(int Coeff, int Exp , Node prev , Node next)
        {
            this.Coeff=Coeff;
            this.Exp=Exp;
            this.Next=next;
            this.Prev=prev;
        }
    }
    public String print(Polynomial X)
    {
        Node node = X.Head ;
        if (X.Head==null){
            return "Error" ;
        }
        String res = "" ;
        while (node!= null) {
             if ((int)node.Exp != 0 && (int)node.Exp != 1) {
                if ((int)node.Coeff > 0)
                    if ((int)node.Coeff == 1){
                        if (node == X.Head) res += "x^" + node.Exp;
                        else res += "+"+ "x^" + node.Exp;}
                    else{
                        if (node == X.Head) res += node.Coeff +"x^" + node.Exp;
                        else res += "+" + node.Coeff + "x^" + node.Exp;}
                else if ((int)node.Coeff < 0){
                    if (node.Coeff == -1)
                        res += "-1x^"+node.Exp;
                    else res +=  node.Coeff + "x^" + node.Exp; }
            }
            else if (node.Exp==1){
                if (node.Coeff > 0) {
                    if (node.Coeff == 1) {
                        if (node == X.Head) res += "x";
                        else res += "+x";
                    }
                    else res += "+" + node.Coeff + "x";
                }else if ((int)node.Coeff < 0)
                    if (node.Coeff == -1)
                        res += "-1x";
                    else res +=  node.Coeff + "x";
            }
            else {
                if ((int)node.Coeff > 0)
                    if (node==X.Head){
                        res += node.Coeff ;
                    }
                    else{res += "+" + node.Coeff;}
                else if (node.Coeff < 0)
                    res +=  node.Coeff;
                else {
                    if (res.isEmpty()){
                        res+="0";
                    }
                }
            }
            node = node.Next ;
        }
        return res ;
    }
    public void Setadd(int elem , int Exp) {         //addLast
        if (Head==null)
            Head = Tail = new Node(elem, Exp,null, null);
        else {
            Tail.Next = new Node(elem,Exp,Tail, null);
            Tail = Tail.Next;
        }
        Size++;
    }

    public void add(int elem , int Exp) {         //addLast
        if (Head==null)
            Head = Tail = new Node(elem, Exp,null, null);
        else {
            Tail.Next = new Node(elem,Exp,Tail, null);
            Tail = Tail.Next;
        }
        Size++;
    }
    public void setPolynomial(Polynomial X , int C[])
    {
        if (C.length==0) {
            System.out.println("Error");
            return;
        }
        int N = C.length;//3
        int i = 0;
        X.Head = null ;
        while(i<N)
        {
            if (C[i]==0 ){
                if (i<N-1){
                        i++;
                        continue;}
                else {
                    X.Setadd(C[i],N-i-1);
                    i++ ;
                    continue;
                }
            }
            X.Setadd(C[i],N-i-1);
            i++;
        }
    }
    public void clearPolynomial(Polynomial X)
    {
        Node trav = X.Head ;
        while(trav!=null)
        {
            Node next = trav.Next;
            trav.Prev=trav.Next=null ;
            trav.Coeff = 0 ;
            trav.Exp = 0 ;
            trav = next ;
        }
        X.Head=X.Tail=null ;
        Size = 0 ;
    }
    public float evaluatePolynomial(Polynomial X, float value)
    {
        float r = 0 ;
        Node node = X.Head ;
        if (X.Head==null){
            return 0 ;
        }
        while (node!= null){
            r +=  (double)node.Coeff * Math.pow((double) value, (double) node.Exp) ;
            node= node.Next;
        }
        return r ;
    }

    public Polynomial subtract(Polynomial X1 )
    {
        Polynomial  P = new Polynomial();
        if(Head==null || X1.Head==null){
            System.out.println("Error");
            return P;
        }
        Node cur2 = X1.Head;
        Node cur1=Head;
        while(cur1 != null && cur2 != null)
        {
            if(cur1.Exp>cur2.Exp)
            {
                P.add(cur1.Coeff,cur1.Exp);
                cur1=cur1.Next;
            }
            else if(cur1.Exp<cur2.Exp)
            {
                P.add(-cur2.Coeff,cur2.Exp);
                cur2=cur2.Next;
            }
            else
            {
                P.add(cur1.Coeff-cur2.Coeff,cur2.Exp);
                cur2=cur2.Next;
                cur1=cur1.Next;
            }
        }
        while(cur1!=null)
        {
            P.add(cur1.Coeff, cur1.Exp);
            cur1=cur1.Next;
        }
        while(cur2!=null)
        {
            P.add(cur2.Coeff, cur2.Exp);
            cur2=cur2.Next;
        }
        return P ;
    }
    public Polynomial add(Polynomial X1 )
    {

        Polynomial  P = new Polynomial();
        if (Head ==null ||X1.Head==null){
        System.out.println("Error");
        return P;
    }
        Node cur2 = X1.Head;
        Node cur1=Head;
        while(cur1 != null && cur2 != null)
        {
            if(cur1.Exp>cur2.Exp)
            {
                P.add(cur1.Coeff,cur1.Exp);
                cur1=cur1.Next;
            }
            else if(cur1.Exp<cur2.Exp)
            {
                P.add(cur2.Coeff,cur2.Exp);
                cur2=cur2.Next;
            }
            else
            {
                P.add(cur1.Coeff+cur2.Coeff,cur2.Exp);
                cur2=cur2.Next;
                cur1=cur1.Next;
            }
        }
        while(cur1!=null)
        {
            P.add(cur1.Coeff, cur1.Exp);
            cur1=cur1.Next;
        }
        while(cur2!=null)
        {
            P.add(cur2.Coeff, cur2.Exp);
            cur2=cur2.Next;
        }
        return P ;
    }
    public Polynomial multiply(Polynomial X1 ) {
        List<Point> points = new ArrayList<>();
        Polynomial P = new Polynomial();
        if(Head==null || X1.Head==null){
            System.out.println("Error");
            return P;
        }
        Node cur2 = X1.Head;
        Node cur1 = Head;
        for (cur1 = Head; cur1 != null; cur1 = cur1.Next) {
            for (cur2 = X1.Head; cur2 != null; cur2 = cur2.Next) {
                int Coef = cur1.Coeff * cur2.Coeff;
                int Exp = cur2.Exp + cur1.Exp;
                Point Point = new Point(Exp,Coef);
                points.add(Point);
            }
        }
        Collections.sort(points, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                if (p1.x != p2.x) {
                    return Integer.compare(p2.x, p1.x); // Sort x-coordinate in descending order
                } else {
                    return Integer.compare(p2.y, p1.y); // Sort y-coordinate in descending order
                }
            }
        });

        int N = points.size();
        List<Point> newpoints = new ArrayList<>();
        newpoints.add(points.get(0));
        int count=0;
        for(int i = 1 ; i < N ; i++)
        {
            Point point2 = points.get(i);
            Point point1= newpoints.get(count);
            if(point2.x==point1.x)
            {
                Point point3 = new Point(point1.x, point1.y+point2.y);
                newpoints.set(count,point3);
            }
            else
            {
                newpoints.add(point2);
                count++;
            }
        }
        for (Point point : newpoints) {
            P.add((int)point.getY(),(int)point.getX());
        }
        return P ;
    }
//    public void addMiddle(int V,int Exp, int idx)
//    {
//        Size++;
//        Node  INS = new Node(V,Exp,null,null);
//        Node  cur = Head.Next;
//        for(int count = 1; count<idx ;){
//            count++;
//            cur=cur.Next;
//        }
//        Node temp =cur.Prev ;
//        temp.Next=INS;
//        INS.Prev=temp;
//        INS.Next=cur;
//        cur.Prev=INS;
//    }
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.###");
        Polynomial A = new Polynomial();
        Polynomial B = new Polynomial();
        Polynomial C = new Polynomial();
        Polynomial list = new Polynomial();
        Scanner in = new Scanner(System.in) ;
        String str ;
        while (in.hasNext()) {
            str = in.nextLine();
            if (str.equals("")){
                str = in.nextLine();
            }
            switch (str) {
                case "add":
                    String z = in.nextLine();
                    String y = in.nextLine();
                    if (z.equals("B") && y.equals("A"))
                        list = B.add(A);
                    else if (z.equals("A") && y.equals("B"))
                        list = B.add(A);
                    else if (z.equals("C") && y.equals("A"))
                        list = C.add(A);
                    else if (z.equals("A") && y.equals("C"))
                        list = C.add(A);
                    else if (z.equals("B") && y.equals("C"))
                        list = C.add(B);
                    else if (z.equals("C") && y.equals("B"))
                        list = C.add(B);
                    else {
                        System.out.println("Error");
                        break ;
                    }
                    if (list.Head==null) break;
                    System.out.println(list.print(list));
                    break;
                case "sub":
                    z = in.nextLine();
                    y = in.nextLine();
                    if (z.equals("B") && y.equals("A"))
                        list = B.subtract(A);
                    else if (z.equals("A") && y.equals("B"))
                        list = A.subtract(B);
                    else if (z.equals("C") && y.equals("A"))
                        list = C.subtract(A);
                    else if (z.equals("A") && y.equals("C"))
                        list = A.subtract(C);
                    else if (z.equals("B") && y.equals("C"))
                        list = B.subtract(C);
                    else if (z.equals("C") && y.equals("B"))
                        list = C.subtract(B);
                    else {
                        System.out.println("Error");
                        break;
                    }
                    if (list.Head==null) break;
                    System.out.println(list.print(list));
                    break;
                case "mult":
                    z = in.nextLine();
                    y = in.nextLine();
                    if (z.equals("B") && y.equals("A")) {
                        if (B.Head ==null|| A.Head ==null){
                            System.out.println("Error");
                            break;
                        }
                        else if (B.Head.Coeff ==0|| A.Head.Coeff ==0 ) {
                            System.out.println("0");
                            break;
                        }

                        list = B.multiply(A);
                    }
                    else if (z.equals("A") && y.equals("B")) {
                        if (B.Head ==null|| A.Head ==null){
                            System.out.println("Error");
                            break;
                        }
                        else if (B.Head.Coeff ==0|| A.Head.Coeff ==0 ) {
                            System.out.println("0");
                            break;
                        }
                        list = B.multiply(A);
                    }
                    else if (z.equals("C") && y.equals("A")) {
                        if (C.Head ==null|| A.Head ==null){
                            System.out.println("Error");
                            break;
                        }
                        else if (C.Head.Coeff ==0|| A.Head.Coeff ==0 ) {
                            System.out.println("0");
                            break;
                        }

                        list = C.multiply(A);
                    }
                    else if (z.equals("A") && y.equals("C")) {
                        if (C.Head ==null|| A.Head ==null){
                            System.out.println("Error");
                            break;
                        }
                        else if (C.Head.Coeff ==0|| A.Head.Coeff ==0 ) {
                            System.out.println("0");
                            break;
                        }
                        list = C.multiply(A);
                    }
                    else if (z.equals("B") && y.equals("C")) {
                        if (B.Head ==null|| C.Head ==null){
                            System.out.println("Error");
                            break;
                        }
                        else if (B.Head.Coeff ==0|| C.Head.Coeff ==0 ) {
                            System.out.println("0");
                            break;
                        }

                        list = C.multiply(B);
                    }
                    else if (z.equals("C") && y.equals("B")) {
                        if (B.Head ==null|| C.Head ==null){
                            System.out.println("Error");
                            break;
                        }
                        else if (B.Head.Coeff ==0|| C.Head.Coeff ==0 ) {
                            System.out.println("0");
                            break;
                        }

                        list = C.multiply(B);
                    }
                    else {
                        System.out.println("Error");
                        break;
                    }
                    if (list.Head==null) break;
                    System.out.println(list.print(list));
                    break;
                case "set":
                    z = in.nextLine();
                    str = in.nextLine().replaceAll("\\[", "").replaceAll("\\]", "");
                    String s[] = str.split(",");
                    int[] arr = new int[s.length];
                    if (s.length == 1 && s[0].isEmpty())
                        arr = new int[]{};
                    else {
                        for (int i = 0; i < s.length; ++i)
                            arr[i] = Integer.parseInt(s[i]);
                    }
                    if(z.equals("A")){
                        A.setPolynomial(A, arr);
                    }
                    else if (z.equals("B")){
                        B.setPolynomial(B,arr);
                    }
                    else if (z.equals("C")){
                        C.setPolynomial(C,arr);
                    }
                    else System.out.println("Error");
                    break;
                case "clear":
                    z = in.nextLine();
                    if (z.equals("A")) {
                        if (A.Head!=null){
                            A.clearPolynomial(A);
                            System.out.println("[]");}
                        else System.out.println("Error");
                    }
                    else if (z.equals("B")) {
                        if (B.Head!=null){
                            B.clearPolynomial(B);
                            System.out.println("[]");}
                        else System.out.println("Error");
                    }
                    else {
                        if (C.Head!=null){
                            C.clearPolynomial(C);
                            System.out.println("[]");}
                        System.out.println("Error");
                    }
                    break;
                case "eval":
                    z = in.nextLine();
                    float value = in.nextFloat();
                    if (z.equals("A")){
                        if(A.Head==null){
                            System.out.println("Error");
                        }
                        else System.out.println(df.format(A.evaluatePolynomial(A, value)));}
                    else if (z.equals("B"))
                        if(B.Head==null){
                            System.out.println("Error");
                        }
                        else System.out.println(df.format(B.evaluatePolynomial(B, value)));
                    else if (z.equals("C"))
                        if(C.Head==null){
                            System.out.println("Error");
                        }
                        else System.out.println(df.format(C.evaluatePolynomial(C, value)));
                    else System.out.println("Error");
                    break;
                case "print" :
                    z = in.nextLine();
                    if (z.equals("A"))
                        System.out.println(A.print(A));
                    else if (z.equals("B"))
                        System.out.println(B.print(B));
                    else if (z.equals("C"))
                        System.out.println(C.print(C));
                    else
                        System.out.println("Error");
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
        }
    }
}
