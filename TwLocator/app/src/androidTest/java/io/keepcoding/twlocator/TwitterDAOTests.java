package io.keepcoding.twlocator;

import android.content.Context;
import android.database.Cursor;
import android.test.AndroidTestCase;

import java.lang.ref.WeakReference;

import io.keepcoding.twlocator.model.Twitter;
import io.keepcoding.twlocator.model.dao.TwitterDAO;
import io.keepcoding.twlocator.model.db.DBHelper;

public class TwitterDAOTests extends AndroidTestCase {
    public void testInsertNullTweetReturnsInvalidId() {
        Twitter tweet = null;

       // final WeakReference<Context> context = null;
        WeakReference<Context> weakself = new WeakReference<Context>(getContext());

        TwitterDAO dao = new TwitterDAO(weakself);

        long id = dao.insert(tweet);

        assertEquals(id, DBHelper.INVALID_ID);
    }

    public void testInsertTweetWithNullNameReturnsInvalidId() {
        Twitter tweet = new  Twitter("","");
        tweet.setName(null);

        WeakReference<Context> weakself = new WeakReference<Context>(getContext());

        TwitterDAO dao = new TwitterDAO(weakself);

        long id = dao.insert(tweet);

        assertEquals(id, DBHelper.INVALID_ID);
    }
    public void testInsertTweetWithNameReturnsInvalidId() {
        Twitter tweet = new  Twitter("ddd","dddd");


        WeakReference<Context> weakself = new WeakReference<Context>(getContext());

        TwitterDAO dao = new TwitterDAO(weakself);

        long id = dao.insert(tweet);

    }

    public void testQueryAllTweets() {
        insertTwitterStubs(10);

        WeakReference<Context> weakself = new WeakReference<Context>(getContext());

        TwitterDAO dao = new TwitterDAO(weakself);
        final Cursor cursor = dao.queryCursor();
        final int notebookCount = cursor.getCount();

        assertTrue(notebookCount > 9);
    }

    private void insertTwitterStubs(final int tweetToInsert) {

        WeakReference<Context> weakself = new WeakReference<Context>(getContext());

        TwitterDAO dao = new TwitterDAO(weakself);
        for (int i = 0; i < tweetToInsert; i++) {
            final String testName = String.format("%s %d", "Test title", i);
            final String testTweet = String.format("%s %d", "Test title", i);
            final Twitter twitter = new Twitter(testName,testTweet);
            long id = dao.insert(twitter);
        }

    }

    public void testDeleteAllTweets() {
        insertTwitterStubs(10);

        WeakReference<Context> weakself = new WeakReference<Context>(getContext());

        TwitterDAO dao = new TwitterDAO(weakself);
        dao.deleteAll();

        final Cursor cursor = dao.queryCursor();
        final int tweetsCount = cursor.getCount();

        assertEquals(0, tweetsCount);
    }




}
