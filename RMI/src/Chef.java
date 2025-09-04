import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.List;

public class Chef implements Cozinha {
    public Chef() {}

    private List<Preparo> preparos;
    
     @Override
    public int novoPreparo(int comanda, String[] pedido) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'novoPreparo'");
    }

    @Override
    public int tempoPreparo(int preparo) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tempoPreparo'");
    }


    @Override
    public String[] pegarPreparo(int preparo) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pegarPreparo'");
    }

    public static void main(String[] args) {
        // Server para ADM
        try {
            // Instancia o objeto servidor e a sua stub
            Chef server = new Chef();
            Cozinha stub = (Cozinha) UnicastRemoteObject.exportObject(server, 0);

            // Registra a stub no RMI Registry para que ela seja obtida pelos clientes
            Registry registry = LocateRegistry.createRegistry(6601);

            registry.bind("Preparo", stub);
            System.out.println("Servidor pronto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
   
}