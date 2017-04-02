
public interface Observable<O> {

	public void addObserver(O observer);
	public void removeObserver(O observer);
}
