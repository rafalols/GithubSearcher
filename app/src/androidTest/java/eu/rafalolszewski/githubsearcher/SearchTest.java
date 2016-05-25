package eu.rafalolszewski.githubsearcher;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import eu.rafalolszewski.githubsearcher.model.SearchHistory;
import eu.rafalolszewski.githubsearcher.ui.search.SearchActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Created by rafal on 25.05.16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchTest {

    private static final String USER_FOR_SEARCH = "rafalols";
    private static final String USER_FOR_SEARCH2 = "google";

    IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<SearchActivity> activityTestRule = new ActivityTestRule<SearchActivity>(SearchActivity.class);

    @Before
    public void registerIdlingResource() {
        GitHubSearcherApplication app =  (GitHubSearcherApplication) activityTestRule.getActivity().getApplication();
        idlingResource = app.appComponent.countingIdlingResource();
        Espresso.registerIdlingResources(idlingResource);
    }

    @Test
    public void searchTestByClickingEnter() throws Throwable {
        onView(withId(R.id.edittext_searcher))
                .perform(click())
                .perform(typeText(USER_FOR_SEARCH))
                .perform(pressImeActionButton());

        onView(withId(R.id.recycler_user_list))
                .perform(scrollTo(hasDescendant(withText(USER_FOR_SEARCH))));
    }

    @Test
    public void searchTestByClickingButton() throws Throwable {
        onView(withId(R.id.edittext_searcher))
                .perform(click())
                .perform(typeText(USER_FOR_SEARCH));
        onView(withId(R.id.button_search))
                .perform(click());

        onView(withId(R.id.recycler_user_list))
                .perform(scrollTo(hasDescendant(withText(USER_FOR_SEARCH))));
    }

    @Test
    public void historyTest() throws Throwable{
        //Search for user
        onView(withId(R.id.edittext_searcher))
                .perform(click())
                .perform(typeText(USER_FOR_SEARCH2))
                .perform(pressImeActionButton());

        Espresso.pressBack();

        //check if at fisrt position is previous search
        onData(instanceOf(SearchHistory.class))
                .inAdapterView(withId(R.id.list_search_history))
                .atPosition(0)
                .onChildView(withId(R.id.search))
                .check(matches(withText(USER_FOR_SEARCH2)));
    }

    @After
    public void unregisterIdlingResource(){
        Espresso.unregisterIdlingResources(idlingResource);
    }

}
