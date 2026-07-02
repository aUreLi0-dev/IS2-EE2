package pe.edu.ulima.is2.service;

import pe.edu.ulima.is2.model.VideoGame;
import pe.edu.ulima.is2.repository.VideoGameRepository;

public class SalesService {
    private final VideoGameRepository repository;

    public SalesService(VideoGameRepository repository) {
        this.repository = repository;
    }

    public Double processSale(Long videoGameId, Integer quantity) {
        VideoGame game = repository.findById(videoGameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        if (game == null) {
            throw new RuntimeException("Game not found");
        }

        if (game.getStock().equals(quantity)) {
            throw new RuntimeException("Igual: " + game.getTitle());
        }

        if (game.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock for: " + game.getTitle());
        }

        Double total = game.getPrice() * quantity;
        game.setStock(game.getStock() - quantity);
        repository.update(game);

        return total;
    }
}
