import java.util.*;

/**
 * Created by Miron on 31.05.2017.
 */
public class Tree {
    private Node root;
    private Node ventNode;
    private List<Node> postOrder;
    private List<Node> oldPreOrder;
    private List<Node> preOrder;
    public List<Node> preOrderListOfNodes(){
        return preOrder;
    }
    public List<Node> postOrderListOfNodes(){
        return postOrder;
    }

    public Tree(Scanner scanner)
    {
        ventNode=new Node(this,0,ventNode());
        postOrder=new LinkedList<>();
        preOrder=new LinkedList<>();
        readTreeNonRec(scanner);
        oldPreOrder=preOrder;
    }

    public Tree()
    {
        this(new Scanner(System.in));
    }


    public Node ventNode()
    {
        return ventNode;
    }
    /*Wczytywanie drzewa rekurencyjnie*/
    private void readTreeRec(Scanner scanner)
    {
        int a=scanner.nextInt();
        if (a!=0)
        {
            root=new Node(this,ventNode);
            root.readNodeFromInput(scanner,a,new Integer(1));
        }
        else {
            root = ventNode;
        }
    }
    /*Wczytywanie drzewa iteracyjne*/
    private void readTreeNonRec(Scanner scanner)
    {
        ArrayList<Node> stack=new ArrayList<>();
        int a=scanner.nextInt();
        if(a!=0)
        {
            Node newNode;
            root=new Node(this,a,ventNode);
            Node actualNode;
            preOrder.add(root);
            stack.add(root);
            while(stack.isEmpty()==false)
            {
                actualNode=stack.get(stack.size()-1);
                if(actualNode.left()==null)
                {
                    a=scanner.nextInt();
                    if(a==0) {
                        actualNode.setLeft(ventNode);
                    }
                    else
                    {
                        newNode=new Node(this,a,actualNode);
                        actualNode.setLeft(newNode);
                        stack.add(newNode);
                    }
                    preOrder.add(actualNode.left());
                }else if(actualNode.right()==null)
                {
                    a=scanner.nextInt();
                    if(a==0) {
                        actualNode.setRight(ventNode);
                    }
                    else
                    {
                        newNode=new Node(this,a,actualNode);
                        actualNode.setRight(newNode);
                        stack.add(newNode);
                    }
                    preOrder.add(actualNode.right());
                }
                else {
                    postOrder.add(actualNode);
                    stack.remove(stack.size()-1);
                }
            }
        }
    }

    public void compress()
    {
        HashMap<Node,Node> map=new HashMap<>();
        for(Node node:this.postOrder)
        {
            if(map.containsKey(node.left()))
            {
                node.left().setCopy(map.get(node.left()));
                node.setLeft(node.left().copy());
            }
            if(map.containsKey(node.right()))
            {
                node.right().setCopy(map.get(node.right()));
                node.setRight(node.right().copy());
            }
            if(!map.containsKey(node)) {
                map.put(node, node);
            }
        }
        Node act;
        preOrder=new LinkedList<>();
        LinkedList<Node> stack=new LinkedList<>();
        stack.add(root);
        while(stack.isEmpty()==false)
        {
            act=stack.pop();
            preOrder.add(act);
            if(act!=ventNode)
            {
                stack.add(act.left());
                stack.add(act.right());
            }
        }
    }

    public String toString()
    {
        StringBuilder res=new StringBuilder();
        int currentLine=1;
        Node lastNode=oldPreOrder.get(0);
        for(Node node:oldPreOrder)
        {
            if(node.isCopy()==false)
            {
                if(!(node==ventNode&&lastNode.isCopy())) {
                    res.append(Integer.toString(node.val()) + "\n");
                    node.setCompressedId(currentLine);
                    currentLine++;
                }
            }
            else if(node.father().isCopy()==false)
            {
                res.append(Integer.toString(node.copy().compressedId()-currentLine)+"\n");
                currentLine++;
            }
            if(node!=ventNode)
                lastNode=node;
        }
        return res.toString();
    }
}
