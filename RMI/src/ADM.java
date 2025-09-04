import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ADM implements Restaurante {
    
    private ArrayList<Comanda> comandas;
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
        String file = "menu_restaurante.csv";
        Path filePath = Paths.get("RMI", file);
        List<String> cardapio = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                cardapio.add(linha);
            }
        } catch (IOException e) {
            throw new RemoteException("Erro ao ler o cardápio em " + filePath, e);
        }

        return cardapio.toArray(new String[0]);
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
            int port = 6600;

            // Registra a stub no RMI Registry para que ela seja obtida pelos clientes
            Registry registryServer = LocateRegistry.createRegistry(6600);

            registryServer.bind("Atendimento", stubRestaurante);
            System.out.println("Servidor rodando na porta " + port + "\n" + stubRestaurante );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Cliente para Chef
        // String host = (args.length < 1) ? null : args[0];
        // try {
        //     // Obtém uma referência para o registro do RMI
        //     Registry registryClient = LocateRegistry.getRegistry(host,6601);

        //     // Obtém a stub do servidor
        //     Cozinha stubCozinha = (Cozinha) registryClient.lookup("Preparo");
            
        // } catch (Exception ex) {
        //     ex.printStackTrace();
        // }

        // Lógica de Negócio
        // ...
    }

}