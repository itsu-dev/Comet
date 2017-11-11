package com.Itsu.Comet.utils;

import java.awt.SplashScreen;
import java.io.IOException;

public class SplashWindow {
	
	public SplashWindow() throws NullPointerException, IllegalStateException, IOException {
		SplashScreen splashScreen = SplashScreen.getSplashScreen();
		splashScreen.setImageURL(this.getClass().getClassLoader().getResource("Comet/splash/Splash.png"));
	}

}
