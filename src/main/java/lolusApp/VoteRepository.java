package lolusApp;

import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Long> {
    Iterable<Vote> findAllByEvent(Event event);
    Long countAllByTypeAndEvent(String type, Event event);
    Vote findVoteByUserIdAndEvent(String userId, Event event);
}
