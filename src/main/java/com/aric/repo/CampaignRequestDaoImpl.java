/**
 * 
 */
package com.aric.repo;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aric.domain.Campaign;
import com.aric.domain.CampaignRequest;
import com.aric.domain.enums.CampaignRequestStatus;

/**
 * @author dursun
 * 
 */
@Repository
@Transactional
public class CampaignRequestDaoImpl implements CampaignRequestDao {

	@Autowired
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aric.repo.CampaignRequestDao#createCampaignRequest()
	 */
	@Override
	public CampaignRequest createCampaignRequest(Campaign campaign,
			Long customerId, Date trxDate) {
		CampaignRequest campaignRequest = new CampaignRequest(campaign,
				customerId, CampaignRequestStatus.WAITING);
		this.em.persist(campaignRequest);
		return campaignRequest;
	}

	@Override
	public CampaignRequest findCampaignRequest(Long campaignRequestId) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<CampaignRequest> query = criteriaBuilder
				.createQuery(CampaignRequest.class);
		Root<CampaignRequest> campaignRequest = query
				.from(CampaignRequest.class);
		query.select(campaignRequest);
		query.where(criteriaBuilder.equal(
				campaignRequest.get("campaignRequestId"), campaignRequestId));
		return em.createQuery(query).getSingleResult();
	}

	@Override
	public CampaignRequest update(CampaignRequest campaignRequest) {
		CampaignRequest newRequest = em.merge(campaignRequest);
		return newRequest;
	}
}
