package com.example.android.testing.uiautomator.BasicSample;

import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import java.io.FileNotFoundException;
/**
 *
 *  Use uiautomator 2.0 framework
 *  Testing Support Library
 *  to send a Wi-Fi direct invitation to connect
 *
 *  @author  Anthony Nutter
 *  @version 1.0
 *  @since   2016-1-13
 */
public class sendInvite extends UiAutomatorTestCase
{

	UiDevice device;
	private static final String LOG_TAG = "WiFiDirectAutomation";
	private boolean LOLLIPOP_OR_BELOW;

	public void testDemo() throws UiObjectNotFoundException, FileNotFoundException
	{
		if (android.os.Build.VERSION.SDK_INT <= 21) //LOLLIPOP_MR1 is 22
		{
			LOLLIPOP_OR_BELOW = true;
		} else
        {
			LOLLIPOP_OR_BELOW = false;
		}
		device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
		Bundle extras = InstrumentationRegistry.getArguments();
		String peerID = null;
        // get the peer device's model name
		if ( extras != null )
        {
			if ( extras.containsKey ( "peerID" ) )
            {
				peerID = extras.getString("peerID");
				System.out.println("PeerID: " + peerID);
			} else
            {
				System.out.println("No PeerID in extras");
			}
		} else
        {
			System.out.println("No extras");
		}
		boolean success = false;
		for (int i = 1; i <= 3 && !success; i++) {
			System.out.println("Trying to Send Invitation for " + i + " time");
			success = connect(peerID);
		}
		if (!success) {
			Log.e(LOG_TAG, "Couldn't find peerID in list to send the invite");
		}

	}

    // find a widget containing the peer device's model name and clcik it
	public boolean connect(String peerID)
    {

		boolean success = false;

		UiObject P2PdeviceID = device.findObject(new UiSelector().textContains(peerID));
		assertEquals("Did not find peerID", true, P2PdeviceID.exists());
		try
        {
			if (P2PdeviceID != null)
            {
				P2PdeviceID.click();
				success = true;
			}
		} catch (UiObjectNotFoundException e)
        {
			System.out.println("Did not find device with PeerID: " + peerID + e.getLocalizedMessage());
		}
		return success;
	}

}
