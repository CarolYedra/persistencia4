package es.avalon.curso.repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.avalon.curso.negocio.Libro;
import es.avalon.utilidades.persistencia.DBHelper;

public class LibroRepository {

//BUSCAR TODOS
	public  List<Libro> buscarTodos() {

		List<Libro> libros = new ArrayList<Libro>();
		ResultSet rs = null;
		try (Connection conexion = DBHelper.crearConexion(); Statement sentencia = DBHelper.crearStatement(conexion);) {
			String sql = "select * from libro";
			rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				String autor = rs.getString("autor");
				String titulo = rs.getString("titulo");
				int pagina = rs.getInt("pagina");
				Libro l = new Libro(titulo, autor, pagina);
				libros.add(l);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return libros;

	}

//BUSCAR UNO
	public  Libro buscarUno(String titulo) throws ClassNotFoundException {
		Libro l = null;
		String sql = "select * FROM Libro  WHERE titulo ='" + titulo + "'";
		System.out.println(sql);
		try (Connection conexion = DBHelper.crearConexion();
				Statement sentencia = DBHelper.crearStatement(conexion);
				ResultSet rs = sentencia.executeQuery(sql)) {
			rs.next();
			l = new Libro(rs.getString("titulo"), rs.getString("autor"), rs.getInt("pagina"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

// INSERTAR
	public void insertar(Libro libro) {
		String sql = "insert into Libro (titulo, autor, pagina) values (?,?,?)";
		try (Connection conexion = DBHelper.crearConexion();
				PreparedStatement sentencia = DBHelper.crearPreparedStatement(conexion, sql);) {
			sentencia.setString(1, libro.getTitulo());
			sentencia.setString(2, libro.getAutor());
			sentencia.setInt(3, libro.getPagina());
			sentencia.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//BORRAR
	public static  void borrar(Libro libro) throws ClassNotFoundException {
		String sql = "DELETE FROM Libro where titulo = ?";
		try (Connection conexion = DBHelper.crearConexion();
				PreparedStatement sentencia = DBHelper.borrarPreparedStatement(conexion, sql);) {
			sentencia.setString(1, libro.getTitulo());
			sentencia.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//SALVAR
	public void salvar(Libro libro) throws ClassNotFoundException {
		System.out.println(libro.getAutor() + " " + libro.getTitulo() + " " + libro.getPagina());
		String sql = "update Libro set autor ='" + libro.getAutor() + "', pagina=" + libro.getPagina()
				+ " where titulo ='" + libro.getTitulo() + "'";
		try (Connection conexion = DBHelper.crearConexion(); Statement sentencia = DBHelper.crearStatement(conexion);) {
			sentencia.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//BUSCAR POR MODELO
	public  List<Libro> buscarTodosTitulo(String tituloLibro) {
		List<Libro> libros = new ArrayList<Libro>();
		ResultSet rs = null;
		try (Connection conexion = DBHelper.crearConexion(); Statement sentencia = DBHelper.crearStatement(conexion);) {
			String sql = "select * from Libro where titulo='" + tituloLibro + "'";
			rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				String autor = rs.getString("autor");
				String titulo = rs.getString("titulo");
				int pagina = rs.getInt("pagina");
				Libro l = new Libro(titulo, autor, pagina);
				libros.add(l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return libros;
	}

//FILTRAR
	public  List<Libro> filtrar(String filtro) {
			List<Libro> libros = new ArrayList<Libro>();
			ResultSet rs = null;
			try (Connection conexion = DBHelper.crearConexion(); Statement sentencia = DBHelper.crearStatement(conexion);) {
				String sql = "select * from Libro order by " + filtro;
				rs = sentencia.executeQuery(sql);
				while (rs.next()) {
					String autor = rs.getString("autor");
					String titulo = rs.getString("titulo");
					int pagina = rs.getInt("pagina");
					Libro l = new Libro(titulo, autor, pagina);
					libros.add(l);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return libros;
		}	
}
