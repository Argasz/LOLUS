package lolusApp;

import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Long> {
    Iterable<Vote> findAllByEvent(Event event);
    Long countAllByTypeAndAndEvent(String type, Event event);
}
