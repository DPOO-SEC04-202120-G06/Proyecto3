package consola;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;


@SuppressWarnings("serial")
public class PanelAplicaciones extends JPanel implements ActionListener
{
	public final static String POS = "POS";
	public final static String Inventario = "Encargado de Inventario";
	public final static String RUTA_IMAGEN = "./data/banner.jpg";
	
	private JButton bPOS;
	private JButton bInventario;
	private JPanel panelBotones;
	private JLabel labImagen;
	private ImageIcon imagen;
	private Supermercado ventana;

	public PanelAplicaciones( Supermercado pVentana)
	{
		ventana = pVentana;
		setLayout( new GridLayout( 2, 1, 0, 2 ) );
		panelBotones= new JPanel();
		panelBotones.setLayout( new GridLayout( 2, 3, 8, 2 ) );
		panelBotones.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );

		bPOS = new JButton( "POS" );
		bPOS.setActionCommand( POS );
		bPOS.addActionListener( this );
		bPOS.setPreferredSize( new Dimension( 40, 10 ) );
		panelBotones.add( bPOS );

		bInventario = new JButton( "Encargado de Inventario" );
		bInventario.setActionCommand( Inventario );
		bInventario.addActionListener( this );
		bInventario.setPreferredSize( new Dimension( 40, 10 ) );
		panelBotones.add( bInventario );
		
		imagen = new ImageIcon( RUTA_IMAGEN );
        labImagen = new JLabel( );
        labImagen.setIcon( imagen );
        add( labImagen ); 
        add(panelBotones);

	}

	public void actionPerformed( ActionEvent pEvento )
	{
		String comando = pEvento.getActionCommand( );

		if( comando.equals( POS ) )
		{
			ventana.iniciarPOS( );
		}
		else if( comando.equals( Inventario ) )
		{
			ventana.iniciarInventario( );
		}
	}
}
