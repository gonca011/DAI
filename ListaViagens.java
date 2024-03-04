package Backend;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ListaViagens {
    private List<Viagem> viagens;

    public ListaViagens() {
        this.viagens = new ArrayList<>();
    }

    public List<Viagem> getViagens() {
        return viagens;
    }

    public void setViagens(List<Viagem> viagens) {
        this.viagens = viagens;
    }

    public void adicionarViagem(Viagem viagem){
        viagens.add(viagem);
    }

    public void removerViagem(Viagem viagem){
        viagens.remove(viagem);
    }
    
    public int calcularNumViagens(){
        return viagens.size();
    }  
    
    public double calcularNumKm(){
        double numKm = 0;
        for(Viagem v : viagens){
            numKm += v.getnKm();
        }
        return numKm;
    }

    public double calcularKmPercurso(String origem, String destino){
        double numKm = 0;
        for(Viagem v : viagens){
            if(v.getOrigem().equals(origem) && v.getDestino().equals(destino)){
                numKm += v.getnKm();
            }
        }
        return numKm;
    }

    public int calcularNumViagensPercurso(String origem, String destino){
        int numViagens = 0;
        for(Viagem v : viagens){
            if(v.getOrigem().equals(origem) && v.getDestino().equals(destino)){
                numViagens++;
            }
        }
        return numViagens;
    }


    public double kmEntreDatas(Date dataInicio, Date dataFim){
        double numKm = 0;
        for(Viagem v : viagens){
            if(v.getData().after(dataInicio) && v.getData().before(dataFim)){
                numKm += v.getnKm();
            }
        }
        return numKm;
    }

    public double kmEntreDatasPercurso(Date dataInicio, Date dataFim, String origem, String destino){
        double numKm = 0;
        for(Viagem v : viagens){
            if(v.getData().after(dataInicio) && v.getData().before(dataFim) && v.getOrigem().equals(origem) && v.getDestino().equals(destino)){
                numKm += v.getnKm();
            }
        }
        return numKm;
    }

    public int viagensRealizadasEntreDatas(Date dataInicio, Date dataFim){
        int numViagens = 0;
        for(Viagem v : viagens){
            if(v.getData().after(dataInicio) && v.getData().before(dataFim)){
                numViagens++;
            }
        }
        return numViagens;
    }

    public int viagensRealizadasEntreDatasPercurso(Date dataInicio, Date dataFim, String origem, String destino){
        int numViagens = 0;
        for(Viagem v : viagens){
            if(v.getData().after(dataInicio) && v.getData().before(dataFim) && v.getOrigem().equals(origem) && v.getDestino().equals(destino)){
                numViagens++;
            }
        }
        return numViagens;
    }

    public void listarViagens(){
        for(Viagem v : viagens){
            System.out.println(v.toString());
        }
    }

    


}
