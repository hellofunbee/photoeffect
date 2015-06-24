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

/**
 * @since 2015 06 24
 * @author funbee {@link https://github.com/hellofunbee/gifEffect}
 */
public class Mapp extends Application {
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
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED))
			Toast.makeText(this, "sd is not MOUNTED", Toast.LENGTH_LONG).show();

		super.onCreate();
	}

}
