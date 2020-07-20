package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import androidx.test.filters.LargeTest;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.R.id.txtTitre;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.AllOf.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MyNeighbourRecyclerViewAdapterTest {

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mListNeighbourActivityActivityTestRule =
            new ActivityTestRule<>(ListNeighbourActivity.class);
    private static int ITEMS_COUNT = 11;
    private static int FAVORITES_COUNT = 0;
    private NeighbourApiService mService;


    @Before
    public void setUp() {
        mService = DI.getNewInstanceApiService();

    }




    /** On verifie si le nom du texte affiché dans DetailActivity correspond au nom du voisin.**/





    @Test
    public  void checkIfNeighboursIsDelete (){
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(ITEMS_COUNT));
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.item_list_delete_button)));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }



    @Test
    public void myNeighboursListFavorite_deleteAction_shouldRemoveItemFromFavorite() {
        // Given : We remove the item in favorite List.

        //add item in favorite.
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.FavButton))
                .perform(click());
        pressBack();
        onView(withContentDescription("Favorites")).perform(click());
        //check if list is not empty.
        swipeLeft();
        onView(allOf(withId(R.id.item_list_delete_button),isDisplayed())).perform(click());



        // When perform a click on a delete icon
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours_favoris)).check(withItemCount(FAVORITES_COUNT));
    }



}