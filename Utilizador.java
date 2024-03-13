package Backend;

public class Utilizador {
    private String email;
    private String password;
    private int numMoedas;
    private int numViagens;
    private double saldo;
    private ListaPromocoes listaPromocoes;
    private ListaViagens listaViagensCompradas;
    private ListaViagens listaViagensRealizadas;

    public Utilizador(String email, String password, int numMoedas, int numViagens, double saldo) {
        this.email = email;
        this.password = password;
        this.numMoedas = numMoedas;
        this.numViagens = numViagens;
        this.saldo = saldo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumMoedas() {
        return numMoedas;
    }

    public void setNumMoedas(int numMoedas) {
        this.numMoedas = numMoedas;
    }

    public int getNumViagens() {
        return numViagens;
    }

    public void setNumViagens(int numViagens) {
        this.numViagens = numViagens;
    }

    public ListaPromocoes getListaPromocoes() {
        return listaPromocoes;
    }

    public void setListaPromocoes(ListaPromocoes listaPromocoes) {
        this.listaPromocoes = listaPromocoes;
    }

    public ListaViagens getListaViagensRealizadas() {
        return listaViagensRealizadas;
    }

    public void setListaViagensRealizadas(ListaViagens listaViagens) {
        this.listaViagensRealizadas = listaViagens;
    }

    public ListaViagens getListaViagensCompradas() {
        return listaViagensCompradas;
    }

    public void setListaViagensCompradas(ListaViagens listaViagens) {
        this.listaViagensCompradas = listaViagens;
    }

    private void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void mostrarTodasAsPromocoes(){
        for( Promocao p : listaPromocoes.getPromocoes()){
            System.out.println(p.toString());
        }
    }

    public void mostrarPromocoesDisponiveis(){
        for( Promocao p : listaPromocoes.getPromocoes()){
          Promocao pDisponivel = new Promocao(p.getDescricao(), p.getMoedasQueAtribui(), p.getDataInicioPromocao(), p.getDataFimPromocao(), p.getCondicoesKmPercorridos(), p.getCondicoesViagensRealizadas(), p.getCondicoesOrigem(), p.getCondicoesDestino()); 
        for( Viagem v : listaViagensRealizadas.getViagens()){
            if (listaViagensRealizadas != null){
                if(pDisponivel.validacao(v.getnKm(), listaViagensRealizadas.calcularNumViagens(), v.getOrigem(), v.getDestino()) && p.estaAtiva(v.getData())){
                    System.out.println(pDisponivel.toString());
                }else {
                    System.out.println("Não existem promoções que possa usar, tem de viajar mais e cumprir com os requisitos.");
                } 
            } else {
                System.out.println("Não existem viagens realizadas");
            }
        }
    }
}

    public void usarPromocao(){
        for( Promocao p : listaPromocoes.getPromocoes()){
            for(Viagem v : listaViagensRealizadas.getViagens()){
                if(p.estaAtiva(v.getData()) && p.validacao(v.getnKm(), listaViagensRealizadas.calcularNumViagens(), v.getOrigem(), v.getDestino())){
                    numMoedas += p.getMoedasQueAtribui();
                    listaPromocoes.removerPromocao(p);
                }
                else{
                    System.out.println("Promoção inválida");
                }
            }
        }
    }

    public void usarMoedas(int moedasGastas){
        numMoedas -= moedasGastas;
        System.out.println("Moedas restantes: " + numMoedas);
    }

    public int calcularMoedasPorPontos(){
        for(Viagem v : listaViagensRealizadas.getViagens()){
            numMoedas += v.getPontosPorViagem();
        }
        for(Viagem v : listaViagensRealizadas.getViagens()){
            numMoedas += v.getnKm() * v.getPontosPorKm();
        }
        return numMoedas;
    }

    public void verUltimosPontosGanhos(){
        int pontosGanhosPelaViagem = 0;
        int pontosGanhosPelosKm = 0;
        int pontosTotais = 0;
        for(Viagem v : listaViagensRealizadas.getViagens()){
            if (v == listaViagensRealizadas.verViagemMaisRecente()){ 
                     pontosGanhosPelaViagem = v.getPontosPorViagem();
                     pontosGanhosPelosKm = v.getPontosPorKm();
            }
        }
        pontosTotais = pontosGanhosPelaViagem + pontosGanhosPelosKm;
        System.out.println("Últimos Pontos Ganhos: " + pontosTotais);
    }

    public void comprarViagem(Viagem v){
        if(saldo >= v.getPreco()){
            saldo -= v.getPreco();
            System.out.println("Compra efetuada com sucesso");
        }
        else{
            System.out.println("Saldo insuficiente");
        }
    }
}
