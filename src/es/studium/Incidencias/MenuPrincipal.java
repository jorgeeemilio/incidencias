package es.studium.Incidencias;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MenuPrincipal implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Men� Principal");
	
	MenuBar menuBar = new MenuBar();
	
	Menu mnuUsuarios = new Menu("Usuarios");
	Menu mnuElementos = new Menu("Elementos");
	Menu mnuIndicencias = new Menu("Incidencias");
	
	MenuItem mniNuevoUsuario = new MenuItem("Nuevo");
	MenuItem mniListadoUsuario = new MenuItem("Listado");
	MenuItem mniBajaUsuario = new MenuItem("Baja");
	MenuItem mniModificarUsuario = new MenuItem("Modificar");
	
	MenuItem mniNuevoElemento = new MenuItem("Nuevo");
	MenuItem mniListadoElemento = new MenuItem("Listado");
	MenuItem mniBajaElemento = new MenuItem("Baja");
	MenuItem mniModificarElemento = new MenuItem("Modificar");
	
	MenuItem mniNuevoIncidencias = new MenuItem("Nuevo");
	MenuItem mniListadoIncidencias = new MenuItem("Listado");
	MenuItem mniBajaIncidencias = new MenuItem("Baja");
	MenuItem mniModificarIncidencias = new MenuItem("Modificar");
	
	MenuPrincipal()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setSize(400,400);
		ventana.addWindowListener(this);
		
		mniNuevoUsuario.addActionListener(this);
		mniListadoUsuario.addActionListener(this);
		mniBajaUsuario.addActionListener(this);
		mniModificarUsuario.addActionListener(this);
		mnuUsuarios.add(mniNuevoUsuario);
		mnuUsuarios.add(mniListadoUsuario);
		mnuUsuarios.add(mniBajaUsuario);
		mnuUsuarios.add(mniModificarUsuario);
		
		mniNuevoElemento.addActionListener(this);
		mniListadoElemento.addActionListener(this);
		mniBajaElemento.addActionListener(this);
		mniModificarElemento.addActionListener(this);
		mnuElementos.add(mniNuevoElemento);
		mnuElementos.add(mniListadoElemento);
		mnuElementos.add(mniBajaElemento);
		mnuElementos.add(mniModificarElemento);
		
		menuBar.add(mnuUsuarios);
		menuBar.add(mnuElementos);
		menuBar.add(mnuIndicencias);
		
		ventana.setMenuBar(menuBar);
		
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	@Override
	public void windowOpened(WindowEvent e){}
	@Override
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
	@Override
	public void windowClosed(WindowEvent e){}
	@Override
	public void windowIconified(WindowEvent e){}
	@Override
	public void windowDeiconified(WindowEvent e){}
	@Override
	public void windowActivated(WindowEvent e){}
	@Override
	public void windowDeactivated(WindowEvent e){}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Nuevo Usuario
		if(e.getSource().equals(mniNuevoUsuario))
		{
			new NuevoUsuario();
		}
	}
}
