package top.whitecola.datahandler;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HiURL {
    public static String readURL(String Iurl) throws Throwable {
        URL url = new URL(Iurl);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type","application/json");
        InputStream is = con.getInputStream();
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        while(true)
        {
            int temp = is.read();
            if(temp!=-1)
            {
                bao.write(temp);
            }else{
                break;
            }
        }
        is.close();
        bao.close();
        return new String(bao.toByteArray());


    }
}