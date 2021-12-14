package modelo;

import java.util.ArrayList;

public class pedido {
	private ArrayList<producto> Productos;
	private double total;
	public pedido()
	{
		this.Productos = new ArrayList<producto>();
		this.total = 0;
	}

	public void agregarProducto(producto Producto)
	{
		Productos.add(Producto);
	}
	public void eliminarProducto(producto Producto)
	{
		Productos.remove(Producto);
	}
	public double costoTotalPedido()
	{
		double Total = 0 ;
		double size = Productos.size();
		for(int i = 0; i <= size-1; i++)
		{
			double precio = Productos.get(i).getPrecio() ;
			Total= Total +precio ;
		}
		total=Total;
		return total;
	}
	public ArrayList<producto> getProductos()
	{
		return Productos;
	}
	public void descuento(producto Producto, double precio)
	{
		double size = Productos.size();
		for(int i = 0; i <= size-1; i++)
		{
			if(Productos.get(i)== Producto)
			{
				Productos.remove(i) ;
				Producto.setPrecio(precio);
				Productos.add(Producto);
			}
		}
	}
	public void setotal(double puntos)
	{
		total = costoTotalPedido();
		total = total-puntos;
	}
	
	public double getotal()
	{
		return total;
	}
}
