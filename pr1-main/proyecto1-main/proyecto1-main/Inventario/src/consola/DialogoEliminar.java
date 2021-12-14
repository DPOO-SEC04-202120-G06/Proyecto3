package consola;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import modelo.*;

@SuppressWarnings("serial")
public class DialogoEliminar extends JDialog implements ActionListener
{
	public final static String ACEPTAR = "ACEPTAR";
	public final static String CANCELAR = "CANCELAR";
	private POS pos;
	private JLabel texto;
	private Supermercado principal;
	private JButton botonAceptar;
	private JButton botonCancelar;
	private JTextField TNombre;
	private JPanel panelBotones;

	public DialogoEliminar( Supermercado pPrincipal, POS Pos )
	{
		// Guarda la referencia a la ventana padre
		principal = pPrincipal;

		// Guarda la referencia al avión
		pos = Pos;


		setTitle( "Eliminar Lote" );
		setSize( 300, 150 );
		setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );

		setLayout( new BorderLayout( ) );

		JPanel panelAux = new JPanel( );
		panelAux.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
		panelAux.setLayout( new BorderLayout( ) );
		add( panelAux, BorderLayout.CENTER );

		texto = new JLabel("Ingrese el código de barras:");
		panelAux.add(texto,BorderLayout.NORTH);


		JPanel panelNombre = new JPanel( );
		panelNombre.setLayout( new FlowLayout( FlowLayout.CENTER, 5, 0 ) );
		TNombre = new JTextField( );
		TNombre.setColumns( 15 );
		panelNombre.add( TNombre );
		panelAux.add( panelNombre,BorderLayout.CENTER  );

		inicializarPanelBotones( );
		panelAux.add( panelBotones, BorderLayout.SOUTH );

		setModal( true );
		setLocationRelativeTo( principal );
		setResizable( false );
	}

	public POS registrar()
	{
		String nombre = TNombre.getText();
		if(nombre != "") 
		{
		inventario Inventario = pos.getInventario().eliminarLote(nombre);
		pos.setInventario(Inventario);
		pos=eliminarProducto(nombre,pos);
		}
		else
		{
				JOptionPane.showMessageDialog( this, "Numero de lote invalido", "Registro", JOptionPane.ERROR_MESSAGE );

		}
		return pos;
		
	}
	public POS eliminarProducto(String nombre, POS Pos)
	{
		ArrayList<producto> productos = Pos.getProductos();
		producto Producto = Pos.getProducton(nombre);
		productos.remove(Producto);
		Pos.setProductos(productos);
		return Pos;
	}
	public void inicializarPanelBotones( )
	{
		panelBotones = new JPanel( );
		panelBotones.setLayout( new GridLayout( 1, 2, 8, 1 ) );

		// Aceptar
		botonAceptar = new JButton( );
		botonAceptar.setText( "Aceptar" );
		botonAceptar.setActionCommand( ACEPTAR );
		botonAceptar.addActionListener( this );
		panelBotones.add( botonAceptar );

		// Cancelar
		botonCancelar = new JButton( );
		botonCancelar.setText( "Cancelar" );
		botonCancelar.setActionCommand( CANCELAR );
		botonCancelar.addActionListener( this );
		panelBotones.add( botonCancelar );
	}
	public void actionPerformed( ActionEvent pEvento )
	{
		String comando = pEvento.getActionCommand( );

		if( comando.equals( ACEPTAR ) )
		{
			POS newpos = registrar( );
			principal.actualizarPanel(newpos);

		}
		else if( comando.equals( CANCELAR ) )
		{
			dispose( );
		}

	}
}
