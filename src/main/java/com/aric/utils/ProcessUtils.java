/**
 * 
 */
package com.aric.utils;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.aric.akka.ProcessWork;

/**
 * @author dursun
 * 
 */
public final class ProcessUtils {
	
	
	public static ProcessWork[] partitionToBatches(List<Long> ids,
			Integer numberOfParallelProcessors) {
		if (CollectionUtils.isEmpty(ids)) {
			return new ProcessWork[0];
		}
		int maxCapacityOfResult = (int) Math.ceil(((double) ids.size())
				/ ((double) numberOfParallelProcessors));

		ProcessWork[] result = new ProcessWork[(int) numberOfParallelProcessors];
		int start = 0;
		int end = maxCapacityOfResult;
		for (int i = 0; i < result.length; i++) {
			result[i] = new ProcessWork(ids.subList(start, end));
			start = end;
			end = Math.min(end + maxCapacityOfResult, ids.size());
		}

		return result;
	}
}
