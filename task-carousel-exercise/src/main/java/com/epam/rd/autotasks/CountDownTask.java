package com.epam.rd.autotasks;

public class CountDownTask implements Task{

    private int value;

    public CountDownTask(int value) {
        if (value < 0){
            this.value = 0;
        } else {
            this.value = value;
        }
    }

    public int getValue() {
        return this.value;
    }


    @Override
    public void execute() {
        if (this.value > 0){
            this.value--;
        }
    }

    @Override
    public boolean isFinished() {
        if (this.value == 0){
            return true;
        } else {
            return false;
        }
    }
}
