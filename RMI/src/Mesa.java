import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.List;

public class Mesa {

    private int num;
    private Restaurante stub;
    private List<Integer> comandas = new ArrayList<Integer>();
    
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Restaurante getStub() {
        return stub;
    }

    public void setStub(Restaurante stub) {
        this.stub = stub;
    }

    public void initRegistry(String host, int port) {
        try {
            
            // Obtém uma referência para o registro do RMI
            Registry registry = LocateRegistry.getRegistry(host,port);

            // Obtém a stub do servidor
            Restaurante stubR = (Restaurante) registry.lookup("Atendimento");
            this.setStub(stubR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void pedirCardapio () throws RemoteException{
        String[] cardapio = stub.consultarCardapio();
        for(String prato : cardapio) System.out.println(prato);
    }

    public void criarComanda(String nome) throws RemoteException{
        this.comandas.add(stub.novaComanda(nome, num));
    }
    
    public void fazerPedido(String pedido) throws RemoteException{

    }
    
    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        
        Mesa mesa = new Mesa();
        mesa.initRegistry(host, 6600);
        
        try {
            mesa.pedirCardapio();
            // ...
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }    
}   