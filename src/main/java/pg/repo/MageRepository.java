package pg.repo;

import lombok.AllArgsConstructor;
import pg.Mage;

import javax.lang.model.type.NullType;
import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class MageRepository {
    private Collection<Mage> collection;

    public Optional<Mage> find(String name) {
        for (Mage mage : collection)
            if (mage.getName().equals(name))
                return Optional.of(mage);
        return Optional.empty();
    }
    public void delete(String name) throws IllegalArgumentException {
        Optional<Mage> mage = find(name);
        if (mage.isEmpty())
                throw new IllegalArgumentException();
        collection.remove(mage.get());
    }
    public void save(Mage mage) throws IllegalArgumentException {
        Optional<Mage> m = find(mage.getName());
        if (m.isPresent())
            throw new IllegalArgumentException();
        collection.add(mage);
    }
}
