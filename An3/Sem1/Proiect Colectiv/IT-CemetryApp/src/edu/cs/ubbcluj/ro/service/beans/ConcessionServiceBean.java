package edu.cs.ubbcluj.ro.service.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Concession;
import edu.cs.ubbcluj.ro.model.Dead;
import edu.cs.ubbcluj.ro.repository.ConcessionRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;
import edu.cs.ubbcluj.ro.repository.service.ConcessionService;

@Stateless(name = "ConcessionService", mappedName = "ConcessionService")
@DependsOn({"ConcessionRepository"})
@Local
public class ConcessionServiceBean implements ConcessionService {

    @EJB(name = "ConcessionRepository")
    ConcessionRepository repo;

    @Override
    public Concession insertConcession(Concession Concession) {
        try {
            return repo.save(Concession);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return Concession;
    }

    @Override
    public Concession updateConcession(Concession Concession) {
        try {
            return this.repo.merge(Concession);
        } catch (RepositoryException e) {
            return null;
        }
    }

    @Override
    public void deleteConcession(Concession Concession) {
        try {
            this.repo.delete(Concession);
        } catch (RepositoryException e) {

        }
    }

    @Override
    public List<Concession> getAll() {
        try {
            return repo.getAll();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getData(int graveid) {
        List<String> grave = new ArrayList<String>();
        for (Concession c : this.getAll()) {
            if (c.getGrave().getId() == graveid) {
                grave.add(c.getGrave().getParcel().getGraveyard().getName());
                grave.add(String.valueOf(c.getGrave().getParcel().getNumber()));
                grave.add(String.valueOf(c.getGrave().getNumber()));
                grave.add(c.getOwner().getFirstName());
                grave.add(c.getOwner().getLastName());
                grave.add(c.getOwner().getAddress());
                grave.add(String.valueOf(c.getNumber())); // nr chitantei == nr concesiunii ??
                //data.add(c.getDate().toString());
                int indexOfLastDead = c.getGrave().getDeads().size() - 1;
                if (indexOfLastDead >= 0) {
                    Dead dead = c.getGrave().getDeads().get(indexOfLastDead);
                    grave.add(dead.getFirstName());
                    grave.add(dead.getLastName());
                    if (dead.getFunerals().size() > 0) {
                        grave.add(dead.getFunerals().get(0).getDate().toString());
                    }
                    else {
                        grave.add("");
                    }
                } else {
                    grave.add("");
                    grave.add("");
                    grave.add("");
                }
                grave.add(String.valueOf(c.getGrave().getSurface()));
                grave.add("");
                grave.add("");
                grave.add(c.getGrave().getImageUri());

                return grave;
            }
        }

        return null;
    }

}
