/**
 * This package to filter all the request and response
 * This class helps in validating the JWT token.
 */
package com.marksandspencer.foodshub.common.filter;

import static com.marksandspencer.foodshub.common.constant.ApplicationConstant.AUDIENCE;
import static com.marksandspencer.foodshub.common.constant.ApplicationConstant.JWTHEADER;
import static com.marksandspencer.foodshub.common.constant.ApplicationConstant.JWTISSUER;
import static com.marksandspencer.foodshub.common.constant.ApplicationConstant.JWTKEY;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.HttpsJwksVerificationKeyResolver;

import com.marksandspencer.foodshub.common.constant.ErrorCode;
import com.marksandspencer.foodshub.common.exception.CommonServiceException;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class JwtAuthenticationFilter.
 */
@Slf4j
public class JwtAuthenticationFilter implements Filter {

	@Override
	public void doFilter(final ServletRequest request,
						 final ServletResponse response,
						 final FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Optional<String> token = Optional.ofNullable(httpRequest.getHeader(JWTHEADER));
		try {
			if (token.isPresent()) {
				// validateToken(token.get());
				chain.doFilter(request, response);
			} else {
				throw new CommonServiceException(ErrorCode.INVALID_JWT_TOKEN);
			}
		} catch (CommonServiceException e) {
			log.error("Exception Occured while Validating JWT Token");
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}

	}

	/**
	 * Validate token.
	 *
	 * @param jwtToken the jwt token
	 */
	void validateToken(final String jwtToken) {
		
		HttpsJwks httpsJkws = new HttpsJwks(JWTKEY);
		HttpsJwksVerificationKeyResolver httpsJwksKeyResolver = new HttpsJwksVerificationKeyResolver(httpsJkws);
		
		JwtConsumer consumer = new JwtConsumerBuilder()
									.setRequireExpirationTime()
									.setRequireSubject()
									.setExpectedAudience(AUDIENCE)
									.setAllowedClockSkewInSeconds(3600)
									.setVerificationKeyResolver(httpsJwksKeyResolver)
									.build();

		try {
			// Validate the JWT and process it to the Claims
			JwtClaims jwtClaims = consumer.processToClaims(jwtToken);
			log.debug("JWT ExpirationTime [" + jwtClaims.getExpirationTime() + "]");
			int secondsOfAllowedClockSkew = 30;
			if ((NumericDate.now().getValue() - secondsOfAllowedClockSkew)
						>= jwtClaims.getExpirationTime().getValue()) {
				// send a clear error message to the API consumer that token is
				// expired and renew/refresh is needed
				log.debug("JWT is Expired!!!!!");
				throw new CommonServiceException(ErrorCode.INVALID_JWT_TOKEN);
			}

		} catch (MalformedClaimException e) {
			log.error("Exception Occured while Validating JWT Claim", e.getMessage());
			throw new CommonServiceException(ErrorCode.INVALID_JWT_TOKEN);
		} catch (InvalidJwtException e) {
			// InvalidJwtException will be thrown, if the JWT failed processing
			// or validation in anyway.
			// Hopefully with meaningful explanations(s) about what went wrong.
			log.error("while Validating JWT InvalidJwtException %s", e.getMessage());
			throw new CommonServiceException(ErrorCode.INVALID_JWT_TOKEN);
		}

		JwtConsumer jwtConsumerExpectedIssuer = new JwtConsumerBuilder().setRequireExpirationTime().setRequireSubject()
				.setExpectedAudience(AUDIENCE).setAllowedClockSkewInSeconds(3600).setExpectedIssuer(JWTISSUER)
				.setVerificationKeyResolver(httpsJwksKeyResolver).build();

		try {
			// Validate the JWT and process it to the Claims
			JwtClaims jwtClaims = jwtConsumerExpectedIssuer.processToClaims(jwtToken);
			log.debug(jwtClaims.getIssuer());
		} catch (InvalidJwtException e) {
			// InvalidJwtException will be thrown, if the JWT failed processing
			// or validation in anyway.
			// Hopefully with meaningful explanations(s) about what went wrong.
			log.error("Exception Occured while Validating JWT", e.getMessage());
			throw new CommonServiceException(ErrorCode.INVALID_JWT_TOKEN);
		} catch (MalformedClaimException mce) {
			log.error("while Validating  MalformedClaimException", mce.getMessage());
			throw new CommonServiceException(ErrorCode.INVALID_JWT_TOKEN);
		}

	}
}
