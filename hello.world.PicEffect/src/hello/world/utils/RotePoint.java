package hello.world.utils;

public class RotePoint {
	/**
	 * �����P(x,y)��X��������ļн�
	 * 
	 * @param x������
	 * @param y������
	 * @return �нǻ���
	 */
	private static double radPOX(float x, float y) {
		// P��(0,0)�����
		if (x == 0 && y == 0)
			return 0;

		// P���ĸ��������ϵ������x����x����y����y��
		if (y == 0 && x > 0)
			return 0;
		if (y == 0 && x < 0)
			return Math.PI;
		if (x == 0 && y > 0)
			return Math.PI / 2;
		if (x == 0 && y < 0)
			return Math.PI / 2 * 3;

		// ���ڵ�һ����������������ʱ�����
		if (x > 0 && y > 0)
			return Math.atan(y / x);
		if (x < 0 && y > 0)
			return Math.PI - Math.atan(y / -x);
		if (x < 0 && y < 0)
			return Math.PI + Math.atan(-y / -x);
		if (x > 0 && y < 0)
			return Math.PI * 2 - Math.atan(-y / x);

		return 0;
	}

	/**
	 * 
	 * @param P
	 *            ����ת������
	 * @param A
	 *            ��ת��������
	 * @param rad
	 *            ��ת����
	 * @param isClockwise
	 *            true:˳ʱ��
	 * @return
	 */
	public static MPoint rotatePoint(MPoint P, MPoint A, double rad,
			boolean isClockwise) {
		MPoint Temp1 = new MPoint(P.x - A.x, P.y - A.y);
		float lenO2Temp1 = Temp1.distanceTo(new MPoint(0, 0));
		float angT1OX = (float) radPOX(Temp1.x, Temp1.y);
		double angT2OX = angT1OX - (isClockwise ? 1 : -1) * rad;
		MPoint Temp2 = new MPoint((float) (lenO2Temp1 * Math.cos(angT2OX)),
				(float) (lenO2Temp1 * Math.sin(angT2OX)));
		return new MPoint(Temp2.x + A.x, Temp2.y + A.y);
	}

	public static int rad(MPoint A, MPoint B, MPoint C) {
		int r = 1;
		float c = A.distanceTo(B);
		float a = B.distanceTo(C);
		float b = C.distanceTo(A);
		if (a < 50 || b < 50) {
			return 0;
		}
		int rotaD = (int) (Math.acos((a * a + b * b - c * c) / (2 * a * b)) * 180 / Math.PI);
		double d1 = radPOX(A.x - C.x, -A.y + C.y);
		double d2 = radPOX(B.x - C.x, -B.y + C.y);

		if (d1 - d2 < -3) {
			return rotaD;
		}

		if (d1 - d2 > 3) {
			return -rotaD;
		}

		if (d1 < d2) {

			return -rotaD;
		} else {

			return rotaD;
		}

	}
	public static float rad2(MPoint A, MPoint B, MPoint C) {
		int r = 1;
		float c = A.distanceTo(B);
		float a = B.distanceTo(C);
		float b = C.distanceTo(A);
		float rotaD =(float) (Math.acos((a * a + b * b - c * c) / (2 * a * b)) * 180 / Math.PI);
		double d1 = radPOX(A.x - C.x, -A.y + C.y);
		double d2 = radPOX(B.x - C.x, -B.y + C.y);
		
		if (d1 - d2 < -3) {
			return rotaD;
		}
		
		if (d1 - d2 > 3) {
			return -rotaD;
		}
		
		if (d1 < d2) {
			
			return -rotaD;
		} else {
			
			return rotaD;
		}
		
	}
	public static double radPOY(float x, float y) {
		// P��(0,0)�����
		
		if (x == 0 && y == 0)
			return 0;

		// P���ĸ��������ϵ������x����x����y����y��
		if (y == 0 && x > 0)
			return Math.PI / 2;
		if (y == 0 && x < 0)
			return Math.PI/2*3;
		if (x == 0 && y > 0)
			return Math.PI ;
		if (x == 0 && y < 0)
			return 0;

		// ���ڵ�һ����������������ʱ�����
		if (x > 0 && y > 0)
			return Math.atan(y / x)+Math.PI / 2;
		if (x < 0 && y > 0)
			return Math.PI - Math.atan(y / -x)+Math.PI / 2;
		if (x < 0 && y < 0)
			return Math.PI + Math.atan(-y / -x)+Math.PI / 2;
		if (x > 0 && y < 0)
			return Math.atan(-y / x);

		return 0;
	}

}
