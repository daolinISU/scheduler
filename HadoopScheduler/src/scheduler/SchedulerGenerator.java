package scheduler;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.StringTokenizer;

public class SchedulerGenerator {
	public final int jobNum = 300;
	/*
	 * For high workload, we test with three tasks: Task1: input = input1(burst
	 * time = 4400), period = 6600, index = 1 Task2: input = input2(burst time =
	 * 1500), period = 2250, index = 2 Task3: input = input3(burst time =
	 * 10400), period = 15600, index = 3 For medium workload, we test with three
	 * tasks: Task1: input = input1(burst time =4400), period = 13200, index = 1
	 * Task2: input = input2(burst time = 1500), period = 4500, index = 2 Task3:
	 * input = input3(burst time = 10400), period = 31200, index = 3 For low
	 * workload, we test with three tasks: Task1: input = input1(burst time =
	 * 4400), period = 13200, index = 1 Task2: input = input2(burst time =
	 * 1500), period = 45000, index = 2 Task3: input = input3(burst time =
	 * 10400), period = 312000, index = 3
	 * 
	 * Total 300 tasks
	 */
	public int[] input;
	public static final int[] burstTime = { 4400, 1500, 10400 };
	public int[][] q;
	/**
	 * 
	 * @param input contains period setting for each job
	 */
	public SchedulerGenerator(int[] input) {
		this.input = input;
	}
	/**
	 * 
	 * @return array q with q[i][0] is arrive time
	 * 						q[i][1] is running time
	 */
	public int[][] getQueue() {
		// int[][] q = new int[jobNum][2];
		int[] candidates = { 0, 0, 0 };
		for (int i = 0; i < jobNum; i++) {
			int pick = choose(candidates);
			q[i][0] = candidates[pick];
			q[i][1] = burstTime[pick];
			candidates[pick] += input[pick];

		}
		return q;
	}

	public static int choose(int[] candidates) {
		if (candidates[2] < candidates[1] && candidates[2] < candidates[0]) {
			return 2;
		}
		if (candidates[1] < candidates[0]) {
			return 1;
		}
		return 0;
	}

	@SuppressWarnings("resource")
	public static int[][] dataQueue(String file) {

		// get how many rows
		LineNumberReader lnr;
		int rowNum = 0;
		try {
			lnr = new LineNumberReader(new FileReader(new File(file)));
			lnr.skip(Long.MAX_VALUE);
			System.out.println(lnr.getLineNumber() + 1); // Add 1 because line
															// index starts at 0

			rowNum = (lnr.getLineNumber() + 1) / 2;

			// Finally, the LineNumberReader object should be closed to prevent
			// resource leak
			lnr.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int[][] queue = new int[rowNum][3];
		int count1=0;
		int count2=0;
		int count3=0;
		int i = 0;

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			int lineNum = 0;
			while ((line = br.readLine()) != null) {
				// skip empty line
				if (line.length() < 2)
					continue;
				//System.out.println(lineNum++ + ": " + line);
				// System.out.println(line.length());
				String[] job = line.split("\\s+");
				// arrive time
				String arrTime = job[2].substring(job[2].length()-7, job[2].length());
				queue[i][0] = Integer.parseInt(arrTime);
				queue[i][1] = Integer.parseInt(job[0]);
				if(job[1].equals("input"))
				{
					count1++;
					String temp = 1+""+count1;
					queue[i][2]=Integer.parseInt(temp);
					//System.out.println(temp);
				}
				else if(job[1].equals("input2"))
				{
					count2++;
					String temp = 2+""+count1;
					queue[i][2]=Integer.parseInt(temp);
					//System.out.println(temp);
				}
				
				else
				{
					count3++;
					String temp = 3+""+count1;
					queue[i][2]=Integer.parseInt(temp);
					//System.out.println(temp);
				}
				// StringTokenizer st = new StringTokenizer(line.trim(), " ");
				// while(st.hasMoreTokens()) {
				// System.out.println(st.nextToken());
				// }
				i++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return queue;
	}

	public static void main(String[] args) {
		// int[] input = {6600, 2250, 15600};
		// SchedulerGenerator gen = new SchedulerGenerator(input);
		//
		// for (int[] job : gen.getQueue()) {
		// System.out.println("Arrive time: " + job[0] + "    burstTime = " +
		// job[1]);
		// }

		dataQueue("data.txt");
	}

}
