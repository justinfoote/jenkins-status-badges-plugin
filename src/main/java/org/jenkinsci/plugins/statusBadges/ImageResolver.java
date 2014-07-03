package org.jenkinsci.plugins.statusbadges;

import hudson.model.BallColor;
import java.io.IOException;

public class ImageResolver {

    public StatusImage getBuildImage(BallColor ballColor) throws IOException {
        String subject = "build";
        String status = "unknown";
        String color = "lightgrey";

        if (ballColor.isAnimated()) {
            status = "running";
            color = "blue";
        } else {
            switch (ballColor) {
                case RED:
                case ABORTED:
                    status = "failing";
                    color = "red";
                    break;
                case YELLOW:
                    status = "unstable";
                    color = "yellow";
                    break;
                case BLUE:
                    status = "passing";
                    color = "brightgreen";
                    break;
            }
        }

        return new StatusImage(subject, status, color);
    }

    public StatusImage getCheckstyleImage(int errors) throws IOException {
        String subject = "checkstyle";
        String status = "unknown";
        String color = "lightgrey";

        if (errors == 0) {
            status = "no error";
            color = "brightgreen";
        } else {
            status = errors + " error" + (errors > 1 ? "s" : "");
            color = "red";
        }

        return new StatusImage(subject, status, color);
    }

    public StatusImage getCoverageImage(int coverage) throws IOException {
        String subject = "coverage";
        String status = Integer.toString(coverage) + "%";
        String color = "lightgrey";

        if (coverage > 90) {
            color = "brightgreen";
        } else if (coverage > 80) {
            color = "green";
        } else if (coverage > 70) {
            color = "yellowgreen";
        } else if (coverage > 60) {
            color = "yellow";
        } else if (coverage > 50) {
            color = "orange";
        } else {
            color = "red";
        }

        return new StatusImage(subject, status, color);
    }

}