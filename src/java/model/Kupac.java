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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


@Entity
@Table(name = "kupac")
public class Kupac 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idkupac")
    private int idkupac;
    @Column(name = "ime")
    private String ime;
    @Column(name = "prezime")
    private String prezime;
    
    @ManyToMany(
        fetch = FetchType.LAZY,
            cascade = {
        CascadeType.PERSIST, 
        CascadeType.MERGE
    })
    @JoinTable(
        name = "shop", 
        joinColumns = { @JoinColumn(name = "idkupac") }, 
        inverseJoinColumns = { @JoinColumn(name = "idproizvod") }
    )
    private List<Proizvod> proizvodi;
    
    
    public List<Proizvod> getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(List<Proizvod> proizvodi) {
        this.proizvodi = proizvodi;
    }
    
    public int getIdkupac() {
        return idkupac;
    }
 
    public void setIdkupac(int idkupac) {
        this.idkupac = idkupac;
    }
    
    public String getIme()
    {
        return ime;
    }
    public void setIme(String ime)
    {
        this.ime=ime;
    }
    
    public String getPrezime()
    {
        return prezime;
    }
    public void setPrezime(String prezime)
    {
        this.prezime=prezime;
    }
    
    
    
    
    
    public void DodajKupca() throws ClassNotFoundException, SQLException
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
    
    public void BrisiKupca()
    {
        Kupac pom=null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;
        String idString = String.valueOf(this.idkupac).trim();
        
        if (isNumeric(idString) && (Integer.valueOf(idString) > 0))
        {
            try 
            {
                tx = session.beginTransaction();

                pom = (Kupac)session.get(Kupac.class, Integer.valueOf(idString));
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
    
    public static List ListaKupaca()
    {
        Session session = HibernateUtil.createSessionFactory().openSession();
        List<Kupac> kupci = new ArrayList();
 
        String hql = "from Kupac";
        Query query = session.createQuery(hql);
        kupci=query.list();
        
        return kupci;
    }        
           
    @Override
    public String toString()
    {
        return "Kupac: "+ime+", prezime:"+prezime+" , id:"+idkupac;
    }
    
    public static boolean isNumeric(String s) 
    {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
    }     
    
    public void updateKupac()
    {
        Kupac pom=null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;
        
        String idString = String.valueOf(this.idkupac).trim();
        if (isNumeric(idString) && (Integer.valueOf(idString) > 0))
        {
            try 
            {
                tx = session.beginTransaction();
                pom = (Kupac) session.get(Kupac.class, Integer.valueOf(idString));
                if(pom!=null)  
                {                   
                    if (!ime.equals("") && !prezime.equals(""))
                    {
                        pom.setIme(ime);
                        pom.setPrezime(prezime);
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
 
}
