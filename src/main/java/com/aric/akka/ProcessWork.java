/**
 * 
 */
package com.aric.akka;

import java.util.List;

/**
 * @author dursun
 * 
 */
public class ProcessWork {
	private List<Long> idList;

	public ProcessWork(List<Long> idList) {
		this.idList = idList;
	}
	
	public List<Long> getIdList() {
		return idList;
	}
}
