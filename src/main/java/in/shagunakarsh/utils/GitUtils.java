package in.shagunakarsh.utils;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.http.server.GitServlet;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class GitUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitUtils.class);

    private int port;
    private String rootPath;

    public GitUtils(String rootPath) {
        this.rootPath = rootPath;
        port = 8080;
    }

    public void init(String repoName) throws Exception {
        LOGGER.info("rootPath: {}", rootPath);
        File file = new File(rootPath);
        if(file.exists()) {
            LOGGER.info("rootPath exists: {}", rootPath);
        } else if(!file.mkdirs()) {
            throw new RuntimeException("Error creating dir: " + rootPath);
        }
        Repository repository = this.createNewRepository(rootPath + repoName);
        this.populateRepository(repository);
        GitServlet gitServlet = this.createGitServlet(repository);
        Server server = this.configureAndStartHttpServer(gitServlet);
        server.join();
        LOGGER.info("Git server started!!");

    }

    public GitServlet createGitServlet(Repository repository) {
        GitServlet gs = new GitServlet();
        gs.setRepositoryResolver((req, name) -> {
            repository.incrementOpen();
            return repository;
        });

        return gs;
    }

    public Server configureAndStartHttpServer(GitServlet gs) throws Exception {
        Server server = new Server(port);

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        ServletHolder holder = new ServletHolder(gs);

        handler.addServletWithMapping(holder, "/*");

        server.start();
        return server;
    }

    public Repository createNewRepository(String repoPath) throws IOException {

        LOGGER.info("repoPath: {}", repoPath);
        // prepare a new folder
        File localPath = new File(repoPath);

        Repository repository;

        if(!localPath.mkdirs()) {
            LOGGER.info("repository {} already exists!!", repoPath);
            //use existing
            repository = new FileRepository(new File(localPath, ".git"));
        } else {
            // create the repository
            repository = FileRepositoryBuilder.create(new File(localPath, ".git"));
            repository.create();
        }

        return repository;
    }

    public void populateRepository(Repository repository) throws IOException, GitAPIException {
        try (Git git = new Git(repository)) {
            File myfile = new File(repository.getDirectory().getParent(), "README.md");
            if(!myfile.exists() && !myfile.createNewFile()) {
                throw new IOException("Could not create file " + myfile);
            }

            git.add().addFilepattern("README.md").call();

            System.out.println("Added file " + myfile + " to repository at " + repository.getDirectory());

            git.commit().setMessage("Test-Checkin").call();
        }
    }

    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
}
