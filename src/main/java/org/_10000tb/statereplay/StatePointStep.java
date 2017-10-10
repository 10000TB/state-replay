package org._10000tb.statereplay;

import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.model.TaskListener;
import jenkins.util.SystemProperties;
import org.jenkinsci.plugins.workflow.FilePathUtils;
import org.jenkinsci.plugins.workflow.steps.*;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Set;

 /**  statepoint stateMessage:"<State message>",
  *              isVolatile:<VOLATILE: boolean>,
  *              remoteOperation:<REMOTEOPERATION: boolean>,
  *              retry: <NUMBEROFRETRY: INT>
  */
public class StatePointStep extends Step{
     /*
     * A message indicating what has been done since last statepoint.
     * */
     private final String stateMessage;
     /*
     * A boolean indicating if the execution step(s) since last statepoint volatile or not.
     * */
     private boolean volatileOperation;
     /*
     * A boolean indicating if the execution step(s) since last statepoint is pure remote operation or not.
     * */
     private boolean remoteOperation;
     /*
     * Number of retry that shall be attempted if there is an error occurred since last statepoint.
     *
     * Note: Use this when you are sure the execution steps in that scope are error prone, and highly likely
     *       need retries.
     * */
     private int retry;

    /*
    *  Have a statepoint directory store in JENKINS_HOME directory.
    * */
    @DataBoundConstructor
    public StatePointStep(String stateMessage) { this.stateMessage = stateMessage; }
    public String getStateMessage() { return this.stateMessage; }
    @DataBoundSetter
    public void setVolatileOperation(boolean volatileOperation) {
         this.volatileOperation = volatileOperation;
    }
    @DataBoundSetter
    public void setRemoteOperation(boolean remoteOperation) {
         this.remoteOperation = remoteOperation;
    }
    @DataBoundSetter
    public void setRetry(int retry){
        this.retry = retry;
    }
    public boolean getVolatileOperation() { return this.volatileOperation; }
    public boolean getRemoteOperation () { return this.remoteOperation; }
    public int getRetry() { return this.retry; }

    @Override public StepExecution start(StepContext stepContext) throws Exception {
        Execution execution = new Execution(this, stepContext);
        return execution;
    }

    public static final class Execution extends SynchronousStepExecution<Void> {
        private transient final StatePointStep step;
        private String jenkinsHome;
        private String currentJobName;
        private String currentJobStatesHome;
        private String currentBuildNumber;
        private String currentBuildHome;

        protected Execution(StatePointStep step, StepContext context) throws IOException, InterruptedException {
            super(context);
            this.step = step;
            this.jenkinsHome = SystemProperties.getString("JENKINS_HOME");
            this.currentJobName = context.get(EnvVars.class).get("JOB_NAME");
            this.currentBuildNumber = context.get(EnvVars.class).get("BUILD_NUMBER");

            this.currentJobStatesHome = this.jenkinsHome + ProductionConfig.JENKINS_HOME_TO_JOB_CONNECTION_PATH + this.currentJobName + "/states";
            this.currentBuildHome = this.currentJobStatesHome + "/" + this.currentBuildNumber;
            // Create a states directory is not exist
            maybeCreateDirectory(this.currentJobStatesHome);
            // Create a build root for current job is not exist.
            maybeCreateDirectory(this.currentBuildHome);
        }

        @Override protected Void run() throws Exception {
            getContext().get(TaskListener.class).getLogger().println("statepoint check: "+this.step.getStateMessage());
            getContext().get(TaskListener.class).getLogger().println("statepoint isVolatile: "+this.step.getVolatileOperation());
            getContext().get(TaskListener.class).getLogger().println("statepoint isRemoteOperation: "+this.step.getRemoteOperation());
            getContext().get(TaskListener.class).getLogger().println("statepoint getRetry: "+this.step.getRetry());

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
