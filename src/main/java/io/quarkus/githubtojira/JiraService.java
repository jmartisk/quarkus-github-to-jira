package io.quarkus.githubtojira;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import io.quarkus.githubtojira.model.JiraInfo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class JiraService {

    private JiraRestClient client;

    @ConfigProperty(name = "jira.server")
    String jiraServer;

    @ConfigProperty(name = "jira.token")
    String jiraToken;

    @ConfigProperty(name = "jira.project")
    String jiraProject;

    @ConfigProperty(name = "timeout")
    Duration timeout;

    @PostConstruct
    public void init() throws URISyntaxException {
        client = new AsynchronousJiraRestClientFactory().create(new URI(jiraServer),
                builder -> builder.setHeader("Authorization", "Bearer " + jiraToken));
    }

    public JiraInfo findExistingJiraForPullRequest(String prUrl, String fixVersion) throws Exception {
        SearchResult searchResult = client.getSearchClient().searchJql("project = " + jiraProject + " " +
                "and fixVersion in (\"" + fixVersion + "\") " +
                "and \"Git Pull Request\" ~ \"" + prUrl + "\"").get(timeout.toMillis(), TimeUnit.MILLISECONDS);
        if (searchResult.getTotal() > 0) {
            Issue issue = searchResult.getIssues().iterator().next();
            JiraInfo jiraInfo = new JiraInfo();
            jiraInfo.setKey(issue.getKey());
            jiraInfo.setUrl(jiraServer + "/browse/" + issue.getKey());
            return jiraInfo;
        } else {
            return null;
        }
    }

    // Convert a Quarkus version to a value of the fixVersion field in Jira
    public String fixVersionToJiraVersion(String fixVersion) {
        // hopefully this will be enough for the time being
        return fixVersion + ".GA";
    }

    @PreDestroy
    public void cleanup() {
        try {
            client.close();
        } catch (Exception e) {
            // Ignore
        }
    }

}
