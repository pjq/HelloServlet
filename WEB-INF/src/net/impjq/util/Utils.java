
package net.impjq.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    /**
     * Read data from InputStreamReader
     * 
     * @param isr InputStreamReader
     * @return The Data read from The InputStreamReader
     */
    public static String readFromInputStream(InputStreamReader isr) {
        String result = "";

        BufferedReader rd = new BufferedReader(isr);
        String line;

        try {
            while ((line = rd.readLine()) != null) {
                result += line + '\n';
            }

            isr.close();
            rd.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Read data from InputStreamReader
     * 
     * @param isr InputStreamReader
     * @return The Data read from The InputStreamReader
     */
    public static String readFromInputStream(InputStream is) {
        String result = null;

        try {
            result = readFromInputStream(new InputStreamReader(is, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Read data from InputStreamReader
     * 
     * @param isr InputStreamReader
     * @return The Data read from The InputStreamReader
     */
    public static void storeImageFromInputStream(InputStream is, int length,
            String uploadPath, String photoName) {
        try {
            File path = new File(uploadPath);
            if (!path.exists()) {
                path.mkdirs();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(uploadPath
                    + "/" + photoName);

            byte[] cbuf = new byte[8096 * 10];

            byte[] fileBuf = new byte[length * 8];

            int len = -1;

            int total = 0;
            // while ((len = is.read(fileBuf, total+1, 8 * 1024)) != -1) {
            // total += len;
            // fileOutputStream.write(fileBuf, total, len);
            // }

            // is.read(fileBuf, fileBuf.length, 8096 * 10);

            while ((len = is.read(cbuf)) != -1) {
                fileOutputStream.write(cbuf, 0, len);
            }

            fileOutputStream.close();
            is.close();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String createPhotoName() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
                + ".png";
    }

    /**
     * Check the string is empty
     * 
     * @param string
     * @return true if the string is null or "".
     */
    public static boolean isEmpty(String string) {
        boolean empty = false;

        if (null == string || (null != string && string.equals(""))) {
            empty = true;
        }

        return empty;
    }
}
