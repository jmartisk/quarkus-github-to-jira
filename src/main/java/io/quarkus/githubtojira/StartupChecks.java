package io.quarkus.githubtojira;

import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public class StartupChecks {

    @ConfigProperty(name = "github.token")
    String githubToken;

    @ConfigProperty(name = "jira.token")
    String jiraToken;

    public void checkConfiguration(@Observes Startup startup) {
        if (githubToken == null || githubToken.isEmpty()) {
            throw new IllegalStateException("GitHub token is not configured. Please set the 'github.token' configuration property.");
        }
        if (jiraToken == null || jiraToken.isEmpty()) {
            throw new IllegalStateException("Jira token is not configured. Please set the 'jira.token' configuration property.");
        }
    }
}
