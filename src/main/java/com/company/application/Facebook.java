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

package com.company.application;

import com.sabre.qa.gary.web.WebApplication;
import com.sabre.qa.gary.web.WebPage;
import com.sabre.qa.gary.web.browser.WebBrowser;
import com.sabre.qa.gary.web.view.AutoView;

public class Facebook extends WebApplication {

    public Facebook(WebBrowser browser) {
        super(browser);
    }

    @Override
    public WebPage getHomePage() {
        browser.get("http://www.facebook.com");
        return (WebPage) AutoView.match(browser).any();
    }
}
