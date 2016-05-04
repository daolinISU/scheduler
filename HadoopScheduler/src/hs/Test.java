package hs;

public class Test {

	public static void main(String[] args) {
		Job a = new Job(1, 0, 4, 5, "");
		Job b = new Job(2, 1, 2, 7, "");
		Job	c = new Job(3, 3, 3, 6, "");
		
		EDF edf = new EDF();
		SRT srt = new SRT();
		
		
		//--------  Earliest deadline ---------
		//模拟时间流逝
		int jobEnd = 0;
		for (int i = 0; i < 100; i++) {
			if (i == 0){
				edf.add(a);
			}
			
			if (i == 1){
				edf.add(b);
			}
			
			if (i == 3){
				edf.add(c);
			}
			
			if(jobEnd == i && !edf.queue.isEmpty()){
				// get job to run until it finishes
				Job j = edf.queue.poll();
				jobEnd = i + j.runningTime;
				System.out.println(j.index + " is running;");
			}
			
		}
		
		//------  Shortest Running Time -----------
		jobEnd = 0;
		for (int i = 0; i < 100; i++) {
			if (i == 0){
				srt.add(a);
			}
			
			if (i == 1){
				srt.add(b);
			}
			
			if (i == 3){
				srt.add(c);
			}
			
			if(jobEnd == i && !srt.queue.isEmpty()){
				// get job to run until it finishes
				Job j = srt.queue.poll();
				jobEnd = i + j.runningTime;
				System.out.println(j.index + " is running;");
			}
			
		}

	}

}
