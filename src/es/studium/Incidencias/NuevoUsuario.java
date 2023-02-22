package es.studium.Incidencias;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class NuevoUsuario implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Nuevo Usuario");
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);

	Label lblTitulo = new Label("------- Alta de Usuario -------");
	Label lblNombre = new Label("Nombre:");
	Label lblClave = new Label("Clave:");
	Label lblRepetir = new Label("Repetir Clave:");
	Label lblCorreo = new Label("Correo:");
	Label lblMensaje = new Label("Alta de Usuario Correcta");

	TextField txtNombre = new TextField(10);
	TextField txtClave = new TextField(10);
	TextField txtRepetir = new TextField(10);
	TextField txtCorreo = new TextField(10);

	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");

	Conexion conexion = new Conexion();

	NuevoUsuario()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setSize(220,220);
		ventana.addWindowListener(this);

		ventana.add(lblTitulo);
		ventana.add(lblNombre);
		ventana.add(txtNombre);
		ventana.add(lblClave);
		txtClave.setEchoChar('*');
		ventana.add(txtClave);
		ventana.add(lblRepetir);
		txtRepetir.setEchoChar('*');
		ventana.add(txtRepetir);
		ventana.add(lblCorreo);
		ventana.add(txtCorreo);

		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);

		ventana.add(btnAceptar);
		ventana.add(btnCancelar);

		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e){}
	@Override
	public void windowClosing(WindowEvent e)
	{
		if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
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
		// Nuevo Usuario
		if(e.getSource().equals(btnAceptar))
		{
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(170,100);
			dlgMensaje.addWindowListener(this);
			if(txtNombre.getText().length()==0||txtClave.getText().length()==0||txtRepetir.getText().length()==0||txtCorreo.getText().length()==0)
			{
				lblMensaje.setText("Los campos están vacíos");
			}
			// Comprobar las claves
			else if(!txtClave.getText().equals(txtRepetir.getText()))
			{
				lblMensaje.setText("Las claves no coinciden");
			}
			else
			{
				// Dar de alta
				String sentencia = "INSERT INTO usuarios VALUES (null, '"+txtNombre.getText()+"', SHA2('"+txtClave.getText()+"',256), '"+txtCorreo.getText()+"');";
				int respuesta = conexion.altaUsuario(sentencia);
				if(respuesta!=0)
				{
					// Mostrar Mensaje Error
					lblMensaje.setText("Error en Alta");
				}
			}

			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		else if(e.getSource().equals(btnCancelar))
		{
			txtNombre.setText("");
			txtClave.setText("");
			txtRepetir.setText("");
			txtCorreo.setText("");
			txtNombre.requestFocus();
		}
	}
}
