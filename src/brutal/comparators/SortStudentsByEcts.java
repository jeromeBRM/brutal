package brutal.comparators;

import java.util.*;

import brutal.IStudent;

public class SortStudentsByEcts implements Comparator<IStudent> {

	@Override
	public int compare(IStudent s1, IStudent s2) {
		return s1.getEcts() - s2.getEcts();
	}

}