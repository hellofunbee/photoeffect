package hello.world.piceffect;

import hello.world.builder.piceffect.R;
import hello.world.domain.Mapp;
import hello.world.horilist.HorizontalListView;
import hello.world.horilist.HorizontalListViewAdapter;
import hello.world.paints.Filters;
import hello.world.paints.Pens;
import hello.world.piceffect.Effect.OnNeedFreeListiner;
import hello.world.piceffect.MIV_Sticker.OnRemove;
import hello.world.utils.LPHelper_Large;
import hello.world.utils.To;

import java.io.FileNotFoundException;

import HaoRan.ImageFilter.IImageFilter;
import HaoRan.ImageFilter.Image;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
/**
 * @since 2015 06 24
 * @author funbee {@link https://github.com/hellofunbee/gifEffect}
 */
public class PhotoEffect extends Activity implements OnClickListener {

	private static final int NONE = 0;
	private static final int FILTER = 5;
	private static final int SIZE = 1;
	private static final int ROTATE = 2;
	private static final int TEXT = 3;
	private static final int STICKER = 4;
	private static final int EDGE = 6;
	private Button corner;
	private Button and;
	private Mapp app;
	private Effect meffect;
	private int sw;
	private int sh;
	private Bitmap editBm;
	private MMovedLayout iv_window;
	private int mode = NONE;
	private RelativeLayout rl_actions;
	private int sx;
	private int sy;
	private int fw;
	private int fh;
	private int w;
	private int h;
	float scale;
	private EditText edit_text;
	private RelativeLayout rl_ivWindow;
	private MIV_Sticker iv_sticker;
	private boolean running;
	private View view;
	private GridView gridView;
	private View rl_sticker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		sw = getWindowManager().getDefaultDisplay().getWidth();
		sh = getWindowManager().getDefaultDisplay().getHeight()
				- getStatusBarHeight();
		setContentView(R.layout.activity_piceffect);
		app = (Mapp) getApplication();

		String path = getIntent().getStringExtra("path");

		rl_ivWindow = (RelativeLayout) findViewById(R.id.rl_ivWindow);
		rl_actions = (RelativeLayout) findViewById(R.id.rl_actions);
		iv_window = (MMovedLayout) findViewById(R.id.iv_window);

		meffect = (Effect) findViewById(R.id.meffect);
		meffect.setOnNeedFree(new OnNeedFreeListiner() {
			@Override
			public void free() {
				PhotoEffect.this.free();
			}
		});
		editBm = LPHelper_Large.loadBitmap(path, true, sw, sh);
		w = editBm.getWidth();
		h = editBm.getHeight();
		fw = sw;
		fh = sh - To.dip2px(app, 90);

		initLayout(w, h, fw, fh);

		editBm = editBm.copy(Config.ARGB_8888, true);
		meffect.setBitmap(app, editBm, sw, sh);

		init();
	}

	private void initLayout(int w, int h, int fw, int fh) {
		float fatherShape = fw / (float) fh;
		float picShape = w / (float) h;
		if (fatherShape > picShape) {
			scale = fh / (float) h;
			sx = (int) ((fw - w * scale) / 2);
			sy = 0;
			RelativeLayout.LayoutParams lp = (LayoutParams) iv_window
					.getLayoutParams();
			lp.setMargins(sx, sy, sx, sy);
			lp.width = (int) (w * scale);
			lp.height = fh;
		} else {
			scale = fw / (float) w;
			sx = 0;
			sy = (int) ((fh - h * scale) / 2);
			RelativeLayout.LayoutParams lp = (LayoutParams) iv_window
					.getLayoutParams();
			lp.height = (int) (h * scale);
			lp.width = fw;
			lp.setMargins(sx, sy, sx, sy);

		}
		meffect.setScale(scale);
	}

	public int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height",
				"dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	private void init() {
		findViewById(R.id.back).setOnClickListener(this);
		findViewById(R.id.photos).setOnClickListener(this);
		findViewById(R.id.roll_back).setOnClickListener(this);
		findViewById(R.id.for_word).setOnClickListener(this);
		findViewById(R.id.finish).setOnClickListener(this);
		findViewById(R.id.filter).setOnClickListener(this);
		findViewById(R.id.size).setOnClickListener(this);
		findViewById(R.id.rotate).setOnClickListener(this);
		findViewById(R.id.text).setOnClickListener(this);
		findViewById(R.id.sticker).setOnClickListener(this);
		findViewById(R.id.edge).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.back:
			editBm = null;
			finish();
			break;
		case R.id.photos:

			break;
		case R.id.roll_back:
			meffect.cancel();
			free();
			break;
		case R.id.for_word:
			switch (mode) {
			case SIZE:
				meffect.releaseBackUp();
				meffect.save();
				Bitmap bitmap = meffect.getBitmap();
				w = bitmap.getWidth();
				h = bitmap.getHeight();
				initLayout(w, h, fw, fh);
				free();
				break;
			case TEXT:
			case STICKER:
				iv_sticker.hide();
				meffect.releaseBackUp();
				meffect.stacker(iv_window);
				break;
			default:
				meffect.releaseBackUp();
				meffect.save();
				free();
				break;
			}
			break;
		case R.id.finish:
			meffect.releaseBackUp();
			meffect.saveToSD();
			free();
			break;
		case R.id.filter:
			meffect.backUp();
			mode = FILTER;
			LoadImageFilter();
			break;
		case R.id.size:
			meffect.backUp();
			meffect.cut();
			mode = SIZE;
			break;
		case R.id.rotate:
			meffect.backUp();
			mode = ROTATE;
			loadRotateChoice();
			break;
		case R.id.text:
			// meffect.backUp();
			if (running)
				return;
			mode = TEXT;
			drawText();
			break;
		case R.id.sticker:
			if (running)
				return;
			addSticker();
			mode = STICKER;
			break;
		case R.id.edge:
			edgeit();
			mode = EDGE;
		default:
			break;
		}
	}

	private void free() {
		running = false;
		int c_num = rl_ivWindow.getChildCount();
		if (c_num > 3) {
			for (int i = c_num - 1; i > 2; i--) {
				rl_ivWindow.removeViewAt(i);

			}
		}

		c_num = iv_window.getChildCount();
		if (c_num > 1) {
			for (int i = c_num - 1; i > 0; i--) {
				iv_window.removeViewAt(i);

			}
		}
		if (rl_actions.getChildCount() > 1) {
			rl_actions.removeViewAt(1);
		}
		view = null;
		gridView = null;
		rl_sticker = null;
	}

	private void loadRotateChoice() {

		final int[] rote = new int[] { R.drawable.hor, R.drawable.vti };
		gridView = addGridView(rote);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				meffect.reverse(position);

			}
		});
	}

	


	private GridView addGridView(final int[] ids) {
		view = View.inflate(app, R.layout.horizontal_gridview, null);
		gridView = (GridView) view.findViewById(R.id.gridview);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(sw,
				LinearLayout.LayoutParams.MATCH_PARENT);
		gridView.setLayoutParams(params);
		gridView.setColumnWidth(To.dip2px(app, 50));
		gridView.setHorizontalSpacing(10);
		gridView.setStretchMode(GridView.NO_STRETCH);
		gridView.setNumColumns(100);
		rl_actions.addView(view);
		
		gridView.setAdapter(new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view;
				if (convertView == null) {
					view =  View.inflate(app, R.layout.fiter_demo_item, null);
				} else {
					view =  convertView;
				}
				 ImageView iv = (ImageView) view.findViewById(R.id.item_demopic);
				iv.setImageResource(ids[position]);
				return view;
			}

			@Override
			public long getItemId(int position) {
				return 0;
			}

			@Override
			public Object getItem(int position) {
				return null;
			}

			@Override
			public int getCount() {
				return ids.length;
			}
		});

		return gridView;

	}

	private void edgeit() {
		final int[] edge_show = Pens.getPen_edge();
		gridView = addGridView(edge_show);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				meffect.edge(Pens.getEdgeFrames(edge_show[position]));

			}
		});

	}

	private void LoadImageFilter() {
		view = View.inflate(app, R.layout.horizon_scroll_view, null);
		HorizontalListView h_listView = (HorizontalListView) view
				.findViewById(R.id.h_listview);
		final HorizontalListViewAdapter hlva = new HorizontalListViewAdapter(
				app);
		hlva.notifyDataSetChanged();
		h_listView.setAdapter(hlva);
		rl_actions.addView(view);
		h_listView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						IImageFilter filter = Filters.getFilter(
								(int) hlva.getItemId(position), app);
						new ProcessImageTask(filter).execute();

					}
				});
	}

	private void addSticker() {
		final int[] sticker = Pens.getPen_stickers();
		gridView = addGridView(sticker);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (running) {
					return;
				}
				running = true;
				addStickerView(sticker[position]);

			}
		});

	}

	private void addStickerView(int id) {
		rl_sticker = View.inflate(app, R.layout.item_iv_sticker, null);
		iv_sticker = (MIV_Sticker) rl_sticker.findViewById(R.id.iv_sticker);
		iv_sticker.setStickerId(app, id);
		iv_window.addView(rl_sticker);
		iv_sticker.setOnRemove(new OnRemove() {

			@Override
			public void remove() {
				free();
			}
		});
	}

	private void drawText() {
		final int[] text_sticker = Pens.getPen_textStickers();
		gridView = addGridView(text_sticker);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (running) {
					return;
				}
				running = true;
				addTextView(text_sticker[position]);

			}
		});
	}

	private void addTextView(int id) {

		rl_sticker = View.inflate(app, R.layout.item_iv_sticker, null);
		iv_sticker = (MIV_Sticker) rl_sticker.findViewById(R.id.iv_sticker);
		iv_sticker.setStickerId(app, id);
		iv_window.addView(rl_sticker);

		View rl_edit_text = View.inflate(app, R.layout.item_edit_text, null);
		edit_text = (EditText) rl_edit_text.findViewById(R.id.edit_text);
		rl_ivWindow.addView(rl_edit_text);

		iv_sticker.setOnRemove(new OnRemove() {
			@Override
			public void remove() {
				free();
			}
		});
		setUI();

	}

	private void setUI() {
		edit_text.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				iv_sticker.addText(s);
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}
	public class ProcessImageTask extends AsyncTask<Void, Void, Bitmap> {
		private IImageFilter filter;
		public ProcessImageTask(IImageFilter imageFilter) {
			this.filter = imageFilter;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		public Bitmap doInBackground(Void... params) {
			Image img = null;
			try {
				img = new Image(meffect.getBitmap());
				if (filter != null) {
					img = filter.process(img);
					img.copyPixelsFromBuffer();
				}
				return img.getImage();
			} catch (Exception e) {
				if (img != null && img.destImage.isRecycled()) {
					img.destImage.recycle();
					img.destImage = null;
					System.gc(); 
				}
			} finally {
				if (img != null && img.image.isRecycled()) {
					img.image.recycle();
					img.image = null;
					System.gc(); 
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			if (result != null) {
				super.onPostExecute(result);
				meffect.setImage(result);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == 1) {
				Uri uri = data.getData();
				try {
					BitmapFactory.Options factory = new BitmapFactory.Options();
					factory.inJustDecodeBounds = true;
					Bitmap bmp = BitmapFactory.decodeStream(
							getContentResolver().openInputStream(uri), null,
							factory);
					int wRatio = (int) Math.ceil(factory.outWidth / (float) sw);
					int hRatio = (int) Math
							.ceil(factory.outHeight / (float) sh);
					if (wRatio > 1 || hRatio > 1) {
						if (hRatio > wRatio) {
							factory.inSampleSize = hRatio;
						} else {
							factory.inSampleSize = wRatio;
						}
					}
					factory.inJustDecodeBounds = false;
					bmp = BitmapFactory.decodeStream(getContentResolver()
							.openInputStream(uri), null, factory);
					meffect.setImage(bmp);

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} else if (requestCode == 0) { 
				Bitmap bmp = data.getParcelableExtra("data");
				meffect.setImage(bmp);
			}
		}
	}

}
