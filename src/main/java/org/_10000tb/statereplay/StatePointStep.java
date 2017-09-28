package org._10000tb.statereplay;

import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.model.TaskListener;
import jenkins.util.SystemProperties;
import org.jenkinsci.plugins.workflow.FilePathUtils;
import org.jenkinsci.plugins.workflow.steps.*;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Set;

public class StatePointStep extends Step{
    private final String stateMessage;

    /*
    *  Have a statepoint directory store in JENKINS_HOME directory.
    * */
    @DataBoundConstructor
    public StatePointStep(String stateMessage) { this.stateMessage = stateMessage; }

    public String getStateMessage() { return this.stateMessage; }

    @Override public StepExecution start(StepContext stepContext) throws Exception {
        Execution execution = new Execution(this.stateMessage, stepContext);
        return execution;
    }

    public static final class Execution extends SynchronousStepExecution<Void> {
        private transient final String stateMessage;
        private String jenkinsHome;
        private String currentJobName;
        private String currentJobStatesHome;
        private String currentBuildNumber;
        private String currentBuildHome;

        protected Execution(String stateMessage, StepContext context) throws IOException, InterruptedException {
            super(context); this.stateMessage = stateMessage;
            this.jenkinsHome = SystemProperties.getString("JENKINS_HOME");
            this.currentJobName = context.get(EnvVars.class).get("JOB_NAME");
            //this.currentJobName = env.get("JOB_NAME");
            this.currentBuildNumber = context.get(EnvVars.class).get("BUILD_NUMBER");

            this.currentJobStatesHome = this.jenkinsHome + ProductionConfig.JENKINS_HOME_TO_JOB_CONNECTION_PATH + this.currentJobName + "/states";
            this.currentBuildHome = this.currentJobStatesHome + "/" + this.currentBuildNumber;
            // Create a states directory is not exist
            maybeCreateDirectory(this.currentJobStatesHome);
            // Create a build root for current job is not exist.
            maybeCreateDirectory(this.currentBuildHome);
        }

        @Override protected Void run() throws Exception {
            getContext().get(TaskListener.class).getLogger().println("statepoint check: "+this.stateMessage);

            getContext().get(TaskListener.class).getLogger().println("current stack trace:\n"+Thread.currentThread().getStackTrace());

            return null;
        }

        private static final long serialVersionUID = 1L;
    }

    @Extension
    public static class DescriptorImpl extends StepDescriptor {

        @Override public Set<? extends Class<?>> getRequiredContext() { return Collections.singleton(TaskListener.class); }

        @Override public String getFunctionName() { return "statepoint"; }

        @Override
        public String getDisplayName() { return "A simple tool to help you replay from last failure point in workflow pipeline."; }

    }

    private static void maybeCreateDirectory(String path){
        File file = new File(path);
        try {
            if (!file.exists()){
                file.mkdir();
            }
        } catch (Exception e){ System.err.println("Failed to a directory: "+e.getMessage());}
    }
}
