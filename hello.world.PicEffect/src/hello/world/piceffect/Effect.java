package hello.world.piceffect;

import hello.world.domain.Mapp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;
/**
 * @since 2015 06 24
 * @author funbee {@link https://github.com/hellofunbee/gifEffect}
 */
public class Effect extends CropIV {
	private boolean notInitial = true;
	private Mapp app;
	private Bitmap alterbm;
	private Matrix m;

	private static final int NONE = 0;
	private static final int CORNOR = 3;
	private static final int EDGE = 4;
	private static final int AND = 5;
	private static final int TEXT = 6;
	private static final int CUT = 7;
	private static final int STICKER = 8;

	private int dw;
	private int dh;
	private Paint paint;
	private int sh;
	private int sw;

	public Effect(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (android.os.Build.VERSION.SDK_INT >= 11) {
			setLayerType(LAYER_TYPE_SOFTWARE, null);
		}
		paint = new Paint();
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		paint.setAntiAlias(true);

	}

	public void setBitmap(Mapp app, Bitmap bm, int sw, int sh) {
		this.sw = sw;
		this.sh = sh;

		this.alterbm = bm;
		this.app = app;
		notInitial = false;
		setImageBitmap(bm);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (notInitial) {
			return;
		}
		canvas.setDrawFilter(app.pdf);
		super.onDraw(canvas);
	}

	private boolean edged;
	private float roundPx;
	private Canvas c;
	private int effect_mode;
	private boolean corner;
	private Bitmap backupbm;
	private Matrix backupm;
	private float scale;

	private void tempSave(Bitmap tempbm, Matrix tempm) {
		this.backupbm = Bitmap.createBitmap(tempbm);
		if (tempm != null) {
			this.backupm = new Matrix(tempm);
		}
	}

	public void corner() {
		if (corner) {
			return;
		}
		this.effect_mode = CORNOR;
		alterbm = toRoundCorner(alterbm);
		corner = true;
		invalidate();
	}

	public void edge(int[] frames) {
		if (edged) {
			return;
		}
		this.effect_mode = EDGE;
		alterbm = combinateFrame(alterbm, frames);
		edged = true;
		setImageBitmap(alterbm);
		invalidate();
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void and(int id) {
		this.effect_mode = AND;
		setBackGround();
		alterbm = decodeBitmap(id);
		m = new Matrix();

		invalidate();
	}

	

	public Bitmap toRoundCorner(Bitmap bitmap) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		c = new Canvas(output);
		c.setDrawFilter(app.pdf);

		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		if (roundPx == 0) {
			roundPx = bitmap.getWidth() / 10;
		}
		c.drawARGB(0, 0, 0, 0);
		c.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		c.drawBitmap(bitmap, rect, rect, paint);
		paint.setXfermode(null);
		return output;

	}

	private Bitmap combinateFrame(Bitmap bm, int[] res) {
		Bitmap bmp = decodeBitmap(res[0]);
		final int smallW = bmp.getWidth();
		final int smallH = bmp.getHeight();

		final int bigW = bm.getWidth();
		final int bigH = bm.getHeight();

		int wCount = (int) Math.ceil(bigW * 1.0 / smallW);
		int hCount = (int) Math.ceil(bigH * 1.0 / smallH);

		int newW = (wCount + 2) * smallW;
		int newH = (hCount + 2) * smallH;

		Bitmap newBitmap = Bitmap.createBitmap(newW, newH, Config.ARGB_8888);
		c = new Canvas(newBitmap);
		Paint p = new Paint();
		p.setColor(Color.TRANSPARENT);
		c.drawRect(new Rect(0, 0, newW, newH), p);

		Rect rect = new Rect(smallW, smallH, newW - smallW, newH - smallH);
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		c.drawRect(rect, paint);

		c.drawBitmap(bm, (newW - bigW - 2 * smallW) / 2 + smallW,
				(newH - bigH - 2 * smallH) / 2 + smallH, null);

		int startW = newW - smallW;
		int startH = newH - smallH;
		Bitmap leftTopBm = decodeBitmap(res[0]); // 左上�?
		Bitmap rightTopBm = decodeBitmap(res[1]); // 右上�?
		Bitmap leftBottomBm = decodeBitmap(res[2]); // 左下�?
		Bitmap rightBottomBm = decodeBitmap(res[3]); // 右下�?

		c.drawBitmap(leftTopBm, 0, 0, null);
		c.drawBitmap(leftBottomBm, 0, startH, null);
		c.drawBitmap(rightBottomBm, startW, startH, null);
		c.drawBitmap(rightTopBm, startW, 0, null);

		leftTopBm.recycle();
		leftTopBm = null;
		leftBottomBm.recycle();
		leftBottomBm = null;
		rightBottomBm.recycle();
		rightBottomBm = null;
		rightTopBm.recycle();
		rightTopBm = null;

		// 绘上下边�?
		Bitmap bottomBm = decodeBitmap(res[5]);
		Bitmap topBm = decodeBitmap(res[4]);
		for (int i = 0, length = wCount; i < length; i++) {
			int w = smallW * (i + 1);
			c.drawBitmap(bottomBm, w, startH, null);
			c.drawBitmap(topBm, w, 0, null);
		}

		bottomBm.recycle();
		bottomBm = null;
		topBm.recycle();
		topBm = null;
		// 绘左右边�?
		Bitmap leftBm = decodeBitmap(res[6]);
		Bitmap rightBm = decodeBitmap(res[7]);
		for (int i = 0, length = hCount; i < length; i++) {
			int h = smallH * (i + 1);
			c.drawBitmap(leftBm, 0, h, null);
			c.drawBitmap(rightBm, startW, h, null);
		}

		leftBm.recycle();
		leftBm = null;
		rightBm.recycle();
		rightBm = null;

		c.save(Canvas.ALL_SAVE_FLAG);
		c.restore();

		return newBitmap;
	}

	private Bitmap decodeBitmap(int i) {
		return BitmapFactory.decodeResource(getResources(), i);
	}

	@SuppressWarnings("deprecation")
	private void setBackGround() {
		Bitmap temp = Bitmap.createBitmap(dw, dh, Config.ARGB_8888);
		c = new Canvas(temp);
		c.setDrawFilter(app.pdf);
		this.draw(c);
		this.setBackgroundDrawable(new BitmapDrawable(getResources(), temp));
	}

	private Bitmap and() {
		Bitmap picBitmap = ((BitmapDrawable) this.getBackground()).getBitmap();
		this.setBackgroundDrawable(null);
		Bitmap maskBitmap = Bitmap.createBitmap(dw, dh, Config.ARGB_8888);
		c = new Canvas(maskBitmap);
		c.setDrawFilter(app.pdf);
		this.draw(c);

		int[] picPixels = new int[dw * dh];
		int[] maskPixels = new int[dw * dh];

		picBitmap.getPixels(picPixels, 0, dw, 0, 0, dw, dh);
		maskBitmap.getPixels(maskPixels, 0, dw, 0, 0, dw, dh);

		for (int i = 0; i < maskPixels.length; i++) {
			if (maskPixels[i] == 0x00000000) {
				picPixels[i] = 0x00000000;
			} else {
				maskPixels[i] &= 0xff000000;
				picPixels[i] &= 0x00ffffff;
				picPixels[i] |= maskPixels[i];
			}
		}
		return Bitmap.createBitmap(picPixels, dw, dh, Config.ARGB_8888);
	}

	private void stickerIt(MMovedLayout iv_window) {
		int childnum = iv_window.getChildCount();
		Canvas lc;
		for (int i = childnum - 1; i > 0; i--) {
			View v = iv_window.getChildAt(i);
			c = new Canvas(alterbm);
			c.setDrawFilter(app.pdf);

			m = new Matrix();
			m.postScale(1 / scale, 1 / scale);
			m.postTranslate(v.getLeft() / scale, v.getTop() / scale);

			Bitmap bm = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
					Config.ARGB_8888);
			lc = new Canvas(bm);
			v.draw(lc);
			c.drawBitmap(bm, m, null);
			bm.recycle();
			bm = null;
		}
		lc = null;
		setImageBitmap(alterbm);
		if (onNeedFree != null) {
			onNeedFree.free();
		}
	}

	public Bitmap getBitmap() {
		return alterbm;
	}

	public void setImage(Bitmap result) {
		setImageBitmap(result);
		alterbm = result;

	}

	public void save() {
		switch (effect_mode) {
		case CORNOR:
			this.effect_mode = NONE;
			break;
		case EDGE:
			this.effect_mode = NONE;
			break;
		case AND:
			alterbm = and();
			m = new Matrix();
			invalidate();
			this.effect_mode = NONE;
			break;
		
		case CUT:
			alterbm = getCropImage();
			setImageBitmap(alterbm);
			this.effect_mode = NONE;
		default:

			break;
		}

	}

	public void stacker(MMovedLayout iv_window) {
		stickerIt(iv_window);
		this.effect_mode = NONE;
	}

	

	public void cancel() {
		if (backupbm == null) {
			return;
		}
		switch (effect_mode) {
		case CORNOR:
			rollBack();
			corner = false;
			break;
		case EDGE:
			rollBack();
			edged = false;
			break;
		case AND:
			this.setBackgroundDrawable(null);
			rollBack();
			break;
		case TEXT:
			this.setBackgroundDrawable(null);
			rollBack();
			break;
		case CUT:
			setStat(false);

		default:
			rollBack();
			break;
		}

	}

	public void saveToSD() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			alterbm.compress(Bitmap.CompressFormat.PNG, 100, baos);
			byte[] byteArray = baos.toByteArray();

			FileOutputStream fstream = new FileOutputStream(app.path
					+ SystemClock.uptimeMillis() + "a.png");
			BufferedOutputStream bStream = new BufferedOutputStream(fstream);

			bStream.write(byteArray);

			if (bStream != null) {
				bStream.close();
			}

			Toast.makeText(app, "save ok", 0).show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void reverse(int position) {
		// TODO Auto-generated method stub
		tempSave(alterbm, null);
		if (position == 0) {
			Bitmap bm = Bitmap.createBitmap(alterbm.getWidth(),
					alterbm.getHeight(), Config.ARGB_8888);
			Canvas c = new Canvas(bm);
			Matrix m = new Matrix();
			m.postScale(-1.0f, 1.0f);
			m.postTranslate(alterbm.getWidth(), 0);
			c.drawBitmap(alterbm, m, null);
			setImageBitmap(bm);
			alterbm = bm;
			// m.setValues(values)
		} else {
			Bitmap bm = Bitmap.createBitmap(alterbm.getWidth(),
					alterbm.getHeight(), Config.ARGB_8888);
			Canvas c = new Canvas(bm);
			Matrix m = new Matrix();
			m.postScale(1.0f, -1.0f);
			m.postTranslate(0, alterbm.getHeight());
			c.drawBitmap(alterbm, m, null);
			setImageBitmap(bm);
			alterbm = bm;
		}

	}


	public void backUp() {
		tempSave(alterbm, m);
	}

	public void rollBack() {

		if (backupbm != null) {
			alterbm = Bitmap.createBitmap(backupbm);
			backupbm = null;
		}
		if (backupm != null) {
			m = new Matrix(backupm);
			backupm = null;
		}

		setImageBitmap(alterbm);
	}

	public void releaseBackUp() {
		backupbm = null;
		m = null;
	}

	public void cut() {
		this.effect_mode = CUT;
		setDrawable(new BitmapDrawable(getResources(), alterbm), 200, 200);
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	private OnNeedFreeListiner onNeedFree;

	public void setOnNeedFree(OnNeedFreeListiner onNeedFree) {
		this.onNeedFree = onNeedFree;
	}

	public interface OnNeedFreeListiner {
		void free();
	}

}