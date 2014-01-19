package lab7_8.ex3_bonus;

import java.util.ArrayList;
import lab7_8.ex3_bonus.VertexInterface;

public interface HelperInterface<T extends VertexInterface> {

	public ArrayList<T> generateNextValidVertices(T vertex);

	public Boolean isVertexSolution(T vertex);

	public T generateRoot();
}
