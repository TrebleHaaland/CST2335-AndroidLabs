package ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.core.AllOf.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.jetbrains.annotations.Contract;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import algonquin.cst2335.emmanuelsandroidlabs.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatEditText = onView(withId(android.R.id.content));
        appCompatEditText.perform(replaceText("12345"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(allOf(withId(R.id.myButton) ));
        materialButton.perform(click());

        ViewInteraction textView = onView((withId(R.id.textView) ));
        textView.check(matches(withText("You shall not pass!")));
    }

    @Test
    public void testMissingUpperCase() {
        ViewInteraction appCompatEditText = onView(withId(android.R.id.content));
        appCompatEditText.perform(replaceText("alabitobi1"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(allOf(withId(R.id.myButton) ));
        materialButton.perform(click());

        ViewInteraction textView = onView((withId(R.id.textView) ));
        textView.check(matches(withText("You shall not pass!")));
    }
        @Test
        public void testMissingLowerCase() {
            ViewInteraction appCompatEditText = onView(withId(android.R.id.content));
            appCompatEditText.perform(replaceText("ALABITOBI1"), closeSoftKeyboard());

            ViewInteraction materialButton = onView(allOf(withId(R.id.myButton) ));
            materialButton.perform(click());

            ViewInteraction textView = onView((withId(R.id.textView) ));
            textView.check(matches(withText("You shall not pass!")));
        }
        @Test
        public void testMissingNumeric() {
            ViewInteraction appCompatEditText = onView(withId(android.R.id.content));
            appCompatEditText.perform(replaceText("AlabiTOBI"), closeSoftKeyboard());

            ViewInteraction materialButton = onView(allOf(withId(R.id.myButton) ));
            materialButton.perform(click());

            ViewInteraction textView = onView((withId(R.id.textView) ));
            textView.check(matches(withText("You shall not pass!")));
        }
        @Test
        public void testAllRequirementsMet() {
            ViewInteraction appCompatEditText = onView(withId(android.R.id.content));
            appCompatEditText.perform(replaceText("AlabiTOBI1!"), closeSoftKeyboard());
            ViewInteraction materialButton = onView(allOf(withId(R.id.myButton) ));
            materialButton.perform(click());

            ViewInteraction textView = onView((withId(R.id.textView) ));
            textView.check(matches(withText("Your password meets the requirements")));
        }

    @NonNull
    @Contract("_, _ -> new")
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
