package platform.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.models.Snippet;
import platform.services.SnippetService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ApiController {

    @Autowired
    SnippetService service;


    @GetMapping("/api/code/{UUID}")
    public ResponseEntity<Snippet> getCode(@PathVariable UUID UUID) {
        try {
            Snippet snippet = service.getSnippet(UUID);
            return new ResponseEntity<>(snippet, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<Map<String, String>> newCode(@RequestBody Snippet code) {
        Snippet snippet = new Snippet(code.getCode(), LocalDateTime.now(),
                code.getViews(), code.getTime());
        snippet = service.save(snippet);
        return new ResponseEntity<>(Map.of("id", String.valueOf(snippet.getId())), HttpStatus.OK);

    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<List<Snippet>> latest() {
        return new ResponseEntity<>(service.getLatest(), HttpStatus.OK);
    }

}
