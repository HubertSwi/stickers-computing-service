package pl.com.sticker.scs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.sticker.scs.exceptions.SaveStickerException;
import pl.com.sticker.scs.model.Sticker;
import pl.com.sticker.scs.model.User;
import pl.com.sticker.scs.repository.UserRepository;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StickerService stickerService;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(long id) {
        return userRepository.findUserById(id).orElse(null);
    }

    public User findUserByUid(String uid) {
        return userRepository.findByUid(uid).orElse(null);
    }

    public void generateDefaultStickers(User user) throws SaveStickerException {
        if(!userRepository.findByUid(user.getUid()).isPresent()) {
            Sticker defaultSticker = new Sticker(
                    null,
                    "Press \'+\' to create new Sticker",
                    0, 0, 1,
                    user.getUid());

            stickerService.saveSticker(defaultSticker);
        }
    }
}
