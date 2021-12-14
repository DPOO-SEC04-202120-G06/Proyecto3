package procesamiento;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import modelo.lote;
import modelo.producto;



public class loader{
		
	public static ArrayList<producto> leerInfoArchivoProducto(String rutaArchivo) throws FileNotFoundException, IOException
	{
		ArrayList<producto> productos = new ArrayList<producto>();
		// "Abrir" el archivo de lotes y leerlo linea por linea usando un BufferedReader
		BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
		String linea = br.readLine();   // Leer la linea con el primer ProductoMenu en el archivo   
		// Un lote tiene la forma: lote,fecha de vencimiento, precio de proveedor, precio al público, producto, cantidad
		while (linea != null) // Cuando se llegue al final del archivo, linea tendra el valor null
		{
			// Separar los valores que estan en la linea por el caracter ';'
			String[] partes = linea.split(";");
			
			producto nuevoproducto = new producto(partes[0],partes[1],partes[2],Boolean.parseBoolean(partes[3]),Boolean.parseBoolean(partes[4]),Double.parseDouble(partes[5]),Float.parseFloat(partes[6]),partes[7]);   // Creacion de un objeto ProductoMenu con su nombre y precio base
			productos.add(nuevoproducto);

			linea = br.readLine(); // leer la proxima linea en el archivo con un nuevo ProductoMenu 
		}
		br.close();
		return productos;
	}
	public static ArrayList<lote> leerInfoArchivoLotes(String rutaArchivo) throws FileNotFoundException, IOException
	{
		ArrayList<lote> lotes = new ArrayList<lote>();
		// "Abrir" el archivo de lotes y leerlo linea por linea usando un BufferedReader
		BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
		String linea = br.readLine();   // Leer la linea con el primer ProductoMenu en el archivo   
		// Un lote tiene la forma: lote,fecha de vencimiento, precio de proveedor, precio al público, producto, cantidad
		while (linea != null) // Cuando se llegue al final del archivo, linea tendra el valor null
		{
			// Separar los valores que estan en la linea por el caracter ';'
			String[] partes = linea.split(";");
			
			lote nuevolote = new lote(Integer.parseInt(partes[0]), Integer.parseInt(partes[1]),Double.parseDouble(partes[2]),Double.parseDouble(partes[3]),partes[4],Integer.parseInt(partes[5]));   // Creacion de un objeto ProductoMenu con su nombre y precio base
			lotes.add( nuevolote );

			linea = br.readLine(); // leer la proxima linea en el archivo con un nuevo ProductoMenu 
		}
		br.close();
		return lotes;
	}
	
	
}


