import java.util.ArrayList;
import java.util.Stack;

public class Ej2 {
    public int connectedComponents() {
        int comps = 0;
        ArrayList<V> aux = vertexes;
        for (V vertex : vertexes){
            if (degree(vertex) <= 0 ) { //esta aislado
                comps++;
                aux.remove(vertex);
                break;
            }
            if (aux.contains(vertex)) //si esta, hay una componente conexa; entonces vamos a buscar las conexiones
                comps += findConnection(vertex, aux);
        }
        return comps;
    }

    private int findConnection(V vertex, ArrayList<V> aux){
        DFS(vertex, aux); //hago un DFS para buscar y eliminar de aux los nodos de la componente conexa
        return 1;
    }

    private void DFS(V startNode, ArrayList<V> aux){
        ArrayList<Boolean> visited = new ArrayList<>();
        Stack<Integer> stack = new Stack<Integer>();

        int currentPos = vertexes.indexOf(startNode);
        if (currentPos == -1)
            return;

        vertexes.stream().forEach(x -> visited.add(false));

        stack.push(currentPos);

        while (!stack.isEmpty() && currentPos < vertexes.size()){
            currentPos = stack.pop();
            if (visited.get(currentPos))
                continue;

            aux.remove(vertexes.get(currentPos));
            visited.set(currentPos, true);

            for (int row = 0; row < matrix.cantElements(); row++){
                if (matrix.get(row, currentPos) && !visited.get(row))
                    stack.push(row);
            }
        }
    }
}
