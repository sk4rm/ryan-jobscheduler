package scheduling.schedulers;

import scheduling.IScheduler;
import scheduling.Job;

import java.util.LinkedList;
import java.util.Queue;

public class FCFS implements IScheduler {
    private final Queue<Job> jobs = new LinkedList<>();

    @Override
    public void add(Job job) {
        jobs.add(job);
    }

    @Override
    public void execute() {
        var currentJob = jobs.peek();
        if (currentJob == null) return;

        var isDone = currentJob.execute();
        if (isDone) jobs.remove();

        for (var job : jobs) {
            if (job == currentJob) continue;
            job.await();
        }
    }

    @Override
    public boolean hasJobs() {
        return !jobs.isEmpty();
    }
}
