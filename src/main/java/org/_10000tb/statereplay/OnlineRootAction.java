package org._10000tb.statereplay;

import hudson.Extension;
import hudson.model.RootAction;

@Extension
public class OnlineRootAction implements RootAction{


    @Override
    public String getIconFileName() {
        return "clipboard.png";
    }

    @Override
    public String getDisplayName() {
        return "David Hu Home Page";
    }

    @Override
    public String getUrlName() {
        return "http://10000tb.org";
    }
}
