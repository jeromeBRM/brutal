package brutal.model.comparators;

import java.util.*;

import brutal.model.core.IStudent;

public class SortStudentsByInitiative implements Comparator<IStudent> {

	@Override
	public int compare(IStudent s1, IStudent s2) {
		return s1.getInitiative() - s2.getInitiative();
	}

}