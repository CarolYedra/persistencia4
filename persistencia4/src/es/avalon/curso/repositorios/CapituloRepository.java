package es.avalon.curso.repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.avalon.curso.negocio.Capitulo;
import es.avalon.curso.negocio.Libro;
import es.avalon.utilidades.persistencia.DBHelper;

public class CapituloRepository {
	
	// BUSCAR TODOS
		public static List<Capitulo> buscarTodos() {

			List<Capitulo> capitulos = new ArrayList<Capitulo>();
			ResultSet rs = null;
			try (Connection conexion = DBHelper.crearConexion(); Statement sentencia = DBHelper.crearStatement(conexion);) {
				String sql = "select * from capitulo";
				rs = sentencia.executeQuery(sql);
				while (rs.next()) {
					String tituloCap = rs.getString("titulo");
					int paginas = rs.getInt("paginas");
					String Libro_Titulo = rs.getString("Libro_Titulo");
					Capitulo c = new Capitulo(tituloCap, paginas, Libro_Titulo);
					capitulos.add(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return capitulos;

		}
	//MOSTAR CAPITULOS	

		public static List<Capitulo> buscarTodosCapitulos(String libroTitulo) {
			System.out.println(libroTitulo);
			List<Capitulo> capitulos = new ArrayList<Capitulo>();
			ResultSet rs = null;
			try (Connection conexion = DBHelper.crearConexion(); Statement sentencia = DBHelper.crearStatement(conexion);) {
				String sql = "select * from capitulo where libro_Titulo='" + libroTitulo + "'";
				rs = sentencia.executeQuery(sql);
				while (rs.next()) {
					String tituloCap = rs.getString("titulo");
					int paginas = rs.getInt("paginas");
					String Libro_Titulo = rs.getString("Libro_Titulo");
					Capitulo c = new Capitulo(tituloCap, paginas, Libro_Titulo);
					capitulos.add(c);
				}
				System.out.println(capitulos.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return capitulos;

		}

	// INSERTAR 
		public void insertar(Capitulo capitulo) {
			String sql = "insert into Capitulo (titulo, paginas, Libro_Titulo ) values (?,?,?)";
			try (Connection conexion = DBHelper.crearConexion();
					PreparedStatement sentencia = DBHelper.crearPreparedStatement(conexion, sql);) {
				sentencia.setString(1, capitulo.getTitulo());
				sentencia.setInt(2, capitulo.getPaginas());
				sentencia.setString(3, capitulo.getLibro_Titulo());
				sentencia.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

	// BORRAR 
		public static void borrar(Capitulo capitulo) throws ClassNotFoundException {
			String sql = "DELETE FROM Capitulo where titulo = ?";
			try (Connection conexion = DBHelper.crearConexion();
					PreparedStatement sentencia = DBHelper.borrarPreparedStatement(conexion, sql);) {
				sentencia.setString(1, capitulo.getTitulo());
				sentencia.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}}
			
		
	//ACTUALIZAR 
			public void salvar(Capitulo capitulo) throws ClassNotFoundException {
				System.out.println(capitulo.getTitulo() + " " + capitulo.getPaginas() + " " + capitulo.getLibro_Titulo());
				String sql = "update Capitulo set Libro_Titulo ='" + capitulo.getLibro_Titulo() + "', paginas=" + capitulo.getPaginas()
						+ " where titulo ='" + capitulo.getTitulo() + "'";
				}
			

	//BUSCAR UNO 
			public static Capitulo buscarUno(String titulo) throws ClassNotFoundException {
				Capitulo c = null;
				String sql = "select * FROM Capitulo  WHERE titulo ='" + titulo + "'";
				System.out.println(sql);
				try (Connection conexion = DBHelper.crearConexion();
						Statement sentencia = DBHelper.crearStatement(conexion);
						ResultSet rs = sentencia.executeQuery(sql)) {
					rs.next();
					c = new Capitulo(rs.getString("titulo"), rs.getInt("paginas"), rs.getString("Libro_Titulo"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return c;
			}

		//BUSCAR POR MODELO 

			public static List<Capitulo> buscarTodosTitulo(String tituloCapitulo) {
				List<Capitulo> capitulos = new ArrayList<Capitulo>();
				ResultSet rs = null;
				try (Connection conexion = DBHelper.crearConexion(); Statement sentencia = DBHelper.crearStatement(conexion);) {
					String sql = "select * from Capitulo where titulo='" + tituloCapitulo + "'";
					rs = sentencia.executeQuery(sql);
					while (rs.next()) {
						
						String titulo = rs.getString("titulo");
						int paginas = rs.getInt("paginas");
						String Libro_Titulo = rs.getString("Libro_Titulo");
						Capitulo c = new Capitulo(titulo, paginas, Libro_Titulo);
						capitulos.add(c);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return capitulos;
			}
		//FILTRAR 

			public static List<Capitulo> filtrar(String filtro) {
				List<Capitulo> capitulos = new ArrayList<Capitulo>();
				ResultSet rs = null;
				try (Connection conexion = DBHelper.crearConexion(); Statement sentencia = DBHelper.crearStatement(conexion);) {
					String sql = "select * from Capitulo order by " + filtro;
					rs = sentencia.executeQuery(sql);
					while (rs.next()) {
						
						String titulo = rs.getString("titulo");
						int paginas = rs.getInt("paginas");
						String Libro_Titulo = rs.getString("Libro_Titulo");
						Capitulo c = new Capitulo(titulo, paginas, Libro_Titulo);
						capitulos.add(c);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return capitulos;
			}

		
		}


