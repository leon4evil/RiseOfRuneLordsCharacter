package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.nameTextView), withText("New Character"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("New Character")));

        DataInteraction constraintLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.characterlist),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0)))
                .atPosition(0);
        constraintLayout.perform(click());

        DataInteraction constraintLayout2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.newCharacterList),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(1);
        constraintLayout2.perform(click());

        DataInteraction constraintLayout3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.characterlist),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0)))
                .atPosition(0);
        constraintLayout3.perform(click());

        ViewInteraction checkBox = onView(
                allOf(withText("+1"),
                        childAtPosition(
                                allOf(withId(R.id.flexboxlayout),
                                        childAtPosition(
                                                withId(R.id.currentItemView),
                                                1)),
                                1),
                        isDisplayed()));
        checkBox.perform(click());

        ViewInteraction checkBox2 = onView(
                allOf(withText("+2"),
                        childAtPosition(
                                allOf(withId(R.id.flexboxlayout),
                                        childAtPosition(
                                                withId(R.id.currentItemView),
                                                1)),
                                2),
                        isDisplayed()));
        checkBox2.perform(click());

        ViewInteraction checkBox3 = onView(
                allOf(withText("+2"),
                        childAtPosition(
                                allOf(withId(R.id.flexboxlayout),
                                        childAtPosition(
                                                withId(R.id.currentItemView),
                                                1)),
                                2),
                        isDisplayed()));
        checkBox3.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
