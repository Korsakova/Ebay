/*
 * Copyright 2002 - 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.steps;

import example.common.configuration.EnvProps;
import example.common.services.attachments.Attachments;
import example.common.services.attachments.SelenoidVideo;
import example.common.services.containers.BrowserDataContainer;
import example.common.services.containers.CompanyDataContainer;
import example.common.services.containers.TestProps;
import example.common.services.runners.CustomJunitRunner;
import example.common.services.utils.Check;
import example.common.services.utils.generate;
import example.configuration.AppConfig;
import example.pages.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

/**
 *
 */
@RunWith(CustomJunitRunner.class) // Custom Runner For Listener
@ContextConfiguration(classes = {AppConfig.class})
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@Slf4j
public class CommonStepDef {
    protected CompanyDataContainer company = CompanyDataContainer.getInstance();
    protected BrowserDataContainer browser = BrowserDataContainer.getInstance();

    @ClassRule
    public static TestWatcher testWatcherCreator() {
        return new TestWatcher() {
            @Override
            protected void starting(Description description) {
                super.starting(description);
                TestProps.des = description;
                MDC.put("ClassName", description.getDisplayName());
            }
        };
    }

    @Before
    public void before() {
        TestProps.sessionId = selenoid.getSessionId();
        TestProps.ListSessionId.add(selenoid.getSessionId());
    }

    @After
    public void attachVideo() {
        driver.quit(); // close web driver. If it disable =>turn on @Bean(destroyMethod = "quit")
        if (Boolean.valueOf(props.getEnableVideo())) {
            selenoid.attachAllureVideo(TestProps.sessionId);
        }
    }

    //Configuration
    @Autowired
    @Lazy
    public WebDriver driver;

    @Autowired
    @Lazy
    public EnvProps props;

    @Autowired
    @Lazy
    protected Attachments attachments;

    @Autowired
    @Lazy
    public SelenoidVideo selenoid;

    @Autowired
    @Lazy
    public generate gen;

    @Autowired
    @Lazy
    public Check check;

    // PLEASE USE ONLY PO PATTERN:
    @Autowired
    @Lazy
    protected HomePO homePO;

    @Autowired
    @Lazy
    protected LoginPO loginPO;

    @Autowired
    @Lazy
    protected InMessagePO inMessagePO;

    @Autowired
    @Lazy
    protected NewMessagePO newMessagePO;

    @Autowired
    @Lazy
    protected InboxPO inboxPO;

}
