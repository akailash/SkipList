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
    private int maxlevel;

    //Random number generator, used to create a skiplist.
    private Random randGen;
    private SLNode cursor;
    private int numCmp;

    private int[] elemPerLevel;
    private int numElem;

    //Creates a skiplist with specific number of elements and a maximum level
    public SkipList(int numElements, int maxlvl)
    {
        setup(numElements, maxlvl);
    }

    //print the skiplist
    public void showStructure() {
        gotoBeginning();
        System.out.println("\t\t[3]\t[2]\t[1]\t[0]\t");
        while(cursor!=null)
        {
            System.out.print(cursor.getElement()+"\t");
            for(int i=maxlevel-1; ((i>=0)&&(cursor.getNext(i)!= null)); i--)
                System.out.print("\t|");
            System.out.println();
            cursor= cursor.getNext(maxlevel-1);
        }
    }
    public void showDistribution()
    {
        System.out.println("Distribution of nodes per level in skiplist with "+maxlevel+" level(s).");
        System.out.println("[3]\t[2]\t[1]\t[0]\t");
        for(int i=maxlevel-1; i>=0; i--)
            System.out.print(elemPerLevel[i]+"\t");
        System.out.println();
    }

    private void gotoBeginning() {
        cursor = start;
        numCmp = 0;
    }

    private boolean isEmpty() {
        return (start==null);
    }

    //private member function used to select the level for a node based on specified probability
    private int selectLevel(int maxlvl) 
    {
        int rand = randGen.nextInt() & 0xff;
        int level = maxlvl;
        level--;
        elemPerLevel[level]++;
        if ((0<level)&&((rand%10)==1)&&(elemPerLevel[level-1]<(numElem/10)))
        {
            level--;
            elemPerLevel[level]++;
            rand = randGen.nextInt() & 0xff;
            if ((0<level)&&((rand%5)==1)&&(elemPerLevel[level-1]<(numElem/50)))
            {
                level--;
                elemPerLevel[level]++;
                rand = randGen.nextInt() & 0xff;
                if((0<level)&&((rand%2)==1)&&(elemPerLevel[level-1]<(numElem/100)))
                {
                    level--;
                    elemPerLevel[level]++;
                }
            }
        }
        return level;
    }

    //private member function to be called only from constructor
    private void setup(int numElements, int maxlvl) {
        //System.out.println("Creating skiplist "+ numElements+ ", maxlevel="+maxlvl);
        SLNode[] preNode = new SLNode[maxlvl];
        SLNode newNode = null;
        int newlvl = 0;
        start = null;
        numElem = numElements;
        maxlevel = maxlvl;
        elemPerLevel=new int[maxlevel];

        randGen = new Random();
        for(int i = 1; i<=numElements ; i++)
        {
            newNode = new SLNode(i, maxlvl);

            // select level for new Node
            newlvl = selectLevel(maxlvl);
            // promote new Node to selected level
            newNode.promote(newlvl, preNode);

            //set "start" to first node
            if (start==null)
                start=newNode;
        }
        gotoBeginning();
    }
    public boolean linearSearch(int elem)
   // Performs a search of the element linearly at level 3
   {
     boolean elemFound = false;

     if(isEmpty())
     {
       System.out.println("Empty List");
     }
     else
     {
       gotoBeginning( );
       if (cursor.getElement() == elem)
       {
         numCmp++;
         elemFound = true;
       }
       else
       {
         while ((elemFound == false)&&(cursor.getNext(maxlevel-1) != null))
         {
           numCmp++;
           cursor = cursor.getNext(maxlevel-1);
           if (cursor.getElement() == elem)
           {
             elemFound = true;
           }
         }
       }
     }
     return elemFound;
   }

   public boolean skipSearch(int elem)
   // Performs a skip search of the element and finds the number of comparisons
   // performed before the element is found, If the element is not found then it
   // returns the status that element is not found.
   {
     int i, nextHop;
     boolean elemFound = false;

     if (isEmpty())
     {
       System.out.println("Empty List");
     }
     else
     {
       gotoBeginning( );
       while ((elemFound == false)&&(cursor != null))
       {
         if (cursor.getElement()==elem)
         {
             numCmp++;
             elemFound = true;
         }
         for (i = 0, nextHop = (maxlevel-1); ((elemFound == false)&&(i < maxlevel)); i++)
         {
           if ((cursor.getNext(i)!=null))
           {
               numCmp++;
               if (cursor.getNext(i).getElement() <= elem)
               {
                   nextHop = i;
                   break;
               }
           }
         }
         cursor = cursor.getNext(nextHop);
       }
     }
     return elemFound;
   }

   public int getNbComparisons()
   {
       return numCmp;
   }
}
