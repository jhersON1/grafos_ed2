package bo.edu.uagrm.ficct.inf310sb.grafos.nopesados;


import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.ExcepcionAristaNoExiste;

public class FuertementeConexo {
    private Conexo grafoConexo;
    private int posVerticeDePartida = 0;

    public FuertementeConexo(Digrafo unDigrafo) throws ExcepcionAristaNoExiste {

        this.grafoConexo = new Conexo(unDigrafo, posVerticeDePartida);
    }

    /*    public boolean esFuertementeConexo(){
            return this.grafoConexo.esConexo();
        }*/
    public boolean esConexo(){
        return this.grafoConexo.esConexo();
    }
    public boolean esFuertementeConexo(Digrafo unDigrafo) throws ExcepcionAristaNoExiste {
        if (esConexo()) {
            for (Integer verticesPorRecorrer : grafoConexo.elRecorrido()) {
                posVerticeDePartida = verticesPorRecorrer.intValue();
                if (!grafoConexo.esConexo(unDigrafo, posVerticeDePartida)) {
                    return false;
                }

            }
            return true;
        }else {
            return false;
        }
    }

    public boolean esDebilmenteConexo (Digrafo unDigrafo) throws ExcepcionAristaNoExiste {
        if (esConexo()){
            if (!esFuertementeConexo(unDigrafo)){
                return true;
            }
        }
        return false;
    }

}
