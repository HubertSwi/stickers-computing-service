package pl.com.sticker.scs.exceptions;

/**
 * Checked exception thrown when an attempt is made to save Sticker object
 * and it fails no matter for what reason.
 */
public class SaveStickerException extends Exception {

    public SaveStickerException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
