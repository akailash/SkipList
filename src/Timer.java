//--------------------------------------------------------------------
//
//  Laboratory 15                                          Timer.jshl
//
//  (Shell) Class definition for the Timer ADT
//
//--------------------------------------------------------------------

class Timer
{
    // Data members
    private long startTime,   // Time that the timer was started
                 stopTime;    // Time that the timer was stopped
    
    // Start and stop the timer
    public void start ( )
    {
        startTime = System.currentTimeMillis();
    }
    public void stop ( )
    {
        stopTime = System.currentTimeMillis( );
    }

    // Compute the elapsed time (in milliseconds)
    public long elapsedTime ( )
    {
        return (stopTime - startTime);
    }
    
} // class Timer



