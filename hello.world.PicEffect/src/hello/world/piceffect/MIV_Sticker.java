package hello.world.piceffect;

import hello.world.domain.Mapp;
import hello.world.piceffect.FD_Sticker.OnTexted;
import hello.world.utils.MPoint;
import hello.world.utils.RotePoint;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
/**
 * @since 2015 06 24
 * @author funbee {@link https://github.com/hellofunbee/gifEffect}
 */
public class MIV_Sticker extends ImageView {

	private final int STATUS_Touch_SINGLE = 1;
	private final int STATUS_TOUCH_MULTI_START = 2;
	private final int STATUS_TOUCH_MULTI_TOUCHING = 3;

	private int mStatus = STATUS_Touch_SINGLE;

	private final int defaultCropWidth = 300;
	private final int defaultCropHeight = 300;
	private int cropWidth = defaultCropWidth;
	private int cropHeight = defaultCropHeight;

	private final int EDGE_LT = 1;
	private final int EDGE_RB = 2;
	private final int EDGE_MOVE_IN = 3;
	private final int EDGE_MOVE_OUT = 4;
	private final int EDGE_NONE = 5;

	public int currentEdge = EDGE_NONE;
	protected float oriRationWH = 0;
	protected final float maxZoomOut = 5.0f;
	protected final float minZoomIn = 0.333333f;
	protected FD_Sticker fd;
	protected Rect mDrawableSrc = new Rect();
	protected Rect mDrawableDst = new Rect();
	protected Rect rt = new Rect();
	protected boolean isFrist = true;
	private boolean isTouchInSquare = true;

	protected Context mContext;
	private float oldDis;
	private float nowDis;
	private Matrix m;
	private int nx;
	private int ny;
	private float ox = 0;
	private float oy = 0;
	private int dx;
	private int dy;
	private float rad;
	private float x0;
	private float y0;
	private int id;
	private boolean seted;
	private Mapp app;

	public MIV_Sticker(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	@SuppressLint("NewApi")
	private void init(Context context) {
		this.mContext = context;
		try {
			if (android.os.Build.VERSION.SDK_INT >= 11) {
				this.setLayerType(LAYER_TYPE_SOFTWARE, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setStickerId(Mapp app, int id) {
		seted = true;
		this.app = app;
		this.id = id;
		fd = new FD_Sticker(app);
		fd.setBitmap(BitmapFactory.decodeResource(getResources(), id), app);
		m = new Matrix();
		this.cropWidth = 300;
		this.cropHeight = 300;
		this.isFrist = true;
		fd.setOnTexted(new OnTexted() {
			@Override
			public void show() {
				invalidate();
			}
		});
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getPointerCount() > 1) {
			if (mStatus == STATUS_Touch_SINGLE) {
				mStatus = STATUS_TOUCH_MULTI_START;
			} else if (mStatus == STATUS_TOUCH_MULTI_START) {
				mStatus = STATUS_TOUCH_MULTI_TOUCHING;
			}
		} else {
			if (mStatus == STATUS_TOUCH_MULTI_START
					|| mStatus == STATUS_TOUCH_MULTI_TOUCHING) {
				ox = event.getX();
				oy = event.getY();
			}

			mStatus = STATUS_Touch_SINGLE;
		}

		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:
			ox = event.getX();
			oy = event.getY();
			currentEdge = getTouchEdge((int) ox, (int) oy);

			if (EDGE_LT == currentEdge) {
				if (onRemove != null)
					onRemove.remove();
				return true;

			}
			isTouchInSquare = rt.contains((int) event.getX(),
					(int) event.getY());
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_POINTER_1_DOWN:
			break;
		case MotionEvent.ACTION_POINTER_UP:
			currentEdge = EDGE_NONE;
			break;
		case MotionEvent.ACTION_MOVE:
			if (mStatus == STATUS_TOUCH_MULTI_TOUCHING) {

			} else if (mStatus == STATUS_Touch_SINGLE) {
				nx = (int) event.getX();
				ny = (int) event.getY();
				dx = (int) (nx - ox);
				dy = (int) (ny - oy);
				switch (currentEdge) {
				case EDGE_RB:
					x0 = (rt.left + rt.right) / 2;
					y0 = (rt.top + rt.bottom) / 2;
					rad += RotePoint.rad2(new MPoint(ox, oy),
							new MPoint(nx, ny), new MPoint(x0, y0));
					rad = rad % 360;

					nowDis = (new MPoint(x0, y0).distanceTo(new MPoint(nx, ny)));
					oldDis = (new MPoint(x0, y0).distanceTo(new MPoint(ox, oy)));
					int dd = (int) ((nowDis - oldDis) * Math.sin(Math.PI / 4));
					if (rt.right - rt.left + dd == 0) {
						return true;
					}
					rt.set(rt.left - dd, rt.top - dd, rt.right + dd, rt.bottom
							+ dd);
					m.reset();
					m.postRotate(rad, x0, y0);
					fd.setMatrix(m);
					break;
				case EDGE_MOVE_IN:
					if (isTouchInSquare) {
						rt.offset((int) dx, (int) dy);
						x0 = (rt.left + rt.right) / 2;
						y0 = (rt.top + rt.bottom) / 2;
						m.reset();
						m.postRotate(rad, x0, y0);
						fd.setMatrix(m);
					}
					break;
				case EDGE_MOVE_OUT:
					break;
				}
				ox = nx;
				oy = ny;
				rt.sort();
				invalidate();
			}
			break;
		}
		return true;
	}
	public int getTouchEdge(int x, int y) {
		ps = new float[] { x, y };
		mm = new Matrix(m);
		mm.invert(mm);
		mm.mapPoints(ps);
		x = (int) ps[0];
		y = (int) ps[1];
		if ((fd.getBounds().right - fd.getW()) <= x && x < fd.getBounds().right
				&& (fd.getBounds().bottom - fd.getH()) <= y
				&& y < fd.getBounds().bottom) {
			if (fd.hide) {
				fd.hide = false;
			}
			invalidate();
			return EDGE_RB;// 右下
		} else if (fd.getBounds().left <= x
				&& x < (fd.getBounds().left + fd.getW())
				&& fd.getBounds().top <= y
				&& y < (fd.getBounds().top + fd.getH())) {
			if (fd.hide) {
				fd.hide = false;
			}
			invalidate();
			return EDGE_LT;// 左上
		} else if (fd.getBounds().contains(x, y)) {
			if (fd.hide) {
				fd.hide = false;
			}
			invalidate();
			return EDGE_MOVE_IN;// 里面移动
		}

		fd.hide = true;
		invalidate();
		return EDGE_MOVE_OUT;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (seted) {
			canvas.setDrawFilter(app.pdf);

			configureBounds();
			fd.draw(canvas);
		}
	}

	protected void configureBounds() {
		if (isFrist) {
			oriRationWH = 1;

			final float scale = mContext.getResources().getDisplayMetrics().density;
			int w = Math.min(getWidth(), (int) (200
					* scale + 0.5f));
			int h = (int) (w / oriRationWH);

			int left = (getWidth() - w) / 2;
			int top = (getHeight() - h) / 2;
			int right = left + w;
			int bottom = top + h;

			mDrawableSrc.set(left, top, right, bottom);
			mDrawableDst.set(mDrawableSrc);

			int floatWidth = dipTopx(mContext, cropWidth);
			int floatHeight = dipTopx(mContext, cropHeight);

			if (floatWidth > getWidth()) {
				floatWidth = getWidth();
				floatHeight = cropHeight * floatWidth / cropWidth;
			}

			if (floatHeight > getHeight()) {
				floatHeight = getHeight();
				floatWidth = cropWidth * floatHeight / cropHeight;
			}

			int floatLeft = (getWidth() - floatWidth) / 2;
			int floatTop = (getHeight() - floatHeight) / 2;
			rt.set(floatLeft, floatTop, floatLeft + floatWidth, floatTop
					+ floatHeight);
			isFrist = false;
		}

		fd.setBounds(rt);
	}

	public int dipTopx(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public void addText(CharSequence s) {
		fd.setText(s, id);
	}

	public void hide() {
		fd.hide = true;
		invalidate();
	}

	private OnRemove onRemove;
	private float[] ps;
	private Matrix mm;

	public void setOnRemove(OnRemove remove) {
		this.onRemove = remove;
	}

	public interface OnRemove {
		void remove();
	}

}
