package scheduler;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HadoopScheduler {
	/**
	 * 
	 * @param input
	 * @param nodeNum
	 * @return array of {startTime, runningTime, deadline}
	 */
	public static Job[] EDFSchedule(SchedulerGenerator s) {
		Job[] res = new Job[s.jobNum];
		Job[] jobs = new Job[s.jobNum];
		int[][] input = s.getQueue();
		//input getting from Scheduler Generator are already sorted 
		for (int i = 0; i < input.length; i++) {
			int deadline = input[i][0] + getPeriod(input[i][1], s);
			jobs[i] = new Job(input[i][0], input[i][1], deadline);
		}
		
		PriorityQueue<Job> q = new PriorityQueue<Job>(10, new Comparator<Job>() {

			@Override
			public int compare(Job o1, Job o2) {
				return o1.deadline - o2.deadline;
			}			
		});
		for(Job job : jobs){
			q.add(job);
		}
		
		
		for (int i = 0; i < res.length; i++) {
			res[i] = q.poll();
		}
		
		
		return res;
	}
	
	
	/**
	 * 
	 * @param input
	 * @param nodeNum
	 * @return array of {startTime, runningTime, deadline}
	 */
	public static Job[] ShortestRunning(SchedulerGenerator s) {
		Job[] res = new Job[s.jobNum];
		Job[] jobs = new Job[s.jobNum];
		int[][] input = s.getQueue();
		//input getting from Scheduler Generator are already sorted 
		for (int i = 0; i < input.length; i++) {
			int deadline = input[i][0] + getPeriod(input[i][1], s);
			jobs[i] = new Job(input[i][0], input[i][1], deadline);
		}
		
		PriorityQueue<Job> q = new PriorityQueue<Job>(10, new Comparator<Job>() {

			@Override
			/**
			 * returns shortest running time on priorityqueue
			 */
			public int compare(Job o1, Job o2) {
				return o1.runningTime - o2.runningTime;
			}			
		});
		
		int curTime = 0;
		int idx = 0;
		int curIdx = 0;
		while(curIdx < s.jobNum) {
			//add all job arrived before this job ends
			while(idx < s.jobNum && jobs[idx].arriveTime <= curTime) {
				q.offer(jobs[idx]);
				idx++;
			}
			res[curIdx] = q.poll();
			//update job finishing time
			curTime += res[curIdx].runningTime;
			curIdx++;
		}
		
		return res;
	}
	
	

	private static int getPeriod(int runningTime, SchedulerGenerator s) {
		
		int index = Arrays.binarySearch(s.burstTime, runningTime);
		if (index < 0 || index > 2) throw new IllegalArgumentException("wrong running time");
		
		return s.input[index];
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
}
