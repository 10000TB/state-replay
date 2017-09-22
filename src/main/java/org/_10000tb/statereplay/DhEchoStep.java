package org._10000tb.statereplay;

import hudson.Extension;
import hudson.model.TaskListener;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.jenkinsci.plugins.workflow.steps.*;
import org.kohsuke.stapler.DataBoundConstructor;
import java.util.Collections;
import java.util.Set;

/*
*   `statepoint` statement support
* */
public class DhEchoStep extends Step{
    public final String message;

    @DataBoundConstructor
    public DhEchoStep(String message) { this.message = message; }

    public String getMessage() { return message; }

    @Override
    public StepExecution start(StepContext stepContext) throws Exception {
        return new Execution(this.message, stepContext);
    }
    
    @Extension
    public static class DescriptorImpl extends StepDescriptor{

        @Override
        public String getFunctionName() {
            return "dhecho";
        }

        @Override
        public String getDisplayName() {
            return "Print message from DHecho";
        }

        @Override
        public Set<? extends Class<?>> getRequiredContext() {
            return Collections.singleton(TaskListener.class);
        }
    }

    public static class Execution extends AbstractSynchronousStepExecution<Void> {

        @SuppressFBWarnings(value="SE_TRANSIENT_FIELD_NOT_RESTORED", justification="Only used when starting.")
        private transient final String message;

        Execution(String message, StepContext context){
            super(context);
            this.message = message;
        }

        @Override
        protected Void run() throws Exception {
            getContext().get(TaskListener.class).getLogger().println("10000tb's Echo: "+this.message);
            return null;
        }

        private static final long serialVersionUID = 1L;
    }
}
