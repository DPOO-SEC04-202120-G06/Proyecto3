package modelo;

public class cliente {
	private String cedula;
	private String nombre;
	private String ciudad;
	private String sexo;
	private String E_civil;
	private double puntos;
	private String S_labor;
	private String edad;
	public cliente(String thedad,String labor,String thecedula, String thenombre, String theciudad, String thesexo,String theE_civil, double thepuntos)
	{
		this.cedula = thecedula;
		this.nombre = thenombre;
		this.ciudad = theciudad;
		this.sexo = thesexo;
		this.E_civil = theE_civil;
		this.puntos = thepuntos;
		this.S_labor = labor;
		this.edad = thedad;
		
	}
	public String getcedula()
	{
		return cedula;
		
	}
	public String getnombre()
	{
		return nombre;
		
	}
	public String getciudad()
	{
		return ciudad;
		
	}
	public String getsexo()
	{
		return sexo;
		
	}
	public String getE_civil()
	{
		return E_civil;
		
	}
	public double getpuntos()
	{
		return puntos;
		
	}
	public String getedad()
	{
		return edad;
		
	}
	public String getlabor()
	{
		return S_labor;
		
	}
	public void setpuntos(double npuntos)
	{
		this.puntos= npuntos;
	}
	
}
