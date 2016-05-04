package hs;

import java.util.PriorityQueue;

public abstract class Scheduler {
	PriorityQueue<Job> queue;
	
	public abstract void initQueue();
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}
	
	public void add(Job job){
		queue.offer(job);
	}
	
	public Job getJob(){
		return queue.poll();
	}
	
}
