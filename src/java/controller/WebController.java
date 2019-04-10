package controller;

import java.sql.SQLException;
import model.Kupac;
import model.Proizvod;
import model.Shop;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController 
{
    @RequestMapping(value="/prodaja",method=RequestMethod.GET)
    public String createForm(ModelMap model) throws ClassNotFoundException, SQLException
    {
        model.addAttribute("kupac",new Kupac());
        model.addAttribute("proizvod",new Proizvod());
        model.addAttribute("proizvodi",Proizvod.ListaProizvoda());
        model.addAttribute("kupci",Kupac.ListaKupaca());
        model.addAttribute("prodaje",Shop.ListaProdaja());
        return "prodaja";   
    }
    
    @RequestMapping(value = "/kupac.htm", method = RequestMethod.GET)
    public String getKupac(ModelMap model)
    {
        model.addAttribute("kupac",new Kupac());
        model.addAttribute("kupci",Kupac.ListaKupaca());
        return "kupac";  
    }
    @RequestMapping(value = "/proizvod.htm", method = RequestMethod.GET)
    public String getProizvod(ModelMap model)
    {
        model.addAttribute("proizvodi",Proizvod.ListaProizvoda());
        model.addAttribute("proizvod",new Proizvod());
        return "proizvod";  
    }
    
    @RequestMapping(value="/kupac",method=RequestMethod.POST)
    public String DodajKupca(@ModelAttribute("kupac") Kupac kupac, ModelMap model) throws ClassNotFoundException, SQLException
    {   
        kupac.DodajKupca();
        createForm(model); 
        return "kupac";        
    }
    
    @RequestMapping(value="/proizvod",method=RequestMethod.POST)
    public String DodajProizvod(@ModelAttribute("proizvod") Proizvod proizvod, ModelMap model) throws ClassNotFoundException, SQLException
    {   
        proizvod.DodajProizvod();
        createForm(model); 
        return "proizvod";        
    }

    @RequestMapping(value="/brisiKupca.htm",method=RequestMethod.POST)
    public String BrisiKupca(@ModelAttribute("kupac") Kupac kupac, ModelMap model) throws ClassNotFoundException, SQLException
    {   
        kupac.BrisiKupca();
        createForm(model); 
        return "kupac";        
    }        
       
    @RequestMapping(value="/brisiProizvod.htm",method=RequestMethod.POST)
    public String BrisiProizvod(@ModelAttribute("proizvod") Proizvod proizvod, ModelMap model) throws ClassNotFoundException, SQLException
    {   
        proizvod.BrisiProizvod();
        createForm(model); 
        return "proizvod";        
    }   
    
    @RequestMapping(value="/updateKupca.htm",method=RequestMethod.POST)
    public String updateKupac(@ModelAttribute("kupac") Kupac kupac, ModelMap model) throws ClassNotFoundException, SQLException
    {   
        kupac.updateKupac();
        createForm(model); 
        return "kupac";        
    }      
    
    @RequestMapping(value="/updateProizvod.htm",method=RequestMethod.POST)
    public String UpdateProizvod(@ModelAttribute("proizvod") Proizvod proizvod, ModelMap model) throws ClassNotFoundException, SQLException
    {   
        proizvod.UpdateProizvod();
        createForm(model); 
        return "proizvod";        
    }           
  
    @RequestMapping(value="/Prodaj.htm",method=RequestMethod.POST)
    public String izvrsiProdaju(@ModelAttribute("shop") Shop shop, ModelMap model) throws ClassNotFoundException, SQLException
    {   
        shop.prodaj();
        createForm(model); 
        return "prodaja";        
    }                  
    
    @ModelAttribute("shop")
    public Shop getShopObject() 
    {
        return new Shop();
    }

}
