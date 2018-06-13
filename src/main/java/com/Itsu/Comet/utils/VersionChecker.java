package com.Itsu.Comet.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * <h6>Comet project</h6>
 * <p>for PMMP/Jupiter/Nukkit plugin
 *
 * <p>Java（PHP）構文向けIDEプロジェクト
 * <p>Made by Itsu(Twitter: @itsu_dev)
 *
 * @author Itsu
 *
 */

public class VersionChecker {
	
	public VersionChecker() {
		try {
			HttpURLConnection con = (HttpURLConnection) new URL("https://raw.githubusercontent.com/itsu020402/Comet/master/Version").openConnection();
			con.connect();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String version = reader.readLine();
			
			if(!Version.VERSION.equals(version)) {
				MessagePopup.information("使用可能なアップデート（" + version + "）があります。");
			}
		} catch (IOException e) {
			return;
		}
	}

}
