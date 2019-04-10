package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Entity
@Table(name = "shop")
public class Shop 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idshop")
    private int idshop;
    @Column(name = "idkupac")
    private int idKupac;
    @Column(name = "idproizvod")
    private int idProizvod;

    public int getIdshop() {
        return idshop;
    }

    public void setIdshop(int idshop) {
        this.idshop = idshop;
    }
    
    public int getIdKupac()
    {
        return idKupac;
    }
       
    public void setIdKupac(int IdKupac)
    {
        this.idKupac=IdKupac;
    }
    
    public int getIdProizvod()
    {
        return idProizvod;
    }
       
    public void setIdProizvod(int IdProizvod)
    {
        this.idProizvod=IdProizvod;
    }
    
    public void prodaj()
    {
            Session session = HibernateUtil.createSessionFactory().openSession();
            Transaction tx = null;

            if((idKupac > 0) && (idProizvod > 0))
            {
                try 
                {
                    tx = session.beginTransaction();
                    Kupac pomKupac = (Kupac)session.get(Kupac.class, idKupac);
                    Proizvod pomProizvod = (Proizvod)session.get(Proizvod.class, idProizvod);
                    if(pomKupac!=null && pomProizvod!=null)  
                    {
                        pomKupac.getProizvodi().add(pomProizvod);
			session.save(pomKupac);
                    }
                    tx.commit();
                } catch (HibernateException e) {
                if (tx != null) {
                tx.rollback();
                }
                System.out.println(e);
                } finally {
                HibernateUtil.close();
                }
            }
            session.close();
    }  
    
    public static List ListaProdaja()
    {
        Session session = HibernateUtil.createSessionFactory().openSession();
        List<Shop> prodaje = new ArrayList();
 
        String hql = "from Shop";
        Query query = session.createQuery(hql);
        prodaje=query.list();
        session.close();
        return prodaje;
    }        
    
    @Override
    public String toString()
    {
        Session session = HibernateUtil.createSessionFactory().openSession();
        Kupac pomKupac = (Kupac)session.get(Kupac.class, idKupac);
        Proizvod pomProizvod = (Proizvod)session.get(Proizvod.class, idProizvod);
        
        if(pomKupac!=null && pomProizvod!=null)  
        {
               return "Kupac "+pomKupac.getIme()+" "+pomKupac.getPrezime()+" je kupio proizvod: "+pomProizvod.getIme();
        }
        session.close();
        return "Kupac id: "+idKupac+", Proizvod id :"+idProizvod;
    }
}
