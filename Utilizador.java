package Backend;


public class Utilizador {
    private int email;
    private int password;
    private int numMoedas;
    private int numViagens;
    private double numKm;
    private ListaPromocoes listaPromocoes;
    private ListaViagens listaViagens;

    public Utilizador(int email, int password, int numMoedas, int numViagens, double numKm) {
        this.email = email;
        this.password = password;
        this.numMoedas = numMoedas;
        this.numViagens = numViagens;
        this.numKm = numKm;
    }

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
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

    public double getNumKm() {
        return numKm;
    }

    public void setNumKm(double numKm) {
        this.numKm = numKm;
    }

    public ListaPromocoes getListaPromocoes() {
        return listaPromocoes;
    }

    public void setListaPromocoes(ListaPromocoes listaPromocoes) {
        this.listaPromocoes = listaPromocoes;
    }

    public ListaViagens getListaViagens() {
        return listaViagens;
    }

    public void setListaViagens(ListaViagens listaViagens) {
        this.listaViagens = listaViagens;
    }

    public void mostrarPromocoes(){
        for( Promocao p : listaPromocoes.getPromocoes()){
          Promocao pDisponivel = new Promocao(p.getDescricao(), p.getMoedasQueAtribui(), p.getDataInicioPromocao(), p.getDataFimPromocao(), p.getCondicoesKmPercorridos(), p.getCondicoesViagensRealizadas(), p.getCondicoesOrigem(), p.getCondicoesDestino()); 
        for( Viagem v : listaViagens.getViagens()){
            if(pDisponivel.validacao(v.getnKm(), listaViagens.calcularNumViagens(), v.getOrigem(), v.getDestino()) && p.estaAtiva(v.getData())){
                System.out.println(pDisponivel.toString());
                }
            }
        }
    }

    
    public void usarPromocao(){
        for( Promocao p : listaPromocoes.getPromocoes()){
            for(Viagem v : listaViagens.getViagens()){
                if(p.estaAtiva(v.getData()) && p.validacao(v.getnKm(), listaViagens.calcularNumViagens(), v.getOrigem(), v.getDestino())){
                    numMoedas += p.getMoedasQueAtribui();
                    listaPromocoes.removerPromocao(p);
                }
            }
        }
    }



}
