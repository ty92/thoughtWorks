package BowlingGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BowlingGame {
	public static void main(String[] args) {
		int bowlingScore = 0;
		String[] cores = null;

		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			String res = is.readLine().replaceAll("\\|", " ");
			cores = res.split("\\s+");
		} catch (IOException e) {
			e.printStackTrace();
		}

		bowlingScore = getBowlingScore(cores);
		System.out.println("score sum is:" + bowlingScore);
	}

	public static int getBowlingScore(String[] cores) {
		int sum = 0;

		int len = cores.length;
		int[][] scores = createFrame(len, cores);

		// m<len-1
		for (int m = 0; m < (len == 10 ? len : len - 1); m++) {
			// 长度为1只能是X
			if (cores[m].length() == 1) {
				if (scores[m + 1][0] == 10 && m + 2 < len)
					sum += 10 + scores[m + 1][0] + scores[m + 2][0];
				else if (m + 1 < len)
					sum += 10 + scores[m + 1][0] + scores[m + 1][1];
			} else {
				// 长度为2，两种情况：第二次为/(第一次击球+第二次击球=10)，第二次不为/
				if (scores[m][0] + scores[m][1] == 10 && m + 1 < len) {
					sum += 10 + scores[m + 1][0];
				} else {
					sum += scores[m][0] + scores[m][1];
				}
			}
		}

		return sum;
	}

	public static int[][] createFrame(int len, String[] cores) {
		int[][] frame = new int[len][2];

		// 初始化二维数组
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

	// 字符转换int
	public static int resever(int num, char ch) {
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
		//将char 7转换为int 7
		default:
			res = Integer.parseInt(String.valueOf(ch));
		}
		return res;
	}
}
