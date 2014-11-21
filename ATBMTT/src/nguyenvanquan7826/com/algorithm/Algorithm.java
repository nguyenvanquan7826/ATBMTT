package nguyenvanquan7826.com.algorithm;

import java.util.ArrayList;

import nguyenvanquan7826.com.ConvertNumber;
import nguyenvanquan7826.com.main.Const;
import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.EditText;

@SuppressLint("DefaultLocale")
public class Algorithm {
	public ArrayList<String[]> axmodn(int a, int x, int n, int[] result) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		int d = 1;
		int xx = x;
		int aa = a;
		Log.d("axmodn", "x = " + x + "\ta = " + a + "\td = " + d);
		// "d=1");
		while (x != 0) {
			if (x % 2 != 0) {
				d = (d * a) % n;
			}
			list.add(new String[] { x + "", a + "", d + "" });

			x = x / 2;
			a = (a * a) % n;
			Log.d("axmodn", "x = " + x + "\ta = " + a + "\td = " + d);
		}
		list.add(new String[] { x + "", "", "" });
		list.add(new String[] { "Kết quả: " + aa + "^" + xx + " mod " + n
				+ " = " + d });
		result[0] = d;
		return list;
	}

	public ArrayList<String[]> phanTuNghichDao(int a, int n, int[] result) {
		ArrayList<String[]> list = new ArrayList<String[]>();

		int x0 = n, x1 = a, v0 = 0, v1 = 1, y = 0, temp;
		// String result = String.format("%-10s %-10s %-10s\n", "x", "v", "y");
		// result += String.format("%-10d %-10d %-10s\n", x0, v0, "");
		list.add(new String[] { x0 + "", v0 + "", "" });
		Log.d("phan tu nghich dao", "x= " + x0 + "\tv = " + v0 + "\ty = " + y);
		while (x1 != 0) {
			y = x0 / x1;
			temp = v1;
			v1 = v0 - v1 * y;
			v0 = temp;

			temp = x1;
			x1 = x0 % x1;
			x0 = temp;
			Log.d("phan tu nghich dao", "x= " + x0 + "\tv = " + v0 + "\ty = "
					+ y);
			// result += String.format("%-10d %-10d %-10d\n", x0, v0, y);
			list.add(new String[] { x0 + "", v0 + "", y + "" });
		}
		// result += String.format("%-10d %-10d\n", x1, v1);
		list.add(new String[] { x1 + "", "", "" });
		if (x0 == 1) {
			if (v0 > 0) {
				// result += String.format("Do v > 0\nNên %d^(-1) = %-10d\n", a,
				// v0, "");
				list.add(new String[] { "Do v > 0 Nên " + a + "^(-1) mod " + n
						+ " = " + v0 });
				result[0] = v0;
			} else {
				// result += String.format(
				// "Do v <= 0 \nNên %d^(-1) mod %d = %d + %d = %-10d\n",
				// a, n, n, v0, v0 + n, "");
				list.add(new String[] { "Do v <= 0 Nên " + a + "^(-1) mod " + n
						+ " = " + n + " + " + v0 + " = " + (n + v0) });
				result[0] = n + v0;
			}
		} else {
			// result += String
			// .format("Do x != 1 \nNên không tồn tại phần tử nghịch đảo của %d\n",
			// a);
			list.add(new String[] { "Do x != 1 \nNên không tồn tại phần tử nghịch đảo của "
					+ a });
			result[0] = -1;
		}
		return list;
	}

	public String createKeyRSA(int p, int q, int e) {
		int phi = (p - 1) * (q - 1);
		int n = p * q;
		int d[] = new int[1];
		phanTuNghichDao(e, phi, d);

		String result = "Tính n = " + p + " * " + q + " = " + n + "\n";
		result += "Φ(n) = " + (p - 1) + " * " + (q - 1) + " = " + phi + "\n";
		result += "D = e^(-1) mod Φ(n) = " + e + "^(-1) mod " + phi + " = "
				+ d[0] + "\n";
		result += "Vậy khóa công khai (n, e) = (" + n + ", " + e + ").\n";
		result += "khóa bí mật là d = " + d[0];

		return result;
	}

	public ArrayList<String[]> maVong(String input, int key, int z, boolean type) {
		input = chuanHoaXau(input);
		System.out.println("ban ro : " + input);
		char[] arrChar = input.toCharArray();
		int[] arrNum = new int[arrChar.length];
		int[] arrNumVong = new int[arrChar.length];

		for (int i = 0; i < arrChar.length; i++) {
			arrNum[i] = toNumber(arrChar[i]);
			if (type) {
				arrNumVong[i] = arrNum[i] + key;
			} else {
				arrNumVong[i] = arrNum[i] - key;
			}
		}

		String[] title;

		// giai ma
		if (type) {
			title = new String[] { "Bản rõ (x)", "Dạng số", "x + k",
					"(x+k) mod " + z, "Bản mã (y)" };
		} else {// ma hoa
			title = new String[] { "Bản mã (y)", "Dạng số", "y - k",
					"(y-k) mod " + z, "Bản rõ (x)" };
		}

		ArrayList<String[]> list = new ArrayList<String[]>();
		String[][] row = new String[5][arrChar.length + 1];

		row[0][0] = title[0];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[0][i] = arrChar[i - 1] + "";
		}
		list.add(row[0]);

		row[1][0] = title[1];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[1][i] = arrNum[i - 1] + "";
			System.out.println(row[1][i]);
		}
		list.add(row[1]);

		row[2][0] = title[2];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[2][i] = arrNumVong[i - 1] + "";
			System.out.println(row[2][i]);
		}
		list.add(row[2]);

		row[3][0] = title[3];
		for (int i = 1; i < arrChar.length + 1; i++) {
			while (arrNumVong[i - 1] < 0) {
				arrNumVong[i - 1] += z;
			}
			row[3][i] = arrNumVong[i - 1] % z + "";
			System.out.println(row[3][i]);
		}
		list.add(row[3]);

		row[4][0] = title[4];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[4][i] = toChar(arrNumVong[i - 1] % z) + "";
			System.out.println(row[4][i]);
			arrChar[i - 1] = toChar(arrNumVong[i - 1] % z);
		}
		list.add(row[4]);

		list.add(new String[] { title[4] + " là: " + String.valueOf(arrChar) });
		System.out.println("ban ma : " + list.get(list.size() - 1)[0]);
		return list;
	}

	public ArrayList<String[]> hoanvi(int[] ttDung, int[] hoanVi, String input,
			boolean type) {
		input = chuanHoaXau(input);
		// int len = input.length();

		for (int i = 0; i < hoanVi.length; i++) {
			hoanVi[i]--;
			ttDung[i]--;
			System.out.println(hoanVi[i] + " - " + ttDung[i]);
		}

		int size = ttDung.length;
		System.out.println(size);

		ArrayList<String[]> list = new ArrayList<String[]>();

		String[] title;

		// ma hoa
		if (type) {
			title = new String[] { "Bản rõ (x)", "Thứ tự đúng", "Hoán vị",
					"Bản mã (y)" };
		} else {// giai ma
			title = new String[] { "Bản mã (y)", "Thứ tự đúng", "Hoán vị",
					"Bản rõ (x)" };
		}

		// tinh pi^(-1) de giai ma
		if (!type) {
			int[] hv = new int[hoanVi.length];
			for (int i = 0; i < hv.length; i++) {
				hv[i] = hoanVi[i];
			}

			for (int i = 0; i < ttDung.length; i++) {
				hoanVi[hv[i]] = ttDung[i];
			}
		}
		System.out.println(input);
		int n = input.length() % size;
		if (n != 0) {
			for (int i = 0; i < size - n; i++) {
				input += "x";
			}
		}

		System.out.println(input);

		String[][] row = new String[4][input.length() + input.length() / size];

		System.out.println(input.length() + "\t" + input.length() / size);

		char[][] item = new char[input.length() / size][size];

		for (int i = 0; i < item.length; i++) {
			for (int j = 0; j < item[0].length; j++) {
				item[i][j] = input.charAt(i * item[0].length + j);
				// System.out.println("i = " + i + "\tj = " + j + "\titem = "
				// + item[i][j]);
			}
		}

		int r = 0;
		row[r][0] = title[r];
		for (int i = 0, k = 1; i < item.length; i++) {
			for (int j = 0; j < item[0].length; j++) {
				row[r][k++] = item[i][j] + "";
			}
			if (i < item.length - 1) {
				row[r][k++] = " ";
			}
		}
		list.add(row[r]);

		for (int i = 0; i < row[r].length; i++) {
			System.out.print(row[r][i]);
		}
		System.out.println();

		r = 1;
		row[r][0] = title[r];
		for (int i = 0, k = 1; i < item.length; i++) {
			for (int j = 0; j < item[0].length; j++) {
				row[r][k++] = (ttDung[j] + 1) + "";
			}
			if (i < item.length - 1) {
				row[r][k++] = " ";
			}
		}
		list.add(row[r]);

		for (int i = 0; i < row[r].length; i++) {
			System.out.print(row[r][i]);
		}
		System.out.println();

		r = 2;
		row[r][0] = title[r];
		for (int i = 0, k = 1; i < item.length; i++) {
			for (int j = 0; j < item[0].length; j++) {
				row[r][k++] = (hoanVi[j] + 1) + "";
			}
			if (i < item.length - 1) {
				row[r][k++] = " ";
			}
		}
		list.add(row[r]);
		for (int i = 0; i < row[r].length; i++) {
			System.out.print(row[r][i]);
		}
		System.out.println();

		r = 3;
		row[r][0] = title[r];
		String result = "";
		for (int i = 0, k = 1; i < item.length; i++) {
			for (int j = 0; j < item[0].length; j++) {
				row[r][k++] = item[i][hoanVi[j]] + "";
				result += item[i][hoanVi[j]];
			}
			if (i < item.length - 1) {
				row[r][k++] = " ";
			}
		}
		list.add(row[r]);
		for (int i = 0; i < row[r].length; i++) {
			System.out.print(row[r][i]);
		}
		System.out.println();

		// cat chuoi giai ma (cat ban ro)
		System.out.println(result.length());
		// if (!type) {
		// result = result.substring(0, len);
		// }

		// System.out.println(len);
		System.out.println(result.length());

		list.add(new String[] { title[3] + " là: " + result });

		return list;
	}

	public ArrayList<String[]> maAffine(String input, int keyA, int keyB,
			int z, boolean type) {
		input = chuanHoaXau(input);
		System.out.println("ban ro : " + input);
		char[] arrChar = input.toCharArray();
		int[] arrNum = new int[arrChar.length];
		int[] arrNumAffine = new int[arrChar.length];

		for (int i = 0; i < arrChar.length; i++) {
			arrNum[i] = toNumber(arrChar[i]);
			if (type) {
				arrNumAffine[i] = (keyA * arrNum[i] + keyB);
			} else {
				int[] rs = new int[1];
				phanTuNghichDao(keyA, z, rs);
				arrNumAffine[i] = rs[0] * (arrNum[i] - keyB);
			}
		}

		String[] title;

		// giai ma
		if (type) {
			title = new String[] { "Bản rõ (x)", "Dạng số", "ax + b",
					"(ax+b) mod " + z, "Bản mã (y)" };
		} else {// ma hoa
			title = new String[] { "Bản mã (y)", "Dạng số", "a⁻¹(y-b)",
					"a⁻¹(y-b) mod " + z, "Bản rõ (x)" };
		}

		ArrayList<String[]> list = new ArrayList<String[]>();
		String[][] row = new String[5][arrChar.length + 1];

		row[0][0] = title[0];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[0][i] = arrChar[i - 1] + "";
		}
		list.add(row[0]);

		row[1][0] = title[1];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[1][i] = arrNum[i - 1] + "";
			System.out.println(row[1][i]);
		}
		list.add(row[1]);

		row[2][0] = title[2];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[2][i] = arrNumAffine[i - 1] + "";
			System.out.println(row[2][i]);
		}
		list.add(row[2]);

		row[3][0] = title[3];
		for (int i = 1; i < arrChar.length + 1; i++) {
			while (arrNumAffine[i - 1] < 0) {
				arrNumAffine[i - 1] += z;
			}
			row[3][i] = arrNumAffine[i - 1] % z + "";
			System.out.println(row[3][i]);
		}
		list.add(row[3]);

		row[4][0] = title[4];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[4][i] = toChar(arrNumAffine[i - 1] % z) + "";
			System.out.println(row[4][i]);
			arrChar[i - 1] = toChar(arrNumAffine[i - 1] % z);
		}
		list.add(row[4]);

		list.add(new String[] { title[4] + " là: " + String.valueOf(arrChar) });
		System.out.println("ban ma : " + list.get(list.size() - 1)[0]);
		return list;
	}

	public String countKeyAffine(int n, long[] result) {
		ConvertNumber cv = new ConvertNumber();
		String text = cv.primeMultiBase(n);
		String[] item = text.split("x");

		if (item.length == 1) {

			try {
				Integer.parseInt(item[0]);
				result[0] = n - 1;
				return "Do " + n + " là số nguyên tố => Φ(" + n + ") = "
						+ (n - 1);
			} catch (NumberFormatException e) {

			}
		}

		text = n + " = " + text + "\n";
		text += "=> Φ(" + n + ") = ";

		long count = 1;
		for (int i = 0; i < item.length; i++) {
			System.out.println(item[i]);

			int index = item[i].length();
			for (int j = 0; j < item[i].length(); j++) {
				if (item[i].charAt(j) == '^') {
					index = j;
					System.out.println("index = " + j);
					break;
				}
			}

			int p = Integer.parseInt(item[i].substring(0, index));
			int r = 1;
			if (index < item[i].length()) {
				r = Integer.parseInt(item[i].substring(index + 1,
						item[i].length()));
			}
			if (r == 1) {
				text += (p - 1);
			} else {
				text += (p - 1) + "*(" + p + "^" + (r - 1) + ")";
			}
			if (i < item.length - 1) {
				text += "*";
			}
			count = count * (p - 1) * (long) Math.pow(p, r - 1);
		}
		text += " = " + count;
		result[0] = count;
		return text;
	}

	public ArrayList<String[]> maTuSinh(String input, int key, int z,
			boolean type) {
		input = chuanHoaXau(input);
		System.out.println("ban ro : " + input);
		char[] arrChar = input.toCharArray();
		int[] arrNum = new int[arrChar.length];
		int[] arrNumKey = new int[arrChar.length];
		int[] arrNumTuSinh = new int[arrChar.length];

		for (int i = 0; i < arrChar.length; i++) {
			arrNum[i] = toNumber(arrChar[i]);
		}
		arrNumKey[0] = key;

		if (type) {
			for (int i = 0; i < arrNumTuSinh.length; i++) {
				if (i > 0) {
					arrNumKey[i] = arrNum[i - 1];
				}
				arrNumTuSinh[i] = (arrNum[i] + arrNumKey[i]) % z;
			}
		} else {
			for (int i = 0; i < arrNumTuSinh.length; i++) {
				if (i > 0) {
					arrNumKey[i] = arrNum[i - 1] - arrNumKey[i - 1];
				}
				while (arrNumKey[i] < 0) {
					arrNumKey[i] += z;
				}

				arrNumTuSinh[i] = (arrNum[i] - arrNumKey[i]);
				while (arrNumTuSinh[i] < 0) {
					arrNumTuSinh[i] += z;
				}
				arrNumTuSinh[i] %= z;
			}
		}

		String[] title;

		// giai ma
		if (type) {
			title = new String[] { "Bản rõ (x)", "Dạng số", "Dòng khóa z",
					"(x+z) mod " + z, "Bản mã (y)" };
		} else {// ma hoa
			title = new String[] { "Bản mã (y)", "Dạng số", "Dòng khóa z",
					"(y-z) mod " + z, "Bản rõ (x)" };
		}

		ArrayList<String[]> list = new ArrayList<String[]>();
		String[][] row = new String[5][arrChar.length + 1];

		row[0][0] = title[0];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[0][i] = arrChar[i - 1] + "";
		}
		list.add(row[0]);

		row[1][0] = title[1];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[1][i] = arrNum[i - 1] + "";
			System.out.println(row[1][i]);
		}
		list.add(row[1]);

		row[2][0] = title[2];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[2][i] = arrNumKey[i - 1] + "";
			System.out.println(row[2][i]);
		}
		list.add(row[2]);

		row[3][0] = title[3];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[3][i] = arrNumTuSinh[i - 1] + "";
			System.out.println(row[3][i]);
		}
		list.add(row[3]);

		row[4][0] = title[4];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[4][i] = toChar(arrNumTuSinh[i - 1]) + "";
			System.out.println(row[4][i]);
			arrChar[i - 1] = toChar(arrNumTuSinh[i - 1]);
		}
		list.add(row[4]);

		list.add(new String[] { title[4] + " là: " + String.valueOf(arrChar) });
		System.out.println("ban ma : " + list.get(list.size() - 1)[0]);
		return list;
	}

	public ArrayList<String[]> vigenere(String input, int[] arrKey, int z,
			boolean type) {
		input = chuanHoaXau(input);
		System.out.println("ban ro : " + input);
		char[] arrChar = input.toCharArray();
		int[] arrNum = new int[arrChar.length];
		int[] arrNumVigenere = new int[arrChar.length];

		int size = arrKey.length;

		for (int i = 0; i < arrChar.length; i++) {
			arrNum[i] = toNumber(arrChar[i]);
			if (type) {
				arrNumVigenere[i] = (arrNum[i] + arrKey[i % size]) % z;
			} else {
				arrNumVigenere[i] = arrNum[i] - arrKey[i % size];
				while (arrNumVigenere[i] < 0) {
					arrNumVigenere[i] += z;
				}
				arrNumVigenere[i] %= z;
			}
		}

		String[] title;

		// giai ma
		if (type) {
			title = new String[] { "Bản rõ (x)", "Dạng số", "Khóa",
					"(x+k) mod " + z, "Bản mã (y)" };
		} else {// ma hoa
			title = new String[] { "Bản mã (y)", "Dạng số", "Khóa",
					"(y-k) mod " + z, "Bản rõ (x)" };
		}

		ArrayList<String[]> list = new ArrayList<String[]>();
		String[][] row = new String[5][arrChar.length + 1];

		row[0][0] = title[0];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[0][i] = arrChar[i - 1] + "";
		}
		list.add(row[0]);

		row[1][0] = title[1];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[1][i] = arrNum[i - 1] + "";
			System.out.println(row[1][i]);
		}
		list.add(row[1]);

		row[2][0] = title[2];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[2][i] = arrKey[(i - 1) % size] + "";
			System.out.println(row[2][i]);
		}
		list.add(row[2]);

		row[3][0] = title[3];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[3][i] = arrNumVigenere[i - 1] + "";
			System.out.println(row[3][i]);
		}
		list.add(row[3]);

		row[4][0] = title[4];
		for (int i = 1; i < arrChar.length + 1; i++) {
			row[4][i] = toChar(arrNumVigenere[i - 1]) + "";
			System.out.println(row[4][i]);
			arrChar[i - 1] = toChar(arrNumVigenere[i - 1]);
		}
		list.add(row[4]);

		list.add(new String[] { title[4] + " là: " + String.valueOf(arrChar) });
		System.out.println("ban ma : " + list.get(list.size() - 1)[0]);
		return list;
	}

	public ArrayList<String[]> hill(String input, int[][] arrKey, int z,
			boolean type) {
		input = chuanHoaXau(input);
		int size = 2;

		ArrayList<String[]> list = new ArrayList<String[]>();

		for (int i = 0; i < input.length() % size; i++) {
			input += "x";
		}

		String titleRs = "";
		if (type) {
			titleRs = "Bản mã y = x.k mod n = ";
		} else {
			titleRs = "Bản rõ x = y.k^(-1) mod n = ";
			int detK = (arrKey[0][0] * arrKey[1][1] - arrKey[0][1]
					* arrKey[1][0]);
			int nghichDaoDetKValue[] = new int[1];
			phanTuNghichDao(detK, z, nghichDaoDetKValue);
			String nghichDaoDetK = "* Tìm K^(-1)" + "\n" + "Ta có det(K) = "
					+ arrKey[0][0] + " * " + arrKey[1][1] + " - "
					+ arrKey[0][1] + " * " + arrKey[1][0] + " = " + detK + "\n"
					+ "=> (Det(K))^(-1)  mod 26 = " + nghichDaoDetKValue[0]
					+ "\n" + "K^(-1) = (det(K))^(-1)*K\"" + " mod n" + "\n"
					+ "(K\" là ma trận bù đại số của K)";

			list.add(new String[] { nghichDaoDetK });

			int temp = arrKey[0][0];
			arrKey[0][0] = arrKey[1][1];
			arrKey[1][1] = temp;
			arrKey[0][1] = 0 - arrKey[0][1];
			arrKey[1][0] = 0 - arrKey[1][0];

			String[] nghichDao = new String[7];

			nghichDao[0] = nghichDaoDetKValue[0] + "";
			nghichDao[1] = "*";
			nghichDao[2] = arrKey[0][0] + "  " + arrKey[0][1] + "\n"
					+ arrKey[1][0] + "  " + arrKey[1][1];

			nghichDao[3] = "=";

			nghichDao[4] = "";
			nghichDao[5] = "=>";
			nghichDao[6] = "";

			for (int i = 0, k = 0; i < arrKey.length; i++) {
				if (i > 0) {
					nghichDao[4] += "\n";
					nghichDao[6] += "\n";
				}
				for (int j = 0; j < arrKey[0].length; j++) {
					arrKey[i][j] = nghichDaoDetKValue[0] * (z + arrKey[i][j]);
					nghichDao[4] += arrKey[i][j] + "  ";
					arrKey[i][j] %= z;
					nghichDao[6] += arrKey[i][j] + "  ";
				}

			}

			list.add(nghichDao);

		}

		char[] arrChar = input.toCharArray();

		int[][] arrNum = new int[input.length() / size][size];
		int[][] arrResult = new int[input.length() / size][size];

		String[] rs = new String[7];

		rs[0] = "";
		for (int i = 0, k = 0; i < arrNum.length; i++) {
			for (int j = 0; j < arrNum[0].length; j++) {
				arrNum[i][j] = toNumber(arrChar[k++]);
				rs[0] += arrNum[i][j] + "  ";
			}
			rs[0] += "\n";
		}
		rs[1] = "*";

		rs[2] = "";
		for (int i = 0, k = 0; i < arrKey.length; i++) {
			if (i > 0) {
				rs[2] += "\n";
			}
			for (int j = 0; j < arrKey[0].length; j++) {
				rs[2] += arrKey[i][j] + "  ";
			}
		}

		rs[3] = "=";

		arrResult = nhanMaTran(arrNum, arrKey);

		rs[4] = "";
		rs[5] = "=>";
		rs[6] = "";
		for (int i = 0, k = 0; i < arrResult.length; i++) {
			if (i > 0) {
				rs[4] += "\n";
				rs[6] += "\n";
			}
			for (int j = 0; j < arrResult[0].length; j++) {
				rs[4] += arrResult[i][j] + "  ";
				arrResult[i][j] %= z;
				rs[6] += arrResult[i][j] + "  ";
			}

		}
		list.add(rs);

		String rs1 = "=> " + titleRs;
		for (int i = 0; i < arrResult.length; i++) {
			for (int j = 0; j < arrResult[0].length; j++) {
				rs1 += toChar(arrResult[i][j]);
			}
		}
		list.add(new String[] { rs1 });
		return list;
	}

	private int[][] nhanMaTran(int[][] a, int[][] b) {
		int[][] rs = new int[a.length][b[0].length];
		int m = a.length, n = a[0].length, p = b[0].length;
		for (int i = 0; i < m; i++) {
			for (int k = 0; k < p; k++) {
				rs[i][k] = 0;
				for (int j = 0; j < n; j++) {
					rs[i][k] += a[i][j] * b[j][k];
				}
			}
		}
		return rs;
	}

	public int[] converStringToArrNumber(String input) {
		input = chuanHoaXau(input);
		System.out.println(input);
		String[] tokens = input.split("-");
		int[] arrNum = new int[tokens.length];

		for (int i = 0; i < tokens.length; i++) {
			System.out.println(tokens[i]);
			try {
				arrNum[i] = Integer.parseInt(tokens[i]);
			} catch (NumberFormatException e) {
				System.err.println(e);
			}
		}
		return arrNum;
	}

	public static String chuanHoaXau(String str) {
		str = str.trim();
		str = str.toLowerCase();
		str = str.replaceAll("\\s+", " ");
		return str;
	}

	public static int toNumber(char c) {
		return c - 'a';
	}

	public static char toChar(int num) {
		return (char) (num + 'a');
	}

	// public static boolean checkEditText(Activity activity, EditText edit,
	// String name) {
	// if (edit.getText().toString().trim().equals("")) {
	// edit.requestFocus();
	// Toast.makeText(activity, "bạn chưa nhập " + name,
	// Toast.LENGTH_SHORT).show();
	// return false;
	// }
	// return true;
	// }

	// public static boolean checkInput(Activity activity, EditText[] edit,
	// String[][] title, int indexType) {
	//
	// for (int i = 0; i < title[0].length; i++) {
	// if (!checkEditText(activity, edit[i],
	// title[indexType][i].toLowerCase())) {
	// return false;
	// }
	// }
	// return true;
	// }

	public static String checkEditText(EditText edit, String name) {
		if (edit.getText().toString().trim().equals("")) {
			edit.requestFocus();
			return Const.errorInput + name;
		}
		return null;
	}

	public static String checkInput(EditText[] edit, String[] title) {
		String error = null;
		for (int i = 0; i < title.length; i++) {
			error = checkEditText(edit[i], title[i].toLowerCase());
			if (error != null) {
				return error;
			}
		}
		return error;
	}

	public static byte getIndexOfChar(char c) {
		c = Character.toLowerCase(c);
		return (byte) (c - 'a');
	}

	public int ucln(int a, int b) {
		while (b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

}
