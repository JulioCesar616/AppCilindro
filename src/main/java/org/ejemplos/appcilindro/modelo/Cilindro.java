package org.ejemplos.appcilindro.modelo;

public class Cilindro {
    //Creación de la variable Radio
    private double radio;
    //Creación de la variable Altura
    private double altura;
    //Creación de la variable Área
    private double area;
    //Creación de la variable Volumen
    private double volumen;

    /**
     * Este es el constructor el cual
     * inicializa objetos
     * Además puede utilizarse para dar valores iniciales
     * a los objetos
     * @param radio
     * @param altura
     */
    public Cilindro(double radio, double altura){
        this.radio = radio;
        this.altura = altura;
        actualizarCalculos();
    }
//Constructor vacío, creará un cilindro con medidas en cero
    public Cilindro() {
this(0,0);
    }
    //Setters

    /**
     * El set asigna el valor del radio
     * y recalcula área y volumen.
     * @param radio
     */
    public void setRadio(double radio){
        //"this se refiere a este objeto.
        this.radio = radio;
      actualizarCalculos();

    }
    //pone el valor a la altura
    public void setAltura(double altura){
        this.altura = altura;
       actualizarCalculos();

    }
    //Getters

    /**
     * El get regresa el valor de una variable
     * en este caso radio
     * @return radio
     */
    //GetRadio obtiene el valor del radio
    public double getRadio(){
        return radio;
    }

    /**
     * El get regresa el valor de una variable
     * en este caso Altura
     * @return altura
     */
    //GetAltura obtiene el valor de la altura
    public double getAltura(){
        return altura;
    }

    /**
     * El get regresa el valor de la variable Área
     * @return area
     */
    public double getArea(){
        return area;
    }

    /**
     * El get regresa el valor de
     * la variable Volumen
     * @return volumen
     */
    //GetVolumen obtiene el valor del volumen
    public double getVolumen(){
        return volumen;
    }
    /**
     * Calcula el área mediante la fórmula
     * @return 2*Math.PI*radio*(radio+altura)
     */
    public double calcularArea(){
        //A=2πr(r+h)
        return 2*Math.PI*radio*(radio+altura);
    }
    //Calcula el Volumen mediante la fórmula
    public double calcularVolumen(){
        //V = π r² h
        return Math.PI*Math.pow(radio,2)*altura;
    }
    //Mantendrá sincronizados los atributos calculados
    private void actualizarCalculos(){
        this.area = calcularArea();
        this.volumen = calcularVolumen();
    }

    /**
     * Aquí reescribimos el método para
     * que tenga un comportamiento distinto
     * en este caso para que nos entregue los datos de una manera más legible
     * @returnreturn String.format(" Cilindro[radio = % .4f m, altura = % .4f m, area = % 4f m ², volumen = % .4f m ³ ] ",
     *radio, altura, area, volumen)
     */
    @Override
    public String toString(){
        return String.format("Cilindro[radio=%.4f m, altura=%.4f m, area=%4f m², volumen=%.4f m³]",
                radio, altura, area, volumen);
    }
}

