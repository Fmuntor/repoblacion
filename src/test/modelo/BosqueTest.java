package test.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import repoblacion.modelo.Bosque;

public class BosqueTest {
	
	private static final String EXCEPCION_MENSAJE_ADECUADO = "Debería haber saltado una excepción con el mensaje adecuado.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String ERROR_ANCHO_NO_VALIDO = "ERROR: Anchura no válida.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto árbol.";
	private static final String ERROR_ALTO_NO_VALIDO="ERROR: Altura no válida.";
	private static final String ERROR_POBLACION_NO_VALIDA="ERROR: La población debe ser mayor que cero.";
	private static final String ERROR_POBLACION_SUPERA_PERIMETRO="ERROR: La población no puede superar el perímetro del bosque.";
	private static final String POBLACION_NO_VALIDA="ERROR: El valor de la población no es el esperado.";
	private static final String ANCHO_NO_VALIDO="ERROR: El valor del ancho del bosque no es el esperado.";
	private static final String ALTO_NO_VALIDO="ERROR: El valor del alto del bosque no es el esperado.";
	

	
	@Test
	public void constructorNoValidaAnchoAltoPoblacionLanzaExcepcion() {
		Bosque bosque = null;
		int anchoMinNoValido=5, anchoMaxNoValido=1500;
		int altoMinNoValido=5, altoMaxNoValido=600;
		int ancho=20,alto=20;
		int poblacionMinNoValida=-1, poblacionMaxNoValida=90, poblacionValida=70;
		
		try {
			bosque = new Bosque(anchoMinNoValido,alto, poblacionMaxNoValida );
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_ANCHO_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, bosque, OBJETO_DEBERIA_SER_NULO);
		}
		catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			bosque = new Bosque(anchoMaxNoValido,alto, poblacionMaxNoValida );
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_ANCHO_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, bosque, OBJETO_DEBERIA_SER_NULO);
		}
		catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			bosque = new Bosque(ancho,altoMinNoValido, poblacionMaxNoValida );
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_ALTO_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, bosque, OBJETO_DEBERIA_SER_NULO);
		}
		catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			bosque = new Bosque(ancho,altoMaxNoValido, poblacionMaxNoValida );
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_ALTO_NO_VALIDO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, bosque, OBJETO_DEBERIA_SER_NULO);
		}
		catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			bosque = new Bosque(ancho,alto, poblacionMinNoValida);
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_POBLACION_NO_VALIDA, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, bosque, OBJETO_DEBERIA_SER_NULO);
		}
		catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
		try {
			bosque = new Bosque(ancho,alto, poblacionMaxNoValida);
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (IllegalArgumentException e) {
			assertEquals(ERROR_POBLACION_SUPERA_PERIMETRO, e.getMessage(), MENSAJE_NO_CORRECTO);
			assertEquals(null, bosque, OBJETO_DEBERIA_SER_NULO);
		}
		catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	
	
	@Test
	public void constructorAnchoValidoAltoValidoPoblacionValidaCreaBosque() {
		Bosque bosque = null;
		
		bosque = new Bosque(20,30,70);
		assertEquals(70, bosque.duplicaBosque().length, POBLACION_NO_VALIDA);
		assertEquals(20, bosque.getAncho(), ANCHO_NO_VALIDO);
		assertEquals(30, bosque.getAlto(), ALTO_NO_VALIDO);
	}
	
	
}
