package BowlingGame;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * Unit test for simple BowingGame.
 */
public class BowingGameTest {
	String[] testCores = {"X","4/","-9","8-"};
	@BeforeClass
	public static void init() {
		
	}
	
	
	@Test
	public void testGetBowingScore() {
		int num = getBowlingScore(testCores);
		System.out.println(num);
	}
	
	public int getBowlingScore(String[] cores) {
		int sum = 0;

		int len = cores.length;
		int[][] scores = createFrame(len, cores);

		// m<len-1
		for (int m = 0; m < (len == 10 ? len : len - 1); m++) {
			// ����Ϊ1ֻ����X
			if (cores[m].length() == 1) {
				if (scores[m + 1][0] == 10 && m + 2 < len)
					sum += 10 + scores[m + 1][0] + scores[m + 2][0];
				else if (m + 1 < len)
					sum += 10 + scores[m + 1][0] + scores[m + 1][1];
			} else {
				// ����Ϊ2������������ڶ���Ϊ/(��һ�λ���+�ڶ��λ���=10)���ڶ��β�Ϊ/
				if (scores[m][0] + scores[m][1] == 10 && m + 1 < len) {
					sum += 10 + scores[m + 1][0];
				} else {
					sum += scores[m][0] + scores[m][1];
				}
			}
		}

		return sum;
	}

	@Test
	public void testCreateFrame() {
		int[][] temp = createFrame(4,testCores);
		for(int i=0; i<4; i++) {
			for(int j=0; j<2; j++) {
				System.out.print(temp[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public int[][] createFrame(int len, String[] cores) {
		int[][] frame = new int[len][2];

		// ��ʼ����ά����
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < 2; j++) {
				frame[i][j] = 0;
			}
		}

		for (int i = 0; i < len; i++) {
			if (cores[i].length() == 2) {
				frame[i][0] = resever(0, cores[i].charAt(0));
				frame[i][1] = resever(frame[i][0], cores[i].charAt(1));
			} else {
				frame[i][0] = resever(0, cores[i].charAt(0));
			}
		}
		return frame;
	}

	@Test
	public void testResever(){
		char[] ch = {'X','/','-','9'};
		for(int i=0; i<ch.length; i++) {
			System.out.print(resever(4,ch[i])+" ");
		}
	}
	public int resever(int num, char ch) {
		int res = 0;
		switch (ch) {
		case 'X':
			res = 10;
			break;
		case '/':
			res = 10 - num;
			break;
		case '-':
			res = 0;
			break;
		//��char 7ת��Ϊint 7
		default:
			res = Integer.parseInt(String.valueOf(ch));
		}
		return res;
	}
	
	@AfterClass
	public static void destory() {
		
	}
}
