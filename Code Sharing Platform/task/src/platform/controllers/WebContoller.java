package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.models.CustomNotFoundException;
import platform.models.Snippet;
import platform.services.SnippetService;

import java.util.*;

@Controller
public class WebContoller {

    @Autowired
    SnippetService service;

    @GetMapping("/code/{UUID}")
    public String getCode(@PathVariable UUID UUID, Model model) throws CustomNotFoundException {
        try {
            Snippet snippet = service.getSnippet(UUID);
            model.addAttribute("snippet", snippet);
            return "index";
        }
        catch (Exception ex) {
            throw new CustomNotFoundException("error");
        }
    }

    @GetMapping("/code/new")
    public String addNewCode(Model model)  {
       return "new";
    }

    @GetMapping("/code/latest")
    public String getLatest(Model model)  {
        model.addAttribute("codes", service.getLatest());
        return "latest";
    }

}
