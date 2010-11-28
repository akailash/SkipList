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
    private SLNode start;
    private final int maxlevel = 4;
    private Random randGen;
    public SkipList()
    {
        setup(0, 1);
    }

    public SkipList(int numElements, int maxlvl)
    {
        setup(numElements, maxlvl);
    }

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

    private int selectLevel(int maxlvl) 
    {
        int rand = randGen.nextInt();
        int level = 1;
        if ((rand%10)==1)
        {
            level++;
            rand = randGen.nextInt();
            if ((rand%5)==1)
            {
                level++;
                rand = randGen.nextInt();
                if((rand%2)==1)
                    level++;
            }
        }
        return level;
    }

    private void setup(int numElements, int maxlvl) {
        SLNode[] preNode = new SLNode[maxlvl];
        SLNode prev= null;
        SLNode newNode = null;
        int newlvl = 0;
        start = null;
        randGen = new Random();
        if (numElements>0)
        {
            start = new SLNode(1);
            // select level for new Node
            newlvl = selectLevel(maxlvl);
            // promote new Node to selected level
            start.promote(newlvl, preNode);
            prev = start;
        }
        for(int i = 2; i<=numElements ; i++)
        {
            newNode = new SLNode(i);
            // select level for new Node
            newlvl = selectLevel(maxlvl);
            // promote new Node to selected level
            newNode.promote(newlvl, preNode);
            
            prev = newNode;
        }
    }

}
