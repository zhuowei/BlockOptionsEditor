package net.zhuoweizhang.mcpeoptionseditor;

import java.util.*;

import android.app.*;
import android.os.Bundle;

import android.preference.*;

public class MainActivity extends PreferenceActivity /*implements Preference.OnPreferenceClickListener*/ {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}

	public void onResume() {
		super.onResume();
		try {
			Map<String, String> prefsMap = OptionsFileIO.readOptionsFile();
			OptionsFileSquash.puff(prefsMap, getPreferenceScreen());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onPause() {
		super.onPause();
		try {
			Map<String, String> prefsMap = OptionsFileSquash.squish(getPreferenceScreen());
			OptionsFileIO.writeOptionsFile(prefsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
