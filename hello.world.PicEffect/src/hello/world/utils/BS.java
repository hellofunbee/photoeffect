package hello.world.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
/**
 * @since 2015 06 24
 * @author funbee {@link https://github.com/hellofunbee/gifEffect}
 */
public class BS {
	public static Bitmap small(Bitmap bitmap,float f) {
		  Matrix matrix = new Matrix(); 
		  matrix.postScale(f,f); //���Ϳ�Ŵ���С�ı���
		  Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
		  return resizeBmp;
		 }
}
