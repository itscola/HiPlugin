package top.whitecola.datahandler;

import java.nio.charset.Charset;

public class HiStream {
    public static byte[] textToByteData(String str, Charset charset){
        return str.getBytes(charset);
    }

    public static String byteDataToString(byte[] data,Charset charset){
        return new String(data,charset);
    }



}
