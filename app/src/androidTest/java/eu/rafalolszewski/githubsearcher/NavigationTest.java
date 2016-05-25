package eu.rafalolszewski.githubsearcher;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import eu.rafalolszewski.githubsearcher.ui.search.SearchActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by rafal on 25.05.16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NavigationTest {

    private static final String USER_FOR_SEARCH = "rafalols";

    @Rule
    public ActivityTestRule<SearchActivity> activityTestRule = new ActivityTestRule<SearchActivity>(SearchActivity.class);

    @Test
    public void searchTestByClickingEnter() throws Throwable {
        onView(withId(R.id.edittext_searcher))
                .perform(click())
                .perform(typeText(USER_FOR_SEARCH))
                .perform(pressImeActionButton());


        onView(withId(R.id.recycler_user_list))
                .perform(scrollTo(hasDescendant(withText(USER_FOR_SEARCH))));

    }


}
