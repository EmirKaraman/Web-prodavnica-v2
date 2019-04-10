package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import static model.Kupac.isNumeric;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Entity
@Table(name = "proizvod")
public class Proizvod 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idproizvod")
    private int idproizvod;
    @Column(name = "ime")
    private String ime;
    @Column(name = "kod")
    private String kod;
    
    @ManyToMany(mappedBy = "proizvodi",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Kupac> kupci;
    
    
    public List<Kupac> getKupci() {
        return kupci;
    }

    public void setKupci(List<Kupac> kupci) {
        this.kupci = kupci;
    }
    
    
    
    public int getIdproizvod()
    {
        return idproizvod;
    }
    
    public void setIdproizvod(int idproizvod)
    {
        this.idproizvod=idproizvod;
    }
    
    public String getIme()
    {
        return ime;
    }
    
    public void setIme(String ime)
    {
        this.ime=ime;
    }
    
    public String getKod()
    {
        return kod;
    }
    
    public void setKod(String kod)
    {
        this.kod=kod;
    }
        
    
    
    
    public static List ListaProizvoda()
    {
        Session session = HibernateUtil.createSessionFactory().openSession();
        List<Proizvod> proizvodi = new ArrayList();
        
        String hql = "from Proizvod";
        Query query = session.createQuery(hql);
        proizvodi=query.list();

        return proizvodi;
    }
    
    
    public void DodajProizvod() throws ClassNotFoundException, SQLException
    {
        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;
    
            try {
                tx = session.beginTransaction();
                session.persist(this);
                tx.commit();
            } catch (HibernateException e) {
            if (tx != null) {
            tx.rollback();
            }
            System.out.println(e);
            } finally {
            HibernateUtil.close();
            }
        
        session.close();
    }
    
    public void BrisiProizvod()
    {
        Proizvod pom=null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;
        String idString = String.valueOf(this.idproizvod).trim();
        
        if (isNumeric(idString) && (Integer.valueOf(idString) > 0))
        {
            try 
            {
                tx = session.beginTransaction();

                pom = (Proizvod)session.get(Proizvod.class, Integer.valueOf(idString)); 
                if(pom!=null)  
                {
                    session.delete(pom);
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
    }
    
    public void UpdateProizvod()
    {
        Proizvod pom=null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;
        
        String idString = String.valueOf(this.idproizvod).trim();
        if (isNumeric(idString) && (Integer.valueOf(idString) > 0))
        {
            try 
            {
                tx = session.beginTransaction();

                pom = (Proizvod) session.get(Proizvod.class, Integer.valueOf(idString));
                if(pom!=null)  
                {                   
                    if (!ime.equals("") && !kod.equals(""))
                    {
                        pom.setIme(ime);
                        pom.setKod(kod);
                        session.update(pom);
                    }
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
    
    
    @Override
    public String toString()
    {        
        return "Proizvod: "+ime+", kod: "+kod+", id: "+idproizvod+"\n";    
    }
}
