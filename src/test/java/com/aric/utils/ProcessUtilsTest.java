/**
 * 
 */
package com.aric.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.aric.akka.ProcessWork;

/**
 * @author dursun
 *
 */
public class ProcessUtilsTest {
	private static final Integer numberOfParallelProcessors = 10;
	
	/**
	 * Test method for {@link com.aric.akka.ProcessUtils#partitionToBatches(java.util.List, java.lang.Integer)}.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testPartitionToBatchesForEmptyIds() {
		ProcessWork[] partitionToBatches = ProcessUtils.partitionToBatches(null, numberOfParallelProcessors);
		assertNotNull(partitionToBatches);
		assertTrue(partitionToBatches.length==0);
		
		List<Long> ids = (List<Long>)Collections.EMPTY_LIST;
		partitionToBatches = ProcessUtils.partitionToBatches(ids, numberOfParallelProcessors);
		assertNotNull(partitionToBatches);
		assertTrue(partitionToBatches.length==0);
		
		partitionToBatches = ProcessUtils.partitionToBatches(new ArrayList<Long>(), numberOfParallelProcessors);
		assertNotNull(partitionToBatches);
		assertTrue(partitionToBatches.length==0);
	}
	/**
	 * Test method for {@link com.aric.akka.ProcessUtils#partitionToBatches(java.util.List, java.lang.Integer)}.
	 */
	@Test
	public void testPartitionToBatchesForNonEmptyIdsWithRemaining() {
		List<Long> ids = new ArrayList<Long>(113);
		for (int i=0;i<113;i++){
			ids.add(new Long(i));
		}
		ProcessWork[] partitionToBatches = ProcessUtils.partitionToBatches(ids, numberOfParallelProcessors);
		assertEquals(numberOfParallelProcessors, new Integer(partitionToBatches.length));
		for (int i = 0; i < partitionToBatches.length - 1; i++) {
			assertEquals(12, partitionToBatches[i].getIdList().size());
		}
		assertEquals(5, partitionToBatches[numberOfParallelProcessors-1].getIdList().size());
		
	}
	/**
	 * Test method for {@link com.aric.akka.ProcessUtils#partitionToBatches(java.util.List, java.lang.Integer)}.
	 */
	@Test
	public void testPartitionToBatchesForNonEmptyIdsWithoutRemaining() {
		List<Long> ids = new ArrayList<Long>(110);
		for (int i=0;i<110;i++){
			ids.add( new Long(i));
		}
		ProcessWork[] partitionToBatches = ProcessUtils.partitionToBatches(ids, numberOfParallelProcessors);
		assertEquals(numberOfParallelProcessors, new Integer(partitionToBatches.length));
		for (int i = 0; i < partitionToBatches.length - 1; i++) {
			assertEquals(11, partitionToBatches[i].getIdList().size());
		}
		assertEquals(11, partitionToBatches[numberOfParallelProcessors-1].getIdList().size());
	}

}
