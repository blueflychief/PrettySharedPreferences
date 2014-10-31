package com.tale.prettysharedpreferences;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import com.tale.prettysharedpreferences.sample.InputActivity;
import com.tale.prettysharedpreferences.sample.R;

import java.util.Random;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.typeText;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;

/**
 * Created by TALE on 10/30/2014.
 */
@LargeTest
public class InputActivityTest extends ActivityInstrumentationTestCase2<InputActivity> {

    public final String TAG = InputActivity.class.getSimpleName();
    public Random random;

    @SuppressWarnings("deprecation")
    public InputActivityTest() {
        // This constructor was deprecated - but we want to support lower API levels.
        super("com.tale.prettysharedpreferences.sample", InputActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        // Espresso will not launch our activity for us, we must launch it via getActivity().
        getActivity();
        random = new Random();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        Log.d(TAG, "tearDown");
    }

    public void testSaveLoadString() {
        final String value = getStringValue();
        onView(withId(R.id.etString)).perform(typeText(value));
        onView(withId(R.id.btSaveString)).perform(click());
        onView(withId(R.id.tvStringVal)).check(matches(withText(value)));
    }

    public void testSaveLoadBoolean() {
        final String value = getBooleanValue();
        onView(withId(R.id.etBoolean)).perform(typeText(value));
        onView(withId(R.id.btSaveBoolean)).perform(click());
        onView(withId(R.id.tvBooleanVal)).check(matches(withText(value)));
    }

    public void testSaveLoadInteger() {
        final String value = getIntValue();
        onView(withId(R.id.etInteger)).perform(typeText(value));
        onView(withId(R.id.btSaveInteger)).perform(click());
        onView(withId(R.id.tvIntegerVal)).check(matches(withText(value)));
    }

    public void testSaveLoadLong() {
        final String value = getLongValue();
        onView(withId(R.id.etLong)).perform(typeText(value));
        onView(withId(R.id.btSaveLong)).perform(click());
        onView(withId(R.id.tvLongVal)).check(matches(withText(value)));
    }

    public void testSaveLoadFloat() {
        final String value = getFloatValue();
        onView(withId(R.id.etFloat)).perform(typeText(value));
        onView(withId(R.id.btSaveFloat)).perform(click());
        onView(withId(R.id.tvFloatVal)).check(matches(withText(value)));
    }

    public void testSaveLoadDouble() {
        final String value = getDoubleValue();
        onView(withId(R.id.etDouble)).perform(typeText(value));
        onView(withId(R.id.btSaveDouble)).perform(click());
        onView(withId(R.id.tvDoubleVal)).check(matches(withText(value)));
    }


    public void testSaveLoadAll() {
        String stringVal = getStringValue();
        onView(withId(R.id.etString)).perform(typeText(stringVal));

        String boolVal = getBooleanValue();
        onView(withId(R.id.etBoolean)).perform(typeText(boolVal));

        String intVal = getIntValue();
        onView(withId(R.id.etInteger)).perform(typeText(intVal));

        String longVal = getLongValue();
        onView(withId(R.id.etLong)).perform(typeText(longVal));

        String floatVal = getFloatValue();
        onView(withId(R.id.etFloat)).perform(typeText(floatVal));

        String doubleVal = getDoubleValue();
        onView(withId(R.id.etDouble)).perform(typeText(doubleVal));

        // Click save all
        onView(withId(R.id.btSaveAll)).perform(click());

        // Check
        onView(withId(R.id.tvStringVal)).check(matches(withText(stringVal)));
        onView(withId(R.id.tvBooleanVal)).check(matches(withText(boolVal)));
        onView(withId(R.id.tvIntegerVal)).check(matches(withText(intVal)));
        onView(withId(R.id.tvLongVal)).check(matches(withText(longVal)));
        onView(withId(R.id.tvFloatVal)).check(matches(withText(floatVal)));
        onView(withId(R.id.tvDoubleVal)).check(matches(withText(doubleVal)));
    }

    private String getStringValue() {
        return String.format("String value: %d", random.nextInt());
    }

    private String getBooleanValue() {
        return random.nextInt() % 2 == 0 ? "true" : "false";
    }

    private String getIntValue() {
        return String.valueOf(random.nextInt());
    }

    private String getLongValue() {
        return String.valueOf(random.nextLong());
    }

    private String getFloatValue() {
        return String.valueOf(random.nextFloat());
    }

    private String getDoubleValue() {
        return String.valueOf(random.nextDouble());
    }
}