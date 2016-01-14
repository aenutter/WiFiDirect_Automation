package com.example.android.testing.uiautomator.BasicSample;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 *  Use uiautomator 2.0 framework
 *  Testing Support Library
 *  to check if Wi-Fi is enabled and enable
 *  Wi-Fi direct
 *
 *  @author  Anthony Nutter
 *  @version 1.0
 *  @since   2016-1-13
 */
public class openWifiDirect extends UiAutomatorTestCase
{

	private boolean LOLLIPOP_OR_BELOW;
	private static final String LOG_TAG = "WiFiDirectAutomation";
	private UiDevice device;

	public void testDemo() throws UiObjectNotFoundException
    {

		if (android.os.Build.VERSION.SDK_INT <= 21) //LOLLIPOP_MR1 is 22
        {
			LOLLIPOP_OR_BELOW = true;
		} else {
			LOLLIPOP_OR_BELOW = false;
		}

		Log.e(LOG_TAG, "Starting our test!");
		device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
		// Open WiFi Settings
		UiObject wifiOption = device.findObject(new UiSelector().textContains("Fi"));
		try
        {
			wifiOption.click();
		} catch (UiObjectNotFoundException e)
        {
			e.printStackTrace();
		}

        // Check for Wi-Fi State
		UiObject isWiFiON = device.findObject(new UiSelector().className("android.widget.Switch"));
		assertThat(isWiFiON.isChecked(), is(equalTo(true)));
		if (isWiFiON.isChecked())
        {
            System.out.println("WiFi is Turned ON");
            Log.e(LOG_TAG, "WiFi is Turned ON");
			sleep(2000);
			if (LOLLIPOP_OR_BELOW) {
                getUiDevice().pressMenu();
            }
			UiObject selectP2P = device.findObject(new UiSelector().textContains("Fi Direct"));
			try
            {
				selectP2P.clickAndWaitForNewWindow();	
			} catch(UiObjectNotFoundException e)
			{
                e.printStackTrace();
			}
		// Wi-Fi is disabled
		} else {
			System.out.println("WiFi is Turned OFF");
			Log.e(LOG_TAG, "WiFi is Turned OFF");
		}
	}
}
