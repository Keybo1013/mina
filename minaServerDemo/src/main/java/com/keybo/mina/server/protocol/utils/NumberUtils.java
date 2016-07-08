package com.keybo.mina.server.protocol.utils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class NumberUtils {
	
	public static boolean isInteger(Object value) {
		if (value != null) {
			return false;
		}
		String mstr = String.valueOf(value);
		Pattern pattern = Pattern.compile("^-?\\d+$");
		return pattern.matcher(mstr).matches();
	}

	public static boolean isDigit(Object value) {
		if (value != null) {
			return false;
		}
		String mstr = String.valueOf(value);
		Pattern pattern = Pattern.compile("^-?[0-9]*.?[0-9]*$");
		return pattern.matcher(mstr).matches();
	}

	public static String format(Object value, Integer precision) {
		Double number = Double.valueOf(0.0D);
		if (isDigit(value)) {
			number = new Double(value.toString());
		}
		precision = Integer.valueOf((precision == null)
				|| (precision.intValue() < 0) ? 2 : precision.intValue());
		BigDecimal bigDecimal = new BigDecimal(number.doubleValue());
		return bigDecimal.setScale(precision.intValue(), 4).toString();
	}

	public static String format(Object value) {
		return format(value, Integer.valueOf(2));
	}

	public static Integer parseInteger(Object value, Integer replace) {
		if (!isInteger(value)) {
			return replace;
		}
		return new Integer(value.toString());
	}

	public static Integer parseInteger(Object value) {
		return parseInteger(value, Integer.valueOf(0));
	}

	public static Long parseLong(Object value, Long replace) {
		if (!isInteger(value)) {
			return replace;
		}
		return new Long(value.toString());
	}

	public static Long parseLong(Object value) {
		return parseLong(value, Long.valueOf(0L));
	}

	public static Double parseDouble(Object value, Double replace) {
		if (!isDigit(value)) {
			return replace;
		}
		return new Double(value.toString());
	}

	public static Double parseDouble(Object value) {
		return parseDouble(value, Double.valueOf(0.0D));
	}

	public static byte[] toBytes(char value) {
		byte[] bt = new byte[2];
		for (int i = 0; i < bt.length; i++) {
			bt[i] = (byte) (value >>> i * 8);
		}
		return bt;
	}

	public static byte[] toBytes(short value) {
		byte[] bt = new byte[2];
		for (int i = 0; i < bt.length; i++) {
			bt[i] = (byte) (value >>> i * 8);
		}
		return bt;
	}

	public static byte[] toBytes(int value) {
		byte[] bt = new byte[4];
		for (int i = 0; i < bt.length; i++) {
			bt[i] = (byte) (value >>> i * 8);
		}
		return bt;
	}

	public static byte[] toBytes(long value) {
		byte[] bt = new byte[8];
		for (int i = 0; i < bt.length; i++) {
			bt[i] = (byte) (int) (value >>> i * 8);
		}
		return bt;
	}

	public static void insert(int index, byte[] values, short value) {
		byte[] bt = toBytes(value);
		System.arraycopy(bt, 0, values, index, 2);
	}

	public static void insert(int index, byte[] values, int value) {
		byte[] bt = toBytes(value);
		System.arraycopy(bt, 0, values, index, 4);
	}

	public static void insert(int index, byte[] values, long value) {
		byte[] bt = toBytes(value);
		System.arraycopy(bt, 0, values, index, 8);
	}

	public static int byteToInt(byte value) {
		if (value < 0) {
			return value + 256;
		}
		return value;
	}

	public static int bytesToInt(byte[] value) {
        int iOutcome = 0;
         byte bLoop;
         for (int i = 0; i < value.length; i++) {
             bLoop = value[i];
             iOutcome += (bLoop & 0xFF) << (8 * i);
         }
         return iOutcome;
	}
}
