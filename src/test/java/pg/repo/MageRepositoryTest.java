package pg.repo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import pg.Mage;

import java.util.*;

public class MageRepositoryTest {
    private MageRepository repo = new MageRepository(Arrays.stream(new Mage[]{
            new Mage("Mage1", 12),
            new Mage("Mage2", 12),
            new Mage("Mage3", 12),
            new Mage("Mage4", 12),
            new Mage("Mage5", 12),
            new Mage("Mage6", 12)
    }).toList());

    @Test
    public void delete_nonExistingElement_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    repo.delete("Mage7");
                });
    }

    @Test
    public void find_nonExistingElement_EmptyOptional() {
        Assertions.assertEquals(Optional.empty(), repo.find("Mage7"));
    }

    @Test
    public void find_existingElement_MageOptional() {
        Assertions.assertNotEquals(Optional.empty(), repo.find("Mage1"));
    }

    @Test
    public void save_alreadyExistingElement_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    repo.save(new Mage("Mage1", 12));
                });
    }
}
