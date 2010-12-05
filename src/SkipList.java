/*
 * Class for SkipList
 * 
 */

/**
 *
 * @author akailash
 */
import java.util.Random;

public class SkipList {

    //private member storing the start of the skiplist
    private SLNode start;

    //Set maxlevel of skiplist as 4
    private final int maxlevel = 4;

    //Random number generator, used to create a skiplist.
    private Random randGen;

    //Creates a skiplist with specific number of elements and a maximum level
    public SkipList(int numElements, int maxlvl)
    {
        setup(numElements, maxlvl);
    }

    //print the skiplist
    public void showStructure() {
        SLNode curr=start;
        System.out.println("\t\t[0]\t[1]\t[2]\t[3]\t");
        while(curr!=null)
        {
            System.out.print(curr.getLabel()+"\t");
            for(int i=0; curr.getLevel(i)!= null; i++)
                System.out.print("\t|");
            System.out.println();
            curr= curr.getLevel(0);
        }
    }

    //private member function used to select the level for a node based on specified probability
    private int selectLevel(int maxlvl) 
    {
        int rand = randGen.nextInt();
        int level = 1;
        if ((maxlvl>level)&&((rand%10)==1))
        {
            level++;
            rand = randGen.nextInt();
            if ((maxlvl>level)&&((rand%5)==1))
            {
                level++;
                rand = randGen.nextInt();
                if((maxlvl>level)&&((rand%2)==1))
                    level++;
            }
        }
        return level;
    }

    //private member function to be called only from constructor
    private void setup(int numElements, int maxlvl) {
        SLNode[] preNode = new SLNode[maxlvl];
        SLNode prev= null;
        SLNode newNode = null;
        int newlvl = 0;
        start = null;
        randGen = new Random();
        if (numElements>0)
        {
            start = new SLNode(1, maxlevel);
            // select level for new Node
            newlvl = selectLevel(maxlvl);
            // promote new Node to selected level
            start.promote(newlvl, preNode);
            prev = start;
        }
        for(int i = 2; i<=numElements ; i++)
        {
            newNode = new SLNode(i, maxlevel);
            // select level for new Node
            newlvl = selectLevel(maxlvl);
            // promote new Node to selected level
            newNode.promote(newlvl, preNode);
            
            prev = newNode;
        }
    }

}
