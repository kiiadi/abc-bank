package com.abc;


import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StringUtilsTest {

    @Test
    public void shouldPluralizeAccounts() throws Exception {
        assertThat(StringUtils.pluralize("account", 1), is("account"));
        assertThat(StringUtils.pluralize("account", 2), is("accounts"));
    }
}
