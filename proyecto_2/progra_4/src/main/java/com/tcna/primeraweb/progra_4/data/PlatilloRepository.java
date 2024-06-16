package com.tcna.primeraweb.progra_4.data;


import com.tcna.primeraweb.progra_4.logic.Categoria;
import com.tcna.primeraweb.progra_4.logic.Platillo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component("platilloRepository")
public class PlatilloRepository {
    List<Platillo> list;
    CategoriaRepository categoriaRepository;


    public Platillo findById(String id) {
        for (Platillo p : list) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public List<Platillo> findByCategoria(String id){
        List<Platillo> platillos=new ArrayList<Platillo>();
        for(Platillo p:list){
            if(p.getCategoria().getId().equals(id)){
                platillos.add(p);
            }
        }
        return platillos;
    }



    public List<Platillo> getAll() {
        return list;
    }




    @Autowired
    public PlatilloRepository(CategoriaRepository categoriaRepository) {

        this.categoriaRepository = categoriaRepository;

        list = new ArrayList<Platillo>();
        try {
            Categoria entradas = categoriaRepository.findById("111");
            list.add(new Platillo(entradas, "111", "Ensalada Capresse","Tomate, mozzarella y hojas de albahaca fresca", 5000));
            list.add(new Platillo(entradas,"222", "Crema Espinaca", "Crema a base caldo de pollo, leche, queso parmesano y abundante espinaca", 4000));
            list.add(new Platillo(entradas,"333", "Patacones", "Plátanos verdes fritos y majados, con guacamole dip a elección", 3500));
            list.add(new Platillo(entradas,"444", "Papas al Romero", "Papas horneadas con una mezcla de romero fresco orgánico y sal himalaya", 3000));

            Categoria carnes = categoriaRepository.findById("222");
            list.add(new Platillo(carnes, "555", "Lomito a la Parrilla", "Lomito prime con papas baby, chipotle y cebolla chalota", 15000));
            list.add(new Platillo(carnes, "666", "Picaña", "Picaña al horno con papas y pimientos", 17000));
            list.add(new Platillo(carnes,"777", "Fajitas de Pollo", "Tiras de pollo a la parrilla mezcladas con verduras y tortillas", 14000));
            list.add(new Platillo(carnes,"888", "Filete de Pescado", "Filete empanizado, con guarnición de arroz y ensalada", 16500));
        } catch (Exception ex) {}
    }


}

