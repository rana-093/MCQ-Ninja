package net.therap.exception;

/**
 * @author masud.rana
 * @since 15/7/21
 */
public class WebSecurityException extends Exception {

    private static final long serialVersionUID = -3387516993124229948L;

    public WebSecurityException(String message) {
        super(message);
    }
}
