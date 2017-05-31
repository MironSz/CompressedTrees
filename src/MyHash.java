/**
 * Created by Miron on 31.05.2017.
 */
public class MyHash {
    public Node l;
    public Node r;
    int val;
    public MyHash(Node l, Node r, int val)
    {
        this.val=val;
        this.l=l;
        this.r=r;
    }
    public boolean isEqual(Object o)
    {
        if(o instanceof MyHash)
        {
            MyHash o2=(MyHash)o;
            return (o2.l==l&&o2.r==r&&(o2.val-val==0));
        }
        return false;
    }
    public MyHash(Node node)
    {
        this(node.left(),node.right(),node.val());
    }
}
