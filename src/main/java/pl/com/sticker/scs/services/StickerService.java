package pl.com.sticker.scs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.sticker.scs.model.Sticker;
import pl.com.sticker.scs.exceptions.GetStickerException;
import pl.com.sticker.scs.exceptions.SaveStickerException;
import pl.com.sticker.scs.repository.StickerRepository;

import java.util.List;

@Service
public class StickerService {

    @Autowired
    private StickerRepository stickerRepository;

    public List<Sticker> getStickers(String userUid) throws GetStickerException {
        System.out.println("Getting sticker");

        try {
            return stickerRepository.findStickersByUserUid(userUid);
        } catch (Exception e) {
            throw new GetStickerException("Failed to get stickers for user with id: " + userUid, e);
        }
    }

    public Sticker handleSaveSticker(Sticker sticker, String stickerId) throws SaveStickerException {
        if (Integer.parseInt(stickerId) == 0) {
            sticker.setId(null);
        }

        System.out.println("Saving sticker: " + sticker);

        try {
            return stickerRepository.save(sticker);
        } catch (Exception e) {
            throw new SaveStickerException("Failed to save sticker with id: " + sticker.getId(), e);
        }
    }

    public Sticker saveSticker(Sticker sticker) throws SaveStickerException {
        System.out.println("Saving sticker: " + sticker);

        try {
            return stickerRepository.save(sticker);
        } catch (Exception e) {
            throw new SaveStickerException("Failed to save sticker with id: " + sticker.getId(), e);
        }
    }

    public Iterable<Sticker> saveStickers(Iterable<Sticker> stickers) throws SaveStickerException {
        System.out.println("Saving stickers:");
        for(Sticker s : stickers)
            System.out.println(s);

        try {
            return stickerRepository.saveAll(stickers);
        } catch (Exception e) {
            throw new SaveStickerException("Failed to save stickers.", e);
        }
    }

    public boolean deleteUserSticker(String userUid, String stickerId) {
        System.out.println("Deleting sticker");

        if (stickerRepository.findById(Long.parseLong(stickerId)).isPresent()) {
            Sticker s = stickerRepository.findById(Long.parseLong(stickerId)).get();

            if (userUid.equals(s.getUserUid())) {
                stickerRepository.delete(s);
                return true;
            }
        }

        return false;
    }
}
