package hs;

public class Job {

	public int index;
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
	public Job(int index, int arriveTime, int runningTime, int deadline, String dataFile) {
		super();
		this.index = index;
		this.arriveTime = arriveTime;
		this.runningTime = runningTime;
		this.deadline = deadline;
		this.dataFile = dataFile;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Job [index=" + index + ", arriveTime=" + arriveTime
				+ ", startTime=" + startTime + ", runningTime=" + runningTime
				+ ", deadline=" + deadline + ", dataFile=" + dataFile + "]";
	}
	
	




}
