package Enum;
import automation.utils.PropertiesUtils;

public enum Login {

	Servidor(PropertiesUtils.getAppProperty("usuario.servidor"), PropertiesUtils.getAppProperty("senha.servidor")),
	Magistrado(PropertiesUtils.getAppProperty("usuario.magistrado"), PropertiesUtils.getAppProperty("senha.magistrado")),
	Distribuidor(PropertiesUtils.getAppProperty("usuario.distribuidor"), PropertiesUtils.getAppProperty("senha.distribuidor")),
	Conciliador(PropertiesUtils.getAppProperty("usuario.conciliador"), PropertiesUtils.getAppProperty("senha.conciliador")),
	Portal(PropertiesUtils.getAppProperty("usuario.portal"), PropertiesUtils.getAppProperty("senha.portal")),
	Assessor(PropertiesUtils.getAppProperty("usuario.assessor"), PropertiesUtils.getAppProperty("senha.assessor")),
	Plantao(PropertiesUtils.getAppProperty("usuario.plantao"), PropertiesUtils.getAppProperty("senha.plantao")),
	Coordenador(PropertiesUtils.getAppProperty("usuario.coordenador"), PropertiesUtils.getAppProperty("senha.coordenador"));
	

	
	private final String login;
	private final String senha;
	
	Login(String login, String senha){
		this.login = login;
		this.senha = senha;
	}
	
	public String getLogin(){
		return this.login;
	}
	
	public String getSenha(){
		return this.senha;
	}
}
