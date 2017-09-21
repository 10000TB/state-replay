package org._10000tb.statereplay;

import hudson.model.FreeStyleBuild;
import hudson.model.FreeStyleProject;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.io.IOException;

import static org.junit.Assert.*;

public class SleepBuilderTest {
    @Rule
    public JenkinsRule r = new JenkinsRule();

    @Test
    public void checkSleepOutput() throws Exception{
        // Given
        long time = 12034;
        SleepBuilder builder = new SleepBuilder(time);
        FreeStyleProject p = r.createFreeStyleProject();
        p.getBuildersList().add(builder);

        // When
        FreeStyleBuild freeStyleBuild = p.scheduleBuild2(0).get();
        assertTrue(freeStyleBuild.getLog().contains("Sleeping for \"+time+\" ms"));
    }
}