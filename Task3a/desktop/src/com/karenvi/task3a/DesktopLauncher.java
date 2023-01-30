package com.karenvi.task3a;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.karenvi.task3a.MyTask3a;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Software Architecture Task 3 (a) - Karen Villmones");
		config.setWindowedMode(480, 800);
		config.useVsync(true);
		new Lwjgl3Application(new MyTask3a(), config);
	}
}
