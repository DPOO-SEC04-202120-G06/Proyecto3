package consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.*;

@SuppressWarnings("serial")
public class PanelPedido extends JPanel implements ActionListener
{
	private JPanel PanelTitulo;
	private JPanel PanelProductos;
	private JPanel PanelBotones;
	private JTextField txtCedula;
	private Supermercado ventana;
	private POS pos;
	private pedido Pedido;
	private JButton botonAceptar;
	private JButton bAgregar;
	private JButton bBack;
	private JButton bPuntos;
	private DialogoProducto dPedido;
	private DialogoAsignacion dAsignacion;
	public final static String ACEPTAR = "ACEPTAR";
	public final static String AGREGAR = "AGREGAR_PRODUCTO";
	public final static String BACK = "REGRESAR";
	public final static String PUNTOS = "PUNTOS";

	public PanelPedido(Supermercado pVentana, POS Pos, pedido Pedidoi)
	{
		this.ventana = pVentana;
		this.pos = Pos;
		this.Pedido = Pedidoi;
		setLayout( new BorderLayout( ) );
		PanelTitulo = new JPanel();
		PanelTitulo.setLayout(new GridLayout(2,1,2,2));
		PanelTitulo.setPreferredSize( new Dimension( 570, 100 ) );
		PanelTitulo.add(new JLabel("Pedido"));
		JPanel panelCedula = new JPanel( );
		panelCedula.setLayout( new FlowLayout( FlowLayout.LEFT, 5, 0 ) );
		JLabel etiquetaCedula = new JLabel( "Cédula " );
		txtCedula = new JTextField( );
		txtCedula.setColumns( 15 );
		panelCedula.add( etiquetaCedula );
		panelCedula.add( txtCedula );
		JPanel panelTotal = new JPanel( );
		panelTotal.setLayout( new FlowLayout( FlowLayout.LEFT, 5, 0 ) );
		JLabel txtTotal = new JLabel( "Total " );
		JLabel txt = new JLabel(Double.toString(Pedido.costoTotalPedido()));
		panelTotal.add( txtTotal );
		panelTotal.add( txt );
		PanelTitulo.add( panelCedula );
		PanelTitulo.add(panelTotal);
		add( PanelTitulo, BorderLayout.NORTH);

		inicializarPanelPedido( );
		add( PanelProductos, BorderLayout.CENTER);

		inicializarPanelBotones( );
		add( PanelBotones, BorderLayout.SOUTH);

	}

	public void inicializarPanelPedido( )
	{
		PanelProductos = new JPanel( );
		PanelProductos.setLayout( new GridLayout(10, 1, 8, 5 ) );
		PanelProductos.setPreferredSize( new Dimension(540, 470 ) );
		PanelProductos.setBorder( BorderFactory.createEtchedBorder() );
		JPanel panelNombre = new JPanel( );
		panelNombre.setLayout( new FlowLayout( FlowLayout.LEFT, 25, 0 ) );
		panelNombre.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		panelNombre.setPreferredSize(new Dimension(560,30));;
		JLabel etiquetaNombre = new JLabel( "Nombre                                                                                                    " );
		panelNombre.add( etiquetaNombre );
		JLabel etiquetaPrecio = new JLabel( "Precio " );
		panelNombre.add(etiquetaPrecio);
		PanelProductos.add( panelNombre );
		ArrayList<producto> productos = Pedido.getProductos();
		int size = productos.size();
		System.out.println(size);
		if (size > 0)
		{
			for(int i = 0; i <= size-1; i++)
			{

				producto Producto = productos.get(i);
				String nombre = Producto.getNombre();
				System.out.println(nombre);
				Double precio = (Producto.getPrecio());
				String promo = pos.getPromo(Producto.getCodigoBarras());
				if (promo != null)
				{
					double descuento = (Double.parseDouble(promo))/100;
					precio = precio - (precio*descuento);
					Pedido.descuento(Producto, precio);
				}
				JPanel PanelProducto = new JPanel();
				String precioS = Double.toString(precio);
				PanelProducto.setLayout( new FlowLayout( FlowLayout.LEFT, 5, 0 ) );
				JLabel etiquetaNombr = new JLabel(nombre +(" ".repeat(80-nombre.length())));
				JLabel etiquetaprecio = new JLabel(precioS);
				PanelProducto.add( etiquetaNombr );
				PanelProducto.add( etiquetaprecio );
				PanelProductos.add( PanelProducto );
				PanelTitulo = new JPanel();
				PanelTitulo.setLayout(new GridLayout(2,1,2,2));
				PanelTitulo.setPreferredSize( new Dimension( 570, 100 ) );
				PanelTitulo.add(new JLabel("Pedido"));
				JPanel panelCedula = new JPanel( );
				panelCedula.setLayout( new FlowLayout( FlowLayout.LEFT, 5, 0 ) );
				JLabel etiquetaCedula = new JLabel( "Cédula " );
				txtCedula = new JTextField( );
				txtCedula.setColumns( 15 );
				panelCedula.add( etiquetaCedula );
				panelCedula.add( txtCedula );
				JPanel panelTotal = new JPanel( );
				panelTotal.setLayout( new FlowLayout( FlowLayout.LEFT, 5, 0 ) );
				JLabel txtTotal = new JLabel( "Total " );
				JLabel txt = new JLabel(Double.toString(Pedido.getotal()));
				panelTotal.add( txtTotal );
				panelTotal.add( txt );
				PanelTitulo.add( panelCedula );
				PanelTitulo.add(panelTotal);
				add( PanelTitulo, BorderLayout.NORTH);
			}
		}
	}

	public void inicializarPanelBotones( )
	{
		PanelBotones = new JPanel( );
		PanelBotones.setLayout( new GridLayout( 1, 4, 8, 1 ) );
		PanelBotones.setSize(570,100);

		// Aceptar
		botonAceptar = new JButton( );
		botonAceptar.setText( "Aceptar" );
		botonAceptar.setActionCommand( ACEPTAR );
		botonAceptar.addActionListener( this );
		botonAceptar.setPreferredSize( new Dimension(40, 50 ) );
		PanelBotones.add( botonAceptar );

		// Cancelar
		bAgregar = new JButton( );
		bAgregar.setText( "Agregar Producto" );
		bAgregar.setActionCommand( AGREGAR );
		bAgregar.addActionListener( this );
		PanelBotones.add( bAgregar );

		bBack = new JButton( "Regresar al inicio" );
		bBack.setActionCommand( BACK );
		bBack.addActionListener( this );
		PanelBotones.add( bBack );

		bPuntos = new JButton( "Pagar con puntos" );
		bPuntos.setActionCommand( PUNTOS );
		bPuntos.addActionListener( this );
		PanelBotones.add( bPuntos );
	}
	public void agregarProducto( )
	{
		dPedido = new DialogoProducto( ventana, pos,Pedido );
		dPedido.setVisible( true );
		validate();

	}

	public JPanel mostrarProducto(producto Producto)
	{
		JPanel PanelProducto = new JPanel();
		PanelProducto.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
		String nombre = Producto.getNombre();
		JLabel etiquetaNombre = new JLabel(nombre +(" ".repeat(100-nombre.length())));
		JLabel etiquetaPrecio = new JLabel(Double.toString(Producto.getPrecio()));
		PanelProducto.add( etiquetaNombre );
		PanelProducto.add( etiquetaPrecio );
		PanelProductos.add( PanelProducto );
		return this;
	}



	@Override
	public void actionPerformed( ActionEvent pEvento )
	{
		String comando = pEvento.getActionCommand( );

		if( comando.equals( ACEPTAR ) )
		{
			String cedula = txtCedula.getText();
			if (cedula == "")
			{
				JOptionPane.showMessageDialog( this, "La cedula es necesaria para realizar la compra", "Registro", JOptionPane.ERROR_MESSAGE );

			}
			else
			{

				boolean registrado = pos.verificarCliente(cedula);
				if ( registrado)
				{
					pos.Comprar(Pedido);
					pos.calcularPuntos(Pedido, pos.getCliente(txtCedula.getText()));
					JOptionPane.showMessageDialog( this, "Pedido completado con exito", "Registro", JOptionPane.INFORMATION_MESSAGE );
					ventana.nuevoPedido();
				}
				else
				{
					dAsignacion = new DialogoAsignacion( ventana, pos );
					dAsignacion.setVisible( true );
					validate();
					pos.Comprar(Pedido);
					pos.calcularPuntos(Pedido, pos.getCliente(txtCedula.getText()));
				}
			}
		
		}
		else if( comando.equals( AGREGAR ) )
		{
			agregarProducto( );
		}
		else if( comando.equals( BACK ) )
		{
			ventana.regresar( );
		}
		else if( comando.equals( PUNTOS ) )
		{
			String cedula = txtCedula.getText();

			if(cedula != "") 
			{
				boolean encontrado = pos.verificarCliente(cedula);
				if(encontrado)
				{
					cliente Cliente = pos.getCliente(cedula);
					ventana.pagarPuntos(Cliente , Pedido);
				}
				else
				{
					JOptionPane.showMessageDialog( this, "El cliente no está registrado", "Error", JOptionPane.ERROR_MESSAGE );
				}
			}
			else
			{
				JOptionPane.showMessageDialog( this, "El campo de la cedula está vacío", "Error", JOptionPane.ERROR_MESSAGE );
			}
		}
	}
}


