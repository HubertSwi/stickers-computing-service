package pl.com.sticker.scs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.sticker.scs.model.Sticker;

import java.util.List;

@Repository
public interface StickerRepository extends JpaRepository<Sticker, Long> {

    List<Sticker> findStickersByUserUid(String userUid);
}
