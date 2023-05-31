package pg.controller;

import lombok.AllArgsConstructor;
import pg.Mage;
import pg.repo.MageRepository;

import java.util.Optional;

@AllArgsConstructor
public class MageController {
    private MageRepository repo;
    public String find(String name) {
        Optional<Mage> mage = repo.find(name);
        if (mage.isEmpty())
            return "not found";
        else
            return mage.get().toString();
    }
    public String delete(String name) {
        try {
            repo.delete(name);
        } catch (IllegalArgumentException e) {
            return "not found";
        }
        return "done";
    }
    public String save(String name, String level) {
        int levelInt;
        try {
            levelInt = Integer.parseInt(level);
        } catch(NumberFormatException e) {
            return "bad request";
        }

        Mage mage = new Mage(name, levelInt);
        try {
            repo.save(mage);
        } catch (IllegalArgumentException e) {
            return "bad request";
        }
        return "done";
    }
}
