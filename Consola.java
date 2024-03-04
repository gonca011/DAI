package Frontend;

public class Consola {
    private int pontosPorKm;
    private int pontosPorViagem;
    private int viagensRealizadas;
    private String promocoes;
    private double kmPercorridos;
    private int moedas;

    public Consola(int pontosPorKm, int pontosPorViagem, int viagensRealizadas, String promocoes, double kmPercorridos, int moedas) {
        this.pontosPorKm = pontosPorKm;
        this.pontosPorViagem = pontosPorViagem;
        this.viagensRealizadas = viagensRealizadas;
        this.promocoes = promocoes;
        this.kmPercorridos = kmPercorridos;
        this.moedas = moedas;
    }

    public void setPontosPorKm(int pontosPorKm) {
        this.pontosPorKm = pontosPorKm;
    }

    public int getPontosPorViagem() {
        return pontosPorViagem;
    }

    public void setPontosPorViagem(int pontosPorViagem) {
        this.pontosPorViagem = pontosPorViagem;
    }

    public int getViagensRealizadas() {
        return viagensRealizadas;
    }

    public void setViagensRealizadas(int viagensRealizadas) {
        this.viagensRealizadas = viagensRealizadas;
    }

    public String getPromocoes() {
        return promocoes;
    }

    public void setPromocoes(String promocoes) {
        this.promocoes = promocoes;
    }

    public double getKmPercorridos() {
        return kmPercorridos;
    }

    public void setKmPercorridos(double kmPercorridos) {
        this.kmPercorridos = kmPercorridos;
    }

    public int getMoedas() {
        return moedas;
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }

    public double calcularPontosPorViagens() {
        return viagensRealizadas * pontosPorViagem;
    }

    public double calcularPontosPorKm() {
        return kmPercorridos * pontosPorKm;
    }
    
    public int calcularMoedas(){
        int resposta = (int) (calcularPontosPorViagens() + calcularPontosPorKm());
        moedas += resposta;
        return resposta;
    }

    public int descontarMoedas(int moedaDescontada){
        moedas -= moedaDescontada;
        return moedas;
    }

    @Override
    public String toString() {
        return "pontosPorKm=" + pontosPorKm +
            ", pontosPorViagem=" + pontosPorViagem +
            ", viagensRealizadas=" + viagensRealizadas +
            ", promocoes='" + promocoes + '\'' +
            ", kmPercorridos=" + kmPercorridos +
            ", moedas=" + moedas;

    }

}
