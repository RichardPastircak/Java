package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class RatingServiceJPA implements RatingService{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setRating(Rating rating) throws RatingException {
        //potentional mistake
        if(getRating(rating.getGame(), rating.getPlayer()) != 0){
            entityManager.createNamedQuery("Rating.deletePlayerRating").setParameter("game", rating.getGame()).setParameter("player", rating.getPlayer()).executeUpdate();
        }
        entityManager.persist(rating);
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        if(entityManager.createNamedQuery("Rating.getAverageRating").setParameter("game", game).getSingleResult() == null){
                return 0;
        }
        return ((Double)entityManager.createNamedQuery("Rating.getAverageRating").setParameter("game", game).getSingleResult()).intValue();
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        try {
            return (int)entityManager.createNamedQuery("Rating.getRating").setParameter("game", game).setParameter("player", player).getSingleResult();
        }catch (NoResultException e) {
            return -1;
        }
    }

    @Override
    public void reset() throws RatingException {
        entityManager.createNamedQuery("Rating.resetRatings").executeUpdate();
    }
}
