package com.epam.rd.autotasks;

public class CompleteByRequestTask implements Task {

    private boolean finished = false;
    private boolean completed = false;

    @Override
    public void execute() {
        if (this.completed) {
            this.finished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return this.finished;
    }

    public void complete() {
        this.completed = true;
    }
}
