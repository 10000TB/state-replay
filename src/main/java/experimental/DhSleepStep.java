package experimental;

import hudson.Extension;
import hudson.Util;
import hudson.model.TaskListener;
import hudson.util.ListBoxModel;
import jenkins.util.Timer;
import org.jenkinsci.plugins.workflow.steps.*;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DhSleepStep extends Step{

    public static final Logger LOGGER = Logger.getLogger(DhSleepStep.class.getName());
    private final int time;
    private TimeUnit unit = TimeUnit.SECONDS;

    @DataBoundConstructor
    public DhSleepStep(int time) { this.time = time; }

    @DataBoundSetter
    public void setUnit(TimeUnit unit) { this.unit = unit; }

    public int getTime() { return this.time; }

    public TimeUnit getUnit() { return  this.unit; }

    @Override
    public StepExecution start(StepContext stepContext) throws Exception {
        return new Execution(this,stepContext);
    }

    public static final class Execution extends AbstractStepExecutionImpl {

        private static final long serialVersionUID = 1L;

        private transient final DhSleepStep step;
        private long end;
        private transient volatile ScheduledFuture<?> task;

        Execution(DhSleepStep step, StepContext context) {
            super(context);
            this.step = step;
        }

        @Override
        public boolean start() throws Exception {
            long now = System.currentTimeMillis();
            this.end = now + step.unit.toMillis(step.time);
            setUpTimer(now);
            return false;
        }

        @Override
        public void stop(@Nonnull Throwable throwable) throws Exception {
            if (task != null) {
                task.cancel(false);
            }
            getContext().onFailure(throwable);
        }

        @Override
        public void onResume(){
            setUpTimer(System.currentTimeMillis());
        }

        @Override
        public String getStatus() {
            long now = System.currentTimeMillis();
            if (end > now) {
                return "DH:sleeping for another " + Util.getTimeSpanString(end - now);
            } else {
                return "DH:should have stopped sleeping " + Util.getPastTimeString(end-now);
            }
        }

        private void setUpTimer(long now){
            TaskListener listener = null;
            try {
                listener = getContext().get(TaskListener.class);
            } catch (Exception e){
                LOGGER.log(Level.WARNING, null, e);
            }
            if ( end > now ){
                listener.getLogger().println("Sleeping for "+ Util.getTimeSpanString(end - now));
                task = Timer.get().schedule(new Runnable() {
                    @Override
                    public void run() {
                        getContext().onSuccess(null);
                    }
                }, end-now, TimeUnit.MILLISECONDS);
            } else {
                listener.getLogger().println("From DH: no need to sleep any longer");
                getContext().onSuccess(null);
            }
        }
    }

    @Extension public static final class DescriptorImpl extends StepDescriptor {

        @Override public Set<? extends Class<?>> getRequiredContext() {
            return Collections.singleton(TaskListener.class);
        }

        public ListBoxModel doFillUnitItems() {
            ListBoxModel r = new ListBoxModel();
            for (TimeUnit unit : TimeUnit.values()){
                r.add(unit.name());
            }
            return r;
        }

        @Override public String getDisplayName() {
            return "dhsleep";
        }

        @Override public String getFunctionName() {
            return "dhsleep";
        }
    }
}