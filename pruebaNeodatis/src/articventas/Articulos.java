package articventas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;


public class Articulos {
	private int codarti;
	private String denom; 
	private int stock; 
	private float pvp;
	
	private Set<Ventas> Compras;
	
	public Articulos(int codarti, String denom, int stock, float pvp, Set<Ventas> compras) {
		super();
		this.codarti = codarti;
		this.denom = denom;
		this.stock = stock;
		this.pvp = pvp;
		Compras = compras;
	}
	
	
	public Articulos(){}
	
	public Articulos(int codarti, String denom, int stock, float pvp) {
		super();
		this.codarti = codarti;
		this.denom = denom;
		this.stock = stock;
		this.pvp = pvp;
	}
	public int getCodarti() {
		return codarti;
	}
	public void setCodarti(int codarti) {
		this.codarti = codarti;
	}
	public String getDenom() {
		return denom;
	}
	public void setDenom(String denom) {
		this.denom = denom;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public float getPvp() {
		return pvp;
	}
	public void setPvp(float pvp) {
		this.pvp = pvp;
	}


	public Set<Ventas> getCompras() {
		return Compras;
	}


	public void setCompras(Set<Ventas> compras) {
		Compras = compras;
	} 
	
	public void datosVentas() {
		ODB odb = ODBFactory.open("ARTICVENTAS.DAT");
		Objects<Object> obj = odb.getObjects(Articulos.class);
		odb.close();
		while(obj.hasNext()){
			try {
	    	  Articulos art = (Articulos) obj.next();
	    	  Iterator<Ventas> it = art.getCompras().iterator();
	    		  while(it.hasNext()){
	    			  Ventas ven = it.next();
	    			  float importe = art.getPvp()*ven.getUniven();
	    			  System.out.printf(" Venta  %s: %s, %s, %s, %s, %s, %s, %s %n",
	    					  ven.getCodventa(), art.getCodarti(), art.getDenom(), ven.getNumcli().getNumcli(),
	    					  ven.getNumcli().getNombre(), ven.getFecha(), ven.getUniven(), importe
	    					  );
	    		  }
	    	  } catch (NullPointerException e) {
	    		  //System.out.println(e.getMessage());
	    	  }
	      }	
	}
	
	public void ventasArticulos() {
		ODB odb = ODBFactory.open("ARTICVENTAS.DAT");
		Objects<Object> obj = odb.getObjects(Articulos.class);
		odb.close();
		while(obj.hasNext()){
			try {
	    	  Articulos art = (Articulos) obj.next();
	    	  Iterator<Ventas> it = art.getCompras().iterator();
	    	  int sumVentas = 0;
	    	  float importe = 0;
	    	  int numVentas = art.getCompras().size();
	    		  while(it.hasNext()){
	    			  Ventas ven = it.next();
	    			  sumVentas += ven.getUniven();
	    			  importe = art.getPvp()*ven.getUniven();
	    		  }
	    		  System.out.println(" Articulo "+art.getCodarti()+": "+sumVentas+", "+importe+", "+numVentas);
	    	  } catch (NullPointerException e) {
	    		  //System.out.println(e.getMessage());
	    	  }
	      }
	}
	
	public void listaClientes() {
		ODB odb = ODBFactory.open("ARTICVENTAS.DAT");
		Objects<Object> obj1 = odb.getObjects(Clientes.class);
		while(obj1.hasNext()){
			Objects<Object> obj2 = odb.getObjects(Articulos.class);
			Clientes cli = (Clientes) obj1.next();
			int numVentas = 0;
			float totalImporte = 0;
	    	  while(obj2.hasNext()){
	    		  Articulos art = (Articulos) obj2.next();
	    		  try {
	    			  Iterator<Ventas> it = art.getCompras().iterator();
		    		  while(it.hasNext()){
		    			  Ventas ven = it.next();
		    			  if(ven.getNumcli().equals(cli)) {
		    				  numVentas += 1;
		    				  totalImporte += art.getPvp()*ven.getUniven();
		    			  }
		    		  }
	    	    	  } catch (NullPointerException e) {
	    	    		//System.out.println(e.getMessage());
	    	    	  }
	    		  }
	    	  System.out.printf(" Cliente  %s: %s, %s, %s, %s %n", 
	    			  cli.getNumcli(), cli.getNombre(), cli.getPobla(), totalImporte, numVentas);
	    	  }
		odb.close();
		}
	
	public void clienteMayorGasto() {
		ODB odb = ODBFactory.open("ARTICVENTAS.DAT");
		Objects<Object> obj1 = odb.getObjects(Clientes.class);
		Clientes bingo = null;
		float mayorTotalImprte = 0;
		while(obj1.hasNext()){
			float totalImporte = 0;
			Objects<Object> obj2 = odb.getObjects(Articulos.class);
			Clientes cli = (Clientes) obj1.next();
	    	  while(obj2.hasNext()){
	    		  Articulos art = (Articulos) obj2.next();
	    		  try {
	    			  Iterator<Ventas> it = art.getCompras().iterator();
		    		  while(it.hasNext()){
		    			  Ventas ven = it.next();
		    			  if(ven.getNumcli().equals(cli)) {
		    				  totalImporte += art.getPvp()*ven.getUniven();
		    			  }
		    		  }
	    	    	  } catch (NullPointerException e) {
	    	    		//System.out.println(e.getMessage());
	    	    	  }
	    		  }
	    	  if(totalImporte > mayorTotalImprte) {
	    		  mayorTotalImprte = totalImporte;
	    		  bingo = cli;
	    		  }
	    	  }
		System.out.println(bingo.getNombre());
		odb.close();
		}
	
	public void clienteMayorVentas() {
		ODB odb = ODBFactory.open("ARTICVENTAS.DAT");
		Objects<Object> obj1 = odb.getObjects(Clientes.class);
		Clientes bingo = null;
		int mayorNumVentas = 0;
		while(obj1.hasNext()){
			int numVentas = 0;
			Objects<Object> obj2 = odb.getObjects(Articulos.class);
			Clientes cli = (Clientes) obj1.next();
	    	  while(obj2.hasNext()){
	    		  Articulos art = (Articulos) obj2.next();
	    		  try {
	    			  Iterator<Ventas> it = art.getCompras().iterator();
		    		  while(it.hasNext()){
		    			  Ventas ven = it.next();
		    			  if(ven.getNumcli().equals(cli)) {
		    				  numVentas += 1;
		    			  }
		    		  }
	    	    	  } catch (NullPointerException e) {
	    	    		//System.out.println(e.getMessage());
	    	    	  }
	    		  }
	    	  if(numVentas > mayorNumVentas) {
	    		  mayorNumVentas = numVentas;
	    		  bingo = cli;
	    		  }
	    	  }
		System.out.println(bingo.getNombre());
		odb.close();
		}
	
	public void articuloMasVendido() {
		ODB odb = ODBFactory.open("ARTICVENTAS.DAT");
		Objects<Object> obj = odb.getObjects(Articulos.class);
		odb.close();
		int mayorNumVentas = 0;
		String nombre = null;
		while(obj.hasNext()){
			Articulos art = (Articulos) obj.next();
			int numVentas = 0;
			try {
	    	  Iterator<Ventas> it = art.getCompras().iterator();
	    		  while(it.hasNext()){
	    			  Ventas ven = it.next();
	    			  numVentas += ven.getUniven();
	    		  }
	    	  } catch (NullPointerException e) {
	    		  //System.out.println(e.getMessage());
	    	  }
			if(numVentas > mayorNumVentas) {
	    		  mayorNumVentas = numVentas;
	    		  nombre = art.getDenom();
	    	  }
	      }
		System.out.println(nombre);
	}
	
	public void mediaImporteVentas() {
		ODB odb = ODBFactory.open("ARTICVENTAS.DAT");
		Objects<Object> obj = odb.getObjects(Articulos.class);
		odb.close();
		while(obj.hasNext()){
			try {
	    	  Articulos art = (Articulos) obj.next();
	    	  Iterator<Ventas> it = art.getCompras().iterator();
	    	  int sumVentas = 0;
	    	  float importe = 0;
	    	  int numVentas = art.getCompras().size();
	    	  float media = 0;
	    		  while(it.hasNext()){
	    			  Ventas ven = it.next();
	    			  sumVentas += 1;
	    			  importe = art.getPvp()*ven.getUniven();
	    		  }
	    		  media = importe/sumVentas;
	    		  System.out.println("  Articulo "+art.getCodarti()+": "+media);
	    	  } catch (NullPointerException e) {
	    		  //System.out.println(e.getMessage());
	    	  }
	      }
	}
	
}


