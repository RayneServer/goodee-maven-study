package dev.coma.study.config.security;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import dev.coma.study.member.MemberDAO;
import dev.coma.study.member.MemberDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenManager {
	// Token을 생성하거나 검증
	
	// 노출되면 안 됨
	@Value("${jwt.secret-key}")
	private String secretKey;
	@Value("${jwt.token-valid-time}")
	private Long tokenValidTime;
	@Value("${jwt.refresh-valid-time}")
	private Long refreshValidTime;
	@Value("${jwt.issuer}")
	private String issuer;
	private SecretKey key;
	@Autowired
	private MemberDAO memberDAO;
	
	// 생성자에서 코드 작성 가능
	@PostConstruct
	public void init() {
		String keyData = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
		key = Keys.hmacShaKeyFor(keyData.getBytes());
	}
	
	// Token 발급
	private String createToken(Authentication authentication, Long validTime) {
		return Jwts.builder()
							 .setSubject(authentication.getName()) // 사용자의 ID
							 .claim("roles", authentication.getAuthorities().toString())
							 .setIssuedAt(new Date(System.currentTimeMillis())) // Token 생성 시간
							 .setExpiration(new Date(System.currentTimeMillis() + validTime))
							 .setIssuer(issuer)
							 .signWith(key)
							 .compact()
							 ;
	}
	
	public String makeAccessToken(Authentication authentication) {
		return this.createToken(authentication, tokenValidTime);
	}
	
	public String makeRefreshToken(Authentication authentication) {
		return this.createToken(authentication, refreshValidTime);
	}
	
	// Token 검증
	public Authentication getAuthenticationByToken(String token) throws Exception {
		Claims claims = Jwts.parser()
												.verifyWith(key)
												.build()
												.parseSignedClaims(token)
												.getPayload()
												;
		
		// 검증 통과 (실패 시 Exception 발생)
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId(claims.getSubject());
		UserDetails userDetails = memberDAO.selectMember(memberDTO);
		
		// MemberDTO(UserDetails)를 Authentication으로 변경
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		return authentication;
	}
}
