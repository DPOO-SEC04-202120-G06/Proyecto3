package consola;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.*;

@SuppressWarnings("serial")
public class PanelAgregar extends JPanel implements ActionListener
{
	public final static String PRODUCTOS = "AGREGAR_PRODUCTOS";
    public final static String LOTES = "AGREGAR_LOTES";
    public final static String BACK = "REGRESAR";
    
    private JPanel panelBotones;
    private JPanel panelDatos;
    private JButton bProductos;
    private JButton bLotes;
    private JButton bBack;
    private JTextField ruta;
    private POS pos;
    private Supermercado principal;
    
    public PanelAgregar(Supermercado pPrincipal, POS Pos)
    {
    	principal = pPrincipal;
    	pos = Pos;
    	setLayout( new GridLayout( 2, 3, 4, 2 ) );
        setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
        
        JPanel panelAux = new JPanel( );
        panelAux.setBorder( new EmptyBorder( 2, 3, 10, 10 ) );
        panelAux.setLayout( new BorderLayout( ) );
        add( panelAux, BorderLayout.CENTER );

        // Crea el panel de datos
        inicializarPanelDatos( );
        panelAux.add( panelDatos, BorderLayout.CENTER );

        // Crea el panel de botones
        inicializarPanelBotones( );
        panelAux.add( panelBotones, BorderLayout.SOUTH );
        
    }
    
    public void agregarLotes()
    {
    	String route = ruta.getText();
    	ArrayList<lote> lotes = principal.ejecutarCargarLotes(route);
    	pos.agregarLotes(lotes);
    }
    
    public void agregarProductos()
    {
    	String route = ruta.getText();
    	ArrayList<producto> productos = principal.ejecutarCargarProductos(route);
    	pos.agregarproductos(productos);
    }
    
    public void inicializarPanelDatos( )
    {
        panelDatos = new JPanel( );
        panelDatos.setLayout( new GridLayout( 1, 1, 2, 8) );
        panelDatos.setBorder( BorderFactory.createTitledBorder( "Agregar lotes/productos" ) );

        JPanel panel = new JPanel( );
        ruta = new JTextField( );
        ruta.setColumns( 15 );
        panel.add( ruta );
        panelDatos.add( panel );
        
    }
    
    public void inicializarPanelBotones( )
    {
        panelBotones = new JPanel( );
        panelBotones.setLayout( new GridLayout( 1, 3 ) );

        bLotes = new JButton( );
        bLotes.setText( "Agregar lotes" );
        bLotes.setActionCommand( LOTES );
        bLotes.addActionListener( this );
        panelBotones.add( bLotes );

        bProductos = new JButton( );
        bProductos.setText( "Agregar Productos" );
        bProductos.setActionCommand( PRODUCTOS );
        bProductos.addActionListener( this );
        panelBotones.add( bProductos );
        
        bBack = new JButton( "Regresar al inicio" );
        bBack.setActionCommand( BACK );
        bBack.addActionListener( this );
        panelBotones.add( bBack );
    }
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );

        if( comando.equals( LOTES ) )
        {
        	agregarLotes();
        }
        else if( comando.equals( PRODUCTOS	 ) )
        {
            agregarProductos();
        }
        else if( comando.equals( BACK ) )
        {
            principal.regresar( );
        }
        }
}
