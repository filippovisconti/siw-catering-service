package it.uniroma3.siw.siwcateringservice.auth.service;

//@Service
//@Slf4j
public class RoleServiceImpl {//implements RoleService {

	/*
	@Autowired
	FirebaseAuth firebaseAuth;

	//@Autowired
	//private SecurityProperties securityProps;

	public void setUserClaims (String uid, List<MyPermission> requestedPermissions) throws FirebaseAuthException {
		List<String> permissions = requestedPermissions
				.stream()
				.map(Enum::toString)
				.collect(Collectors.toList());

		Map<String, Object> claims = Map.of("custom_claims", permissions);

		firebaseAuth.setCustomUserClaims(uid, claims);


	}
	@Override
	public void addRole(String uid, String role) throws Exception {
		try {
			UserRecord user = firebaseAuth.getUser(uid);
			Map<String, Object> claims = new HashMap<>();
			user.getCustomClaims().forEach((k, v) -> claims.put(k, v));
			if (role.equals("READ") || role.equals("WRITE")) {
				if (!claims.containsKey(role)) {
					claims.put(role, true);
				}
				firebaseAuth.setCustomUserClaims(uid, claims);
			} else {
				throw new Exception("Not a valid Application role, Allowed roles => READ, WRITE"
						);
			}

		} catch (FirebaseAuthException e) {
			log.error("Firebase Auth Error ", e);
		}

	}

	@Override
	public void removeRole(String uid, String role) {
		try {
			UserRecord user = firebaseAuth.getUser(uid);
			Map<String, Object> claims = new HashMap<>();
			user.getCustomClaims().forEach((k, v) -> claims.put(k, v));
			if (claims.containsKey(role)) {
				claims.remove(role);
			}
			firebaseAuth.setCustomUserClaims(uid, claims);
		} catch (FirebaseAuthException e) {
			log.error("Firebase Auth Error ", e);
		}
	}

	 */

}