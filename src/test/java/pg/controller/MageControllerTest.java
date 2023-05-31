package pg.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pg.Mage;
import pg.repo.MageRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class MageControllerTest {

    @Test
    public void delete_existingElement_doneString() {
        MageRepository repo = mock(MageRepository.class);
        doNothing().when(repo).delete("exists");
        MageController controller = new MageController(repo);

        Assertions.assertEquals("done", controller.delete("exists"));
    }

    @Test
    public void delete_nonExistingElement_notFoundString() {
        MageRepository repo = mock(MageRepository.class);
        doThrow(IllegalArgumentException.class).when(repo).delete("nonExisting");
        MageController controller = new MageController(repo);

        Assertions.assertEquals("not found", controller.delete("nonExisting"));
    }

    @Test
    public void find_nonExistingElement_notFoundString() {
        MageRepository repo = mock(MageRepository.class);
        when(repo.find("nonExisting")).thenReturn(Optional.empty());
        MageController controller = new MageController(repo);

        Assertions.assertEquals("not found", controller.find("nonExisting"));
    }

    @Test
    public void find_existingElement_elementString() {
        MageRepository repo = mock(MageRepository.class);
        when(repo.find("existing")).thenReturn(Optional.of(new Mage("existing", 200)));
        MageController controller = new MageController(repo);

        Assertions.assertEquals(new Mage("existing", 200).toString(), controller.find("existing"));
    }

    @Test
    public void save_newElement_doneString() {
        MageRepository repo = mock(MageRepository.class);

        doNothing().when(repo).save(new Mage("new", 10));
        MageController controller = new MageController(repo);

        Assertions.assertEquals("done", controller.save("new", "10"));
    }

    @Test
    public void save_existingElement_badRequest() {
        MageRepository repo = mock(MageRepository.class);

        doThrow(IllegalArgumentException.class).when(repo).save(new Mage("existing", 404));
        MageController controller = new MageController(repo);

        Assertions.assertEquals("bad request", controller.save("existing", "404"));
    }
}
