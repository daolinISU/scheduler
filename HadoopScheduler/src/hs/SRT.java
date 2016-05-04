package hs;

import java.util.Comparator;
import java.util.PriorityQueue;


public class SRT extends Scheduler {

	/**
	 * 
	 */
	public SRT() {
		super();
		initQueue();
	}
	
	@Override
	public void initQueue() {
		this.queue = new PriorityQueue<Job>(10, new Comparator<Job>() {

			@Override
			/**
			 * returns shortest running time on priorityqueue
			 */
			public int compare(Job o1, Job o2) {
				return o1.runningTime - o2.runningTime;
			}			
		});

	}

}
