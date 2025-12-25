package com.epam.rd.autotasks.sprintplanning.tickets;

public class UserStory extends Ticket {

    private final UserStory[] dependsOn;

    public UserStory(int id, String name, int estimate, UserStory... dependsOn) {
        super(id, name, estimate);
        this.dependsOn = dependsOn;
    }

    @Override
    public void complete() {
        if (dependsOn != null) {
            for (UserStory dependency : dependsOn) {
                if (!dependency.isCompleted()) {
                    return;
                }
            }
        }
        super.complete();
    }

    public UserStory[] getDependencies() {
        if (dependsOn == null) {
            return new UserStory[0];
        }
        UserStory[] defensiveCopy = new UserStory[dependsOn.length];
        for (int i = 0; i < dependsOn.length; i++) {
            defensiveCopy[i] = dependsOn[i];
        }
        return defensiveCopy;
    }

    @Override
    public String toString() {
        return "[US " + getId() + "] " + getName();
    }
}

