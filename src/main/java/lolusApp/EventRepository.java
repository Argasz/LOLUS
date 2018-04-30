package lolusApp;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
    Iterable<Event> findAllByLatBetweenAndLngBetween(String latStart, String latEnd, String lngStart, String lngEnd);
    Iterable<Event> findAllByTimeBetween(String timeStart, String timeEnd);
    Iterable<Event> findAllByTimeBetweenAndLatBetweenAndLngBetween(String timeStart, String timeEnd, String latStart, String latEnd, String lngStart, String lngEnd);
}
