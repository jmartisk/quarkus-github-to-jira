package io.quarkus.githubtojira;

import io.quarkus.githubtojira.model.JiraInfo;
import io.quarkus.githubtojira.model.ProjectInfo;
import io.quarkus.githubtojira.model.PullRequestInfo;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.regex.Pattern;

@Path("/")
public class GithubToJiraResource {

    @Inject
    GitHubService gitHubService;

    // used to filter the project names to only get projects related to backports
    private static Pattern projectNamePattern = Pattern.compile("Backports.+");
    @Inject
    JiraService jiraService;

    @CheckedTemplate
    public static class Templates {

        public static native TemplateInstance index(List<ProjectInfo> projects);

        public static native TemplateInstance importing(Integer projectNumber,
                                                        String fixVersion,
                                                        List<PullRequestInfo> pullRequests);

    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Blocking
    public TemplateInstance index() throws Exception {
        return Templates.index(gitHubService.getBackportProjectsMap(projectNamePattern));
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Blocking
    @Path("/importing/{projectNumber}/{fixVersion}")
    public TemplateInstance importing(Integer projectNumber, String fixVersion) throws Exception {
        List<PullRequestInfo> pullRequests = gitHubService.getPullRequestsBackportedToVersion(projectNumber, fixVersion);
        for(PullRequestInfo pr : pullRequests) {
            JiraInfo existingJira = jiraService.findExistingJiraForPullRequest(pr.getUrl(),
                    jiraService.fixVersionToJiraVersion(fixVersion));
            pr.setExistingJira(existingJira);
        }
        return Templates.importing(projectNumber, fixVersion, pullRequests);
    }

}
