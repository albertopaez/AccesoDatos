package clases;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

import clases.Jugadores;

public class EjemplosObjectVAlues2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Paises p1 = new Paises("ESPAÑA", 1);
		Paises p2 = new Paises("INGLATERRA", 2);
		Paises p3 = new Paises();
		
		Jugadores j1 = new Jugadores("Maria", "voleibol", "Madrid", 14, p1);
	    Jugadores j2 = new Jugadores("Miguel", "tenis", "Madrid", 15, p1);
	    Jugadores j3 = new Jugadores("Mario", "baloncesto", "Guadalajara", 15, p1);
	    Jugadores j4 = new Jugadores("Alicia", "tenis", "Madrid", 14, p1);
	    
	    System.out.println(j1.getPais().getNombrepais());
		
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		odb.store(p1);
		odb.store(p2);
		odb.store(p3);
		
		odb.store(j1); 
	    odb.store(j2);
	    odb.store(j3);
	    odb.store(j4);
	    
		odb.close();
		*/

		visualizarmediadeedad();
		System.out.println("-----------------------------------------------------");
		JugadoresPaises();
		System.out.println("-----------------------------------------------------");
		JugadoresPaisesSpain();
		System.out.println("-----------------------------------------------------");
		contadorymediaporpais();
	}

	private static void JugadoresPaises() {
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		Values valores = odb.getValues(
				new ValuesCriteriaQuery(Jugadores.class).field("nombre").field("edad").field("pais.nombrepais"));

		while (valores.hasNext()) {
			ObjectValues objectValues = (ObjectValues) valores.next();
			System.out.printf("Nombre: %s, Edad: %d, Pais: %s %n", objectValues.getByAlias("nombre"),
					objectValues.getByIndex(1), objectValues.getByIndex(2));

		}
		odb.close();

	}

	private static void JugadoresPaisesSpain() {
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		
		Values valores = odb.getValues(new ValuesCriteriaQuery(
				 Jugadores.class,
				 new And().add(Where.equal("pais.nombrepais","ESPAÑA"))
					 .add(Where.equal("edad",15))
					)
				.field("nombre")
				.field("ciudad"));

		while (valores.hasNext()) {
			ObjectValues objectValues = (ObjectValues) valores.next();
			System.out.printf("Nombre: %s, Ciudad: %s %n",
					objectValues.getByAlias("nombre"),
					objectValues.getByIndex(1));

		}
		odb.close();

	}
	private static void visualizarmediadeedad() {
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		Values val;
		ObjectValues ov;
		try {
			val = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).avg("edad"));
			ov = val.nextValues();
			System.out.printf("AVG-La media de edad es: %f %n", ov.getByIndex(0));
			
			Values val2 = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).sum("edad").count("edad"));
			ObjectValues ov2 = val2.nextValues();
			float media;
			BigDecimal sumaedad = (BigDecimal) ov2.getByIndex(0);
			BigInteger cuenta = (BigInteger) ov2.getByIndex(1);
			media = sumaedad.floatValue() / cuenta.floatValue();

			System.out.printf("La media de edad es: %.2f Contador = %d  " + "suma = %.2f %n", media, cuenta, sumaedad);


		} catch (ArithmeticException e) {
			System.out.println(e.getMessage());

		}

		odb.close();
	}//
	
	private static void contadorymediaporpais() {
		   ODB odb = ODBFactory.open("EQUIPOS.DB");
		   System.out.println("Numero de jugadores por país, "+
		          " max de edad y media de edad: ");
		   Values groupby = odb.getValues(new ValuesCriteriaQuery(
		         Jugadores.class,Where.isNotNull("pais.nombrepais"))
				 .field("pais.nombrepais").count("nombre")
		              .max("edad").sum("edad").groupBy("pais.nombrepais")  );
		  if (groupby.size() == 0)
			System.out.println( " La consulta no devuelve datos. ");
		  else
		  { 
			while(groupby.hasNext()) {
		      ObjectValues objetos= (ObjectValues) groupby.next();     
		      float media = ((BigDecimal) objetos.getByIndex(3)).floatValue() /
			    	 ((BigInteger) objetos.getByIndex(1)).floatValue();
		    
		      System.out.printf("Pais: %-8s Num jugadores: %d,  Edad Máxima: %.0f, "
		    		+ "Suma de Edad: %.0f, Edad media: %.2f %n",
		    		objetos.getByAlias("pais.nombrepais"),
			        objetos.getByIndex(1),
			        objetos.getByIndex(2), 
			        objetos.getByIndex(3), media );     
		    }	
		  }
		  odb.close();
		}//


}
