package com.ASSESSMENT.Meeting.config.service;

import com.ASSESSMENT.Meeting.config.customExeption.*;
import com.ASSESSMENT.Meeting.persistence.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.mindrot.jbcrypt.BCrypt;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.function.Consumer;

public class ValidationService {
    private final String SECRET_KEY = "miClaveSecretaParaProecccionDeTokenMiClaveSecretaParaProecccionDeTokenMiClaveSecret";
    private final SecureRandom secureRandom = new SecureRandom();
    private final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    private final int EXPIRATION_TIME = 6000 * 60 * 60 * 24;
    /**
     * This method is used to validate the data that is passed to the method
     * @param object is the data that will be validated
     * @param message is the message that will be displayed when the exception is thrown
     * */
    public <T> void Validation( T object, String message) {
        PvtValidation(object, message);
    }

    /**
     * This method is used to validate the data that is passed to the method
     * @param entity is the entity that will be updated
     * @param updateFunction is the function that will be applied to the entity
     * @param value is the value that will be validated
     * @param message is the message that will be displayed when the exception is thrown
     * */
    public <T, K> void validateAndApplyIfNotNull(T entity, Consumer<T> updateFunction, K value, String message) {
        PvtvalidateAndApplyIfNotNull(entity, updateFunction, value, message);
    }
    /** this method is used to validate if the email of the user exists
     * @param email is the email of the user
     * */
    public <T> void EmailExist(T email, String message) {
        PvtEmailExist(email, message);
    }
    /** this method is used to encode the password of the user
     * @param password is the password of the user
     * */
    public String EncodePassword(String password) {
        return PvtEncodePassword(password);
    }
    /** this method is used to validate if the password of the user is correct
     * @param plainPassword is the password of the user
     * @param hashedPassword is the password of the user encoded
     * */
    public void CheckPassword(String plainPassword, String hashedPassword) {
        PvtCheckPassword(plainPassword, hashedPassword);
    }
    /** this method is used to generate a token for the user
     * @param user is the user
     * */
    public String GenerateToken(User user) {
        return PvtGenerateToken(user);
    }
    /** this method is used to get the data of the token
     * @param token is the token
     * */
    public Claims GetDataToken(String token) {
        return PvtGetDataToken(token);
    }
    
    /** this method is used to generate a code of access
     * @param Longitud is the length of the code
     * */
    public String generarCodigoAcceso(int Longitud) {
        return PvtgenerarCodigoAcceso(Longitud);
    }

    private <T> void PvtValidation( T object, String message) {
        try {
            if (object == null ){
                throw new BadRequestDataException(message);
            }
            if (object instanceof String strData){
                if ( ((String) object).isEmpty()){
                    throw new BadRequestDataException(message);
                }
                CharactersSpecial(strData);
            }
        } catch (BadRequestDataException e){
            throw new BadRequestDataException(e.getMessage());
        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw new InternalServerError(e.getMessage());
        }
    }
    private <T,K> void PvtvalidateAndApplyIfNotNull(T entity, Consumer<T> updateFunction, K value, String message) {
        try {
            if (value != null) {
                Validation(value, message);
                updateFunction.accept(entity);
            }
        } catch (BadRequestDataException e){
            throw new BadRequestDataException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
    private String PvtEncodePassword(String password) {
        try {
            return BCrypt.hashpw(password, BCrypt.gensalt(12));
        } catch (InternalServerError  | NullPointerException e) {
            throw new InternalServerError("Error in the password encoding"+ e.getMessage());
        }
    }
    private <T> void PvtEmailExist(T email, String message) {
        if (email != null) {
            throw new BadRequestDataException(message);
        }
    }
    private  void PvtCheckPassword(String plainPassword, String hashedPassword) {
        try {
            if (!BCrypt.checkpw(plainPassword, hashedPassword)) {
                throw new TokenNotAutorization("Password not correct");
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InternalServerError("Error in the password validation" + e.getMessage());
        }
    }
    private String PvtGenerateToken( User user){
        try {
            SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            Date expirationDate = new Date(System.currentTimeMillis() + 3600000);
            String token = Jwts.builder()
                    .claim("email", user.getEmail())
                    .claim("username", user.getUserName())
                    .claim("id", user.getUserId())
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
            return token;
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InternalServerError("Error in the token generation" + e.getMessage());
        }
    }
    private Claims PvtGetDataToken(String token){
        try {
            if (token == null || !token.startsWith("Bearer ") || token.isEmpty()) {
                throw new BadRequestDataException("Token is null");
            }
            String tokenJwt = token.substring(7);
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(tokenJwt)
                    .getBody();
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new TokenNotAutorization(e.getMessage());
        }
    }
    private void CharactersSpecial(String input) {
        String specialCharacters = "!#$%&*()'+/;<=>?[]^_`{|}~";
        for (int i = 0; i < input.length(); i++) {
            if (specialCharacters.contains(String.valueOf(input.charAt(i)))) {
                throw new BadRequestDataException("The data contains special characters : "+ input);
            }
        }
    }

    private String PvtgenerarCodigoAcceso (int Longitud){
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes).substring(0, Longitud);
    }

}
