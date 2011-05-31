package net.impjq.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class Utils {
	/**
	 * Read data from InputStreamReader
	 * 
	 * @param isr
	 *            InputStreamReader
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
	 * @param isr
	 *            InputStreamReader
	 * @return The Data read from The InputStreamReader
	 */
	public static String readFromInputStream(InputStream is) {
		String result = null;

		try {
            result = readFromInputStream(new InputStreamReader(is,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

		return result;
	}
}
