package org.ejemplos.appcilindro.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias de la clase cilindro (Modelo)
 * Cada metodo @Test verifica un solo comportamiento
 */
class CilindroTest {
    //Tolerancia al comparar doubles; los decimales rara vez
    //Coindiden exactamente asi que acepta una diferencia mínima
    private static final double DELTA = 0.0001;
    //Objecto base que se utiliza en las pruebas
    private Cilindro cil;

    /**
     * Se ejecuta antes de cada prueba. Crea un cilindro nuevo
     * para que una prueba no afecte a las demás.
     */
    @BeforeEach
    void setUp() {
        //Depósito de diametro 2 m (radio 1 m) y altura 3 m
        cil = new Cilindro(1.0, 3.0);
    }

    @Test
    void constructorVacio() {
        Cilindro vacio = new Cilindro();
        //assertEquals(esperado, obtenido, tolerancia)
        assertEquals(0.0, vacio.getRadio(), DELTA);
        assertEquals(0.0, vacio.getAltura(), DELTA);
        assertEquals(0.0, vacio.getArea(), DELTA);
        assertEquals(0.0, vacio.getVolumen(), DELTA);
    }

    //Verifica que el constructor con parametros guarde Radio y Altura
    @Test
    void constructorConParametros() {
        assertEquals(1.0, cil.getRadio(), DELTA);
        assertEquals(3.0, cil.getAltura(), DELTA);
    }

    @Test
        //Verifica que getRadio() devuelva el radio con el que se creó
    void getRadio() {
        assertEquals(1.0, cil.getRadio(), DELTA);
    }

    //Verifica que setRadio() cambie el radio y recalcule área y volumen
    @Test
    void setRadio() {
        cil.setRadio(2.0);
        assertEquals(2.0, cil.getRadio(), DELTA);
        assertEquals(2 * Math.PI * 2.0 * (2.0 + 3.0), cil.getArea(), DELTA);
        assertEquals(Math.PI * 2.0 * 2.0 * 3.0, cil.getVolumen(), DELTA);
    }

    //Verifica que getAltura() devuelva la altura con la que se creó.
    @Test
    void getAltura() {
        assertEquals(3.0, cil.getAltura(), DELTA);
    }

    //Verifica que setAltura() cambie la altura y recalcule área y volumen
    @Test
    void setAltura() {
        cil.setAltura(5.0);
        assertEquals(5.0, cil.getAltura(), DELTA);
        assertEquals(2 * Math.PI * 1.0 * (1.0 + 5.0), cil.getArea(), DELTA);
        assertEquals(Math.PI * 1.0 * 1.0 * 5.0, cil.getVolumen(), DELTA);
    }

    //Verifica la fórmula del área con el caso de la tabla(d=2,h=3)
    @Test
    void calcularArea() {
        assertEquals(25.1327, cil.calcularArea(), DELTA);
    }

    //Verifica la fórmula del volumen con el caso de la tabla(d=2,h=3)
    @Test
    void calcularVolumen() {
        assertEquals(9.4248, cil.calcularVolumen(), DELTA);
    }

    //Verifica que getArea() devuelva el área calculada al crear el objeto
    @Test
    void getArea() {
        assertEquals(2 * Math.PI * 1.0 * (1.0 + 3.0), cil.getArea(), DELTA);
    }
    //Verifica que getVolumen() devuelva el volumen calculado al crear el objeto
    @Test
    void getVolumen() {
        assertEquals(Math.PI * Math.pow(1.0,2)*3.0,cil.getVolumen(), DELTA);
    }
    @Test
    void casoDiametro6Altura4(){
        //La clase trabaja con radio, así que se convierte:r = d/2=3
        Cilindro deposito = new Cilindro(6.0/2,4.0);
        assertEquals(131.9469, deposito.getArea(), DELTA);
        assertEquals(113.0973,deposito.getVolumen(), DELTA);
    }

}