import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class ADM implements Restaurante {

    private final Scanner scan = new Scanner(System.in);
    private ArrayList<Comanda> comandas; //  Cada comanda possui um uma mesa e um cliente
    private ArrayList<Integer> mesas; //  Adicionado para poder controlar as mesas
    
    public ADM() {
        this.comandas = new ArrayList<>();
        this.mesas = new ArrayList<>();
    }

    public int novaMesa(int mesa) {
        if(!(mesas.contains(mesa))) {
            mesas.add(mesa);
            return 1;
        }
    }

    @Override
    public int novaComanda(String nome, int mesa) throws RemoteException {
        try {
            if(mesas.contains(mesa)) {
                Comanda cmd = new Comanda(nome, mesa);
                comandas.add(cmd);
                return 1;
            }
            else throw new Exception("A mesa desejada não existe!");
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public String[] consultarCardapio() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String fazerPedido(int comanda, String[] pedido) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float valorComanda(int comanda) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean fecharComanda(int comanda) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /* ********************* Cliente para cozinha ************************

    try {
         // Obtém uma referência para o registro do RMI
         Registry registry = LocateRegistry.getRegistry(host,port);

         // Obtém a stub do servidor
         Cozinha stub= (Cozinha) registry.lookup("Cozinha");

         // Utilizar o metodo servidor
            ...
      } catch (Exception ex) {
         ex.printStackTrace();


     *********************************************************************/

    /* ********************* Servidor para Mesa ************************

    implementar novaComanda, novoPedido, etc...


     *********************************************************************/
}
