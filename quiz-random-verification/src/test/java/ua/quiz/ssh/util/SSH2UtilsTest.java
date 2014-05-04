package ua.quiz.ssh.util;

import org.junit.Test;

import java.security.KeyPair;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SSH2UtilsTest {

    @Test
    public void generateRSAKeyPairTest() {
        KeyPair keyPair = SSH2Utils.generateRSAKeyPair();
        assertNotNull(keyPair);
        assertNotNull(keyPair.getPrivate());
        assertNotNull(keyPair.getPublic());
    }

    @Test
    public void isValidRSAKeyPairTest() {
        KeyPair keyPair1 = SSH2Utils.generateRSAKeyPair();
        KeyPair keyPair2 = SSH2Utils.generateRSAKeyPair();
        assertTrue(SSH2Utils.isValidRSAKeyPair(keyPair1));
        assertTrue(SSH2Utils.isValidRSAKeyPair(keyPair2));
        assertFalse(SSH2Utils.isValidRSAKeyPair(new KeyPair(keyPair1.getPublic(), keyPair2.getPrivate())));

        try {
            SSH2Utils.isValidRSAKeyPair(null);
            assertTrue(false);
        } catch (NullPointerException | IllegalArgumentException e) {
            //OK
        }
        try {
            SSH2Utils.isValidRSAKeyPair(new KeyPair(null, keyPair1.getPrivate()));
            assertTrue(false);
        } catch (NullPointerException | IllegalArgumentException e) {
            //OK
        }
        try {
            SSH2Utils.isValidRSAKeyPair(new KeyPair(keyPair1.getPublic(), null));
            assertTrue(false);
        } catch (NullPointerException | IllegalArgumentException e) {
            //OK
        }
    }
}
