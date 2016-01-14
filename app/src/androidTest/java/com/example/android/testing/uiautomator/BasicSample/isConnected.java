package com.example.android.testing.uiautomator.BasicSample;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

/**
 *
 *  Use uiautomator 2.0 framework
 *  Testing Support Library
 *  to check if there is a Wi-Fi direct peer connection
 *
 *  @author  Anthony Nutter
 *  @version 1.0
 *  @since   2016-1-13
 */
public class isConnected extends UiAutomatorTestCase
{

	private static final String LOG_TAG = "WiFiDirectAutomation";
	private boolean LOLLIPOP_OR_BELOW;
	private UiDevice device;

	public void testDemo() throws UiObjectNotFoundException {

		device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
		System.out.println("Waiting for Connection Confirmation..!");
		Log.e(LOG_TAG, "Waiting for Connection Confirmation..!");

		if (android.os.Build.VERSION.SDK_INT <= 21) //LOLLIPOP_MR1 is 22
		{
			LOLLIPOP_OR_BELOW = true;
		} else
		{
			LOLLIPOP_OR_BELOW = false;
		}
		boolean success = false;

		for (int i = 1; i <= 3 && !success; i++)
		{
			System.out.println("Trying to Check for Connection status for " + i + " time");
			success = checkConnection();
		}
		if (!success)
		{
			System.out.println("\nNo P2P connection found..");
			Log.e(LOG_TAG, "No P2P connection found..");
		}
	}

	public boolean checkConnection()
	{
		boolean success = false;
		System.out.println("In checkConnection()");
		UiObject P2PdeviceID;

		if (LOLLIPOP_OR_BELOW)
		{
			P2PdeviceID = device.findObject(new UiSelector().text("Connected"));
		} else
		{
			P2PdeviceID = device.findObject(new UiSelector().textContains("disconnect"));
		}
		assertEquals("isConnected test shows no active P2P connection", true, P2PdeviceID.exists());

		if (P2PdeviceID.exists())
		{
			System.out.println("P2P Connection established");
			Log.e(LOG_TAG, "P2P Connection established");
			success = true;
		}
		else
		{
			System.out.println("P2P Connection not established");
			Log.e(LOG_TAG, "P2P Connection not established");
		}
		return success;
	}
}