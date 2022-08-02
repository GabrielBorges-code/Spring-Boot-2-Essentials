package academy.devdojo.springboot2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.repository.AnimeRepository;

@Service
public class AnimeService  implements AnimeRepository {

    private static List<Anime> animes;
    static {
        animes = new ArrayList<>(List.of(new Anime(1L, "DBZ"), new Anime(2L, "Sete Pecados Capitais"), new Anime(3L, "Naruto")));
    }

    public List<Anime> listAll() {
        return animes;
    }

    public Anime findById(Long id) {
        return animes.stream()
                    .filter(anime -> anime.getId().equals(id))
                    .findFirst()
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime ID not found"));

    }

    public Anime save(Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextLong(4, 1000));
        animes.add(anime);
        return anime;

    }

    public void delete(Long id) {
        animes.remove(findById(id));
    }

    public void replace(Anime anime) {
        delete(anime.getId());
        animes.add(anime);

    }
}
