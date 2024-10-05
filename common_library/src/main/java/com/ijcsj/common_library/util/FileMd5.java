package com.ijcsj.common_library.util;

public class FileMd5 {
    public native byte[] get_file_md5(byte[] chars1,byte[] chars);
    public native byte[] get_attribute(byte[] chars1);

    static {
        System.loadLibrary("common_library");
    }

}
