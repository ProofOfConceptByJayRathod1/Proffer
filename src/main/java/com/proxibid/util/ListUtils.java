package com.proxibid.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

	/* Return a list of a fixed sized list */
	public static <T> List<List<T>> chunkList(List<T> list, int chunkSize) {
		if (chunkSize <= 0) {
			throw new IllegalArgumentException("Invalid chunk size: " + chunkSize);
		}
		List<List<T>> chunkList = new ArrayList<>(list.size() / chunkSize);
		for (int i = 0; i < list.size(); i += chunkSize) {
			chunkList.add(list.subList(i, i + chunkSize >= list.size() ? list.size() - 1 : i + chunkSize));
		}
		return chunkList;
	}
}
