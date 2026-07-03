package pe.edu.ulima.is2;

public class ConexionSimulada {
    private boolean conectado = false;

    public void conectar()      { conectado = true; }
    public void desconectar()   { conectado = false; }
    public boolean isConectado(){ return conectado; }

    public String ejecutar(String consulta) {
        if (!conectado) throw new IllegalStateException("No hay conexión activa");
        return "Resultado de: " + consulta;
    }
}