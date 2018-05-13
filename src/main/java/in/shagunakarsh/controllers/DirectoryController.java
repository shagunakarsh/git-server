package in.shagunakarsh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/repo")
public class DirectoryController {

    /**
     * TODO: build a UI to view a repo files
     * @return
     */
    @RequestMapping("/")
    public ResponseEntity<String> showRepo() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
