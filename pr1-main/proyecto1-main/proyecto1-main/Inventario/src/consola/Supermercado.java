package consola;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.*;

import modelo.*;

import procesamiento.loader;


@SuppressWarnings("serial")
public class Supermercado extends JFrame
{

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------


	private JPanel panelAplicaciones;
	private DialogoAsignacion dAsignacion;
	private POS pos;
	private DialogoPromo dPromo;
	private DialogoPuntos dPuntos;



	public Supermercado( )
	{
		setTitle( "Supermercado" );
		setSize( 580, 700 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );


		pos = new POS();


		// Configura la interfaz
		setLayout( new BorderLayout( ) );

		pos = pos.agregarproductos(ejecutarCargarProductos("./data/productos.txt")) ;  // el parametro es la ruta del archivo productos.txt dentro del proyecto Eclipse
		pos = pos.agregarLotes(ejecutarCargarLotes("./data/lotes.txt")) ;  // parametros: ruta del archivo lotes.txt 

		// Panel de botones
		panelAplicaciones = new PanelAplicaciones( this );
		add( panelAplicaciones, BorderLayout.CENTER );

		setResizable( false );
		setLocationRelativeTo( null );

		// Configurar ventana para llamar al metodo dispose() al cerrar la ventana
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
	}

	public void iniciarPOS()
	{
		remove( panelAplicaciones );

		panelAplicaciones = new PanelPOS( this );
		add( panelAplicaciones, BorderLayout.CENTER );
		validate();
	}

	public void iniciarInventario()
	{
		remove( panelAplicaciones );

		panelAplicaciones = new PanelInventario( this );
		add( panelAplicaciones, BorderLayout.CENTER );
		validate();

	}

	public void registrarCliente( )
	{
		dAsignacion = new DialogoAsignacion( this, pos );
		dAsignacion.setVisible( true );
		validate();

	}

	public void nuevoPedido( )
	{
		remove( panelAplicaciones );
		pedido Pedido = new pedido();
		panelAplicaciones = new PanelPedido( this, pos , Pedido);
		add( panelAplicaciones, BorderLayout.CENTER );
		validate();
	}

	public void actualizarPedido( pedido Pedido)
	{
		remove( panelAplicaciones );

		panelAplicaciones = new PanelPedido( this, pos , Pedido);
		add( panelAplicaciones, BorderLayout.CENTER );
		validate();
	}


	public void eliminarLotes()
	{
		remove( panelAplicaciones );

		panelAplicaciones = new PanelEliminar( this, pos );
		add( panelAplicaciones, BorderLayout.CENTER );
		validate();
	}
	
	public void actualizarPanel(POS Pos)
	{
		remove(panelAplicaciones);
		panelAplicaciones = new PanelEliminar( this, Pos );
		add( panelAplicaciones, BorderLayout.CENTER );
		pos = Pos;
		validate();
	}

	public void agregarLotes()
	{
		remove( panelAplicaciones );

		panelAplicaciones = new PanelAgregar( this, pos );
		add( panelAplicaciones, BorderLayout.CENTER );
		validate();
	}

	public void actualizarpos(POS Pos)
	{
		pos = Pos;
	}
	
	public void agregarPromo()
	{
		dPromo = new DialogoPromo( this, pos );
		dPromo.setVisible( true );
		validate();
	}
	public JPanel getPanel()
	{
		return panelAplicaciones;
	}
	
	public void pagarPuntos(cliente Cliente, pedido Pedido)
	{
		dPuntos = new DialogoPuntos( this, pos , Cliente, Pedido);
		dPuntos.setVisible( true );
		validate();
	}

	public void regresar( )
	{
		remove( panelAplicaciones );

		panelAplicaciones = new PanelAplicaciones( this );
		add( panelAplicaciones, BorderLayout.CENTER );
		validate();
	}
	

	public ArrayList<producto> ejecutarCargarProductos(String rutaArchivo)
	{
		ArrayList<producto> productos = new ArrayList<producto>();
		try
		{
			productos = loader.leerInfoArchivoProducto(rutaArchivo);
			JOptionPane.showMessageDialog( this,"OK Se cargó el archivo " + rutaArchivo + " con información de los productos.", "Archivo", JOptionPane.INFORMATION_MESSAGE );

		}
		catch (FileNotFoundException e)
		{
			System.out.println();
			JOptionPane.showMessageDialog( this, "ERROR: el archivo " + rutaArchivo + " no se encontró.", "Archivo", JOptionPane.ERROR_MESSAGE );
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog( this, "ERROR: hubo un problema leyendo el archivo \" + rutaArchivo", "Archivo", JOptionPane.ERROR_MESSAGE );	
			System.out.println(e.getMessage());
		}

		return productos;
	}

	public ArrayList<lote> ejecutarCargarLotes(String rutaArchivo)
	{
		ArrayList<lote> lotes = new ArrayList<lote>();
		try
		{
			lotes = loader.leerInfoArchivoLotes(rutaArchivo);
			JOptionPane.showMessageDialog( this,"OK Se cargó el archivo " + rutaArchivo + " con información de los productos.", "Archivo", JOptionPane.INFORMATION_MESSAGE );
			System.out.println("OK Se cargó el archivo " + rutaArchivo + " con información de los productos.");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo " + rutaArchivo + " no se encontró.");
			JOptionPane.showMessageDialog( this, "ERROR: el archivo " + rutaArchivo + " no se encontró.", "Archivo", JOptionPane.ERROR_MESSAGE );
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo " + rutaArchivo);
			JOptionPane.showMessageDialog( this, "ERROR: hubo un problema leyendo el archivo \" + rutaArchivo", "Archivo", JOptionPane.ERROR_MESSAGE );	
			System.out.println(e.getMessage());
		}

		return lotes;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try
		{
			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );

			Supermercado interfaz = new Supermercado( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}