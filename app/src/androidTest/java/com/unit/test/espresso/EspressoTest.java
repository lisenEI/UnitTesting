package com.unit.test.espresso;

import android.support.test.runner.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import com.unit.test.unittesting.MainActivity;
import com.unit.test.unittesting.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * @author lisen
 * @since 12-11-2018
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test() {
        onView(withId(R.id.bt_toast)).perform(click());
    }

}
