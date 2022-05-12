package com.hibernate.services;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.runner.RunWith;
@RunWith(JUnitPlatform.class)
@SelectClasses({EmployeeServiceTest.class,ItemServiceTest.class})

public class MySuiteTests {
}
