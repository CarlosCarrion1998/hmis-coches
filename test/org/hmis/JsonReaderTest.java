package org.hmis;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class JsonReaderTest {

	@ParameterizedTest
	@CsvSource({"data/coches.json"})
	void testLeerCochesJSON(String ruta) {
		Coche [] coches = JsonReader.leerCochesJSON(ruta);
		assertEquals (4, coches.length);
	}

	@ParameterizedTest
	@CsvSource({"data/coches.json"})
	void testLeerCochesJSONprimero(String ruta) {
		Coche primero = new Coche ();
		
		Coche [] coches = JsonReader.leerCochesJSON(ruta);
		primero.setMarca(coches[0].getMarca());
		primero.setModelo(coches[0].getModelo());
		primero.setAño(coches[0].getAño());
		primero.setPrecio(coches[0].getPrecio());
		assertEquals(primero, coches[0]);
		assertEquals(primero.toString(), coches[0].toString());
		assertTrue (primero.equals(coches[0]));
		assertTrue (coches[0].equals(primero));
	}
	
	@ParameterizedTest
	@CsvSource({"Toyota, Prius, 2020, 20000"})
	void testInicializarCoches(String marca, String modelo, String año, String precio) {
		Coche toyota = new Coche(marca, modelo, Integer.parseInt(año), Integer.parseInt(precio));
		Coche toyotaIgual = new Coche("Toyota", "Prius", 2020, 20000);
		
		assertEquals(toyota, toyotaIgual);
	}
	
	@ParameterizedTest
	@MethodSource("datosDePrueba")
	void testEquals(Coche coche1, Coche coche2) {
		Coche toyota = new Coche(coche1.getMarca(), coche1.getModelo(), coche1.getAño(), coche1.getPrecio());
		JsonReader falloClase = new JsonReader();
		assertTrue(toyota.equals(toyota));
		assertFalse(toyota.equals(null));
		assertFalse(toyota.equals(falloClase));
		assertFalse(toyota.equals(coche2));
		
		
	}
	
	@ParameterizedTest
	@CsvSource({"rutaCochesMal"})
	void testFalloJSON(String rutaMala) {
		JsonReader.leerCochesJSON(rutaMala);
	}
	
	@Test
	void testMain() {
		Main main = new Main();
		Main.main(null);
		assertNotNull(main);
	}
	
	private static Stream<Arguments> datosDePrueba() {
	    Coche coche1 = new Coche("Toyota", "Prius", 2020, 20000);
	    Coche coche2 = new Coche("Hyundai", "i20", 2020, 20000);
	    
	    Coche coche3 = new Coche("Hyundai", "Prius", 2020, 20000);
	    
	    Coche coche4 = new Coche("Toyota", "Prius", 2020, 10000);
	    
	    Coche coche5 = new Coche("Toyota", "Prius", 2021, 20000);
	    
	    Coche coche6 = new Coche("Hyundai", "i20", 2022, 10000);
	    Coche coche7 = new Coche("Hyundai", "i20", 2022, 20000);
	    
	    Coche coche8 = new Coche("Toyota", "Saxo", 2020, 20000);
	    
	    return Stream.of(
	        Arguments.of(coche1, coche2),
	        Arguments.of(coche1, coche3),
	        Arguments.of(coche1, coche4),
	        Arguments.of(coche1, coche5),
	        Arguments.of(coche1, coche6),
	        Arguments.of(coche1, coche7),
	        Arguments.of(coche1, coche8)
	    );
	}
	
	


}
