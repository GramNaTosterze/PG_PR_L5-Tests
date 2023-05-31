package pg;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Mage {
    @Getter
    private String name;
    private int level;

    @Override
    public String toString() {
        return String.format("%s, %d", name, level);
    }
}
