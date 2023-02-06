package test.modelo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import repoblacion.modelo.Arbol;
import repoblacion.modelo.Especie;
import repoblacion.modelo.Posicion;

public class ArbolTest {
	
	private static final String EXCEPCION_MENSAJE_ADECUADO = "Debería haber saltado una excepción con el mensaje adecuado.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String ERROR_ESPECIE_NULA = "ERROR: La especie no puede ser nula.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto árbol.";
	private static final String ERROR_POSICION_NULA = "ERROR: La posición no puede ser nula.";
	private static final String ARBOL_NULO = "Debería haber saltado una excepción indicando que no se puede copiar un árbol nulo.";
	private static final String ERROR_COPIAR_ARBOL_NULO = "ERROR: No se puede copiar un árbol nulo.";
	private static final String POSICION_X_NO_ESPERADA="La posición de la coordenada X no es la esperada.";
	private static final String POSICION_Y_NO_ESPERADA="La posición de la coordenada Y no es la esperada.";
	private static final String ESPECIE_NO_ESPERADA="La especie del árbol no es la esperada.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";

	
	@Test
	public void constructorNoValidaEspecieNoValidaPosicionLanzaExcepcion() {
		Arbol arbol = null;
		try {
			arbol = new Arbol(null, new Posicion(5.0,5.0));
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (NullPointerException e) {
			assertEquals(ERROR_ESPECIE_NULA, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, arbol, OBJETO_DEBERIA_SER_NULO);
		}
		catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			arbol = new Arbol(Especie.ALAMO, null);
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (NullPointerException e) {
			assertEquals(ERROR_POSICION_NULA, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, arbol, OBJETO_DEBERIA_SER_NULO);
		}
		catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			arbol = new Arbol(null, null);
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (NullPointerException e) {
			assertEquals(ERROR_POSICION_NULA, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(ERROR_ESPECIE_NULA, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, arbol, OBJETO_DEBERIA_SER_NULO);
		}
		catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorArbolNuloLanzaExcepcion() {
		Arbol arbol = null;
		
		try {
			arbol = new Arbol(null);
			fail(ARBOL_NULO);
		} catch (NullPointerException e) {
			assertEquals(ERROR_COPIAR_ARBOL_NULO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, arbol, OBJETO_DEBERIA_SER_NULO);
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorEspecieCorrectaPosicionCorrectaCreaArbol() {
		Arbol arbol = null;
		
		arbol = new Arbol(Especie.ALAMO,new Posicion(5.0,6.0));
		assertEquals(Especie.ALAMO, arbol.getEspecie(), ESPECIE_NO_ESPERADA);
		assertEquals(5.0, arbol.getPosicion().getX(), POSICION_X_NO_ESPERADA);
		assertEquals(6.0, arbol.getPosicion().getY(), POSICION_Y_NO_ESPERADA);
	}
	
	@Test
	public void toStringDevuelveLaCadenaEsperada() {
		Arbol arbol= new Arbol (Especie.ALAMO,new Posicion(5.0,5.0));
		assertEquals(String.format("especie=%s, posicion=(%s)", arbol.getEspecie(),arbol.getPosicion()), arbol.toString(), CADENA_NO_ESPERADA);
	}
}
