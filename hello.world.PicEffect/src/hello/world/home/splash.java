package hello.world.home;

import java.io.File;

import hello.world.builder.piceffect.R;
import hello.world.domain.Mapp;
import hello.world.piceffect.PhotoEffect;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
/**
 * @since 2015 06 24
 * @author funbee {@link https://github.com/hellofunbee/gifEffect}
 */
public class splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		app = (Mapp) getApplication();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_2);
	}

	static final int REQUEST_VIDEO_CAPTURE = 2;
	static final int REQUEST_PHOTO_CAPTURE = 1;
	static final int REQUEST_CAMERA_CAPTURE = 0;
	private Mapp app;

	public void camera(View v) {

		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT,
				Uri.fromFile(new File(app.path, "tmp_img")));

		startActivityForResult(intent, REQUEST_CAMERA_CAPTURE);

		// overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
	}

	public void photo(View v) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		startActivityForResult(intent, REQUEST_PHOTO_CAPTURE);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == RESULT_OK && requestCode == REQUEST_PHOTO_CAPTURE) {
			Uri uri = data.getData();
			String[] proj = { MediaStore.Images.Media.DATA };
			Cursor cursor = managedQuery(uri, proj, null, null, null);
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			String path = cursor.getString(column_index);
			Intent intent = new Intent(this, PhotoEffect.class);
			intent.putExtra("path", path);
			startActivity(intent);

		}
		if (resultCode == RESULT_OK && requestCode == REQUEST_CAMERA_CAPTURE) {
			String path = app.path + "tmp_img";

			Intent intent = new Intent(this, PhotoEffect.class);
			intent.putExtra("path", path);
			startActivity(intent);

		}

		// if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK)
		// {
		// Uri videoUri = data.getData();
		// Intent intent = new Intent(Splash.this, VidioActivity.class);
		// intent.putExtra("path", videoUri);
		// startActivity(intent);
		// // mVideoView.setVideoURI(videoUri);
		// }
	}

}