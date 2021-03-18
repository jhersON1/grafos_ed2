package bo.edu.uagrm.ficct.inf310sb.grafos.nopesados;

import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.grafos.excepciones.ExcepcionNroVerticesInvalido;
import bo.edu.uagrm.ficct.inf310sb.grafos.pesados.GrafoPesado;

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
        //listaDeAdyacencia();
        digrafo();
    }
    private static void grafo() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste {
        Grafo grafo = new Grafo(3);
        grafo.insertarArista(0,0);
        grafo.insertarArista(0,2);
        grafo.insertarArista(0,1);
        grafo.insertarArista(1,2);

        System.out.println(grafo);
    }
    private static void digrafo() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        Digrafo digrafo = new Digrafo(5);

        digrafo.insertarArista(0,0);
        digrafo.insertarArista(1,0);
        digrafo.insertarArista(1,2);
        digrafo.insertarArista(2,0);
        digrafo.insertarArista(2,1);
        //digrafo.insertarArista(2,2);
        OperacionesDigrafo opDigrafo =  new OperacionesDigrafo(digrafo,0);
        System.out.println(digrafo);


        /*System.out.println(digrafo.cantidadDeAristas());*/
        //digrafo.eliminarArista(2,2);
        System.out.println(opDigrafo.cantidadDeIslasDigrafo());
    }

    private static void digrafoDfs() throws ExcepcionAristaYaExiste, ExcepcionNroVerticesInvalido, ExcepcionAristaNoExiste {
        Digrafo digrafoDfs = new Digrafo(10);

        digrafoDfs.insertarArista(0,1);
        digrafoDfs.insertarArista(0,2);
        digrafoDfs.insertarArista(0,6);
        digrafoDfs.insertarArista(1,3);
        digrafoDfs.insertarArista(2,4);
        digrafoDfs.insertarArista(3,4);
        digrafoDfs.insertarArista(4,1);
        digrafoDfs.insertarArista(5,3);
        digrafoDfs.insertarArista(5,6);
        digrafoDfs.insertarArista(6,4);
        digrafoDfs.insertarArista(6,7);
        digrafoDfs.insertarArista(6,8);
        digrafoDfs.insertarArista(8,5);
        digrafoDfs.insertarArista(9,6);

        DFS dfs = new DFS(digrafoDfs,0);
        System.out.println(dfs.elRecorrido());
    }
//  5. Para un grafo no dirigido implementar un algoritmo para encontrar el número de islas que hay en el grafo
    private static void operacionesGrafo() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        Grafo grafo = new Grafo(7);

        grafo.insertarArista(0,1);
       grafo.insertarArista(2,3);
        grafo.insertarArista(3,4);
        grafo.insertarArista(5,6);


        OperacionesGrafo operaciones = new OperacionesGrafo(grafo);
        System.out.println(operaciones.cantidadDeIslas());
    }

    private static void digrafoFuertementeConexo() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        Digrafo digrafo = new Digrafo(4);

        digrafo.insertarArista(0,2);
        digrafo.insertarArista(0,1);
        digrafo.insertarArista(1,3);
        //digrafo.insertarArista(2,3);
        //digrafo.insertarArista(3,0);

        FuertementeConexo fuertementeConexo= new FuertementeConexo(digrafo);
        Conexo conexo = new Conexo(digrafo,0);

        //System.out.println(conexo.esConexo(digrafo,1));
        System.out.println(fuertementeConexo.esFuertementeConexo(digrafo));
    }
// 4. Para un grafo no dirigido implementar un algoritmo para encontrar que en que vértices del grafo hay ciclos

    private static void existeCicloGrafo() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        Grafo grafo = new Grafo(7);

        grafo.insertarArista(0,2);
        grafo.insertarArista(0,1);

        grafo.insertarArista(1,3);
        grafo.insertarArista(1,5);
        grafo.insertarArista(1,4);
        grafo.insertarArista(4,6);
        grafo.insertarArista(5,6);
        //grafo.insertarArista(0,3);

/*        grafo.insertarArista(0,1);
        grafo.insertarArista(1,2);
        grafo.insertarArista(1,3);
        grafo.insertarArista(6,4);
        grafo.insertarArista(5,7);*/

        //DFS dfs = new DFS(grafo,3);
        OperacionesGrafo existeCiclo = new OperacionesGrafo(grafo);
        //dfs.hayCiclo(0);
        System.out.println(grafo.listasDeAdyacencias);
        System.out.println(existeCiclo.listaDeVerticesQueTienenCiclo());
        //grafo.eliminarArista2(0,1);
        System.out.println(grafo.listasDeAdyacencias);

    }
    // 6. Para un grafo dirigido implementar un algoritmo para encontrar el número de islas que hay en el grafo
    private static void digrafoVerif() throws ExcepcionAristaYaExiste, ExcepcionNroVerticesInvalido, ExcepcionAristaNoExiste {
        Digrafo digrafo = new Digrafo(9);

        digrafo.insertarArista(0,1);
        digrafo.insertarArista(1,2);
        digrafo.insertarArista(3,0);
        digrafo.insertarArista(4,3);
        digrafo.insertarArista(5,6);

        OperacionesDigrafo opDigrafo = new OperacionesDigrafo(digrafo,6);
        //DFS dfs = new DFS(digrafo,0);
        //dfs.continuarDFS(0);
        System.out.println(opDigrafo.cantidadDeIslasDigrafo());
    }

    //2. Para un grafo dirigido implementar un algoritmo para encontrar si el grafo dirigido tiene ciclos
    private static void existeCicloDigrafo() throws ExcepcionAristaYaExiste, ExcepcionNroVerticesInvalido, ExcepcionAristaNoExiste {
        Digrafo digrafo = new Digrafo(5);

        digrafo.insertarArista(0,1);
        digrafo.insertarArista(1,2);
        digrafo.insertarArista(3,0);
        digrafo.insertarArista(4,3);
        digrafo.insertarArista(1,4);
        //digrafo.insertarArista(2,4);

        OperacionesDigrafo opDigrafo = new OperacionesDigrafo(digrafo,2);
        System.out.println(opDigrafo.existeCiclo());
    }
    //3. Para un grafo dirigido implementar un algoritmo para encontrar si es débilmente conexo
    //ya esta implementado

    //7. Para un grafo dirigido implementar el algoritmo de Wharsall, que luego muestre entre que vértices hay camino

    private static void algoritmoWharsall() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        Digrafo digrafo = new Digrafo(4);

        digrafo.insertarArista(0,1);
        digrafo.insertarArista(1,2);
        digrafo.insertarArista(3,0);
/*        digrafo.insertarArista(4,3);
        digrafo.insertarArista(1,4);*/

        OperacionesDigrafo opDigrafo = new OperacionesDigrafo(digrafo,0);
        opDigrafo.algoritmoWharsall();
        String s = "";
        for (int i = 0; i < digrafo.cantidadDeVertices(); i++){
            for (int j = 0; j < digrafo.cantidadDeVertices(); j++){
                s += opDigrafo.p[i][j] + ", ";

            }
            s += "\n";

        }
        System.out.println(s);
    }

    //16. Para un grafo dirigido solo usando como base la lógica de un recorrido (dfs o bfs) encuentre desde que vértices se puede llegar a un vértice a.

    private static void verticesConCaminoA () throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        Digrafo digrafo = new Digrafo(4);

        digrafo.insertarArista(0,1);
        digrafo.insertarArista(1,2);
        digrafo.insertarArista(3,0);
/*        digrafo.insertarArista(4,3);
        digrafo.insertarArista(1,4);*/

        OperacionesDigrafo opDigrafo = new OperacionesDigrafo(digrafo,0);
        System.out.println(opDigrafo.verticesConCaminoA(3));
    }

    // grafo no pesados

    private static void listaDeAdyacencia() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste {
        GrafoPesado grafoPesado = new GrafoPesado(5);

        grafoPesado.insertarArista(0,1,20);
        grafoPesado.insertarArista(1,2,10);
        grafoPesado.insertarArista(3,0,60);
        grafoPesado.insertarArista(2,4,200);

        System.out.println(grafoPesado.adyacentesDeVertice(0));
    }
}
