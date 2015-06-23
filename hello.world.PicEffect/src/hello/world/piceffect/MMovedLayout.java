package hello.world.piceffect;

import hello.world.domain.Mapp;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;
import android.widget.RelativeLayout;

public class MMovedLayout extends RelativeLayout {
	private int mw;
	private int mh;
	private Context context;
	private Mapp app;
	private GestureDetector detector;

	public MMovedLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		app = (Mapp) context.getApplicationContext();
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}

}
