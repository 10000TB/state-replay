package experimental;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.FreeStyleProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.FormValidation;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import java.io.IOException;

public class SleepBuilder extends Builder{
    private long time;

    @DataBoundConstructor
    public SleepBuilder(long time) {
        this.time = time;
    }

    public  long getTime(){
        return  this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {
        listener.getLogger().println("Sleeping for \"+time+\" ms");

        Thread.sleep(time);

        return true;
    }

    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return aClass == FreeStyleProject.class;
        }

        @Override
        public String getDisplayName() {
            return org._10000tb.statereplay.Messages.SleepBuilder_DisplayName();
        }

        public FormValidation doCheckTime(@QueryParameter String time) {
            try {
                if (Long.valueOf(time)<0) {
                    return FormValidation.error("Pls enter a positive number, :)");
                }
                return FormValidation.ok();
            }catch (NumberFormatException e){
                return FormValidation.error("Pls enter a number");
            }
        }
    }
}