package com.example.android0128.introductionmvp.movies;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.android0128.introductionmvp.R;
import com.example.android0128.introductionmvp.movies.MovieActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.android0128.introductionmvp.TestUtils.getToolbarNavigationContentDescription;
import static com.example.android0128.introductionmvp.custom.action.NavigationViewActions.navigateTo;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MoviesScreenTest {

    @Rule
    public ActivityTestRule<MovieActivity> mActivityTestRule =
            new ActivityTestRule<>(MovieActivity.class);

    @Test
    public void clickAddTaskButton_opensAddTaskUi() {
        /*onView(ViewMatchers.withId(R.id.toolbar))
                .perform(navigateTo(R.id.action_go_favorites));
                */

        onView(withId(R.id.toolbar)).perform(click());

        /*onView(withContentDescription(getToolbarNavigationContentDescription(
                mActivityTestRule.getActivity(), R.id.toolbar)))
                .perform(navigateTo(R.id.action_go_favorites));*/

        /*onView(withContentDescription(getToolbarNavigationContentDescription(
                mActivityTestRule.getActivity(), R.id.toolbar))).perform(click());
                */


        // Click on the add task button
        //onView(withId(R.id.action_go_favorites)).perform(click());

        // Check if the add task screen is displayed
        /*onView(withId(R.id.add_task_title)).check(matches(isDisplayed()));
        onView(withId(R.id.add_task_title)).perform(typeText(title),
                closeSoftKeyboard());
                */
    }
}
