package net.zhuoweizhang.mcpeoptionseditor;

import java.util.*;

import android.content.*;
import android.preference.*;

public class OptionsFileSquash {

	public static void puff(Map<String, String> map, PreferenceGroup prefs) {
		for (Map.Entry<String, String> e : map.entrySet()) {
			//For each entry, get the type of preference and set its value accordingly
			Preference pref = prefs.findPreference(e.getKey());
			if (pref instanceof CheckBoxPreference) {
				((CheckBoxPreference) pref).setChecked(Integer.parseInt(e.getValue()) == 1);
			} else if (pref instanceof EditTextPreference) {
				((EditTextPreference) pref).setText(e.getValue());
			} else if (pref instanceof ListPreference) {
				((ListPreference) pref).setValueIndex(Integer.parseInt(e.getValue()));
			}
		}
	}

	public static Map<String, String> squish(PreferenceGroup prefs) {
		Map<String, String> map = new HashMap<String, String>();
		squishPref(prefs, map);
		return map;
	}

	private static void squishPref(Preference pref, Map<String, String> map) {
		if (pref instanceof PreferenceGroup) {
			PreferenceGroup prefs = (PreferenceGroup) pref;
			int count = prefs.getPreferenceCount();
			for (int i = 0; i < count; ++i) {
				Preference prefer = prefs.getPreference(i);
				squishPref(prefer, map);

			}
			return;
		}
		System.out.println(pref);
		String key = pref.getKey();
		String output = "";
		if (pref instanceof CheckBoxPreference) {
			output = ((CheckBoxPreference) pref).isChecked() ? "1" : "0";
		} else if (pref instanceof EditTextPreference) {
			output = ((EditTextPreference) pref).getText();
		} else if (pref instanceof ListPreference) {
			output = ((ListPreference) pref).getValue();
		}
		System.out.println(key + ":" + output);
		map.put(key, output);
	}

}
