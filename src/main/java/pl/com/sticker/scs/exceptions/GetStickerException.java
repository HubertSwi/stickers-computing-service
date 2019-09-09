package pl.com.sticker.scs.exceptions;

/**
 * Checked exception thrown when an attempt is made to get Stickers list
 * and it fails no matter for what reason.
 */
public class GetStickerException extends Exception {

    public GetStickerException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
