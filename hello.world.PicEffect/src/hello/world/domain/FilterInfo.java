package hello.world.domain;

import HaoRan.ImageFilter.IImageFilter;

/**
 * @since 2015 06 24
 * @author funbee {@link https://github.com/hellofunbee/gifEffect}
 */
public class FilterInfo {
	public int filterID;
	public IImageFilter filter;

	public FilterInfo(int filterID, IImageFilter filter) {
		this.filterID = filterID;
		this.filter = filter;
	}
}
