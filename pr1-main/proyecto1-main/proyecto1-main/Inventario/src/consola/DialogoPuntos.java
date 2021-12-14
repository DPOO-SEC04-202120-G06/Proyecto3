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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.*;

@SuppressWarnings("serial")
public class DialogoPuntos extends JDialog implements ActionListener
{
	public final static String ACEPTAR = "ACEPTAR";
	public final static String CANCELAR = "CANCELAR";
	private POS pos;
	private pedido Pedido;
	private JLabel texto;
	private cliente Cliente;
	private Supermercado principal;
	private JButton botonAceptar;
	private JButton botonCancelar;
	private JTextField TPuntos;
	private JPanel panelBotones;
	private JPanel panelPuntos;

	public DialogoPuntos( Supermercado pPrincipal, POS Pos, cliente Clientei, pedido Pedidoi)
	{
		principal = pPrincipal;

		pos = Pos;

		Pedido =Pedidoi;

		Cliente = Clientei;

		setTitle( "Pagar con puntos" );
		setSize( 300, 150 );
		setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );

		setLayout( new BorderLayout( ) );

		JPanel panelAux = new JPanel( );
		panelAux.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
		panelAux.setLayout( new BorderLayout( ) );
		add( panelAux, BorderLayout.CENTER );

		JPanel panelPuntos = new JPanel( );
		panelPuntos.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
		JLabel etiqueta = new JLabel( "Puntos totales :" );
		String puntos = Double.toString(Cliente.getpuntos());
		JLabel txtPuntos = new JLabel(puntos);
		panelPuntos.add( etiqueta );
		panelPuntos.add( txtPuntos );
		panelAux.add(panelPuntos,BorderLayout.NORTH);


		JPanel panel = new JPanel( );
		panelPuntos.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
		JLabel tag = new JLabel( "Puntos a gastar" );
		TPuntos = new JTextField( );
		TPuntos.setColumns( 15 );
		panel.add(tag);
		panel.add( TPuntos );
		panelAux.add( panel,BorderLayout.CENTER  );

		inicializarPanelBotones( );
		panelAux.add( panelBotones, BorderLayout.SOUTH );

		setModal( true );
		setLocationRelativeTo( principal );
		setResizable( false );
	}

	public void pagar()
	{
		double puntos = Double.parseDouble(TPuntos.getText());
		if ((puntos-Cliente.getpuntos())>0)
		{
			Pedido.setotal(Pedido.getotal()-(puntos*15));
			pos.gastarPuntos(Pedido, Cliente, puntos);
			principal.actualizarPedido(Pedido);
			dispose();
		}
		else
		{
			JOptionPane.showMessageDialog( this, "Puntos insuficientes", "Puntos", JOptionPane.ERROR_MESSAGE );
		}
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
			pagar();
		}
		else if( comando.equals( CANCELAR ) )
		{
			dispose( );
		}

	}
}

