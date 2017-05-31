import java.util.Scanner;

/**
 * Created by Miron on 31.05.2017.
 */
public class Node {
    private int val;
    private Node left;
    private Node right;
    public boolean isCom;
    public void setVal(int n)
    {
        val=n;
    }
    public int val()
    {
        return val;
    }
    public Node left()
    {
        return left;
    }
    public Node right()
    {
        return right;
    }
    public void setLeft(Node n)
    {
        left=n;
    }
    public void setRight(Node n)
    {
        right=n;
    }
    public void readNode(Scanner scanner, int val)
    {
        this.val=val;
        int a=scanner.nextInt();
        if(a!=0)
        {
            this.left=new Node();
            this.left.readNode(scanner,a);
        }
        else
        {
            this.left=null;
        }
         a=scanner.nextInt();
        if(a!=0)
        {
            this.right=new Node();
            this.right.readNode(scanner,a);
        }
        else
        {
            this.right=null;
        }
    }

}
