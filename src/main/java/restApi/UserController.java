package restApi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Entity.Users;
import SercurityJWT.JwtUtil;
import Services.MyUserDetailsService;
import Services.UserService;
import model.AuthenticationRequest;
import model.AuthenticationResponse;

/* Ben bu proje içerisinde kendi yazdığım sorguları controller'a adapte ettim
 * ancak PagingSortingRepository ögesini içeren sınıfı da yazdım istenirse ordan
 * hazır sınıflar alınabilir. Daha iyi sonuçlar elde edilebilir.  */


@Controller
public class UserController {
	@Autowired
	private AuthenticationManager authenticationManager;
	private UserService userService;
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	private static Logger log  =
			Logger.getLogger(UserController.class);
	
	@GetMapping("/add")
	public void add(@RequestBody Users user) {
		userService.add(user);
	}
	@GetMapping("/update")
	public void update(@RequestBody Users user) {
		userService.update(user);
	}
	@GetMapping("/delete")
	public void delete(@RequestBody Users user) {
		userService.delete(user);
	}
	@GetMapping("/getById/{id}")
	public Users getByUser(@PathVariable int id) {
		return userService.getById(id);
	}
	
	@RequestMapping({"/hello"})
	public String hello(){
		return "Hello World";
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Yanlış kullanıcı adı veya  parolar",e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt=jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	
	}
	
	
	
	
	
	
	
}
