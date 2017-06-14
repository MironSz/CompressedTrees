import java.util.Scanner;

/**
 * Created by Miron on 31.05.2017.
 */
public class Node {
    private int compressedId;
    private Node father;
    private int val;
    private Node left;
    private Node right;
    private Tree tree;
    private int hash = -1;
    private int P=1000000007,M=1000000009;/*Jakies liczby do hashowania*/
    private Node copy;/*Wierzchołek, którego ten wierzchołek jest kopią*/



    public void setCompressedId(int n)
    {
        this.compressedId=n;
    }

    public int compressedId()
    {
        return compressedId;
    }

    public Node father()
    {
        return father;
    }

    public void setCopy(Node node)
    {
        this.copy=node;
    }

    public Node copy()
    {
        return this.copy;
    }

    public boolean isCopy()
    {
        return this.copy()!=this;
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

    public Node(Tree tree,Node father)
    {
        this.father=father;
        if(father==null)
            this.father=this;
        this.copy=this;
        this.tree=tree;
    }

    public Node(Tree tree, int val, Node father)
    {
        this(tree,father);
        this.val=val;
        this.left=null;
        this.right=null;
    }

    public void readNodeFromInput(Scanner scanner, int val, Integer numberOfNodes)
    {
        numberOfNodes=numberOfNodes+1;
        tree.preOrderListOfNodes().add(this);
        this.val=val;
        int a=scanner.nextInt();
        if(a!=0)
        {
            this.left=new Node(tree,this);
            this.left.readNodeFromInput(scanner,a,numberOfNodes);
        }
        else
        {
            tree.preOrderListOfNodes().add(tree.ventNode());
            this.left=tree.ventNode();
        }
         a=scanner.nextInt();
        if(a!=0)
        {
            this.right=new Node(tree,this);
            this.right.readNodeFromInput(scanner,a,numberOfNodes);
        }
        else
        {
            tree.preOrderListOfNodes().add(tree.ventNode());
            this.right=tree.ventNode();
        }
        tree.postOrderListOfNodes().add(this);
    }
    public int hashCode()
    {
        if(hash != -1)
            return hash;
        if(this==tree.ventNode())
            return 0;
        long res=((this.val*P*P)+this.left().hashCode()*P+this.right().hashCode())%M;
        this.hash=(int)res;
        return (int) res;
    }
    public boolean equals(Object o)
    {
        if(o instanceof  Node)
        {
            if(this.left()==(Node)((Node) o).left()&&this.right()==(Node)((Node) o).right()&&this.val()==((Node) o).val())
                return true;
        }
        return false;
    }
}
