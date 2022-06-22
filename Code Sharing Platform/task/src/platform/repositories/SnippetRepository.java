package platform.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.models.Snippet;

import java.util.List;
import java.util.UUID;

@Repository
public interface SnippetRepository extends CrudRepository<Snippet, UUID> {

    @Query("SELECT u FROM snippets u where time < 1 AND views < 1")
    List<Snippet> findAllEnabled();
}
