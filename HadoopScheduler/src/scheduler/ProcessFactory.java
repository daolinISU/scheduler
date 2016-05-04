
package scheduler;


import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * ProcessFactory generates processes and puts them in a queue
 * 
 * @author Michael Riha
 */

public class ProcessFactory {
	/*
	 * Create processCount random processes and add to a priority queue
	 * 
	 * @return q A PriorityQueue ordered with lowest arrival time first
	 */
	public static PriorityQueue<Process> generateProcesses(int processCount) {
		String names = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

		// Use a priority queue to order processes by arrival time (IMPORTANT!!)
		PriorityQueue<Process> q = new PriorityQueue<Process>();

		// get random arrival, expected time, and priority
		Random randomArrival = new Random();
		Random randomPriority = new Random();
		Random randomExpectedTime = new Random();

		double nextArrival = 0.0;

		// Generate new processes and add to the process queue
		for (int i = 0; i != processCount && nextArrival < 95; ++i) {
			Process p = new Process();
			p.setArrivalTime(nextArrival);
			p.setBurstTime(randomExpectedTime.nextInt(10) + 1);
			p.setPriority(randomPriority.nextInt(4) + 1);
			p.setName(names.charAt(i)+"");
			q.add(p);

			nextArrival += randomArrival.nextFloat() * 10;
		}
		return q;
	}

	/*
	 * Create processCount from jobs with period and add to a priority queue
	 * 
	 * @return q A PriorityQueue ordered with lowest arrival time first
	 */
	public static PriorityQueue<Process> generateProcesses(
			SchedulerGenerator gen) {
		String names = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

		// Use a priority queue to order processes by arrival time (IMPORTANT!!)
		PriorityQueue<Process> q = new PriorityQueue<Process>();

		// get random arrival, expected time, and priority
	
		int[][] list = gen.getQueue();

		// Generate new processes and add to the process queue
		for (int i = 0; i < SchedulerGenerator.jobNum; ++i) {
			Process p = new Process();
			p.setArrivalTime(list[i][0]);
			p.setBurstTime(list[i][1]);
			p.setPriority(Arrays.binarySearch(SchedulerGenerator.burstTime, list[i][1]));
			p.setName(names.charAt(i)+"");
			q.add(p);
		}
		return q;
	}
	
	/*
	 * Create processCount random processes and add to a priority queue
	 * 
	 * @return q A PriorityQueue ordered with lowest arrival time first
	 */
	public static PriorityQueue<Process> generateProcesses(int[][] list) {
		String names = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

		// Use a priority queue to order processes by arrival time (IMPORTANT!!)
		PriorityQueue<Process> q = new PriorityQueue<Process>();
		

		// Generate new processes and add to the process queue
		for (int i = 0; i < list.length; ++i) {
			Process p = new Process();
			p.setArrivalTime(list[i][0]);
			p.setBurstTime(list[i][1]);
			p.setPriority(1);
			String temp = list[i][2]+"";
			if(temp.charAt(0)=='1')
			{
				p.setName('A'+temp.substring(1));
			}
			else if(temp.charAt(0)=='2')
			{
				p.setName('B'+temp.substring(1));
			}
			else
			{
				p.setName('C'+temp.substring(1));
			}
			
			q.add(p);
		}
		return q;
	}
}
