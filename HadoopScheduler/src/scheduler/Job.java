package scheduler;

import java.util.Comparator;

public class Job {

	public int arriveTime;
	public int startTime = -1; // set to be -1 before scheduler decides its schedule
	public int runningTime;
	public int deadline;
	public String dataFile = null;
	
	

	/**
	 * @param arriveTime
	 * @param runningTime
	 * @param deadline
	 * @param dataFile
	 */
	public Job(int arriveTime, int runningTime, int deadline, String dataFile) {
		super();
		this.arriveTime = arriveTime;
		this.runningTime = runningTime;
		this.deadline = deadline;
		this.dataFile = dataFile;
	}




}
