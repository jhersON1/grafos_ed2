package bo.edu.uagrm.ficct.inf310sb.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.ExcepcionNroVerticesInvalido;
import bo.edu.uagrm.ficct.inf310sb.grafos.nopesados.Conexo;
import bo.edu.uagrm.ficct.inf310sb.grafos.nopesados.DFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrafoPesado {
    protected List<List<AdyacenteConPeso>> listasDeAdyacencias;
    //private DFS dfs;
    public GrafoPesado() {
        this.listasDeAdyacencias = new ArrayList<List<AdyacenteConPeso>>();

    }

    public GrafoPesado(int nroDeVerticesIncial) throws ExcepcionNroVerticesInvalido {
        if (nroDeVerticesIncial < 0){
            throw new ExcepcionNroVerticesInvalido();
        }
        this.listasDeAdyacencias = new ArrayList<>();
        for (int i = 0; i < nroDeVerticesIncial; i++){
            this.listasDeAdyacencias.add(new ArrayList<AdyacenteConPeso>());
        }
    }

    public void insertarVertice(){
        this.listasDeAdyacencias.add(new ArrayList<AdyacenteConPeso>());
    }

    public int cantidadDeAristas(){
        int cantAristas = 0;
        int cantLazos = 0;
        for (int i = 0; i < this.listasDeAdyacencias.size(); i++){
            List<AdyacenteConPeso> adyacentesDeUnVertice = this.listasDeAdyacencias.get(i);
            for (AdyacenteConPeso posAdyacente : adyacentesDeUnVertice) {
                if (i == posAdyacente.getIndiceVertice()) {
                    cantLazos++;
                } else {
                    cantAristas++;
                }
            }
        }
        cantAristas = (cantAristas / 2) + cantLazos;
        return cantAristas;
    }

    public int cantidadDeVertices() {
        return listasDeAdyacencias.size();
    }

    public void validarVertice (int posicionDeVertice) {
        if (posicionDeVertice < 0 || posicionDeVertice >= cantidadDeVertices()){
            throw new IllegalArgumentException("El vértice " + posicionDeVertice + " no pertece al grafo");
        }
    }

    public void insertarArista (int posVerticeOrigen, int posVerticeDestino, double costo) throws ExcepcionAristaYaExiste {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        List<AdyacenteConPeso> adyacenciasDelOrigen = this.listasDeAdyacencias.get(posVerticeOrigen);
        adyacenciasDelOrigen.add(new AdyacenteConPeso(posVerticeDestino, costo));
        if (posVerticeOrigen != posVerticeDestino){
            List<AdyacenteConPeso> adyacenciasDelDestino = this.listasDeAdyacencias.get(posVerticeDestino);
            adyacenciasDelDestino.add(new AdyacenteConPeso(posVerticeOrigen, costo));
        }
    }


    public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino){
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<AdyacenteConPeso> adyacenciasDelOrigen = this.listasDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso destino = new AdyacenteConPeso(posVerticeDestino);
        /*return adyacenciasDelOrigen.contains(posVerticeDestino);*/
        return adyacenciasDelOrigen.contains(destino);
    }

    public void eliminarVertice(int posVerticeAEliminar){
        validarVertice(posVerticeAEliminar);
        this.listasDeAdyacencias.remove(posVerticeAEliminar);
        for (List<AdyacenteConPeso> adyacentesDeUnVertice: this.listasDeAdyacencias) {
            AdyacenteConPeso adyacenteConPeso = new AdyacenteConPeso(posVerticeAEliminar);
            int posicionDeVerticeEnAdy = adyacentesDeUnVertice.indexOf(posVerticeAEliminar);
            if (posicionDeVerticeEnAdy >= 0) {
                adyacentesDeUnVertice.remove(posicionDeVerticeEnAdy);
            }
            for (int i = 0; i < adyacentesDeUnVertice.size(); i++){
                AdyacenteConPeso posicionAdyacente = adyacentesDeUnVertice.get(i);
                if (posicionAdyacente.getIndiceVertice() > posVerticeAEliminar){
                    posicionAdyacente.setIndiceVertice(posicionAdyacente.getIndiceVertice() - 1);
                    //adyacentesDeUnVertice.set(i,posicionAdyacente - 1);
                }
            }
        }
    }

    public int gradoDeVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<AdyacenteConPeso> adyacenciasDelVertice = this.listasDeAdyacencias.get(posDeVertice);
        return adyacenciasDelVertice.size();
    }

    public Iterable<Integer> adyacentesDeVertice(int posDeVertice) {
        validarVertice(posDeVertice);
        List<AdyacenteConPeso> adyacenciasDelVertice = this.listasDeAdyacencias.get(posDeVertice);
        List<Integer> adyacentesDelVertice = new ArrayList<>();
        for (AdyacenteConPeso adyacente : adyacenciasDelVertice){
            adyacentesDelVertice.add(adyacente.getIndiceVertice());
        }
        Iterable<Integer> it = adyacentesDelVertice;
        return it;
    }
/*    public int primerAdyacenteDeVertice(int posDeVertice){
        validarVertice(posDeVertice);
        Iterable<Integer> adyacentesEnTurno = adyacentesDeVertice(posDeVertice);
        for (Integer posVerticeAdyacente : adyacentesEnTurno) {
            return  posVerticeAdyacente;
        }
        return -1;
    }*/
    // hay que implementar

    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if (!this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }
        for (int i = 0; i < listasDeAdyacencias.get(posVerticeOrigen).size(); i++){
            if ( listasDeAdyacencias.get(posVerticeOrigen).get(i).getIndiceVertice() == posVerticeDestino){
                listasDeAdyacencias.get(posVerticeOrigen).remove(i);
            }
        }
        for (int i = 0; i <  listasDeAdyacencias.get(posVerticeDestino).size(); i++){
            if ( listasDeAdyacencias.get(posVerticeDestino).get(i).getIndiceVertice() == posVerticeOrigen){
                listasDeAdyacencias.get(posVerticeDestino).remove(i);
            }
        }
    }
    public void eliminarArista2(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if (!this.existeAdyacencia(posVerticeOrigen,posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }
        for (int i = 0; i < listasDeAdyacencias.get(posVerticeOrigen).size(); i++){
            if ( listasDeAdyacencias.get(posVerticeOrigen).get(i).getIndiceVertice() == posVerticeDestino){
                listasDeAdyacencias.get(posVerticeOrigen).remove(i);
            }
        }

    }


    public List<List<AdyacenteConPeso>> listaDeAyacencia (){
        return this.listasDeAdyacencias;
    }


    @Override
    public String toString (){
        return listasDeAdyacencias.toString();
    }


    public List<List<AdyacenteConPeso>> copiaAristas(){
        List<List<AdyacenteConPeso>> listaAux = new ArrayList<>();
        for (int i = 0; i < listasDeAdyacencias.size(); i++){
            listaAux.add(listasDeAdyacencias.get(i));
        }
        return listaAux;
    }

    public List<AdyacenteConPeso> listaDesordenada (){
        List<AdyacenteConPeso> listaAux = new ArrayList<>();
        for (int i = 0; i < listasDeAdyacencias.size(); i++){
            for (int j = 0; j < listasDeAdyacencias.get(i).size(); j++){
                listaAux.add(listasDeAdyacencias.get(i).get(j));
            }
        }
        return listaAux;
    }



    public List<AdyacenteConPeso> ordenarListasDeAdyacencia(){
        List<AdyacenteConPeso> listaResultado = new ArrayList<AdyacenteConPeso>();
        for(List<AdyacenteConPeso> listaDeAdyacencia : listasDeAdyacencias){
            for(AdyacenteConPeso adyacenteConPeso : listaDeAdyacencia){
                listaResultado.add(adyacenteConPeso);
            }
        }
        ordenarLista(listaResultado);
        return listaResultado;
    }

    private void ordenarLista(List<AdyacenteConPeso> lista){
        for(int i = 0; i < lista.size() - 1; i++){
            for(int j=i+1; j < lista.size(); j++){
                AdyacenteConPeso adyacenteConPesoA = lista.get(i);
                AdyacenteConPeso adyacenteConPesoB = lista.get(j);
                if(adyacenteConPesoB.getPeso()<adyacenteConPesoA.getPeso()){
                    lista.set(i, adyacenteConPesoB);
                    lista.set(j, adyacenteConPesoA);
                }
            }
        }
    }
    public int cantidadDeIslas(GrafoPesado grafo) throws ExcepcionAristaNoExiste {
        int cantIslas = 1;
         bo.edu.uagrm.ficct.inf310sb.grafos.pesados.DFS dfs = new bo.edu.uagrm.ficct.inf310sb.grafos.pesados.DFS(grafo,0);
            while (!dfs.controlMarcados.estaTodoMarcado()) {
                int noMarcado = dfs.controlMarcados.encontrarNoMarcado();
                if (noMarcado > 0){
                    dfs.continuarDFS(noMarcado);
                    cantIslas++;
                }
            }

        return cantIslas;
    }
    public boolean existeCicloEnUnGrafo(GrafoPesado grafoPesado) throws ExcepcionAristaNoExiste {
        if (cantidadDeAristas() >= (cantidadDeVertices()-(cantidadDeIslas(grafoPesado) - 1))){
            return true;
        }
        return false;
    }

    // algoritmo de kruskal

/*    public GrafoPesado kruskal (GrafoPesado grafoPesado) throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste {
        GrafoPesado grafoAux = new GrafoPesado(grafoPesado.cantidadDeVertices());
        List<AdyacenteConPeso> listaOrdenada = new ArrayList<AdyacenteConPeso>();
        listaOrdenada = grafoPesado.ordenarListasDeAdyacencia();

        for (int i = 0; i < listaOrdenada.size(); i++) {
            int posDestino = listaOrdenada.get(i).getIndiceVertice();
            double peso = listaOrdenada.get(i).getPeso();
            if (!grafoAux.existeAdyacencia(i,posDestino)){
                grafoAux.insertarArista(i, posDestino, peso);
            }
        }
        return grafoAux;
    }*/

    private void cargarListaDeAristas(List<AdyacenteConPesoIndiceOrigen> lista) {
        int i = 0;
        for (List<AdyacenteConPeso> unaListaDeAdyacencia : this.listasDeAdyacencias) {
            for (AdyacenteConPeso unAdyacenteConPeso : unaListaDeAdyacencia) {
                AdyacenteConPesoIndiceOrigen adyacenteEnTurno = new AdyacenteConPesoIndiceOrigen(i,
                        unAdyacenteConPeso.getIndiceVertice(), unAdyacenteConPeso.getPeso());
                AdyacenteConPesoIndiceOrigen adyacenteParalelo = new AdyacenteConPesoIndiceOrigen(unAdyacenteConPeso.getIndiceVertice(),
                        i, unAdyacenteConPeso.getPeso());
                if (!lista.contains(adyacenteParalelo)) {
                    lista.add(adyacenteEnTurno);
                }
            }
            i++;
        }
        Collections.sort(lista);
    }
    private void cargarListaDeVertices(List<Integer> listaDeVertices) {
        for (Integer unVertice : listaDeVertices) {
            this.insertarVertice();
        }
    }
}
