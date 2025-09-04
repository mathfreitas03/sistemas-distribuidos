import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;

public class ADM implements Restaurante {
    
    private ArrayList<Comanda> comandas;
    private Random rand = new Random();  
    private Cozinha stubCozinha;
    private Restaurante stubRestaurante;
    private int nextId = 0;

    public void setStubCozinha(Cozinha stubCozinha) {
        this.stubCozinha = stubCozinha;
    }

    public void setStubRestaurante(Restaurante stubRestaurante) {
        this.stubRestaurante = stubRestaurante;
    }

    public ADM() {
        this.comandas = new ArrayList<Comanda>();
    }

    // métodos da interface Restaurante

     @Override
    public int novaComanda(String nome, int mesa) throws RemoteException {        
        
        Comanda cm = new Comanda(mesa, nextId++, nome);
        comandas.add(cm);

        return cm.getId();
    }

    @Override
    public String[] consultarCardapio() throws RemoteException {
        return Menu.getMenuItems();
    }

    @Override
    public String fazerPedido(int comanda, String[] pedido) throws RemoteException {
        int idPreparo = stubCozinha.novoPreparo(comanda, pedido);
        
        return "OK";
    }

    @Override
    public float valorComanda(int comanda) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'valorComanda'");
    }

    @Override
    public boolean fecharComanda(int comanda) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fecharComanda'");
    }

    

    public static void main(String[] args) {
        // Server para Mesa
        try {
            // Instancia o objeto servidor e a sua stub
            ADM server = new ADM();
            Restaurante stubRestaurante = (Restaurante) UnicastRemoteObject.exportObject(server, 0);

            // Registra a stub no RMI Registry para que ela seja obtida pelos clientes
            Registry registryServer = LocateRegistry.createRegistry(6600);

            registryServer.bind("Atendimento", stubRestaurante);
            System.out.println("Servidor pronto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Cliente para Chef
        String host = (args.length < 1) ? null : args[0];
        try {
            // Obtém uma referência para o registro do RMI
            Registry registryClient = LocateRegistry.getRegistry(host,6601);

            // Obtém a stub do servidor
            Cozinha stubCozinha = (Cozinha) registryClient.lookup("Preparo");
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Lógica de Negócio
        // ...
    }

}