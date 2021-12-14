package consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.*;

@SuppressWarnings("serial")
public class PanelEliminar extends JPanel implements ActionListener
{
	private JPanel PanelTitulo;
	private JPanel PanelProductos;
	private JPanel PanelBotones;
	private DialogoEliminar dEliminar;
	private Supermercado ventana;
	private POS pos;
	private JButton bEliminar;
	private JButton bBack;
	public final static String ACEPTAR = "ACEPTAR";
    public final static String ELIMINAR = "ELIMINAR_LOTE";
    
    public PanelEliminar(Supermercado pVentana, POS Pos)
    {
    	ventana = pVentana;
    	pos = Pos;
    	setLayout( new BorderLayout( ) );
    	JPanel PanelAux = new JPanel();
    	PanelAux.setLayout( new BorderLayout( ) );
        PanelTitulo = new JPanel();
        PanelTitulo.setLayout(new GridLayout(2,1,2,2));
        PanelTitulo.setPreferredSize( new Dimension( 570, 50 ) );
        PanelTitulo.add(new JLabel("Eliminar Lotes"));

        PanelAux.add( PanelTitulo, BorderLayout.NORTH);
        
        mostrarPos( );
        PanelAux.add( PanelProductos, BorderLayout.CENTER);
        
        inicializarPanelBotones( );
        PanelAux.add( PanelBotones, BorderLayout.SOUTH);
        
        add(PanelAux);
        
    }
    
    public void mostrarPos()
    {
    	HashMap<String, lote> Inventario = pos.getInventario().getinventario();
    	ArrayList<producto> productos = pos.getProductos();
    	int size = productos.size();
    	System.out.println(size);
    	PanelProductos = new JPanel( );
    	PanelProductos.setLayout( new GridLayout(size+1,1,0,3) );
    	PanelProductos.setPreferredSize( new Dimension(540, 520 ) );
    	PanelProductos.setBorder( BorderFactory.createEtchedBorder() );
    	JPanel panelNombre = new JPanel( );
        panelNombre.setLayout( new FlowLayout( FlowLayout.LEFT, 25, 0 ) );
        panelNombre.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        JLabel etiquetaNombre = new JLabel( "Nombre/#lote                                                                                   " );
        panelNombre.add( etiquetaNombre );
        JLabel etiquetaPrecio = new JLabel( "Fecha de vencimiento(YY-MM-DD)");
        panelNombre.add(etiquetaPrecio);
        PanelProductos.add( panelNombre );
    	for(int i = 0; i <= size-1; i++)
		{
    		

    		producto Producto = productos.get(i);
    		System.out.println(Producto.getLote());
    		String nombre = Producto.getNombre();
    		System.out.println(nombre);

    		lote Lote = Inventario.get(nombre);
    		nombre = nombre + Lote.getid();
    		JPanel PanelProducto = new JPanel();
        	PanelProducto.setLayout( new FlowLayout( FlowLayout.LEFT, 5, 0 ) );
            JLabel etiquetaNombr = new JLabel(nombre +(" ".repeat(80-nombre.length())));
            JLabel etiquetaFecha = new JLabel(Integer.toString(Lote.getFechaDeVencimiento()) );
            PanelProducto.add( etiquetaNombr );
            PanelProducto.add( etiquetaFecha );
            PanelProductos.add( PanelProducto );
		}
    }
    
    public void inicializarPanelBotones( )
    {
    	 PanelBotones = new JPanel( );
    	 PanelBotones.setLayout( new GridLayout( 1, 3, 8, 1 ) );
    	 PanelBotones.setPreferredSize(new Dimension( 570, 50 ));

         // Aceptar
         bEliminar = new JButton( );
         bEliminar.setText( "Eliminar lote" );
         bEliminar.setActionCommand( ELIMINAR );
         bEliminar.addActionListener( this );
         bEliminar.setPreferredSize( new Dimension(40, 50 ) );
         PanelBotones.add( bEliminar );

         
         bBack = new JButton( "Regresar al inicio" );
         bBack.setActionCommand( ACEPTAR );
         bBack.addActionListener( this );
         PanelBotones.add( bBack );
     }
    public void eliminarlote()
    {
    	dEliminar = new DialogoEliminar( ventana, pos );
    	dEliminar.setVisible( true );
    	validate();
    }
    
	public void actionPerformed( ActionEvent pEvento )
	{
		String comando = pEvento.getActionCommand( );

		if( comando.equals( ELIMINAR ) )
		{
			eliminarlote();
		}
		else if( comando.equals( ACEPTAR ) )
		{
			ventana.iniciarInventario( );
		}
	}
    

}
