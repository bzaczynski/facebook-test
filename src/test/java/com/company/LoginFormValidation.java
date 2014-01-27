/**
 * Copyright 2014 Bartosz Zaczynski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.company;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;

import static com.sabre.qa.gary.testng.providers.xml.XmlDataProvider.getNamedObject;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.company.model.FacebookProfile;
import com.company.testcase.FacebookTestCase;
import com.company.ui.view.api.HomeView;
import com.company.ui.view.api.IndexView;
import com.company.ui.view.api.LoginView;
import com.sabre.qa.gary.annotation.ManualSteps;
import com.sabre.qa.gary.testng.providers.xml.XmlDataProvider;
import com.sabre.qa.gary.web.view.View;

public class LoginFormValidation extends FacebookTestCase {

    // these will show in test report (useful for manual reproduction):

    @ManualSteps(
        preconditions = "Open Facebook index page at http://www.facebook.com.",
        steps = {
            "Check \"Keep me logged in flag\"",
            "Type invalid username.",
            "Type fake password.",
            "Click login button.",
            "Assert that error message is shown."}
    )

    // this will automatically locate and pull test arguments from XML:

    @Test(dataProvider = "XmlDataProvider",
          dataProviderClass = XmlDataProvider.class)
    
    public void shouldShowErrorOnIncorrectEmail(FacebookProfile fakeProfile,
                                                boolean keepMeLoggedIn) {

        // given
        IndexView indexView = (IndexView) application.getHomePage();

        // when
        if (keepMeLoggedIn) {
            Reporter.log("Checking \"Keep me logged in flag\"");
            indexView.checkKeepMeLoggedIn();
        } else {
            Reporter.log("Unhecking \"Keep me logged in flag\"");
            indexView.uncheckKeepMeLoggedIn();
        }

        Reporter.log("Typing username and password");
        indexView.typeUsername(fakeProfile.getUsername());
        indexView.typePassword(fakeProfile.getPassword());

        Reporter.log("Clicking login button");
        View view = indexView.clickLoginButton();

        // then
        assertThat("Navigate to login view", view.is(LoginView.class));

        LoginView loginView = $(view);

        assertThat("Error displayed", loginView.isErrorDisplayed());
        assertThat(loginView.getErrorMessage(), anyOf(equalTo("Incorrect Email"),
                                                      equalTo("Please re-enter your password")));
    }
    
    @Test(dependsOnMethods = {"shouldShowErrorOnIncorrectEmail"})
    public void shouldLoginToFacebook() {
        
        // given
        IndexView indexView = (IndexView) application.getHomePage();
        FacebookProfile profile = getRealFacebookProfile(); 

        // when
        indexView.typeUsername(profile.getUsername());
        indexView.typePassword(profile.getPassword());

        View view = indexView.clickLoginButton();

        // then
        assertThat("Navigate to home view", view.is(HomeView.class));

        HomeView homeView = $(view);

        assertThat(homeView.getWelcomeMessage(),
                   containsString("Welcome to Facebook"));
    }
    
    private FacebookProfile getRealFacebookProfile() {
        
        // programmatically obtain a named object from XML:
        
        return (FacebookProfile) getNamedObject("facebook.test.user.real");
    }
}
