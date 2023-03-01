package es.studium.Incidencias;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ListadoUsuarios implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Listado Usuarios");

	TextArea txaListado = new TextArea(6, 25);
	Button btnPdf = new Button("PDF");

	Conexion conexion = new Conexion();

	ListadoUsuarios()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setSize(220,220);
		ventana.addWindowListener(this);
		
		// Rellenar el TextArea
		conexion.rellenarListadoUsuarios(txaListado);
		
		ventana.add(txaListado);
		ventana.add(btnPdf);

		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	@Override
	public void windowOpened(WindowEvent e){}
	@Override
	public void windowClosing(WindowEvent e)
	{

		ventana.setVisible(false);
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
	{}
}
