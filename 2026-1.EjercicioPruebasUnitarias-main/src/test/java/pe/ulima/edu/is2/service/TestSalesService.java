package pe.ulima.edu.is2.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.ulima.is2.model.VideoGame;
import pe.edu.ulima.is2.repository.VideoGameRepository;
import pe.edu.ulima.is2.service.SalesService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TestSalesService {
    private SalesService salesService;


    @Mock
    private VideoGameRepository videoGameRepository;

    @BeforeEach
    public void setup(){    
        //Inicializacion
        salesService = new SalesService(videoGameRepository);
    }

    @Test
    public void testVideojuegoIdNoExiste(){
        when(videoGameRepository.findById(123l)).thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> salesService.processSale(123l,1)
        );
    }


    @Test
    public void testCantIgualStock(){
        VideoGame videoGame = new VideoGame();
        videoGame.setStock(5);
        when(videoGameRepository.findById(12l))
                .thenReturn(Optional.of(videoGame));

        assertThrows(
                RuntimeException.class,
                () -> salesService.processSale(12l,1)
        );

    }

    @Test
    public void testVentaExitosa(){
        VideoGame videoGame = new VideoGame();
        videoGame.setPrice(10.0);
        videoGame.setStock(5);
        when(videoGameRepository.findById(12l))
                .thenReturn(Optional.of(videoGame));

        double total = salesService.processSale(12l,2);
        assertEquals(20,total);

    }

    @Test
    public void testCambioStock(){
        VideoGame videoGame = new VideoGame();
        videoGame.setPrice(10.0);
        videoGame.setStock(5);
        when(videoGameRepository.findById(12l))
                .thenReturn(Optional.of(videoGame));

        double total = salesService.processSale(12l,2);
        assertEquals(20,total);
        assertEquals(3,videoGame.getStock());

    }


    @AfterEach
    public void tearDown(){
        //Fin
        System.out.println("Chau, terminé bn");
    }
}
