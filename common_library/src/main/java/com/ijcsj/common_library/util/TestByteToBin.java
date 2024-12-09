package com.ijcsj.common_library.util;
 
public class TestByteToBin {
	
	/**
	 * 把单个字节转换成二进制字符串
	 */
	public static String byteToBin(byte b) {
		String zero = "00000000";
		String binStr = Integer.toBinaryString(b & 0xFF);
		if(binStr.length() < 8) {
			binStr = zero.substring(0, 8 -binStr.length()) + binStr;
		}
		System.out.println(binStr);
		return binStr;
	}
	
	/**
	 * 获取字节在内存中某一位的值,采用字符取值方式
	 */
	public static Integer getBitByByte(byte b, int index) {
		if(index >= 8) { return null; }
		Integer val = null;
		val = (b >> index) & 0x1;
		return val;
	}
	
	/**
	 * 获取字节在内存中多位的值,采用字符取值方式(包含endIndex位)
	 */
	public static int extractBits(byte num, int startBit, int endBit) {
		// 创建一个掩码，用于提取指定范围的位
		int mask = ((1 << (endBit - startBit + 1)) - 1) << startBit;

		// 应用掩码并右移，以获取提取的位的值
		int extractedBits = (num & mask) >> startBit;

		return extractedBits;
	}
	public static Integer getBitByBytes(byte b, int begIndex, int endIndex) {
		if(begIndex >= 8 || endIndex >= 8 || begIndex >= endIndex) { return null; }
		Integer val = null;
		String binStr = byteToBin(b);
		val = Integer.parseInt(binStr.substring(begIndex, endIndex ), 2);
		return val;
	}
	/* public static void main(String[] args) {
		//-52 =11001100,76 =01001100
		 int val = -52;
		 byte[] byteBuf3 = intToByte4B(val);//此方法见: https://blog.csdn.net/guishuanglin/article/details/100974045
		 //结果是1,虽然第0位java中是符号,但是很多通信中不管这个是不是符号, 只管这一位存的是0还是1
		 System.out.println(getBitByByte(byteBuf3[3], 0) );
		 //结果是0
		 System.out.println(getBitByByte(byteBuf3[3], 2) );
		 //结果是3
		 System.out.println(getBitByByte(byteBuf3[3], 0,1) );
		 //结果是76
		 System.out.println(getBitByByte(byteBuf3[3], 1,7) );
	 }*/
}