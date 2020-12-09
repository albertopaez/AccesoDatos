package articventas;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

import articventas.Ventas;
import clases.Jugadores;

public class Principal {

	public static void main(String[] args) {
		crearbd();

	}

	/////////////////////////////////////////
	public static void crearbd() {
		// creación de registros en la BD
		File fichero = new File("ARTICVENTAS.DAT");
		if (fichero.delete())
			System.out.println("BD Borrada");
		ODB odb = ODBFactory.open("ARTICVENTAS.DAT");
		// creo los artículos
		Articulos ar1 = new Articulos(1, "Mesas", 30, (float) 100.5);
		Articulos ar2 = new Articulos(2, "Pupitres", 10, (float) 150.7);
		Articulos ar3 = new Articulos(6, "Cuadernos", 100, (float) 4.5);
		Articulos ar4 = new Articulos(8, "Tabletas", 10, (float) 175.4);
		Articulos ar5 = new Articulos(9, "Bolígrafos", 100, (float) 3.5);
		Articulos ar6 = new Articulos(10, "Lapiceros", 300, (float) 2.5);
		Articulos ar7 = new Articulos(14, "Sillas", 30, (float) 120.5);
		Articulos ar8 = new Articulos(16, "Portátil", 25, (float) 400.5);

		Articulos ar11 = new Articulos(17, "Espejo baño", 20, (float) 100.5);
		Articulos ar21 = new Articulos(18, "Reloj cocina", 10, (float) 20.7);
		Articulos ar31 = new Articulos(20, "Tarjetero", 50, (float) 14.5);
		Articulos ar41 = new Articulos(22, "Estuches", 110, (float) 20.4);
		Articulos ar51 = new Articulos(23, "Libro BD", 10, (float) 23.5);
		Articulos ar61 = new Articulos(24, "Tijeras", 30, (float) 5.0);
		Articulos ar71 = new Articulos(25, "Cubiertos", 130, (float) 10.5);
		Articulos ar81 = new Articulos(26, "Teclado", 25, (float) 40.5);

		// Almacenamos artículos 16 artículos
		odb.store(ar1);
		odb.store(ar2);
		odb.store(ar3);
		odb.store(ar4);
		odb.store(ar5);
		odb.store(ar6);
		odb.store(ar7);
		odb.store(ar8);
		odb.store(ar11);
		odb.store(ar21);
		odb.store(ar31);
		odb.store(ar41);
		odb.store(ar51);
		odb.store(ar61);
		odb.store(ar71);
		odb.store(ar81);

		// Creo los Clientes 12 clientes, num, nom, pob

		Clientes cli1 = new Clientes(1, "Antonio Ruiz", "Talavera");
		Clientes cli2 = new Clientes(2, "La Alameda S.L.", "Talavera");
		Clientes cli3 = new Clientes(7, "Los molinos CB", "Madrid");
		Clientes cli4 = new Clientes(8, "Pedro Morán S.L.", "Talavera");
		Clientes cli5 = new Clientes(12, "Azulejos Martín S.L.", "Talavera");
		Clientes cli6 = new Clientes(15, "Bar Girasol", "Oropesa");
		Clientes cli7 = new Clientes(9, "Escuela Mayores", "Talavera");
		Clientes cli8 = new Clientes(17, "Galería Madrid S.L.", "Madrid");
		Clientes cli9 = new Clientes(19, "El corte Chino", "Talavera");
		Clientes cli10 = new Clientes(20, "UNICAS S.A.", "Oropesa");
		Clientes cli11 = new Clientes(21, "Deportivo SAS", "Talavera");
		Clientes cli12 = new Clientes(22, "Academia Padel", "Madrid");

		odb.store(cli1);
		odb.store(cli2);
		odb.store(cli3);
		odb.store(cli4);
		odb.store(cli5);
		odb.store(cli6);
		odb.store(cli7);
		odb.store(cli8);
		odb.store(cli9);
		odb.store(cli10);
		odb.store(cli11);
		odb.store(cli12);

		// crear unas ventas

		// ventas para el artículo 1
		Ventas v1 = new Ventas(1, cli1, 5, "05/06/2014");
		Ventas v2 = new Ventas(2, cli2, 4, "15/06/2014");
		Ventas v3 = new Ventas(3, cli6, 3, "25/06/2014");

		Set<Ventas> vvvv = new HashSet<Ventas>();
		vvvv.add(v1);
		vvvv.add(v2);
		vvvv.add(v3);
		ar1.setCompras(vvvv);

		// ventas para el artículo 2
		Ventas v4 = new Ventas(4, cli6, 5, "03/07/2014");
		Ventas v5 = new Ventas(5, cli7, 4, "11/08/2014");

		Set<Ventas> vv2 = new HashSet<Ventas>();
		vv2.add(v4);
		vv2.add(v5);
		ar2.setCompras(vv2);
		// ventas para el artículo 3
		Ventas v6 = new Ventas(6, cli1, 3, "25/04/2014");
		Set<Ventas> vv3 = new HashSet<Ventas>();
		vv3.add(v6);
		ar3.setCompras(vv3);

		// ventas para el artículo 4
		Ventas v7 = new Ventas(7, cli10, 3, "15/03/2014");
		Ventas v8 = new Ventas(8, cli10, 4, "01/05/2014");
		Ventas v9 = new Ventas(9, cli11, 6, "25/04/2014");
		Ventas v10 = new Ventas(10, cli12, 2, "20/05/2014");
		Set<Ventas> vv4 = new HashSet<Ventas>();
		vv4.add(v7);
		vv4.add(v8);
		vv4.add(v9);
		vv4.add(v10);
		ar4.setCompras(vv4);

		// ventas para el artículo 5
		Ventas v11 = new Ventas(11, cli1, 1, "10/05/2014");
		Ventas v12 = new Ventas(12, cli7, 3, "01/06/2014");
		Ventas v13 = new Ventas(13, cli8, 6, "25/06/2014");
		Ventas v14 = new Ventas(14, cli9, 5, "10/07/2014");
		Ventas v15 = new Ventas(15, cli9, 4, "15/09/2014");
		Set<Ventas> vv5 = new HashSet<Ventas>();
		vv5.add(v11);
		vv5.add(v12);
		vv5.add(v13);
		vv5.add(v14);
		vv5.add(v15);
		ar5.setCompras(vv5);

		odb.store(v1);
		odb.store(v2);
		odb.store(v3);
		odb.store(v4);
		odb.store(v5);
		odb.store(v6);
		odb.store(v7);
		odb.store(v8);
		odb.store(v9);
		odb.store(v10);
		odb.store(v11);
		odb.store(v12);
		odb.store(v13);
		odb.store(v14);
		odb.store(v15);

		odb.store(ar1);
		odb.store(ar2);
		odb.store(ar3);
		odb.store(ar4);
		odb.store(ar5);
		
		// INSERCIONES MANUALES
		
		Objects<Object> objects1 = odb.getObjects(Articulos.class);
		
		Values val2 = odb.getValues(new ValuesCriteriaQuery(Articulos.class).sum("pvp"));
		ObjectValues ov2= val2.nextValues();
		BigDecimal value2 = (BigDecimal)ov2.getByAlias("pvp");
		
		ICriterion criterio3 = Where.equal("codarti",1);
		CriteriaQuery query3 = new CriteriaQuery(Articulos.class, criterio3);
		Objects<Articulos> objects3 = odb.getObjects(query3);

		

		odb.commit();

		odb.close();
		System.out.println("BASE DE DATOS CREADA");	
		
		System.out.println("1. Lista de todos los articulos");	
		int i = 1;
	      // visualizar los objetos		
	      while(objects1.hasNext()){
	    	  Articulos art1 = (Articulos) objects1.next();
	         System.out.printf(" %d: %s, %s, %s %n",
	      		   i++, art1.getCodarti(), art1.getDenom(),
	      		 art1.getStock(), art1.getPvp());      
	      }	
		
		System.out.println("2. Suma de precios de todos los articulos: "+value2+"$");
		
		System.out.println("3. Articulo con la id 1: ");
		int y = 1;
	      // visualizar los objetos		
	      while(objects3.hasNext()){
	    	  Articulos art3 = (Articulos) objects3.next();
	         System.out.printf(" %d: %s, %s, %s %n",
	      		   y++, art3.getCodarti(), art3.getDenom(),
	      		 art3.getStock(), art3.getPvp());      
	      }
	      
	      
	      System.out.println("Ejercicio a: Datos de ventas");	
	      ar1.datosVentas();
		
	      System.out.println("Ejercicio b: Ventas de articulos");	
	      ar1.ventasArticulos();
	      
	      System.out.println("Ejercicio c: Lista de clientes");	
	      ar1.listaClientes();
	      
	      System.out.println("Ejercicio d: Consultas varias");
	      System.out.print(" Consulta 1: ");
	      ar1.articuloMasVendido();
	      System.out.println(" Consulta 2: ");
	      ar1.mediaImporteVentas();
	      System.out.print(" Consulta 3: ");
	      ar1.clienteMayorGasto();
	      System.out.print(" Consulta 4: ");
	      ar1.clienteMayorVentas();
	}

}
