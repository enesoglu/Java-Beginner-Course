package com.epam.rd.autotasks.collections;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;

public class BirthJournalManagementImpl implements BirthJournalManagement {

    private final Map<WeekDay, List<Baby>> journal = new EnumMap<>(WeekDay.class);
    private boolean isCommitted = false;

    @Override
    public boolean addEntryOfBaby(WeekDay day, Baby baby) {
        if (isCommitted) {
            return false;
        }
        if (!journal.containsKey(day)) {
            journal.put(day, new ArrayList<>());
        }
        journal.get(day).add(baby);
        return true;
    }

    @Override
    public void commit() {
        this.isCommitted = true;
    }

    @Override
    public int amountBabies() {
        int count = 0;
        for (List<Baby> babies : journal.values()) {
            count += babies.size();
        }
        return count;
    }

    @Override
    public List<Baby> findBabyWithHighestWeight(String gender) {
        double maxWeight = -1.0;
        boolean foundAny = false;

        for (List<Baby> dailyBabies : journal.values()) {
            for (Baby baby : dailyBabies) {
                if (baby.getGender().equals(gender)) {
                    if (!foundAny || baby.getWeight() > maxWeight) {
                        maxWeight = baby.getWeight();
                        foundAny = true;
                    }
                }
            }
        }

        List<Baby> result = new ArrayList<>();
        if (!foundAny) {
            return Collections.unmodifiableList(result);
        }

        for (List<Baby> dailyBabies : journal.values()) {
            for (Baby baby : dailyBabies) {
                if (baby.getGender().equals(gender) && Double.compare(baby.getWeight(), maxWeight) == 0) {
                    result.add(baby);
                }
            }
        }

        Collections.sort(result, new Comparator<Baby>() {
            @Override
            public int compare(Baby b1, Baby b2) {
                return b1.getName().compareTo(b2.getName());
            }
        });

        return Collections.unmodifiableList(result);
    }

    @Override
    public List<Baby> findBabyWithSmallestHeight(String gender) {
        int minHeight = Integer.MAX_VALUE;
        boolean foundAny = false;

        for (List<Baby> dailyBabies : journal.values()) {
            for (Baby baby : dailyBabies) {
                if (baby.getGender().equals(gender)) {
                    if (baby.getHeight() < minHeight) {
                        minHeight = baby.getHeight();
                        foundAny = true;
                    }
                }
            }
        }

        List<Baby> result = new ArrayList<>();
        if (!foundAny) {
            return Collections.unmodifiableList(result);
        }

        for (List<Baby> dailyBabies : journal.values()) {
            for (Baby baby : dailyBabies) {
                if (baby.getGender().equals(gender) && baby.getHeight() == minHeight) {
                    result.add(baby);
                }
            }
        }

        Collections.sort(result, new Comparator<Baby>() {
            @Override
            public int compare(Baby b1, Baby b2) {
                return Double.compare(b1.getWeight(), b2.getWeight());
            }
        });

        return Collections.unmodifiableList(result);
    }

    @Override
    public Set<Baby> findBabiesByBirthTime(String from, String to) {
        Set<Baby> result = new HashSet<>();
        int fromMinutes = parseTimeToMinutes(from);
        int toMinutes = parseTimeToMinutes(to);

        for (List<Baby> dailyBabies : journal.values()) {
            for (Baby baby : dailyBabies) {
                int babyMinutes = parseTimeToMinutes(baby.getTime());
                if (babyMinutes >= fromMinutes && babyMinutes <= toMinutes) {
                    result.add(baby);
                }
            }
        }
        return result;
    }

    private int parseTimeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
}