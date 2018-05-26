package lolusApp;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface EventRepository extends CrudRepository<Event, Long> {
    Iterable<Event> findAllByLatBetweenAndLngBetween(String latStart, String latEnd, String lngStart, String lngEnd);
    Iterable<Event> findAllByTimeBetween(Date timeStart, Date timeEnd);
    Iterable<Event> findAllByTimeBetweenAndLatBetweenAndLngBetween(Date timeStart, Date timeEnd, String latStart, String latEnd, String lngStart, String lngEnd);
    Event findEventByTimeAndLatAndLng(Date time, String lat, String lng);
}
