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

package com.company.ui.partials.impl;

import com.company.ui.partials.api.WithLogin;
import com.sabre.qa.gary.web.WebPage;
import com.sabre.qa.gary.web.browser.WebBrowser;
import com.sabre.qa.gary.web.view.AutoView;
import com.sabre.qa.gary.web.view.View;
import org.openqa.selenium.WebElement;

public class WithLoginImpl extends WebPage implements WithLogin {

    public WithLoginImpl(WebBrowser browser) {
        super(browser);
    }

    @Override
    public void checkKeepMeLoggedIn() {

        WebElement checkbox = getCheckbox();

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    @Override
    public void uncheckKeepMeLoggedIn() {

        WebElement checkbox = getCheckbox();

        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    @Override
    public void typeUsername(String text) {
        type($("#email"), text);
    }

    @Override
    public void typePassword(String text) {
        type($("#pass"), text);
    }

    @Override
    public View clickLoginButton() {
        $("#loginbutton input").click();
        return AutoView.match(browser).timeout().any();
    }

    private WebElement getCheckbox() {
        return $("#persist_box");
    }
}
