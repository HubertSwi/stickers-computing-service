package pl.com.sticker.scs.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.sticker.scs.model.Sticker;
import pl.com.sticker.scs.exceptions.SaveStickerException;
import pl.com.sticker.scs.services.StickerService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StrickertController {

    @Autowired
    private StickerService stickerService;

    @ApiOperation(value="Returns user's stickers")
    @GetMapping("/scs/users/{userUid}/stickers")
    public ResponseEntity<List> getUserStickers(@PathVariable(value="userUid") String userUid) {
        System.out.printf("Called GET: /scs/users/%s/stickers\n", userUid);

        try {
            return ResponseEntity.ok()
                    .body(stickerService.getStickers(userUid));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(null);
        }
    }

    @ApiOperation(value="Saves sticker")
    @PutMapping(path = "/scs/users/{userUid}/stickers/{id}")
    public ResponseEntity<Sticker> saveSticker(@RequestBody Sticker sticker,
                                               @PathVariable(value="userUid") String userUid,
                                               @PathVariable("id") String stickerId) {
        System.out.printf("Called PUT: /scs/users/%s/stickers/%s\n", userUid, stickerId);

        try {
            return ResponseEntity.ok()
                    .body(stickerService.handleSaveSticker(sticker, stickerId));
        } catch (SaveStickerException sse) {
            return ResponseEntity.badRequest()
                    .body(null);
        }
    }

    @ApiOperation(value="Saves stickers")
    @PutMapping("/scs/users/{userUid}/stickers")
    public ResponseEntity<List<Sticker>> saveStickers(@RequestBody List<Sticker> stickersList,
                                                      @PathVariable(value="userUid") String userUid) {
        System.out.printf("Called PUT: /scs/users/%s/stickers\n", userUid);

        try {
            List<Sticker> savedStickerList = StreamSupport.stream((stickerService.saveStickers(stickersList)).spliterator(), false)
                    .collect(Collectors.toList());
            return ResponseEntity.ok()
                    .body(savedStickerList);
        } catch (SaveStickerException sse) {
            return ResponseEntity.badRequest()
                    .body(null);
        }
    }

    @ApiOperation(value="Deletes sticker")
    @DeleteMapping("/scs/users/{userUid}/stickers/{stickerId}")
    public ResponseEntity<Boolean> deleteSticker (@PathVariable String userUid, @PathVariable String stickerId) {
        System.out.printf("Called DELETE: /scs/users/%1$s/stickers/%2$s", userUid, stickerId);

        boolean response = stickerService.deleteUserSticker(userUid, stickerId);

        return ResponseEntity.ok().body(response);
    }
}
