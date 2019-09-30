package mmt.interview.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {
	
	private String url;
	
	
	private boolean isParallel;
	
	private String count;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isParallel() {
		return isParallel;
	}

	@JsonProperty("isParallel")
	public void setParallel(boolean isParallel) {
		this.isParallel = isParallel;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	
}
