package com.epam.rd.autocode.collections;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class StudentGradebookImpl implements StudentGradebook {

	private final Map<Student, Map<String, BigDecimal>> map;
	private final Comparator<Student> studentComparator;

	public StudentGradebookImpl() {
		this.studentComparator = new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				
				int result = s1.getLastName().compareTo(s2.getLastName());
				if (result != 0) {
					return result;
				}
				result = s1.getFirstName().compareTo(s2.getFirstName());
				if (result != 0) {
					return result;
				}
				return s1.getGroup().compareTo(s2.getGroup());
			}
		};

		this.map = new TreeMap<>(studentComparator);
	}

	@Override
	public boolean addEntryOfStudent(Student student, String discipline, BigDecimal grade) {
		if (!map.containsKey(student)) {
			map.put(student, new HashMap<>());
		}

		Map<String, BigDecimal> grades = map.get(student);

		if (grades.containsKey(discipline) && grades.get(discipline).equals(grade)) {
			return false;
		}

		grades.put(discipline, grade);
		return true;
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public Comparator<Student> getComparator() {
		return this.studentComparator;
	}

	@Override
	public List<String> getStudentsByDiscipline(String discipline) {
		List<String> result = new ArrayList<>();

		for (Map.Entry<Student, Map<String, BigDecimal>> entry : map.entrySet()) {
			Student student = entry.getKey();
			Map<String, BigDecimal> grades = entry.getValue();

			if (grades.containsKey(discipline)) {
				BigDecimal grade = grades.get(discipline);
				String formatted = student.getFirstName() + "_" + student.getLastName() + ": " + grade;
				result.add(formatted);
			}
		}
		return result;
	}

	@Override
	public Map<Student, Map<String, BigDecimal>> removeStudentsByGrade(BigDecimal grade) {
		Map<Student, Map<String, BigDecimal>> removedStudents = new TreeMap<>(studentComparator);

		Iterator<Map.Entry<Student, Map<String, BigDecimal>>> iterator = map.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<Student, Map<String, BigDecimal>> entry = iterator.next();
			Student student = entry.getKey();
			Map<String, BigDecimal> grades = entry.getValue();

			boolean shouldRemove = false;

			for (BigDecimal g : grades.values()) {
				if (g.compareTo(grade) < 0) {
					shouldRemove = true;
					break;
				}
			}

			if (shouldRemove) {
				removedStudents.put(student, grades);
				iterator.remove();
			}
		}

		return removedStudents;
	}

	@Override
	public Map<BigDecimal, List<Student>> getAndSortAllStudents() {
		Map<BigDecimal, List<Student>> result = new TreeMap<>();

		for (Map.Entry<Student, Map<String, BigDecimal>> entry : map.entrySet()) {
			Student student = entry.getKey();
			Map<String, BigDecimal> grades = entry.getValue();

			if (grades.isEmpty()) {
				continue;
			}

			BigDecimal sum = BigDecimal.ZERO;
			for (BigDecimal g : grades.values()) {
				sum = sum.add(g);
			}

			BigDecimal average = sum.divide(new BigDecimal(grades.size()), 1, RoundingMode.HALF_UP);

			if (!result.containsKey(average)) {
				result.put(average, new ArrayList<>());
			}
			result.get(average).add(student);
		}

		return result;
	}
}