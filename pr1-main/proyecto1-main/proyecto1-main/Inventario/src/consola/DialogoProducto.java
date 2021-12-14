package consola;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.*;

@SuppressWarnings("serial")
public class DialogoProducto extends JDialog implements ActionListener
{
    public final static String ACEPTAR = "ACEPTAR";
    public final static String CANCELAR = "CANCELAR";
    private POS pos;
    private JLabel texto;
	private pedido Pedido;
    private Supermercado principal;
    private JButton botonAceptar;
    private JButton botonCancelar;
    private JTextField Cbarras;
    private JPanel panelBotones;
    
    public DialogoProducto( Supermercado pPrincipal, POS Pos ,pedido PEDIDO)
    {
        // Guarda la referencia a la ventana padre
        principal = pPrincipal;

        // Guarda la referencia al avión
        pos = Pos;
        
        Pedido = PEDIDO;

        setTitle( "Agregar Producto" );
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
        Cbarras = new JTextField( );
        Cbarras.setColumns( 15 );
        panelNombre.add( Cbarras );
        panelAux.add( panelNombre,BorderLayout.CENTER  );
        
        inicializarPanelBotones( );
        panelAux.add( panelBotones, BorderLayout.SOUTH );

        setModal( true );
        setLocationRelativeTo( principal );
        setResizable( false );
    }
    
    public void registrar()
    {
    	String nombre = Cbarras.getText();
    	producto Producto = pos.getProducto(nombre);
    	Pedido.agregarProducto(Producto);
    	principal.actualizarPedido(Pedido);
    	dispose();
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
            registrar( );
        }
        else if( comando.equals( CANCELAR ) )
        {
            dispose( );
        }
       
        }
}
