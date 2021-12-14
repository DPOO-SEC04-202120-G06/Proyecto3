package consola;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.*;

@SuppressWarnings("serial")
public class DialogoPromo extends JDialog implements ActionListener
{
	public final static String ACEPTAR = "ACEPTAR";
	public final static String CANCELAR = "CANCELAR";
	private POS pos;
	private JLabel texto;	
	private Supermercado principal;
	private JButton botonAceptar;
	private JButton botonCancelar;
	private JTextField tcb;
	private JTextField tva;
	private JPanel panelBotones;
	private JPanel panelDatos;

	public DialogoPromo( Supermercado pPrincipal, POS Pos)
	{
		// Guarda la referencia a la ventana padre
		principal = pPrincipal;

		// Guarda la referencia al avión
		pos = Pos;


		setTitle( "Agregar Promo" );
		setSize( 300, 250 );
		setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );

		setLayout( new BorderLayout( ) );

		panelDatos = new JPanel( );
		panelDatos.setLayout( new GridLayout( 8, 2, 2, 8) );
		panelDatos.setBorder( BorderFactory.createTitledBorder( "Datos del pasajero" ) );

		setLayout( new BorderLayout( ) );

		JPanel panelAux = new JPanel( );
		panelAux.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
		panelAux.setLayout( new BorderLayout( ) );
		add( panelAux, BorderLayout.CENTER );

		inicializarPanelDatos( );
		panelAux.add( panelDatos, BorderLayout.CENTER );

		inicializarPanelBotones( );
		panelAux.add( panelBotones, BorderLayout.SOUTH );

		setModal( true );
		setLocationRelativeTo( principal );
		setResizable( false );
	}

	public POS registrar()
	{
		String cb = tcb.getText();
		String valor = tva.getText();
		pos.setPromo(cb, valor);
		return pos;
	}

	public void inicializarPanelDatos( )
	{
		panelDatos = new JPanel( );
		panelDatos.setLayout( new GridLayout( 2, 1, 2, 8) );
		panelDatos.setBorder( BorderFactory.createTitledBorder( "Datos del cliente" ) );
		texto = new JLabel("Código de barras:");
		JPanel panelNombre = new JPanel( );
		panelNombre.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
		tcb = new JTextField( );
		tcb.setColumns( 15 );
		panelNombre.add(texto);
		panelNombre.add( tcb );
		panelDatos.add( panelNombre);

		texto = new JLabel("Porcentaje:");
		JPanel panelPromo = new JPanel( );
		panelPromo.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
		tva = new JTextField( );
		tva.setColumns( 15 );
		panelPromo.add(texto);
		panelPromo.add( tva );
		panelDatos.add( panelPromo);
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
			if(Integer.parseInt(tva.getText())<100)
			{
				String cb = tcb.getText();
				if(pos.getProducto(cb)==null)
				{
					JOptionPane.showMessageDialog( this, "El código de barras es inválido", "Registro", JOptionPane.ERROR_MESSAGE );
					dispose();	
				}
				else
				{
					POS newpos = registrar( );
					principal.actualizarpos(newpos);
					dispose( );
					JOptionPane.showMessageDialog( this, "Promoción creada con éxito", "Promoción", JOptionPane.INFORMATION_MESSAGE );
				}
			}
			else
			{
				JOptionPane.showMessageDialog( this, "El porcentaje debe ser inferior al 100%", "Registro", JOptionPane.ERROR_MESSAGE );
			}
		}
		else if( comando.equals( CANCELAR ) )
		{
			dispose( );
		}

	}
}

