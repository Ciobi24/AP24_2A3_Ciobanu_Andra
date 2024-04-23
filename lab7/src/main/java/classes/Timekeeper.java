package classes;

import java.sql.SQLOutput;
import java.util.concurrent.atomic.AtomicBoolean;

public class Timekeeper extends Thread
{
    int time = 0;
    int maxTime = -1;
    AtomicBoolean isTimeFinished;

    Timekeeper(int maxTime, AtomicBoolean isTimeFinished)
    {
        this.maxTime = maxTime;
        this.isTimeFinished = isTimeFinished;
    }

    @Override
    public void run()
    {
        System.out.println("Starting program..");
        while(true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            time += 1;
            System.out.println("Current game has been running for: " + time + " seconds.");

            if(time >= maxTime )
            {
                System.out.println("Stopping game as time limit has been exceeded.");
                isTimeFinished.set(true);
                return;
            }

        }

    }

}
