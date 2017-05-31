import javax.transaction.TransactionRequiredException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Miron on 31.05.2017.
 */
public class Tree {
    private Node root;
    private List<Node> postOrder;
    public List<Node> preOrderListOfNodes(){
        return null;
    }
    public List<Node> postOrderListOfNodes(){
        return postOrder;
    }
    public Tree(Scanner scanner)
    {
        int a=scanner.nextInt();
        if (a!=0)
        {
            root=new Node();
            root.readNode(scanner,a);
        }
        else
        {
            root=null;
        }
    }
    public Tree()
    {
        this(new Scanner(System.in));
    }

    public static Tree compress(Tree tree)
    {
        HashMap<MyHash,Node> map=new HashMap<>();
        for(Node node:tree.postOrderListOfNodes())
        {
            if(map.containsKey(new MyHash(node.left())))
            {
                node.setLeft(map.get(new MyHash(node.left())));
            }
            if(map.containsKey(new MyHash(node.right())))
            {
                node.setRight(map.get(new MyHash(node.right())));
            }
            MyHash hasz=new MyHash(node);
            if(!map.containsKey(hasz))
                map.put(hasz,node);
        }
        return tree;
    }

}
