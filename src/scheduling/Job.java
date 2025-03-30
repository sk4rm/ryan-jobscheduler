package scheduling;

public class Job {
    private final int burstTime;
    private int progress;
    private int waitingTime;

    public Job(int burstTime) {
        assert burstTime > 0 : "burstTime must be greater than 0";
        this.burstTime = burstTime;
    }

    public boolean execute() {
        progress++;
        return progress >= burstTime;
    }

    public void await() {
        waitingTime++;
    }

    public int burstTime() {
        return burstTime;
    }

    public int waitingTime() {
        return waitingTime;
    }
}
