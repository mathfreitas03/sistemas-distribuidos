import java.rmi.RemoteException;

public class KitchenServer implements Cozinha {

    @Override
    public String[] pegarPreparo(int preparo) throws RemoteException {
        return new String[0];
    }

    @Override
    public int novoPreparo(int comanda, String[] pedido) throws RemoteException {
        return 0;
    }

    @Override
    public int tempoPreparo(int preparo) throws RemoteException {
        return 0;
    }
}
