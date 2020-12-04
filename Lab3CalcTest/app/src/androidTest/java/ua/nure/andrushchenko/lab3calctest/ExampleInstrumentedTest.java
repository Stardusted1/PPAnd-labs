package ua.nure.andrushchenko.lab3calctest;


import android.util.Log;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityScenarioRule<CalculatorActivity> rule = new ActivityScenarioRule<>(CalculatorActivity.class);
    @Before
    public void setUp() {
        Log.e("Test","Test set up");
    }

    @After
    public void tearDown() {
        Log.e("Test","Test tear down");
    }


    @Test
    public void isRightActivity() {
        Espresso.onView(ViewMatchers.withText("Lab3CalcTest")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void OnOnePlusOneTest() {
        Espresso.onView(ViewMatchers.withId(R.id.button_1))
                .perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.button_plus))
                .perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.button_2))
                .perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.button_equals))
                .perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.result))
                .check(ViewAssertions.matches(ViewMatchers.withText("3.0")));
    }

    @Test
    public void OnThreeDivideByTwoTest() {
        Espresso.onView(ViewMatchers.withId(R.id.button_3))
                .perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.button_divide))
                .perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.button_2))
                .perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.button_equals))
                .perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.result))
                .check(ViewAssertions.matches(ViewMatchers.withText("1.5")));
    }

    @Test
    public void OnThreeDivideByZeroTest() {
        Espresso.onView(ViewMatchers.withId(R.id.button_3))
                .perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.button_divide))
                .perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.button_0))
                .perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.button_equals))
                .perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.result))
                .check(ViewAssertions.matches(ViewMatchers.withText("Infinity")));
    }
}