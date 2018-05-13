package in.shagunakarsh.controllers;

import in.shagunakarsh.utils.FileUtils;
import in.shagunakarsh.utils.GitUtils;
import io.swagger.annotations.ApiOperation;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/repo")
public class DirectoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryController.class);

    @Autowired
    private GitUtils gitUtils;

    @Autowired
    private FileUtils fileUtils;

    @ApiOperation(value = "GET Api to View Git Repository files")
    @RequestMapping(value = "/{repo}", method = RequestMethod.GET)
    public String showRepo(@PathVariable String repo, Model model) {
        model.addAttribute("name", repo);
        model.addAttribute("fileMap", fileUtils.getFileMap(repo));
        return "view-files";
    }

    @ApiOperation(value = "POST Api to create new Git Repository")
    @RequestMapping(value = "/{repo}", method = RequestMethod.POST)
    public String createRepo(@PathVariable String repo, Model model) throws IOException, GitAPIException {
        gitUtils.createNewRepository(gitUtils.getRootPath() + repo);
        model.addAttribute("name", repo);
        model.addAttribute("fileMap", fileUtils.getFileMap(repo));
        return "view-files";
    }


    @ApiOperation(value = "GET Api to view files within a given repo dir/sub-directories")
    @RequestMapping(value = "/{repo}/files", method = RequestMethod.GET)
    public String showRepoFiles(@PathVariable String repo, @RequestParam(name="path", required = false) String file, HttpServletRequest httpServletRequest, Model model) {
        LOGGER.info("path: {}", repo+ File.separator + file);
        model.addAttribute("name", repo);
        model.addAttribute("fileMap", fileUtils.getFileMap(repo, file));
        return "view-files";
    }


}
