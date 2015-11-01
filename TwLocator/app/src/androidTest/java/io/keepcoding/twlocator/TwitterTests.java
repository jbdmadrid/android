package io.keepcoding.twlocator;

import android.test.AndroidTestCase;

import io.keepcoding.twlocator.model.Twitter;

public class TwitterTests extends AndroidTestCase {
    public void testCanCreateATweets() {
        Twitter tweet = new Twitter("prueba", "hola mundo");

        assertNotNull(tweet);
    }

}
