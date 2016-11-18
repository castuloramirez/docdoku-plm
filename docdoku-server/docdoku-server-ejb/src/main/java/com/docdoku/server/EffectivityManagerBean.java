package com.docdoku.server;

import com.docdoku.core.exceptions.CreationException;
import com.docdoku.core.exceptions.EffectivityAlreadyExistsException;
import com.docdoku.core.exceptions.EffectivityNotFoundException;
import com.docdoku.core.product.*;
import com.docdoku.core.security.UserGroupMapping;
import com.docdoku.core.services.IAccountManagerLocal;
import com.docdoku.core.services.IEffectivityManagerLocal;
import com.docdoku.server.dao.EffectivityDAO;
import com.docdoku.server.dao.PartRevisionDAO;

import javax.annotation.security.DeclareRoles;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.Set;

@DeclareRoles({UserGroupMapping.GUEST_PROXY_ROLE_ID, UserGroupMapping.REGULAR_USER_ROLE_ID, UserGroupMapping.ADMIN_ROLE_ID})
@Local(IEffectivityManagerLocal.class)
@Stateless(name = "EffectivityManagerBean")
public class EffectivityManagerBean implements IEffectivityManagerLocal {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private IAccountManagerLocal accountManager;

    @Override
    public SerialNumberBasedEffectivity createSerialNumberBasedEffectivity(
            PartRevision pPartRevision, String pName, String pDescription, ConfigurationItem pConfigurationItem, String pStartNumber, String pEndNumber)
            throws EffectivityAlreadyExistsException, CreationException {
        EffectivityDAO effectivityDAO = new EffectivityDAO(em);
        SerialNumberBasedEffectivity serialNumberBasedEffectivity = new SerialNumberBasedEffectivity();
        serialNumberBasedEffectivity.setName(pName);
        serialNumberBasedEffectivity.setDescription(pDescription);
        serialNumberBasedEffectivity.setConfigurationItem(pConfigurationItem);
        serialNumberBasedEffectivity.setStartNumber(pStartNumber);
        serialNumberBasedEffectivity.setEndNumber(pEndNumber);
        effectivityDAO.createEffectivity(serialNumberBasedEffectivity);

        Set<Effectivity> effectivities = pPartRevision.getEffectivities();
        effectivities.add(serialNumberBasedEffectivity);
        pPartRevision.setEffectivities(effectivities);
        PartRevisionDAO partRevisionDAO = new PartRevisionDAO(em);
        partRevisionDAO.updateRevision(pPartRevision);
        return serialNumberBasedEffectivity;
    }

    @Override
    public DateBasedEffectivity createDateBasedEffectivity(
            PartRevision pPartRevision, String pName, String pDescription, Date pStartDate, Date pEndDate)
            throws EffectivityAlreadyExistsException, CreationException {
        EffectivityDAO effectivityDAO = new EffectivityDAO(em);
        DateBasedEffectivity dateBasedEffectivity = new DateBasedEffectivity();
        dateBasedEffectivity.setName(pName);
        dateBasedEffectivity.setDescription(pDescription);
        dateBasedEffectivity.setStartDate(pStartDate);
        dateBasedEffectivity.setEndDate(pEndDate);
        effectivityDAO.createEffectivity(dateBasedEffectivity);

        Set<Effectivity> effectivities = pPartRevision.getEffectivities();
        effectivities.add(dateBasedEffectivity);
        pPartRevision.setEffectivities(effectivities);
        PartRevisionDAO partRevisionDAO = new PartRevisionDAO(em);
        partRevisionDAO.updateRevision(pPartRevision);
        return dateBasedEffectivity;
    }

    @Override
    public LotBasedEffectivity createLotBasedEffectivity(
            PartRevision pPartRevision, String pName, String pDescription, ConfigurationItem pConfigurationItem, String pStartLotId, String pEndLotId)
            throws EffectivityAlreadyExistsException, CreationException {
        EffectivityDAO effectivityDAO = new EffectivityDAO(em);
        LotBasedEffectivity lotBasedEffectivity = new LotBasedEffectivity();
        lotBasedEffectivity.setName(pName);
        lotBasedEffectivity.setDescription(pDescription);
        lotBasedEffectivity.setConfigurationItem(pConfigurationItem);
        lotBasedEffectivity.setStartLotId(pStartLotId);
        lotBasedEffectivity.setEndLotId(pEndLotId);
        effectivityDAO.createEffectivity(lotBasedEffectivity);

        Set<Effectivity> effectivities = pPartRevision.getEffectivities();
        effectivities.add(lotBasedEffectivity);
        pPartRevision.setEffectivities(effectivities);
        PartRevisionDAO partRevisionDAO = new PartRevisionDAO(em);
        partRevisionDAO.updateRevision(pPartRevision);
        return lotBasedEffectivity;
    }

    @Override
    public Effectivity getEffectivity(int pId) throws EffectivityNotFoundException {
        return new EffectivityDAO(em).loadEffectivity(pId);
    }

    @Override
    public Effectivity updateEffectivity(int pId, String pName, String pDescription) throws EffectivityNotFoundException {
        EffectivityDAO effectivityDAO = new EffectivityDAO(em);
        Effectivity effectivity = effectivityDAO.loadEffectivity(pId);
        effectivity.setName(pName);
        effectivity.setDescription(pDescription);
        effectivityDAO.updateEffectivity(effectivity);
        return effectivity;
    }

    @Override
    public SerialNumberBasedEffectivity updateSerialNumberBasedEffectivity(int pId, String pName, String pDescription, String pStartNumber, String pEndNumber) throws EffectivityNotFoundException {
        EffectivityDAO effectivityDAO = new EffectivityDAO(em);
        SerialNumberBasedEffectivity serialNumberBasedEffectivity = (SerialNumberBasedEffectivity) effectivityDAO.loadEffectivity(pId);
        serialNumberBasedEffectivity.setName(pName);
        serialNumberBasedEffectivity.setDescription(pDescription);
        serialNumberBasedEffectivity.setStartNumber(pStartNumber);
        serialNumberBasedEffectivity.setEndNumber(pEndNumber);
        effectivityDAO.updateEffectivity(serialNumberBasedEffectivity);
        return serialNumberBasedEffectivity;
    }

    @Override
    public DateBasedEffectivity updateDateBasedEffectivity(int pId, String pName, String pDescription, Date pStartDate, Date pEndDate) throws EffectivityNotFoundException {
        EffectivityDAO effectivityDAO = new EffectivityDAO(em);
        DateBasedEffectivity dateBasedEffectivity = (DateBasedEffectivity) effectivityDAO.loadEffectivity(pId);
        dateBasedEffectivity.setName(pName);
        dateBasedEffectivity.setDescription(pDescription);
        dateBasedEffectivity.setStartDate(pStartDate);
        dateBasedEffectivity.setEndDate(pEndDate);
        effectivityDAO.updateEffectivity(dateBasedEffectivity);
        return dateBasedEffectivity;
    }

    @Override
    public LotBasedEffectivity updateLotBasedEffectivity(int pId, String pName, String pDescription, String pStartLotId, String pEndLotId) throws EffectivityNotFoundException {
        EffectivityDAO effectivityDAO = new EffectivityDAO(em);
        LotBasedEffectivity lotBasedEffectivity = (LotBasedEffectivity) effectivityDAO.loadEffectivity(pId);
        lotBasedEffectivity.setName(pName);
        lotBasedEffectivity.setDescription(pDescription);
        lotBasedEffectivity.setStartLotId(pStartLotId);
        lotBasedEffectivity.setEndLotId(pEndLotId);
        effectivityDAO.updateEffectivity(lotBasedEffectivity);
        return lotBasedEffectivity;
    }

    @Override
    public void deleteEffectivity(PartRevision pPartRevision, int pId) throws EffectivityNotFoundException {
        EffectivityDAO effectivityDAO = new EffectivityDAO(em);
        Effectivity effectivity = effectivityDAO.loadEffectivity(pId);
        boolean effectivityFind = false;

        Object[] objects = pPartRevision.getEffectivities().toArray();
        for(int i=0; i<objects.length && !effectivityFind; i++) {
            if(((Effectivity)objects[i]).getId() == effectivity.getId()) {
                effectivityFind = true;
                PartRevisionDAO partRevisionDAO = new PartRevisionDAO(em);
                partRevisionDAO.removePartRevisionEffectivity(pPartRevision, ((Effectivity)objects[i]));
            }
        }

        effectivityDAO.removeEffectivity(effectivity);
    }
}
