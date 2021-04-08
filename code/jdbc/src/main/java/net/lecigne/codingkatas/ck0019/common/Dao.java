package net.lecigne.codingkatas.ck0019.common;

import java.util.List;

public interface Dao<T> {

	void create(T t);

	T read(long id);

	List<T> readAll();

	void delete(long id);

	void update(T t);

}
