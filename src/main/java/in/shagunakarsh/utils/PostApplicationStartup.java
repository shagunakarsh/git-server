package in.shagunakarsh.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

public class PostApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostApplicationStartup.class);

    private GitUtils gitUtils;
    private String repoName;

    public PostApplicationStartup(String repoName, GitUtils gitUtils) {
        this.gitUtils = gitUtils;
        this.repoName = repoName;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        LOGGER.info("application ready!");
        try {
            gitUtils.init(repoName);
        } catch (Exception e) {
            LOGGER.error("Error initializing git repo", e);
        }
    }

    public GitUtils getGitUtils() {
        return gitUtils;
    }

    public void setGitUtils(GitUtils gitUtils) {
        this.gitUtils = gitUtils;
    }
}
