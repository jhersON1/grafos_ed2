package bo.edu.uagrm.ficct.inf310sb.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.ExcepcionNroVerticesInvalido;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String args[]) throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        //grafo();
        //digrafo();
        //grafoBfs();
        //digrafoBfs();
        //grafoDfs();
        //digrafoDfs();
        //operacionesGrafo();
        //digrafoFuertementeConexo();
        //existeCicloGrafo();
        //digrafoVerif();
        //existeCicloDigrafo();
        //algoritmoWharsall();
        //verticesConCaminoA();
        listaDeAdyacencia();
    }
    private static void listaDeAdyacencia() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        GrafoPesado grafoPesado = new GrafoPesado(5);

        grafoPesado.insertarArista(0,1,20);
        grafoPesado.insertarArista(1,2,10);
        grafoPesado.insertarArista(3,0,60);
        grafoPesado.insertarArista(2,4,200);
       //grafoPesado.insertarArista(1,4,200);
        //List<List<AdyacenteConPeso>> hola = new ArrayList<>();
        //DFS dfs = new DFS(grafoPesado,0);
        GrafoPesado grafoAux = new GrafoPesado(grafoPesado.cantidadDeVertices());

        //grafoAux = grafoPesado.kruskal();

        List<AdyacenteConPeso> listaAux = new ArrayList<>();

        listaAux = grafoPesado.ordenarListasDeAdyacencia();

        for(int i = 0; i < listaAux.size(); i++){
            int vertice = listaAux.get(i).getIndiceVertice();
            double peso = listaAux.get(i).getPeso();
            System.out.println("origen: "+i+" destino: " + vertice + " peso: " + peso);
        }
        System.out.println("---------------------------------");
        for (int i = 0; i < grafoAux.listasDeAdyacencias.size(); i++){
            for (int j = 0; j < grafoAux.listasDeAdyacencias.get(i).size(); j++){
                int vertice = grafoAux.listasDeAdyacencias.get(i).get(j).getIndiceVertice();
                double peso = grafoAux.listasDeAdyacencias.get(i).get(j).getPeso();
                System.out.println("VerticeOrigen: "+i+" verticeDestino: "+ vertice + " peso: " + peso);
                //System.out.println("vertice: "+ vertice + " peso: " + peso);
            }
        }
        //System.out.println(grafoPesado.cantidadDeIslas(grafoPesado));
    }
}
