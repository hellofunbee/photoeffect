package hello.world.piceffect;

import hello.world.builder.piceffect.R;
import hello.world.domain.Mapp;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.Layout.Alignment;

public class FD_Sticker extends Drawable {

	private Drawable d_tool;
	private Drawable d_dele;
	private Paint mLinePaint = new Paint();
	private BitmapDrawable drawableBm;
	private boolean setted;
	private Matrix m;
	private int w;
	private int h;
	private Bitmap alterbm;
	private int id;
	private Mapp app;
	public boolean hide;
	private int width;
	private int height;

	{
		mLinePaint.setARGB(200, 50, 50, 50);
		mLinePaint.setStrokeWidth(1F);
		mLinePaint.setStyle(Paint.Style.STROKE);
		mLinePaint.setAntiAlias(true);
		mLinePaint.setColor(Color.WHITE);
	}

	public FD_Sticker(Mapp app) {
		super();
		this.app = app;
		init();

	}

	private void init() {
		Bitmap bm = BitmapFactory.decodeResource(app.getResources(),
				R.drawable.rotate_tool);
		Bitmap dele = BitmapFactory.decodeResource(app.getResources(),
				R.drawable.dele_tool);

		d_tool = new BitmapDrawable(app.getResources(), bm);
		d_dele = new BitmapDrawable(app.getResources(), dele);
		w = bm.getWidth();
		h = bm.getHeight();
		m = new Matrix();
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.setMatrix(m);
		int left = getBounds().left;
		int top = getBounds().top;
		int right = getBounds().right;
		int bottom = getBounds().bottom;
		Rect mRect = new Rect(left + w / 2, top + h / 2, right - w / 2, bottom
				- h / 2);
		if (!hide) {
			canvas.drawRect(mRect, mLinePaint);

			d_dele.setBounds(left, top, left + w, top + h);
			d_dele.draw(canvas);

			d_tool.setBounds(right - w, bottom - h, right, bottom);
			d_tool.draw(canvas);
		}
		if (!setted)
			return;
		drawableBm.setBounds(mRect);
		drawableBm.draw(canvas);

	}

	@Override
	public void setBounds(Rect bounds) {
		super.setBounds(new Rect(bounds.left - w / 2, bounds.top - h / 2,
				bounds.right + w / 2, bounds.bottom + h / 2));
	}

	@Override
	public void setAlpha(int alpha) {

	}

	@Override
	public void setColorFilter(ColorFilter cf) {

	}

	@Override
	public int getOpacity() {
		return 0;
	}

	public void setBitmap(Bitmap bitmap, Context c) {
		bitmap = bitmap.copy(Config.ARGB_8888, true);
		alterbm = bitmap;
		this.drawableBm = new BitmapDrawable(c.getResources(), bitmap);
		this.width = alterbm.getWidth();
		this.height = alterbm.getHeight();
		setted = true;
	}

	public void setMatrix(Matrix m) {
		this.m = m;
	}

	public void setText(CharSequence s, int id) {
		this.id = id;
		addText(s);
	}

	private TextPaint textPaint;
	private StaticLayout layout;
	private Canvas c;
	private int oldl;

	public void addText(CharSequence s) {
		if (textPaint == null) {
			textPaint = new TextPaint();
		}
		textPaint.setARGB(0xFF, 0, 0, 0xFF);
		textPaint.setTextSize(width / 10);
		if (s.length() < oldl) {
			alterbm = reget(id);
		}
		layout = new StaticLayout(s, textPaint, (int)(width*0.6f),
				Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
		c = new Canvas(alterbm);
		c.setDrawFilter(app.pdf);
		c.translate(1*width/5, 1*height/5);
		layout.draw(c);
		oldl = s.length();

		this.drawableBm = new BitmapDrawable(app.getResources(), alterbm);
		if (onTexted != null) {
			onTexted.show();
		}
	}

	private Bitmap reget(int id) {
		alterbm = BitmapFactory.decodeResource(app.getResources(), id);
		alterbm = alterbm.copy(Config.ARGB_8888, true);
		return alterbm;
	}

	private OnTexted onTexted;

	public void setOnTexted(OnTexted onTexted) {
		this.onTexted = onTexted;
	}

	public interface OnTexted {
		void show();
	}

}
