/**
 *
 */
package org.rash.auction.util;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.rash.auction.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mshai9
 */
public class JWTokenUtility {

    private static final Logger logger = LoggerFactory.getLogger(RsaKeyProducer.class.getName());

    public static String buildJWT(User user) {
        RsaJsonWebKey rsaJsonWebKey = RsaKeyProducer.produce();
        logger.info("RSA hash code... " + rsaJsonWebKey.hashCode());

        JwtClaims claims = new JwtClaims();

        claims.setSubject(user.getEmail()); // the subject/principal is whom the token is about
        claims.setClaim("roles", user.getUserRole().getKey());
        claims.setClaim("displayName", user.getDisplayName());
        claims.setIssuedAtToNow();

        // claims.setExpirationTime(NumericDate.fromSeconds(60 * 60));

        JsonWebSignature jsonWebSignature = new JsonWebSignature();
        jsonWebSignature.setPayload(claims.toJson());
        jsonWebSignature.setKey(rsaJsonWebKey.getPrivateKey());
        jsonWebSignature.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        String jsonWebToken = null;
        try {
            jsonWebToken = jsonWebSignature.getCompactSerialization();
        } catch (JoseException ex) {
            ex.printStackTrace();
        }

        logger.info("Claim:\n" + claims);
        logger.info("JWS:\n" + jsonWebSignature);

        logger.info("JWT:\n" + jsonWebToken);

        return jsonWebToken;
    }


    public static String buildJWT(String subject) {
        RsaJsonWebKey rsaJsonWebKey = RsaKeyProducer.produce();

        System.out.println("RSA hash code... " + rsaJsonWebKey.hashCode());

        JwtClaims claims = new JwtClaims();
        claims.setSubject(subject); // the subject/principal is whom the token is about

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(rsaJsonWebKey.getPrivateKey());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        String jwt = null;
        try {
            jwt = jws.getCompactSerialization();
        } catch (JoseException ex) {
            logger.info("" + ex);
        }

        System.out.println("Claim:\n" + claims);
        System.out.println("JWS:\n" + jws);
        System.out.println("JWT:\n" + jwt);

        return jwt;
    }

    /**
     * Validate the JSON Web Token for signature, expiration and not before time.
     */
    public static String validateToken(String userJWToken) throws InvalidJwtException {
        String subject = null;
        RsaJsonWebKey rsaJsonWebKey = RsaKeyProducer.produce();

        logger.info("RSA hash code... " + rsaJsonWebKey.hashCode());

        JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireSubject() // the JWT must have a subject claim
                .setVerificationKey(rsaJsonWebKey.getKey()) // verify the signature with the public key
                .build(); // create the JwtConsumer instance

        try {
            // Validate the JWT and process it to the Claims
            JwtClaims jwtClaims = jwtConsumer.processToClaims(userJWToken);
            subject = (String) jwtClaims.getClaimValue("sub");
            logger.info("JWT validation succeeded! " + jwtClaims);
        } catch (InvalidJwtException e) {
            e.printStackTrace(); // on purpose
            throw e;
        }

        return subject;
    }

    public static JwtClaims validateTokenClaims(String userJWToken) throws InvalidJwtException {
        RsaJsonWebKey rsaJsonWebKey = RsaKeyProducer.produce();

        logger.info("RSA hash code... " + rsaJsonWebKey.hashCode());

        JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireSubject() // the JWT must have a subject claim
                .setVerificationKey(rsaJsonWebKey.getKey()) // verify the signature with the public key
                .build(); // create the JwtConsumer instance

        JwtClaims jwtClaims = null;
        try {
            // Validate the JWT and process it to the Claims
            jwtClaims = jwtConsumer.processToClaims(userJWToken);
            logger.info("JWT validation succeeded! " + jwtClaims);
        } catch (InvalidJwtException e) {
            e.printStackTrace(); // on purpose
            throw e;
        }

        return jwtClaims;
    }

    public static JWTPrincipal buildPrincipal(final JwtClaims claims) {
        JWTPrincipal principal = new JWTPrincipal();

        principal.setName((String) claims.getClaimValue("sub"));

        String role = (String) claims.getClaimValue("roles");
        principal.setRoles(new String[]{role});

        principal.setDisplayName((String) claims.getClaimValue("displayName"));
        return principal;
    }
}
