package Backend;
import java.util.Date;

public class Promocao {
    private String descricao;
    private int moedasQueAtribui;
    private Date dataInicioPromocao;
    private Date dataFimPromocao;
    private double condicoesKmPercorridos;
    private int condicoesViagensRealizadas;
    private String condicoesOrigem;
    private String condicoesDestino;

    public Promocao(String descricao, int moedasQueAtribui, Date dataInicioPromocao, Date dataFimPromocao, double condicoesKmPercorridos, int condicoesViagensRealizadas, String condicoesOrigem, String condicoesDestino) {
        this.descricao = descricao;
        this.moedasQueAtribui = moedasQueAtribui;
        this.dataInicioPromocao = dataInicioPromocao;
        this.dataFimPromocao = dataFimPromocao;
        this.condicoesKmPercorridos = condicoesKmPercorridos;
        this.condicoesViagensRealizadas = condicoesViagensRealizadas;
        this.condicoesOrigem = condicoesOrigem;
        this.condicoesDestino = condicoesDestino;
    }

    public String getCondicoesDestino() {
        return condicoesDestino;
    }

    public void setCondicoesDestino(String condicoesDestino) {
        this.condicoesDestino = condicoesDestino;
    }

    public String getCondicoesOrigem() {
        return condicoesOrigem;
    }

    public void setCondicoesOrigem(String condicoesOrigem) {
        this.condicoesOrigem = condicoesOrigem;
    }

    public double getCondicoesKmPercorridos() {
        return condicoesKmPercorridos;
    }

    public void setCondicoesKmPercorridos(double condicoesKmPercorridos) {
        this.condicoesKmPercorridos = condicoesKmPercorridos;
    }

    public int getCondicoesViagensRealizadas() {
        return condicoesViagensRealizadas;
    }

    public void setCondicoesViagensRealizadas(int condicoesViagensRealizadas) {
        this.condicoesViagensRealizadas = condicoesViagensRealizadas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getMoedasQueAtribui() {
        return moedasQueAtribui;
    }

    public void setMoedasQueAtribui(int moedasQueAtribui) {
        this.moedasQueAtribui = moedasQueAtribui;
    }

    public Date getDataInicioPromocao() {
        return dataInicioPromocao;
    }

    public void setDataInicioPromocao(Date dataInicioPromocao) {
        this.dataInicioPromocao = dataInicioPromocao;
    }

    public Date getDataFimPromocao() {
        return dataFimPromocao;
    }

    public void setDataFimPromocao(Date dataFimPromocao) {
        this.dataFimPromocao = dataFimPromocao;
    }

    public boolean estaAtiva(Date dataAtual) {
        return dataAtual.after(dataInicioPromocao) && dataAtual.before(dataFimPromocao);
    }

    public boolean validacao(double kmPercorridos, int viagensRealizadas, String origem, String destino) {
        if(kmPercorridos >= condicoesKmPercorridos && viagensRealizadas >= condicoesViagensRealizadas){
            System.out.println("Condições de viagens realizadas e km percorridos cumpridas");
            if( origem.equals(condicoesOrigem) && destino.equals(condicoesDestino) ){
                System.out.println("Condições de origem e destino cumpridas");
            } 
        }
        else{
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Promocao{" + "descricao=" + descricao + ", moedasQueAtribui=" + moedasQueAtribui + ", dataInicioPromocao=" + dataInicioPromocao + ", dataFimPromocao=" + dataFimPromocao + ", condicoesKmPercorridos=" + condicoesKmPercorridos + ", condicoesViagensRealizadas=" + condicoesViagensRealizadas + '}';
    }

}
