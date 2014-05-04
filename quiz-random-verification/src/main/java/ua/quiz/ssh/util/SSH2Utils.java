package ua.quiz.ssh.util;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;

public class SSH2Utils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SSH2Utils.class);
    private static final String RSA = "RSA";
    private static final BigInteger NEGATIVE_ONE = BigInteger.valueOf(-1);

    @NotNull
    public static KeyPair generateRSAKeyPair() {
        try {
            return KeyPairGenerator.getInstance(RSA).generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Failed to generate key pair: algorithm '" + RSA + "' not found");
            throw new IllegalStateException(e);
        }
    }

    public static boolean isValidRSAKeyPair(@NotNull KeyPair keyPair) {
        PrivateKey privateKey = keyPair.getPrivate();
        if (!(privateKey instanceof RSAPrivateCrtKey)) {
            throw new IllegalArgumentException("Private key type is not supported: java.security.interfaces.RSAPrivateCrtKey expected, but actual " +
                    (privateKey == null ? null : privateKey.getClass()));
        }
        PublicKey publicKey = keyPair.getPublic();
        if (!(publicKey instanceof RSAPublicKey)) {
            throw new IllegalArgumentException("Public key type is not supported: java.security.interfaces.RSAPublicKey expected, but actual " +
                    (publicKey == null ? null : publicKey.getClass()));
        }

        return isValidRSAKeyPair((RSAPrivateCrtKey) privateKey, (RSAPublicKey) publicKey);
    }

    public static boolean isValidRSAKeyPair(@NotNull RSAPrivateCrtKey privateKey, @NotNull RSAPublicKey publicKey) {
        BigInteger n = publicKey.getModulus();
        BigInteger e = publicKey.getPublicExponent();
        BigInteger d = privateKey.getPrivateExponent();
        BigInteger p = privateKey.getPrimeP();
        BigInteger q = privateKey.getPrimeQ();

        BigInteger pq = p.multiply(q);//shold equal to n
        BigInteger eulerMod = p.add(NEGATIVE_ONE).multiply(q.add(NEGATIVE_ONE));// φ(n)=(p-1)*(q-1)
        BigInteger mod = d.multiply(e).mod(eulerMod);// (d*e) mod φ(n), should be 1
        return n.equals(pq) && BigInteger.ONE.equals(mod);
    }
}
