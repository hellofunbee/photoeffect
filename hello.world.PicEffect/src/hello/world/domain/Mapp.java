package hello.world.domain;

import java.io.File;
import java.util.List;
import java.util.Map;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.os.Environment;
import android.widget.Toast;

public class Mapp extends Application {
	private String addr;
	public float scale;
	public boolean run = false;
	public int h, w;
	public List<Bitmap> freams;
	public boolean stopNow;
	public Map<Integer, Bitmap> map;
	public int key;
	public SharedPreferences sp;
	public int bgColor = Color.parseColor("#FFFFcc");
	public String path;
	public PaintFlagsDrawFilter pdf = new PaintFlagsDrawFilter(0,
			Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
	public List<String> textList;
	@Override
	public void onCreate() {
		
		
		
		path = Environment.getExternalStorageDirectory() + "/helloworld/";
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}
		sp = getSharedPreferences("location", Mapp.MODE_PRIVATE);
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED))
			Toast.makeText(this, "sd is not MOUNTED", Toast.LENGTH_LONG).show();
		
		super.onCreate();
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Bitmap getBitmap(int bitmapStyle) {
		if (map.get(bitmapStyle) == null) {
			return BitmapFactory.decodeResource(getResources(), bitmapStyle);
		} else {
			return map.get(bitmapStyle);
		}
	}
}
