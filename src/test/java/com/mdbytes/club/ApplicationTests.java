package com.mdbytes.club;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({ "com.mdbytes.club.repository" })
class ApplicationTests {}
