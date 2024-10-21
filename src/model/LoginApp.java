package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginApp extends JFrame {// CREAR INTERFAZ PARA LOGIN Y REGISTER

	private JTextField username = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JButton login = new JButton("Login");
	private JButton register = new JButton("Register");

	public LoginApp() {

		setTitle("- Login -");// CARACTERISTICAS DE LA VENTANA
		setSize(343, 210);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);

		// TEXTO PARA MOSTRAR = USUARIO of JLABEL
		JLabel usuario = new JLabel("Usuario");
		usuario.setBounds(10, 10, 80, 25);
		add(usuario);

		// CASILLA PARA MOSTRAR EL CAMPO PARA INTRODUCIR EL USERNAME DEL USUARIO of
		// FIELD
		username.setBounds(85, 10, 160, 25);
		add(username);

		// TEXTO PARA MOSTRAR = CONTRASEÑA of JLABEL
		JLabel contraseña = new JLabel("Contraseña");
		contraseña.setBounds(10, 40, 80, 25);
		add(contraseña);

		// CASILLA PARA MOSTRAR EL CAMPO PARA INTRODUCIR EL PASSWORD DEL USUARIO of
		// FIELD

		password.setBounds(85, 40, 160, 25);
		add(password);

		// BOTONES PARA INICIAR SESION Y REGISTRARSE (Sin action)
		login.setBounds(70, 80, 85, 25);
		add(login);

		register.setBounds(175, 80, 85, 25);
		add(register);

		// TODO METODO PARA INICIAR SESION LOGIN

		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String nombre = username.getText();
				String contraseña = new String(password.getPassword());

				try (Connection conexion = ConnectionToDataBase.conectar()) {

					String consulta = "SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ?";
					PreparedStatement statement = conexion.prepareStatement(consulta);
					statement.setString(1, nombre);
					statement.setString(2, contraseña);

					ResultSet resultado = statement.executeQuery();
					if (resultado.next()) {
						JOptionPane.showMessageDialog(null, "Logueado correctamente");

					} else {
						JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
					}
				} catch (SQLException i) {
					i.printStackTrace();
					JOptionPane.showMessageDialog(null, "Credenciales incorrectas, el usuario no existe");// o,Ó
				}
			}
		});

		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = username.getText();
				String contraseña = new String(password.getPassword());

				try (Connection conexion = ConnectionToDataBase.conectar()) {

					String consulta = "INSERT INTO usuarios (nombre,contraseña) VALUES (?, ?)";
					PreparedStatement statement = conexion.prepareStatement(consulta);
					statement.setString(1, usuario);
					statement.setString(2, contraseña);
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Usuario registrado con éxito");

				} catch (SQLException e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al registrar usuario, intenta de nuevo");
				}
			}
		});
	} // ESTE LOGIN ES ELEGANTE Y DOMINANTE, HACE LAS FUNCIONES BÁSICAS DE LOGGEAR Y
		// REGISTRAR USUARIOS, Y ALMACENARLOS EN LA BASE DE DATOS POSTGRESQL
		// NO ME QUIERO QUEDAR ATRAPADO EN SWING MUCHO TIEMPO, COMPRENDEDÉ TECNICAS MÁS
		// PODEROSAS, PASEMOS A Web...
}
