package br.com.newsmanagerApp.filters;

public class JpaRealm { 
//extends AuthorizingRealm{
//
//	private static String REALM_NAME = "jpaRealm";
//
//    @Inject
//    private UsuarioDAOImpl usuarioDAO;
//
//    public JpaRealm() {
//        setName(REALM_NAME); // This name must match the name in the User class's getPrincipals() method
//    }
//    
//	@Override
//	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection authToken) {
//		UsernamePasswordToken token = (UsernamePasswordToken) authToken;
//        UsuarioVO user = new UsuarioVO();
//		try {
//			user = usuarioDAO.buscarCredencial(token.getUsername());
//		} catch (DaoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        if (user != null) {
//            return (AuthorizationInfo) new SimpleAuthenticationInfo(user.getId(), user.getSenha(), getName());
//        } else {
//            return null;
//        }
//	}
//
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken principals) throws AuthenticationException {
//		Long userId = (Long) principals.getCredentials();
//		//UsuarioVO user = usuarioDAO.getByID(userId);
//        
//          return null;
//        
//	}

}
