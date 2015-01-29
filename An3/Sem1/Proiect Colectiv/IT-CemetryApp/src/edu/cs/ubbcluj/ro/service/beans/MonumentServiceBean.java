package edu.cs.ubbcluj.ro.service.beans;

import java.util.List;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Monument;
import edu.cs.ubbcluj.ro.repository.MonumentRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;
import edu.cs.ubbcluj.ro.repository.service.MonumentService;

@Stateless(name = "MonumentService", mappedName = "MonumentService")
@DependsOn({ "MonumentRepository" })
@Local
public class MonumentServiceBean implements MonumentService {

	@EJB(name = "MonumentRepository")
	MonumentRepository repo;

	@Override
	public Monument insertMonument(Monument Monument) {
		try {
			return repo.save(Monument);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return Monument;
	}

	@Override
	public Monument updateMonument(Monument Monument) {
		try {
			return this.repo.merge(Monument);
		} catch (RepositoryException e) {
			return null;
		}
	}

	@Override
	public void deleteMonument(Monument Monument) {
		try {
			this.repo.delete(Monument);
		} catch (RepositoryException e) {

		}
	}

	@Override
	public List<Monument> getAll() {
		try {
			return repo.getAll();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;

	}

    @Override
    public boolean isMonument(int graveId) {
        try {
            if (repo.getById(graveId) != null) {
                return true;
            }
            return false;
        }catch (RepositoryException e) {
            return false;
        }
    }
}
