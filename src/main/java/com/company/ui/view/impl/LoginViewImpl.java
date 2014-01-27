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

package com.company.ui.view.impl;

import com.company.ui.partials.api.WithLogin;
import com.company.ui.partials.impl.WithLoginImpl;
import com.company.ui.view.api.LoginView;
import com.sabre.qa.gary.web.browser.WebBrowser;
import com.sabre.qa.gary.web.view.View;
import com.sabre.qa.gary.web.view.ViewImpl;

public class LoginViewImpl extends ViewImpl implements LoginView {

    private WithLogin withLogin;

    public LoginViewImpl(WebBrowser browser) {
        super(browser);
        withLogin = new WithLoginImpl(browser);
    }

    @Override
    public String getUrlPattern() {
        return ".*?www.facebook.com/login.php.*";
    }

    @Override
    public boolean isContentIdentified() {
        return $("body").hasClass("login_page");
    }

    @Override
    public boolean isErrorDisplayed() {
        return $(".login_error_box").isPresent();
    }

    @Override
    public String getErrorMessage() {
        return $(".login_error_box > div:nth-of-type(1)").getText();
    }

    // --- WithLogin partial:

    @Override
    public void checkKeepMeLoggedIn() {
        withLogin.checkKeepMeLoggedIn();
    }

    @Override
    public void uncheckKeepMeLoggedIn() {
        withLogin.uncheckKeepMeLoggedIn();
    }

    @Override
    public void typeUsername(String text) {
        withLogin.typeUsername(text);
    }

    @Override
    public void typePassword(String text) {
        withLogin.typePassword(text);
    }

    @Override
    public View clickLoginButton() {
        return withLogin.clickLoginButton();
    }
}
