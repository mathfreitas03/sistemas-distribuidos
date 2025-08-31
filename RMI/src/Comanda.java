
public class Comanda {
    private int mesa;
    private String nomeCliente;

    public Comanda(String nome, int mesa) {
        this.nomeCliente = nome;
        this.mesa = mesa;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    
}