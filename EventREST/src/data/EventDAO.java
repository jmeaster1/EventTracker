package data;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import entities.CatchLog;

@Transactional
public interface EventDAO {
	public List<CatchLog> index();

	public CatchLog show(int id);

	public CatchLog create(CatchLog log);

	public CatchLog update(int id, CatchLog log);

	public boolean destroy(int id);
}