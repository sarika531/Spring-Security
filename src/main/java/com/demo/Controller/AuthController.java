//package com.demo.Controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.demo.Payload.JWTAurhResponse;
//import com.demo.Payload.LoginDto;
//import com.demo.Payload.UserDto;
//import com.demo.Security.JwtTokenProvider;
//import com.demo.Service.UserService;
//
//
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//	
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	
//
//	@Autowired
//	private JwtTokenProvider jwtTokenProvider; 
//	
//	@PostMapping("/register")
//	public ResponseEntity<UserDto> createUser (@RequestBody UserDto userDto) {
//    return new ResponseEntity<>(userService.createUser(userDto),HttpStatus.CREATED);
//		
//	}
//
//	// User Login - Authenticate and return JWT token
//	@PostMapping("/login")
//	public ResponseEntity<JWTAurhResponse> loginUser(@RequestBody LoginDto loginDto) {
//		try {
//			// Authenticate the user
//			var authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
//			);
//
//			// Set the authentication in the security context
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			
//			// Generate JWT token
//			String token = jwtTokenProvider.generateToken(authentication);
//			
//			// Create response with token
//			JWTAurhResponse authResponse = new JWTAurhResponse(token);
//			
//			// Return the token in the response
//		    return new ResponseEntity<>(authResponse, HttpStatus.OK);
//	      } catch (BadCredentialsException e) {
//		// Handle invalid credentials error
//		return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
//	}
//	}
//}
