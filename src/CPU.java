import scheduling.IScheduler;
import scheduling.Job;
import scheduling.schedulers.FCFS;

import java.util.Scanner;

public class CPU {
    private final IScheduler scheduler;
    private int currentTime;

    public CPU(IScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public static void main(String[] args) {
        // User input

        var sc = new Scanner(System.in);

        System.out.print("Number of jobs: ");
        var jobs = new Job[sc.nextInt()];

        System.out.print("Burst times: ");
        for (var i = 0; i < jobs.length; i++) {
            jobs[i] = new Job(sc.nextInt());
        }

        // Driver

        var cpu = new CPU(new FCFS());
        for (var job : jobs) cpu.scheduleJob(job);

        cpu.run();

        // Output

        var totalWaitingTime = 0.0;
        var totalTurnaroundTime = 0.0;

        System.out.printf("%-15s%-20s%-20s%-20s\n", "Job", "Burst Time", "Waiting Time", "Turnaround Time");
        for (var i = 0; i < jobs.length; i++) {
            System.out.printf("%-15s%-20s%-20s%-20s\n", i, jobs[i].burstTime(), jobs[i].waitingTime(), jobs[i].burstTime() + jobs[i].waitingTime());
            totalWaitingTime += jobs[i].waitingTime();
            totalTurnaroundTime += jobs[i].waitingTime();
        }
        System.out.printf("\nTotal runtime: %d\n", cpu.totalRuntime());
        System.out.printf("Average Waiting Time: %.2f\n", totalWaitingTime / jobs.length);
        System.out.printf("Average Turnaround Time: %.2f\n", totalTurnaroundTime / jobs.length);
    }

    public void scheduleJob(Job job) {
        scheduler.add(job);
    }

    public void run() {
        currentTime = 0;
        while (scheduler.hasJobs()) {
            scheduler.execute();
            currentTime++;
        }
    }

    public int totalRuntime() {
        return currentTime;
    }
}
