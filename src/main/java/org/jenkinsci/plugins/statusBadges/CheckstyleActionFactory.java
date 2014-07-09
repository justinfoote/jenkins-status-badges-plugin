package org.jenkinsci.plugins.statusbadges;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.model.Action;
import hudson.model.BallColor;
import hudson.model.TransientProjectActionFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@Extension
public class CheckstyleActionFactory extends TransientProjectActionFactory {

    private final ImageResolver iconResolver;

    public CheckstyleActionFactory() {
        iconResolver = new ImageResolver();
    }

    @Override
    public Collection<? extends Action> createFor(AbstractProject target) {
        return Collections.singleton(new CheckstyleAction(this,target));
    }

    public StatusImage getCheckstyleImage(int errors, String style) throws IOException {
        return iconResolver.getCheckstyleImage(errors, style);
    }

}
