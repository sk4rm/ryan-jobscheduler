package scheduling;

public interface IScheduler {
    void add(Job job);

    void execute();

    boolean hasJobs();
}