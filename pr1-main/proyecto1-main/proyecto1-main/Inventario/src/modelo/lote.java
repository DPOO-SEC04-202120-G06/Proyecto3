package modelo;


public class lote {
	private int id;
	private int fechaDeVencimiento;
	private double precioProveedor;
	private double precioVenta;
	private String Producto;
	private int cantidad;
	
	public lote(int theid, int thefechaDeVencimiento, double precioproveedor, double precioventa, String theproducto, int thecantidad)
	{
		this.id = theid;
		this.fechaDeVencimiento= thefechaDeVencimiento;
		this.precioProveedor = precioproveedor;
		this.precioVenta = precioventa;
		this.Producto = theproducto;
		this.cantidad = thecantidad;
		
	}
	
	public int getid()
	{
		return id;
	}
	public int getFechaDeVencimiento()
	{
		return fechaDeVencimiento;
	}
	
	public double getprecioProveedor()
	{
		return precioProveedor;
	}
	
	public double getprecioVenta()
	{
		return precioVenta;	
	}
	public String getproducto()
	{
		return Producto;	
	}
	public int getcantidad()
	{
		return cantidad;
	}
	public void comprar()
	{
		this.cantidad= cantidad-1;
	}
	
}
