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

package com.company.testcase;

import com.company.application.Facebook;
import com.sabre.qa.gary.base.WebDriverTestCase;
import org.testng.annotations.BeforeClass;

public abstract class FacebookTestCase extends WebDriverTestCase {

    @BeforeClass(alwaysRun = true)
    protected void initApplication() {
        application = new Facebook(browser);
    }
}
