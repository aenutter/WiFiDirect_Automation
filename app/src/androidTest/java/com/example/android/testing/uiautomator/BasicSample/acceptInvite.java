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
 *  to accept a Wi-Fi direct invitation
 *
 *  @author  Anthony Nutter
 *  @version 1.0
 *  @since   2016-1-13
 */
public class acceptInvite extends UiAutomatorTestCase
{
	private UiDevice device;
	private static final String LOG_TAG = "WiFiDirectAutomation";
	private boolean LOLLIPOP_OR_BELOW;

	public void testDemo() throws UiObjectNotFoundException
    {

		device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
		Log.e(LOG_TAG, "Waiting for Invitation!");
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
			System.out.println("Trying to Accept Invite for " + i + " time");
			success = accept();
		}
		if (!success)
        {
			Log.e(LOG_TAG, "Didn't get any invitation from peer");
		}
	}

	public boolean accept() {
		boolean success = false;
        UiObject isConnected = null;

				UiObject okCancelDialog = device.findObject(new UiSelector().textContains("Invitation to connect"));
                assertEquals("Did not receive invitation to connect", true, okCancelDialog.exists());
				if (okCancelDialog.exists())
                {
					Log.w(LOG_TAG, "Recieved Invitation !");
					UiObject okButton =device.findObject(new UiSelector().text("Connect"));
					try
                    {
						okButton.clickAndWaitForNewWindow();
                        if (LOLLIPOP_OR_BELOW)
                        {
                            isConnected = device.findObject(new UiSelector().text("Connected"));
                        } else
                        {
                            isConnected = device.findObject(new UiSelector().textContains("disconnect"));
                        }

						if (isConnected.exists())
                        {
							Log.w(LOG_TAG, "P2P Connected!");
							success = true;
						}
						assertEquals("Invitation received but did not connect", true, isConnected.exists());

					} catch (UiObjectNotFoundException e)
                    {
						// TODO Auto-generated catch block
                        Log.e(LOG_TAG, "Exception on Connect button!" + e.getMessage());
					}
				}
                else
                {
                    Log.w(LOG_TAG, "Did not recieve Invitation !");
                }
		return success;
	}

}