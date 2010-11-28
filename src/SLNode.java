/*
 * Class for single node of SkipList
 * 
 */

/**
 *
 * @author akailash
 */
public class SLNode {

    private int label, level;
    private SLNode[] next;
    private final int maxlevel = 4;

    public SLNode(int num)
    {
        setup(num);
    }

    private void setup(int num) {
        label = num;
        level = 0;
        next = new SLNode[maxlevel];
        for (int i=0; i<maxlevel; i++)
            next[i]=null;
    }
    public int getLabel()
    {
        return label;
    }

    public void setLevel(int lvl, SLNode lvlNext)
    {
        if (lvl<=level)
        {
            next[lvl]= lvlNext;
        }
    }

    public SLNode getLevel(int level)
    {
        if (level<maxlevel)
        {
            return next[level];
        }
        return null;
    }
    public void promote(int lvl, SLNode[] preNode)
    {
        level = lvl;
        for (int i=0; i<lvl; i++)
        {
            if(preNode[i]!=null)
                preNode[i].setLevel(i, this);
            preNode[i]=this;
        }
    }
}
