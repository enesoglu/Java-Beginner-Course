package com.epam.rd.autotasks.sprintplanning;

import com.epam.rd.autotasks.sprintplanning.tickets.Bug;
import com.epam.rd.autotasks.sprintplanning.tickets.Ticket;
import com.epam.rd.autotasks.sprintplanning.tickets.UserStory;

public class Sprint {
    private final int capacity;
    private final int ticketsLimit;
    private final Ticket[] tickets;
    private int ticketCount;
    private int totalEstimate;

    public Sprint(int capacity, int ticketsLimit) {
        this.capacity = capacity;
        this.ticketsLimit = ticketsLimit;
        this.tickets = new Ticket[ticketsLimit];
        this.ticketCount = 0;
        this.totalEstimate = 0;
    }

    private boolean canAcceptTicket(Ticket ticket) {
        if (ticket == null ||
                ticket.isCompleted() ||
                ticketCount >= ticketsLimit ||
                (totalEstimate + ticket.getEstimate()) > capacity) {
            return false;
        }
        return true;
    }

    public boolean addUserStory(UserStory userStory) {
        if (!canAcceptTicket(userStory)) {
            return false;
        }

        if (userStory.getDependencies() != null) {
            for (UserStory dependency : userStory.getDependencies()) {
                if (!dependency.isCompleted()) {
                    boolean dependencyInSprint = false;
                    for (int i = 0; i < ticketCount; i++) {
                        if (tickets[i] == dependency) {
                            dependencyInSprint = true;
                            break;
                        }
                    }
                    if (!dependencyInSprint) {
                        return false;
                    }
                }
            }
        }

        tickets[ticketCount++] = userStory;
        totalEstimate += userStory.getEstimate();
        return true;
    }

    public boolean addBug(Bug bugReport) {
        if (!canAcceptTicket(bugReport)) {
            return false;
        }

        tickets[ticketCount++] = bugReport;
        totalEstimate += bugReport.getEstimate();
        return true;
    }

    public Ticket[] getTickets() {
        Ticket[] defensiveCopy = new Ticket[ticketCount];
        for (int i = 0; i < ticketCount; i++) {
            defensiveCopy[i] = tickets[i];
        }
        return defensiveCopy;
    }

    public int getTotalEstimate() {
        return totalEstimate;
    }
}
