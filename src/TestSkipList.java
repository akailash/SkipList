/*
 * To test SkipList class
 */

/**
 *
 * @author akailash
 */
public class TestSkipList {

    private static final int MAX_NB_REPETITIONS = 10000;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] Set = {{1, 34, 20, 103, 200, 56, 198, 45, 99, 44},
        {201, 300, 400, 229, 345, 355, 278, 356, 245, 388},
        {401, 600, 500, 555, 655, 490, 501, 599, 423, 506},
        {601, 778, 800, 777, 666, 561, 656, 730, 703, 718},
        {801, 1000, 918, 1004, 1342, 950, 815, 867, 942, 1200}};

        //SkipList created with 1000 elements with 4 levels
        SkipList SL = new SkipList(1000,4);
//        SL.showStructure();
        SL.showDistribution();

        /*
        //Linked List simulated by creating a skiplist of 1 level.
        SkipList LL = new SkipList(1000,1);
//        LL.showStructure();
        LL.showDistribution();
         */

        Timer searchTimer = new Timer( );   // Timer for searching routine
        double LLtimeElapsed=0, SLtimeElapsed=0;
        boolean found = false;

        for(int i=0; i<Set.length; i++)
        {
            LLtimeElapsed=0;
            SLtimeElapsed=0;

            System.out.println("Set "+(i+1));
            for(int j=0; j<Set[i].length; j++)
            {
                found = false;

                searchTimer.start();
                for(int repetitions=0; repetitions<MAX_NB_REPETITIONS; repetitions++)
                    found=SL.linearSearch(Set[i][j]);
                searchTimer.stop();

                LLtimeElapsed = LLtimeElapsed + searchTimer.elapsedTime();

                System.out.print("Number of comparisons to find " + Set[i][j] + " in a linked list:" + SL.getNbComparisons());
                if(found==false)
                    System.out.println("("+Set[i][j]+ " does not exist in the list)");
                else
                    System.out.println();

                found =false;
                searchTimer.start();
                for(int repetitions=0; repetitions<MAX_NB_REPETITIONS; repetitions++)
                    found = SL.skipSearch(Set[i][j]);
                searchTimer.stop();

                SLtimeElapsed = SLtimeElapsed + searchTimer.elapsedTime();
                
                System.out.print("Number of comparisons to find " + Set[i][j] + " in a skip list:" + SL.getNbComparisons());
                if(found==false)
                    System.out.println("("+Set[i][j]+ " does not exist in the list)");
                else
                    System.out.println();
            }
            System.out.println("Time taken to search all elements in a linked list: " + (LLtimeElapsed/MAX_NB_REPETITIONS) + "ms");
            System.out.println("Time taken to search all elements in a skip list: " + (SLtimeElapsed/MAX_NB_REPETITIONS) + "ms");
        }
    }

}
