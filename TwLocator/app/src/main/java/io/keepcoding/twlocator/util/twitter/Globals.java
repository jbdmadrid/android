
package io.keepcoding.twlocator.util.twitter;

import android.content.Context;

public class Globals {
	//singleton twitterhelper
	private static TwitterHelper twitterHelper;
	public static final Integer MODE_CONNECT_FIRST_TIME = 1;
	public static final Integer MODE_RECONNECT = 2;

	public static TwitterHelper getSharedTwitterHelper(Context ctx) {
		if (twitterHelper == null) {
			twitterHelper = new TwitterHelper(ctx);
		}
		return twitterHelper;
	}
}
