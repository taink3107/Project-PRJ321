package day7.Ex3_remake2;

public class Task {
    private int task_begin;
    private int task_end;

    public Task(int task_begin, int task_end) {
        this.task_begin = task_begin;
        this.task_end = task_end;
    }

    public int getTask_begin() {
        return task_begin;
    }

    public void setTask_begin(int task_begin) {
        this.task_begin = task_begin;
    }

    public int getTask_end() {
        return task_end;
    }

    public void setTask_end(int task_end) {
        this.task_end = task_end;
    }

    @Override
    public String toString() {
        return "Task{" +
                "task_begin=" + task_begin +
                ", task_end=" + task_end +
                '}';
    }
}
