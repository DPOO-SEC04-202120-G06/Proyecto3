package modelo;

import java.util.HashMap;
import java.util.ArrayList;



public class inventario {
	private HashMap<String, lote> invent;
	public inventario() 
	{
		this.invent = new HashMap<String, lote>();
	}
	public boolean verificarDisponibilidad(String nombreProducto)
	{
		boolean contiene = invent.containsKey(nombreProducto);
		return contiene;
	}
	public void Comprarproducto(String nombreProducto) {
		lote Lote = invent.get(nombreProducto);
		Lote.comprar();
		invent.replace(nombreProducto, Lote);
	}
	public inventario eliminarLote(String nombreProducto)
	{
		lote Lote = invent.get(nombreProducto);
		invent.remove(nombreProducto, Lote);
		return this;
		
	}
	public inventario agregarLotes(ArrayList<lote> Lotes)
	{
		double size = Lotes.size();
		for(int i = 0; i <= size-1; i++)
		{
			lote Lote = Lotes.get(i) ;
			String nombre = Lote.getproducto() ;
			invent.put(nombre, Lote);
		}
		return this;
	}

	public HashMap<String, lote> getinventario()
	{
		return invent;
	}
}
