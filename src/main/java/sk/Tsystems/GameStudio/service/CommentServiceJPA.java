package sk.Tsystems.GameStudio.service;

import sk.Tsystems.GameStudio.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class CommentServiceJPA implements CommentService{

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public void addComment(Comment comment) {
        this.entityManager.persist(comment);

    }

    @Override
    public List<Comment> getComments(String game) {
        return  entityManager
                .createQuery("select c from Comment c where c.game=:myGame order by c.commented_on desc")
                .setParameter("myGame",game)
                .getResultList();



    }

    @Override
    public void reset() {

    }
}
