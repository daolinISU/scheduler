package hs;

import java.util.Comparator;
import java.util.PriorityQueue;


public class EDF extends Scheduler {
	
	

	/**
	 * 
	 */
	public EDF() {
		super();
		initQueue();
	}

	@Override
	public void initQueue() {
		this.queue = new PriorityQueue<Job>(10, new Comparator<Job>() {

			@Override
			public int compare(Job o1, Job o2) {
				return o1.deadline - o2.deadline;
			}			
		});

	}

}
