package com.vinted.api.automation;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("com.vinted.api.automation")
@IncludeTags("smoke")
public class SmokeTestSuite {

}
