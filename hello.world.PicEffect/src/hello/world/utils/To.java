package hello.world.utils;

import android.content.Context;
/**
 * @since 2015 06 24
 * @author funbee {@link https://github.com/hellofunbee/gifEffect}
 */
public class To {
	    /** 
	     * �����ֻ��ķֱ��ʴ� dip �ĵ�λ ת��Ϊ px(����) 
	     */  
	    public static int dip2px(Context context, float dpValue) {  
	        final float scale = context.getResources().getDisplayMetrics().density;  
	        return (int) (dpValue * scale + 0.5f);  
	    }  
	  
	    /** 
	     * �����ֻ��ķֱ��ʴ� px(����) �ĵ�λ ת��Ϊ dp 
	     */  
	    public static int px2dip(Context context, float pxValue) {  
	        final float scale = context.getResources().getDisplayMetrics().density;  
	        return (int) (pxValue / scale + 0.5f);  
	    }  
	  
}
