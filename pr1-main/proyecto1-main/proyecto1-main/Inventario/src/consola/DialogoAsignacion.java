package consola;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.*;


@SuppressWarnings("serial")
public class DialogoAsignacion extends JDialog implements ActionListener
{
    public final static String ACEPTAR = "ACEPTAR";
    public final static String CANCELAR = "CANCELAR";
    public final static String SOLTERO = "Soltero";
    public final static String CASADO = "Casado";
    public final static String INDEPENDIENTE = "Independiente";
    public final static String ESTUDIANTE = "Estudiante";
    public final static String EMPLEADO = "Empleado";
    public final static String DESEMPLEADO = "Desempleado";
    
    private POS pos;
    private Supermercado principal;
    private JPanel panelBotones;
    private JPanel panelDatos;
    private JButton botonAceptar;
    private JButton botonCancelar;
    private JComboBox cbEstado;
    private JComboBox cbSituacion;
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextField txtApellidos;
    private JTextField txtsexo;
    private JTextField txtciudad;
    
    public DialogoAsignacion( Supermercado pPrincipal, POS Pos )
    {
        // Guarda la referencia a la ventana padre
        principal = pPrincipal;

        // Guarda la referencia al avión
        pos = Pos;

        setTitle( "Registro de cliente" );
        setSize( 300, 500 );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );

        setLayout( new BorderLayout( ) );

        JPanel panelAux = new JPanel( );
        panelAux.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
        panelAux.setLayout( new BorderLayout( ) );
        add( panelAux, BorderLayout.CENTER );

        // Crea el panel de datos
        inicializarPanelDatos( );
        panelAux.add( panelDatos, BorderLayout.CENTER );

        // Crea el panel de botones
        inicializarPanelBotones( );
        panelAux.add( panelBotones, BorderLayout.SOUTH );

        setModal( true );
        setLocationRelativeTo( principal );
        setResizable( false );
    }
    public void registrar( )
    {
    	String cedula;
        String nombre;
        String ciudad;
        String E_civil;
        String sexo;
        double puntos;
        String S_labor;
        String edad;
        

        nombre = txtNombre.getText( )+' '+txtApellidos.getText();
        cedula = txtCedula.getText( );
        E_civil = ( String )cbEstado.getSelectedItem( );
        ciudad = txtciudad.getText();
        sexo = txtsexo.getText();
        S_labor = ( String )cbSituacion.getSelectedItem( );
        edad = txtEdad.getText();
        puntos = 0;

        if( cedula == null || cedula.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "La cédula es requerida", "Registro", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
           
                if( nombre == null || nombre.equals( "" ) )
                {
                    JOptionPane.showMessageDialog( this, "El nombre es requerido", "Registro", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    // Crea al pasajero
                    cliente Cliente = new cliente( edad,S_labor,cedula,nombre,ciudad,sexo,E_civil,puntos);

                    // Verifica que no este ya el pasajero registrado
                    boolean es = pos.verificarCliente( Cliente.getcedula() );

                    if( es == true )
                    {
                        JOptionPane.showMessageDialog( this, "El cliente ya ha sido registrado", "Registro", JOptionPane.ERROR_MESSAGE );
                    }
                    else
                    {
                      pos.registarCliente(Cliente);
                      principal.actualizarpos(pos);
                      dispose();
                    }
                  
                }
         }
    }
    public void inicializarPanelDatos( )
    {
        panelDatos = new JPanel( );
        panelDatos.setLayout( new GridLayout( 8, 2, 2, 8) );
        panelDatos.setBorder( BorderFactory.createTitledBorder( "Datos del cliente" ) );

        // Cédula
        JPanel panelCedula = new JPanel( );
        panelCedula.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel etiquetaCedula = new JLabel( "Cédula " );
        txtCedula = new JTextField( );
        txtCedula.setColumns( 15 );
        panelCedula.add( etiquetaCedula );
        panelCedula.add( txtCedula );
        panelDatos.add( panelCedula );

        // Nombre
        JPanel panelNombre = new JPanel( );
        panelNombre.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel etiquetaNombre = new JLabel( "Nombre " );
        txtNombre = new JTextField( );
        txtNombre.setColumns( 15 );
        panelNombre.add( etiquetaNombre );
        panelNombre.add( txtNombre );
        panelDatos.add( panelNombre );

        JPanel panelciudad = new JPanel( );
        panelciudad.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel etiquetaciudad = new JLabel( "Ciudad " );
        txtciudad = new JTextField( );
        txtciudad.setColumns( 15 );
        panelciudad.add( etiquetaciudad );
        panelciudad.add( txtciudad );
        panelDatos.add( panelciudad );
        
        JPanel panelsexo = new JPanel( );
        panelsexo.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel etiquetasexo = new JLabel( "Sexo " );
        txtsexo = new JTextField( );
        txtsexo.setColumns( 15 );
        panelsexo.add( etiquetasexo );
        panelsexo.add( txtsexo );
        panelDatos.add( panelsexo );
        
        JPanel panelApellidos = new JPanel( );
        panelApellidos.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel etiquetaApellidos = new JLabel( "Apellidos " );
        txtApellidos = new JTextField( );
        txtApellidos.setColumns( 15 );
        panelApellidos.add( etiquetaApellidos );
        panelApellidos.add( txtApellidos );
        panelDatos.add( panelApellidos );
        
        JPanel panelEdad = new JPanel( );
        panelEdad.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel etiquetaEdad = new JLabel( "Edad " );
        txtEdad = new JTextField( );
        txtEdad.setColumns( 15 );
        panelEdad.add( etiquetaEdad );
        panelEdad.add( txtEdad );
        panelDatos.add( panelEdad );
        
        // Ubicación en el avión

        JPanel pEstado = new JPanel( new FlowLayout( FlowLayout.RIGHT ) );
        pEstado.setPreferredSize( new Dimension( 250, 30 ) );
        JLabel lEstado = new JLabel( "Estado civil " );
        cbEstado = new JComboBox( );
        cbEstado.setAlignmentX( Component.LEFT_ALIGNMENT );
        cbEstado.setPreferredSize( txtCedula.getPreferredSize( ) );
        cbEstado.addActionListener( this );
        cbEstado.addItem(CASADO);
        cbEstado.addItem(SOLTERO);
        pEstado.add( lEstado );
        pEstado.add( cbEstado );

        // Clase de silla
        JPanel pSituacion = new JPanel( new FlowLayout( FlowLayout.RIGHT ) );
        pSituacion.setPreferredSize( new Dimension( 250, 30 ) );
        JLabel lSituacion = new JLabel( "Situacion laboral " );
        cbSituacion = new JComboBox( );
        cbSituacion.setAlignmentX( Component.LEFT_ALIGNMENT );
        cbSituacion.addActionListener( this );
        cbSituacion.addItem( INDEPENDIENTE );
        cbSituacion.addItem( EMPLEADO );
        cbSituacion.addItem( DESEMPLEADO );
        cbSituacion.addItem( ESTUDIANTE );
        cbSituacion.setPreferredSize( txtCedula.getPreferredSize( ) );
        pSituacion.add( lSituacion );
        pSituacion.add( cbSituacion );

        panelDatos.add( pEstado );
        panelDatos.add( pSituacion );
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

