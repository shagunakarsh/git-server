package in.shagunakarsh.config;

import in.shagunakarsh.utils.FileUtils;
import in.shagunakarsh.utils.PostApplicationStartup;
import in.shagunakarsh.utils.GitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "classpath:git-server.properties")
public class BeanConfig {

    public BeanConfig(Environment environment) {
        this.environment = environment;
    }

    @Autowired
    private Environment environment;

    @Bean("applicationStartup")
    public PostApplicationStartup applicationStartup() {
        PostApplicationStartup postApplicationStartup = new PostApplicationStartup(
                environment.getProperty("git.repo.name"), gitUtils());
        return postApplicationStartup;
    }

    @Bean("gitUtils")
    public GitUtils gitUtils() {

        GitUtils gitUtils = new GitUtils(environment.getProperty("git.server.root"));
        gitUtils.setPort(environment.getProperty("git.server.port", Integer.class).intValue());
        return gitUtils;
    }

    @Bean("fileUtils")
    public FileUtils fileUtils() {
        return new FileUtils(environment.getProperty("git.server.root"));
    }
}
