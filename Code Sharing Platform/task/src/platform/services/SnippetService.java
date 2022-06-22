package platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.models.Snippet;
import platform.repositories.SnippetRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SnippetService {

    private final SnippetRepository repository;

    @Autowired
    public SnippetService(SnippetRepository repository) {
        this.repository = repository;
    }

    public List<Snippet> getLatest() {
        List<Snippet> list = repository.findAllEnabled();
        return list.stream().sorted(
                Comparator.reverseOrder()).limit(10).collect(Collectors.toCollection(ArrayList::new));
    }

    public Snippet getSnippet(UUID id) throws RuntimeException{
        Optional<Snippet> snp = repository.findById(id);
        if (snp.isEmpty()) {
            throw new RuntimeException();
        }
        Snippet snippet = snp.get();
        if(snippet.getViews() > 0 && (snippet.getViews() <= snippet.getViewsCommit())) {
            throw new RuntimeException();
        }
        if(snippet.getTime() > 0) {
            LocalDateTime dt = snippet.getDate().plusSeconds(snippet.getTime());
            if (dt.isBefore(LocalDateTime.now())) {
                throw new RuntimeException();
            }
        }
        snippet.incrementViews();
        repository.save(snippet);
        if(snippet.getViews() > 0) {
            snippet.setViews(snippet.getViews() - snippet.getViewsCommit());
            snippet.setViewsRestrict(true);
        }
        if(snippet.getTime() > 0) {
            snippet.setTime(snippet.getTime() - (snippet.getDate().until(LocalDateTime.now(), ChronoUnit.SECONDS)));
            snippet.setTimeRestrict(true);
        }
        return snippet;
    }

    public Snippet save(Snippet snippet) {
        return repository.save(snippet);
    }

}
