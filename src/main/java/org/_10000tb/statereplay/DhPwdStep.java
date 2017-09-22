package org._10000tb.statereplay;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import hudson.Extension;
import hudson.FilePath;
import hudson.slaves.WorkspaceList;
import org.jenkinsci.plugins.workflow.steps.*;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
/*
*  Return current working directory.
*
*  Example:
*  <pre>
*     node {
*         def x = dhpwd() //get current working directory
*     }
*  </pre>
* */
public class DhPwdStep extends Step {

    private boolean tmp;

    @DataBoundConstructor public DhPwdStep(){}

    public boolean isTmp() { return this.tmp; }

    @DataBoundSetter public void setTmp(boolean tmp) { this.tmp = tmp; }

    @Override
    public StepExecution start(StepContext stepContext) throws Exception {
        return new Execution(this.tmp, stepContext);
    }

    @Extension
    public static final class DescriptorImpl extends StepDescriptor{

        @Override
        public Set<? extends Class<?>> getRequiredContext() {
            return Collections.singleton(FilePath.class);
        }

        @Override
        public String getFunctionName() {
            return "dhpwd";
        }

        @Override
        public String getDisplayName() {
            return  "1000tb's determining current woring directory.";
        }

        @Override
        public String argumentsToString(@Nonnull Map<String, Object> namedArgs) {
            return null;
        }
    }
    // TODO use 1.652 use WorkspaceList.tempDir
    public static FilePath tempDir(FilePath ws){
        return ws.sibling(ws.getName() + System.getProperty(WorkspaceList.class.getName(), "@") + "tmp");
    }

    public static class Execution extends SynchronousStepExecution<String> {

        @SuppressFBWarnings(value="SE_TRANSIENT_FIELD_NOT_RESTORED", justification="Only used when starting.")
        private transient final boolean tmp;

        Execution(boolean tmp, StepContext context){
            super(context);
            this.tmp = tmp;
        }

        @Override
        protected String run() throws Exception {
            FilePath cwd = getContext().get(FilePath.class);
            return null;
        }

        private static final long serialVersionUID = 1L;
    }
}
