package hello.world.domain;

import HaoRan.ImageFilter.IImageFilter;

public class FilterInfo {
	public int filterID;
	public IImageFilter filter;

	public FilterInfo(int filterID, IImageFilter filter) {
		this.filterID = filterID;
		this.filter = filter;
	}
}
