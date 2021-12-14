package consola;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class PanelPOS extends JPanel implements ActionListener 
{
	public final static String REGISTRAR = "REGISTRAR_CLIENTE";

    /**
     * Opción anular.
     */
    public final static String NUEVO = "NUEVO_PEDIDO";

    /**
     * Opción buscar.
     */
    public final static String BACK = "REGRESAR";
    
    private JButton bRegistro;

    /**
     * Botón Anular.
     */
    private JButton bNuevo;

    /**
     * Botón de búsqueda.
     */
    private JButton bBack;
    
    private Supermercado ventana;
    
    public PanelPOS( Supermercado pVentana)
    {
        // Guarda la referencia a la ventana padre
        ventana = pVentana;

        // Configura propiedades visuales
        setLayout( new GridLayout( 3, 4, 8, 5 ) );
        setBorder( new EmptyBorder( 5, 5, 5, 5 ) );

        // Botón registrar
       	bRegistro = new JButton( "Registrar Cliente" );
        bRegistro.setActionCommand( REGISTRAR );
        bRegistro.addActionListener( this );
        bRegistro.setPreferredSize( new Dimension( 40, 10 ) );
        add( bRegistro );

        // Botón anular registro
        bNuevo = new JButton( "Nuevo Pedido" );
        bNuevo.setActionCommand( NUEVO );
        bNuevo.addActionListener( this );
        add( bNuevo );

        // Botón buscar pasajero
        bBack = new JButton( "Regresar al inicio" );
        bBack.setActionCommand( BACK );
        bBack.addActionListener( this );
        add( bBack );
        
    }
	@Override
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );

        if( comando.equals( REGISTRAR ) )
        {
            ventana.registrarCliente( );
        }
        else if( comando.equals( NUEVO ) )
        {
            ventana.nuevoPedido( );
        }
        else if( comando.equals( BACK ) )
        {
            ventana.regresar( );
        }
	}
}
