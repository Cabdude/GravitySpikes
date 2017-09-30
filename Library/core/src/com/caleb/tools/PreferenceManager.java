package com.caleb.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferenceManager {

	private Preferences levelPrefs;

	public PreferenceManager() {
	}

	public void addPreferences(String level) {
		getPreferenceFile().putString("Level_" + level, level);
		getPreferenceFile().flush();
	}

	public String getPreferences(String level) {
		return getPreferenceFile().getString("Level_" + level);
	}

	public Preferences getPreferenceFile() {
		if (levelPrefs == null) {
			levelPrefs = Gdx.app.getPreferences("UserLevelData.txt");
		}

		return levelPrefs;
	}

}
