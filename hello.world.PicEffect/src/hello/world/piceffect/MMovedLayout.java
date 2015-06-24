package hello.world.piceffect;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
/**
 * @since 2015 06 24
 * @author funbee {@link https://github.com/hellofunbee/gifEffect}
 */
public class MMovedLayout extends RelativeLayout {

	public MMovedLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}

}
