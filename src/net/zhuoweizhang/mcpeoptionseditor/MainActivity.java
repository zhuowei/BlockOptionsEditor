package net.zhuoweizhang.mcpeoptionseditor;

import java.util.*;

import android.app.*;
import android.os.Bundle;

import android.preference.*;

import android.view.*;
import android.widget.*;

import android.widget.FrameLayout.LayoutParams;

import com.amazon.device.ads.*;

public class MainActivity extends PreferenceActivity /*implements Preference.OnPreferenceClickListener*/ {

    private AdLayout adView; // The ad view used to load and display the ad.
    private static final String APP_KEY = "d279a4036c1d4cd89a26daf41d1c42d2"; // Sample Application Key. Replace this variable with your Application Key
    private static final String LOG_TAG = "SimpleAdSample"; // Tag used to prefix all log messages

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); //to include our advertisement view
		addPreferencesFromResource(R.xml.preferences);
		setupAdvertisements();
	}

	public void onResume() {
		super.onResume();
		try {
			Map<String, String> prefsMap = OptionsFileIO.readOptionsFile();
			OptionsFileSquash.puff(prefsMap, getPreferenceScreen());
		} catch (Exception e) {
			e.printStackTrace();
		}
		loadAd();
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

	protected void setupAdvertisements() {
        // For debugging purposes enable logging, but disable for production builds
        AdRegistration.enableLogging(BuildConfig.DEBUG);
        // For debugging purposes flag all ad requests as tests, but set to false for production builds
        AdRegistration.enableTesting(BuildConfig.DEBUG);
        
        adView = (AdLayout)findViewById(R.id.ad_view);
        
        try {
            AdRegistration.setAppKey(APP_KEY);
        } catch (Exception e) {
		e.printStackTrace();
            return;
        }
        
    }
    
    /**
     * Load a new ad.
     */
    public void loadAd() { 
        // Load the ad with the appropriate ad targeting options.
        AdTargetingOptions adOptions = new AdTargetingOptions();
        adView.loadAd(adOptions);
    }
}
