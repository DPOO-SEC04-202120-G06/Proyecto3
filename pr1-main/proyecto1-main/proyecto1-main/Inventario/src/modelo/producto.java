package modelo;


public class producto {
	private String nombre;
	private String codigoBarras;
	private String lote;
	private boolean congelado;
	private boolean refrigerado;
	private double precio;
	private float peso;
	private String categoria;
	public producto(String elnombre,String elcodigoBarras,String thelote, boolean thecongelado, boolean therefrigerado, double elprecio, float elpeso, String thecategoria)
	{
		this.codigoBarras= elcodigoBarras;
		this.lote = thelote;
		this.congelado = thecongelado;
		this.refrigerado = therefrigerado;
		this.nombre = elnombre;
		this.precio = elprecio;
		this.peso = elpeso;
		this.categoria = thecategoria;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	public String getCodigoBarras()
	{
		return codigoBarras;
	}
	
	public String getLote()
	{
		return lote;
	}
	
	public boolean getCongelado()
	{
		return congelado;	
	}
	public boolean getRefrigerado()
	{
		return refrigerado;	
	}
	public double getPrecio()
	{
		return precio;
	}
	public float getPeso()
	{
		return peso;
	}
	
	public String getCategoria()
	{
		return categoria;
	}
	public void setPrecio(double newprecio)
	{
		precio = newprecio;
	}

}
