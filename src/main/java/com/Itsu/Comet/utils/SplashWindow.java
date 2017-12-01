package com.Itsu.Comet.utils;

import java.awt.SplashScreen;
import java.io.IOException;

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

public class SplashWindow {
	
	public SplashWindow() throws NullPointerException, IllegalStateException, IOException {
		SplashScreen splashScreen = SplashScreen.getSplashScreen();
		splashScreen.setImageURL(this.getClass().getClassLoader().getResource("Comet/splash/Splash.png"));
	}

}
