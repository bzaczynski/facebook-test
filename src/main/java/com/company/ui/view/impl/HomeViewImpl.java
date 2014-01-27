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

import com.company.ui.view.api.HomeView;
import com.sabre.qa.gary.web.browser.WebBrowser;
import com.sabre.qa.gary.web.view.ViewImpl;

public class HomeViewImpl extends ViewImpl implements HomeView {

    public HomeViewImpl(WebBrowser browser) {
        super(browser);
    }

    @Override
    public String getUrlPattern() {
        return ".*?www.facebook.com.*?welcome.*";
    }

    @Override
    public boolean isContentIdentified() {
        return $("body").hasClass("home");
    }

    @Override
    public String getWelcomeMessage() {
        return $("#pagelet_welcome h2.uiHeaderTitle").getText();
    }
}
