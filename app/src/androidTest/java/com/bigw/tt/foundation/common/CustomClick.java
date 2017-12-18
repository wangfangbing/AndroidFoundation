package com.bigw.tt.foundation.common;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import org.hamcrest.Matcher;

/**
 * Created by bigw on 19/12/2017.
 */
public class CustomClick implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return ViewMatchers.isEnabled();
    }

    @Override
    public String getDescription() {
        return "fake description";
    }

    @Override
    public void perform(UiController uiController, View view) {
        view.performClick();
    }
}
