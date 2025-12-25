package com.epam.rd.autotasks;

public class TaskCarousel {

    private final int capacity;
    private final Task[] tasks;
    private int count = 0;
    private int taskIndex = 0;

    public TaskCarousel(int capacity) {
        this.capacity = capacity;
        this.tasks = new Task[capacity];
    }

    public boolean addTask(Task task) {
        if (task == null || task.isFinished() || isFull()) {
            return false;
        }

        this.tasks[count] = task;
        this.count++;
        return true;
    }

    public boolean execute() {
        if (isEmpty()) {
            return false;
        }

        if (taskIndex >= count) {
            taskIndex = 0;
        }

        Task currentTask = tasks[taskIndex];
        currentTask.execute();

        if (currentTask.isFinished()) {
            int elementsToMove = count - taskIndex - 1;
            if (elementsToMove > 0) {
                     System.arraycopy(tasks, taskIndex + 1, tasks, taskIndex, elementsToMove);
            }

            count--;
            tasks[count] = null;

        } else {
            taskIndex++;
        }

        return true;
    }

    public boolean isFull() {
        if (this.capacity == this.count) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        if (this.count == 0) {
            return true;
        }
        return false;
    }

}
