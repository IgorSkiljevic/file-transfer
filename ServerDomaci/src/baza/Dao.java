package baza;

import java.util.ArrayList;

public interface Dao<T> {

	public ArrayList<T> izvuciSve();

	public void ubaciSve(ArrayList<T> klijenti);

}
