package in.shagunakarsh.controllers;

import in.shagunakarsh.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/repo")
public class DirectoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryController.class);

    @Autowired
    private FileUtils fileUtils;

    /**
     * TODO: build a UI to view a repo files
     * @return
     */
    @RequestMapping("/{repo}")
    public String showRepo(@PathVariable String repo, Model model) {
        model.addAttribute("name", repo);
        model.addAttribute("fileMap", fileUtils.getFileMap(repo));
        return "view-files";
    }

    @RequestMapping("/{repo}/files")
    public String showFiles(@PathVariable String repo, @RequestParam(name="path", required = false) String file, HttpServletRequest httpServletRequest, Model model) {
        LOGGER.info("path: {}", repo+ File.separator + file);
        model.addAttribute("name", repo);
        model.addAttribute("fileMap", fileUtils.getFileMap(repo, file));
        return "view-files";
    }


}
