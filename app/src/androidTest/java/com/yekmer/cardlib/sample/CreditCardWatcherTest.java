package com.yekmer.cardlib.sample;

import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by yekmer
 */
public class CreditCardWatcherTest extends ActivityInstrumentationTestCase2<SampleActivity> {

    public CreditCardWatcherTest() {
        super(SampleActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // For each test method invocation, the Activity will not actually be created
        // until the first time this method is called.
        getActivity();
    }

    public void testIsTextViewExists() {
        onView(withId(R.id.credit_card_edit_text)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.credit_card_edit_text)).check(matches(withText("1234 5")));
    }
}
