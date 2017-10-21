package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.CatchLog;

public class EventDAOImpl implements EventDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<CatchLog> index() {
		String query = "SELECT c FROM CatchLog c";
		List<CatchLog> x = em.createQuery(query, CatchLog.class).getResultList();
		System.out.println(x.get(0));
		return x;
	}

	@Override
	public CatchLog show(int id) {
		String query = "SELECT c FROM CatchLog c WHERE c.id = :id";
		return em.createQuery(query, CatchLog.class).setParameter("id", id).getResultList().get(0);
	}

	@Override
	public CatchLog create(CatchLog log) {
		em.persist(log);
		  em.flush();
		  return em.find(CatchLog.class, log.getId());
	}

	@Override
	public CatchLog update(int id, CatchLog log) {
		CatchLog managed = em.find(CatchLog.class, id);
		managed.setCommonName(log.getCommonName());
		return managed;
	}

	@Override
	public boolean destroy(int id) {
		CatchLog managed = em.find(CatchLog.class, id);
		em.remove(managed);
		return true;
	}

}
