package scheduler;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Controls the scheduling algorithm simulation for each algorithm and their derivatives
 * @author Michael Riha
 */

public class Main
{
    private static final int SIMULATION_RUNS = 5;
    private static final int MAX_PROCESSES_PER_RUN = 20;
    private static final int ALGORITHM_COUNT = 3;
    
    public static void main(String[] args) throws CloneNotSupportedException 
    { 
        Scheduler sjfna = new ShortestJobFirstNoAging();
        Scheduler rr = new RoundRobin();
        Scheduler srt = new ShortestRemainingTime();
        int[][] q1 = SchedulerGenerator.dataQueue("data.txt");
        // Hold duplicated process queues for each algorithm to use
        PriorityQueue<Process>[] q = new PriorityQueue[ALGORITHM_COUNT+1];
        q = (PriorityQueue<Process>[]) q;
        
        // Test each scheduling algorithm SIMULATION_RUNS times
        for (int i = 0; i < SIMULATION_RUNS; ++i)
        {
            System.out.println("---------------------------");
            System.out.format("Scheduling Process Queue %d:\n", i + 1);
            System.out.println("---------------------------");
            
            //generate a new process queue for this testing round then duplicate it
            q[0] = ProcessFactory.generateProcesses(q1);
            for (int j = 1; j < ALGORITHM_COUNT + 1; ++j)
                q[j] = copyQueue(q[0]);
            
            // Print the process list by ascending arrival time   
            while (!q[ALGORITHM_COUNT].isEmpty())
                System.out.println(q[ALGORITHM_COUNT].poll());
                        
            // Run each scheduling algorithm and show the results

            
            System.out.println("\nShortest Remaining Time");
            srt.schedule(q[0]);
            
            System.out.println("\nRound Robin");
            rr.schedule(q[1]);                            
            
            System.out.println("\nShortest Job First");
            sjfna.schedule(q[2]);                   
        }
        System.out.println("\n-------------------------------------------");
        System.out.println("Average Statistics by Scheduling Algorithm");
        System.out.println("-------------------------------------------");

        System.out.println("\nShortest Remaining Time");
        srt.printAvgStats();

        System.out.println("\nRound Robin");
        rr.printAvgStats();            

        System.out.println("\nShortest Job First");
        sjfna.printAvgStats();                   
    }
    
    private static PriorityQueue<Process> copyQueue(PriorityQueue<Process> q) throws CloneNotSupportedException
    {        
        PriorityQueue<Process> qcopy = new PriorityQueue<Process>();
        ArrayList<Process> qoriginal = new ArrayList<Process>();
        while (!q.isEmpty())
        {
            Process p = q.poll();
            qcopy.add((Process) p.clone());
            qoriginal.add(p);
        }
        q.addAll(qoriginal);
        return qcopy;
    }
}
