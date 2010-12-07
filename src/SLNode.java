/*
 * Class for single node of SkipList
 * 
 */

/**
 *
 * @author akailash
 */
public class SLNode {

    //private members storing level and label of the node
    private int label, level;
    // next pointer array storing path to the next on each level of current node
    private SLNode[] next;

    public SLNode(int num, int maxlevel)
    {
        setup(num, maxlevel);
    }

    //private member function to be called only from constructor/destructor
    private void setup(int num, int maxlevel) {
        //System.out.println("Creating element "+ num+ ", maxlevel="+maxlevel);
        //set label as num and default level as 0.
        label = num;
        level = 0;
        //create and initialize next pointer as per max level of skiplist.
        next = new SLNode[maxlevel];
        for (int i=0; i<maxlevel; i++)
            next[i]=null;
    }

    //private member function to be called only from promote to set levels of previous nodes
    //set the next node at particular level of this node to point to a particular node
    private void setLevel(int lvl, SLNode lvlNext)
    {
        //System.out.println("Setting element "+label+" .next["+lvl+"] to "+ lvlNext.getElement());
        if (lvl<=next.length)
        {
            next[lvl]= lvlNext;
        }
    }

    //public functions of SLNode

    //get label of node
    public int getElement()
    {
        return label;
    }

    //get the next node at particular level from this node.
    public SLNode getNext(int lvl)
    {
        if(lvl<next.length)
            return next[lvl];
        else
            return null;
    }

    //promote this node to particular level and update the next pointers of previous nodes at that level
    public void promote(int lvl, SLNode[] preNode)
    {
        //System.out.println("Promoting element "+label +" to level "+ lvl);
        level = lvl;
        for (int i=next.length; i>level; i--)
        {
            if(preNode[i-1]!=null)
                preNode[i-1].setLevel(i-1, this);
            preNode[i-1]=this;
        }
    }
}
