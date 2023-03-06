package es.studium.Incidencias;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class EliminarUsuario implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Baja Usuario");
	Dialog dlgSeguro = new Dialog(ventana, "�Segur@?", true);
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);
	
	Label lblElegir = new Label("Elegir el usuario a eliminar:");
	Label lblSeguro = new Label("�Seguro de eliminar ");
	Label lblMensaje = new Label("Usuario Eliminado");
	
	Choice choUsuarios = new Choice();
	
	Button btnEliminar = new Button("Eliminar");
	Button btnSi = new Button("S�");
	Button btnNo = new Button("No");
	
	Conexion conexion = new Conexion();
	
	EliminarUsuario()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setSize(220,220);
		ventana.addWindowListener(this);
		
		ventana.add(lblElegir);
		// Rellenar el Choice
		conexion.rellenarChoiceUsuarios(choUsuarios);
		ventana.add(choUsuarios);
		btnEliminar.addActionListener(this);
		ventana.add(btnEliminar);
		
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	
	@Override
	public void windowOpened(WindowEvent e){}
	@Override
	public void windowClosing(WindowEvent e)
	{
		if(dlgSeguro.isActive())
		{
			dlgSeguro.setVisible(false);
		}
		else if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
			dlgSeguro.setVisible(false);
			ventana.setVisible(false);
		}
		else
		{
			ventana.setVisible(false);
		}
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
		if(e.getSource().equals(btnEliminar))
		{
			if(choUsuarios.getSelectedIndex()!=0)
			{
				dlgSeguro.setLayout(new FlowLayout());
				dlgSeguro.setSize(170,100);
				dlgSeguro.addWindowListener(this);
				
				lblSeguro.setText("�Seguro de eliminar "+choUsuarios.getSelectedItem());
				dlgSeguro.add(lblSeguro);
				btnSi.addActionListener(this);
				dlgSeguro.add(btnSi);
				btnNo.addActionListener(this);
				dlgSeguro.add(btnNo);
				dlgSeguro.setResizable(false);
				dlgSeguro.setLocationRelativeTo(null);
				dlgSeguro.setVisible(true);
			}
		}
		else if(e.getSource().equals(btnNo))
		{
			dlgSeguro.setVisible(false);
		}
		else if(e.getSource().equals(btnSi))
		{
			String tabla[] = choUsuarios.getSelectedItem().split("-");
			int respuesta = conexion.eliminarUsuario(tabla[0]);
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(170,100);
			dlgMensaje.addWindowListener(this);
			if(respuesta==0)
			{
				lblMensaje.setText("Usuario Eliminado");
			}
			else
			{
				lblMensaje.setText("Error al eliminar");
			}
			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
	}
}
