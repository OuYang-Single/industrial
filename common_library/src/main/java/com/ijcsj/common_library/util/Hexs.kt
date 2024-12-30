package com.ijcsj.common_library.util

import okhttp3.internal.and
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.experimental.and

object Hexs {
    /**
     * 用于建立十六进制字符的输出的小写字符数组
     */
    private val DIGITS_LOWER = charArrayOf(
        '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    )

    /**
     * 用于建立十六进制字符的输出的大写字符数组
     */
    private val DIGITS_UPPER = charArrayOf(
        '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    )
    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param data
     * byte[]
     * @param toLowerCase
     * `true` 传换成小写格式 ， `false` 传换成大写格式
     * @return 十六进制char[]
     */
    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param data
     * byte[]
     * @return 十六进制char[]
     */
    @JvmOverloads
    fun encodeHex(data: ByteArray, toLowerCase: Boolean = true): CharArray {
        return encodeHex(data, if (toLowerCase) DIGITS_LOWER else DIGITS_UPPER)
    }

    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param data
     * byte[]
     * @param toDigits
     * 用于控制输出的char[]
     * @return 十六进制char[]
     */
    internal fun encodeHex(data: ByteArray, toDigits: CharArray): CharArray {
        val l = data.size
        val out = CharArray(l shl 1)
        // two characters form the hex value.
        var i = 0
        var j = 0
        while (i < l) {
            out[j++] = toDigits[0xF0 and data[i].toInt() ushr 4]
            out[j++] = toDigits[0x0F and data[i].toInt()]
            i++
        }
        return out
    }
    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data
     * byte[]
     * @param toLowerCase
     * `true` 传换成小写格式 ， `false` 传换成大写格式
     * @return 十六进制String
     */
    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data
     * byte[]
     * @return 十六进制String
     */
    @JvmOverloads
    fun encodeHexStr(data: ByteArray, toLowerCase: Boolean = true): String {
        return encodeHexStr(data, if (toLowerCase) DIGITS_LOWER else DIGITS_UPPER)
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data
     * byte[]
     * @param toDigits
     * 用于控制输出的char[]
     * @return 十六进制String
     */
    fun encodeHexStr(data: ByteArray, toDigits: CharArray): String {
        var stringBuffer=  StringBuffer()
        for (element in data) {
            var v= ByteArray(1);
            v[0]=element
            stringBuffer.append( String(encodeHex(v, toDigits)))
            stringBuffer.append(" ")
        }
        return stringBuffer.toString()
    }

    /**
     * 将十六进制字符数组转换为字节数组
     *
     * @param data
     * 十六进制char[]
     * @return byte[]
     * @throws RuntimeException
     * 如果源十六进制字符数组是一个奇怪的长度，将抛出运行时异常
     */
    fun decodeHex(data: CharArray): ByteArray {
        val len = data.size
        if (len and 0x01 != 0) {
            throw RuntimeException("Odd number of characters.")
        }
        val out = ByteArray(len shr 1)

        // two characters form the hex value.
        var i = 0
        var j = 0
        while (j < len) {
            var f = toDigit(data[j], j) shl 4
            j++
            f = f or toDigit(data[j], j)
            j++
            out[i] = (f and 0xFF).toByte()
            i++
        }
        return out
    }

    /**
     * 将十六进制字符转换成一个整数
     *
     * @param ch
     * 十六进制char
     * @param index
     * 十六进制字符在字符数组中的位置
     * @return 一个整数
     * @throws RuntimeException
     * 当ch不是一个合法的十六进制字符时，抛出运行时异常
     */
    fun toDigit(ch: Char, index: Int): Int {
        val digit = ch.digitToIntOrNull(16) ?: -1
        if (digit == -1) {
            throw RuntimeException(
                "Illegal hexadecimal character " + ch
                        + " at index " + index
            )
        }
        return digit
    }

    /**
     * 16进制转字节。低字节在前、高字节在后
     * @param value
     * @return
     */
    fun hex2LowHighByte(value: Long): ByteArray {
        val a = ByteArray(2) //双字节
        a[0] = (value and 0xff).toByte() //获得低位字节
        a[1] = (value ushr 8).toByte() //获得高位字节
        return a
    }

    /**
     * 将两个字节拼接还原成有符号的整型数据
     * 数据域中的数据都按小端存储，示例：数据0x1234，则Byte0为0x34,Byte1为0x12  高低位
     *
     * @param byte1
     * @param byte2
     * @return
     */
    fun pinJie2ByteToInt(byte1: Byte, byte2: Byte): Int {
        var result = byte1.toInt()
        result = result shl 8 or (0x00FF and byte2.toInt())
        return result
    }

    /**
     * 把单个字节转换成二进制字符串
     */
    fun byteToBin(b: Byte): String {
        val zero = "00000000"
        var binStr = Integer.toBinaryString((b and 0xFF.toByte()).toInt())
        if (binStr.length < 8) {
            binStr = zero.substring(0, 8 - binStr.length) + binStr
        }
        println(binStr)
        return binStr
    }

    /**
     * 获取字节在内存中某一位的值,采用字符取值方式
     */
    fun getBitByByte(b: Byte, index: Int): Int? {

        return TestByteToBin.getBitByByte(b,index)
    }

    /**
     * 获取字节在内存中多位的值,采用字符取值方式(包含endIndex位)
     */
   /* fun getBitByByte(b: Byte, begIndex: Int, endIndex: Int): Int? {
        if (begIndex >= 8 || endIndex >= 8 || begIndex >= endIndex) {
            return null
        }
        var `val`: Int? = null
        val binStr = byteToBin(b)
        `val` = binStr.substring(begIndex, endIndex + 1).toInt(2)
        return `val`
    }*/

   /* fun getBitByByte(data: Byte, startBit: Int, endBit: Int): Int {
        // 将整数转换为二进制字符串
        // 将整数转换为二进制字符串
        var mask = (1 shl endBit - startBit + 1) - 1
        mask = mask shl startBit

        // 使用掩码提取位

        // 使用掩码提取位
        val result: Int = data and mask shr startBit
        return result
    }*/
    fun getBitByByte(b: Byte, startBit: Int, endBit: Int): Int {
        // 创建一个掩码，用于提取指定范围的位
   /*     var mask = (1 shl endBit - startBit + 1) - 1
        mask = mask shl startBit*/

        // 使用掩码提取位
        return TestByteToBin.extractBits(b,startBit,endBit)
    }
    fun intToByte4B(n: Int): ByteArray? {
        val b = ByteArray(4)
        b[0] = (n shr 24 and 0xff).toByte() //数据组起始位,存放内存起始位, 即:高字节在前
        b[1] = (n shr 16 and 0xff).toByte() //高字节在前是与java存放内存一样的, 与书写顺序一样
        b[2] = (n shr 8 and 0xff).toByte()
        b[3] = (n and 0xff).toByte()
        return b
    }

    /**
     * 转换成小端模式-高字节在后(java为高字节在前,内存数组第0位表示最前)
     */
    fun intToByte4L(n: Int): ByteArray? {
        val b = ByteArray(4)
        b[0] = (n and 0xff).toByte()
        b[1] = (n shr 8 and 0xff).toByte()
        b[2] = (n shr 16 and 0xff).toByte() //高字节在后是与java存放内存相反, 与书写顺序相反
        b[3] = (n shr 24 and 0xff).toByte() //数据组结束位,存放内存起始位, 即:高字节在后
        return b
    }



    //4位byte转为int
    /*    private fun Byte4Int(b: ByteArray): Int {
            return b[0].toInt() and 0xff.toInt() shl 24.toInt() or (b[1] and 0xff shl 16.toInt()) or (b[2] and 0xff shl 8.toInt()) or (b[3].toInt() and 0xff)
        }*/

    //int转为2位byte
    public fun Int2Byte(a: Int): ByteArray {
        val b = ByteArray(2)
        b[0] = (a shr 8).toByte()
        b[1] = a.toByte()
        return b
    }

    //int转为3位byte
    private fun Int3Byte(a: Int): ByteArray? {
        val b = ByteArray(3)
        b[0] = (a shr 16).toByte()
        b[1] = (a shr 8).toByte()
        b[2] = a.toByte()
        return b
    }
    fun validatePhoneNumber(number: String): Boolean {
        /**
         * 校验手机号码是否合法
         */
        val pattern: Pattern = Pattern.compile("^1[3-9]\\d{9}$")
        val matcher: Matcher = pattern.matcher(number)
        return matcher.matches()
    }
}