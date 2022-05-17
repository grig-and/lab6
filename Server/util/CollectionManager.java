package util;
import content.Movie;
import content.MovieGenre;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Collection manager class
 */
public class CollectionManager {
    private TreeMap<String, Movie> movies;
    private FileManager file;
    private LocalDate date;

    /**
     * Constructor
     *
     * @param fileManager FileManager
     */
    public CollectionManager(FileManager fileManager) {
        this.file = fileManager;
        this.movies = file.read();
        this.date = LocalDate.now();
    }

    /**
     * Clear collection
     */
    public void clear() {
        movies.clear();
    }

    @Override
    public String toString() {
        return movies
                .entrySet()
                .stream()
                .map((e) -> e.getKey() + ":\n" + e.getValue().toString() + "\n")
                .sorted(Comparator.comparingInt(String::length))
                .reduce((a, b) -> a + b)
                .orElse("Коллекция пуста\n")
                .trim();
    }

    /**
     * Filter movie greater then genre
     *
     * @param genre MovieGenre for filter
     * @return filtered movies
     */
    public String filterGreaterThanGenre(MovieGenre genre) {
        return movies
                .values()
                .stream()
                .filter((v) -> v.getGenre().compareTo(genre) > 0)
                .map((s) -> s + "\n")
                .sorted(Comparator.comparingInt(String::length))
                .reduce((a, b) -> a + b)
                .orElse("Нет таких элементов коллекции");
    }

    /**
     * @return info about collection
     */
    public String getInfo() {
        return "type: " + movies.getClass() + "\n" + "date: " + date + "\nsize: " + movies.size();
    }

    /**
     * Save to CSV
     */
    public void save() {
        file.save();
    }

    /**
     * @param key key
     * @return is collection contains key
     */
    public boolean contains(String key) {
        return movies.containsKey(key);
    }

    public boolean containsID(Long id) {
        return movies.values().stream().anyMatch((v) -> v.getId() == id);
    }

    /**
     * Insert movie by key
     *
     * @param key   key
     * @param movie movie
     */
    public void insert(String key, Movie movie) {
        movies.put(key, movie);
    }

    /**
     * @return movies in ascending by OscarsCount order
     */
    public String getAscending() {
        return movies.entrySet().stream()
                .sorted((e1, e2) -> {
                    Movie v1 = e1.getValue();
                    Movie v2 = e2.getValue();
                    return v1.getOscarsCount().compareTo(v2.getOscarsCount());
                })
                .map((s) -> s + "\n")
                .sorted(Comparator.comparingInt(String::length))
                .reduce((a, b) -> a + b)
                .orElse("Коллекция пуста\n");
    }

    /**
     * Remove all elements with OC > entered
     *
     * @param movie movie for comparation
     * @return n of removed elements
     */
    public int removeGreater(Movie movie) {
        final int[] i = {0};
        movies = movies.entrySet().stream()
                .filter((e) -> {
                    i[0]++;
                    return e.getValue().getOscarsCount() <= movie.getOscarsCount();
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (l, r) -> l, TreeMap::new));
        return i[0];
    }

    /**
     * Remove all with key > entered key
     *
     * @param key key
     * @return n of removed elements
     */
    public int removeGreaterKey(String key) {
        int i = 0;
        movies = movies.entrySet().stream()
                .filter((e) -> e.getKey().compareTo(key) <= 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (l, r) -> l, TreeMap::new));
        return i;
    }

    /**
     * Remove by key
     *
     * @param key key
     */
    public void removeKey(String key) {
        movies.remove(key);
    }

    /**
     * @return sum of oscars
     */
    public int getSumOscars() {
        int n = 0;
        for (Movie movie : movies.values()) {
            n += movie.getOscarsCount();
        }
        return n;
    }

    /**
     * Update existing element
     *
     * @param id    id
     * @param movie movie
     */
    public void update(Long id, Movie movie) {
        movies = movies.entrySet().stream()
                .peek((e) -> {
                    if (e.getValue().getId() == id) {
                        e.setValue(movie);
                    }
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (l, r) -> l, TreeMap::new));
    }

    /**
     * Replace if OC greater
     *
     * @param key   key
     * @param movie movie
     */
    public String replaceIfGreater(String key, Movie movie) {
        movies = movies.entrySet().stream()
                .peek((e) -> {
                    if ((e.getKey().equals(key)) && (e.getValue().getOscarsCount() < movie.getOscarsCount())) {
                        e.setValue(movie);
                    }
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (l, r) -> l, TreeMap::new));
        return "";
    }

}
