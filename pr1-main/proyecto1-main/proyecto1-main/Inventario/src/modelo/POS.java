package modelo;

import java.util.ArrayList;
import java.util.HashMap;





public class POS {
	private inventario Inventario;
	private ArrayList<cliente> clientes;
	private ArrayList<producto> productos;
	private HashMap<String, String> promo;

	public POS()
	{
		this.Inventario = new inventario();
		this.clientes = new ArrayList<cliente>();
		this.productos = new ArrayList<producto>();
		this.promo = new HashMap<String, String>();
	}

	public void calcularPuntos(pedido Pedido, cliente Cliente)
	{
		double puntos= (Pedido.costoTotalPedido())/1000;
		double decimal= puntos % 1;
		puntos = puntos-decimal;
		double puntosAnteriores = Cliente.getpuntos();
		cliente ClienteA = Cliente; 
		Cliente.setpuntos(puntos+puntosAnteriores);
		clientes.set(clientes.indexOf(ClienteA), Cliente);
	}
	
	public void gastarPuntos(pedido Pedido, cliente Cliente, double puntos)
	{
		double puntosAnteriores = Cliente.getpuntos();
		cliente ClienteA = Cliente; 
		Cliente.setpuntos(puntosAnteriores-puntos);
		clientes.set(clientes.indexOf(ClienteA), Cliente);
	}
	
	public void registarCliente(cliente Cliente)
	{
		clientes.add(Cliente);		
	}

	public void setInventario(inventario I) 
	{
		Inventario = I;
	}
	
	public void setProductos(ArrayList<producto> I) 
	{
		productos = I;
	}
	
	
	public void Comprar(pedido Pedido)
	{
		ArrayList<producto> Productos = Pedido.getProductos();
		int size = Productos.size();
		for(int i = 0; i <= size-1; i++)
		{

			producto Producto = Productos.get(i) ;
			String nombre = Producto.getNombre();
			Inventario.Comprarproducto(nombre);


		}
	}
	
	public ArrayList<producto> getProductos()
	{
		return productos;
	}

	public boolean verificarCliente(String cedulaa)
	{
		boolean encontrado = false;
		
		int c = Integer.parseInt(cedulaa);
		double size = clientes.size();
		for( int i = 0; i < size ; i++ )
		{

			cliente Cliente = clientes.get(i);
			String cedulab = Cliente.getcedula();
			int a = Integer.parseInt(cedulab);
			if(a == c)
			{
				encontrado = true;
			}
		}

		return encontrado;
	}

	public cliente getCliente(String cedulaa)
	{
		double size = clientes.size();
		double a = Integer.parseInt(cedulaa);
		cliente ClienteR = null;
		for( int i = 0; i < size ; i++ )
		{
			cliente Cliente = clientes.get(i);
			String cedulab = Cliente.getcedula();
			double b = Integer.parseInt(cedulab);
			if(a == b)
			{
				ClienteR = Cliente;
			}
		}

		return ClienteR;
	}
	public producto getProducto(String codigobarras)
	{
		producto Productof = null ;
		double size = productos.size();
		int x = Integer.parseInt(codigobarras);
		for( int i = 0; i < size-1 ; i++ )
		{
			
			producto Producto = productos.get(i);
			String codigoBarras = Producto.getCodigoBarras();

			if(x == Integer.parseInt(codigoBarras))
			{
				Productof = Producto;
			}
		}
		return Productof;
	}

	public POS agregarLotes(ArrayList<lote> lotes)
	{
		Inventario = Inventario.agregarLotes(lotes);
		System.out.println((Inventario.getinventario()).get("aceite").getFechaDeVencimiento());
		return this;
	}
	public inventario getInventario()
	{
		return Inventario;
	}

	public POS agregarproductos(ArrayList<producto> Productos)
	{
		double size = Productos.size();
		System.out.println(Productos.get(2).getNombre());
		for( int i = 0; i < size-1 ; i++ )
		{
			producto Producto = Productos.get(i);
			productos.add(Producto);
		}
		return this;
	}
	public producto getProducton(String nombre)
	{
		producto Productof = null;
		double size = productos.size();
		for( int i = 0; i < size-1 ; i++ )
		{

			producto Producto = productos.get(i);
			String codigoBarras = Producto.getNombre();
			if(nombre == codigoBarras)
			{
				Productof = Producto;
			}
		}
		return Productof;
	}
	public void setPromo(String cb, String valor)
	{
		promo.put(cb, valor);
	}
	
	public String getPromo(String cb)
	{
		String Promo = promo.get(cb);
		return Promo;
	}
}
